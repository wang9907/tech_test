package com.summer.tech.springboot.enums;

public enum ResultEnum {

	UNKONW_ERROR(1001, "系统错误"), SUCCESS(0, "成功"), SAMLL_ERROR(3002, "年龄太小了"), BIG_ERROR(
			3004, "年龄太大了");

	private Integer code;

	private String message;

	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
