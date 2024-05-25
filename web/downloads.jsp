<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
<style type="text/css">
<!--
.style1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 36px;
	color: #000000;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
    
</head>


<body>
    <%@include file="index_menu.jsp" %>
    <br>
    <hr>
		<form action="upload.jsp" method="post" enctype="multipart/form-data">
		  <table width="100%" cellpadding="5" cellspacing="5">
		    <tr>
		      <td align="center"><span class="style1">UPLOAD DE ARQUIVOS</span></td>
		    </tr>
		    <tr>
		      <td width="48%" align="center"><input name="arquivo" type="file" size="70" />
		      <input type="submit" value="Enviar" /></td>
		    </tr>
		  </table>
		</form>    
    <hr>
    <div align="center">
      <iframe style="width:80%; height:400; border:thin #000000 1px;" src="http://cpro14059.publiccloud.com.br/"> </iframe>
    </div>
</body>

</html>
