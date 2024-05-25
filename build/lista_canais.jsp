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
	  var g_sigla;
      var g_linha;
	  
	  function jsListar()
	  {
	    UCanais.listaCanais(
		  function(a) {
		    innerHTML('tab_canais', a);
		  }
		);
		document.all.mensagem.innerHTML = 'Normal';
	  }
	  
	  function jsSalvar()
	  {
	    if (document.all.mensagem.innerHTML == 'Normal')
		{
		  alert('Voce não esta nem inserindo e nem editando...');
		  return;
		}
		
	    UCanais.salvarCanal(
		  g_sigla,
 	      p_form.SIGLA.value,
	      p_form.NOME_CANAL.value,
	      p_form.CONTATO.value,
	      p_form.TELEFONE.value,
	      p_form.EMAIL.value,
	      p_form.SITE.value,
	      p_form.USUARIO.value,	
	      p_form.SENHA.value,		  
		  function(a) {
		    alert(a);
			if (g_sigla != '---')
			{
	          g_linha.cells[1].innerHTML = p_form.SIGLA.value;
	          g_linha.cells[2].innerHTML = p_form.NOME_CANAL.value;
	          g_linha.cells[3].innerHTML = p_form.CONTATO.value;
	          g_linha.cells[4].innerHTML = p_form.TELEFONE.value;
	          g_linha.cells[5].innerHTML = p_form.EMAIL.value;
	          g_linha.cells[6].innerHTML = p_form.SITE.value;	
	          g_linha.cells[7].innerHTML = p_form.USUARIO.value;
	          g_linha.cells[8].innerHTML = p_form.SENHA.value;				
			}  
			
			document.all.mensagem.innerHTML = 'Normal';
		  }
		);
		
	  }
	  
	  function jsAtivar(sigla)
	  {
	     g_sigla                 = sigla;
         g_linha                 = document.getElementById(sigla);
	     p_form.SIGLA.value      = g_linha.cells[1].innerHTML;
	     p_form.NOME_CANAL.value = g_linha.cells[2].innerHTML;
	     p_form.CONTATO.value    = g_linha.cells[3].innerHTML;
	     p_form.TELEFONE.value   = g_linha.cells[4].innerHTML;
	     p_form.EMAIL.value      = g_linha.cells[5].innerHTML;
	     p_form.SITE.value       = g_linha.cells[6].innerHTML;
	     p_form.USUARIO.value    = g_linha.cells[7].innerHTML;
	     p_form.SENHA.value      = g_linha.cells[8].innerHTML;
		 document.all.mensagem.innerHTML = 'Atualizando registro...';
	  }


	  function jsApagar(sigla)
	  {
		  UCanais.apagarCanal(
				  sigla,	  
				  function(a) {
				    alert(a);
				    jsListar();
				  }
				);
	  }	 


	  function jsMover(sigla, ordem)
	  {
		  UCanais.moverRegistro(
				  sigla,
				  ordem,	  
				  function(a) {
					  if (a.substr(0,4) == 'ERRO')
						  alert(a);
						  
					  else
				        jsListar();
				  }
				);
	  }	 
	   
	  
	  function jsCancelar()
	  {
	     document.all.mensagem.innerHTML = 'Normal';
	     p_form.SIGLA.value      = '';
	     p_form.NOME_CANAL.value = '';
	     p_form.CONTATO.value    = '';
	     p_form.TELEFONE.value   = '';
	     p_form.EMAIL.value      = '';
	     p_form.SITE.value       = '';
	     p_form.USUARIO.value    = '';	
	     p_form.SENHA.value    = '';		
	  }
	  
	  function jsNovo()
	  {
	     g_sigla                 = '---';
         g_linha                 = null;
	     p_form.SIGLA.value      = '';
	     p_form.NOME_CANAL.value = '';
	     p_form.CONTATO.value    = '';
	     p_form.TELEFONE.value   = '';
	     p_form.EMAIL.value      = '';
	     p_form.SITE.value       = '';
	     p_form.USUARIO.value    = '';
	     p_form.SENHA.value      = '';
		 document.all.mensagem.innerHTML = 'Inserindo Novo Registro...';
	  }	  


	  function jsMostrar()
	  {
		  document.all.t_filtro.style.visibility = 'hidden';
		  document.all.t_filtro.style.height = '0';
	  }
	  	  
	</script>
    
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">
<%@include file="index_menu.jsp" %>
<form method="post" name="p_form">
<table width="400" align="center" id=t_filtro>
  <tr>
    <td colspan="2" align="center" class="caixasDialogo">FILTRO</td>
  </tr>
    <tr><td>SIGLA</td><td><input name=SIGLA type=text id="SIGLA"></td></tr>
    <tr><td>NOME_CANAL</td><td><input name=NOME_CANAL type=text id="NOME_CANAL"  ></td></tr>
    <tr><td>CONTATO</td><td><input name=CONTATO type=text id="CONTATO"  ></td></tr>
    <tr><td>TELEFONE</td><td><input name=TELEFONE type=text id="TELEFONE"   ></td></tr>
    <tr><td>EMAIL</td><td><input name=EMAIL type=text id="EMAIL"    ></td>
    <tr><td>SITE</td><td><input name=SITE type=text id="SITE"    ></td>
    <tr><td>USUARIO</td><td><input name=USUARIO type=text id="USUARIO"    ></td>
    <tr><td>SENHA</td><td><input name=SENHA type=text id="SENHA"    ></td>
    </tr>

  <tr>
    <td width="109"><input type="button" name="button7" id="button7" value="Novo" onClick="jsNovo()"></td>
    <td width="279"><input type="button" name="button6" id="button6" value="Listar" onClick="jsListar()">
      <input type="button" name="button8" id="button8" value="Salvar" onClick="jsSalvar()">
      <input type="button" name="button9" id="button9" value="Cancelar" onClick="jsCancelar()"></td>
    </tr>
  <tr>
    <td colspan="2" align="center" id=mensagem>Normal</td>
    </tr>
</table>
</form>

<div id=tab_canais>
<table width="100%" class="grid">
  <tr class="caixasDialogo">
    <td width="10%">#</td>
    <td width="11%">SIGLA</td>
    <td width="11%">NOME_CANAL</td>
    <td width="11%">CONTATO</td>
    <td width="11%">TELEFONE</td>
    <td width="11%">EMAIL</td>
    <td width="11%">SITE</td>
    <td width="11%">USUARIO</td>
    <td width="11%">SENHA</td>
    </tr>
</table>
</div>



</body>

</html>
