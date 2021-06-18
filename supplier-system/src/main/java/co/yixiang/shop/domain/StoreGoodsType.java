package co.yixiang.shop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import co.yixiang.common.annotation.Excel;
import co.yixiang.common.core.domain.BaseEntity;

/**
 * 商品类型(商品模型)对象 store_goods_type
 * 
 * @author hupeng
 * @date 2019-08-24
 */
public class StoreGoodsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id自增 */
    private Integer id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String name;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .toString();
    }
}
