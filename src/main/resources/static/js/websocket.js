var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	var socket = new SockJS('/hello'); //构建一个SockJS对象
	stompClient = Stomp.over(socket); //用Stomp将SockJS进行协议封装
	stompClient.connect({}, function(frame) { //连接回调
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
		    showGreeting(JSON.parse(greeting.body).content);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	/*消息发送*/
	stompClient.send("/app/hello", {atytopic:"atytopic"}, JSON.stringify({
		'name' : $("#name").val()
	}));
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
	 var time = $('#danmu').data("nowTime")+1;
	 $('#danmu').danmu("addDanmu", {"text": message, "color": "green", "size": "1", "position": "0", "time": time,"isnew":""});
}

function sendMessage() {
//	stompClient.send("/app/message", {}, JSON.stringify({
//		'message' : $("#mysend").val()
//	}));
	/*消息发送*/
	stompClient.send("/app/hello", {atytopic:"atytopic"}, JSON.stringify({
		'name' : $("#mysend").val()
	}));
}

$(function() {
	connect();
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendName();
	});
	$("#mybutton").click(function() {
		sendMessage();
	});
});