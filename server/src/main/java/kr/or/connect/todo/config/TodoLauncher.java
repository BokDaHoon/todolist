package kr.or.connect.todo.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

public class TodoLauncher {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DataSource dataSource = context.getBean(DataSource.class);
		TodoDao dao = new TodoDao(dataSource);
		/*List<Todo> result = dao.selectTodoList();*/
		Todo todo = new Todo().setTodo("Test2").setCompleted(0);
		
		System.out.println("-------Query Start------------");
		/*for (Todo todo : result) {
			System.out.println(todo.toString());
		} // SELECT LIST */
		
		dao.insert(todo);
		
		System.out.println("-------Query End--------------");
		context.close();
	}
}
