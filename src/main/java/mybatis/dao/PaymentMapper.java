package mybatis.dao;

import com.example.tnj.domain.PayVO;
import com.example.tnj.domain.PaymentDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PaymentMapper {
    //예약테이블에 생성된 resNum 가져오기
    @Select("select resNum from reservation where accomNum=#{accomNum} ORDER BY resNum DESC LIMIT 1")
    public int selectresNum(int accomNum);

    //결제테이블 insert
    @Insert("insert into pay( resNum, id, payDate, pay_Status, payment, accomNum, amount, impUid, merchantUid, productName)  values( #{resNum},#{id}, now(), 'Y', #{payment}, #{accomNum}, #{amount}, #{impUid}, #{merchantUid}, #{productName})")
    public void insertPay(PaymentDTO pDTO);

    //결제내역
    @Select("select * from pay where accomNum= #{accomNum}")
    public PayVO payInfo(@Param("accomNum") int accomNum);

    //결제취소
    @Update("update pay set pay_Status='N' where accomNum=#{accomNum}")
    public int cancelPay(@Param("accomNum") int accomNum);

}
