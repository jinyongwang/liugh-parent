package com.liugh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author liugh123
 * @since 2018-06-25
 */
@TableName("tb_operation_log")
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "operation_log_id", type = IdType.AUTO)
    private Integer operationLogId;
    /**
     * 日志描述
     */
    @TableField("log_description")
    private String logDescription;
    /**
     * 方法参数
     */
    @TableField("action_args")
    private String actionArgs;
    /**
     * 用户主键
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 类名称
     */
    @TableField("class_name")
    private String className;
    /**
     * 方法名称
     */
    @TableField("method_name")
    private String methodName;
    private String ip;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 是否成功 1:成功 2异常
     */
    private Integer succeed;
    /**
     * 异常堆栈信息
     */
    private String message;


    public Integer getOperationLogId() {
        return operationLogId;
    }

    public void setOperationLogId(Integer operationLogId) {
        this.operationLogId = operationLogId;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    public String getActionArgs() {
        return actionArgs;
    }

    public void setActionArgs(String actionArgs) {
        this.actionArgs = actionArgs;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected Serializable pkVal() {
        return this.operationLogId;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "operationLogId=" + operationLogId +
                ", logDescription=" + logDescription +
                ", actionArgs=" + actionArgs +
                ", userNo=" + userNo +
                ", className=" + className +
                ", methodName=" + methodName +
                ", ip=" + ip +
                ", createTime=" + createTime +
                ", succeed=" + succeed +
                ", message=" + message +
                "}";
    }
}
