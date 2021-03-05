package com.example.messageApp.dao;

import com.example.messageApp.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<UserDO, Integer> {

    UserDO getByAccount(String account);

    UserDO getById(Integer id);

    @Query("select r.name from UserDO r where r.id = ?1")
    String getNameById(Integer id);

}
