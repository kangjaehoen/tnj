package mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;


@Mapper
public interface AbledateMapper {
    @Insert("insert into abledate (accomNum, date, ableStatus) values (#{accomNum}, #{date}, #{ableStatus})")
    public int createdate(int accomNum, LocalDate date, String ableStatus);
    @Update("update abledate set ableStatus =#{id} where accomNum = #{accomNum} and date >= #{chkin_Date} and date <= #{chkout_Date}")
    public int book (String id, LocalDate chkin_Date, LocalDate chkout_Date);

}
