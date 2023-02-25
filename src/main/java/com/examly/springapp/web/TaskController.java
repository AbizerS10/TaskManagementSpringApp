package com.examly.springapp.web;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.data.models.Task;
import com.examly.springapp.data.payloads.request.TaskRequest;
import com.examly.springapp.data.payloads.response.MessageResponse;
import com.examly.springapp.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    
    @Autowired
    TaskService taskService;

    @GetMapping("/alltasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTask();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id){
        Task task = taskService.getASingleTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/saveTask")
    public ResponseEntity<MessageResponse> addTask( @RequestBody TaskRequest task) {
        MessageResponse newTask = taskService.createTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("changeStatus/{id}")
    public ResponseEntity<Task> updateTask( @PathVariable Integer id, @RequestBody TaskRequest task) {
        Task updateTask = taskService.updateTask(id, task).get();
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
