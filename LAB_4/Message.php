<?php
	
	class Message {
		private $id;
		private $topic;
		private $message;
		private $postingDate;
		private $authorCountry = null;
		private $messageImage = null;
		
		public function __construct($id, $topic, $message, $postingDate, $authorCountry) {
			$this->id = $id;
			$this->topic = $topic;
			$this->message = $message;
			$this->postingDate = $postingDate;
			$this->authorCountry = $authorCountry;
		}
		
		public function getId() {
			return $this->id;
		}
		
		public function setId($id) {
			$this->id = $id;
		}
		
		public function getTopic() {
			return $this->topic;
		}
		
		public function setTopic($topic) {
			$this->topic = $topic;
		}
		
		public function getMessage() {
			return $this->message;
		}
		
		public function setMessage($message) {
			$this->message = $message;
		}
		
		public function getPostingDate() {
			return $this->postingDate;
		}
		
		public function setPostingDate($postingDate) {
			$this->postingDate = $postingDate;
		}
		
		public function getAuthorCountry() {
			return $this->authorCountry;
		}
		
		public function setAuthorCountry($authorCountry) {
			$this->authorCountry = $authorCountry;
		}
		
		public function getMessageImage() {
			return $this->messageImage;
		}
		
		public function setMessageImage($messageImage) {
			$this->messageImage = $messageImage;
		}
	}

?>