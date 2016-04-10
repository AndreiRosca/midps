
	var TWO_SECONDS = 2000;
	var newMessagesFetchingTimer = setInterval(fetchNewMessages, TWO_SECONDS);
	
	function fetchNewMessages() {
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState == 4 && httpRequest.status == 200) {
				var messageList = JSON.parse(httpRequest.responseText);
				appendFreshMessagesToTheDOM(messageList);
			}
		};
		var lastMessageId = document.getElementById("lastMessageId").value;
		var topicId = document.getElementById("topicId").value;
		var request = { "lastMessageId" : lastMessageId, "topicId" : topicId };
		httpRequest.open("POST", "fetchNewMessagesHandler.php", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("messageMetaData=" + JSON.stringify(request));
	}
	
	function appendFreshMessagesToTheDOM(messageList) {
		var messageTable = document.getElementById("messageTable");
		for (var i = 0; i < messageList.length; ++i) {
			var freshRow = messageTable.insertRow(-1);
			var messageColumn = freshRow.insertCell(0);
			var messageColumnText = document.createTextNode(messageList[i].message);
			messageColumn.appendChild(messageColumnText);
			
			var postingDateColumn = freshRow.insertCell(1);
			var postingDateColumnText = document.createTextNode(messageList[i].date);
			postingDateColumn.appendChild(postingDateColumnText);
			
			var authorCountryColumn = freshRow.insertCell(2);
			var authorCountryColumnText = document.createTextNode(messageList[i].authorCountry);
			authorCountryColumn.appendChild(authorCountryColumnText);
		}
		if (messageList.length > 0) {
			var lastMessageId = messageList[messageList.length - 1].messageId;
			document.getElementById("lastMessageId").value = lastMessageId;
		}
	}
	
	function stopFetchingNewMessages() {
		clearInterval(newMessagesFetchingTimer);
	}

	function showReplyBox() {
		document.getElementById("replySection").style.display = "inline-block";
	}
	
	function hideReplyBox() {
		document.getElementById("replySection").style.display = "none";
	}
	
	function cancelReplyBox() {
		hideReplyBox();
		document.getElementById("messageReplyContent").value = "";
	}
	
	function handleMessageReply() {
		var topicReply = { "topicId" : null, "messageContent" : null, "messageImage" : null };
		topicReply["messageContent"] = document.getElementById("messageReplyContent").value;
		topicReply["topicId"] = document.getElementById("topicId").value;
		asynchronouslySendReply(topicReply);
		hideReplyBox();
		document.getElementById("messageReplyContent").value = "";
	}
	
	function asynchronouslySendReply(topicReply) {
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState == 4 && httpRequest.status == 200) {
				;
			}
		};
		httpRequest.open("POST", "topicReplyHandler.php", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var formDate = new FormData();
		httpRequest.send("jsonReplyRequest=" + JSON.stringify(topicReply));
	}
