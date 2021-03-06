package com.example.messageApp.model;

import com.example.messageApp.domain.MessageDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageListResp implements Serializable {

    private Integer userId;

    private List<MessageDO> messageList;
}
