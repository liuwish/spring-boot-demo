package com.github.jartisan.springbootdemo.service.authorization;

import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.springbootdemo.model.authorization.TokenModel;

/**
 * token管理
 * @author wjl
 * @date 2017/08/11
 */
public interface TokenManager {
	 /**
     * 创建一个 token 关联上指定用户
     * @param userId 指定用户的 id
     * @return 生成的 token
     */
    public TokenModel createToken (long userId) throws BaseException;
    /**
     * 检查 token 是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken (TokenModel model) throws BaseException;
    /**
     * 从字符串中解析 token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken (String authentication) throws BaseException;
    /**
     * 清除 token
     * @param userId 登录用户的 id
     */
    public void deleteToken (long userId) throws BaseException;
}
