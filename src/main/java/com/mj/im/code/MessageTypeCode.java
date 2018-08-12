package com.mj.im.code;

/**
 * 聊天室消息类型
 * 
 * @author zyc
 */
public class MessageTypeCode {
	public static final String ID = "MessageType";

	public final static String Message = "Message";// 普通消息
	public final static String PanelMessage = "PanelMessage";// 面板消息
	public final static String SystemMessage = "SystemMessage";// 系统消息
	public final static String AudioMessage = "AudioMessage";// 音频消息
	public final static String VideoMessage = "VideoMessage";// 视频消息
	public final static String ImageMessage = "ImageMessage";// 图片消息
	public final static String FileMessage = "FileMessage";// 附件消息
	public final static String OnlineMessage = "OnlineMessage";// 上线消息
	public final static String OfflineMessage = "OfflineMessage";// 下线消息

	public static String getContentTypeName(String type) {
		if (Message.equals(type)) {
			return "普通消息";
		} else if (PanelMessage.equals(type)) {
			return "面板消息";
		} else if (SystemMessage.equals(type)) {
			return "系统消息";
		} else if (AudioMessage.equals(type)) {
			return "音频消息";
		} else if (VideoMessage.equals(type)) {
			return "视频消息";
		} else if (ImageMessage.equals(type)) {
			return "图片消息";
		} else if (FileMessage.equals(type)) {
			return "附件消息";
		} else if (OnlineMessage.equals(type)) {
			return "上线消息";
		} else if (OfflineMessage.equals(type)) {
			return "下线消息";
		} else {
			return "其他";
		}
	}
}
