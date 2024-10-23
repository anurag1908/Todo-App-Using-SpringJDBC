package com.app.todo.services.implementation;

import com.app.todo.DAO.TodoDao;
import com.app.todo.models.Todo;
import com.app.todo.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DaoTodoServicesImplementation implements TodoServices {

    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.insertTodoInDatabase(todo);
    }

    @Override
    public Todo getSingleTodo(int todoId) {
        return todoDao.getSingleTodoFromDatabase(todoId);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodoFromDatabase();
    }

    @Override
    public Todo updateTodo(int todoId, Todo updationInTodo) {
        return todoDao.updateTodoInDatabase(todoId , updationInTodo);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodoFromeDatabase(todoId);
    }
}
