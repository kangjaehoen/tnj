package mybatis.dao;

import com.example.tnj.domain.AccDetailDTO;
import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.ResVO;
import com.example.tnj.domain.ReservationDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ReservationMapper {
    //숙소 정보
    @Select("select * from accom where accomNum=#{accomNum}")
    public List<AccDetailDTO> listAll(@Param("accomNum") int accomNum);

    //예약정보 가져오기
    @Select("SELECT r.resDate, r.chkin_Date, r.chkout_Date, r.adultCnt, r.kidCnt, a.chkin_Time, a.chkout_Time, a.accName " +
            "FROM reservation r JOIN accom a ON r.accomNum = a.accomNum WHERE r.accomNum = #{accomNum}")
    public List<ReservationDTO> oneReserv(@Param("accomNum") int accomNum);
    
    //리뷰 개수
    @Select("SELECT COUNT(*) FROM review WHERE accomNum = #{accomNum}")
    public int cntReview(@Param("accomNum") int accomNum);

    //리뷰 별점
    @Select("SELECT round(avg(satisfy),1) " +
            "FROM review r JOIN accom a ON r.accomNum = a.accomNum WHERE r.accomNum= #{accomNum}")
    public double revRating(@Param("accomNum") int accomNum);

    //숙소 가격
    @Select("SELECT DISTINCT FORMAT(a.price,0) AS price\n" +
            "FROM accom a JOIN reservation r ON r.accomNum = a.accomNum\n" +
            "WHERE r.accomNum = #{accomNum}")
    public String accomPrice(@Param("accomNum") int accomNum);

    //숙소 정보
    @Select("select * from accom where accomNum =#{accomNum}")
    public AccVO accInfo(@Param("accomNum") int accomNum);

    //예약 중복확인
    @Select("SELECT COUNT(*) FROM reservation WHERE chkin_Date = #{chkin_Date} AND chkout_Date = #{chkout_Date}")
    public int chkDuplicate(ReservationDTO rDTO);

    //예약 insert
    @Insert("insert into reservation (resDate,chkin_Date, chkout_Date, adultCnt, kidCnt, id, accomNum) values( now(),#{chkin_Date}, #{chkout_Date}, #{adultCnt}, #{kidCnt},#{id}, #{accomNum})")
    public void insertRes(ReservationDTO rDTO);


}

