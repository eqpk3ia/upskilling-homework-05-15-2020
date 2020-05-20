package com.sanket.todo.controller;

import java.util.List;
import java.util.Optional;

import com.sanket.todo.entity.UserAccount;
import com.sanket.todo.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ua")
public class UserAccountController {
    @Autowired
    private UserAccountRepository userRepository;

    @GetMapping("")
    public List<UserAccount> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserAccount getUsers(@PathVariable("id") Long id) {
        Optional<UserAccount> result = userRepository.findById(id);

        return result.isPresent() ? result.get() : null;        
    }

    @PostMapping("")
    public UserAccount getAddUser(@RequestParam("fName") String fName, @RequestParam("lName") String lName,
            @RequestParam("email") String email, @RequestParam("pwd") String pwd) {
        return userRepository.save(new UserAccount(fName, lName, email, pwd));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);

        return "User with Id " + id + " deleted";
    }

    @DeleteMapping("")
    public String deleteUsers() {
        userRepository.deleteAll();

        return "All Users are deleted";
    }

    @PatchMapping("/{id}")
    public UserAccount updateUser(@PathVariable("id") Long id, @RequestParam("fName") String fName,
            @RequestParam("lName") String lName, @RequestParam("email") String email,
            @RequestParam("pwd") String pwd) {
                UserAccount updateUser = null;

        Optional<UserAccount> result = (Optional<UserAccount>) userRepository.findById(id);

        if (result.isPresent()) {
            updateUser = result.get();

            updateUser.setFirstName(fName);
            updateUser.setLastName(lName);
            updateUser.setUserId(email);
            updateUser.setPassword(pwd);

            updateUser = userRepository.save(updateUser);
        }

        return updateUser;        
    }
}