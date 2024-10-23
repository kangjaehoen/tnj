package mybatis.dao;


import com.example.tnj.domain.ResVO;
import org.apache.ibatis.annotations.*;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ResMapper {
//예약확인//2차에선 리스트 타입을 abledate로 바꿀 예정
@Select("select * from reservation where accomNum in (select accomNum from accom where id = #{id} and  chkin_Date >= #{startdate} and chkin_Date <= #{enddate})")
public List<ResVO> reservationcheck(String id, LocalDate startdate, LocalDate enddate);
}
