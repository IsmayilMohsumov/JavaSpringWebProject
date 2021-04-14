package com.weblogin.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_skill")
@SequenceGenerator(name= "skill_seq", sequenceName = "skill_seq", initialValue=3, allocationSize = 1)
public class Skill {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill_seq")
    private Integer id;

    @Column(name = "name")
    private String skillName;

    public Skill() {
    }

    public Skill( String skill) {
        this.skillName=skill;
    }


    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}

