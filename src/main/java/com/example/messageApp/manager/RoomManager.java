package com.example.messageApp.manager;

import com.example.messageApp.dao.RoomDAO;
import com.example.messageApp.dao.Room_UserDAO;
import com.example.messageApp.dao.UserDAO;
import com.example.messageApp.domain.RoomDO;
import com.example.messageApp.domain.Room_UserDO;
import com.example.messageApp.domain.UserDO;
import com.example.messageApp.model.RoomResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomManager {

    @Resource
    RoomDAO roomDAO;

    @Resource
    Room_UserDAO room_userDAO;

    @Resource
    UserDAO userDAO;

    private static Logger logger = LoggerFactory.getLogger(RoomManager.class);

    public Boolean addFriend(String address, UserDO user) {

        UserDO friend = userDAO.getByAccount(address);
        Integer friendId = friend.getId();
        Integer userId = user.getId();

        List<Integer> friendRoomList = room_userDAO.findRoomIdByUserId(friendId);
        List<Integer> userRoomList = room_userDAO.findRoomIdByUserId(userId);

        for (Integer i : friendRoomList) {
            if(userRoomList.contains(i)) {
                logger.error("room is already existed");
                return false;
            }
        }

        roomDAO.save(new RoomDO());
        RoomDO roomDO = roomDAO.findTopByOrderByIdDesc();
        Integer roomId = roomDO.getId();
        Room_UserDO room_userDO_user = new Room_UserDO();
        Room_UserDO room_userDO_friend = new Room_UserDO();
        room_userDO_user.setRoomId(roomId);
        room_userDO_user.setUserId(userId);

        room_userDO_friend.setRoomId(roomId);
        room_userDO_friend.setUserId(friendId);

        room_userDAO.save(room_userDO_user);
        room_userDAO.save(room_userDO_friend);

        return true;

    }

    public List<RoomResp> showFriends(UserDO user) {
        List<RoomResp> roomRespList = new ArrayList<>();

        Integer userId = user.getId();
        List<Integer> userRoomList = room_userDAO.findRoomIdByUserId(userId);

        for (Integer i : userRoomList) {
            RoomResp roomResp = new RoomResp();
            roomResp.setRoomId(i);
            List<Integer> userIdList = room_userDAO.findUserIdByRoomId(i);


            for (Integer j : userIdList) {
                if (!userId.equals(j)) {

                    String name = userDAO.getNameById(j);
                    roomResp.setRoomName(name);
                }
            }
            roomRespList.add(roomResp);
        }

        return roomRespList;

    }


}
