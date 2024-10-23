package com.app.todo.DAO;


import com.app.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);


    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template)
    {
        this.template = template;

        String createTableQuery = " create table if not exists todoTable(id int primary key, title varchar(50), content varchar(500), status varchar(10) not null) ";
        int updates = template.update(createTableQuery);
        logger.info("Todo Table created {}",updates);
    }


    public JdbcTemplate getJdbcTemplate() {
        return template;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    //intert-todo
    public Todo insertTodoInDatabase(Todo todo)
    {
        String query = " insert into todoTable(id,title,content,status) values(?,?,?,?) ";
        int rows = template.update(query,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus());
        logger.info("Database operation : {} rows inserted",rows);
        return todo;

    }

    //get-single-Todo
    public Todo getSingleTodoFromDatabase(int id)
    {
        String query = "select * from todoTable where id = ? ";
//        Map<String,Object> todoData = template.queryForMap(query,new TodoRowMapper(),id);
//        logger.info("Resultant todo = {}",todoData);

//      Instead of converting Objects to Todo again and again we can use RowMapper
//        Todo todo = new Todo();
//        todo.setId(((int)todoData.get("id")));
//        todo.setTitle(((String)todoData.get("title")));
//        todo.setContent(((String)todoData.get("content")));
//        todo.setStatus(((String)todoData.get("status")));

        //Above task using RowMapper
        Todo todo = (Todo) template.queryForObject(query,new TodoRowMapper(),id);
        return todo;

    }

    //get-all-todo
    public List<Todo> getAllTodoFromDatabase(){
        String query = "SELECT * FROM todoTable";

//       Insted to this we can use RowMapper
//        List<Map<String,Object>> todos = template.queryForList(query);
//
//        List<Todo> listOfAllTodos = todos.stream().map((current)->{
//            Todo todo = new Todo();
//            todo.setId(((int)current.get("id")));
//            todo.setTitle(((String)current.get("title")));
//            todo.setContent(((String)current.get("content")));
//            todo.setStatus(((String)current.get("status")));
//
//            return todo;
//        }).collect(Collectors.toList())

        //Above task using RowMapper
         List<Todo> listOfAllTodos = template.query(query, new TodoRowMapper());

        return listOfAllTodos;

    }


    //update-todo
    public Todo updateTodoInDatabase(int givenId , Todo updatedTodo) {
        String query = "update todoTable set title=? , content=? , status=? where id=?";
        template.update(query, updatedTodo.getTitle(), updatedTodo.getContent(), updatedTodo.getStatus(), givenId);
        updatedTodo.setId(givenId);
        logger.info("Updated todo : {}",updatedTodo);
        return updatedTodo;

    }

    //delete-single-todo
    public void deleteTodoFromeDatabase(int id) {
        String query = "DELETE FROM todoTable WHERE id = ?";
        int noOfRowsDeleted = template.update(query,id);
        logger.info("Deleted {} rows",noOfRowsDeleted);
    }

    //delete-multiple-todo
    public void deleteMultipleTodo(int[] ids) {
        String query = "DELETE FROM todoTable WHERE id = ?";
        int deletedCount = 0;
        for (int id : ids) {
            int noOfRowsDeleted = template.update(query, id);
            deletedCount = deletedCount + noOfRowsDeleted;
        }
        logger.info("Deleted {} rows", deletedCount);
    }
}
