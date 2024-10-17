package mybatis.dao;

import com.example.tnj.domain.WishListDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface WishListMapper {

    @Insert(" INSERT INTO wishList " +
            " (id,accomNum,checkStatus) " +
            " VALUES (#{id}, #{accomNum}, #{checkStatus})")
    public boolean wishListClick(WishListDTO dto);

    @Select(" SELECT id, accomNum, checkStatus " +
            " FROM wishList " +
            " WHERE checkStatus = 1")
    public List<WishListDTO> checkWish();

    @Select(" SELECT checkStatus " +
            " FROM wishList " +
            " WHERE accomNum = #{accomNum}")
    public WishListDTO checkWishAccomNum(int accomNum);

    @Delete(" DELETE FROM wishList" +
            " WHERE accomNum = #{accomNum}")
    public boolean deleteWishAccomNum(int accomNum);
}
