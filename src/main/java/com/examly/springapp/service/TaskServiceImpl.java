package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.data.models.Task;
import com.examly.springapp.data.payloads.request.TaskRequest;
import com.examly.springapp.data.payloads.response.MessageResponse;
import com.examly.springapp.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public MessageResponse createTask(TaskRequest taskrequest) {
        Task newTask = new Task();
        newTask.setTaskId(taskrequest.getTaskId());
        newTask.setTaskName(taskrequest.getTaskName());
        newTask.setTaskDate(taskrequest.getTaskDate());
        newTask.setTaskHolderName(taskrequest.getTaskHolderName());
        newTask.setTaskStatus(taskrequest.getTaskStatus());
        taskRepository.save(newTask);
        return new MessageResponse("New Task created succcessfully");
    }

    @Override
    public Optional<Task> updateTask(Integer taskId, TaskRequest taskrequest) {
        Optional<Task> task = taskRepository.findById(taskId);
        task.get().setTaskStatus(taskrequest.getTaskStatus());
        taskRepository.save(task.get());
        return task;
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getASingleTask(Integer id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
    
}
