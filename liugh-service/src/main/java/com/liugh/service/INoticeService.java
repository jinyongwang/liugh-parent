package com.liugh.service;

import com.liugh.entity.Notice;
import com.baomidou.mybatisplus.service.IService;

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
}
