package com.github.jartisan.springbootdemo.dao.general.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.jartisan.springbootdemo.dao.general.entity.Term;

/**
 * @author: wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@Mapper
public interface TermMapper {
	/**
	 * 根据主键删除记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
    /***
     * 插入记录
     * @param record
     * @return
     */
    int insert(Term record);
    /***
     * 插入记录
     * @param record
     * @return
     */
    int insertSelective(Term record);
    /***
     * 根据主键查询记录
     * @param id
     * @return
     */
    Term selectByPrimaryKey(Integer id);
    /***
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Term record);
    /***
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Term record);
}