package com.github.jartisan.springbootdemo.model.authorization;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Token 的 Model 类，
 * 可以增加字段提高安全性
 * 例如时间戳、url 签名
 * @author wjl
 * @date 2017/08/11
 */
public class TokenModel {
	
	/***
	 * userId
	 */
    private long userId;
    
    /***
     * uuid
     */
    private String token;
    
    public TokenModel (long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
    public long getUserId () {
        return userId;
    }
    public void setUserId (long userId) {
        this.userId = userId;
    }
    public String getToken () {
        return token;
    }
    public void setToken (String token) {
        this.token = token;
    }
    
    @Override
   	public String toString() {
   		return ToStringBuilder.reflectionToString(this);
   	}
}
