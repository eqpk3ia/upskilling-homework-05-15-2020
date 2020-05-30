package com.sanket.todo.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sanket.todo.entity.Task;
import com.sanket.todo.entity.User;
import com.sanket.todo.repository.TaskRepository;
import com.sanket.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController extends AbstractController<Task> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public JpaRepository<Task, Long> getRepository() {
        return taskRepository;
    }

    @PostMapping("/")
    public Task addTask(@RequestParam("name") String name, @RequestParam("descr") String descr) {

        Task newTask = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            if (null != currentUserName && !currentUserName.trim().isEmpty()) {
                User currentUser = userRepository.findByEmail(currentUserName);

                if (null != currentUser) {
                    //Set<User> users = new HashSet<User>(Arrays.asList(currentUser));

                    newTask = new Task(name, descr, currentUser);
                    save(newTask);
                }
            }
        }

        return newTask;
    }

    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestParam("name") String name,
            @RequestParam("descr") String descr) {
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