package mybatis.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface Testdata {
    @Insert("insert into pay (resNum, id, payDate, pay_Status, accomNum, amount, name) values(${resNum}, ${id}, ${payDate}, 'Y', ${accomNum}, ${amount}, ${name} )")
    public int insertpaydata(int resNum, String id, LocalDate payDate, int accomNum, int amount, String name);
}
