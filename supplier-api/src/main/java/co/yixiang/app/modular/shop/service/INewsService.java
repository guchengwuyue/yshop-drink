package co.yixiang.app.modular.shop.service;




import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;

import java.util.List;


public interface INewsService extends IService<WechatNewsArticle> {

    List<NewsDTO> getList(int page,int limit);

}
