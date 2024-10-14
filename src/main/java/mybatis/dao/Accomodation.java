package mybatis.dao;

import com.example.tnj.domain.AccProperty;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Accomodation {
    @Select("select * from accom where id = #{id}")
    public List<AccProperty> listminel(String id);
    @Insert("insert into accom (accomNum, id, postcode, address, price, accCall, adultPrice, kidPrice, dayoff, category, accType, accPic, onSale, accomRule, informtext, ocupacy, maximumOcupacy) values (#{accomNum}, #{id}, #{postcode}, #{address}, #{price}, #{accCall}, #{adultPrice}, #{kidPrice}, #{dayoff}, #{category}, #{accType}, #{accPic}, #{onSale}, #{accomRule}, #{informtext}, #{ocupacy}, #{maximumOcupacy} )")
    public boolean accinsert(AccProperty ap);
}
