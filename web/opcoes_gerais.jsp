<%@page import="nexcabo.UUtils"%>
<html>
<head>
    <title>Nex Web</title>
    <link rel="shortcut icon" href="imgs/NavigationNext.png" type="image/x-icon">
   
    <script type='text/javascript' src='/NexWeb/dwr/interface/UOpcoesGerais.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='site.js'></script>
    <link href="site.css" rel="stylesheet" type="text/css" />
    
    <script>
	  var g_sinopse_id;
	  
	  function jsMostraFiltro()
	  {
	    UOpcoesGerais.mostraFiltroCliente(
		  p_form.P_CLIENTES.value,
		  
		  function(a) {
		    innerHTML('tab_clientes', a);
		  }
		);
	  }
	  
	  function jsGerar()
	  {
	     UOpcoesGerais.gerarArquivoXML(
					p_form.P_CLIENTES.value, 
					p_form.mesano.value, 
					p_form.dia1.value, 
					p_form.dia2.value, 
					function(a) {
					   document.all.download.innerHTML = "Baixar Arquivo";
					   document.all.download.href = a;
					}
         
		 );					
		 
	  }
	  
	  
	  function jsAtivar(obj, row_id)
	  {
	    UOpcoesGerais.ativarDesativar(
		  row_id,
		  
		  function(a) {
		    obj.src = a;
		  }
		);
	  }	
	  
	  function jsAddHoras()
	  {
	     UOpcoesGerais.salvarAddHoras(
		    p_form.p_add_horas.value,
			p_form.p_tam_sinopse.value,
			function(a) {
			  alert(a);
			}
		 );
	  }  

	  function jsEdit(sigla, cliente)
	  {
		numero = prompt("Digite o numero do canal:");
	    UOpcoesGerais.alterarNumeroCanal(sigla, cliente, numero,		  
		  function(a) {
		    alert(a);
		    document.getElementById('rs_id_' + sigla).innerHTML = numero;
		  }
		);
		//mostraArea('ID_Formulario');
	  }	



	  function jsGenero(sigla, cliente)
	  {
		genero = prompt("Digite o numero do canal:");
	    UOpcoesGerais.alterarGenero(sigla, cliente, genero,		  
		  function(a) {
		    alert(a);
		    document.getElementById('rs_id_genero_' + sigla).innerHTML = genero;
		  }
		);
		//mostraArea('ID_Formulario');
	  }	 	


	  function jsDuplicar()  
	  {
			nome = prompt("Digite o nome do canal:");
		    UOpcoesGerais.duplicar(p_form.P_CLIENTES.value, nome,		  
			  function(a) {
			    alert(a);
			  }
			);
	  }			  	  

	  function jsApagar()  
	  {
		    UOpcoesGerais.apagar(p_form.P_CLIENTES.value,  
			  function(a) {
			    alert(a);
			  }
			);
		  }		  
	</script>
    <style type="text/css">
<!--
#ID_Formulario {
	position:absolute;
	left:4034px;
	top:97px;
	width:405px;
	height:187px;
	z-index:1;
	background-color: #FFFFCC;
	border:solid #000000 3px;
}
-->
    </style>
</head>


<body onLoad="dwr.util.useLoadingMessage('Por Favor Espere! Carregando...')">
<%@include file="index_menu.jsp" %>
<form method="post" name="p_form">

<table width="100%" align="center">
  <tr>
    <td colspan="8" align="center" class="caixasDialogo">FILTRO</td>
  </tr>
  <tr>
    <td width="137" align="right">A partir do Dia</td>
    <td width="30"><input name="dia1" type="text" id="dia1" size="5"></td>
    <td width="18">at&eacute;</td>
    <td width="30"><input name="dia2" type="text" id="dia2" size="5"></td>
    <td width="52">Mes/Ano</td>
    <td width="240"><input type="text" name="mesano" id="mesano">
      <input type="button" name="button5" id="button5" value="Gerar" onClick="jsGerar()"></td>
    <td width="268"> <a href=# id="download"></a></td>
    <td width="540">tam sinopse
      <input name="p_tam_sinopse" type="text" id="p_tam_sinopse" size="5">
       add horas  
        <input name="p_add_horas" type="text" id="p_add_horas" size="5">
      <input type="button" name="button7" id="button8" value="Salvar Padr&atilde;o" onClick="jsAddHoras()"></td>
    </tr>
</table>


<table width="100%">
  <tr>
    <td width="10%" height="225" valign="top">
      <select name="P_CLIENTES" size="40" id="P_CLIENTES" onChange="jsMostraFiltro()">
        <%  UUtils.fillCombo(UUtils.con, out, "select nome i, nome from t_xml_combo order by ordem"); %>
      </select>
    </td>
        
        <td width="90%" valign="top">
          <div id=tab_clientes>
            <table width="100%" class="grid">
              <tr class="caixasDialogo">
                <td width="4%">ATIVO</td>
                <td width="23%">SIGLA</td>
                <td width="3%">NRO</td>
                <td width="30%">CANAL</td>
                <td width="26%">NOME</td>
                <td width="14%">GENERO</td>
              </tr>
            </table>
          </div>
        </td>
    </tr>
</table>


<div id="ID_Formulario"><br>
    <table width="350" align="center">
      <tr>
        <td width="131">NRO</td>
        <td width="203"><input name=P_NRO type=text id="P_NRO"                >        </td>
      </tr>
      <tr>
        <td> NOME </td>
        <td><input name=P_NOME type=text id="P_NOME"                 >        </td>
      </tr>
      <tr>
        <td> GENERO</td>
        <td><input name=P_GENERO type=text id="P_GENERO"              >        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td><input type="button" name="button6" id="button6" value="Cancelar" onClick="escondeArea('ID_Formulario')">
            <input type="button" name="button6" id="button7" value="Salvar" onClick="jsSalvar()"></td>
      </tr>
    </table>
</div>
</form>

<script>

	     UOpcoesGerais.pegaConfig(
		    1,
			function(a) {
			  p_form.p_tam_sinopse.value = a;
			}
		 );

	     UOpcoesGerais.pegaConfig(
		    2,
			function(a) {
			  p_form.p_add_horas.value = a;
			}
		 );


</script>
</body>

</html>
