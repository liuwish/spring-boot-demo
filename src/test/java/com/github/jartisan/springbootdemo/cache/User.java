package com.github.jartisan.springbootdemo.cache;
/***
 * 
 * @author wjl
 * @date: 2016年2月26日 上午11:39:24
 */
public class User implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	
	
	public User() {
	}
	
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
