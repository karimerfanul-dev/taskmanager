package com.erfandev.taskmanager.service;

import com.erfandev.taskmanager.dto.TaskRequest;
import com.erfandev.taskmanager.dto.TaskResponse;
import com.erfandev.taskmanager.entity.Task;
import com.erfandev.taskmanager.exception.TaskNotFoundException;
import com.erfandev.taskmanager.mapper.TaskMapper;
import com.erfandev.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Task> findAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
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

    public List<TaskResponse> getCompletedTasks(boolean status) {
        List<Task> task=taskRepository.findByCompleted(status);
            List<TaskResponse> taskResponses=new ArrayList<>();
        for(Task t:task) {
            taskMapper.toResponse(t);
            taskResponses.add(taskMapper.toResponse(taskRepository.save(t)));
        }
        return taskResponses;
    }

    public List<TaskResponse> getCompletedTasks(boolean status,Pageable pageable) {
        Page<Task> task=taskRepository.findByCompleted(status,pageable);
        return task.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public List<TaskResponse> searchBYTitle(String title) {

        List<Task> tasks=taskRepository.findByTitleContainingIgnoreCase(title);
        List<TaskResponse> taskResponses=new ArrayList<>();
        for(Task t:tasks) {
            taskMapper.toResponse(t);
            taskResponses.add(taskMapper.toResponse(taskRepository.save(t)));
        }
        return taskResponses;
    }

    public Page<Task> searchTaskBYTitle(String title, Pageable pageable) {
        return  taskRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Task> searchTasksByTitleAndCompletion(String title, Boolean completed,Pageable pageable) {
        return taskRepository.findByTitleContainingAndCompleted(title,completed,pageable);
    }

    public Page<Task> getTaskByCompletion(Boolean completed, Pageable pageable) {
        return taskRepository.findByCompleted(completed, pageable);
    }
}
