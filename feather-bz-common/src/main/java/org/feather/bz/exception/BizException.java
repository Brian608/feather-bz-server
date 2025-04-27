package org.feather.bz.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.bz.domain.constant.CommonConstant;
import org.feather.bz.domain.enums.BizCodeEnum;

import java.text.MessageFormat;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.exception
 * @className: BizException
 * @author: feather
 * @description:
 * @since: 2025-04-23 21:29
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends  RuntimeException{


    private int code;
    private String msg;

    public BizException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }

    public BizException(int code, String msg, Object... arguments) {
        super(MessageFormat.format(msg, arguments));
        this.code = code;
        this.msg = MessageFormat.format(msg, arguments);
    }

    public BizException(String msg){
        this.code = CommonConstant.DEFAULT_ERROR_CODE;
        this.msg = msg;
    }



}

