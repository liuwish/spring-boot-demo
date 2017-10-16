package com.github.jartisan.springbootdemo.dao.general.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.dao.general.qo.TermQuery;
/**
 * @author: wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@Mapper
public interface TermMapperCustom {
	 /***
     * 根据删除状态查询所有学期
     * @param deleted
     * @return
     */
    List<Term> selectByDeleted(int deleted);
    
    /***
     * 根据查询条件查询学期
     * @param query
     * @return
     */
    List<Term> selectByCondition(@Param("query") TermQuery query);
    
    /***
     * 根据code查询学期
     * @param code
     * @return
     */
    Term selectByCode(Integer code); 
}
