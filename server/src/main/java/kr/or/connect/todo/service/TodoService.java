package kr.or.connect.todo.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	
	private TodoDao dao;
	
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public void insertTodo(Map<String, Object> param) {
		dao.insert(new Todo().setTodo((String) param.get("todo")));
	}
	
	public List<Todo> selectTodoList() {
		return dao.selectTodoList();
	}

	public void completedTodoDelete() {
		dao.completedTodoDelete();
	}

	public void updateTodo(Map<String, Object> param) {
		dao.updateById(new Todo()
				.setId(Integer.parseInt(param.get("id").toString()))
				.setCompleted(Integer.parseInt(param.get("completed").toString())));
	}
	
	public void deleteTodo(Integer id) {
		dao.deleteById(new Todo().setId(id));
	}

	public Integer selectCountLeftItem() {
		return dao.selectCountLeftItem();
	}
}
