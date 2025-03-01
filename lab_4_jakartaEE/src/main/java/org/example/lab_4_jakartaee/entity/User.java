package org.example.lab_4_jakartaee.entity;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.*;

@Entity //делаем класс табличкой для бдшки
@Table(name = "USER_TABLE")
public class User {
    @Id // задает примири ку
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
//        if (isPasswordValid(password)) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
//        } else {
//            throw new IllegalArgumentException("Пароль должен содержать хотя бы 4 буквы, одну цифру и один спец. символ.");
//        }
    }

    public boolean chekPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }


    public static boolean isPasswordValid(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Дурачек совсем, наивный.. Пустую строку решил мне отправить");
        }
//        String regex = "^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$";
        String regex = "^(?=(.*[a-zA-Z]){4,})(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        //если совпадения мяу мяу мяу
        if (!matcher.matches()) throw new IllegalArgumentException("Пароль должен содержать хотя бы 4 буквы, одну цифру и один спец. символ.");

        // Проверяем, соответствует ли пароль регулярному выражению
        return matcher.matches();
    }

    public User() {

    }


}
