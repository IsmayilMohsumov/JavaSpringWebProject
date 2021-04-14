package com.weblogin.demo.controller;

import com.weblogin.demo.model.User;
import com.weblogin.demo.repository.UserSkillRepository;
import com.weblogin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class AuthenticationController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @Autowired
    private UserService userService;

    @Autowired
    private UserSkillRepository userSkillRepository;


    @RequestMapping(value = {"/login","/"},method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;

    }



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("home");
        String email= principal.getName();
        User userByEmail = userService.findByEmail(email);
        modelAndView.addObject("detailsOfUser",userByEmail);
        modelAndView.addObject("skillsOfUser",userSkillRepository.findUserSkillsByUserId(userByEmail.getId()));
        return modelAndView;
    }


    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView adminPage(){
        List<User> all = userService.findAll();
        ModelAndView model = new ModelAndView("admin");
        model.addObject("userList",all);
        return model;
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deleteUserById(@RequestParam(defaultValue = "")Integer id){
        userService.deleteUserById(id);

        List<User> all = userService.findAll();
        ModelAndView model = new ModelAndView("admin");
        model.addObject("userList",all);
        return model;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
    public ModelAndView editUserById(@PathVariable("id")Integer id){
        System.out.println("79cu setir:"+id);

        //
        User userById = userService.findUserById(id);
        ModelAndView model = new ModelAndView("admin");
        model.addObject("userList",userById);
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }



    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap,@RequestParam("files") MultipartFile[] files) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage","Something went wrong!");
            System.out.println("Controller 58 ci setir");
            modelMap.addAttribute("bindingResult",bindingResult);
        }else if(userService.isUserAlreadyPresent(user)){
                modelAndView.addObject("successMessage" ,"User already exist!");
        }else{
            //no any error we will save the user

            StringBuilder fileNames = new StringBuilder();
            for (MultipartFile file : files) {
                Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename()+" ");
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            userService.saveUserAndImage(user,uploadDirectory);
            modelAndView.addObject("successMessage" ,"User Registered Successfully!!!");
        }

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");




        return modelAndView;
    }








//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    public ModelAndView upload(ModelAndView model,@RequestParam("files") MultipartFile[] files,@RequestParam("id")Integer id) {
//            System.out.println("82 ci setir Controller : " + id);
//        StringBuilder fileNames = new StringBuilder();
//        for (MultipartFile file : files) {
//            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
//            fileNames.append(file.getOriginalFilename()+" ");
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        model.addObject("msg", "Successfully uploaded files "+fileNames.toString());
//        model.setViewName("uploadstatusview");
//
//        userService.addImage(2,uploadDirectory);
//        System.out.println("96ci setir: " + fileNames.toString());
//        return model;
//    }

}
