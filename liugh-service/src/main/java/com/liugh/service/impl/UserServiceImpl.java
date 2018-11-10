package com.liugh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liugh.base.BusinessException;
import com.liugh.base.Constant;
import com.liugh.base.PublicResultConstant;
import com.liugh.entity.Menu;
import com.liugh.entity.SmsVerify;
import com.liugh.service.*;
import com.liugh.entity.User;
import com.liugh.entity.UserToRole;
import com.liugh.mapper.UserMapper;
import com.liugh.util.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liugh123
 * @since 2018-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IUserToRoleService userToRoleService;
    @Autowired
    private IMenuService menuService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ISmsVerifyService smsVerifyService;


    @Autowired
    private IRoleService roleService;

    @Override
//    @Cacheable(value = "UserToRole",keyGenerator="wiselyKeyGenerator")
    public User getUserByUserName(String username) {
        System.out.println("执行getUserByUserName方法了.....");
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.where("user_name={0}", username);
        return this.selectOne(ew);
    }

    @Override
    public User getUserByMobile(String mobile) {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("mobile", mobile);
        return this.selectOne(ew);
    }

    @Override
    public User register(User user, String  roleCode) {
        user.setUserNo(GenerationSequenceUtil.generateUUID("user"));
        user.setCreateTime(System.currentTimeMillis());
        boolean result = this.insert(user);
        if (result) {
            UserToRole userToRole  = UserToRole.builder().userNo(user.getUserNo()).roleCode(roleCode).build();
            userToRoleService.insert(userToRole);
        }
        return user;
    }

    @Override
    public Map<String, Object> getLoginUserAndMenuInfo(User user) {
        Map<String, Object> result = new HashMap<>();
        UserToRole userToRole = userToRoleService.selectByUserNo(user.getUserNo());
        user.setToken(JWTUtil.sign(user.getUserNo(), user.getPassword()));
        result.put("user",user);
        List<Menu> buttonList = new ArrayList<Menu>();
        //根据角色主键查询启用的菜单权限
        List<Menu> menuList = menuService.findMenuByRoleCode(userToRole.getRoleCode());
        List<Menu> retMenuList = menuService.treeMenuList(Constant.ROOT_MENU, menuList);
        for (Menu buttonMenu : menuList) {
            if(buttonMenu.getMenuType() == Constant.TYPE_BUTTON){
                buttonList.add(buttonMenu);
            }
        }
        result.put("menuList",retMenuList);
        result.put("buttonList",buttonList);
        return result;
    }

    @Override
    public void deleteByUserNo(String userNo) throws Exception{
        User user = this.selectById(userNo);
        if (ComUtil.isEmpty(user)) {
            throw new BusinessException(PublicResultConstant.INVALID_USER);
        }
        EntityWrapper<UserToRole> ew = new EntityWrapper<>();
        ew.eq("user_no", userNo);
        userToRoleService.delete(ew);
        this.deleteById(userNo);
    }

    @Override
    public Page<User> selectPageByConditionUser(Page<User> userPage, String info, Integer[] status, String startTime, String endTime) {
        //注意！！ 分页 total 是经过插件自动 回写 到传入 page 对象
        return userPage.setRecords(mapper.selectPageByConditionUser(userPage, info,status,startTime,endTime));
    }

    @Override
    public Map<String, Object> checkMobileAndPasswd(JSONObject requestJson) throws Exception{
        //由于 @ValidationParam注解已经验证过mobile和passWord参数，所以可以直接get使用没毛病。
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        User user = this.selectOne(new EntityWrapper<User>().where("mobile = {0} and status = 1",mobile));
        if (ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("password"), user.getPassword())) {
            throw new BusinessException(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        //测试websocket用户登录给管理员发送消息的例子  前端代码参考父目录下WebSocketDemo.html
//        noticeService.insertByThemeNo("themeNo-cwr3fsxf233edasdfcf2s3","13888888888");
//        MyWebSocketService.sendMessageTo(JSONObject.toJSONString(user),"13888888888");
        user.setRoleName(null);
        return this.getLoginUserAndMenuInfo(user);
    }

    @Override
    public Map<String, Object> checkMobileAndCatcha(JSONObject requestJson) throws Exception {
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        User user = this.getUserByMobile(mobile);
        //如果不是启用的状态
        if(!ComUtil.isEmpty(user) && user.getStatus() != Constant.ENABLE){
            throw new BusinessException("该用户状态不是启用的!");
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.AUTH.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        if (ComUtil.isEmpty(user)) {
            //设置默认密码
            User userRegister = User.builder().password(BCrypt.hashpw("123456", BCrypt.gensalt()))
                    .mobile(mobile).username(mobile).build();
            user =this.register(userRegister, Constant.RoleType.USER);
        }
        return this.getLoginUserAndMenuInfo(user);
    }

    @Override
    public User checkAndRegisterUser(JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        User userRegister = requestJson.toJavaObject(User.class);
        if(!StringUtil.checkMobileNumber(userRegister.getMobile())){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        if (!userRegister.getPassword().equals(requestJson.getString("rePassword"))) {
            throw new BusinessException(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(userRegister.getMobile(),
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.REG.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        //验证码是否过期
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        userRegister.setPassword(BCrypt.hashpw(requestJson.getString("password"), BCrypt.gensalt()));
        //默认注册普通用户
        return this.register(userRegister, Constant.RoleType.USER);
    }

    @Override
    public User updateForgetPasswd(JSONObject requestJson) throws Exception {
        String mobile = requestJson.getString("mobile");
        if(!StringUtil.checkMobileNumber(mobile)){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        if (!requestJson.getString("password").equals(requestJson.getString("rePassword"))) {
            throw new BusinessException(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        User user = this.getUserByMobile(mobile);
        roleService.getRoleIsAdminByUserNo(user.getUserNo());
        if(ComUtil.isEmpty(user)){
            throw new BusinessException(PublicResultConstant.INVALID_USER);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.FINDPASSWORD.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        user.setPassword(BCrypt.hashpw(requestJson.getString("password"),BCrypt.gensalt()));
        this.updateById(user);
        return user;
    }

    @Override
    public void resetMobile(User currentUser, JSONObject requestJson) throws Exception {
        String newMobile = requestJson.getString("newMobile");
        if(!StringUtil.checkMobileNumber(newMobile)){
          throw  new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(newMobile,
                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.MODIFYINFO.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            throw  new BusinessException(PublicResultConstant.VERIFY_PARAM_ERROR);
        }
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw  new BusinessException(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        currentUser.setMobile(newMobile);
        this.updateById(currentUser);
    }

    @Override
    public void resetPassWord(User currentUser, JSONObject requestJson) throws Exception {
        if (!requestJson.getString("password").equals(requestJson.getString("rePassword"))) {
            throw  new BusinessException(PublicResultConstant.INVALID_RE_PASSWORD);
        }
        if(!BCrypt.checkpw(requestJson.getString("oldPassword"),currentUser.getPassword())){
            throw  new BusinessException(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        currentUser.setPassword(BCrypt.hashpw(requestJson.getString("password"),BCrypt.gensalt()));
        this.updateById(currentUser);
    }


}
