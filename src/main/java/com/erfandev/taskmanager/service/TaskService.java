package com.erfandev.taskmanager.service;

import com.erfandev.taskmanager.dto.TaskRequest;
import com.erfandev.taskmanager.dto.TaskResponse;
import com.erfandev.taskmanager.entity.Task;
import com.erfandev.taskmanager.exception.TaskNotFoundException;
import com.erfandev.taskmanager.mapper.TaskMapper;
import com.erfandev.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,
                       TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public TaskResponse getTaskById(Long id) {
        return taskMapper.toResponse(taskRepository.findById(id)
                .orElseThrow( ()-> new TaskNotFoundException(id)));
    }

    public TaskResponse createTask(TaskRequest task) {
        Task newTask = taskMapper.toEntity(task);
        Task savedTask=taskRepository.save(newTask);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
       Task task=taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
       taskMapper.updateTask(task,updatedTask);
         return taskMapper.toResponse(taskRepository.save(task));
    }
    public void deleteTaskById(Long id) {
       Task task= taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
                 taskRepository.delete(task);
    }

    public List<Task> getCompletedTasks(boolean status) {
        return taskRepository.findByCompleted(status);
    }

    public List<Task> search(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}
