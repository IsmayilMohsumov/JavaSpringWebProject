package com.weblogin.demo.service;

import com.weblogin.demo.model.Skill;

public interface SkillService {
    
    boolean isSkillAlreadyExist(String skillName);
    Skill findAllBySkillName(String skillName);

    public void addSkill(Skill skillObj);
}
