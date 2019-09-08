package com.ruoyi.app.common.utils;


import com.ruoyi.app.modular.shop.service.dto.CateDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TreeUtil
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/9/8
 **/
public class TreeUtil {
    /**
     * 获得指定节点下所有归档
     * @param list
     * @param parentId
     * @return
     */
    public static List<CateDTO> list2TreeConverter(List<CateDTO> list, int parentId) {
        List<CateDTO> returnList = new ArrayList<>();

        for (CateDTO res : list) {
            //判断对象是否为根节点

            if (res.getPid() == parentId) {
                //该节点为根节点,开始递归

                recursionFn(list, res); //通过递归为节点设置childList

                returnList.add(res);
            }
        }

        return returnList;
    }

    /**
     * 递归列表
     * 通过递归,给指定t节点设置childList
     * @param list
     * @param t
     */
    public static void recursionFn(List<CateDTO> list, CateDTO t) {
        //只能获取当前t节点的子节点集,并不是所有子节点集
        List<CateDTO> childsList = getChildList(list, t);
        //设置他的子集对象集
        t.setChildsList(childsList);

        //迭代子集对象集
        for (CateDTO nextChild : childsList) { //遍历完,则退出递归

            //判断子集对象是否还有子节点
            if (!CollectionUtils.isEmpty(childsList)) {
                //有下一个子节点,继续递归
                recursionFn(list, nextChild);
            }
        }
    }

    /**
     * 获得指定节点下的所有子节点
     * @param list
     * @param t
     * @return
     */
    public static List<CateDTO> getChildList(List<CateDTO> list, CateDTO t) {
        List<CateDTO> childsList = new ArrayList<CateDTO>();
        //遍历集合元素,如果元素的Parentid==指定元素的id,则说明是该元素的子节点
        for (CateDTO t1 : list) {
            if (t1.getPid() == t.getId() ) {
                childsList.add(t1);
            }
        }

        return childsList;
    }

    /**
     * 判断是否还有下一个子节点
     * @param list
     * @param t
     */
    public static boolean hasChild(List<CateDTO> list, CateDTO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
