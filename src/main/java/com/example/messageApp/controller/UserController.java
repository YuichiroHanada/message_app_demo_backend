package com.example.messageApp.controller;

import com.example.messageApp.domain.UserDO;
import com.example.messageApp.manager.UserManager;
import com.example.messageApp.model.CreateReq;
import com.example.messageApp.security.SimpleLoginUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

   @Resource
    UserManager userManager;

   @GetMapping("/hello")
   public String hello() {
       return "hello";
   }

   @PostMapping("/create")
   public String create(@RequestBody CreateReq req) {

       System.out.println(req.getAccount());
       try {
           userManager.saveUser(req.getAccount(), req.getPassword(), req.getName());
           return "OK";

       }
       catch (Exception e) {
           return "failed";
       }

    }

    @GetMapping("/get")
    public String getUser(@AuthenticationPrincipal SimpleLoginUser user) {

       UserDO userDO = user.getUser();
       return userDO.getAccount();

    }


}
