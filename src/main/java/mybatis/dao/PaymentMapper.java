package mybatis.dao;

import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.PayVO;
import com.example.tnj.domain.PaymentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {
    //예약테이블에 생성된 resNum 가져오기
    @Select("select resNum from reservation where accomNum=#{accomNum} ORDER BY resNum DESC LIMIT 1")
    public int selectresNum(@Param("accomNum") int accomNum);

    //결제테이블 insert
    @Insert("insert into pay( resNum, id, payDate, pay_Status, accomNum, amount, impUid, merchantUid, name, apply_num)  values( #{resNum},#{id}, now(), 'Y', #{accomNum}, #{amount}, #{impUid}, #{merchantUid}, #{name}, #{apply_num})")
    public void insertPay(PaymentDTO pDTO);

    //결제내역
    @Select("select * from pay where accomNum= #{accomNum}")
    public PayVO payInfo(@Param("accomNum") int accomNum);

    //결제취소
    @Update("update pay set pay_Status='N' where impUid=#{impUid}")
    public int cancelPay(@Param("impUid") String impUid);

    //resNum에 해당하는 모든 결제정보
    @Select("select * from pay where resNum=#{resNum}")
    public List<PaymentDTO> listAll(@Param("resNum") int resNum);

    //세션에 따른 리스트출력
    @Select("select DATE_FORMAT(payDate,'%Y') as year, DATE_FORMAT(payDate,'%M') as month ,impUid,name,amount,pay_Status from pay where id=#{id} and DATE_FORMAT(payDate,'%Y')=#{year} and DATE_FORMAT(payDate,'%M')=#{month}")
    public List<PaymentDTO> payList(@Param("id") String id, String year, String month);

    //결제상태에 따른 조회
    @Select("select * from pay where pay_Status=#{pay_Status}")
    public List<PaymentDTO> statusByList(@Param("pay_Status") String payStatus,@Param("resNum") int resNum);


}
