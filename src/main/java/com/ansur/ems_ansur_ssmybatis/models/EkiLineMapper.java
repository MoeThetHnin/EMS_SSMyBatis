package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EkiLineMapper {
	
	
	@Select("select * from eki_line")
	@Results(value= {
		@Result(column="el_id", property="el_id"),
		@Result(column="el_number", property="el_number"),
		@Result(column="el_line", property="el_line"),
		@Result(column="el_line_logo_name",property="el_line_logo_name"),
		@Result(column="eo_number",property="eo_number"),
		@Result(column="eo_logo_name",property="eo_logo_name"),
		@Result(column="eo_name",property="eo_name")
	})
	List<EkiLine> getEkiLineList();

	@Insert("insert into eki_line (el_number,el_line,el_line_logo_name,eo_number,eo_logo_name,eo_name) values(#{el_number},#{el_line},#{uploadImageFileName},#{eo_number},#{eo_logo_name},#{eo_name})")
	void insertEkiLine(EkiLine ekiLine);
	
	@Select("SELECT DISTINCT eo_number,eo_logo_name,eo_name FROM eki_line")
	List<EkiLine> getEOLogoList();

	@Select("select * from eki_line where eo_number=#{eo_number}")
	List<EkiLine> getEkiLineListByOperator(String eo_number);

	

	
	
	

}
