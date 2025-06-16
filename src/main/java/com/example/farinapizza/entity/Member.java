package com.example.farinapizza.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private short registered; // 是否為已註冊，1為已註冊
}