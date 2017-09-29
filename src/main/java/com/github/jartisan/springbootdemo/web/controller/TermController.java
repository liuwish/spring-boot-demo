package com.github.jartisan.springbootdemo.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.parent.base.response.APIResult;
import com.github.jartisan.springbootdemo.configuration.ReadOnlyConnection;
import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.dao.general.qo.TermQuery;
import com.github.jartisan.springbootdemo.service.general.GeneralService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: TermController
 * @Description: 通用数据-学期
 * @author wjl
 * @date 2017年3月27日
 */
@RestController
@RequestMapping("/general/term")
public class TermController {
	private static final Logger logger = LogManager.getLogger(TermController.class);
	
	
	@Autowired
	private GeneralService generalService;
	
	/***
	 * 查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/terms",method = RequestMethod.GET)
	@ReadOnlyConnection
	public APIResult terms() throws BaseException {
		List<Term> terms = generalService.selectTermByUsable();
		return new APIResult().success(terms);
	}
	
	
	/***
	 * 分页查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/page/terms",method = RequestMethod.GET)
	@ReadOnlyConnection
	public APIResult termsByPage(@Valid TermQuery termQuery) throws BaseException {
		PageInfo<Term> terms = generalService.selectTermByUsableByPage(termQuery);
		return new APIResult().success(terms);
	}
	
	
	/***
	 * 分页查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/page/query",method = RequestMethod.POST)
	@ReadOnlyConnection
	public APIResult termsByQuery(@RequestBody @Valid TermQuery termQuery) throws BaseException {
		PageInfo<Term> terms = generalService.selectTermByUsableByPage(termQuery);
		return new APIResult().success(terms);
	}
}
