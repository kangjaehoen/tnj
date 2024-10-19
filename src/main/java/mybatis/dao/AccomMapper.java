package mybatis.dao;

import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.PayVO;

import java.util.List;

@Mapper
public interface AccomMapper {
    @Select("select * from accom where id = #{id}")
    public List<AccVO> listmine(String id);
    //리스트불러오기:조건
    @Select("select * from accom where id =#{id} and (accName like concat('%', #{key}, '%') or address like concat('%', #{key}, '%') or detailAddress like concat('%', #{key}, '%') or category like concat('%', #{key}, '%') or accType like concat('%', #{key}, '%') )")
    public List<AccVO> searchmine(String id, String key);
    //등록
    @Insert("insert into accom (accomNum, id, accName, postcode, address, detailAddress, price, accCall, adultPrice, kidPrice, dayoff, category, accType, onSale, accomRule, informtext, occ, maxocc,chkin_Time,chkout_Time,room,bed,bathroom) values (#{accomNum}, #{id}, #{accName}, #{postcode}, #{address}, #{detailAddress}, #{price}, #{accCall}, #{adultPrice}, #{kidPrice}, #{dayoff}, #{category}, #{accType}, #{onSale}, #{accomRule}, #{informtext}, #{occ}, #{maxocc},#{chkin_Time},#{chkout_Time},#{room},#{bed},#{bathroom} )")
    @Options(useGeneratedKeys = true, keyProperty = "accomNum")
    public boolean accinsert(AccVO ac);
    //수정
    @Update("update accom set accName =#{ac.accName}, id =#{ac.id}, postcode =#{ac.postcode}, address =#{ac.address}, detailAddress =#{ac.detailAddress}, accCall =#{ac.accCall}, price =#{ac.price}, adultPrice =#{ac.adultPrice}, kidPrice =#{ac.kidPrice}, occ =#{ac.occ}, maxocc =#{ac.maxocc}, dayoff =#{ac.dayoff}, category =#{ac.category}, accType =#{ac.accType}, onSale =#{ac.onSale}, accomRule =#{ac.accomRule}, informtext =#{ac.informtext}, chkin_Time =#{ac.chkin_Time}, chkout_Time =#{ac.chkout_Time}, room =#{ac.room}, bed =#{ac.bed}, bathroom =#{ac.bathroom} where accomNum =#{loadedAccomNum}")
    public int accupdate(@Param("ac") AccVO ac, @Param("loadedAccomNum") int loadedAccomNum);
    //삭제
    @Delete("delete from accom where id=#{id} and accomNum =#{accomNum}")
    public int accdelete(String id, int accomNum);
    //결제확인
    @Select("select * from pay where accomNum in (select accomNum from accom where id = #{id})")
    public List<PayVO>reservationcheck(String id);
    //불러오기
    @Select("select * from accom where id =#{id} and accomNum=#{accomNum}")
    public AccVO oneacc(String id, int accomNum);
    //판매상태확인
    @Select("select onSale from accom where accomNum =#{accomNum}")
    public int isOnSale(int accomNum);
    //판매변경
    @Update("update accom set onSale =#{onSale} where accomNum =#{accomNum}")
    public int saleupdate(@Param("accomNum") int accomNum,@Param("onSale") int onSale);

    @Select(" SELECT a.accomNum, a.id, a.accName, a.postcode, a.address, " +
            "        a.detailAddress, a.accCall, a.adultPrice, a.kidPrice, " +
            "        a.dayoff, a.category, a.accType, a.onSale , " +
            "        a.accomRule, a.occ, a.maxOcc, FORMAT(a.price,0) as price, " +
            "        a.informtext, a.chkin_Time, a.chkout_Time, a.room, " +
            "        a.bed, a.bathroom, round(avg(r.satisfy),1) as satisAvg " +
            " FROM accom a " +
            " LEFT JOIN review r " +
            " ON  a.accomNum = r.accomNum" +
            " WHERE category = #{category} " +
            " GROUP BY a.accomNum ")
    public List<CategoryVO> categoryAccomList(String category);


    @Select(" SELECT a.accomNum, a.id, a.accName, a.postcode, a.address, " +
            "        a.detailAddress, a.accCall, a.adultPrice, a.kidPrice, " +
            "        a.dayoff, a.category, a.accType,  a.onSale , " +
            "        a.accomRule, a.occ, a.maxOcc, FORMAT(a.price,0) as price, " +
            "        a.informtext, a.chkin_Time, a.chkout_Time, a.room, " +
            "        a.bed, a.bathroom, round(avg(r.satisfy),1) as satisAvg " +
            " FROM accom a " +
            " LEFT JOIN review r " +
            " ON  a.accomNum = r.accomNum" +
            " WHERE a.accType = #{accType} " +
            " GROUP BY a.accomNum ")
    public List<CategoryVO> accTypeAccomList(String accType);

    @Select(" SELECT a.accomNum, a.id, a.accName, a.postcode, a.address, " +
            "        a.detailAddress, a.accCall, a.adultPrice, a.kidPrice, " +
            "        a.dayoff, a.category, a.accType, a.onSale , " +
            "        a.accomRule, a.occ, a.maxOcc, FORMAT(a.price,0) as price, " +
            "        a.informtext, a.chkin_Time, a.chkout_Time, a.room, " +
            "        a.bed, a.bathroom, round(avg(r.satisfy),1) as satisAvg " +
            " FROM accom a " +
            " LEFT JOIN review r " +
            " ON a.accomNum = r.accomNum " +
            " WHERE (a.accName LIKE CONCAT('%', #{search}, '%') " +
            " OR a.address LIKE CONCAT('%', #{search}, '%') " +
            " OR a.accType LIKE CONCAT('%', #{search}, '%') " +
            " OR a.category LIKE CONCAT('%', #{search}, '%')) " +
            " GROUP BY a.accomNum ")
    public List<CategoryVO> searchAccomList(String search);

}


