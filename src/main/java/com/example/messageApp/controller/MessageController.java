package com.example.messageApp.controller;

import com.example.messageApp.domain.UserDO;
import com.example.messageApp.manager.MessageManager;
import com.example.messageApp.model.MessageListResp;
import com.example.messageApp.security.SimpleLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Resource
    MessageManager messageManager;

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);


    @GetMapping("/{roomId}/show")
    public MessageListResp messageShow(@PathVariable("roomId") Integer roomId, @AuthenticationPrincipal SimpleLoginUser user) {

        UserDO userDO = user.getUser();

        try {
            return messageManager.messageShow(roomId, userDO.getId());
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }

    @PostMapping("/{roomId}/send")
    public String sendMessage(@PathVariable("roomId") Integer roomId, @RequestParam String message, @AuthenticationPrincipal SimpleLoginUser user) {

        UserDO userDO = user.getUser();

        if (messageManager.sendMessage(roomId, userDO.getId(), message) != null) {

            return "送信完了!";
        } else {
            logger.error("roomId or message is not exist");
            return "送信失敗、、";
        }
    }
}
