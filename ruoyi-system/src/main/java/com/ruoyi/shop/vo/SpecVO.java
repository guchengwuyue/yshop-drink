package com.ruoyi.shop.vo;

import java.util.List;

/**
 * @ClassName SpecVO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/8/27
 **/
public class SpecVO {
    private String goodsId;

    private List<String> list;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SpecVO{" +
                "goodsId='" + goodsId + '\'' +
                ", list=" + list +
                '}';
    }
}
