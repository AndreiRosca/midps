<?php

	require("MessageDAO.php");
	//require("DatabaseConnectionFactory.php");
	
	class MessageService {
		private $connection;
		private $messageDAO;
		
		public function __construct() {
			$this->connection = DatabaseConnectionFactory::getConnection();
			$this->messageDAO = new MessageDAO($this->connection);
		}
		
		public function findNewerThan($message, $topicId) {
			return $this->messageDAO->findNewerThan($message, $topicId);
		}
		
		public function findById($id) {
			return $this->messageDAO->findById($id);
		}
		
		public function create($message) {
			$this->messageDAO->create($message);
		}
		
		public function deleteMessage($message) {
			$this->messageDAO->deleteMessage($message);
		}
		
		public function findByTopic($topic) {
			return $this->messageDAO->findByTopic($topic);
		}
		
		public function dispose() {
			$this->connection->close();
		}
	}
?>