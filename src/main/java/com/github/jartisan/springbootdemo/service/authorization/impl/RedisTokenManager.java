package com.github.jartisan.springbootdemo.service.authorization.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.jartisan.parent.base.consts.RedisKey;
import com.github.jartisan.parent.base.enums.GlobalCode;
import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.springbootdemo.model.authorization.TokenModel;
import com.github.jartisan.springbootdemo.service.authorization.TokenManager;
/***
 * Redis Token 管理实现类
 * @author wjl
 */
@Service
public class RedisTokenManager implements TokenManager {

	private static final Logger logger = LogManager.getLogger(RedisTokenManager.class);

	/***
	 *@Value("#{server['user.token.expires']}")
	 *#用户token过期时间 单位分钟 
	 */
	private int userTokenExpires=30;
	

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	@Override
	public TokenModel createToken(long userId) throws BaseException{
		// 使用 uuid 作为源 token
		String token = UUID.randomUUID().toString().replace("-", "");
		TokenModel model = new TokenModel(userId, token);
		// 存储到 redis 并设置过期时间
		BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(RedisKey.USER_TOKEN_KEY+userId);
		ops.set(token, userTokenExpires, TimeUnit.MINUTES);
		return model;
	}

	@Override
	public boolean checkToken(TokenModel model) throws BaseException{
		if (model == null) {
			return false;
		}
		String token = stringRedisTemplate.boundValueOps(RedisKey.USER_TOKEN_KEY+model.getUserId()).get();
		if (token == null || !token.equals(model.getToken())) {
			return false;
		}
		// 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
		stringRedisTemplate.boundValueOps(RedisKey.USER_TOKEN_KEY+model.getUserId()).expire(userTokenExpires, TimeUnit.MINUTES);
		return true;
	}

	@Override
	public TokenModel getToken(String authentication) throws BaseException{
		if (authentication == null || authentication.length() == 0) {
			return null;
		}
		final int len = 2;
		String[] param = authentication.split("_");
		if (param.length != len) {
			return null;
		}
		// 使用 userId 和源 token 简单拼接成的 token，可以增加加密措施
		long userId = 0;
		String token = "";
		try {
			userId = Long.parseLong(param[0]);
			token = param[1];
		} catch (NumberFormatException e) {
			logger.error("[获取token失败!] token is  {}",authentication);
			throw new BaseException(GlobalCode.ERROR_190001.getCode(), GlobalCode.ERROR_190001.getMsg());
		}
		return new TokenModel(userId, token);
	}

	@Override
	public void deleteToken(long userId) throws BaseException{
		stringRedisTemplate.delete(RedisKey.USER_TOKEN_KEY+userId);
	}

}
