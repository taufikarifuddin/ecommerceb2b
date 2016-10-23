package com.ecommerce.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.base.ControllerInterface;
import com.ecommerce.dao.productcategory.ProductCategory;
import com.ecommerce.dao.productcategory.ProductCategoryDAO;
import com.ecommerce.resource.BaseResponse;
import com.ecommerce.resource.Constant;

@RestController
public class ProductCategoryController implements ControllerInterface<ProductCategory>{
	
	@Autowired
	ProductCategoryDAO productCategoryDAO;
	
	@PostMapping(value="/admin/productCategory/update")
	@Override
	public BaseResponse update(@RequestBody @Valid ProductCategory data,BindingResult bindingResult) {
		return doUpdate(data, bindingResult);
	}

	@PostMapping(value="/admin/productCategory/remove")
	@Override
	public BaseResponse delete(@RequestParam( required = true, defaultValue = "0" )int id) {
		BaseResponse response = new BaseResponse(Constant.DEFAULT_UPDATE_FAIL_MSG, true);
		if( productCategoryDAO.delete(id) ){
			response.setErrorResponse(false);
			response.setMessageResponse(Constant.DEFAULT_UPDATE_FAIL_MSG);
		}
		return response;
	}

	@GetMapping(value="/productCategory/get")	
	@Override
	public BaseResponse get(@RequestParam( required = true, defaultValue = "0" ) int id) {
		return new BaseResponse(productCategoryDAO.getOne(id));
	}

	@GetMapping(value="/productCategory/getAll")	
	@Override
	public BaseResponse getAll() {
		return new BaseResponse(productCategoryDAO.getAll());
	}
	
	@Override
	public BaseResponse doUpdate(ProductCategory data, BindingResult bindingResult) {
		
		BaseResponse response = new BaseResponse(Constant.DEFAULT_UPDATE_FAIL_MSG, true);
		if( bindingResult.hasErrors() ){
			response.setDataResponse(bindingResult.getAllErrors());
			return response;
		}
		
		if( data.product_category_id == 0 ){
			productCategoryDAO.create(data);
		}else{
			productCategoryDAO.update(data);
		}
		
		response.setMessageResponse(Constant.DEFAULT_UPDATE_SUCCESS_MSG);
		response.setErrorResponse(false);
		
		return response;
	}
	
}