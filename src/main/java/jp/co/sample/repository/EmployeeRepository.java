package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
		=(rs,i) ->{
	Employee employee = new Employee();
	employee.setId(rs.getInt("id"));
	employee.setName(rs.getString("name"));
	employee.setImage(rs.getString("image"));
	employee.setGender(rs.getString("gender"));
	employee.setHireDate(rs.getInt("hireDate"));
	employee.setMailAddress(rs.getString("mailAddress"));
	employee.setZipCode(rs.getString("zipCode"));
	employee.setAddress(rs.getString("address")); 
	employee.setTelephone(rs.getString("telephone")); 
	employee.setSalary(rs.getInt("salary")); 
	employee.setCharacteristics(rs.getString("characteristics")); 
	employee.setDependentsCount(rs.getInt("dependentsCount")); 
	
	return employee;
	}
		
	public List<Employee> findAll(){
		
		
	}
		
		
		
}
