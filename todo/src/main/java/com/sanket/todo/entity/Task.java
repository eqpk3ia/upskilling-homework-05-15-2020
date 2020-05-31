package com.sanket.todo.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Column(name = "DESCRIPTION", nullable = true)
    public String description;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a Z")
    @Column(name = "CREATE_DT", insertable = true, updatable = false)
    public ZonedDateTime createDate;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss a Z")
    @Column(name = "LAST_UPDT_DT", insertable = false, updatable = true, nullable = true)
    public ZonedDateTime udpateDate;

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

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getUdpateDate() {
        return udpateDate;
    }

    public void setUdpateDate(ZonedDateTime udpateDate) {
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