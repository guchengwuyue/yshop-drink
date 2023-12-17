package co.yixiang.yshop.framework.common.util.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Set 工具类
 *
 * @author yshop
 */
public class SetUtils {

    @SafeVarargs
    public static <T> Set<T> asSet(T... objs) {
        return new HashSet<>(Arrays.asList(objs));
    }

}
