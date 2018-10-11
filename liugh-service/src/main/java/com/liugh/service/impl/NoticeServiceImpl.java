package com.liugh.service.impl;

import com.liugh.entity.Notice;
import com.liugh.mapper.NoticeMapper;
import com.liugh.service.INoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息通知表 服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-07-27
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public void insertByThemeNo(String themeNo,String  mobile) {
        Notice notice = Notice.builder().themeNo(themeNo).type(1).title("测试通知1111111111")
                                                                        //未读
                .mobile(mobile).createTime(System.currentTimeMillis()).isRead(0).build();
        this.insert(notice);
    }
}
