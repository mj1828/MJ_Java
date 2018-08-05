/**
 * 聊天室js
 */

var chatRoomId;
var siteId;

(function($) {
	window.initChatRoom = function(options) {
		chatRoomId = options.ChatRoomId;
		siteId = options.SiteId;
		initResourceUpload();
	}
})(jQuery);

function initResourceUpload() {
	$("#image").dropzone({
		url : frontAppContext + "im/upload?ChatRoomId=" + chatRoomId + "&SiteID=" + siteId + "&Type=ImageMessage",
		addRemoveLinks : true,
		dictRemoveLinks : "x",
		dictCancelUpload : "x",
		maxFiles : 10,
		maxFilesize : 5,
		acceptedFiles : ".jpg,.png",
		init : function() {
			this.on("success", function(file, data) {
				var result = JSON.parse(data);
				sendResourceMessage(result.FilePath, "ImageMessage");
			});
			this.on("removedfile", function(file) {
				console.log("File " + file.name + "removed");
			});
		}
	});
	$("#audio").dropzone({
		url : frontAppContext + "im/upload?ChatRoomId=" + chatRoomId + "&SiteID=" + siteId + "&Type=AudioMessage",
		addRemoveLinks : true,
		dictRemoveLinks : "x",
		dictCancelUpload : "x",
		maxFiles : 10,
		maxFilesize : 5,
		acceptedFiles : ".mp3",
		init : function() {
			this.on("success", function(file, data) {
				var result = JSON.parse(data);
				sendResourceMessage(result.FilePath, "AudioMessage");
			});
			this.on("removedfile", function(file) {
				console.log("File " + file.name + "removed");
			});
		}
	});
	$("#video").dropzone({
		url : frontAppContext + "im/upload?ChatRoomId=" + chatRoomId + "&SiteID=" + siteId + "&Type=VideoMessage",
		addRemoveLinks : true,
		dictRemoveLinks : "x",
		dictCancelUpload : "x",
		maxFiles : 10,
		maxFilesize : 5,
		acceptedFiles : ".mp4",
		init : function() {
			this.on("success", function(file, data) {
				var result = JSON.parse(data);
				sendResourceMessage(result.FilePath, "VideoMessage");
			});
			this.on("removedfile", function(file) {
				console.log("File " + file.name + "removed");
			});
		}
	});
	$("#file").dropzone({
		url : frontAppContext + "im/upload?ChatRoomId=" + chatRoomId + "&SiteID=" + siteId + "&Type=FileMessage",
		addRemoveLinks : true,
		dictRemoveLinks : "x",
		dictCancelUpload : "x",
		maxFiles : 10,
		maxFilesize : 5,
		acceptedFiles : ".zip",
		init : function() {
			this.on("success", function(file, data) {
				var result = JSON.parse(data);
				sendResourceMessage(result.FilePath, "FileMessage");
			});
			this.on("removedfile", function(file) {
				console.log("File " + file.name + "removed");
			});
		}
	});
}

// 发送消息
function sendMessage(msg, msgType) {
	var dc = {};
	dc.ChatRoomId = chatRoomId;
	dc.Message = $("#myMsg").val();
	var msgType = $("#MsgType").val();
	dc.MsgType = (msgType == "" ? "Message" : msgType);
	dc.MsgTimestamp = new Date().getTime();
	var message = {};
	message.Message = dc;
	send(message);
}

function sendResourceMessage(path, msgType) {
	var dc = {};
	dc.ChatRoomId = chatRoomId;
	dc.Message = path;
	dc.MsgType = msgType;
	dc.MsgTimestamp = new Date().getTime();
	var message = {};
	message.Message = dc;
	send(message);
}

function sendPanelMessage(content) {
	var dc = {};
	dc.ChatRoomId = chatRoomId;
	dc.Message = content;
	dc.MsgType = "PanelMessage";
	dc.MsgTimestamp = new Date().getTime();
	var message = {};
	message.Message = dc;
	send(message);
}

function send(message, id) {
	Server.sendRequest('im.sendMessage', message, function(response) {
		if (response.Status != 1) {
			alert(response.Message);
		} else {
			if (id) {
				$("#" + id).val("");
			} else {
				$("#myMsg").val("");
			}
		}
	});
}

