<?php

	require("Category.php");

	class CategoryDAO {
		private $connection;
		
		public function __construct($connection) {
			$this->connection = $connection;
		}
		
		public function find($id) {
			$sql = "SELECT `id`, `name` FROM `categories` WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$statement->bind_param('i', $id);
			$statement->execute();
			$statement->bind_result($categoryId, $categoryName);
			$category = null;
			while ($statement->fetch()) 
				$category = new Category($categoryId, $categoryName);
			$statement->close();
			return $category;
		}
		
		public function findAll() {
			$sql = "SELECT `id`, `name` FROM `categories`";
			$statement = $this->connection->prepare($sql);
			$statement->bind_result($id, $name);
			$statement->execute();
			$categoryList = array();
			while ($statement->fetch())
				$categoryList[] = new Category($id, $name);
			$statement->close();
			return $categoryList;
		}
		
		public function create($category) {
			$sql = "INSERT INTO `categories` (`name`) VALUES (?)";
			$statement = $this->connection->prepare($sql);
			$categoryName = $category->getName();
			$statement->bind_param('s', $categoryName);
			$statement->execute();
			$category->setId($statement->insert_id);
			$statement->close();
		}
		
		public function update($category) {
			$sql = "UPDATE `categories` SET `name` = ? WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$categoryName = $category->getName();
			$categoryId = $category->getId();
			$statement->bind_param('si', $categoryName, $categoryId);
			$statement->execute();
			$statement->close();
		}
		
		public function deleteCategory($category) {
			$sql = "DELETE FROM `categories` WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$categoryId = $category->getId();
			$statement->bind_param('i', $categoryId);
			$statement->execute();
			$statement->close();
		}
		
		public function getNumberOfTopics($category) {
			$sql = "SELECT COUNT(`category_id`) FROM `topics` WHERE `category_id` = ?";
			$statement = $this->connection->prepare($sql);
			$categoryId = $category->getId();
			$statement->bind_result($amountOfTopics);
			$statement->bind_param('i', $categoryId);
			$statement->execute();
			$numberOfTopics = 0;
			while ($statement->fetch())
				$numberOfTopics = $amountOfTopics;
			$statement->close();
			return $numberOfTopics;
		}
		
		public function getNumberOfMessages($category) {
			$sql = "SELECT COUNT(c.`id`) FROM `categories` c INNER JOIN `topics` t ON c.`id` = t.`category_id` " .
				"INNER JOIN `messages` m ON m.`topic_id` = t.`id` WHERE c.`id` = ?";
			$statement = $this->connection->prepare($sql);
			$categoryId = $category->getId();
			$statement->bind_result($amountOfMessages);
			$statement->bind_param('i', $categoryId);
			$statement->execute();
			$numberOfMessages = 0;
			while ($statement->fetch())
				$numberOfMessages = $amountOfMessages;
			$statement->close();
			return $numberOfMessages;
		}
		
		public function getLastMessage($category) {
			$sql = "SELECT CONCAT(m.`postingDate`, ' ', m.`author_country`) FROM `categories` c INNER JOIN `topics` t " .
				" ON c.`id` = t.`category_id` INNER JOIN `messages` m ON m.`topic_id` = t.`id` WHERE c.`id` = ? ORDER BY m.`id` DESC LIMIT 1";
			$statement = $this->connection->prepare($sql);
			$categoryId = $category->getId();
			$statement->bind_result($lastMsg);
			$statement->bind_param('i', $categoryId);
			$statement->execute();
			$lastMessage = 0;
			while ($statement->fetch())
				$lastMessage = $lastMsg;
			$statement->close();
			return $lastMessage;
		}
	}
?>