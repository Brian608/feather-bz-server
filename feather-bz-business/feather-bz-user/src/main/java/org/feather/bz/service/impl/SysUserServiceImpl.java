package org.feather.bz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.mapper.SysUserMapper;
import org.feather.bz.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.service.impl
 * @className: SysUserServiceImpl
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:50
 * @version: 1.0
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
}
