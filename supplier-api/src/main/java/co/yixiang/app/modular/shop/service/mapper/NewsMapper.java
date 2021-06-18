package co.yixiang.app.modular.shop.service.mapper;


import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;
import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import co.yixiang.app.modular.mapper.EntityMapper;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author hupeng
* @date 2019-09-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper extends EntityMapper<NewsDTO, WechatNewsArticle> {

}