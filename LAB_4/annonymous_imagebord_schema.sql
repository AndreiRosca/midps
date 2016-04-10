CREATE DATABASE `annonymous_imagebord_schema`;
use `annonymous_imagebord_schema`;

CREATE TABLE `categories` (`id` INT AUTO_INCREMENT, `name` VARCHAR(255) NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `topics` (`id` INT AUTO_INCREMENT, `title` VARCHAR(255) NOT NULL, `creation_date` TIMESTAMP NOT NULL, 
	`author_country` VARCHAR(255), `category_id` INT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`category_id`) REFERENCES `categories`(`id`));
CREATE TABLE `message_images` (`id` BIGINT AUTO_INCREMENT, `image_content` BLOB NOT NULL, PRIMARY KEY(`id`));
CREATE TABLE `messages` (`id` BIGINT NOT NULL AUTO_INCREMENT, `topic_id` INT NOT NULL, `message` VARCHAR(255) NOT NULL, `postingDate` TIMESTAMP NOT NULL,
	`author_country` VARCHAR(255), `message_image` BIGINT, PRIMARY KEY(`id`), FOREIGN KEY(`topic_id`) REFERENCES `topics`(`id`),
		FOREIGN KEY (`message_image`) REFERENCES `message_images`(`id`));
