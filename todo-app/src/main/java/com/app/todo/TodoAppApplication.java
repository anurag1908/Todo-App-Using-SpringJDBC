package com.app.todo;

import com.app.todo.DAO.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner {

	@Autowired
	private TodoDao todoDao;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//1. Made for testing Insert new entry
//      Todo todo= new Todo();
//		todo.setId(2);
//		todo.setTitle("Second Todo");
//		todo.setContent("This is the second time i'm calling insert method to put data into Database");
//		todo.setStatus("Pending");
//		todoDao.insertTodoInDatabase(todo);


//2. Made for testing of fetching one specific todo
//		Todo ourtodo = todoDao.getSingleTodoFromDatabase(7);
//		System.out.println(ourtodo);


//3. Made of Get all Todo's
//		List<Todo> ourTodoList = todoDao.getAllTodoFromDatabase();
//		System.out.println(ourTodoList);

//4. Made for update todo
//		Todo currentTodo = new Todo();
//		currentTodo.setTitle("New Second Todo");
//		currentTodo.setContent("This is the second time i'm calling insert method to put data into Database");
//		currentTodo.setStatus("Updated!");
//
//		Todo updatedTodo = todoDao.updateTodoInDatabase(2 , currentTodo);


//5. Delete single Todo
//       todoDao.deleteTodoFromeDatabase(8);


//6. Delete multiple todo
//		int ids[] = {1,2,3};
//		todoDao.deleteMultipleTodo(ids);




	}
}