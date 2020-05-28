package com.sanket.todo.controller;

import java.util.List;
import java.util.Optional;

import com.sanket.todo.entity.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractController<E extends TodoEntity> {

    public abstract JpaRepository<E, Long> getRepository();

    @GetMapping("/")
    public List<E> getAll () {
        return getRepository().findAll();
    }

    @GetMapping("/{id}")
    public E getById(@PathVariable(name = "id", required = true) Long id) {
        Optional<E> result =getRepository().findById(id);

        return result.isPresent() ? result.get() : null;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        getRepository().deleteById(id);

        return "Entity with Id " + id + " deleted";
    }

    @DeleteMapping("/")
    public String deleteAll() {
        getRepository().deleteAll();

        return "All entities are deleted";
    }

    protected E save(E entity) {
        return getRepository().save(entity);
    }
}