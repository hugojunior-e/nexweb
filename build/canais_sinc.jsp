<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/SincronizarCanais.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    <script>
	  var g_sinopse_id;
	  
	  function jsApagar(c)
	  {
		  SincronizarCanais.apaga(
		  c,
		  
		  function(a) {
		    alert(a);
		    jsMostrar();
		  }
		);
	  }
	  
	  
	  function jsMostrar()
	  {
		     SincronizarCanais.mostrar(
			  function(a) {
				  innerHTML('tab_sinopse', a);
			  }
			);	
	  }	  
	  
	  function jsSalvar()
	  {
			SincronizarCanais.salvar(
					p_form.p_canal1.value,
					p_form.p_canal2.value,
			  function(a) {
					alert(a);
					jsMostrar();
			  }
			);			   
	  }
	  	  
	</script>
	
    <style type="text/css">
#ID_Formulario {
	position:absolute;
	left:4046px;
	top:133px;
	width:683px;
	height:391px;
	z-index:1;
	background-color: #FFFFCC;
	border:solid #000000 3px;
}
    </style>
    	
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">
<%@include file="index_menu.jsp" %>

<hr>
<form name=p_form>
<table> 
<tr>
<td> Canal1:<br><select name="p_canal1" id="p_canal1"> <% nexcabo.UUtils.fillCombo(nexcabo.UUtils.con,out, "SELECT nome_canal a, nome_canal from t_siglas_canais order by nome_canal");  %> </select> 
</td>
<td>Canal2:<br><select name="p_canal2" id="p_canal2"> <% nexcabo.UUtils.fillCombo(nexcabo.UUtils.con,out, "SELECT nome_canal a, nome_canal from t_siglas_canais order by nome_canal");  %> </select> 
</td>
<td><br><input type=button value="Inserir Novo" onclick="jsSalvar()">
</td>
</tr>
</table>
</form>

<hr>
<div id=tab_sinopse>
<table  width="100%" class="grid">
  <tr class="caixasDialogo">
    <td  valign="top">CANAL 1</td>
    <td  valign="top">CANAL 2</td>
    <td  valign="top">#</td>
  </tr>
</table>
</div>



</body>

<script> jsMostrar(); </script>
</html>
