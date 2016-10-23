package com.ecommerce.dao.productcategory;

import javax.validation.constraints.NotNull;

public class ProductCategory {

	public int product_category_id;
	
	@NotNull(message = "Nama Kategori tidak boleh kosong")
	public String product_category_name;

	@NotNull(message = "Icon Produk Kategori tidak boleh kosong")
	public String product_category_icon;
	
	public String product_category_desc;
	
	
	public ProductCategory(int product_category_id, String product_category_name, String product_category_icon,
			String product_category_desc) {		
		this.product_category_id = product_category_id;
		this.product_category_name = product_category_name;
		this.product_category_icon = product_category_icon;
		this.product_category_desc = product_category_desc;
	}


	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}


	public int getProduct_category_id() {
		return product_category_id;
	}


	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}


	public String getProduct_category_name() {
		return product_category_name;
	}


	public void setProduct_category_name(String product_category_name) {
		this.product_category_name = product_category_name;
	}


	public String getProduct_category_icon() {
		return product_category_icon;
	}


	public void setProduct_category_icon(String product_category_icon) {
		this.product_category_icon = product_category_icon;
	}


	public String getProduct_category_desc() {
		return product_category_desc;
	}


	public void setProduct_category_desc(String product_category_desc) {
		this.product_category_desc = product_category_desc;
	}
	
	
	
	
}
