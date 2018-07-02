package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PostMapper {
	
	@Results(value = {
			@Result(column = "p_id", property="p_id"),
			@Result(column = "p_number", property="p_number"),
			@Result(column = "p_name", property="p_name")
	})
	@Select("select * from post")	
	List<Post> getPostList();

	
	@Select("select max(p_number) from post")
	String getLastPostNum();
	
	@Select("select * from post where p_id = #{id}")
	Post getPostById(Integer id);
	
	@Insert("insert into post (p_number,p_name) values (#{p_number},#{p_name})")
	void insertPost(Post post);


	@Select("select count(p_id) from post where p_name=#{p_name}")
	boolean checkAlreadyExist(Object p_name);

	@Select("select * from post where p_name=#{p_name}")
	Post getPostByName(String p_name);


	@Update("update post set p_name = #{p_name} where p_id=#{p_id}")
	void updatePost(Post post);

	@Delete("delete from post where p_id=#{id}")
	void deleteById(Integer id);
	


	
	
	
	

}
