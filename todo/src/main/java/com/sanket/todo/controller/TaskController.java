package com.sanket.todo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sanket.todo.entity.Task;
import com.sanket.todo.entity.TaskList;
import com.sanket.todo.entity.User;
import com.sanket.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Task addTask(@RequestParam(name = "name", required = true) @NotBlank String name,
            @RequestParam(name = "descr", required = false) @NotBlank String descr) {
        Task newTask = null;

        if (!name.trim().isEmpty()) {
            User currentUser = getCurrentUser();

            if (null != currentUser) {
                newTask = new Task(name, descr, "NEW", currentUser, null);
                save(newTask);

            }
        }

        return newTask;
    }

    @PutMapping("/lists/{id}")
    public Task addTask(@PathVariable(name = "id", required = true) Long taskListId,
            @RequestParam(name = "name", required = true) @NotBlank String name,
            @RequestParam(name = "descr", required = false) @NotBlank String descr) {
        Task newTask = null;

        if (null != taskListId) {
            User currentUser = getCurrentUser();

            if (null != currentUser) {
                TaskList taskList = taskListController.getById(taskListId);

                if (null != taskList) {
                    newTask = new Task(name, descr, "NEW", currentUser, new HashSet<TaskList>(Arrays.asList(taskList)));
                    save(newTask);
                }
            }
        }

        return newTask;
    }

    @PostMapping("/")
    public Task updateTask(@RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "name", required = false) @NotBlank String name,
            @RequestParam(name = "descr", required = false) @NotBlank String descr,
            @RequestParam(name = "status", required = false) @NotBlank String status) {
        Task task = getById(id);

        if (null != task) {
            if (null != name) {
                task.setName(name);
            }

            if (null != descr) {
                task.setDescription(descr);
            }

            if (null != status) {
                task.setStatus(status);
            }

            save(task);
        }

        return task;
    }

    @GetMapping("/{id}/lists/")
    public List<TaskList> getTaskListByTaskId(@PathVariable(name = "id", required = true) Long id) {
        List<TaskList> taskLists = null;

        Task newTask = getById(id);

        if (null != id) {
            Set<TaskList> results = newTask.getTaskLists();

            if (null != results && !results.isEmpty()) {
                taskLists = new ArrayList<TaskList>(results);
            }
        }

        return taskLists;
    }

    @PatchMapping("/lists/{id}")
    public void patchTaskListForTaskId(@PathVariable(name = "id", required = true) Long id, @RequestBody Task task) {
        TaskList currentTaskList = taskListController.getById(id);

        if (null != currentTaskList) {
            List<Task> results = taskRepository.findByTaskLists(currentTaskList);

            for (Task resultTask : results) {
                resultTask.setTaskLists(task.getTaskLists());

                save(resultTask);
            }
        }
    }

    @GetMapping("/status/{status}")
    public List<Task> getAllByTaskStatus(@PathVariable(name = "status", required = true) @NotBlank String status) {
        return taskRepository.findByStatus(status.toUpperCase());
    }
}