package com.example.messageApp.manager;

import com.example.messageApp.dao.UserDAO;
import com.example.messageApp.domain.UserDO;
import com.example.messageApp.model.UserResp;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import javax.annotation.Resource;

@Service
public class UserManager {

    @Resource
    UserDAO userDAO;

    @Resource
    BCryptPasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UserManager.class);


    public void saveUser(String account, String password, String name) {

        UserDO userDO = new UserDO();
        userDO.setAccount(account);
        userDO.setName(name);
        String pass = passwordEncoder.encode(password);
        userDO.setPassword(pass);
        userDO.setAdmin(false);
        userDAO.save(userDO);
    }

    public UserResp getUserDO(@AuthenticationPrincipal UserDO user) {

        if (user != null) {
            UserResp resp = new UserResp();
            resp.setAccount(user.getAccount());
            resp.setName(user.getName());
            return resp;
        }
        else {
            logger.error("not your account in db");
            return null;
        }
    }

//    public Boolean changeUserPassword(@RequestBody ChangePasswordReq req) {
//
//        UserDO userDO = userDAO.getByAccount(req.getAccount());
//        if (userDO == null) {
//            logger.error("account is not correct");
//            return false;
//        }
//        if (userDO.getPassword().equals(req.getPassword())) {
//            userDO.setPassword(req.getNewPassword());
//            userDAO.save(userDO);
//            return true;
//        }
//        else{
//            logger.error("old password is not correct");
//            return false;
//        }
//    }
}
