package com.example.messageApp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "room_info")
public class RoomDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
