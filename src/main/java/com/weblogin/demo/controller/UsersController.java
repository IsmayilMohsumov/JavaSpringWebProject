package com.weblogin.demo.controller;

import com.weblogin.demo.model.User;
import com.weblogin.demo.service.NotificationService;
import com.weblogin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;



    @PostMapping("/editUserById")
    public ResponseEntity<Object> editUserById(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
//        System.out.println("gelen id:"+userId);
        User user = userService.findUserById(userId);
//        System.out.println("gelen user:"+user);

        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(
            @RequestParam("id")Integer userId, @RequestParam("name")String userName,@RequestParam("surname")String userSurname,@RequestParam("email")String userEmail) //@RequestParam String skill,
    {

        User user = userService.findUserById(userId);
        userService.updateUser(userName,userSurname,userEmail,user.getId());
        return new ResponseEntity<Object>(user, HttpStatus.OK);

    }

    @PostMapping("/deleteUserById")
    public ResponseEntity<Object> deleteUser(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        System.out.println("gelen id:"+userId);
        userService.deleteUserById(userId);

        return new ResponseEntity<Object>(userId, HttpStatus.OK);
    }



}
