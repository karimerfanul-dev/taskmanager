package com.erfandev.taskmanager.service;

import com.erfandev.taskmanager.entity.Task;
import com.erfandev.taskmanager.exception.TaskNotFoundException;
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

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow( ()-> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
       Task task=taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.getCompleted());
        task.setCreatedAt(updatedTask.getCreatedAt());
        return taskRepository.save(task);
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
