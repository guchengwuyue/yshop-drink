package co.yixiang.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import co.yixiang.app.common.persistence.dao.WechatNewsArticleMapper;
import co.yixiang.app.common.persistence.model.WechatNewsArticle;
import co.yixiang.app.modular.shop.service.INewsService;
import co.yixiang.app.modular.shop.service.dto.NewsDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.modular.shop.service.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewsServiceImpl extends ServiceImpl<WechatNewsArticleMapper, WechatNewsArticle>  implements INewsService {

    private final NewsMapper newsMapper;

    /**
     * 新闻列表
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<NewsDTO> getList(int page, int limit) {
        QueryWrapper<WechatNewsArticle> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        Page<WechatNewsArticle> pageModel = new Page<>(page, limit);
        IPage<WechatNewsArticle> pageList = baseMapper.selectPage(pageModel,wrapper);

        return newsMapper.toDto(pageList.getRecords());
    }
}
