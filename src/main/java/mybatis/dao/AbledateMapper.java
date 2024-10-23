package mybatis.dao;

import com.example.tnj.domain.AbleDateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface AbledateMapper {
    //불러오기 숙소번호로
    @Select("select * from abledate where accomNum =#{accomNum}")
    public List<AbleDateVO> ableList (int accomNum);
    //불러오기 날짜만
    @Select("select date from abledate where accomNum =#{accomNum}")
    public List<LocalDate> ableListdate (int accomNum);
    //불러오기 id로 예약된 것들만
    @Select("select * from abledate where accomNum in (select accomNum from accom where id =#{id}) and id is not null")
    public List<AbleDateVO>myablelist (String id);

    //등록 각각-오래걸림
    @Insert("insert into abledate (accomNum, date, ableStatus) values (#{accomNum}, #{date}, #{ableStatus})")
    public int createdate(int accomNum, LocalDate date, String ableStatus);
    //등록 동시에
    @Insert({
            "<script>",
            "INSERT INTO abledate (accomNum, date, ableStatus) VALUES ",
            "<foreach collection='dates' item='item' separator=','>",
            "(#{item.accomNum}, #{item.date}, #{item.ableStatus})",
            "</foreach>",
            "</script>"
    })
    int batchInsertDates(@Param("dates") List<AbleDateVO> ableDates);
    //삭제 예약 안된 것만
    @Delete("delete from abledate where id is null and accomNum =#{accomNum}")
    public int deleteabledates(int accomNum);

    //예약시 변경
    @Update("update abledate set id =#{id} where accomNum = #{accomNum} and date >= #{chkin_Date} and date <= #{chkout_Date}")
    public int book (String id, int accomNum, LocalDate chkin_Date, LocalDate chkout_Date);
}
