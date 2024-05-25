<html>
<head>
<title>Nex Web</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Lucas Ezer">
<link rel="shortcut icon" href="imgs/NavigationNext.png"
	type="image/x-icon">
<script type='text/javascript' src='site.js'></script>

<script type='text/javascript'
	src='/NexWeb/dwr/interface/UValidarUsuario.js'></script>
<script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
<script type='text/javascript' src='/NexWeb/dwr/util.js'></script>

<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/core.css" rel="stylesheet" type="text/css" />
<link href="assets/css/components.css" rel="stylesheet" type="text/css" />
<link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="account-pages"></div>
	<div class="clearfix"></div>
	<div class="wrapper-page">
		<div class=" card-box">
			<div class="panel-heading">
				<h3 class="text-center">
					Logon <strong class="text-custom">NexWeb</strong>
				</h3>
			</div>


			<div class="panel-body">
				<form class="form-horizontal m-t-20" method=post name=p_form>

					<div class="form-group ">
						<div class="col-xs-12">
							<input class="form-control" type="text" required=""
								placeholder="usuario" name="usuario">
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-12">
							<input class="form-control" type="password" required=""
								placeholder="Senha" name="senha">
						</div>
					</div>


		<div class="form-group text-center m-t-40">
			<div class="col-xs-12">
				<button
					class="btn btn-info btn-block text-uppercase waves-effect waves-light"
					type="button" name="submit" onClick="jsValidaSenha()">Log In</button>
			</div>
			</div>
		</div>
		</div>
	</div>


	<script>
		UValidarUsuario.invalidaUsuario(function() {
		});
	</script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/detect.js"></script>
	<script src="assets/js/fastclick.js"></script>
	<script src="assets/js/jquery.slimscroll.js"></script>
	<script src="assets/js/jquery.blockUI.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>


	<script src="assets/js/jquery.core.js"></script>
	<script src="assets/js/jquery.app.js"></script>

</body>
</html>
