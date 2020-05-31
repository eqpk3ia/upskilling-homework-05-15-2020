package com.sanket.todo.repository;

import java.util.List;

import com.sanket.todo.entity.Task;
import com.sanket.todo.entity.TaskList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByTaskLists(TaskList taskList);
}