package com.liugh.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUserName(String username);

    User getUserByMobile(String mobile);

    /**
     * 注册用户
     * @param user
     * @param roleCode
     * @return
     */
    User register(User user, String roleCode);

    Map<String, Object> getLoginUserAndMenuInfo(User user);

    boolean deleteByUserNo(String userNo);

    Page<User> selectPageByConditionUser(Page<User> userPage, String info, Integer[] status, String startTime, String endTime);
}
