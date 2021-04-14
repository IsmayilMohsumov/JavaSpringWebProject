package com.weblogin.demo.repository;

import com.weblogin.demo.model.Skill;
import com.weblogin.demo.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill,Integer> {

//    join
//    select AU.EMAIL , AU.FIRST_NAME, ASS.NAME from auth_user au left join auth_user_skill aus on AU.AUTH_USER_ID=AUS.USER_ID left join auth_skill ass on AUS.SKILL_ID=ASS.ID where AU.AUTH_USER_ID=7;

        @Query(value = "select  ASS.NAME from auth_user au left join auth_user_skill aus on AU.AUTH_USER_ID=AUS.USER_ID left join auth_skill ass on AUS.SKILL_ID=ASS.ID where AU.AUTH_USER_ID=?1",nativeQuery = true)
        List<String> findUserSkillsByUserId(Integer id);

        UserSkill findUserSkillsBySkillId(Integer id);

        void deleteBySkill(Skill skill);


}
