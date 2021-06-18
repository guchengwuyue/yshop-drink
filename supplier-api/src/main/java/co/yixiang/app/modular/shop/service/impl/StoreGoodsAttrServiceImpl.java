package co.yixiang.app.modular.shop.service.impl;

import co.yixiang.app.common.persistence.dao.StoreGoodsAttrMapper;
import co.yixiang.app.common.persistence.model.StoreGoodsAttr;
import co.yixiang.app.modular.shop.service.IStoreGoodsAttrService;
import co.yixiang.app.common.persistence.model.StoreGoodsAttr;
import co.yixiang.app.common.persistence.dao.StoreGoodsAttrMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.modular.shop.service.IStoreGoodsAttrService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品关联的属性表(与商品相关) 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-09-07
 */
@Service
public class StoreGoodsAttrServiceImpl extends ServiceImpl<StoreGoodsAttrMapper, StoreGoodsAttr> implements IStoreGoodsAttrService {

}
