package co.yixiang.app.modular.shop.service.impl;

import co.yixiang.app.common.persistence.dao.StoreGoodsAttributeMapper;
import co.yixiang.app.common.persistence.model.StoreGoodsAttribute;
import co.yixiang.app.modular.shop.service.IStoreGoodsAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.common.persistence.model.StoreGoodsAttribute;
import co.yixiang.app.common.persistence.dao.StoreGoodsAttributeMapper;
import co.yixiang.app.modular.shop.service.IStoreGoodsAttributeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的属性表(独立) 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@Service
public class StoreGoodsAttributeServiceImpl extends ServiceImpl<StoreGoodsAttributeMapper, StoreGoodsAttribute> implements IStoreGoodsAttributeService {

}
