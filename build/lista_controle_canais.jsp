<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/UCanais.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    <script>
	  function jsListaControleCanais()
	  {
		  UCanais.listarControleCanais( p_form.periodo.value,
				  function(a) {
					  innerHTML('tab_canais', a);
				  }
				);
	  }	  


	  function jsMarcarControle(sigla, periodo, flag) 
	  {
		  UCanais.marcarControle(sigla, periodo, flag, 
				  function(a) {
					 // alert(a);
					  jsListaControleCanais();
				  }
				);		  
	  }


	  function SomarData(txtData,DiasAdd) 
	  {
	  		var d = new Date();
  			d.setTime(Date.parse(txtData.split("/").reverse().join("/"))+(86400000*(DiasAdd)));
	  		
	  		// Crio a var da DataFinal			
	  		var DataFinal;

	  		// Aqui comparo o dia no objeto d.getDate() e vejo se é menor que dia 10.			
	  		if(d.getDate() < 10)
	  		{
	  			// Se o dia for menor que 10 eu coloca o zero no inicio
	  			// e depois transformo em string com o toString()
	  			// para o zero ser reconhecido como uma string e não
	  			// como um número.
	  			DataFinal = "0" + d.getDate().toString();
	  		}
	  		else
	  		{	
	  			// Aqui a mesma coisa, porém se a data for maior do que 10
	  			// não tenho necessidade de colocar um zero na frente.
	  			DataFinal = d.getDate().toString();	
	  		}
	  		
	  		// Aqui, já com a soma do mês, vejo se é menor do que 10
	  		// se for coloco o zero ou não.
	  		if((d.getMonth()+1) < 10){
	  			DataFinal += "/" +  "0" + (d.getMonth()+1).toString()+ "/" + d.getFullYear().toString();;
	  		}
	  		else
	  		{
	  			DataFinal += "/" + (d.getMonth()+1).toString() + "/" + d.getFullYear().toString();
	  		}
	  		return DataFinal;
	  }	  
	  

	  function jsAltera(valor)
	  {
		  var info = SomarData(p_form.periodo.value, valor);
		  p_form.periodo.value = info;
		  jsListaControleCanais();
	  }
	  	  
	</script>
    
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">

<form method="post" name="p_form">

<table class="grid" width=200>
  <tr>
    <td>
      Dia:<input type=text name=periodo value="21/09/2013" size=10>
      <img src="imgs/cal_reverse.gif" OnClick="jsAltera(-1)"><img src="imgs/cal_forward.gif" OnClick="jsAltera(1)">
    </td>
    </tr>
</table>

<div id=tab_canais>
<table class="grid" width=200>
  <tr class="caixasDialogo">
    <td width="10">#</td>
    <td width="50">Data/Hora</td>
    <td>CANAL</td>
    </tr>
</table>
</div>
</form>

<script> 
  hoje = new Date();
  dia = hoje.getDate();
  mes = hoje.getMonth();
  ano = hoje.getFullYear();
  if (dia < 10)
    dia = "0" + dia;
  if (ano < 2000)
    ano = "19" + ano;

  p_form.periodo.value = dia+"/"+(mes+1)+"/"+ano;
  jsListaControleCanais(); 
</script>

</body>

</html>
