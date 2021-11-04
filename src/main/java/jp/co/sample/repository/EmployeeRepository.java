package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * @author nemur
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));

		return employee;
	};
	
	/**
	 * @author nemur
	 * 従業員一覧情報を入社日（降順）で取得。
	 * 従業員が存在しない場合はサイズ０件の従業員一覧を返す。
	 * 
	 */
	public List<Employee> findAll() {
		
		String findSql = "SELECT * FROM employee ORDER BY hireDate DESC;";
		
		List<Employee> employeeList = template.query(findSql,EMPLOYEE_ROW_MAPPER);
		if (employeeList.size() == 0) {
			return null;
		} 
		return (List<Employee>) employeeList.get(0);
	}
	
	/**
	 * @author nemur
	 * 主キーから従業員情報を取得する。
	 * 従業員が存在しない場合はSpringが自動的に例外を発生する。
	 */
	public Employee load(Integer id) {
		String sql="SELECT * FROM employee WHERE id=:id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		
		Employee employee = template.queryForObject(sql,param,EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	/**
	 * @author nemur
	 * 従業員情報を変更する。
	 * idカラムを除いた従業員情報すべてのカラムを更新できるようなSQLを発行する。
	 * 全行更新されないようにWhere句の指定を考える。
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql ="UPDATE employees "
				+ "SET name=:name,image=:image,gender=:gender,hireDate=:hireDate,"
				+ "mailAddress=:mailAddress,zipCode=:zipCode,address=:address,"
				+ "telephone=:telephone,salary=:salary,characteristics=:characteristics,"
				+ "dependentsCount=:dependentsCount"
				+ "WHERE id=null;";
		template.update(updateSql, param);
	}

}
