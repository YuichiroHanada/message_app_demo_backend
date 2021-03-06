package com.example.messageApp.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserResp implements Serializable {

    private String account;

    private String name;
}
