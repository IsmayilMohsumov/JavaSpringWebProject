package com.weblogin.demo.controller;

import com.weblogin.demo.model.User;
import com.weblogin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @GetMapping("/registerInAdmin")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "registerForm";
    }

    @PostMapping("/registerInAdmin")
    public ModelAndView registerUser(@Valid User user, BindingResult binding, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if(binding.hasErrors()){
            modelAndView.addObject("successMessage","Something went wrong!");
            System.out.println("RegisterController  34 cu setir"+binding);
            modelMap.addAttribute("bindingResult",binding);
            modelAndView.setViewName("registerForm");
        }else if(userService.isUserPresentCheckByEmail(user.getEmail())){
            modelAndView.addObject("successMessage","Email already taken by someone!");
            modelAndView.setViewName("registerForm");
        }else{
             userService.saveUser(user);
            modelAndView.addObject("successMessage","Created successfully!");
            modelAndView.setViewName("admin");
        }

        return modelAndView;
    }

}
