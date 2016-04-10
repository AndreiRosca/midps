<?php
	
	class Topic {
		private $id;
		private $title;
		private $creationDate;
		private $authorCountry;
		private $category = null;
		private $messages = array();
		
		public function __construct($id, $title, $creationDate, $authorCountry) {
			$this->id = $id;
			$this->title = $title;
			$this->creationDate = $creationDate;
			$this->authorCountry = $authorCountry;
		}
		
		public function getId() {
			return $this->id;
		}
		
		public function setId($id) {
			$this->id = $id;
		}
		
		public function getTitle() {
			return $this->title;
		}
		
		public function setTitle($title) {
			$this->title = $title;
		}
		
		public function getCreationDate() {
			return $this->creationDate;
		}
		
		public function setCreationDate($creationDate) {
			$this->creationDate = $creationDate;
		}
		
		public function getAuthorCountry() {
			return $this->authorCountry;
		}
		
		public function setAuthorCountry($authorCountry) {
			$this->authorCountry = $authorCountry;
		}
		
		public function getCategory() {
			return $this->category;
		}
		
		public function setCategory($category) {
			$this->category = $category;
		}
		
		public function getMessages() {
			return $this->messages;
		}
		
		public function setMessages($messages) {
			$this->messages = $messages;
		}
		
		public function addMessage($message) {
			;
		}
	}

?>