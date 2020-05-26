package com.sanket.todo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    public String name;
    public String description;

    @CreationTimestamp
    @Column(insertable = true, updatable = false)
    public Date createDate;

    @CreationTimestamp
    @Column(insertable = false, updatable = true)
    public Date udpateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUdpateDate() {
        return udpateDate;
    }

    public void setUdpateDate(Date udpateDate) {
        this.udpateDate = udpateDate;
    }

    public Task(User userAccount, String name, String description, Date createDate, Date udpateDate) {
        this.user = userAccount;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.udpateDate = udpateDate;
    }

    public Task() {
    }

    public User getUserAccount() {
        return user;
    }

    public void setUserAccount(User userAccount) {
        this.user = userAccount;
    }
}