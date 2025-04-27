package co.yixiang.yshop.module.store.service.storeshop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import co.yixiang.yshop.framework.common.exception.ErrorCode;
import co.yixiang.yshop.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import co.yixiang.yshop.module.store.controller.admin.storeshop.vo.*;
import co.yixiang.yshop.module.store.dal.dataobject.storeshop.StoreShopDO;
import co.yixiang.yshop.framework.common.pojo.PageResult;

import co.yixiang.yshop.module.store.convert.storeshop.StoreShopConvert;
import co.yixiang.yshop.module.store.dal.mysql.storeshop.StoreShopMapper;

import static co.yixiang.yshop.framework.common.exception.util.ServiceExceptionUtil.exception;
import static co.yixiang.yshop.module.store.enums.ErrorCodeConstants.*;

/**
 * 门店管理 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class StoreShopServiceImpl implements StoreShopService {

    @Resource
    private StoreShopMapper shopMapper;

    @Override
    public Long createShop(StoreShopCreateReqVO createReqVO) {
        //管理员只能绑定一个门店
        createReqVO.getAdminId().forEach(val -> {
           Long count = shopMapper.selectCount(new LambdaQueryWrapper<StoreShopDO>()
                    .apply(
                        "FIND_IN_SET ('" + val + "',admin_id)"));
           if(count > 0){
               throw exception(new ErrorCode(1000,"管理员ID："+val+"已经绑定过其他门店，不能再次绑定"));
           }
        });
        // 插入
        StoreShopDO shop = StoreShopConvert.INSTANCE.convert(createReqVO);
        Integer status = doShopStatus(shop.getStartTime(),shop.getEndTime());
        shop.setStatus(status);
        shopMapper.insert(shop);
        // 返回
        return shop.getId();
    }

    @Override
    public void updateShop(StoreShopUpdateReqVO updateReqVO) {

        //管理员只能绑定一个门店
        updateReqVO.getAdminId().forEach(val -> {
            Long count = shopMapper.selectCount(new LambdaQueryWrapper<StoreShopDO>()
                    .ne(StoreShopDO::getId,updateReqVO.getId())
                    .apply(
                            "FIND_IN_SET ('" + val + "',admin_id)"));
            if(count > 0){
                throw exception(new ErrorCode(1000,"管理员ID："+val+"已经绑定过其他门店，不能再次绑定"));
            }
        });
        // 校验存在
        validateShopExists(updateReqVO.getId());
        // 更新
        StoreShopDO updateObj = StoreShopConvert.INSTANCE.convert(updateReqVO);
        Integer status = doShopStatus(updateObj.getStartTime(),updateObj.getEndTime());
        updateObj.setStatus(status);
        shopMapper.updateById(updateObj);
    }


    /**
     * 处理营业时间
     * @param startTime
     * @param endTime
     * @return
     */
    private Integer doShopStatus(Date startTime,Date endTime){
        Date now = new Date();
        Integer sH = DateUtil.hour(startTime,true);
        Integer sM = DateUtil.minute(startTime);
        Integer eH = DateUtil.hour(endTime,true);
        Integer eM = DateUtil.minute(endTime);
        Integer nH = DateUtil.hour(now,true);
        Integer nM = DateUtil.minute(now);
        if(nH < sH){
            return 0;
        }else if(eH < nH){
            return 0;
        }else if(nH == sH && sM > nM){
            return 0;
        }else if(eH == nH && eM < nM){
            return 0;
        } else{
            return 1;
        }
    }

    @Override
    public void deleteShop(Long id) {
        // 校验存在
        validateShopExists(id);
        // 删除
        shopMapper.deleteById(id);
    }

    private void validateShopExists(Long id) {
        if (shopMapper.selectById(id) == null) {
            throw exception(SHOP_NOT_EXISTS);
        }
    }

    @Override
    public StoreShopDO getShop(Long id) {
        return shopMapper.selectById(id);
    }

    @Override
    public List<StoreShopDO> getShopList() {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        LambdaQueryWrapper<StoreShopDO> wrapper = new LambdaQueryWrapper<>();
        if(shopId == 0) {
            wrapper.ge(StoreShopDO::getId,shopId);
        }else {
            wrapper.eq(StoreShopDO::getId,shopId);
        }
        return shopMapper.selectList(wrapper);
    }

    @Override
    public PageResult<StoreShopDO> getShopPage(StoreShopPageReqVO pageReqVO) {
        return shopMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StoreShopDO> getShopList(StoreShopExportReqVO exportReqVO) {
        return shopMapper.selectList(exportReqVO);
    }

}
