package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DepartmentMapper {
	
	@Results(value= {
			@Result(column="dep_id", property="dep_id"),
			@Result(column="dep_number", property="dep_number"),
			@Result(column="dep_name", property="dep_name")
	})
	@Select("select * from department")
	List<Department> getDepartmentList();
	
	@Select("select max(dep_number) from department")
	String getLastDepNum();
	
	@Select("select * from department where dep_id = #{dep_id}")
	Department getDepartmentById(int dep_id);
	

	
	@Insert("insert into department (dep_number,dep_name) values (#{dep_number},#{dep_name})")
	void insertDepartment(Department department);

	@Update("update department set dep_name = #{dep_name} where dep_id=#{dep_id}")
	void updateDepartment(Department department);

	
	@Select("select count(dep_id) from department where dep_name = #{dep_name}")
	boolean checkAlreadyExist(String dep_name);

	@Select("select * from department where dep_name = #{dep_name}")
	Department getDepartmentByName(String dep_name);

	@Delete("delete from department where dep_id=#{id}")
	void deleteById(Integer id);

	


	
	
	

}
