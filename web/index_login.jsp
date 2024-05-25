<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />


    <script type='text/javascript' src='/NexWeb/dwr/interface/UValidarUsuario.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    
    
</head>


<body>
<%@include file="index_menu.jsp" %>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div align="center">

STATUS DA CONEXÃO COM O BANCO: <%= nexcabo.UUtils.connectNexcabo(request, out) %>

<script>

	UValidarUsuario.pegaValidacao(function(a){
		  if ( a == 'SIM' )
		  {
		  }  
		  else
		  {
			  alert('ACESSO NEGADO A ESTE PORTAL');
			  window.location.href = 'index.jsp'; 
	  	  }
	});

</script>

    
    
</div>
</body>

</html>
