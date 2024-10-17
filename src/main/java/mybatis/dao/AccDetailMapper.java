package mybatis.dao;

import com.example.tnj.domain.AccDetailDTO;
import com.example.tnj.domain.AccVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccDetailMapper {
    //숙소 정보
    @Select("select * from accom where accomNum=#{accomNum}")
    public List<AccDetailDTO> listAll(@Param("accomNum") int accomNum);

    //리뷰 개수
    @Select("SELECT COUNT(*) AS review_count FROM review WHERE accomNum = #{accomNum}")
    public String cntReview(@Param("accomNum") int accomNum);

    //리뷰 별점
    @Select("SELECT round(avg(satisfy),1) " +
            "FROM review r JOIN accom a ON r.accomNum = a.accomNum WHERE r.accomNum= #{accomNum}")
    public double revRating(@Param("accomNum") int accomNum);

    //체크 인아웃 시간
    @Select("select * from accom where accomNum=#{accomNum}")
    public AccVO acinfo(int accomNum);
}
