package com.krw.spring.repo;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.krw.spring.service.domain.User;

@Mapper
public interface UserMapper {

	// @formatter:off
	@Insert("insert into users ("
			+ "email, "
			+ "username, "
			+ "nickname, "
			+ "password, "
			+ "authority "
			+ ") values ("
			+ "#{email},"
			+ "#{username},"
			+ "#{nickname},"
			+ "#{password},"
			+ "#{authority}"
			+ ")")
	// @formatter:on
	int insert(User user);
	
	// @formatter:off
	@Results(id = "User", value = {
		@Result(property = "userSeq", column = "userSeq"),
		@Result(property = "uuid", column = "uuid"),
		@Result(property = "email", column = "email"),
		@Result(property = "username", column = "username"),
		@Result(property = "password", column = "password"),
		@Result(property = "authority", column = "authority"),
		@Result(property = "nickname", column = "nickname"),
		@Result(property = "created", column = "created"),
		@Result(property = "enabled", column = "enabled"),
		@Result(property = "accountNonExpired", column = "accountNonExpired"),
		@Result(property = "accountNonLocked", column = "accountNonLocked"),
		@Result(property = "credentialsNonExpired", column = "credentialsNonExpired")
    })
	// @formatter:on
	@Select("select * from users where email = #{email}")
	User selectByEmail(User user);

	@ResultMap("User")
	@Select("select * from users where uuid = #{uuid}")
	User selectByUuid(@Param("uuid") UUID uuid);
	
	@ResultMap("User")
	@Select("select * from users")
	List<User> selectAll();
	
}
