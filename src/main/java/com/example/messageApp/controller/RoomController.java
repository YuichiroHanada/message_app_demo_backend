package com.example.messageApp.controller;

import com.example.messageApp.domain.UserDO;
import com.example.messageApp.manager.RoomManager;
import com.example.messageApp.model.RoomResp;
import com.example.messageApp.security.SimpleLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    RoomManager roomManager;

    private static Logger logger = LoggerFactory.getLogger(RoomController.class);

    @PostMapping("/friend")
    public String addFriend(@RequestParam String address, @AuthenticationPrincipal SimpleLoginUser user) {

        UserDO userDO = user.getUser();

        if (roomManager.addFriend(address, userDO)) {
            return "友達登録が完了し、新しい部屋が出来ました!";
        }
        return "新しい部屋が出来ませんでした。既に相手が作っている可能性があります。";
    }

    @GetMapping("/show")
    public List<RoomResp> showFriends(@AuthenticationPrincipal SimpleLoginUser user) {

        UserDO userDO = user.getUser();

        try {
            return roomManager.showFriends(userDO);
        }
        catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
    }
}
