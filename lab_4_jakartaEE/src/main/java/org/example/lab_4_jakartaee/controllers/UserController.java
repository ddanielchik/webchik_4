package org.example.lab_4_jakartaee.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.service.UserService;
import org.example.lab_4_jakartaee.utils.JwtUtil;

@Path("/user")  
public class UserController {

    @EJB
    UserService userService;

    @Inject
    JwtUtil jwtUtil;

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(UserCredentials credentials) {
        if (userService.exists(credentials.getUsername())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        User user = userService.register(credentials.getUsername(), credentials.getPassword());
        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials credentials) {
        String token = jwtUtil.generateToken(credentials.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(credentials.getUsername());
        TokenResponse tokenResponse = new TokenResponse(token, refreshToken);
        return Response.ok(tokenResponse).build();
    }

    @POST
    @Path("/refresh")
    public Response refresh(RefreshRequest refreshRequest) {
        if (jwtUtil.validateToken(refreshRequest.getRefreshToken(), jwtUtil.extractUsername(refreshRequest.getRefreshToken()))) {
            String username = jwtUtil.extractUsername(refreshRequest.getRefreshToken());
            String newAccessToken = jwtUtil.generateToken(username);
            String newRefreshToken = jwtUtil.generateRefreshToken(username);
            return Response.ok(new TokenResponse(newAccessToken, newRefreshToken)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid refresh token").build();
        }
    }

    // Обработчик OPTIONS запроса для /login маршрута, для поддержки CORS
    @OPTIONS
    @Path("/login")
    public Response optionsLogin() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
    @OPTIONS
    @Path("/register")
    public Response optionsRegister() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }



    public static class UserCredentials {
        private String username;
        private String password;

        public UserCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class TokenResponse {
        private String token;
        private String refreshToken;

        public TokenResponse(String token, String refreshToken) {
            this.token = token;
            this.refreshToken = refreshToken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    public static class ValidationResponse {
        private boolean valid;

        public ValidationResponse(boolean valid) {
            this.valid = valid;
        }

        public boolean isValid() {
            return valid;
        }
    }

    public static class RefreshRequest {
        private final String refreshToken;

        public RefreshRequest(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
    }
}
