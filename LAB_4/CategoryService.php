<?php
	require("DatabaseConnectionFactory.php");
	require("CategoryDAO.php");
	
	class CategoryService {
		private $connection;
		private $categoryDAO;
		
		public function __construct() {
			$this->connection = DatabaseConnectionFactory::getConnection();
			$this->categoryDAO = new CategoryDAO($this->connection);
		}
		
		public function findAll() {
			return $this->categoryDAO->findAll();
		}
		
		public function find($id) {
			return $this->categoryDAO->find($id);
		}
		
		public function getNumberOfTopics($category) {
			return $this->categoryDAO->getNumberOfTopics($category);
		}
		
		public function getNumberOfMessages($category) {
			return $this->categoryDAO->getNumberOfMessages($category);
		}
		
		public function getLastMessage($category) {
			return $this->categoryDAO->getLastMessage($category);
		}
		
		public function dispose() {
			$this->connection->close();
		}
	}
?>