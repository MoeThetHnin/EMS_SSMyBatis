package com.ansur.ems_ansur_ssmybatis.models;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TransporationMapper {
	
	@Select("select * from transporation")
	@Results(value={
            @Result(column="t_id", property="t_id"),
            @Result(column="t_date", property="t_date"),
            @Result(column="t_operator", property="t_operator"),
            @Result(column="t_line", property="t_line"),
            @Result(column="t_kuru_eki", property="t_kuru_eki"),
            @Result(column="t_kaeru_eki", property="t_kaeru_eki"),
            @Result(column="t_charge", property="t_charge"),
            @Result(column="t_purpose", property="t_purpose"),
            @Result(column="t_kuru_charge", property="t_kuru_charge"),
            @Result(column="t_kaeru_charge", property="t_kaeru_charge"),
            @Result(column="t_remark", property="t_remark")
    })
	List<Transporation> getTranspoList();


	
	@Insert("insert into transporation (t_date,emp_id,t_charge,t_operator,t_line,t_kuru_eki,t_kaeru_eki,t_purpose,t_kuru_charge,t_kaeru_charge,t_remarks) values(#{t_date},#{emp_id},#{t_charge},#{t_operator},#{t_line},#{t_kuru_eki},#{t_kaeru_eki},#{t_purpose},#{t_kuru_charge},#{t_kaeru_charge},#{t_remarks})")
	void insertTranspoInfo(Transporation transpo);


	@Select("select * from transporation where emp_id=#{emp_id}")
	List<Transporation> getTranspoListByEmpId(String emp_id);

	@Select("select sum(t_kuru_charge) from transporation where extract(month from t_date)=#{param1} and extract(year from t_date)=#{param2} and emp_id=#{param3}")
	Integer totalKuru(Integer param1, Integer param2, String param3);
	
	@Select("select sum(t_kaeru_charge) from transporation where extract(month from t_date)=#{param1} and extract(year from t_date)=#{param2} and emp_id=#{param3}")
	Integer totalKaeru(Integer param1, Integer param2, String param3);


	@Select("select sum(t_charge) from transporation where extract(month from t_date)=#{param1} and extract(year from t_date)=#{param2} and emp_id=#{param3}")
	Integer totalCharge(Integer param1, Integer integer, String param3);


	@Select("select * from transporation where extract(month from t_date)=#{param1} and extract(year from t_date)=#{param2} and emp_id=#{param3}")
	List<Transporation> getMonthlyTranspoListByEmpId(Integer param1, Integer integer, String param3);




}
