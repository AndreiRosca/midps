
	var TWO_SECONDS = 2000;
	var newTopicsFetchingTimer = setInterval(fetchNewTopics, TWO_SECONDS);
	
	function fetchNewTopics() {
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState == 4 && httpRequest.status == 200) {
				var topicList = JSON.parse(httpRequest.responseText);
				appendFreshTopicsToTheDOM(topicList);
			}
		};
		var lastTopicId = document.getElementById("lastTopicId").value;
		var categoryId = document.getElementById("categoryId").value;
		var request = { "lastTopicId" : lastTopicId, "categoryId" : categoryId };
		httpRequest.open("POST", "fetchNewTopicsHandler.php", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("topicMetaData=" + JSON.stringify(request));
	}
	
	
	var rowStyles = ["active", "success", "warning", "danger", "info"];
	var rowStyleIndex = 0;
	
	function appendFreshTopicsToTheDOM(topicList) {
		
		var topicTable = document.getElementById("topicTable");
		for (var i = 0; i < topicList.length; ++i) {
			var freshRow = topicTable.insertRow(-1);
			freshRow.style.className = rowStyles[rowStyleIndex];
			rowStyleIndex = (rowStyleIndex < rowStyles.length - 1) ? rowStyleIndex + 1 : 0;
			var titleColumn = freshRow.insertCell(0);
			var titleColumnLink = document.createElement("a");
			titleColumnLink.href = "viewTopic.php?id=" + topicList[i].topicId;
			titleColumnLink.appendChild(document.createTextNode(topicList[i].title));
			titleColumn.appendChild(titleColumnLink);
			
			var dateColumn = freshRow.insertCell(1);
			var dateColumnText = document.createTextNode(topicList[i].date);
			dateColumn.appendChild(dateColumnText);
			
			var numberOfMessagesColumn = freshRow.insertCell(2);
			var numberOfMessagesColumnText = document.createTextNode(topicList[i].numberOfMessages);
			numberOfMessagesColumn.appendChild(numberOfMessagesColumnText);
			
			var authorCountryColumn = freshRow.insertCell(3);
			var authorCountryColumnText = document.createTextNode(topicList[i].authorCountry);
			authorCountryColumn.appendChild(authorCountryColumnText);
			
			var lastMessageColumn = freshRow.insertCell(4);
			var lastMessageColumnText = document.createTextNode(topicList[i].lastMessage);
			lastMessageColumn.appendChild(lastMessageColumnText);
		}
		if (topicList.length > 0) {
			var lastTopicId = topicList[topicList.length - 1].topicId;
			document.getElementById("lastTopicId").value = lastTopicId;
		}
	}
	
	function stopFetchingNewTopics() {
		clearInterval(newTopicsFetchingTimer);
	}



	function showAddTopicBox() {
		document.getElementById("addTopicSection").style.display = "inline-block";
	}
	
	function cancelTopicCreation() {
		document.getElementById("addTopicSection").style.display = "none";
		document.getElementById("topicTitle").value = "";
		document.getElementById("topicFirstPost").value = "";
	}
	
	function handleTopicCreation() {
		var topicTitle = document.getElementById("topicTitle").value;
		if ((topicTitle = topicTitle.trim()).length == 0)
			return;
		var firstPost = document.getElementById("topicFirstPost").value;
		if ((firstPost = firstPost.trim()).length == 0)
			return;
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState == 4 && httpRequest.status == 200) {
				;
			}
		};
		var categoryId = document.getElementById("categoryId").value;
		var topicCreationRequest = { "title" : topicTitle, "firstPost" : firstPost, "categoryId" : categoryId,
			"firstPostImage" : null };
		httpRequest.open("POST", "topicCreationHandler.php", true);
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("jsonTopicCreationRequest=" + JSON.stringify(topicCreationRequest));
		cancelTopicCreation();
	}

	