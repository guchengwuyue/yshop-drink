package co.yixiang.yshop.module.infra.convert.codegen;

import co.yixiang.yshop.framework.common.pojo.PageResult;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.CodegenDetailRespVO;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.CodegenPreviewRespVO;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.CodegenUpdateReqVO;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.column.CodegenColumnRespVO;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.table.CodegenTableRespVO;
import co.yixiang.yshop.module.infra.controller.admin.codegen.vo.table.DatabaseTableRespVO;
import co.yixiang.yshop.module.infra.dal.dataobject.codegen.CodegenColumnDO;
import co.yixiang.yshop.module.infra.dal.dataobject.codegen.CodegenTableDO;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.apache.ibatis.type.JdbcType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface CodegenConvert {

    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);

    // ========== TableInfo 相关 ==========

    @Mappings({
            @Mapping(source = "name", target = "tableName"),
            @Mapping(source = "comment", target = "tableComment"),
    })
    CodegenTableDO convert(TableInfo bean);

    List<CodegenColumnDO> convertList(List<TableField> list);

    @Mappings({
            @Mapping(source = "name", target = "columnName"),
            @Mapping(source = "metaInfo.jdbcType", target = "dataType", qualifiedByName = "getDataType"),
            @Mapping(source = "comment", target = "columnComment"),
            @Mapping(source = "metaInfo.nullable", target = "nullable"),
            @Mapping(source = "keyFlag", target = "primaryKey"),
            @Mapping(source = "keyIdentityFlag", target = "autoIncrement"),
            @Mapping(source = "columnType.type", target = "javaType"),
            @Mapping(source = "propertyName", target = "javaField"),
    })
    CodegenColumnDO convert(TableField bean);

    @Named("getDataType")
    default String getDataType(JdbcType jdbcType) {
        return jdbcType.name();
    }

    // ========== CodegenTableDO 相关 ==========

//    List<CodegenTableRespVO> convertList02(List<CodegenTableDO> list);

    CodegenTableRespVO convert(CodegenTableDO bean);

    PageResult<CodegenTableRespVO> convertPage(PageResult<CodegenTableDO> page);

    // ========== CodegenTableDO 相关 ==========

    List<CodegenColumnRespVO> convertList02(List<CodegenColumnDO> list);

    CodegenTableDO convert(CodegenUpdateReqVO.Table bean);

    List<CodegenColumnDO> convertList03(List<CodegenUpdateReqVO.Column> columns);

    List<DatabaseTableRespVO> convertList04(List<TableInfo> list);

    // ========== 其它 ==========

    default CodegenDetailRespVO convert(CodegenTableDO table, List<CodegenColumnDO> columns) {
        CodegenDetailRespVO respVO = new CodegenDetailRespVO();
        respVO.setTable(convert(table));
        respVO.setColumns(convertList02(columns));
        return respVO;
    }

    default List<CodegenPreviewRespVO> convert(Map<String, String> codes) {
        return codes.entrySet().stream().map(entry -> {
            CodegenPreviewRespVO respVO = new CodegenPreviewRespVO();
            respVO.setFilePath(entry.getKey());
            respVO.setCode(entry.getValue());
            return respVO;
        }).collect(Collectors.toList());
    }

}
