<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/InserirProgramacoes.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    <script>
	  var g_sinopse_id;
	  
	  function jsApagar()
	  {
	    InserirProgramacoes.apagarProgramacao(
		  p_form.p_mesano.value,
		  p_form.p_canal.value,
		  
		  function(a) {
		    alert(a);
		  }
		);
	  }
	  
	  function jsLimpar()
	  {
			p_form.P_BLOCO.value  = '';     
			p_form.P_NRO.value  = '';       
			p_form.P_TIT_ORIG.value  = '';  
			p_form.P_TIT_POR.value  = '';   
			p_form.P_DURACAO.value  = '';   
			p_form.P_ANO.value  = '';       
			p_form.P_CLASSIFI.value  = '';  
			p_form.P_DIRETOR.value  = '';   
			p_form.P_ELENCO.value  = '';    
			p_form.P_GENERO.value  = '';    
			p_form.P_HORARIO.value  = '';   
			p_form.P_IDIOMA.value  = '';    
			p_form.P_JUSTIFI.value  = '';   
			p_form.P_PROGRAMA.value  = '';  
			p_form.P_ROTEIRO.value  = '';   
			p_form.P_S_CURTA.value  = '';   
			p_form.P_S_LONGA.value  = '';   
	  }
	  
	  function jsQtd(valor)
	  {
	    return (valor.value == '') ? 0 : valor.value.split('\n').length;
	  }
	  
	  function jsConferir()
	  {
	        var texto = '';
			texto += "\nP_BLOCO="     + jsQtd(p_form.P_BLOCO);
			texto += "\nP_NRO="       + jsQtd(p_form.P_NRO);
			texto += "\nP_TIT_ORIG="  + jsQtd(p_form.P_TIT_ORIG);
			texto += "\nP_TIT_POR="   + jsQtd(p_form.P_TIT_POR);
			texto += "\nP_DURACAO="   + jsQtd(p_form.P_DURACAO);
			texto += "\nP_ANO="       + jsQtd(p_form.P_ANO);
			texto += "\nP_CLASSIFI="  + jsQtd(p_form.P_CLASSIFI);
			texto += "\nP_DIRETOR="   + jsQtd(p_form.P_DIRETOR);
			texto += "\nP_ELENCO="    + jsQtd(p_form.P_ELENCO);
			texto += "\nP_GENERO="    + jsQtd(p_form.P_GENERO);
			texto += "\nP_HORARIO="   + jsQtd(p_form.P_HORARIO);
			texto += "\nP_IDIOMA="    + jsQtd(p_form.P_IDIOMA);
			texto += "\nP_JUSTIFI="   + jsQtd(p_form.P_JUSTIFI);
			texto += "\nP_PROGRAMA="  + jsQtd(p_form.P_PROGRAMA);
			texto += "\nP_ROTEIRO="   + jsQtd(p_form.P_ROTEIRO);
			texto += "\nP_S_CURTA="   + jsQtd(p_form.P_S_CURTA);
			texto += "\nP_S_LONGA="   + jsQtd(p_form.P_S_LONGA);
			
			document.all.id_conferir.innerHTML = "<pre>" + texto + "</pre>";
	  }	  
	  
	  function jsArquivoSimples()
	  {
			nome_arq = prompt('Digite o nome do arquivo');
			InserirProgramacoes.importarArquivo(
			  1,
			  nome_arq,
			  function(a) {
				p_form.P_HORARIO.value   = a[0];   
				p_form.P_PROGRAMA.value  = a[1];  
				p_form.P_GENERO.value    = a[2];   	  
				p_form.P_CLASSIFI.value  = a[3];   	  
				p_form.P_ANO.value       = a[4];   	  
				p_form.P_DIRETOR.value   = a[5];   	  
				p_form.P_ELENCO.value    = a[6];   	  
				p_form.P_S_LONGA.value   = a[7];   	  
			  }
			);	
	  }
	  
	  function jsArquivoComposto()
	  {
			nome_arq = prompt('Digite o nome do arquivo');
			InserirProgramacoes.importarArquivo(
			  2,
			  nome_arq,
			  function(a) {
				p_form.P_HORARIO.value   = a[0];   
				p_form.P_PROGRAMA.value  = a[1];
			  }
			);	
	  }	  
	  
	  function jsBuscarSinopse()
	  {
			nome_arq = prompt('Digite o nome do arquivo');
			InserirProgramacoes.buscarSinopse(
			  nome_arq,
			  p_form.P_PROGRAMA.value,
			  function(a) {
					p_form.P_DIRETOR.value   = a[0];   
					p_form.P_ROTEIRO.value   = a[1];   
					p_form.P_ELENCO.value    = a[2];    
					p_form.P_CLASSIFI.value  = a[3];  
					p_form.P_TIT_ORIG.value  = a[4]; 
					p_form.P_TIT_POR.value   = a[5];   
					p_form.P_ANO.value       = a[6];
					p_form.P_GENERO.value    = a[7]; 
					p_form.P_S_LONGA.value   = a[8];    
					p_form.P_S_CURTA.value   = a[9];   		 
			  }
			);			   
	  }
	  
	  function jsSalvar()
	  {
			InserirProgramacoes.salvar(
                p_form.p_mesano.value,
		        p_form.p_canal.value,			
                p_form.P_HORARIO.value   ,
				p_form.P_PROGRAMA.value	 ,		
				p_form.P_DIRETOR.value   ,   
				p_form.P_ROTEIRO.value   ,   
				p_form.P_ELENCO.value    ,    
				p_form.P_CLASSIFI.value  ,  
				p_form.P_TIT_ORIG.value  , 
				p_form.P_TIT_POR.value   ,   
				p_form.P_ANO.value       ,
				p_form.P_GENERO.value    , 
				p_form.P_S_LONGA.value   ,    
				p_form.P_S_CURTA.value   ,  			
			    function(a) {
					alert(a);		 
			  }
			);		  
	  }

	  function jsCanaisCadastrados()
	  {
			window.open('canais_ja_lancados.jsp?mesano=' + p_form.p_mesano.value);
	  }


	  function jsEfetuarCorrecoes()
	  {
		  InserirProgramacoes.jsEfetuarCorrecoes(function(a) {
                       alert(a);
			  });
	  }
	  	  	  
	</script>
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">
<%@include file="index_menu.jsp" %>
<form method="post" name="p_form">

