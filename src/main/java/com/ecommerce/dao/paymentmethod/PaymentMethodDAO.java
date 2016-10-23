package com.ecommerce.dao.paymentmethod;

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
public class PaymentMethodDAO implements DAOInterface<PaymentMethod>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public PaymentMethod getOne(int id) {
		try{
			String query = "select * from payment_method where payment_method_id = ?";
			return (PaymentMethod) jdbcTemplate.queryForObject(query, new Object[]{ id },new PaymentMethodRowMapper());
		}catch( Exception e ){
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<PaymentMethod> getAll() {
		try{
			String query = "select * from payment_method";
			return jdbcTemplate.query(query, new PaymentMethodRowMapper());
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<PaymentMethod> getByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PaymentMethod data) {
		String query = "UPDATE payment_method SET "
				+ "payment_method_name=?,"
				+ "payment_method_desc=?,"
				+ " WHERE payment_method_id = ?";
		return jdbcTemplate.update(query, new Object[]{ data.payment_method_name,data.payment_method_desc,
				data.payment_method_id}) > 0;
	}

	@Override
	public boolean create(PaymentMethod data) {
		String query = "INSERT INTO payment_method"
				+ "(payment_method_name, payment_method_desc) "
				+ "VALUES (?,?)";
		return jdbcTemplate.update(query,new Object[]{ data.payment_method_name,
				data.payment_method_desc}) > 0;
	}

	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM payment_method WHERE payment_method_id = ? ";
		return jdbcTemplate.update(query,id) > 0;
	}
	
	class PaymentMethodRowMapper implements RowMapper<PaymentMethod>{

		@Override
		public PaymentMethod mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			PaymentMethod pm = new PaymentMethod();
			pm.payment_method_desc = rs.getString("payment_method_desc");
			pm.payment_method_name = rs.getString("payment_method_name");
			pm.payment_method_id = rs.getInt("payment_method_id");
			return pm;
		}
		
	}

}
