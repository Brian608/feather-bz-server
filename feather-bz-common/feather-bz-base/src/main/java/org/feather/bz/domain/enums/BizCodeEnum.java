package org.feather.bz.domain.enums;

import lombok.Getter;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.enums
 * @className: BizCodeEnum
 * @author: feather
 * @description:
 * @since: 2025-04-23 21:30
 * @version: 1.0
 */
@Getter
public enum BizCodeEnum {
    /**
     * 通用操作码
     */
    OPS_REPEAT(110001,"重复操作"),
    CLIENT_REQUEST_BODY_CHECK_ERROR(110002, "客户端请求体参数校验不通过"),
    /**
     * 客户端@PathVariable参数错误
     * 一般是类型不匹配，比如本来是Long类型，客户端却给了一个无法转换成Long字符串
     * org.springframework.validation.BindException
     */
    CLIENT_PATH_VARIABLE_ERROR(110003, "客户端URL中的参数类型错误"),

    /**
     * 客户端@RequestParam参数校验不通过
     * 主要是未能通过Hibernate Validator校验的异常处理
     * javax.validation.ConstraintViolationException
     */
    CLIENT_REQUEST_PARAM_CHECK_ERROR(110004, "客户端请求参数校验不通过"),

    /**
     * 客户端@RequestParam参数必填
     * 入参中的@RequestParam注解设置了必填，但是客户端没有给值
     * javax.validation.ConstraintViolationException
     */
    CLIENT_REQUEST_PARAM_REQUIRED_ERROR(110005, "客户端请求缺少必填的参数"),

    /**
     * 通用的业务方法入参检查错误
     * java.lang.IllegalArgumentException
     */
    SERVER_ILLEGAL_ARGUMENT_ERROR(110006, "业务方法参数检查不通过"),

    /**
     * 客户端HTTP请求方法错误
     * org.springframework.web.HttpRequestMethodNotSupportedException
     */
    CLIENT_HTTP_METHOD_ERROR(110007, "客户端HTTP请求方法错误"),

    /**
     * 客户端@RequestBody请求体JSON格式错误或字段类型错误
     * org.springframework.http.converter.HttpMessageNotReadableException
     * <p>
     * eg:
     * 1、参数类型不对:{"test":"abc"}，本身类型是Long
     * 2、{"test":}  test属性没有给值
     */
    CLIENT_REQUEST_BODY_FORMAT_ERROR(110008, "客户端请求体JSON格式错误或字段类型不匹配"),







    USER_NAME_EXIST(200001, "用户名已经存在"),
    SEND_NOTICE_DUPLICATED(200002, "不允许重复发送通知"),
    ACCOUNT_UNREGISTER(200003,"账号不存在"),
    VERIFICATION_CODE_WRONG(200004, "验证码错误"),
    ;




    private final String message;

    private final int code;

    BizCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}

