<?php

	class FetchNewMessagesController {
		
		public function handleRequest() {	
			if ($_SERVER["REQUEST_METHOD"] == "POST") {
				$request = json_decode($_POST["messageMetaData"]);
				$messageService = new MessageService();
				$message = $messageService->findById($request->lastMessageId);
				$freshMessageList = $messageService->findNewerThan($message, $request->topicId);
				$messageService->dispose();
				header('Content-Type: application/json');
				
				$json = array();
				foreach ($freshMessageList as $message) {
					$json[] = array("message" => $message->getMessage(), "date" => $message->getPostingDate(),
					"authorCountry" => $message->getAuthorCountry(), "topic" => array(), "messageId" => $message->getId());
				}
				
				echo json_encode($json);
			}
		}
	}

?>