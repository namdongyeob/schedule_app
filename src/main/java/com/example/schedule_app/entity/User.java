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

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public void update(UpdateUserRequest request) {
        this.username = request.getUsername();
    }
}
