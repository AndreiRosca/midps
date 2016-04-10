<?php
	
	class TopicDAO {
		private $connection;
		
		public function __construct($connection) {
			$this->connection = $connection;
		}
		
		public function findByCategory($id) {
			$sql = "SELECT t.`id`, t.`title`, t.`creation_date`, t.`author_country`, t.`category_id`, c.`name` ".
				" FROM `topics` t INNER JOIN `categories` c ON c.`id` = t.`category_id` WHERE t.`category_id` = ?";
			$statement = $this->connection->prepare($sql);
			$statement->bind_param('i', $id);
			$statement->bind_result($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry, $categoryId, $categoryName);
			$statement->execute();
			$topicList = array();
			while ($statement->fetch()) {
				$topic = new Topic($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry);
				$topic->setCategory(new Category($categoryId, $categoryName));
				$topicList[] = $topic;
			}
			$statement->close();
			return $topicList;
		}
		
		public function findById($id) {
			$sql = "SELECT t.`id`, t.`title`, t.`creation_date`, t.`author_country`, t.`category_id`, c.`name` ".
				" FROM `topics` t INNER JOIN `categories` c ON c.`id` = t.`category_id` WHERE t.`id` = ?";
			$statement = $this->connection->prepare($sql);
			$statement->bind_param('i', $id);
			$statement->bind_result($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry, $categoryId, $categoryName);
			$statement->execute();
			$topic = null;
			while ($statement->fetch()) {
				$topic = new Topic($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry);
				$topic->setCategory(new Category($categoryId, $categoryName));
			}
			$statement->close();
			return $topic;
		}
		
		public function findAll() {
			$sql = "SELECT t.`id`, t.`title`, t.`creation_date`, t.`author_country`, t.`category_id`, c.`name` ".
				" FROM `topics` t INNER JOIN `categories` c ON c.`id` = t.`category_id`";
			$statement = $this->connection->prepare($sql);
			$statement->bind_result($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry, $categoryId, $categoryName);
			$statement->execute();
			$topicList = array();
			while ($statement->fetch()) {
				$topic = new Topic($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry);
				$topic->setCategory(new Category($categoryId, $categoryName));
				$topicList[] = $topic;
			}
			$statement->close();
			return $topicList;
		}
		
		public function findNewerThan($topic, $currentCategoryId) {
			$sql = "SELECT t.`id`, t.`title`, t.`creation_date`, t.`author_country`, t.`category_id`, c.`name` ".
				" FROM `topics` t INNER JOIN `categories` c ON c.`id` = t.`category_id` WHERE t.`id` > ? AND t.`category_id` = ?";
			$statement = $this->connection->prepare($sql);
			$topicId = $topic->getId();
			$statement->bind_param('ii', $topicId, $currentCategoryId);
			$statement->bind_result($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry, $categoryId, $categoryName);
			$statement->execute();
			$topicList = array();
			while ($statement->fetch()) {
				$topic = new Topic($topicId, $topicTitle, $topicCreationDate, $topicAuthorCountry);
				$topic->setCategory(new Category($categoryId, $categoryName));
				$topicList[] = $topic;
			}
			$statement->close();
			return $topicList;
		}
		
		public function create($topic) {
			$sql = "INSERT INTO `topics` (`id`, `title`, `creation_date`, `author_country`, `category_id`) VALUES (?, ?, ?, ?, ?)";
			$statement = $this->connection->prepare($sql);
			$id = $topic->getId();
			$title = $topic->getTitle();
			$creationDate = $topic->getCreationDate();
			$authorCountry = $topic->getAuthorCountry();
			$category = $topic->getCategory();
			$categoryId = $category->getId();
			$statement->bind_param('isssi', $id, $title, $creationDate, $authorCountry, $categoryId);
			$statement->execute();
			$topic->setId($statement->insert_id);
			$statement->close();
		}
		
		public function update($topic) {
			$sql = "UPDATE `topics` SET `title` = ?, `creation_date` = ?, `author_country` = ?, `category_id` = ? WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$title = $topic->getTitle();
			$creationDate = $topic->getCreationDate();
			$authorCountry = $topic->getAuthorCountry();
			$categoryId = $topic->getCategory()->getId();
			$id = $topic->getId();
			$statement->bind_param('sssii', $title, $creationDate, $authorCountry, $categoryId, $id);
			$statement->execute();
			$statement->close();
		}
		
		public function deleteTopic($topic) {
			$sql = "DELETE FROM `topics` WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$topicId = $topic->getId();
			$statement->bind_param('i', $topicId);
			$statement->execute();
			$statement->close();
		}
		
		public function getNumberOfMessages($topic) {
			$sql = "SELECT COUNT(`id`) FROM `messages` WHERE `topic_id` = ?";
			$statement = $this->connection->prepare($sql);
			$topicId = $topic->getId();
			$statement->bind_param('i', $topicId);
			$statement->bind_result($amountOfMessages);
			$statement->execute();
			$numberOfMessages = 0;
			while ($statement->fetch()) {
				$numberOfMessages = $amountOfMessages;
			}
			$statement->close();
			return $numberOfMessages;
		}
		
		public function getLastMessage($topic) {
			$sql = "SELECT CONCAT(m.`postingDate`, ' ', m.`author_country`) FROM `topics` t " .
				"INNER JOIN `messages` m ON m.`topic_id` = t.`id` WHERE t.`id` = ? ORDER BY m.`id` DESC LIMIT 1";
			$statement = $this->connection->prepare($sql);
			$topicId = $topic->getId();
			$statement->bind_result($lastMsg);
			$statement->bind_param('i', $topicId);
			$statement->execute();
			$lastMessage = 0;
			while ($statement->fetch())
				$lastMessage = $lastMsg;
			$statement->close();
			return $lastMessage;
		}
	}
?>