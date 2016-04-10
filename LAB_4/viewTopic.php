<?php
	require("ControllerConfiguration.php");
?>

<!DOCTYPE html>
<html>
<head>
	<title>:: Messages ::</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous" />
	<link type="text/css" rel="stylesheet" href="./css/style.css" />
	<script src="./scripts/replyUtilities.js"></script>
</head>
<body>
	<div id="mainPageContent">
	<?php
		require("header.php");
	?>
	<table class="table table-condensed" id="messageTable">
		<th>Message</th><th>Posting date</th><th>Author country</th>
		<?php
			$topicId = null;
			$styles = array("active", "success", "warning", "danger", "info");
			$styleIndex = 0;
			foreach ($model as $messageModel) {
				$rowStyle = $styles[$styleIndex];
				$styleIndex = $styleIndex < count($styles) ? $styleIndex + 1 : 0;
				echo "<tr class='" . $rowStyle . "'>";
				echo "<td>" . $messageModel->getMessage() . "</td>";
				echo "<td>" . $messageModel->getPostingDate() . "</td>" . "<td>" . $messageModel->getAuthorCountry() . "</td>";
				echo "</tr>";
				$topicId = $messageModel->getTopicId();
			}
		?>
	</table><br/>
	<input type="hidden" value="<?= $messageModel->getId() ?>" id="lastMessageId" />
	<input type="hidden" value="<?= $messageModel->getTopic()->getId() ?>" id="topicId" />
	<a href="javascript:showReplyBox()">Post a reply</a>
	<table id="addTopicTable">
	<tr>
		<td>
			<form>
				<br/>
				<input type="hidden" id="topicId" name="topicId" value="<?= $topicId ?>" />
				<table class="table table-hover" id="replySection" enctype="multipart/form-data" action="#">
					<th>Comment</th>
					<tr>
						<td><textarea class="form-control" cols="30" rows="2" id="messageReplyContent" name="comment"></textarea></td>
					</tr>
					<tr>
						<td><input type="file" name="commentImage" /></td>
					</tr>
					<tr>
						<td><input class="btn btn-primary" type="button" value="Submit" onclick="javascript:handleMessageReply()" />
						<input class="btn btn-warning" type="button" value="Cancel" onclick="javascript:cancelReplyBox()" /></td>
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