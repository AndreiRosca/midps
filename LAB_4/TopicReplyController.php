<?php

	class TopicReplyController {
		
		public function handleRequest() {	
			if ($_SERVER["REQUEST_METHOD"] == "POST") {
				$request = json_decode($_POST["jsonReplyRequest"]);
				$topicService = new TopicService();
				$messageService = new MessageService();
				$message = new Message(null, $topicService->findById($request->topicId), $request->messageContent, date("Y-m-d H:m:s"), 
					$_SERVER["REMOTE_ADDR"]);
				$messageService->create($message);
				$messageService->dispose();
				$topicService->dispose();
				header('Content-Type: application/json');
				echo json_encode(array("message" => $message->getMessage(), "date" => $message->getPostingDate(),
					"authorCountry" => $message->getAuthorCountry(), "topic" => array()));
			}
		}
	}

?>