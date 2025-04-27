package org.feather.bz.domain.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.entity
 * @className: BaseEntity
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:45
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id 用户id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;


    /**
     * 创建时间 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    private Boolean deleted;
}
