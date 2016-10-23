package com.ecommerce.base;

import org.springframework.validation.BindingResult;

import com.ecommerce.resource.BaseResponse;

public interface ControllerInterface<T> {
	public BaseResponse update(T data,BindingResult bindingResult);
	public BaseResponse delete(int id);
	public BaseResponse get(int id);	
	public BaseResponse getAll();	
	public BaseResponse doUpdate(T data,BindingResult bindingResult);
}
