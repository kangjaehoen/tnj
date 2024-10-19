package mybatis.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishListMapper {
    @Delete("delete from wishlist where accomNum #={accomNum}")
    public int deleteByAccomNum(int accomNum);
}
