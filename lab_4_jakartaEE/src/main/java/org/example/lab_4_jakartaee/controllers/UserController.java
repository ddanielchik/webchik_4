package org.example.lab_4_jakartaee.controllers;

import jakarta.ejb.EJB;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.service.UserService;

@Path("/user")
public class UserController {
    @EJB
    UserService userService;

    @POST
    @Path("/register")
    public Response register(
            @FormParam("username") String name,
            @FormParam("password") String password
    ) {
        User user = userService.register(name, password);
        return Response.ok(user).build();
    }


    @POST
    @Path("/login")
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {

    }


}
