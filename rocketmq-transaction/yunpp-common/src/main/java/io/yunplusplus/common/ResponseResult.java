package io.yunplusplus.common;

/**
 * 响应结果包装
 *
 * @author zhaoyt
 */
public class ResponseResult<T> {

    /**
     * 成功描述
     */
    private static final String OK_MSG = "ok";
    /**
     * 成功代码
     */
    private static final Integer OK_CODE = 200;

    private static final String SUCCESS = "1";

    private static final String ERROR = "0";
    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 成功失败(1成功，2失败)
     */
    private String success;
    /**
     * 结果消息，正常时该字段为空或者为“ok”
     */
    private String message;
    /**
     * 查询单个数据时
     */
    private T body;

    /**
     * 构造函数
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param code    结果代码
     * @param message 结果描述
     */
    private ResponseResult(String success, Integer code, String message) {
        super();
        this.success = success;
        this.message = message;
        this.code = code;
    }

    /**
     * 返回成功
     *
     * @param <T> 返回对象
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<T>(SUCCESS, OK_CODE, OK_MSG);
    }

    /**
     * 返回成功携带数据
     *
     * @param body 数据
     * @param <T>  返回对象
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> ok(T body) {
        ResponseResult<T> responseResult = new ResponseResult<T>(SUCCESS, OK_CODE, OK_MSG);
        responseResult.body = body;
        return responseResult;
    }


    /**
     * 返回错误携带数据
     *
     * @param code    错误代码
     * @param message 错误提示
     * @param <T>     返回对象
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(Integer code, String message) {
        return new ResponseResult<T>(ERROR, code, message);
    }

    /**
     * 返回错误携带数据
     *
     * @param code    错误代码
     * @param message 错误提示
     * @param result  数据
     * @param <T>     返回对象
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(Integer code, String message, T result) {
        ResponseResult<T> responseResult = new ResponseResult<T>(ERROR, code, message);
        responseResult.setBody(result);
        return responseResult;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", success='" + success + '\'' +
                ", body=" + body +
                '}';
    }
}
