<?php
	class CategoryModel {
		private $category;
		private $numberOfTopics;
		private $numberOfMessages;
		private $lastMessage;
		
		public function __construct($category) {
			$this->category = $category;
		}
		
		public function getId() {
			return $this->category->getId();
		}
		
		public function setId($id) {
			$this->category->setId($id);
		}
		
		public function getName() {
			return $this->category->getName();
		}
		
		public function setName($name) {
			$this->category->setName($name);
		}
		
		public function getNumberOfTopics() {
			return $this->numberOfTopics;
		}
		
		public function setNumberOfTopics($numberOfTopics) {
			$this->numberOfTopics = $numberOfTopics;
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