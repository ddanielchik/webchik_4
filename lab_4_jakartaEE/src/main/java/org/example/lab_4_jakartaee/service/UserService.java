package org.example.lab_4_jakartaee.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.managerDB.UserDAO;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public boolean exists(String username) {
        return userDAO.existsByUsername(username);
    }

    public User register(String username, String password) {
        if (exists(username)) throw new IllegalArgumentException("username already exists");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDAO.save(user);
        return user;
    }

//    public void logout(HttpServletRequest request) {
//        HttpServletRequest.log
//    }


}
