/**
 * WebSocket js封装
 */
var websocket = '';
var loadOnlineMember;
var showMessage;

(function($) {
	window.websocket = function(options) {
		Server.sendRequest('ChatRoomMemberFront.memberInfo', options,function(response){
			if(response.Status==1){
				if (window.WebSocket) {
					websocket = new WebSocket(encodeURI('ws://' + document.domain
							+ ':8887'));
					websocket.onopen = function() {
						loadOnlineMember = options.LoadOnlineMember;
						showMessage = options.ShowMessage;
						options.MsgType = "OnlineMessage";
						options.FromMemberId = response.FromMemberId;
						options.FromUserName = response.FromUserName;
						websocket.send(JSON.stringify(options));
						wsOnlineMembers(options.ChatRoomId);
						heartCheck.start(websocket)
					};
					websocket.onerror = function() {
						console.log('连接发生错误');
					};
					websocket.onclose = function() {
						console.log('已经断开连接');
						clearTimeout(heartCheck.timeoutObj);
					};
					// 消息接收
					websocket.onmessage = function(message) {
						heartCheck.reset(websocket)
						var content = JSON.parse(message.data);
						var messageType = content.MsgType;
						switch (messageType) {
						case "OnlineMessage":
							onlineEvent(content);
							break;
						case "OfflineMessage":
							offlineEvent(content);
							break;
						case "PanelMessage":
							panelEvent(content);
							break;
						case "SystemMessage":
							systemEvent(content);
							break;
						case "AudioMessage":
							audioEvent(content);
							break;
						case "VideoMessage":
							videoEvent(content);
							break;
						case "ImageMessage":
							imageEvent(content);
							break;
						case "FileMessage":
							fileEvent(content);
							break;
						default:
							messageEvent(content);
						}
					};
				} else {
					alert("该浏览器不支持下单提醒。<br/>建议使用高版本的浏览器，<br/>如 IE10、火狐 、谷歌  、搜狗等");
				}
			}else{
				console.log("没有登录呀，你进不了聊天室呀？要不要注册个账号呀");
			}
		})
	}

	// 封装聊天室右键
	window.memberContextMenu = function(options) {
		Server.sendRequest('ChatRoomMemberFront.memberInfo', options,function(response){
			if (response.Status==1) {
				if(response.MemberType && response.MemberType!="Common"){
					bindMemberContextMenu(options);
				}else{
					removeMemberContextMenu(options);
				}
			}
		});
	}
})(jQuery)

// 心跳包
var heartCheck = {
    timeout: 60000,// 60ms
    timeoutObj: null,
    reset: function(ws){
    		clearInterval(this.timeoutObj);
　　　　 this.start(ws);
    },
    start: function(ws){
        var self = this;
        this.timeoutObj = setInterval(function(){
        		console.log("心跳数据");
        		var data = {};
			data.type = "keeplive";
			data.message = "live";
			ws.send(JSON.stringify(data));
        }, this.timeout)
    },
}
// 上线事件
function onlineEvent(message) {
	wsOnlineMembers(message.ChatRoomId);
}
// 下线事件
function offlineEvent(message) {
	wsOnlineMembers(message.ChatRoomId);
}
// 系统事件
function systemEvent(message) {
	console.log("系统事件");
	var content = message.Message;
	if(content=="ReloadOnLineMember"){
		console.log("你要刷新用户");
		wsOnlineMembers(message.ChatRoomId);
	}else if(content=="ReloadMemberType"){
		console.log("重新加载右键点击事件");
		memberContextMenu(message);
	}
}
// 面板事件
function panelEvent(message) {
	console.log("面板事件");
}
// 消息事件
function messageEvent(message) {
	showMessage(message)
}
// 音频事件
function audioEvent(message) {
	console.log("音频事件");
	showMessage(message,"AudioMessage")
}
// 视频事件
function videoEvent(message) {
	console.log("视频事件");
	showMessage(message,"VideoMessage")
}
// 图片事件
function imageEvent(message) {
	console.log("图片事件");
	showMessage(message,"ImageMessage")
}
//附件事件
function fileEvent(message) {
	console.log("图片事件");
	showMessage(message,"FileMessage")
}
// 获取在线成员
function wsOnlineMembers(chatRoomId) {
	var dc = {};
	dc.ChatRoomId = chatRoomId;
	Server.sendRequest('im.onlineMember', dc, function(response) {
		if (response.Status != 1) {
			alert(response.Message);
		} else {
			loadOnlineMember(response.OnlineMember);
		}
	});
}

// 绑定右键事件
function bindMemberContextMenu(options){
		$("#memberlist").on("contextmenu","li",function(e){
	        // 取消默认的浏览器自带右键 很重要！！
	        e.preventDefault();
	        var id = $(this).attr("id");
	        if(options.MemberId==id){
	        		console.log("不能编辑当前登录用户！");
	        		return;
	        }
	        var chatRoomId = options.ChatRoomId;
	        var memberType = $(this).attr("membertype");
	        if(memberType=="Admin"){
	        		console.log("不能编辑管理员");
	        		return;
	        }
	        // 获取当前用户状态
	        var dc = {};
	        dc.MemberID = id;
	        	dc.ChatRoomID = chatRoomId;
	        Server.sendRequest('ChatRoomMemberFront.initChatRoomMember', dc, function(response) {
				if (response.Status != 1) {
					console.log("初始化聊天室用户信息失败");
				} else {
					var gag = response.Gag;
					var html = initMenu(gag,memberType);
					$("body").append(html);
					$("#menu").show();
					$(".menu").on("click",function(e){
				    		executeEvent($(this).attr("type"),$(this).attr("value"),id,chatRoomId,options.ReloadMemberList);
				    })
			        // 获取我们自定义的右键菜单
			        var menu=document.querySelector("#menu");
			        // 根据事件对象中鼠标点击的位置，进行定位
			        menu.style.left=e.clientX+'px';
			        menu.style.top=e.clientY+'px';
			        // 改变自定义菜单的宽，让它显示出来
			        menu.style.width='125px';
				}
			});
		})
}
//绑定右键事件
function removeMemberContextMenu(options){
		$("#memberlist").off("contextmenu","li");
}
// 初始化右键菜单
function initMenu(gag,memberType){
	var html = "<div id='menu' style='display:none;'>"; 
	if(memberType=="Common"){
		html+= "<div class='menu' type='MemberType' value='Assistant' >设置为助手</div>";
	}else{
		html+= "<div class='menu' type='MemberType' value='Common' >取消助手</div>";
	}
	if(gag>0){// 关闭禁言
		html+= "<div class='menu' type='Gag' value='0' >取消禁言</div>";
	}else{// 开启禁言
		html+= "<div class='menu' type='Gag' value='1' >禁言</div>";
	}
	html+="<div class='menu' type='Prohibit' value='1'  >禁止加入</div>";
	html+="</div>";
	return html;
}

// 执行右键菜单点击事件
function executeEvent(type,value,memberId,chatRoomId,callback){
	var dc = {};
	dc.Type = type;
	dc.Value = value;
	dc.MemberID = memberId;
	dc.ChatRoomID = chatRoomId;
	Server.sendRequest('ChatRoomMemberFront.save', dc, function(response) {
		if (response.Status != 1) {
			console.log(response.Message);
		} else {
			wsOnlineMembers(chatRoomId);
		}
	});
}
// 关闭右键菜单，很简单
window.onclick=function(e){
		// 用户触发click事件就可以关闭了，因为绑定在window上，按事件冒泡处理，不会影响菜单的功能
　　　　$("#menu").remove();
}
