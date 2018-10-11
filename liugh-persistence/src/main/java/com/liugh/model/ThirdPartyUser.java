/**
 * 第三方用户实体类
 */
package com.liugh.model;


import lombok.*;

import java.io.Serializable;

/**
 * @author liugh
 * @version 2018年7月26日 下午3:26:23
 */
@SuppressWarnings("serial")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ThirdPartyUser implements Serializable {
	private String account;// 用户
	private String username;// 用户昵称
	private String avatarUrl;// 用户头像地址
	private String gender;// 用户性别
	private String token;// 用户认证
	private String openid;// 用户第三方id
	private String provider;// 用户类型
	private Integer userId;// 用户id
	private Long userThirdpartyId;
}
