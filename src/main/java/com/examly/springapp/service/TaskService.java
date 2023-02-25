package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.data.models.Task;
import com.examly.springapp.data.payloads.request.TaskRequest;
import com.examly.springapp.data.payloads.response.MessageResponse;

import org.springframework.stereotype.Component;

@Component
public interface TaskService {
    MessageResponse createTask(TaskRequest taskrequest);
    Optional<Task> updateTask(Integer taskId, TaskRequest taskrequest);
    void deleteTask(Integer id);
    Task getASingleTask(Integer id);
    List<Task> getAllTask();
}
