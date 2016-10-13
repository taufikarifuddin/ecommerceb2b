package com.ecommerce.base;

import java.util.List;

public interface DAOInterface<T> {
	public T getOne(String id);
	public T getAll();
	public List<T> getByQuery(String query);
	public boolean update(T data);
	public boolean create(T data);
	public boolean delete(String id);
	public boolean delete(String id,String condition);		
}
