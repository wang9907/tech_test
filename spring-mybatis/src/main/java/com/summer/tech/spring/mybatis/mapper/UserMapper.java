package com.summer.tech.spring.mybatis.mapper;

import com.summer.tech.spring.mybatis.entity.Teacher;
import com.summer.tech.spring.mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * 针对crud有四个注解
 * @Select, @Insert， @Update, @Delete
 */
//@CacheNamespace(blocking = true)
public interface UserMapper {

    @Select("select * from user")
    @Results(id="baseResult",value = {@Result(id=true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "address",column = "address"),
            @Result(property = "birthday",column = "birthday"),
            //一对一
            //@Result(property = "account",column = "id",one = @One(select = "com.summer.tech.spring.mybatis.mapper.AccountMapper.selectByPrimaryKey",fetchType = FetchType.EAGER))
            // 一对多
            @Result(property = "accounts" ,column = "id",many = @Many(select = "com.summer.tech.spring.mybatis.mapper.AccountMapper.findAccountByUid"))
            })
    List<User> findAll();

    @Insert("insert into user(name,gender,address,birthday)values(#{name},#{gender},#{address},#{birthday})")
    void saveUser(User user);

    @Update("update user set name=#{name},gender=#{gener},address=#{address},birthday=#{birthday} where id=#{id} ")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void  deleteUser(User user);

    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

   // @Select("select * from user where name like #{name}")
   @Select("select * from user where name like '%${name}%'")
    List<User> findUserByName(String name);

   @Select("select count(*) from user")
   Integer findTotal();

    @SelectProvider(type = UserDynamicSqlProvider.class, method = "selectWhitParamSql")
    List<User> selectWhitParamSql(Map<String, Object> param);

    void insert(User user);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}