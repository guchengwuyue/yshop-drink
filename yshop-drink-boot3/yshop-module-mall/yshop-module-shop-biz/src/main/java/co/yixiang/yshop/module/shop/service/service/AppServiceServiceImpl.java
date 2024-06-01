package co.yixiang.yshop.module.shop.service.service;

import co.yixiang.yshop.module.shop.dal.dataobject.service.ServiceDO;
import co.yixiang.yshop.module.shop.dal.mysql.service.ServiceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 我的服务 Service 实现类
 *
 * @author yshop
 */
@Service
@Validated
public class AppServiceServiceImpl extends ServiceImpl<ServiceMapper, ServiceDO> implements AppServiceService {

    @Resource
    private ServiceMapper serviceMapper;



}
