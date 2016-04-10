<?php
	require("Topic.php");
	require("TopicDAO.php");
	
	class TopicService {
		private $connection;
		private $topicDAO;
		
		public function __construct() {
			$this->connection = DatabaseConnectionFactory::getConnection();
			$this->topicDAO = new TopicDAO($this->connection);
		}
		
		public function findAll() {
			return $this->topicDAO->findAll();
		}
		
		public function findNewerThan($topic, $categoryId) {
			return $this->topicDAO->findNewerThan($topic, $categoryId);
		}
		
		public function findByCategory($id) {
			return $this->topicDAO->findByCategory($id);
		}
		
		public function findById($id) {
			return $this->topicDAO->findById($id);
		}
		
		public function create($topic) {
			$this->topicDAO->create($topic);
		}
		
		public function deleteTopic($topic) {
			$this->topicDAO->deleteTopic($topic);
		}
		
		public function update($topic) {
			$this->topicDAO->update($topic);
		}
		
		public function dispose() {
			$this->connection->close();
		}
		
		public function getNumberOfMessages($topic) {
			return $this->topicDAO->getNumberOfMessages($topic);
		}
		
		public function getLastMessage($topic) {
			return $this->topicDAO->getLastMessage($topic);
		}
	}
?>