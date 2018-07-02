package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface EmployeeMapper {

    @Select("select * from employee order by emp_id asc")
    @Results(value={
            @Result(column="id", property="id"),
            @Result(column="emp_id", property="emp_id"),
            @Result(column="name", property="name"),
            @Result(column="password", property="password"),
            @Result(column="department", property="department"),
            @Result(column="post", property="post"),
            @Result(column="status", property="status"),
            @Result(column="email", property="email"),
            @Result(column="img_name", property="img_name"),
            @Result(column="address", property="address"),
            @Result(column="total_charge", property="total_charge")
    })
    List<Employee> getAllEmployee();
    
    @Select("select * from employee where emp_id=#{emp_id}")
   	Employee getEmployeeByEmpId(String emp_id);
    
    @Select("select max(emp_id) from employee")
	String getLastEmpId();
    
    @Select("select * from employee where emp_id=#{emp_id} and password=#{password}")
    Employee checkLoginData(Employee employee);
    
    @Select("select count(id) from employee where emp_id=#{emp_id} and password=#{old_password}")
   	boolean checkOldPassword(Employee employee);
    
    @Select("select count(id) from employee where email=#{email}")
	boolean checkEmail(String email);
    
    
    @Insert("insert into employee (emp_id,name,password,department,post,status,email,img_name,address) values (#{emp_id},#{name},#{password},#{department},#{post},#{status},#{email},#{uploadImageFileName},#{address})")
    void insertEmployee(Employee employee);  


    
    
    @Update("update employee set total_charge=#{l} where emp_id=#{emp_id}")
	void insertTotalCharge(long l, String emp_id);
    
    @Update("update employee set name=#{name}, department=#{department}, post=#{post}, status=#{status} where emp_id=#{emp_id}")
    void updateEmployeeByAdmin(Employee employee);
    
    @Update("update employee set name=#{name}, email=#{email}, address=#{address} where emp_id=#{emp_id}")
    void updateEmployee(Employee employee);    
    
    @Update("update employee set password=#{password} where emp_id=#{emp_id}")
    void updateEmpPassword(Employee employee);
    
    @Update("update employee set img_name = #{uploadImageFileName} where emp_id=#{emp_id}")
    void updateImgName(Employee employee);
    
    
    @Delete("delete from employee where id=#{id}")
    void deleteEmployee(Integer id);

    @Select("select * from employee where email = #{email}")
	Employee checkUpdateEmail(String email);

    

	

   

	

    
	


    




	
	





   
}