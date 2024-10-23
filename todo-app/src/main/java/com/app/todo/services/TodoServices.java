package com.app.todo.services;

import com.app.todo.models.Todo;

import java.util.List;

public interface TodoServices {
    public Todo createTodo(Todo todo);
    public Todo getSingleTodo(int todoId);
    public List<Todo> getAllTodos();
    public Todo updateTodo(int todoId, Todo updationInTodo);
    public void deleteTodo(int todoId);
}
