package com.erfandev.taskmanager.mapper;

import com.erfandev.taskmanager.dto.TaskRequest;
import com.erfandev.taskmanager.dto.TaskResponse;
import com.erfandev.taskmanager.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequest task) {
        return Task.builder()
                .title(task.title())
                .description(task.description())
                .completed(task.completed() !=null?task.completed():false)
                .build();
    }

    public TaskResponse toResponse(Task newTask) {
        return TaskResponse.builder()
                .id(newTask.getId())
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .completed(newTask.getCompleted())
                .created_at(newTask.getCreatedAt())
                .build();
    }
    public void updateTask(Task task,TaskRequest updatedTask) {
        task.setTitle(updatedTask.title());
        task.setDescription(updatedTask.description());
        task.setCompleted(updatedTask.completed());
    }
}
