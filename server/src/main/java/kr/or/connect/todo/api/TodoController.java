package kr.or.connect.todo.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	private final TodoService service;
	
	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping("/selectTodoList")
	public List<Todo> selectTodoList() {
		return service.selectTodoList();
	}
	
	@GetMapping("/countLeftItem")
	public Integer selectCountLeftItem() {
		return service.selectCountLeftItem();
	}
	
	@PostMapping("/insert")
	public void insertData(@RequestParam Map<String, Object> param) {
		service.insertTodo(param);
	}
	
	@PutMapping("/update")
	public void updateData(@RequestParam Map<String, Object> param) {
		service.updateTodo(param);
	}
	
	@DeleteMapping("/{id}")
	public void deleteData(@PathVariable Integer id) {
		service.deleteTodo(id);
	}
	
	@DeleteMapping("/completedDataDelete")
	public void completedDataDelete() {
		service.completedTodoDelete();
	}
}
