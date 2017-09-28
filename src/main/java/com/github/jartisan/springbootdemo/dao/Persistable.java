package com.github.jartisan.springbootdemo.dao;

import java.io.Serializable;

/***
 * 持久化
 * @author wjl
 * @param <ID>
 */
public interface Persistable <ID extends Serializable> extends Serializable{
	
	/***
	 * getId
	 * @return
	 */
	ID getId();
}
