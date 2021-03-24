package com.todo.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.todo.entities.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {

}
