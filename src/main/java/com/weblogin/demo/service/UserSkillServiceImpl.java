package com.weblogin.demo.service;

import com.weblogin.demo.model.Skill;
import com.weblogin.demo.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserSkillServiceImpl implements UserSkillService {

    @Autowired
    private UserSkillRepository skillRepository;

    @Override
    public void deleteBySkill(Skill skill) {
         skillRepository.deleteBySkill(skill);
    }
}
