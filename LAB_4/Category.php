<?php
	class Category {
		private $id;
		private $name;
		private $topics = array();
		
		public function __construct($id, $name) {
			$this->id = $id;
			$this->name = $name;
		}
		
		public function getId() {
			return $this->id;
		}
		
		public function setId($id) {
			$this->id = $id;
		}
		
		public function getName() {
			return $this->name;
		}
		
		public function setName($name) {
			$this->name = $name;
		}
		
		public function getTopics() {
			return $this->topics;
		}
		
		public function setTopics($topics) {
			$this->topics = $topics;
		}
	}
?>