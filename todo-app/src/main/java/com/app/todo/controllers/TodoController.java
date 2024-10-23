package com.app.todo.controllers;

import com.app.todo.models.Todo;
import com.app.todo.services.TodoServices;
import com.app.todo.services.implementation.TodoServicesImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoServices todoServices;

    Random random = new Random();

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
         int idOfCurrent = random.nextInt(100);
         todo.setId(idOfCurrent);
         logger.info("Creating the todo");
         Todo currentTodo = todoServices.createTodo(todo);
         return new ResponseEntity<>(currentTodo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> currentTodo = todoServices.getAllTodos();
        return new ResponseEntity<>(currentTodo, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo currentTodo = todoServices.getSingleTodo(todoId);
        return new ResponseEntity<>(currentTodo,HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo updationInTodo , @PathVariable int todoId){
        Todo currentTodo = todoServices.updateTodo(todoId , updationInTodo);
        return new ResponseEntity<>(currentTodo,HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        todoServices.deleteTodo(todoId);
        return new ResponseEntity<>("Deletion done",HttpStatus.OK);
    }
}