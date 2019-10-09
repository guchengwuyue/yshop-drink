package com.ruoyi.app.common.api;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import java.util.Collection;
import java.util.Map;


public class Assert {

    protected Assert() {
        // to do noting
    }

    /**
     * 大于O
     */
    public static void gtZero(Integer num, IErrorCode errorCode) {
        if (num == null || num <= 0) {
            Assert.fail(errorCode);
        }
    }

    /**
     * 大于等于O
     */
    public static void geZero(Integer num, IErrorCode errorCode) {
        if (num == null || num < 0) {
            Assert.fail(errorCode);
        }
    }

    /**
     * num1大于num2
     */
    public static void gt(Integer num1, Integer num2, IErrorCode errorCode) {
        if (num1 <= num2) {
            Assert.fail(errorCode);
        }
    }

    /**
     * num1大于等于num2
     */
    public static void ge(Integer num1, Integer num2, IErrorCode errorCode) {
        if (num1 < num2) {
            Assert.fail(errorCode);
        }
    }

    /**
     * obj1 eq obj2
     */
    public static void eq(Object obj1, Object obj2, IErrorCode errorCode) {
        if (!obj1.equals(obj2)) {
            Assert.fail(errorCode);
        }
    }

    public static void isTrue(boolean condition, IErrorCode errorCode) {
        if (!condition) {
            Assert.fail(errorCode);
        }
    }

    public static void isFalse(boolean condition, IErrorCode errorCode) {
        if (condition) {
            Assert.fail(errorCode);
        }
    }

    public static void isNull(IErrorCode errorCode, Object... conditions) {
        if (ObjectUtils.isNotNull(conditions)) {
            Assert.fail(errorCode);
        }
    }

    public static void notNull(IErrorCode errorCode, Object... conditions) {
        if (ObjectUtils.isNull(conditions)) {
            Assert.fail(errorCode);
        }
    }

    /**
     * <p>
     * 失败结果
     * </p>
     *
     * @param errorCode 异常错误码
     */
    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

    public static void fail(boolean condition, IErrorCode errorCode) {
        if (condition) {
            Assert.fail(errorCode);
        }
    }

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(boolean condition, String message) {
        if (condition) {
            Assert.fail(message);
        }
    }

    public static void notEmpty(Object[] array, IErrorCode errorCode) {
        if (ObjectUtils.isEmpty(array)) {
            Assert.fail(errorCode);
        }
    }

    public static void noNullElements(Object[] array, IErrorCode errorCode) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    Assert.fail(errorCode);
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, IErrorCode errorCode) {
        if (CollectionUtils.isNotEmpty(collection)) {
            Assert.fail(errorCode);
        }
    }

    public static void notEmpty(Map<?, ?> map, IErrorCode errorCode) {
        if (ObjectUtils.isEmpty(map)) {
            Assert.fail(errorCode);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj, IErrorCode errorCode) {
        Assert.notNull(errorCode, type);
        if (!type.isInstance(obj)) {
            Assert.fail(errorCode);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, IErrorCode errorCode) {
        Assert.notNull(errorCode, superType);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            Assert.fail(errorCode);
        }
    }

}
