package com.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.DAO.TodoRepository;
import com.todo.entities.Todo;

@RestController
public class TodoController {
	private final TodoRepository repository;

	  TodoController(TodoRepository repository) {
	    this.repository = repository;
	  }


	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @CrossOrigin
	  @GetMapping("/todos")
	  List<Todo> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]

	  @CrossOrigin
	  @PostMapping("/todos")
	  Todo newTodo(@RequestBody Todo newTodo) {
	    return repository.save(newTodo);
	  }

	  // Single item
	  @CrossOrigin
	  @GetMapping("/todos/{id}")
	  Todo one(@PathVariable String id) {
	    
	    return repository.findById(id)
	      .orElseThrow(() -> new TodoNotFoundException(id));
	  }

	  @CrossOrigin
	  @PutMapping("/todos/{id}")
	  Todo replaceTodo(@RequestBody Todo newTodo, @PathVariable String id) {
	    
	    return repository.findById(id)
	      .map(Todo -> {
	        Todo.setData(newTodo.getData());
	        Todo.setDate(newTodo.getDate());
	        return repository.save(Todo);
	      })
	      .orElseGet(() -> {
	        newTodo.setId(id);
	        return repository.save(newTodo);
	      });
	  }

	  @CrossOrigin
	  @DeleteMapping("/todos/{id}")
	  void deleteTodo(@PathVariable String id) {
	    repository.deleteById(id);
	  }
}
