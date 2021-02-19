package com.lizhuopeng.entities;

/**
 * 返回码
 * 0 -> 成功,401 -> 没有权限,404 -> 查询的对象不存在,408 -> 请求超时,500 -> 未知错误
 * @author pandora gen
 */
public enum RespCode {

    SUCCESS(0,"成功"),
    /** 成功 */
    Auth_FAIL(11,"验证不通过"),
    /** 没有权限 */
    UNAUTHORIZED(401,"没有权限"),
    /** 查询的对象不存在 */
    NOTFOUND(404,"查询的对象不存在"),
    /** 请求超时 */
    TIMEOUT(408,"请求超时"),
    /** 请求来自未经授权的IP地址 **/
    UNAUTHORIZED_IP(443,"请求来自未经授权的IP地址"),
    /** 参数错误 **/
    INVALID_PARAM(444,"参数错误"),
    /** 无效签名 **/
    INCORRECT_SIGN(445,"无效签名"),
    /** 未知错误 **/
    UNKNOWN_ERROR(500,"未知错误"),
    /** 服务暂不可用 **/
    UNAVAILABLE(504,"服务暂不可用"),
    /** 接口调用次数已达到设定的上限	 **/
    LIMIT_REACHED(505,"接口调用次数已达到设定的上限"),
    /** 未知错误 */
    ERROR(500,"未知错误");

    /**
     *   201~250	业务自定义返回码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String message;

    RespCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
