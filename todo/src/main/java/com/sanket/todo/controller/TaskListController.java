package com.sanket.todo.controller;

import com.sanket.todo.entity.TaskList;
import com.sanket.todo.repository.TaskListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lists")
public class TaskListController extends AbstractController<TaskList> {

    @Autowired
    private TaskListRepository listRepository;

    @Override
    public JpaRepository<TaskList, Long> getRepository() {
        return listRepository;
    }

    @PostMapping("/")
    public TaskList addTaskList(@RequestParam(required = true, name = "taskListName") String listName) {
        TaskList newTaskList = new TaskList();

        newTaskList.setName(listName.toUpperCase());

        return save(newTaskList);
    }

    @PatchMapping("/{id}")
    public TaskList updateTaskList(@PathVariable(name = "id", required = true) Long id,
            @RequestParam(name = "taskListName", required = true) String taskListName) {
        TaskList taskList = getById(id);

        if (null != taskList) {
            taskList.setName(taskListName.toUpperCase());
            save(taskList);
        }

        return taskList;
    }

}