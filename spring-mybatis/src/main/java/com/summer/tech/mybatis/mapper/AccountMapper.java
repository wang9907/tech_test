package com.summer.tech.mybatis.mapper;

import com.summer.tech.mybatis.entity.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    @Select("select * from account where user_id = #{id}")
    List<Account> findAccountByUid(Integer id);

}