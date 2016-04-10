<?php
	require("ControllerConfiguration.php");
?>

<!DOCTYPE html>
<html>
<head>
	<title>:: Topics ::</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />
	<link href="./css/style.css" type="text/css" rel="stylesheet" />
	<script src="./scripts/topicUtilities.js"></script>
</head>
<body>
	<div id="mainPageContent">
		<?php
			require("header.php");
		?>
		<table class="table table-condensed" id="topicTable">
		<th>Topic title</th><th>Creation date</th><th>Number of messages</th><th>Author country</th><th>Last message</th>
		<?php
			$categoryId = $model["categoryId"];
			$styles = array("active", "success", "warning", "danger", "info");
			$styleIndex = 0;
			foreach ($model["topics"] as $topicModel) {
				$rowStyle = $styles[$styleIndex];
				$styleIndex = $styleIndex < count($styles) - 1 ? $styleIndex + 1 : 0;
				echo "<tr class='" . $rowStyle . "'>";
				echo "<td><a href='viewTopic.php?id=" . $topicModel->getId() ."'>" . $topicModel->getTitle() . "</a></td>";
				echo "<td>" . $topicModel->getCreationDate() . "</td>" . "<td>" . $topicModel->getNumberOfMessages() . "</td>";
				echo "<td>" . $topicModel->getAuthorCountry() . "</td>" . "<td>" . $topicModel->getLastMessage() . "</td>";
				echo "</tr>";
				$categoryId = $topicModel->getCategory()->getId();
			}
		?>
	</table><br/>
	<input type="hidden" value="<?= $topicModel->getId() ?>" id="lastTopicId" />
	<input type="hidden" value="<?= $topicModel->getCategory()->getId() ?>" id="categoryId" />
	<a href="javascript:showAddTopicBox()">Add a topic</a>
	<table id="addTopicTable">
	<tr>
		<td>
			<form>
				<br/>
				<input type="hidden" id="categoryId" name="categoryId" value="<?= $categoryId ?>" />
				<table class="table table-hover" id="addTopicSection" enctype="multipart/form-data" action="#">
					<th>Topic title</th>
					<tr>
						<td><textarea class="form-control" cols="30" rows="2" id="topicTitle" name="topicTitle"></textarea></td>
					</tr>
					<td>Topic's first post</td>
					<tr>
						<td><textarea class="form-control" cols="30" rows="2" id="topicFirstPost" name="topicFirstPost"></textarea></td>
					</tr>
					<!--<tr>
						<td><input type="file" name="commentImage"  /></td>
					</tr>-->
					<tr>
						<td><input type="button" class="btn btn-primary" value="Submit" onclick="javascript:handleTopicCreation()" />
						<input type="button" class="btn btn-warning" value="Cancel" onclick="javascript:cancelTopicCreation()" /></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	</table>
	</div>
	<?php
		require("footer.php");
	?>
</body>
</html>