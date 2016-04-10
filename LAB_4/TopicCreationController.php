<?php

	class TopicCreationController {
		
		public function handleRequest() {	
			if ($_SERVER["REQUEST_METHOD"] == "POST") {
				$request = json_decode($_POST["jsonTopicCreationRequest"]);
				$topicService = new TopicService();
				$categoryService = new CategoryService();
				$messageService = new MessageService();
				$topic = new Topic(null, $request->title, date("Y-m-d H:m:s"), $_SERVER["REMOTE_ADDR"]);
				$topic->setCategory($categoryService->find($request->categoryId));
				$topicService->create($topic);
				$message = new Message(null, $topic, $request->firstPost, date("Y-m-d H:m:s"), $_SERVER["REMOTE_ADDR"]);
				$messageService->create($message);
				$categoryService->dispose();
				$messageService->dispose();
				$topicService->dispose();
				echo json_encode($message);
			}
		}
	}

?>