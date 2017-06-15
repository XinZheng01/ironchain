package com.ironchain.admin.jpush;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ironchain.common.domain.Letter;
import com.ironchain.common.kits.JsonKit;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JpushService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JpushService.class);
	
	@Autowired
	private JPushClient client;
	
	/**
	 * 异步推送
	 * @param letter 消息
	 * @param ids 用户id
	 */
	@Async
	@SuppressWarnings("unchecked")
	public void push(Letter letter, Long[] ids){
		Map<String, String> extras = new HashMap<>();
		extras.put("type", letter.getType() + "");
		String[] userIds = null;
		if(letter.getType() == Letter.TYPE_URL){
			extras.put("url", letter.getUrl());
		}else if(letter.getType() == Letter.TYPE_DEMAND){
			Map<String, Object> attr = JsonKit.nonDefault().fromJson(letter.getAttr(), HashMap.class);
			for (Map.Entry<String, Object> entry : attr.entrySet()) {
				extras.put(entry.getKey(), entry.getValue() != null?entry.getValue().toString() : null);
			}
		}
		//userid long 转 string
		if(ids != null){
			userIds = new String[ids.length];
			for (int i = 0, len = ids.length; i < len; i++) {
				userIds[i] = ids[i].toString();
			}
		}
		
		try {
		    PushResult result = client.sendPush(buildAllPush(userIds, letter.getSendType(), letter.getTitle(), extras));
		    LOGGER.info("推送完成 - 返回参数" + result);
		} catch (APIConnectionException e) {
			LOGGER.error("推送连接服务端异常", e);
		
		} catch (APIRequestException e) {
			LOGGER.error("推送失败 - 异常", e);
			LOGGER.info("HTTP Status: " + e.getStatus());
			LOGGER.info("Error Code: " + e.getErrorCode());
			LOGGER.info("Error Message: " + e.getErrorMessage());
		}
		
	}
	
	/**
	 * 安卓推送 自定义消息
	 * @param userIds
	 * @param type
	 * @param sendType
	 * @param title
	 * @param content
	 * @param extras
	 * @return
	 */
	public PushPayload buildAndroidPush(String[] userIds, int sendType, String title, String content, Map<String, String> extras){
		return PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(sendType == Letter.SEND_TYPE_ALL? Audience.all() : Audience.alias(userIds))
				.setMessage(Message.newBuilder()
						.setTitle(title)
						.setMsgContent(content)
						.addExtras(extras)
						.build())
				.build();
	}
	
	/**
	 * IOS推送
	 * @param userIds
	 * @param type
	 * @param sendType
	 * @param title
	 * @param content
	 * @return
	 */
	public PushPayload buildIosPush(String[] userIds, int sendType, String title, String content, Map<String, String> extras){
		content = content != null && content.length() > 116?content.substring(0, 116) : content;
		return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(sendType == Letter.SEND_TYPE_ALL? Audience.all() : Audience.alias(userIds))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(title)
//                                .setSound("default")
                                .addExtras(extras)
                                .build())
                        .build())
                 .setMessage(Message.content(content))
                 .build();
	}
	
	/**
	 * 推送所有平台
	 * @param userIds
	 * @param type
	 * @param sendType
	 * @param title
	 * @param content
	 * @return
	 */
	public PushPayload buildAllPush(String[] userIds, int sendType, String title, Map<String, String> extras){
		return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(sendType == Letter.SEND_TYPE_ALL? Audience.all() : Audience.alias(userIds))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(title)
//                                .setSound("default")
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                        		.setAlert(title)
                        		.addExtras(extras)
                        		.build())
                        .build())
                 .build();
	}
}
