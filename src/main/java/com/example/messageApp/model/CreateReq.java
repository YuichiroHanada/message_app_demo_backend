package com.example.messageApp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateReq implements Serializable {

    private String account;

    private String password;

    private String name;
}
