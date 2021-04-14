package com.weblogin.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
@SequenceGenerator(name= "role_seq", sequenceName = "auth_role_seq", initialValue=1, allocationSize = 1)
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="role_seq")
    @Column(name = "auth_role_id")
    private int id;

    @Column(name = "role_name")
    private String role;

    @Column(name = "role_desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}