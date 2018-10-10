package com.liugh.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liugh.annotation.CurrentUser;
import com.liugh.annotation.ValidationParam;
import com.liugh.base.BusinessException;
import com.liugh.config.ResponseHelper;
import com.liugh.config.ResponseModel;
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
    public ResponseModel<Page<Notice>> findInfoList(@CurrentUser User user, @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                      @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) throws Exception{

        Page<Notice> noticePage = noticeService.selectPage(new Page<>(pageIndex, pageSize),new EntityWrapper<Notice>().
                eq("mobile",user.getMobile()).orderBy("create_time",false));
        return ResponseHelper.buildResponseModel(noticePage);
    }

    /**
     * 删除全部消息
     * @return
     * @throws Exception
     */
    @DeleteMapping
    public ResponseModel findInfoList(@CurrentUser User user) throws Exception{
        List<Notice> notices = noticeService.selectList(new EntityWrapper<Notice>().eq("mobile",user.getMobile()));
        if(ComUtil.isEmpty(notices)){
            throw new BusinessException("消息不存在");
        }else {
            for (Notice notice:notices) {
                noticeService.deleteById(notice.getNoticeId());
            }
        }
        return ResponseHelper.buildResponseModel(null);
    }

    /**
     * 消息改变为已读
     * @param requestJson
     * @return
     * @throws Exception
     */
    @PostMapping("/read")
    public ResponseModel read(@ValidationParam("noticeId,isRead")
                             @RequestBody JSONObject requestJson) throws Exception{
        Notice notice = noticeService.selectById(requestJson.getString("noticeId"));
        if(ComUtil.isEmpty(notice)){
            throw new BusinessException("消息不存在");
        }
        //已读
        notice.setIsRead(requestJson.getInteger("isRead"));
        boolean result = noticeService.updateById(notice);
        return ResponseHelper.buildResponseModel(result);
    }
    /**
     * 未读消息总数
     * @return
     * @throws Exception
     */
    @GetMapping("/noReadCount")
    public ResponseModel getNoRead(@CurrentUser User user) throws Exception{
        List<Notice> notice = noticeService.selectList(new EntityWrapper<Notice>().where("mobile = {0} and is_read = 0",user.getMobile()));
        return ResponseHelper.buildResponseModel(notice.size());
    }


}

