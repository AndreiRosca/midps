<?php

	class FetchNewTopicsController {
		
		public function handleRequest() {	
			if ($_SERVER["REQUEST_METHOD"] == "POST") {
				$request = json_decode($_POST["topicMetaData"]);
				$topicService = new TopicService();
				$topic = $topicService->findById($request->lastTopicId);
				$freshTopicList = $topicService->findNewerThan($topic, $request->categoryId);
				
				header('Content-Type: application/json');
				
				$json = array();
				foreach ($freshTopicList as $topic) {
					$json[] = array("title" => $topic->getTitle(), "date" => $topic->getCreationDate(),
					"authorCountry" => $topic->getAuthorCountry(), "numberOfMessages" => $topicService->getNumberOfMessages($topic), 
					"lastMessage" => $topicService->getLastMessage($topic), "topicId" => $topic->getId());
				}
				$topicService->dispose();
				echo json_encode($json);
			}
		}
	}

?>