package com.github.jartisan.springbootdemo.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.jartisan.parent.base.annotation.Security;
import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.parent.base.response.RestResult;
import com.github.jartisan.springbootdemo.configuration.SlaveDataSource;
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
	@Autowired
	private RestTemplate restTemplate;
	
	/***
	 * 查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/terms",method = RequestMethod.GET)
	@SlaveDataSource
	public RestResult<List<Term>> terms() throws BaseException {
		List<Term> terms = generalService.selectTermByUsable();
		return RestResult.ok(terms);
	}
	
	/***
	 * 测试 restTemplate
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/terms1",method = RequestMethod.GET)
	@SlaveDataSource
	public RestResult<List<Term>> terms1() throws BaseException {
		URI url=null;
		try {
			url = new URI("http://localhost:8080/jartisan/general/term/v1/terms");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ParameterizedTypeReference<RestResult<List<Term>>> pt = new ParameterizedTypeReference<RestResult<List<Term>>>(){};   
		RestResult<List<Term>> result =restTemplate.exchange(new RequestEntity<>(HttpMethod.GET, url), pt).getBody();
		//restTemplate.getForObject("http://ifeve.com/google-guava/",String.class);
		return result;
	}
	
	
	/***
	 * 分页查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/page/terms",method = RequestMethod.GET)
	@SlaveDataSource @Security
	public RestResult<PageInfo<Term>> termsByPage(@Valid TermQuery termQuery) throws BaseException {
		PageInfo<Term> terms = generalService.selectTermByUsableByPage(termQuery);
		return RestResult.ok(terms);
	}
	
	
	/***
	 * 分页查询可用的学期数据
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/v1/page/query",method = RequestMethod.POST)
	@SlaveDataSource
	public RestResult<PageInfo<Term>> termsByQuery(@RequestBody @Valid TermQuery termQuery) throws BaseException {
		PageInfo<Term> terms = generalService.selectTermByUsableByPage(termQuery);
		return RestResult.ok(terms);
	}
}
