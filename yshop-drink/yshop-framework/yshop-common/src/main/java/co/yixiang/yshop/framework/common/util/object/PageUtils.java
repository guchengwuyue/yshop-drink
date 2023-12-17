package co.yixiang.yshop.framework.common.util.object;

import co.yixiang.yshop.framework.common.pojo.PageParam;

/**
 * {@link co.yixiang.yshop.framework.common.pojo.PageParam} 工具类
 *
 * @author yshop
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
