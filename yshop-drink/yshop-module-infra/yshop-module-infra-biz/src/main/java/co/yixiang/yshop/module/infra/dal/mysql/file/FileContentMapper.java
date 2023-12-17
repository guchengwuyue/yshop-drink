package co.yixiang.yshop.module.infra.dal.mysql.file;

import co.yixiang.yshop.module.infra.dal.dataobject.file.FileContentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileContentMapper extends BaseMapper<FileContentDO> {
}
