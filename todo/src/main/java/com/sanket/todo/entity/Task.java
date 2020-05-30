package com.sanket.todo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "TASK")
public class Task extends TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty(message = "*Please provide your task name")
    public String name;

    @Column(name = "DESCRIPTION")
    public String description;

    @CreationTimestamp
    @Column(name = "CREATE_DT", insertable = true, updatable = false)
    public Date createDate;

    @UpdateTimestamp
    @Column(name = "LAST_UPDT_DT", insertable = false, updatable = true)
    public Date udpateDate;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

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

    public Task(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Task() {
    }

    
}