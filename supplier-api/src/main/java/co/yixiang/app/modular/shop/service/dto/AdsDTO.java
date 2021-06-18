package co.yixiang.app.modular.shop.service.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class AdsDTO implements Serializable {

    private Integer id;

    private String videoUrl;

    /**
     * 轮播图片地址
     */
    private String url;

}
