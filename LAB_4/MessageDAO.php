<?php
	
	require("Message.php");

	class MessageDAO {
		private $connection;
		
		public function __construct($connection) {
			$this->connection = $connection;
		}
		
		public function findByTopic($topic) {
			$sql = "SELECT `id`, `message`, `postingDate`, `author_country`, `message_image` FROM `messages` WHERE `topic_id` = ?";
			$statement = $this->connection->prepare($sql);
			$topicId = $topic->getId();
			$statement->bind_param('i', $topicId);
			$statement->bind_result($messageId, $messageText, $postingDate, $authorCountry, $messageImage);
			$statement->execute();
			$messageList = array();
			while ($statement->fetch()) {
				$message = new Message($messageId, $topic, $messageText, $postingDate, $authorCountry);
				$message->setMessageImage($messageImage);
				$messageList[] = $message;
			}
			$statement->close();
			return $messageList;
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
		
		public function findNewerThan($message, $topicId) {
			$sql = "SELECT `id`, `message`, `postingDate`, `author_country`, `message_image` FROM `messages` WHERE `id` > ? AND `topic_id` = ?";
			$statement = $this->connection->prepare($sql);
			$messageId = $message->getId();
			$statement->bind_param('ii', $messageId, $topicId);
			$statement->bind_result($messageId, $messageText, $postingDate, $authorCountry, $messageImage);
			$statement->execute();
			$messageList = array();
			while ($statement->fetch()) {
				$message = new Message($messageId, $topic, $messageText, $postingDate, $authorCountry);
				$message->setMessageImage($messageImage);
				$messageList[] = $message;
			}
			$statement->close();
			return $messageList;
		}
		
		public function findById($id) {
			$sql = "SELECT `id`, `message`, `postingDate`, `author_country`, `message_image` FROM `messages` WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$statement->bind_param('i', $id);
			$statement->bind_result($messageId, $messageText, $postingDate, $authorCountry, $messageImage);
			$statement->execute();
			$messageResult = null;
			while ($statement->fetch()) {
				$message = new Message($messageId, $topic, $messageText, $postingDate, $authorCountry);
				$message->setMessageImage($messageImage);
				$messageResult = $message;
			}
			$statement->close();
			return $messageResult;
		}
		
		public function create($message) {
			$sql = "INSERT INTO `messages` (`topic_id`, `message`, `postingDate`, `author_country`, message_image) VALUES (?, ?, ?, ?, ?)";
			$statement = $this->connection->prepare($sql);
			$topicId = $message->getTopic()->getId();
			$messageText = $message->getMessage();
			$postingDate = $message->getPostingDate();
			$authorCountry = $message->getAuthorCountry();
			$messageImage = $message->getMessageImage();
			$statement->bind_param('isssi', $topicId, $messageText, $postingDate, $authorCountry, $messageImage);
			$statement->execute();
			$message->setId($statement->insert_id);
			$statement->close();
		}
		
		public function update($message) {
			
		}
		
		public function deleteMessage($message) {
			$sql = "DELETE FROM `messages` WHERE `id` = ?";
			$statement = $this->connection->prepare($sql);
			$messageId = $message->getId();
			$statement->bind_param('i', $messageId);
			$statement->execute();
			$statement->close();
		}
	}
?>