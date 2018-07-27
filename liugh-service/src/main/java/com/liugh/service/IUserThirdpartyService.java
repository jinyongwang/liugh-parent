package com.liugh.service;

import com.liugh.entity.User;
import com.liugh.entity.UserThirdparty;
import com.baomidou.mybatisplus.service.IService;
import com.liugh.model.ThirdPartyUser;

/**
 * <p>
 * 第三方用户表 服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
public interface IUserThirdpartyService extends IService<UserThirdparty> {

    User insertThirdPartyUser(ThirdPartyUser param, String password)throws Exception;

}
