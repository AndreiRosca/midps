<?php
	
	require("CategoryController.php");
	require("TopicController.php");
	require("MessageController.php");
	require("ControllerMapper.php");
	require("TopicReplyController.php");
	require("TopicCreationController.php");
	require("FetchNewMessagesController.php");
	require("FetchNewTopicsController.php");
	
	function getControllerName() {
		$scriptName = explode("/", $_SERVER["SCRIPT_NAME"]);
		$controllerName = explode(".", $scriptName[count($scriptName) - 1])[0];
		return $controllerName;
	}

	function populateControllerMapper($mapper) {
		$mapper->addController("index", new CategoryController());
		$mapper->addController("topics", new TopicController());
		$mapper->addController("viewTopic", new MessageController());
		$mapper->addController("topicReplyHandler", new TopicReplyController());
		$mapper->addController("topicCreationHandler", new TopicCreationController());
		$mapper->addController("fetchNewMessagesHandler", new FetchNewMessagesController());
		$mapper->addController("fetchNewTopicsHandler", new FetchNewTopicsController());
	}
	
	$controllerMapper = new ControllerMapper();
	populateControllerMapper($controllerMapper);
	
	$controller = $controllerMapper->getController(getControllerName());
	$model = $controller->handleRequest();

?>