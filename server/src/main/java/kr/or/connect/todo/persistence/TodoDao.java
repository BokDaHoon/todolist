package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.Todo;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
	
	private final String SELECT_LIST = "SELECT ID, TODO, COMPLETED, DATE FROM TODO ORDER BY DATE DESC";
	private final String SELECT_BY_ID = "SELECT ID, TODO, COMPLETED, DATE FROM TODO WHERE id = :id";
	private final String SELECT_COUNT_LEFT_ITEM = "SELECT COUNT(*) FROM TODO WHERE COMPLETED = 0";
	private final String UPDATE_COMPLETED = "UPDATE todo SET completed = :completed WHERE id = :id";
	private final String DELETE_BY_ID = "DELETE FROM todo WHERE id = :id";
	private final String DELETE_COMPLETED = "DELETE FROM todo WHERE completed = :completed";
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingColumns("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public Integer insert(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public List<Todo> selectTodoList() {
		return jdbc.query(SELECT_LIST, rowMapper);
	}
	
	public Todo selectTodoById(Integer id) {
		Map<String, ?> params = Collections.singletonMap("completed", 1);
		return jdbc.queryForObject(SELECT_LIST, params, rowMapper);
	}
	
	public Integer selectCountLeftItem() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(SELECT_COUNT_LEFT_ITEM, params, Integer.class);
	}
	
	public Integer updateById(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(UPDATE_COMPLETED, params);
	}
	
	public Integer deleteById(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(DELETE_BY_ID, params);
	}

	public void completedTodoDelete() {
		Map<String, ?> params = Collections.singletonMap("completed", 1);
		jdbc.update(DELETE_COMPLETED, params);	
	}

}
