<?php

	class ControllerMapper {
		private $controllerMap = array();
		
		public function addController($mapping, $controller) {
			$this->controllerMap["$mapping"] = $controller;
		}
		
		public function getController($mapping) {
			return $this->controllerMap["$mapping"];
		}
	}
	
?>