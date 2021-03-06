package com.example.messageApp.dao;

import com.example.messageApp.domain.MessageDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDAO extends JpaRepository<MessageDO, Integer> {

    List<MessageDO> getAllByRoomId(Integer roomId);
}
