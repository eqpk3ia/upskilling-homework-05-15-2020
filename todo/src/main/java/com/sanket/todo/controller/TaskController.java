package com.sanket.todo.controller;

import java.util.Arrays;
import java.util.HashSet;

import com.sanket.todo.entity.Task;
import com.sanket.todo.entity.TaskList;
import com.sanket.todo.entity.User;
import com.sanket.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController extends AbstractAuthenticationController<Task> {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListController taskListController;

    @Override
    public JpaRepository<Task, Long> getRepository() {
        return taskRepository;
    }

    @PutMapping("/")
    public Task addTask(@RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "descr", required = false) String descr) {
        Task newTask = null;

        if (!name.trim().isEmpty()) {
            User currentUser = getCurrentUser();

            if (null != currentUser) {
                newTask = new Task(name, descr, currentUser, null);
                save(newTask);

            }
        }

        return newTask;
    }

    @PutMapping("/lists/{taskListId}")
    public Task addTask(@PathVariable(name = "taskListId", required = true) Long taskListId,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "descr", required = false) String descr) {
        Task newTask = null;

        if (!name.trim().isEmpty() && null != taskListId) {
            User currentUser = getCurrentUser();

            if (null != currentUser) {
                TaskList taskList = taskListController.getById(taskListId);

                if (null != taskList) {
                    newTask = new Task(name, descr, currentUser, new HashSet<TaskList>(Arrays.asList(taskList)));
                    save(newTask);
                }
            }
        }

        return newTask;
    }

    @PostMapping("/{id}")
    public Task updateTask(@PathVariable(name = "id", required = true) Long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "descr", required = false) String descr) {
        Task task = getById(id);

        if (null != task) {
            if (null != name && !name.trim().isEmpty()) {
                task.setName(name);
            }

            if (null != descr && !descr.trim().isEmpty()) {
                task.setDescription(descr);
            }

            save(task);
        }

        return task;
    }
}