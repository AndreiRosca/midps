<?php
	
	class MessageModel {
		private $message;
		
		public function __construct($message) {
			$this->message = $message;
		}
		
		public function getId() {
			return $this->message->getId();
		}
		
		public function setId($id) {
			$this->message->setId($id);
		}
		
		public function getTopicId() {
			return $this->message->getTopic()->getId();
		}
		
		public function getTopic() {
			return $this->message->getTopic();
		}
		
		public function setTopic($topic) {
			$this->message->setTopic($topic);
		}
		
		public function getMessage() {
			return $this->message->getMessage();
		}
		
		public function setMessage($message) {
			$this->message->setMessage($message);
		}
		
		public function getPostingDate() {
			return $this->message->getPostingDate();
		}
		
		public function setPostingDate($postingDate) {
			$this->message->setPostingDate($postingDate);
		}
		
		public function getAuthorCountry() {
			return $this->message->getAuthorCountry();
		}
		
		public function setAuthorCountry($authorCountry) {
			$this->message->setAuthorCountry($authorCountry);
		}
		
		public function getMessageImage() {
			return $this->message->getMessageImage();
		}
		
		public function setMessageImage($messageImage) {
			$this->message->setMessageImage($messageImage);
		}
	}

?>