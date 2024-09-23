package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编号
     */
    private String code;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别（1.男 2.女）
     */
    private String sex;

    /**
     * 地区
     */
    private String area;

    /**
     * 职业
     */
    private String profession;

    /**
     * 图片
     */
    private String images;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属账户
     */
    private Integer userId;

    /**
     * openId
     */
    private String openId;

    /**
     * 创建时间
     */
    private String createDate;

    private String userName;

    private String avatar;

    @TableField(exist = false)
    private Integer focusNum;

    @TableField(exist = false)
    private Integer fansNum;
}
