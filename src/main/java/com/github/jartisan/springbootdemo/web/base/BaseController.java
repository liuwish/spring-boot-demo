package com.github.jartisan.springbootdemo.web.base;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.jartisan.parent.base.enums.GlobalCode;
import com.github.jartisan.parent.base.exception.BaseException;
import com.github.jartisan.parent.base.response.RestResult;
/***
 * @author wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@ControllerAdvice
public class BaseController {
	private static final Logger log = LogManager.getLogger(BaseController.class);
	

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RestResult baseExceptionHandler(BaseException ex) {
		RestResult result = new RestResult();
		result.setCode(ex.getErrCode());
		result.setMessage(ex.getErrMsg());
		log.error("发生系统错误:{}:{}", ex.getErrCode(),ex.getErrMsg(),ex);
		return result;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody RestResult handleAllException(Exception ex) {
		RestResult result = new RestResult();
		result.setCode(GlobalCode.ERROR.getCode());
		result.setMessage(GlobalCode.ERROR.getMsg());
		result.setData(ex.getMessage());
		log.error("发生系统错误:{}", ex.getMessage(),ex);
		return result;
	}
	
	@ExceptionHandler(BindException.class)  
	@ResponseStatus(HttpStatus.OK)  
    public @ResponseBody RestResult handleValidationException(BindException e) {  
   	log.warn("参数验证失败", e.getMessage());  
   	Set<String> errorCodes = new HashSet<>();
		for (ObjectError error :  e.getAllErrors()) {
			errorCodes.add(error.getDefaultMessage());
		}
       return new RestResult().failure(GlobalCode.ERROR_190002.getCode(), errorCodes.toString());  
    } 
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)  
	 @ResponseStatus(HttpStatus.OK)  
     public @ResponseBody RestResult handleValidationException(MethodArgumentNotValidException e) {  
		log.warn("参数验证失败", e.getMessage());  
    	Set<String> errorCodes = new HashSet<>();
    	for (ObjectError error :  e.getBindingResult().getAllErrors()) {
			errorCodes.add(error.getDefaultMessage());
		}
        return new RestResult().failure(GlobalCode.ERROR_190002.getCode(), errorCodes.toString());  
     }  
}
