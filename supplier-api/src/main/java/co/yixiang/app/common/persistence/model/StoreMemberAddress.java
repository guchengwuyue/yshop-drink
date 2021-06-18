package co.yixiang.app.common.persistence.model;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商城会员收货地址
 * </p>
 *
 * @author hupeng
 * @since 2019-09-13
 */
@TableName("store_member_address")
public class StoreMemberAddress extends Model<StoreMemberAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 会员ID
     */
    private Long mid;
    /**
     * 收货人姓名
     */
    private String username;
    /**
     * 收货手机号
     */
    private String phone;
    /**
     * 收货地址省份
     */
    private String province;
    /**
     * 收货地址城市
     */
    private String city;
    /**
     * 收货地址区域
     */
    private String area;
    /**
     * 城市信息
     */
    @TableField("city_info")
    private String cityInfo;
    /**
     * 收货详细地址
     */
    private String address;
    /**
     * 状态(0无效, 1新快递)
     */
    private Integer status;
    /**
     * 默认收货地址
     */
    @TableField("is_default")
    private Integer isDefault;
    /**
     * 删除状态(1删除,0未删除)
     */
    @TableField("is_deleted")
    private Long isDeleted;
    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StoreMemberAddress{" +
        ", id=" + id +
        ", mid=" + mid +
        ", username=" + username +
        ", phone=" + phone +
        ", province=" + province +
        ", city=" + city +
        ", area=" + area +
        ", cityInfo=" + cityInfo +
        ", address=" + address +
        ", status=" + status +
        ", isDefault=" + isDefault +
        ", isDeleted=" + isDeleted +
        ", createAt=" + createAt +
        "}";
    }
}
