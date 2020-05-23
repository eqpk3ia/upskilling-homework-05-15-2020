package com.sanket.todo.controller;

import java.util.List;
import java.util.Optional;

import com.sanket.todo.entity.Task;
import com.sanket.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTasks(@PathVariable("id") Long id) {
        Optional<Task> result = taskRepository.findById(id);

        return result.isPresent() ? result.get() : null;        
    }

    /*@PostMapping("")
    public UserAccount getAddUser(@RequestParam("fName") String fName, @RequestParam("lName") String lName,
            @RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        return taskRepository.save(new UserAccount(fName, lName, email, pwd));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);

        return "User with Id " + id + " deleted";
    }

    @DeleteMapping("")
    public String deleteUsers() {
        taskRepository.deleteAll();

        return "All Users are deleted";
    }

    @PatchMapping("/{id}")
    public UserAccount updateUser(@PathVariable("id") Long id, @RequestParam("fName") String fName,
            @RequestParam("lName") String lName, @RequestParam("email") String email,
            @RequestParam("pwd") String pwd) {
                UserAccount updateUser = null;

        Optional<UserAccount> result = (Optional<UserAccount>) taskRepository.findById(id);

        if (result.isPresent()) {
            updateUser = result.get();

            updateUser.setFirstName(fName);
            updateUser.setLastName(lName);
            updateUser.setUserId(email);
            updateUser.setPassword(pwd);

            updateUser = taskRepository.save(updateUser);
        }

        return updateUser;        
    }*/
}