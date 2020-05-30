package com.sanket.todo.repository;

import com.sanket.todo.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //public List<Task> findByUsers(String email);
}