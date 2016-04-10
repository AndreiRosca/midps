<?php

	require("CategoryService.php");
	require("CategoryModel.php");

	class CategoryController {
		
		public function handleRequest() {
			$categoryService = new CategoryService();
			$categoryModelList = array();
			foreach ($categoryService->findAll() as $category) {
				$categoryModel = new CategoryModel($category);
				$categoryModel->setNumberOfTopics($categoryService->getNumberOfTopics($category));
				$categoryModel->setNumberOfMessages($categoryService->getNumberOfMessages($category));
				$categoryModel->setLastMessage($categoryService->getLastMessage($category));
				$categoryModelList[] = $categoryModel;
			}
			$categoryService->dispose();
			return $categoryModelList;
		}
	}

?>