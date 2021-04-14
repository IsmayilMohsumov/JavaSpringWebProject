package com.weblogin.demo.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "auth_user")
@SequenceGenerator(name= "auth_user_seq", sequenceName = "auth_user_seq", initialValue=2, allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="auth_user_seq")
    @Column(name = "auth_user_id")
    private Integer id;

    @NotNull(message="First name is compulsory")
    @Column(name = "first_name")
    private String name;

    @NotNull(message="Last name is compulsory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Email is compulsory")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message="Password is compulsory")
    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;


    @Column(name = "status")
    private String status;

    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", roles=" + roles +
                '}';
    }
}
