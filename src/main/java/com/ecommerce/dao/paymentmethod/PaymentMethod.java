package com.ecommerce.dao.paymentmethod;

import javax.validation.constraints.NotNull;

public class PaymentMethod {
	
	public int payment_method_id;
	
	@NotNull(message = "Nama Jenis Pembayaran Tidak Boleh Kosong")
	public String payment_method_name;

	public String payment_method_desc;
	
	
	public PaymentMethod() {
		// TODO Auto-generated constructor stub
	}


	public PaymentMethod(int payment_method_id, String payment_method_name, String payment_method_desc) {
		super();
		this.payment_method_id = payment_method_id;
		this.payment_method_name = payment_method_name;
		this.payment_method_desc = payment_method_desc;
	}


	public int getPayment_method_id() {
		return payment_method_id;
	}


	public void setPayment_method_id(int payment_method_id) {
		this.payment_method_id = payment_method_id;
	}


	public String getPayment_method_name() {
		return payment_method_name;
	}


	public void setPayment_method_name(String payment_method_name) {
		this.payment_method_name = payment_method_name;
	}


	public String getPayment_method_desc() {
		return payment_method_desc;
	}


	public void setPayment_method_desc(String payment_method_desc) {
		this.payment_method_desc = payment_method_desc;
	}
	
}
