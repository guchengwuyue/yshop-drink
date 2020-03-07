package com.ruoyi.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.common.persistence.model.WechatNewsArticle;
import com.ruoyi.app.modular.shop.service.dto.NewsDTO;

import java.util.List;


public interface INewsService extends IService<WechatNewsArticle> {

    List<NewsDTO> getList(int page,int limit);

}
