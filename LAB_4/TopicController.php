<?php

	require("TopicService.php");
	require("TopicModel.php");

	class TopicController {
		
		public function handleRequest() {	
			$topicService = new TopicService();
			$topicModelList = array("categoryId" => $_GET['id'] * 1, "topics" => array());
			foreach ($topicService->findByCategory($_GET['id'] * 1) as $topic) {
				$topicModel = new TopicModel($topic);
				$topicModel->setNumberOfMessages($topicService->getNumberOfMessages($topic));
				$topicModel->setLastMessage($topicService->getLastMessage($topic));
				$topicModelList["topics"][] = $topicModel;
			}
			$topicService->dispose();
			return $topicModelList;
		}
	}

?>