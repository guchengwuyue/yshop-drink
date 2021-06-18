package co.yixiang.app.modular.shop.service;




import com.baomidou.mybatisplus.extension.service.IService;
import co.yixiang.app.common.persistence.model.StoreGoodsCate;
import co.yixiang.app.modular.shop.service.dto.CateDTO;

import java.util.List;


public interface ICateService extends IService<StoreGoodsCate> {
    List<CateDTO> getList();
}
