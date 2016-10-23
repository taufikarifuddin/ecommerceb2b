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
import com.ecommerce.dao.paymentmethod.PaymentMethod;
import com.ecommerce.dao.paymentmethod.PaymentMethodDAO;
import com.ecommerce.resource.BaseResponse;
import com.ecommerce.resource.Constant;

@RestController
public class PaymentMethodController implements ControllerInterface<PaymentMethod>{

	@Autowired
	PaymentMethodDAO paymentMethodDAO;
	
	@PostMapping(value="/admin/paymentMethod/update")
	@Override
	public BaseResponse update(@RequestBody @Valid PaymentMethod data,BindingResult bindingResult) {
		return doUpdate(data, bindingResult);
	}

	@PostMapping(value="/admin/paymentMethod/remove")
	@Override
	public BaseResponse delete(@RequestParam( required = true, defaultValue = "0" )int id) {
		BaseResponse response = new BaseResponse(Constant.DEFAULT_UPDATE_FAIL_MSG, true);
		if( paymentMethodDAO.delete(id) ){
			response.setErrorResponse(false);
			response.setMessageResponse(Constant.DEFAULT_UPDATE_FAIL_MSG);
		}
		return response;
	}

	@GetMapping(value="/paymentMethod/get")	
	@Override
	public BaseResponse get(@RequestParam( required = true, defaultValue = "0" ) int id) {
		return new BaseResponse(paymentMethodDAO.getOne(id));
	}

	@GetMapping(value="/paymentMethod/getAll")	
	@Override
	public BaseResponse getAll() {
		return new BaseResponse(paymentMethodDAO.getAll());
	}
	
	@Override
	public BaseResponse doUpdate(PaymentMethod data, BindingResult bindingResult) {
		
		BaseResponse response = new BaseResponse(Constant.DEFAULT_UPDATE_FAIL_MSG, true);
		if( bindingResult.hasErrors() ){
			response.setDataResponse(bindingResult.getAllErrors());
			return response;
		}
		
		if( data.payment_method_id == 0 ){
			paymentMethodDAO.create(data);
		}else{
			paymentMethodDAO.update(data);
		}
		
		response.setMessageResponse(Constant.DEFAULT_UPDATE_SUCCESS_MSG);
		response.setErrorResponse(false);
		
		return response;
	}
}
