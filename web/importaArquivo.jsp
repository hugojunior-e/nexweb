<html>
<head>
<title>Nex Web</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="imgs/NavigationNext.png"
	type="image/x-icon">

    <style type="text/css">
        #uploadStatus {
            width: 230px;
        }

        #uploadProgressBar {
            height: 14px;
            border: 1px solid #BBB;
            text-align: center;
            display: inline;
            float: left;
        }

        #uploadIndicator {
            height: 10px;
            position: relative;
            margin: 1px;
            padding: 1px;
            background: #9DC0F4;
            width: 0;
            float: left;
        }

        #uploadPercentage {
            width: 20px;
            display: inline;
            float: right;
        }
    </style>

<script type="text/javascript" src="/NexWeb/dwr/util.js"></script>
<script type="text/javascript" src="/NexWeb/dwr/engine.js"></script>
<script type="text/javascript" src="/NexWeb/dwr/interface/uploadProxy.js"></script>

<script type="text/javascript" language="JavaScript">
	var updater = null;

	function checkStatus() {
		uploadProxy.getStatus(function(stat) {
			if (stat.status == 2) {
				updateProgressBar(100);
				return;
			}

			if (stat.status == 3) {
				alert("An error has occured! " + stat.message);
				return;
			}

			if (stat.status == 4) {
				alert("An error has occured! " + stat.message);
				return;
			}

			// do something with the percentage (nice loading bar, simply show the percentage, etc)
			updateProgressBar(stat.percentComplete);
			window.setTimeout("checkStatus()", 500);
		});
	}

	function updateProgressBar(percentage) {
		// make sure you set the width style property for uploadProgressBar, otherwise progress.style.width won't work
		var progress = document.getElementById("uploadProgressBar");
		var indicator = document.getElementById("uploadIndicator");
		var maxWidth = parseIntWithPx(progress.style.width) - 4;
		var width = percentage * maxWidth / 100;
		indicator.style.width = width + "px";
		var perc = document.getElementById("uploadPercentage");
		perc.innerHTML = percentage + "%";
	}

	function parseIntWithPx(str) {
		var strArray = str.split("p");
		return parseInt(strArray[0]);
	}

	function startUploadMonitoring() {
		window.setTimeout("checkStatus()", 500);
		return true;
	}
</script>

</head>
<body>

	<iframe id='target_upload' name='target_upload' src=''
		style='display: none'></iframe>

	<form enctype="multipart/form-data" name="form" method="post"
		action="Upload" onsubmit="return startUploadMonitoring();"
		target="target_upload">
		File to upload: <input id="importFile" name="importFile" type="file">
		<br /> <input id="submitButton" type="submit" value="Upload" />
	</form>

	<div id="uploadStatus">
		<!-- specify width on this uploadProgressBar, otherwise the progress indicator won't move -->
		<div id="uploadProgressBar" style="width: 200px;">
			<div id="uploadIndicator"></div>
		</div>
		<div id="uploadPercentage"></div>
	</div>
</body>
</html>