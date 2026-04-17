package com.example.schedule_app.entity;

import com.example.schedule_app.dto.UpdateUserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public void update(UpdateUserRequest request) {
        this.username = request.getUsername();
    }
}
