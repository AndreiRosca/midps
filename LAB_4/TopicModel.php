<?php
	
	class TopicModel {
		private $topic;
		private $numberOfMessages;
		private $lastMessage;
		
		public function __construct($topic) {
			$this->topic = $topic;
		}
		
		public function getId() {
			return $this->topic->getId();
		}
		
		public function setId($id) {
			$this->topic->setId($id);
		}
		
		public function getTitle() {
			return $this->topic->getTitle();
		}
		
		public function setTitle($title) {
			$this->topic->setTitle($title);
		}
		
		public function getCreationDate() {
			return $this->topic->getCreationDate();
		}
		
		public function setCreationDate($creationDate) {
			$this->topic->setCreationDate($creationDate);
		}
		
		public function getAuthorCountry() {
			return $this->topic->getAuthorCountry();
		}
		
		public function setAuthorCountry($authorCountry) {
			$this->topic->setAuthorCountry($authorCountry);
		}
		
		public function getCategory() {
			return $this->topic->getCategory();
		}
		
		public function setCategory($category) {
			$this->topic->setCategory($category);
		}

		public function getNumberOfMessages() {
			return $this->numberOfMessages;
		}
		
		public function setNumberOfMessages($numberOfMessages) {
			$this->numberOfMessages = $numberOfMessages;
		}
				
		public function getLastMessage() {
			return $this->lastMessage;
		}
		
		public function setLastMessage($lastMessage) {
			$this->lastMessage = $lastMessage;
		}
	}
?>