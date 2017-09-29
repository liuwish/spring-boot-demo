package com.github.jartisan.springbootdemo.service.general.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.springbootdemo.dao.dbconf.ReadOnlyConnection;
import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.dao.general.mapper.TermMapperCustom;
import com.github.jartisan.springbootdemo.dao.general.qo.TermQuery;
import com.github.jartisan.springbootdemo.service.general.GeneralService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	private TermMapperCustom termMapperCustom;
	


	@Override
	@Cacheable(value="general", key="'terms.usable'") 
	public List<Term> selectTermByUsable() throws BaseException {
		return termMapperCustom.selectByDeleted(0);
	}

	
	@Override
	public PageInfo<Term> selectTermByUsableByPage(TermQuery termQuery) throws BaseException {
		PageHelper.startPage(termQuery.getPage(), termQuery.getSize()); 
		List<Term> terms = termMapperCustom.selectByDeleted(0);
		PageInfo<Term> pageInfo = new PageInfo<Term>(terms);
		return pageInfo;
	}
	 
	

	@Override
	//@Cacheable(value="general", key="'term.code['+#code+']'") 
	@ReadOnlyConnection 
	public Term selectTermByCode(Integer code) throws BaseException {
		return termMapperCustom.selectByCode(code);
	}



	
	
}
