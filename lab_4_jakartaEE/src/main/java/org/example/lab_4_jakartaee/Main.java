package org.example.lab_4_jakartaee;

import org.example.lab_4_jakartaee.entity.User;
import org.example.lab_4_jakartaee.managerDB.UserDAO;
import org.example.lab_4_jakartaee.service.UserService;


public class Main {
    public static void main(String[] args) {
        User user = new User();
        User user1 = new User();
        user1.setPassword("1365244");
        user1.setUsername("aboba");

        UserDAO pidaras = new UserDAO();
        pidaras.save(user1);
        UserService userService = new UserService();
        userService.register("aboba", "AbcD1!");
        userService.login("aboba", "AbcD1!");
        userService.login("aboba", "aboba123");

        System.out.println(user+ "\n"+ user1.getUsername());
    }
}
