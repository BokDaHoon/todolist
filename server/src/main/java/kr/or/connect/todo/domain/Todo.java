package kr.or.connect.todo.domain;

import java.sql.Date;

public class Todo {
	private Integer id;
	private String todo;
	private Integer completed;
	private Date date;
	
	
	public Integer getId() {
		return id;
	}
	public Todo setId(Integer id) {
		this.id = id;
		return this;
	}
	
	
	public String getTodo() {
		return todo;
	}
	public Todo setTodo(String todo) {
		this.todo = todo;
		return this;
	}
	
	
	public Integer getCompleted() {
		return completed;
	}
	public Todo setCompleted(Integer completed) {
		this.completed = completed;
		return this;
	}
	
	
	public Date getDate() {
		return date;
	}
	public Todo setDate(Date date) {
		this.date = date;
		return this;
	}
	
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	}
	
}
