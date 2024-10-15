package mybatis.dao;

import com.example.tnj.domain.PageDTO;
import com.example.tnj.domain.ReplyDTO;
import com.example.tnj.domain.ReplyRatingVO;
import lombok.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {

    @Insert("INSERT INTO " +
            "review(satisfy,accuracy,clean,scp,revContent,accomNum,id, hiredate) " +
            "VALUES (#{satisfy},#{accuracy},#{clean},#{scp},#{revContent},#{accomNum},#{id},now())")
    public int insertReply(ReplyDTO dto);

    @Select("SELECT " +
            " COUNT(CASE WHEN satisfy = 5 THEN 1 END) AS count5," +
            " COUNT(CASE WHEN satisfy = 4 THEN 1 END) AS count4," +
            " COUNT(CASE WHEN satisfy = 3 THEN 1 END) AS count3," +
            " COUNT(CASE WHEN satisfy = 2 THEN 1 END) AS count2," +
            " COUNT(CASE WHEN satisfy = 1 THEN 1 END) AS count1," +
            " COUNT(*) as reviewCount, avg(satisfy) as satisAvg, avg(accuracy) as accuracyAvg, avg(clean) as cleanAvg , avg(scp) scpAvg " +
            " FROM review r " +
            " JOIN accom a " +
            " ON r.accomNum = a.accomNum " +
            " WHERE r.accomNum= #{accomNum}")
    public ReplyRatingVO rating(int accomNum);

    @Select(" SELECT revContent, hiredate, satisfy, u.id " +
            " FROM review r " +
            " JOIN user u " +
            " ON r.id = u.id " +
            " JOIN accom a " +
            " ON r.accomNum = a.accomNum" +
            " WHERE r.accomNum= #{accomNUm} " +
            " ORDER BY satisfy DESC")
    public List<ReplyDTO> listReply(int accomNum);

    @Select(" SELECT revContent, hiredate, satisfy, u.id " +
            " FROM review r " +
            " JOIN user u ON r.id = u.id " +
            " JOIN accom a ON r.accomNum = a.accomNum " +
            " WHERE r.accomNum = #{accomNum}" +
            " ORDER BY satisfy DESC " +
            " LIMIT #{pdto.countNum} OFFSET #{pdto.startNum}")
    public List<ReplyDTO> pagenation(PageDTO pdto,int accomNum);

    @Select(" SELECT count(rnum) FROM review WHERE accomNum = #{accomNum}")
    public int amountReivew(int accommNum);


 /*   @Select(" SELECT revContent, hiredate, satisfy, u.id " +
            " FROM review r " +
            " JOIN user u ON r.id = u.id " +
            " JOIN accom a ON r.accomNum = a.accomNum " +
            " WHERE r.accomNum = 1 " +
            " AND (revContent LIKE CONCAT('%', :${search}, '%') OR :${search} = '') " +
            " ORDER BY satisfy DESC " +
            " LIMIT 4 OFFSET 0")*/
}
