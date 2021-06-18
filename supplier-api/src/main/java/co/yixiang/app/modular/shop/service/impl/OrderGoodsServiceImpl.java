package co.yixiang.app.modular.shop.service.impl;


//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;



import co.yixiang.app.common.persistence.dao.StoreOrderGoodsMapper;
import co.yixiang.app.common.persistence.model.StoreOrderGoods;
import co.yixiang.app.modular.shop.service.IOrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.yixiang.app.common.persistence.dao.*;
import co.yixiang.app.common.persistence.model.*;
import co.yixiang.app.modular.shop.service.IOrderGoodsService;
import org.springframework.stereotype.Service;



@Service
public class OrderGoodsServiceImpl extends ServiceImpl<StoreOrderGoodsMapper, StoreOrderGoods>  implements IOrderGoodsService {

}
