package com.github.jartisan.springbootdemo.dao.general.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.jartisan.springbootdemo.dao.general.entity.Term;
@Mapper
public interface TermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Term record);

    int insertSelective(Term record);

    Term selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);
}