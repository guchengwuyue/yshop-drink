package co.yixiang.app.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @ClassName 异常工具
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/6/27
 **/
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
