package com.sanket.todo.controller;

import java.util.List;

import com.sanket.todo.entity.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractController<E extends TodoEntity> {

    public abstract JpaRepository<E, Long> getRepository();

    @GetMapping("/")
    public List<E> getAll () {
        return getRepository().findAll();
    }

    protected E save(E entity) {
        return getRepository().save(entity);
    }
  
}