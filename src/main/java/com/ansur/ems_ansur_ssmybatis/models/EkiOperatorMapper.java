package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EkiOperatorMapper {

	@Select("select * from eki_operator")
	@Results(value = { 
			@Result(column="eo_id", property="eo_id"),
			@Result(column="eo_number", property="eo_number"), 
			@Result(column="eo_name", property="eo_name"),
			@Result(column="eo_logo_name", property="eo_logo_name")
	})
	List<EkiOperator> getEkiOperatorList();

	@Insert("insert into eki_operator (eo_number,eo_name,eo_logo_name) values (#{eo_number},#{eo_name},#{uploadImageFileName})")
	void insertEkiOperator(EkiOperator ekiOperator);

	@Select("select * from eki_operator where eo_id = #{id}")
	EkiOperator getEkiOperatorById(Integer id);
	

	@Select("select max(eo_number) from eki_operator")
	String getLastEkiOperatorNumber();

	@Select("select eo_number,eo_name,eo_logo_name from eki_operator")
	List<EkiOperator> getEONameAndNumberList();

	@Select("select * from eki_operator where eo_number=#{eo_number}")
	EkiOperator getAllEoByEONumber(String eo_number);

	
	@Select("select count(eo_id) from eki_operator where eo_name=#{eo_name}")
	boolean checkAlreadyExit(String eo_name);

	@Select("select * from eki_operator where eo_name = #{eo_name}")
	EkiOperator getEkiOperatorByName(String eo_name);

	@Update("update eki_operator set eo_name = #{eo_name} where eo_id=#{eo_id}")
	void updateEkiOperator(EkiOperator ekiOperator);

	@Update("update eki_operator set eo_logo_name=#{uploadImageFileName} where eo_id=#{eo_id}")
	void updateImgName(EkiOperator ekiOperator);

	@Delete("delete from eki_operator where eo_id=#{id}")
	void delete(Integer id);

	

	
	
	
	
	

}
