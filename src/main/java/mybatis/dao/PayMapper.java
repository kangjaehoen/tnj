package mybatis.dao;

import com.example.tnj.domain.PayVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PayMapper {

    //결제확인
    @Select("select id, payDate, name from pay where accomNum in (select accomNum from accom where id =#{id}) order by payDate desc")
    public List<PayVO> paycheck(String id);

}
