package org.example.lab_4_jakartaee.entity;

import jakarta.persistence.*;

@Entity // помечаем класс как jpa сущность, т.е. как объект который
// будет сопроставляьься с таблицей бд
@Table(name = "POINT_TABLE")

public class Point {
    @Id // PK~~
    @GeneratedValue(strategy = GenerationType.IDENTITY) // увеличивается на +1 ну бдшка и прочее))

    private long id;
    private float x;
    private float y;
    private float razmer;
    private boolean condition; // попала, не попала хуй пойми))

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
