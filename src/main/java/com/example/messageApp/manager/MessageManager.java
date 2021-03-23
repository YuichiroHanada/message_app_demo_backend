package com.example.messageApp.manager;

import com.example.messageApp.dao.MessageDAO;
import com.example.messageApp.domain.MessageDO;
import com.example.messageApp.model.MessageListResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageManager {

    @Resource
    MessageDAO messageDAO;

    private static Logger logger = LoggerFactory.getLogger(MessageManager.class);


    public MessageListResp messageShow(Integer roomId, Integer userId) {

        if (!(roomId == null)) {

            MessageListResp messageListResp = new MessageListResp();
            messageListResp.setUserId(userId);

            List<MessageDO> messageDOList = messageDAO.getAllByRoomId(roomId);

            messageListResp.setMessageList(messageDOList);

            return messageListResp;

        } else {

            logger.error("roomId is null");
            return null;
        }

    }

    public String sendMessage(Integer roomId, Integer userId, String message) {

        if (roomId == null) {
            logger.error("roomId is null");
            return null;
        }
        else if (message == null) {
            logger.error("message is null");
            return null;
        } else {
            Date date = new Date();
            MessageDO messageDO = new MessageDO();
            messageDO.setRoomId(roomId);
            messageDO.setSendId(userId);
            messageDO.setMessage(message);

            messageDAO.save(messageDO);

            return "送信完了!";

        }

    }
}
