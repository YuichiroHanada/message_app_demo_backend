package com.example.messageApp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String account;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Boolean admin;




}
