package com.example.messageApp.dao;

import com.example.messageApp.domain.RoomDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<RoomDO, Integer> {


    RoomDO findTopByOrderByIdDesc();
}
