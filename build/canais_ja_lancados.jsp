<%@page import="nexcabo.UUtils"%>
<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/InserirProgramacoes.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    <script>
	  function jsMostraFiltro()
	  {
	    InserirProgramacoes.mostraCanaisLancados(
		  '<%= request.getParameter("mesano") %>',
		  
		  function(a) {
		    innerHTML('ID_Formulario', a);
		  }
		);
	  }
	  
	</script>
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">



<div id="ID_Formulario"><br>
    <table align="center" width=100%  class="grid">
      <tr class="caixasDialogo">
        <td >CANAL</td>
        <td >QTD DIAS</td>
        <td >MENOR DATA</td>
        <td>MAIOR DATA</td>
      </tr>
    </table>
</div>
</form>

<script>
	jsMostraFiltro();
</script>
</body>

</html>
