package org.example.lab_4_jakartaee.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.service.UserService;
import org.example.lab_4_jakartaee.util.JwtUtil;

@Path("/user")
public class UserController {
    @EJB
    UserService userService;

    @Inject
    JwtUtil jwtUtil;

    @POST
    @Path("/register")
    public Response register(
            @FormParam("username") String name,
            @FormParam("password") String password
    ) {
        if (userService.exists(name))
            return Response.status(Response.Status.CONFLICT).build();
        User user = userService.register(name, password);
        return Response.ok(user).build();
    }


    @POST
    @Path("/login")
    public Response login(UserCredentials credentials) {
        String token = jwtUtil.generateToken(credentials.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(credentials.getUsername());
        return Response.ok(new TokenResponse(token, refreshToken)).build();
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

    public static class UserCredentials {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public RefreshRequest(String refreshToken){
            this.refreshToken = refreshToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

    }


}
