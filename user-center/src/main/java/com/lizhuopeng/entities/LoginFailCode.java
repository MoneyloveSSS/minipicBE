package com.lizhuopeng.entities;

public enum LoginFailCode {
    /** 验证不通过 */
    Auth_FAIL(11,"验证不通过"),
    /** 账号被锁定 */
    Locked(12,"账号被锁定"),
    /** 账号已过期*/
    expired(13,"账号已过期"),
    /** 账号已停用*/
    NonEnabled(14,"账号已停用"),
    /** 未知错误 */
    ERROR(500,"未知错误");


    private final int code;
    /**
     * 错误信息
     */
    private final String message;

    LoginFailCode(int code, String message){
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
