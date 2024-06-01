package co.yixiang.yshop.module.score.service.scoreproduct;

import co.yixiang.yshop.module.score.controller.app.product.vo.AppScoreProductVO;
import co.yixiang.yshop.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 积分产品 Service 接口
 *
 * @author yshop
 */
public interface AppScoreProductService  extends IService<ScoreProductDO> {

    /**
     * 列表
     * @param page page
     * @param limit limit
     * @return list
     */
    List<AppScoreProductVO> getList(int page, int limit);

    /**
     * 详情
     * @param id 产品ID
     * @return list
     */
    AppScoreProductVO getDetail(Long id);


}
