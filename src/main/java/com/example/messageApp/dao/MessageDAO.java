package com.example.messageApp.dao;

import com.example.messageApp.domain.MessageDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDAO extends JpaRepository<MessageDO, Integer> {
}
