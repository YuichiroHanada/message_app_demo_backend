package com.example.messageApp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "room_user_info")
public class Room_UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer roomId;

    @Column
    private Integer userId;
}
