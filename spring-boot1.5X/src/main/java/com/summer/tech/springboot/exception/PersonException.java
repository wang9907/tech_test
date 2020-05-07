package com.summer.tech.springboot.exception;

import com.summer.tech.springboot.enums.ResultEnum;

public class PersonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errorCode;

	public PersonException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.errorCode = resultEnum.getCode();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
