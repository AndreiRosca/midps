<?php
	require("ControllerConfiguration.php");
?>

<!DOCTYPE html>
<html>
<head>
	<title>:: Annonymous Imagebord ::</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />
	<link href="./css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="mainPageContent">
		<?php
			require("header.php");
		?>
		<table class="table table-condensed" id="categoryList">
			<th>Category name</th><th>Number of topics</th><th>Number of messages</th><th>Last message</th>
			<?php
				$styles = array("active", "success", "warning", "danger", "info");
				$styleIndex = 0;
				foreach ($model as $categoryModel) {
					$rowStyle = $styles[$styleIndex];
					$styleIndex = $styleIndex < count($styles) ? $styleIndex + 1 : 0;
					echo "<tr class='" . $rowStyle . "'>";
					echo "<td><a href='topics.php?id=" . $categoryModel->getId() . "'>" . $categoryModel->getName() . "</a></td>";
					echo "<td>" . $categoryModel->getNumberOfTopics() . "</td>" . "<td>" . $categoryModel->getNumberOfMessages() . "</td>";
					echo "<td>" . $categoryModel->getLastMessage() . "</td>";
					echo "</tr>";
				}
			?>
		</table>
	</div><br/>
	<?php
		require("footer.php");
	?>
</body>
</html>