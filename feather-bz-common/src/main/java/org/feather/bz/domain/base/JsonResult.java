package org.feather.bz.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.bz.domain.constant.CommonConstant;
import org.feather.bz.domain.enums.BizCodeEnum;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.base
 * @className: JsonResult
 * @author: feather
 * @description:
 * @since: 2025-04-23 21:27
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> implements   java.io.Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 状态码 0 表示成功
     */

    private Integer code;
    /**
     * 数据
     */
    private T data;
    /**
     * 描述
     */
    private String msg;


    /**
     * 成功，不传入数据
     * @return
     */
    public static <T> JsonResult<T> buildSuccess() {
        return new JsonResult<T>(CommonConstant.SUCCESS_CODE, null, CommonConstant.SUCCESS_MSG);
    }

    /**
     *  成功，传入数据
     * @param data
     * @return
     */
    public static <T> JsonResult<T> buildSuccess(T data) {
        return new JsonResult<T>(CommonConstant.SUCCESS_CODE, data, CommonConstant.SUCCESS_MSG);
    }

    /**
     * 失败，传入描述信息
     * @param msg
     * @return
     */
    public static  <T> JsonResult<T> buildError(String msg) {
        return new JsonResult<T>(CommonConstant.DEFAULT_ERROR_CODE, null, msg);
    }
    /**
     * 失败，传入描述code 和错误信息
     * @param msg
     * @return
     */
    public static  <T> JsonResult<T> buildError(int code,String msg) {
        return new JsonResult<T>(code, null, msg);
    }

    public static  <T> JsonResult<T> buildError(BizCodeEnum bizCodeEnum) {
        return new JsonResult<T>(bizCodeEnum.getCode(), null, bizCodeEnum.getMessage());
    }



    /**
     * 自定义状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static <T> JsonResult<T> buildCodeAndMsg(int code, String msg) {
        return new JsonResult<T>(code, null, msg);
    }

    /**
     * 传入枚举，返回信息
     * @param codeEnum
     * @return
     */
    public static <T> JsonResult<T> buildResult(BizCodeEnum codeEnum){
        return JsonResult.buildCodeAndMsg(codeEnum.getCode(),codeEnum.getMessage());
    }
}
