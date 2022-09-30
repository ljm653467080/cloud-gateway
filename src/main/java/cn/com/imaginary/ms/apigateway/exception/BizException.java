/**
 * Copyright (c) 2018 开源 All rights reserved.
 *
 * https://www.tslsmart.com
 *
 * 版权所有，侵权必究！
 */

package cn.com.imaginary.ms.apigateway.exception;

import cn.com.imaginary.ms.apigateway.util.MessageUtils;

/**
 * 自定义异常
 *
 * @author Mark sunlightcs@gmail.com
 */
public class BizException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public BizException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public BizException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public BizException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public BizException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public BizException(String msg) {
		super(msg);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public BizException(String msg, Throwable e) {
		super(msg, e);
		this.code = ErrorCode.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}