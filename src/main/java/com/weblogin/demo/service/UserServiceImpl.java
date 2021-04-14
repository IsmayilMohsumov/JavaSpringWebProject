package com.weblogin.demo.service;

import com.weblogin.demo.model.Role;
import com.weblogin.demo.model.User;
import com.weblogin.demo.repository.RoleRepository;
import com.weblogin.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        u.setImage(u.getImage());
        u.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        u.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(u);
    }

    @Override
    public void saveUserAndImage(User u, String url) {
        u.setPassword(encoder.encode(u.getPassword()));
        u.setImage(url);
        u.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        u.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(u);
    }

    @Override
    public boolean isUserAlreadyPresent(User u) {
        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(u.getEmail());
        if(existingUser!=null){
            isUserAlreadyExists=true;
        }

        return isUserAlreadyExists;
    }



    @Override
    public void addImage(Integer id, String url) {
        User user= userRepository.findById(id).get();
        user.setImage(url);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail;
    }

    @Override
    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;

    }

    @Override
    public boolean deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public boolean isUserPresentCheckByEmail(String email) {
        boolean isUserAlreadyExists = false;
        User existingUser = userRepository.findByEmail(email);
        if(existingUser!=null){
            isUserAlreadyExists=true;
        }

        return isUserAlreadyExists;
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameLike("%"+name+"%");
    }

    @Override
    public Integer findIdByEmail(String email) {
        Integer idByEmail = userRepository.findIdByEmail(email);
        return idByEmail;
    }

    @Override
    public void updateDesc(String description, Integer userId) {
        userRepository.updateDesc(description,userId);
    }

    @Override
    public void updateUser(String name, String surname, String email, Integer id) {
        userRepository.updateUser(name,surname,email,id);
    }
}
