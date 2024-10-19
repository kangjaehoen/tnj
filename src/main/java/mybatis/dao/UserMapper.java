package mybatis.dao;

import com.example.tnj.domain.UserDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("Insert into User (id, pw, name, gender, birth, email, accountNum) values (#{id}, #{pw}, #{name}, #{gender}, #{birth}, #{email}, #{accountNum})")
    int registerUser(UserDTO dto);

    @Select("SELECT * FROM USER where id = #{id} ANd pw = #{pw}")
    UserDTO loginUser(@Param("id") String id, @Param("pw") String pw);

    @Select("SELECT id FROM User WHERE name = #{name} AND email = #{email}")
    String findId(@Param("name") String name, @Param("email") String email);

    @Select("SELECT COUNT(*) FROM User WHERE id = #{id}")
    int checkId(@Param("id") String id);

    @Select("SELECT email FROM User WHERE email = #{email}")
    String checkEmail(@Param("email") String email);

    @Select("SELECT pw FROM User WHERE id = #{id}")
    String getPasswordById(@Param("id") String id);

    @Select("SELECT pw FROM User WHERE id = #{id} AND email = #{email}")
    String findPassword(@Param("id") String id, @Param("email") String email);

    @Update("UPDATE User SET pw = #{pw} WHERE id = #{id}")
    int updatePassword(@Param("id") String id, @Param("pw") String pw);
}

