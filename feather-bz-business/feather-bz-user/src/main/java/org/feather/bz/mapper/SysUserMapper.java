package org.feather.bz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.feather.bz.domain.entity.SysUser;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.mapper
 * @className: SysUserMapper.xml
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:51
 * @version: 1.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
