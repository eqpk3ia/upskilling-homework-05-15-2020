package com.sanket.todo.repository;

import com.sanket.todo.entity.TaskList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    public TaskList findByName(String taskListName);
}