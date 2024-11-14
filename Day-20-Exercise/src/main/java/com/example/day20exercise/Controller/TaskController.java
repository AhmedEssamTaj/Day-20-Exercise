package com.example.day20exercise.Controller;

import com.example.day20exercise.ApiResponse.ApiResponse;
import com.example.day20exercise.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")

public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index,@RequestBody Task task) {

        tasks.set(index, task);
        return new ApiResponse("Task updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task deleted successfully");
    }

    // This Endpoint is responsable of changing the status of a task to done..
    // but you must choose enter the id to update that specfiec task

    @PutMapping("/change/status/{id}")
    public ApiResponse changeStatus (@PathVariable String id){

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setStatus("Done");
                return new ApiResponse("Task status updated successfully");
            }
        }

        return new ApiResponse("Task not found");
    }


    // This Endpoint search for a task by title
    @GetMapping("/find/task/{title}")
    public Task getTaskByTitle(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }


}

