<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/USinopse.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    
    <script src="/NexWeb/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="/NexWeb/jquery.maskedinput-1.3.js" type="text/javascript"></script>
    
    <script>
	  var g_sinopse_id;
	  
	  function jsMostraFiltro()
	  {
	    USinopse.mostraDadosFiltro(
		  p_form.dia1.value,
		  p_form.dia2.value,
		  p_form.canal.value,
		  p_form.mesano2.value,
		  
		  function(a) {
		    innerHTML('tab_sinopse', a);
		  }
		);
	  }

	  function jsRepeteco()
	  {
		    USinopse.criarRepeteco(
		  		  p_form.canal.value,
		  		  p_form.mesano2.value,
		  		  
		  		  function(a) {
		  		    alert(a);
		  		  }
		  		);		  
	  }
	  
	  function jsLimparFormulario()
	  {
		p_form2.CANAL.value                    = '';
		p_form2.DATA.value                     = '';
		p_form2.HORARIO.value                  = '';
		p_form2.ANO.value                      = '';
		p_form2.NRO_EPISODIO.value             = '';
		p_form2.DURACAO.value                  = '';
		p_form2.BLOCO.value                    = '';
		p_form2.PROGRAMA.value                 = '';
		p_form2.TITULO_ORIGINAL.value          = '';
		p_form2.TITULO_PORTUGUES.value         = '';
		p_form2.SINOPSE_CURTA.value            = '';
		p_form2.SINOPSE_LONGA.value            = '';
		p_form2.DIRETOR.value                  = '';
		p_form2.ELENCO.value                   = '';
		p_form2.IDIOMA.value                   = '';
		p_form2.CLASSIFICACAO.value            = '';
		p_form2.JUSTIFICATIVA.value            = '';
		p_form2.GENERO.value                   = '';
		p_form2.ROTEIRO.value                  = '';
		p_form2.MESANO.value                   = '';
		p_form2.DATA_CORRETA.value             = '';	  
	  }
	  
	  function jsEdita(a)
	  {
		jsLimparFormulario();
		g_sinopse_id = a;
		
	    USinopse.mostraRegistro(
		  g_sinopse_id,
		  function(dados) {
				p_form2.CANAL.value            = dados[1];
				p_form2.DATA.value             = dados[2];
				p_form2.HORARIO.value          = dados[3];
				p_form2.ANO.value              = dados[4];
				p_form2.NRO_EPISODIO.value     = dados[5];
				p_form2.DURACAO.value          = dados[6];
				p_form2.BLOCO.value            = dados[7];
				p_form2.PROGRAMA.value         = dados[8];
				p_form2.TITULO_ORIGINAL.value  = dados[9];
				p_form2.TITULO_PORTUGUES.value = dados[10];
				p_form2.SINOPSE_CURTA.value    = dados[11];
				p_form2.SINOPSE_LONGA.value    = dados[12];
				p_form2.DIRETOR.value          = dados[13];
				p_form2.ELENCO.value           = dados[14];
				p_form2.IDIOMA.value           = dados[15];
				p_form2.CLASSIFICACAO.value    = dados[16];
				p_form2.JUSTIFICATIVA.value    = dados[17];
				p_form2.GENERO.value           = dados[18];
				p_form2.ROTEIRO.value          = dados[19];
				p_form2.MESANO.value           = dados[20];
				p_form2.DATA_CORRETA.value     = dados[21];			  
		        mostraArea("ID_Formulario");
		  }
		 );  
	  }
	  
  
	  function jsNovo()
	  {
		jsLimparFormulario();
		g_sinopse_id         = -1;
		p_form2.CANAL.value  = p_form.canal.value;
		p_form2.MESANO.value = p_form.mesano2.value;
	    mostraArea("ID_Formulario");
	  }
	  
	  function jsApaga(a)
	  {
		 USinopse.apagarRegistro(
				a,
				function(x) {
				  alert(x);
				  document.getElementById('linha' + a).style.visibility = 'hidden';
				}
		 );		
	  }	  
	  
	  function jsSalvar()
	  {
			USinopse.salvarRegistro(
				g_sinopse_id,
				p_form2.CANAL.value,
				p_form2.DATA.value,
				p_form2.HORARIO.value,
				p_form2.ANO.value,
				p_form2.NRO_EPISODIO.value,
				p_form2.DURACAO.value,
				p_form2.BLOCO.value,
				p_form2.PROGRAMA.value,
				p_form2.TITULO_ORIGINAL.value,
				p_form2.TITULO_PORTUGUES.value,
				p_form2.SINOPSE_CURTA.value,
				p_form2.SINOPSE_LONGA.value,
				p_form2.DIRETOR.value,
				p_form2.ELENCO.value,
				p_form2.IDIOMA.value,
				p_form2.CLASSIFICACAO.value,
				p_form2.JUSTIFICATIVA.value,
				p_form2.GENERO.value,
				p_form2.ROTEIRO.value,
				p_form2.MESANO.value,
				p_form2.DATA_CORRETA.value,
			  
				function(a) {
					alert(a);
					escondeArea('ID_Formulario');
					if (g_sinopse_id != -1)
					{
					  var linha = document.getElementById('linha' + g_sinopse_id);
					  linha.cells[1].innerHTML = p_form2.DATA_CORRETA.value;
					  linha.cells[2].innerHTML = p_form2.PROGRAMA.value;
					  linha.cells[3].innerHTML = p_form2.SINOPSE_LONGA.value;
					  linha.cells[4].innerHTML = p_form2.DIRETOR.value;
					  linha.cells[5].innerHTML = p_form2.TITULO_ORIGINAL.value;
					}  
				}
		    );		
	  }
	  	  
	</script>
    
    <style type="text/css">
