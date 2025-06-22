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

    @Column(length = 50)
    private String username;

    @Column(length = 100, unique = true) // (unique = true) 保證唯一 + 自動建立唯一索引，提高該表的查詢效能
    private String email;

    @Column
    private String password;

    @Column
    private short registered; // 是否為已註冊，1為已註冊
}