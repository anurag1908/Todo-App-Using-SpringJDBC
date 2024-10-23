package com.app.todo.services.implementation;

import com.app.todo.models.Todo;
import com.app.todo.services.TodoServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServicesImplementation implements TodoServices {

    Logger logger = LoggerFactory.getLogger(TodoServicesImplementation.class);
    List<Todo> todoList = new ArrayList<>();
    // create
    public Todo createTodo(Todo todo){
        todoList.add(todo);
        logger.info("Todo created successfully !");
        logger.info("Todos {}" , todoList);
        return todo;
    }


    //get all
    public List<Todo> getAllTodos() {
        return todoList;
    }

    //get single
    public Todo getSingleTodo(int todoId) {
        logger.info("Searching for the target");
        Todo ans = todoList.stream().filter(t -> todoId == t.getId()).findAny().get();
        return ans;
    }

    //updae
    public Todo updateTodo(int todoId, Todo updationInTodo) {
        List<Todo> updatedTodoList = todoList.stream().map(t ->{
            if(t.getId()==todoId)
            {
                t.setTitle(updationInTodo.getTitle());
                t.setContent(updationInTodo.getContent());
                t.setStatus(updationInTodo.getStatus());

                return t;
            }
            else{
                return t;
            }
        }).collect(Collectors.toList());

        todoList = updatedTodoList;
        updationInTodo.setId(todoId);
        return updationInTodo;
    }


    // delete todo
    public void deleteTodo(int todoId) {
        List<Todo> newTodoList = todoList.stream().filter(
                t->t.getId()!=todoId
        ).collect(Collectors.toList());

        todoList = newTodoList;
    }
}