#ID_Formulario {
	position:absolute;
	left:4034px;
	top:222px;
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
 <div id="ID_Formulario" style="width: 760px; height: 527px;;"><br>
    <form method="post" name="p_form2">
    <table width="633" align="center">
    <tr style=" height : 58px; width : 670px;">
    <td width="124">SINOPSE_LONGA </td>
    <td align="center" width="205" colspan="3" style=" width : 389px;">
    	<textarea name=SINOPSE_LONGA style="height : 203px; width : 608px;" cols="60" ></textarea></td>
    </tr>
    <tr>
    <td width="131"> CANAL</td>
    <td width="203"> <input type=text name=CANAL> </td>
    <td> PROGRAMA        </td> <td> <input type=text name=PROGRAMA             style=" width : 293px;"> </td>
    </tr>
    <tr>
    <td> DATA            </td> <td> <input type=text name=DATA                 > </td>
      <td>DIRETOR </td>
      <td><input type=text name=DIRETOR              style=" width : 293px;"></td>
    </tr>
    <tr><td> HORARIO         </td> <td> <input type=text name=HORARIO              > </td>
      <td>ELENCO </td>
      <td><input type=text name=ELENCO               ></td>
    </tr>
    <tr><td> ANO             </td> <td> <input type=text name=ANO                  > </td>
      <td>IDIOMA </td>
      <td><input type=text name=IDIOMA               ></td>
    </tr>
    <tr><td> NRO_EPISODIO    </td> <td> <input type=text name=NRO_EPISODIO         > </td>
      <td>CLASSIFICACAO</td>
      <td><input name=CLASSIFICACAO type=text id="CLASSIFICACAO"        ></td>
    </tr>
    <tr><td> DURACAO         </td> <td> <input type=text name=DURACAO              > </td>
      <td>JUSTIFICATIVA </td>
      <td><input name=JUSTIFICATIVA type=text id="JUSTIFICATIVA"              ></td>
    </tr>
    <tr><td> BLOCO           </td> <td> <input type=text name=BLOCO                > </td>
      <td>GENERO </td>
      <td><input name=GENERO type=text id="GENERO"               style=" width : 293px;"></td>
    </tr>
    <tr><td> TITULO_ORIGINAL </td> <td> <input type=text name=TITULO_ORIGINAL      > </td>
      <td>MESANO </td>
      <td><input name=MESANO type=text id="MESANO"        ></td>
    </tr>
    <tr><td> TITULO_PORTUGUES</td> <td> <input type=text name=TITULO_PORTUGUES     > </td>
      <td>DATA_CORRETA </td>
      <td><input name=DATA_CORRETA type=text id="DATA_CORRETA"               ></td>
    </tr>
    <tr><td> SINOPSE_CURTA   </td> <td> <input type=text name=SINOPSE_CURTA        > </td>
            <td>ROTEIRO </td>
      <td><input name=ROTEIRO type=text id="ROTEIRO"         ></td>
    </tr>
    <tr style="width : 635px; height : 27px;" align="center">
      <td>&nbsp;</td>
      <td><input type="button" name="button2" id="button2" value="Cancelar" onClick="escondeArea('ID_Formulario')">
      <input type="button" name="button3" id="button3" value="Salvar" onClick="jsSalvar()">
      
      </td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    </table>
    </form>
</div>



<form method="post" name="p_form">
<table width="485" align="center">
  <tr>
    <td colspan="2" align="center" class="caixasDialogo">FILTRO</td>
  </tr>
  <tr>
    <td width="128" align="right">Dia</td>
    <td width="345"><input name="dia1" type="text" id="dia1" size="5">
    at&eacute;
    <input name="dia2" type="text" id="dia2" size="5">
    <input type="button" name="button4" id="button4" value="Copiar Repeteco" onClick="jsRepeteco()" style="visibility:hidden">
    </td>
    </tr>
  <tr>
    <td align="right">Mes/Ano</td>
    <td><input type="text" name="mesano2" id="mesano2"></td>
    </tr>
  <tr>
    <td align="right">Canal</td>
    <td><select name="canal" id="canal"> <% nexcabo.UUtils.fillCombo(nexcabo.UUtils.con,out, "SELECT nome_canal a, nome_canal from t_siglas_canais order by nome_canal");  %> </select>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="button" name="button" id="button" value="Filtrar" onClick="jsMostraFiltro()">
      <input type="button" name="button4" id="button4" value="Novo" onClick="jsNovo()">
      <input type="button" name="button3" id="button3" value="Controle de Canais" onClick="window.open('lista_controle_canais.jsp','tela','left=800,width=280,height=600,scrollbars=yes,menubar=no,toolbar=no,titlebar=no,location=no')">
      </td>
    </tr>
</table>
</form>

<div id=tab_sinopse>
<table width="100%" class="grid">
  <tr class="caixasDialogo">
    <td width="10%">#</td>
    <td width="13%">DATA_CORRETA</td>
    <td width="21%">PROGRAMA</td>
    <td width="20%">SINOPSE_LONGA</td>
    <td width="12%">CLASSIFICACAO</td>
    <td width="24%">TITULO ORIGINAL</td>
  </tr>
</table>
</div>

<script>
jQuery(function($){
   $("#DATA_CORRETA").mask("99/99/9999 99:99:99");
   $("#MESANO").mask("99/9999");
   $("#mesano2").mask("99/9999");
   
});
</script>

</body>

</html>
