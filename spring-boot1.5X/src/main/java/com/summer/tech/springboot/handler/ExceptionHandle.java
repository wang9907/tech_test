package com.summer.tech.springboot.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.summer.tech.springboot.jpa.entity.Result;
import com.summer.tech.springboot.exception.PersonException;
import com.summer.tech.springboot.utils.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {

	private final Logger logger = LoggerFactory
			.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handler(Exception e) {
		if (e instanceof PersonException) {
			PersonException pe = (PersonException) e;
			return ResultUtil.error(pe.getErrorCode(), e.getMessage());
		} else {
			logger.error("【系统异常】{}", e);
			return ResultUtil.error(1000, "系统错误");
		}
	}
}
