package com.example.messageApp.dao;

import com.example.messageApp.domain.Room_UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Room_UserDAO extends JpaRepository<Room_UserDO, Integer> {

    @Query("select r.roomId from Room_UserDO r where r.userId = ?1")
    List<Integer> findRoomIdByUserId(Integer userId);

    @Query("select r.userId from Room_UserDO r where r.roomId = ?1")
    List<Integer> findUserIdByRoomId(Integer roomId);
}
