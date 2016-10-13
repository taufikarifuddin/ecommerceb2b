package com.ecommerce.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseModel<T> implements DAOInterface<T>{

	protected String join;
	protected String where;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public T getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getByQuery(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(T data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(T data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id, String condition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	abstract String getTableName();
	abstract String[] getColumn();
	abstract String getPrimaryKey();
	abstract BaseModel<T> join(); 
	abstract BaseModel<T> where(); 
	abstract BaseModel<T> select(); 
	
}
