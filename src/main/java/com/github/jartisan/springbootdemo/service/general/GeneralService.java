package com.github.jartisan.springbootdemo.service.general;

import java.util.List;

import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.dao.general.qo.TermQuery;
import com.github.pagehelper.PageInfo;

/***
 * 基础数据服务类
 * @author wjl
 */
public interface GeneralService {
	/***
     * 查询可用学期数据
     * @return
     */
    List<Term> selectTermByUsable() throws BaseException;
    
    /***
     * 分页查询可用学期数据
     * @return
     */
    PageInfo<Term> selectTermByUsableByPage(TermQuery termQuery) throws BaseException;
    
    
    /***
     * 根据code查询学期
     * @param code
     * @return
     * @throws BaseException
     */
    Term selectTermByCode(Integer code) throws BaseException;
}
