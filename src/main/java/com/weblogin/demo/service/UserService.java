package com.weblogin.demo.service;

import com.weblogin.demo.model.User;

import java.util.List;

public interface UserService {
    public void saveUser(User u);

    public void saveUserAndImage(User u,String url);
    public boolean isUserAlreadyPresent(User u );

    public void addImage(Integer id, String url);

    public User findByEmail(String email);

    public List<User> findAll();

    public boolean deleteUserById(Integer id);

    public User findUserById(Integer id);


    boolean isUserPresentCheckByEmail(String email);

    List<User> findByName(String name);

    public Integer findIdByEmail(String email);

    public void  updateDesc(String description,Integer userId);

    public  void updateUser(String name,String surname,String email,Integer id);
}
