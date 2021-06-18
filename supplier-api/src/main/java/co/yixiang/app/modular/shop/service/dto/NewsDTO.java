package co.yixiang.app.modular.shop.service.dto;


import lombok.Data;

import java.io.Serializable;


@Data
public class NewsDTO implements Serializable {


    private Long id;

    private String title;

    private String localUrl;
    /**
     * 作者
     */
    private String author;
    /**
     * 摘要内容
     */
    private String digest;
    /**
     * 图文内容
     */
    private String content;


}
