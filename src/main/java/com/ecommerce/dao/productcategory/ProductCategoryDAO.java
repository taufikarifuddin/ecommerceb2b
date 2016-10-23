package com.ecommerce.dao.productcategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.base.DAOInterface;

@Service
public class ProductCategoryDAO implements DAOInterface<ProductCategory>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public ProductCategory getOne(int id) {
//		System.out.println(id+" nya gan");
		try{
			String query = "select * from product_category where product_category_id = ?";
			return (ProductCategory) jdbcTemplate.queryForObject(query, new Object[]{ id },new ProductCategoryRowMapper());
		}catch( Exception e ){
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductCategory> getAll() {
		try{
			String query = "select * from product_category";
			return jdbcTemplate.query(query, new ProductCategoryRowMapper());
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductCategory> getByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ProductCategory data) {
		String query = "UPDATE product_category SET "
				+ "product_category_name=?,"
				+ "product_category_icon=?,"
				+ "product_category_desc=?"
				+ " WHERE product_category_id = ?";
		return jdbcTemplate.update(query, new Object[]{ data.product_category_name,data.product_category_icon,
				data.product_category_desc,data.product_category_id}) > 0;
	}

	@Override
	public boolean create(ProductCategory data) {
		String query = "INSERT INTO product_category"
				+ "(product_category_name, product_category_icon, product_category_desc) "
				+ "VALUES (?,?,?)";
		return jdbcTemplate.update(query,new Object[]{ data.product_category_name,data.product_category_icon,
				data.product_category_desc }) > 0;
	}

	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM product_category WHERE product_category_id = ? ";
		return jdbcTemplate.update(query,id) > 0;
	}
	
	class ProductCategoryRowMapper implements RowMapper<ProductCategory>{

		@Override
		public ProductCategory mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			ProductCategory pc = new ProductCategory();
			pc.product_category_desc = rs.getString("product_category_desc");
			pc.product_category_icon = rs.getString("product_category_icon");
			pc.product_category_id = rs.getInt("product_category_id");
			pc.product_category_name = rs.getString("product_category_name");
			return pc;
		}
		
	}
}

