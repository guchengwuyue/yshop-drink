package co.yixiang.app.common.persistence.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author hupeng
 * @since 2019-09-10
 */
@TableName("store_cart")
@Data
public class StoreCart extends Model<StoreCart> {

    private static final long serialVersionUID = 1L;

    /**
     * 购物车表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * session
     */
    @TableField("session_id")
    private String sessionId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 商品货号
     */
    @TableField("goods_sn")
    private String goodsSn;
    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    @TableField("goods_logo")
    private String goodsLogo;
    /**
     * 零售价
     */
    @TableField("market_price")
    private BigDecimal marketPrice;
    /**
     * 本店价(进货价)
     */
    @TableField("goods_price")
    private BigDecimal goodsPrice;
    /**
     * 会员折扣价
     */
    @TableField("member_goods_price")
    private BigDecimal memberGoodsPrice;
    /**
     * 购买数量
     */
    @TableField("goods_num")
    private Integer goodsNum;
    /**
     * 商品规格key 对应store_spec_goods_price 表
     */
    @TableField("spec_key")
    private String specKey;
    /**
     * 商品规格组合名称
     */
    @TableField("spec_key_name")
    private String specKeyName;
    /**
     * 商品条码
     */
    @TableField("bar_code")
    private String barCode;
    /**
     * 购物车选中状态
     */
    private Integer selected;
    /**
     * 加入购物车的时间
     */
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private Date addTime;
    /**
     * 0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠
     */
    @TableField("prom_type")
    private Integer promType;
    /**
     * 活动id
     */
    @TableField("prom_id")
    private Integer promId;
    /**
     * sku
     */
    private String sku;



}
