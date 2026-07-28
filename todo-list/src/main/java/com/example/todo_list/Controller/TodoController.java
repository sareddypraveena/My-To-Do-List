package com.example.todo_list.Controller;

import com.example.todo_list.entity.Todo;
import com.example.todo_list.Service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 1️⃣ Create Task
    @PostMapping
    public Todo createTask(@RequestBody Todo todo) {

        return todoService.createTask(todo);
    }

    // 2️⃣ Get All Active Tasks
    @GetMapping
    public List<Todo> getAllTasks() {

        return todoService.getAllTasks();
    }

    // 3️⃣ Get Particular User's Tasks
    @GetMapping("/user/{userId}")
    public List<Todo> getTasksByUser(
            @PathVariable Long userId) {

        return todoService.getTasksByUser(userId);
    }

    // 4️⃣ Update Task
    @PutMapping("/{id}")
    public Todo updateTask(
            @PathVariable Long id,
            @RequestBody Todo todo) {

        return todoService.updateTask(id, todo);
    }

    // 5️⃣ Delete Task
    @DeleteMapping("/{id}")
    public Todo deleteTask(@PathVariable Long id) {

        return todoService.deleteTask(id);
    }
}