function loadOnlineMember(memberList) {
	$("#memberlist").html("");
	for (var i = 0; i < memberList.length; i++) {
		$("#memberlist").append(initMember(memberList[i]));
	}
	$('#memberlist').scrollTop($('#memberlist')[0].scrollHeight);
}

function showMessage(message, msgType) {
	if (msgType == "PanelMessage") {

	} else {
		$("#chatroommessage").append(initMessage(message, msgType));
		$('#chatroommessage').scrollTop($('#chatroommessage')[0].scrollHeight);
	}
}

function initMessage(message, msgType) {
	var content = "";
	if (msgType == "ImageMessage") {
		content = "<li class='discuss-item'> "
				+ "<div class='ursr-info clearfix'> "
				+ "<a class='pull-left' href='#'> <img "
				+ "src='../../images/user-avatar.png' /> <span>"
				+ message.FromUserName + "</span> " + "</a> "
				+ "<div class='pull-right'>" + message.MsgTimestamp + "</div> "
				+ "</div> "
				+ "<img style='width:100%;height:100%;' src='" + prefix
				+ message.Message + "'></img> " + "</li> ";
	} else if (msgType == "AudioMessage") {
		content = "<li class='discuss-item'> "
				+ "<div class='ursr-info clearfix'> "
				+ "<a class='pull-left' href='#'> <img "
				+ "src='../../images/user-avatar.png' /> <span>"
				+ message.FromUserName
				+ "</span> "
				+ "</a> "
				+ "<div class='pull-right'>"
				+ message.MsgTimestamp
				+ "</div> "
				+ "</div> "
				+ "<audio style='width:100%;height:100%;margin-top:10px;' src='" + prefix
				+ message.Message
				+ "' autoplay='autoplay' preload='preload' controls='controls' ></audio> "
				+ "</li> ";
	} else if (msgType == "VideoMessage") {
		content = "<li class='discuss-item'> "
				+ "<div class='ursr-info clearfix'> "
				+ "<a class='pull-left' href='#'> <img "
				+ "src='../../images/user-avatar.png' /> <span>"
				+ message.FromUserName
				+ "</span> "
				+ "</a> "
				+ "<div class='pull-right'>"
				+ message.MsgTimestamp
				+ "</div> "
				+ "</div> "
				+ "<video style='width:100%;height:100%;' src='" + prefix
				+ message.Message
				+ "' autoplay='autoplay' preload='preload' controls='controls' ></video> "
				+ "</li> ";
	} else if (msgType == "FileMessage") {
		content = "<li class='discuss-item'> "
				+ "<div class='ursr-info clearfix'> "
				+ "<a class='pull-left' href='#'> <img "
				+ "src='../../images/user-avatar.png' /> <span>"
				+ message.FromUserName + "</span> " + "</a> "
				+ "<div class='pull-right'>" + message.MsgTimestamp + "</div> "
				+ "</div> " + "<a href='" + prefix + message.Message
				+ "' target='_blank'>下载附件</a> " + "</li> ";
	} else {
		content = "<li class='discuss-item'> "
				+ "<div class='ursr-info clearfix'> "
				+ "<a class='pull-left' href='#'> <img "
				+ "src='../../images/user-avatar.png' /> <span>"
				+ message.FromUserName + "</span> " + "</a> "
				+ "<div class='pull-right'>" + message.MsgTimestamp + "</div> "
				+ "</div> " + "<p class='discuss-con'> " + message.Message
				+ "</p> " + "</li> ";
	}
	return content;
}

function initMember(memberInfo) {
	var content = "<li class='discuss-item' id='" + memberInfo.FromMemberId
			+ "' chatroomid='" + memberInfo.ChatRoomId + "' membertype='"
			+ memberInfo.MemberType + "''> "
			+ "<div class='ursr-info clearfix'> "
			+ "<a class='pull-left' href='#'> <img "
			+ "src='../../images/user-avatar.png' /> <span>"
			+ memberInfo.FromUserName + "</span> " + "</a> "
			+ "<div class='pull-right'>" + memberInfo.MemberType + "</div> "
			+ "</div> " + "</li> ";
	return content;
}