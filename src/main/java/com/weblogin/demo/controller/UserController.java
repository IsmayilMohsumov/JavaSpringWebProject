package com.weblogin.demo.controller;

import com.weblogin.demo.model.Skill;
import com.weblogin.demo.model.User;
import com.weblogin.demo.model.UserSkill;
import com.weblogin.demo.repository.SkillRepository;
import com.weblogin.demo.repository.UserSkillRepository;
import com.weblogin.demo.service.SkillService;
import com.weblogin.demo.service.UserService;
import com.weblogin.demo.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserSkillService userSkillService;


    @GetMapping("/users")
    public String listUsers(Model model, @RequestParam(defaultValue = "") String name) {
        List<User> all = userService.findByName(name);
        model.addAttribute("userList", all);
        return "showUsers";
    }


    @PostMapping("/addSkill")
    public ResponseEntity<Object> getResultByAjax(
            Principal principal, HttpServletRequest request) //@RequestParam String skill,
    {

        String skillName = request.getParameter("skillName");
//
//        System.out.println("request ile" + skillName);
//        System.out.println("add Skill : " + principal.getName());

//        UserSkill userSkillsBySkillId = userSkillRepository.findUserSkillsBySkillId(6);
//        UserSkill userSkillsBySkillId1 = userSkillRepository.findUserSkillsBySkillId(5);
//        System.out.println("userskill:" + userSkillsBySkillId);
//        System.out.println("userskill1:" + userSkillsBySkillId1);


        Skill skillObj = null;
        boolean skillAlreadyExist = skillService.isSkillAlreadyExist(skillName);
        if (skillAlreadyExist) {
            System.out.println("This skill already exist!!");
            skillObj = skillService.findAllBySkillName(skillName);
            System.out.println("Skill obyekti:" + skillObj);
        } else {
            skillObj = new Skill(skillName);
            skillService.addSkill(skillObj);
            System.out.println("Skill obyekti else de:" + skillObj);
        }
        User userObj = userService.findByEmail(principal.getName());
        System.out.println("User obyekti:" + userObj);
        UserSkill userSkillObj = new UserSkill(userObj, skillObj);

        if (userSkillRepository.findUserSkillsBySkillId(skillObj.getId()) == null) {
            userSkillRepository.save(userSkillObj);
        }else{
            System.out.println("User has this kind of Skill!");
            skillName="hasSkill";
        }



        return new ResponseEntity<Object>(skillName, HttpStatus.OK);

    }

    @PostMapping(value = "/deleteSkill",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteSkill(
           @RequestParam("nameOfSkill")String skillName) //@RequestParam String skill,
    {
        System.out.println("gelen deyer "+skillName);
        Skill allBySkillName = skillService.findAllBySkillName(skillName);
        System.out.println("gelen obyekt "+allBySkillName);

         userSkillService.deleteBySkill(allBySkillName);


        return new ResponseEntity<Object>(skillName, HttpStatus.OK);
    }



    @PostMapping(value = "/updateSkill",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSkill(
            Principal principal,@RequestParam("id")Integer userId,@RequestParam("newName")String newSkillName,@RequestParam("oldName")String oldSkillName) //@RequestParam String skill,
    {

        Skill skillObj = null;
        boolean skillAlreadyExist = skillService.isSkillAlreadyExist(newSkillName);
        if (skillAlreadyExist) {
            System.out.println("This skill already exist!!");
            skillObj = skillService.findAllBySkillName(newSkillName);
            System.out.println("Skill obyekti:" + skillObj);
        } else {
            skillObj = new Skill(newSkillName);
            skillService.addSkill(skillObj);
            System.out.println("Skill obyekti else de:" + skillObj);
        }
        User userObj = userService.findByEmail(principal.getName());
        System.out.println("User obyekti:" + userObj);
        UserSkill userSkillObj = new UserSkill(userObj, skillObj);

        if (userSkillRepository.findUserSkillsBySkillId(skillObj.getId()) == null) {
            userSkillRepository.save(userSkillObj);
            skillObj=skillService.findAllBySkillName(oldSkillName);
            userSkillService.deleteBySkill(skillObj);
        }else{
            System.out.println("User has this kind of Skill!");
            newSkillName="hasSkill";
        }



        System.out.println("kohne skill:"+oldSkillName);
        System.out.println("teze skill:"+newSkillName);
        return new ResponseEntity<Object>(newSkillName, HttpStatus.OK);
    }

    @PostMapping(value = "/updateDesc",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateDescription(
            Principal principal,@RequestParam("newName")String description) //@RequestParam String skill,
    {

        System.out.println("description:"+description);
        User userObj = userService.findByEmail(principal.getName());
        userService.updateDesc(description,userObj.getId());


        return new ResponseEntity<Object>(description, HttpStatus.OK);
    }


}
