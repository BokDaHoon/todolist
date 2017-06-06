package kr.or.connect.todo.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {
	
	@Autowired
	private TodoDao dao;
	
	@Test
	public void shouldSelectAll() {
		List<Todo> allTodos = dao.selectTodoList();
		assertThat(allTodos, is(notNullValue()));
	}
	
	@Test
	public void shouldInsertAndSelect() {
		Todo todo = new Todo();
		todo.setTodo("TEST");
		Integer id = dao.insert(todo);
		
		assertThat(dao.selectTodoById(id).getTodo(), is("TEST"));
	}
}
