package com.example.messageApp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomResp implements Serializable {

    private Integer roomId;

    private String roomName;

    private Integer myId;

    private Integer yourId;

}
