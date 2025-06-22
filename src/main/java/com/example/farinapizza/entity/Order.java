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
    private int id;

    @ManyToOne
    // @JoinColumn 可省略，帶不建議省略(較易維護)
    @JoinColumn(name = "member_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_order_member") // 指定外鍵名稱，若有設定 ddl-auto=create/update 會自動產生
    )
    private Member member;

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