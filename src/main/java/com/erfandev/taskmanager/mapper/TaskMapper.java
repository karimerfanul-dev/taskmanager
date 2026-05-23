package com.erfandev.taskmanager.mapper;

import com.erfandev.taskmanager.dto.CategoryResponse;
import com.erfandev.taskmanager.dto.TaskRequest;
import com.erfandev.taskmanager.dto.TaskResponse;
import com.erfandev.taskmanager.entity.Category;
import com.erfandev.taskmanager.entity.Task;
import com.erfandev.taskmanager.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final CategoryService categoryService;

    public TaskMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Task toEntity(TaskRequest task) {
        Category category=null;
        if(task!=null && task.categoryId()!=null){
            category = categoryService.findById(task.categoryId());
        }
        return Task.builder()
                .title(task.title())
                .description(task.description())
                .completed(task.completed() !=null?task.completed():false)
                .category(category)
                .build();
    }

    public TaskResponse toResponse(Task newTask) {

        CategoryResponse categoryResponse=null;
        if(newTask!=null && newTask.getCategory()!=null){
            categoryResponse = CategoryResponse.builder()
                    .categoryId(newTask.getCategory().getId())
                    .name(newTask.getCategory().getName())
                    .description(newTask.getCategory().getDescription())
                    .build();
        }
        return TaskResponse.builder()
                .id(newTask.getId())
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .completed(newTask.getCompleted())
                .created_at(newTask.getCreatedAt())
                .category(categoryResponse)
                .build();
    }
    public void updateTask(Task task,TaskRequest updatedTask) {
        task.setTitle(updatedTask.title());
        task.setDescription(updatedTask.description());
        task.setCompleted(updatedTask.completed());
        if(updatedTask.categoryId()!=null){
            task.setCategory(categoryService.findById(updatedTask.categoryId()));
        }
    }
}
