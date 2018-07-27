package com.liugh.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.annotation.CurrentUser;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.BusinessException;
import com.liugh.base.PageResult;
import com.liugh.base.PublicResult;
import com.liugh.base.PublicResultConstant;
import com.liugh.entity.Notice;
import com.liugh.entity.User;
import com.liugh.service.INoticeService;
import com.liugh.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * 消息通知表 前端控制器
 * </p>
 *
 * @author liugh
 * @since 2018-07-27
 */
@RestController
@RequestMapping("/notice")
@ApiIgnore
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 获取自己的消息列表
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/infoList")
    public PublicResult findInfoList(@CurrentUser User user,@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) throws Exception{

        Page<Notice> noticePage = noticeService.selectPage(new Page<>(pageIndex, pageSize),new EntityWrapper<Notice>().
                eq("mobile",user.getMobile()).orderBy("create_time",false));
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(noticePage.getTotal(),pageIndex,pageSize,noticePage.getRecords()));
    }

    /**
     * 删除全部消息
     * @return
     * @throws Exception
     */
    @DeleteMapping
    public PublicResult findInfoList(@CurrentUser User user) throws Exception{
        List<Notice> notices = noticeService.selectList(new EntityWrapper<Notice>().eq("mobile",user.getMobile()));
        if(ComUtil.isEmpty(notices)){
            throw new BusinessException("消息不存在");
        }else {
            for (Notice notice:notices) {
                noticeService.deleteById(notice.getNoticeId());
            }
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 消息改变为已读
     * @param requestJson
     * @return
     * @throws Exception
     */
    @PostMapping("/read")
    public PublicResult read(@ValidationParam("noticeId,isRead")
                             @RequestBody JSONObject requestJson) throws Exception{
        Notice notice = noticeService.selectById(requestJson.getString("noticeId"));
        if(ComUtil.isEmpty(notice)){
            throw new BusinessException("消息不存在");
        }
        //已读
        notice.setIsRead(requestJson.getInteger("isRead"));
        boolean result = noticeService.updateById(notice);
        return result? new PublicResult<>(PublicResultConstant.SUCCESS, null):
                new PublicResult<>("更新失败，请联系管理员！",null);
    }
    /**
     * 未读消息总数
     * @return
     * @throws Exception
     */
    @GetMapping("/noReadCount")
    public PublicResult getNoRead(@CurrentUser User user) throws Exception{
        List<Notice> notice = noticeService.selectList(new EntityWrapper<Notice>().where("mobile = {0} and is_read = 0",user.getMobile()));
        return new PublicResult<>(PublicResultConstant.SUCCESS, notice.size());
    }


}

