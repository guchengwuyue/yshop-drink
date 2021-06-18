package co.yixiang.app.common.api;


public enum ApiCode {

    SUCCESS(200, "操作成功"),

    UNAUTHORIZED(401, "非法访问"),

    NOT_PERMISSION(403, "没有权限"),

    NOT_FOUND(404, "你请求的路径不存在"),

    FAIL(500, "操作失败"),

    SYSTEM_EXCEPTION(5000,"系统异常!"),

    PARAMETER_EXCEPTION(5001,"请求参数校验异常"),

    PARAMETER_PARSE_EXCEPTION(5002,"请求参数解析异常"),

    HTTP_MEDIA_TYPE_EXCEPTION(5003,"HTTP Media 类型异常"),

    SYSTEM_LOGIN_EXCEPTION(5005,"系统登录异常")

    ;

    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
