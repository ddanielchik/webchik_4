package org.example.lab_4_jakartaee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.SessionBean;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.managerDB.UserDAO;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;

@Stateless
public class UserService {

    @EJB
    private UserDAO userDAO;

    @EJB
    SecurityContext securityContext;



    public User register(String username,  String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDAO.save(user);
        return user;
    }

    public User login(String username, String password) {

    }

//    public void logout(HttpServletRequest request) {
//        HttpServletRequest.log
//    }




}
