package com.liugh.service;

import com.alibaba.fastjson.JSONObject;
import com.liugh.entity.Notice;
import com.baomidou.mybatisplus.service.IService;
import com.liugh.entity.User;

/**
 * <p>
 * 消息通知表 服务类
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
public interface INoticeService extends IService<Notice> {

    /**
     *
     * @param themeNo 主题的主键,自己根据业务建立表赋值
     * @param mobile 电话
     */
    void insertByThemeNo(String themeNo,String  mobile);

    void deleteByNotices(User user)throws Exception;

    void read(JSONObject requestJson)throws Exception;
}
