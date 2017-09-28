package com.github.jartisan.springbootdemo.dao.general;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.jartisan.springbootdemo.dao.general.entity.Term;
import com.github.jartisan.springbootdemo.dao.general.mapper.TermMapper;
import com.github.jartisan.springbootdemo.dao.general.mapper.TermMapperCustom;
import com.github.jartisan.springbootdemo.dao.general.qo.TermQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 * @author ejb3 2015年2月4日 下午12:27:47
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest
public class TermTest {
	private static Logger log = LoggerFactory.getLogger(TermTest.class);
	@Autowired
	private TermMapper termMapper;
	
	@Autowired
	private TermMapperCustom termMapperCustom;

	@Test
	public void testTermInsert() {
		//for(int i=1;i<=1;i++){
			Term term = new Term();
			term.setCreateUserName("system");
			term.setIsDelete(0);
			term.setCode(99);
			term.setName("春1");
			term.setSeq(9);
			log.info(termMapper.insert(term)+"");
			log.info(term.toString());
		//}
		
	}
	
	@Test
	public void testSelectByDeleted() {
		PageHelper.startPage(1, 10);
		//PageHelper.orderBy方法,mapping 中不可使用orderBy语句。
		//PageHelper.orderBy("seq asc");
		List<Term> terms  = termMapperCustom.selectByDeleted(0);
		PageInfo<Term> page = new PageInfo<Term>(terms);
		log.info(page.toString());
		
		for(Term term:terms){
			log.info(term.toString());
		}
		
	}
	
	
	@Test
	public void testSelectByCode() {
		Term term  = termMapperCustom.selectByCode(10);
		log.info(term.toString());
	}
	
	@Test
	public void testSelectByCondition() {
		TermQuery query = new TermQuery();
		//query.setName("春");
		query.setIsDelete(0);
		query.putOrderBy("name", "DESC");
		query.putOrderBy("seq", "DESC");
		List<Term> terms  = termMapperCustom.selectByCondition(query);
		log.info(terms.toString());
	}

}
