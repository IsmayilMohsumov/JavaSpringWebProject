package com.weblogin.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_user_skill")
@SequenceGenerator(name= "user_skill_seq", sequenceName = "user_skill_seq", initialValue=5, allocationSize = 1)
public class UserSkill {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_skill_seq")
    private Integer id;


    @JoinColumn(name = "user_id", referencedColumnName = "auth_user_id")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Skill skill;

    public UserSkill() {
    }

    public UserSkill( User userId, Skill skillId) {
        this.user=userId;
        this.skill=skillId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "id=" + id +
                ", user=" + user +
                ", skill=" + skill +
                '}';
    }
}
