package com.weblogin.demo.repository;

import com.weblogin.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {
        public Skill findBySkillName(String skillName);

}
