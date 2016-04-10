<?php
	require("DatabaseMetaData.php");

	class DatabaseConnectionFactory {
		public static function getConnection() {
			return new mysqli(HOST, USERNAME, PASSWORD, SCHEMA);
		}
	}
?>