package com.ruoyi.app.common.exception;

import com.itmuch.lightsecurity.exception.LightSecurityException;
import com.ruoyi.app.common.R;
import com.ruoyi.app.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName 全局异常处理
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
@Slf4j
@ControllerAdvice
public class LightSecurityExceptionHandler {
    /**
     * Light Security相关异常
     *
     * @param exception 异常
     * @return 发生异常时的返回
     */
    @ExceptionHandler(value = {LightSecurityException.class})
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public R error(HttpServletRequest request, LightSecurityException exception, HttpServletResponse response) {
        //System.out.println(request.getMethod());
        //System.out.println(exception.getMessage());
        String str1 = "com.itmuch.lightsecurity.exception.LightSecurityException: 没有找到名为Authorization的header";
        String str2 = "com.itmuch.lightsecurity.exception.LightSecurityException: Token invalided.";
        if (exception.getMessage().equals(str1) || exception.getMessage().equals(str2)) {
            response.setStatus(401);
            return R.error(401, "授权失败");
        }
        return R.error(4000, exception.getMessage());

    }



    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public R allError(Exception exception) {
        return R.error(4000,exception.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1]+":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return R.error(4001,msg.toString());
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public R badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(4002,e.getMessage());
    }


    /**
     * 处理 EntityExist
     * @param e
     * @return
     */
    @ExceptionHandler(value = EntityExistException.class)
    public R entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return R.error(4003,e.getMessage());
    }
}
