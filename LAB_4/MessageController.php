<?php

	require("MessageService.php");
	require("MessageModel.php");

	class MessageController {
		
		public function handleRequest() {		
			$messageService = new MessageService();
			$topicService = new TopicService();
			$messageModelList = array();
			foreach ($messageService->findByTopic($topicService->findById($_GET['id'] * 1)) as $message) {
				$messageModel = new MessageModel($message);
				$messageModelList[] = $messageModel;
			}
			$messageService->dispose();
			$topicService->dispose();
			return $messageModelList;
		}
	}

?>