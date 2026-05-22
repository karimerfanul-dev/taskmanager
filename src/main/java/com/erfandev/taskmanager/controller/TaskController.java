package com.erfandev.taskmanager.controller;

import com.erfandev.taskmanager.dto.TaskRequest;
import com.erfandev.taskmanager.dto.TaskResponse;
import com.erfandev.taskmanager.entity.Task;
import com.erfandev.taskmanager.repository.TaskRepository;
import com.erfandev.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService=taskService;
    }

    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskService.findAllTasks();
    }

    @GetMapping("/filtarin/search")
        public ResponseEntity<Map<String,Object>> searchTask(
                @RequestParam(required = false) String title,
                @RequestParam(required = false) Boolean completed,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "createdAt") String sortBy,
                @RequestParam(defaultValue = "DESC") String sortOrder){
        Sort sort=sortOrder.equalsIgnoreCase("ASC")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Task> tasks;
        if(title!=null&& completed!=null){
            tasks=taskService.searchTasksByTitleAndCompletion(title,completed,pageable);
        }
        else if(title!=null){
            tasks=taskService.searchTaskBYTitle(title,pageable);
        }
        else if(completed!=null){
            tasks=taskService.getTaskByCompletion(completed,pageable);
        }
        else{
            tasks=taskService.findAllTasks(pageable);
        }

        List<TaskResponse> taskResponse=tasks.getContent()
                .stream()
                .map(task->new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCompleted(),
                        task.getCreatedAt()))
                .toList();
        Map<String,Object> map=new HashMap<>();
        map.put("tasks",taskResponse);
        map.put("currentPage",tasks.getNumber());
        map.put("totalPages",tasks.getTotalPages());
        map.put("totalElements",tasks.getTotalElements());
        map.put("hasNext",tasks.hasNext());
        map.put("hasPrevious",tasks.hasPrevious());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/pageing")
    public ResponseEntity<Map<String,Object>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder){
        Sort sort=sortOrder.equalsIgnoreCase("ASC")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Task> tasks=taskService.findAllTasks(pageable);

        List<TaskResponse> taskResponse=tasks.getContent()
                .stream()
                .map(task->new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCompleted(),
                        task.getCreatedAt()))
                .toList();
        Map<String,Object> map=new HashMap<>();
        map.put("tasks",taskResponse);
        map.put("currentPage",tasks.getNumber());
        map.put("totalPages",tasks.getTotalPages());
        map.put("totalElements",tasks.getTotalElements());
        map.put("hasNext",tasks.hasNext());
        map.put("hasPrevious",tasks.hasPrevious());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);

    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest task) {
        TaskResponse newTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest updatedTask) {
        return taskService.updateTask(id,updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
         taskService.deleteTaskById(id);
           return ResponseEntity.ok().build();
    }

    @GetMapping("/completed/{status}")
    public List<TaskResponse> findTaskByCompletions(@PathVariable boolean status) {
        return taskService.getCompletedTasks(status);
    }

    @GetMapping("/search")
    public List<TaskResponse> searchTaskByTitle(@RequestParam String title) {
        return taskService.searchBYTitle(title);
    }
}
