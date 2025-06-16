package com.example.farinapizza.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Column
    private String email;

    @Column
    private String branch;

    @Column
    private String date;

    @Column
    private String time;

    @Column
    private String people;

    @Column
    private String note;
}