package cn.com.imaginary.ms.apigateway.model;

import cn.com.imaginary.ms.apigateway.common.constants.CommonConstants;
import lombok.Data;

/**
 * 返回实体
 *
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/8/10 21:53
 */
@Data
public class ReturnData<T> {
    private int code = 0;
    private String msg = "success";

    private T data;


    public static <T> ReturnData<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, "success");
    }

    public static <T> ReturnData<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, "success");
    }

    public static <T> ReturnData<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> ReturnData<T> failed() {
        return restResult(null, CommonConstants.FAIL, "error");
    }

    public static <T> ReturnData<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> ReturnData<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, "error");
    }

    public static <T> ReturnData<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }


    private static <T> ReturnData<T> restResult(T data, int code, String msg) {
        ReturnData<T> apiResult = new ReturnData<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
