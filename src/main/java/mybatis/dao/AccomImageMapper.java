package mybatis.dao;

import com.example.tnj.domain.AccomImageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccomImageMapper {
    //저장하기
    @Insert("insert into accomimage (id, filePath, accomNum) values (#{id}, #{filePath}, #{accomNum})")
    public int insertimg(AccomImageVO ai);
    //불러오기
    @Select("select filePath from accomimage where accomNum=#{accomNum}")
    public List<String>filePathByAccomNum(int accomNum);
    //삭제하기
    @Delete("delete from accomimage where accomNum =#{accomNum}")
    public int deleteimg(int accomNum);
}