<table width="100%" align="center">
  <tr>
    <td colspan="5" align="center" class="caixasDialogo">FILTRO</td>
  </tr>
  <tr>
    <td width="65" align="right">Mes/Ano</td>
    <td width="146"><input type="text" name="p_mesano" id="p_mesano"></td>
    <td width="74" align="right">Canal</td>
    <td width="167"><select name="p_canal" id="p_canal"> <% nexcabo.UUtils.fillCombo(nexcabo.UUtils.con,out, "SELECT nome_canal a, nome_canal from t_siglas_canais order by nome_canal");  %> </select> </td>
    <td width="858"><input type="button" name="button8" id="button8" value="Apagar Canal" onClick="jsApagar()">
    &nbsp;&nbsp; &nbsp;&nbsp;
<input type="button" name="button6" id="button6" value="Arquivo Simples" onClick="jsArquivoSimples()">
<input type="button" name="button7" id="button7" value="Arquivo Geral" onClick="jsArquivoComposto()">
<input type="button" name="button5" id="button5" value="Buscar Sinopse" onClick="jsBuscarSinopse()">
 &nbsp;&nbsp; &nbsp;
 <input type="button" name="button" id="button" value="Salvar Na Base" onClick="jsSalvar()"> 
 &nbsp;&nbsp;&nbsp;
 <input type="button" name="button9" id="button9" value="Conferir Quantidades" onClick="jsConferir()">
<input type="button" name="button10" id="button10" value="Limpar" onClick="jsLimpar()">
<input type="button" name="button10" id="button10" value="Conferir Canais Cadastrados" onClick="jsCanaisCadastrados()">
<input type="button" name="button10" id="button10" value="Efetuar Correções" onClick="jsEfetuarCorrecoes()">
</td>
  </tr>
</table>


<table>
  <tr class="caixasDialogo">
    <td width="137" valign="top">DURACAO</td>
    <td width="137" valign="top">TIT_POR</td>
    <td width="137" valign="top">NRO_EPISODIO</td>
    <td width="137" valign="top">BLOCO</td>
    <td width="137" valign="top">TIT_ORIG</td>
    <td width="137" valign="top">&nbsp;</td>
    <td width="140" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">
      <textarea name="P_DURACAO" size="10" id="P_DURACAO" rows="10" cols="20" ></textarea>    </td>
        
      <td valign="top"><textarea name="P_TIT_POR" size="10" id="P_TIT_POR" rows="10" cols="20" ></textarea></td>
      <td valign="top"><textarea name="P_NRO" size="10" id="P_NRO" rows="10" cols="20" ></textarea></td>
      <td valign="top"><textarea name="P_BLOCO" size="10" id="P_BLOCO" rows="10" cols="20" ></textarea></td>
      <td valign="top"><textarea name="P_TIT_ORIG" size="10" id="P_TIT_ORIG" rows="10" cols="20" ></textarea></td>
      <td valign="top">&nbsp;</td>
      <td rowspan="5" valign="top" id=id_conferir>&nbsp;</td>
  </tr>
  <tr class="caixasDialogo">
    <td valign="top">PROGRAMA</td>
    <td valign="top">CLASSIFI</td>
    <td valign="top">JUSTIFI</td>
    <td valign="top">GENERO</td>
    <td valign="top">ANO</td>
    <td valign="top">ROTEIRO</td>
    </tr>  
  <tr>
    <td valign="top"><textarea name="P_PROGRAMA" size="10" id="P_PROGRAMA" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_CLASSIFI" size="10" id="P_CLASSIFI" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_JUSTIFI" size="10" id="P_JUSTIFI" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_GENERO" size="10" id="P_GENERO" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_ANO" size="10" id="P_ANO" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_ROTEIRO" size="10" id="P_ROTEIRO" rows="10" cols="20" ></textarea></td>
    </tr>
  <tr class="caixasDialogo">
    <td valign="top">HORARIO</td>
    <td valign="top">SIN_CURTA</td>
    <td valign="top">ELENCO</td>
    <td valign="top">SIN_LONGA</td>
    <td valign="top">DIRETOR</td>
    <td valign="top">IDIOMA</td>
    </tr>
  <tr>
    <td valign="top"><textarea name="P_HORARIO" size="10" id="P_HORARIO" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_S_CURTA" size="10" id="P_S_CURTA" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_ELENCO" size="10" id="P_ELENCO" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_S_LONGA" size="10" id="P_S_LONGA" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_DIRETOR" size="10" id="P_DIRETOR" rows="10" cols="20" ></textarea></td>
    <td valign="top"><textarea name="P_IDIOMA" size="10" id="P_IDIOMA" rows="10" cols="20" ></textarea></td>
    </tr>
</table>
</form>




</body>

</html>
