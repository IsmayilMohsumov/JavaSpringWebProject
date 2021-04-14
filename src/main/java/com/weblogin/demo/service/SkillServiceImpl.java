package com.weblogin.demo.service;

import com.weblogin.demo.model.Skill;
import com.weblogin.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public boolean isSkillAlreadyExist(String skillName) {
        boolean alreadyExist = false;
        Skill bySkillName = skillRepository.findBySkillName(skillName);
        if(bySkillName!=null){
            alreadyExist=true;
        }
        return alreadyExist;
    }

    @Override
    public Skill findAllBySkillName(String skillName) {
        Skill bySkillName = skillRepository.findBySkillName(skillName);
        return bySkillName;
    }

    @Override
    public void addSkill(Skill skillObj) {
        skillRepository.save(skillObj);
    }
}
