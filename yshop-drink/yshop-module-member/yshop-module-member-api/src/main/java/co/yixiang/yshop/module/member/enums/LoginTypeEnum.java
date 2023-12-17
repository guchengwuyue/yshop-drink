/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.yshop.module.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 账单明细相关枚举
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
	WEIXIN_H5("weixinh5","weixinh5"),
	H5("h5","H5"),
	WECHAT("wechat","公众号"),
	APP("app","APP"),
	PC("pc","PC"),
	ROUNTINE("routine","小程序"),
	UNIAPPH5("uniappH5","uniappH5");

//
//	WXAPP("wxapp","微信小程序"),
//	ALIAPP("aliapp","支付宝小程序"),
//	WECHAT("wechat","微信公众号"),
//	H5("h5","h5"),
//	PC("pc","pc"),
//	APP("app","APP");




	private String value;
	private String desc;


}
