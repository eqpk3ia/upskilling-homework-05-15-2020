package com.sanket.todo.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sanket.todo.entity.Task;
import com.sanket.todo.entity.TaskList;
import com.sanket.todo.repository.TaskListRepository;
import com.sanket.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lists")
public class TaskListController extends AbstractController<TaskList> {

    @Autowired
    private TaskListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public JpaRepository<TaskList, Long> getRepository() {
        return listRepository;
    }

    @PutMapping("/")
    public TaskList addTaskList(@RequestParam(required = true, name = "name") @NotBlank String name) {
        TaskList newTaskList = new TaskList();

        newTaskList.setName(name.toUpperCase());

        return save(newTaskList);
    }

    @PostMapping("/")
    public TaskList updateTaskList(@RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "name", required = true) @NotBlank String name) {
        TaskList taskList = getById(id);

        if (null != taskList) {
            taskList.setName(name.toUpperCase());
            save(taskList);
        }

        return taskList;
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getAllTasksforTaskList(@PathVariable(name = "id", required = true) Long id) {
        List<Task> results = null;

        TaskList taskList = getById(id);
        if (null != taskList) {
            results = taskRepository.findByTaskLists(taskList);
        }

        return results;
    }

    @GetMapping("/name/{name}")
    public TaskList getByName(@PathVariable(name = "name", required = true) @NotBlank String name) {
        TaskList taskList = null;

        if (!name.trim().isEmpty()) {
            taskList = listRepository.findByName(name.toUpperCase());
        }

        return taskList;
    }

}