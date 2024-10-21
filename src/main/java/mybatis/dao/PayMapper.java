package mybatis.dao;

import com.example.tnj.domain.PayVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PayMapper {

    //결제확인 //이거 pay가 아니라 res라 다시해라
    @Select("select * from reservation where accomNum in (select accomNum from accom where id = #{id} and  resDate > #{startdate} and resDate <#{enddate})")
    public List<PayVO> reservationcheck(String id, LocalDate startdate, LocalDate enddate);



}
