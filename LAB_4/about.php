<?php
	//require("ControllerConfiguration.php");
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
			<tr>
				<td><p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p></td>
			</tr>
			<tr>
				<td>
					<table style="margin: 0 auto">
						<tr>
							<td><img src="images/utm.jpg" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div><br/>
	<?php
		require("footer.php");
	?>
</body>
</html>