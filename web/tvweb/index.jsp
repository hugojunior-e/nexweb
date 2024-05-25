<%@page import="nexcabo.UUtils"%>

<%= nexcabo.UUtils.connectNexcabo(request, out) %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROGRAMA«AÉO | HORARIO DOS PROGRAMAS | HBO MAX</title>
<link href="style.css" type="text/css" rel="Stylesheet">
<link href="stylept.css" type="text/css" rel="Stylesheet">
<link rel="stylesheet" href="jquery-ui.css">

	<script src="jquery-1.10.2.js"></script>
	<script src="jquery-ui-1.10.4.custom.js"></script>
      
    <script type='text/javascript' src='/NexWeb/dwr/interface/TvWeb.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/interface/UUtils.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/engine.js'></script>
    <script type='text/javascript' src='/NexWeb/dwr/util.js'></script>
    <script type='text/javascript' src='/NexWeb/site.js'></script>
    
<script>
  function jsBuscarProgramacao()
  {
	  TvWeb.listaTvWeb(aspnetForm.P_CANAL.value, 
			           aspnetForm.P_DATA.value,
			           function(a) {
						  innerHTML('id_programas', a);           

          });
	  id_programas
  }

  $(function() {
    $( "#datepicker" ).datepicker();
  });
    
</script>

</head>
<body>

<script type="text/javascript" src="wz_tooltip.js"></script>

<form method="post" action="" name="aspnetForm">
  <div id="cPage">
    <div id="cTop01">
      <div class="clear">
        <table width="100%">
          <tr>
            <td width="177" rowspan="2"><img src="http://www.nexcabo.com.br/img/logo2nexcabo.jpg" /></td>
            <td width="395" height="30" align="right"><span class="style1">Canal: </span></td>
            <td width="410" align="left"><label>
              <select name="P_CANAL">
                <% UUtils.fillCombo(nexcabo.UUtils.con, out, "select canal a, canal b from t_clientes_web where cliente='" + request.getParameter("cliente") + "'"); %>
              </select>
            </label></td>
          </tr>
          <tr>
            <td align="right"><span class="style1">Horario: </span></td>
            <td align="left"><label>
              <input type="text" name="P_DATA" id="datepicker" />
              <input type="button" name="Submit" value="Buscar ProgramaÁ„o" onclick="jsBuscarProgramacao()" />
            </label></td>
          </tr>
        </table>
      </div>
    </div>
    <img id="ctl00_cphBody_imgSeccion" src="pes_pro_pt.jpg" align="top" style="border-width:0px;">
    <div id="cContent" class="background-color1">
      <div style="display:block;" id=id_programas>
        <table width=100% class=grid>
          <tr class="caixasDialogo">
            <td> Horario </td>
            <td> Programa </td>
          </tr>
        </table>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</form>
</body>
</html>
