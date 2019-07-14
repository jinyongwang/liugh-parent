package com.liugh.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liugh.entity.InfoToUser;
import com.liugh.mapper.InfoToUserMapper;
import com.liugh.service.IInfoToUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户电话关系表 服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-10-29
 */
@Service
public class InfoToUserServiceImpl extends ServiceImpl<InfoToUserMapper, InfoToUser> implements IInfoToUserService {

}
