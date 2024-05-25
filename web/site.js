function innerHTML(id, valor)
{
	var tabela = document.getElementById(id).innerHTML;
	tabela =  tabela.substr(0,tabela.toLowerCase().indexOf("</tr>") + 5);
	tabela =  tabela.replace("<tbody>", "");
	tabela =  tabela.replace("<TBODY>", "");
	tabela =  tabela + valor + "</TABLE>"
	document.getElementById(id).innerHTML = tabela; 
}



function mostraArea(p_id,obj)
{
	tam =  parseInt (document.getElementById(p_id).clientWidth );
	hei =  parseInt (document.getElementById(p_id).clientHeight );

	if (obj == null)
	{
	  v_left = (screen.availWidth-tam) / 2;
	  v_top = (document.body.scrollTop + (screen.availHeight-hei) / 2) - 150;
	  document.getElementById(p_id).style.left = v_left + 'px';
	  document.getElementById(p_id).style.top  = v_top  + 'px';
	}
}


function jsValidaSenha()
{
	var usuarios = [];
	
	
	usuarios["RODRIGO"] = "rod123";
	usuarios["DIG1"] = "010203";
	usuarios["DIG2"] = "010203";
	
	
	if ( usuarios[p_form.usuario.value] != null && usuarios[p_form.usuario.value] == p_form.senha.value )
	{
	  UValidarUsuario.validaUsuario(function(){});
	  window.location.href = 'index_login.jsp';  
	}
		
	else
	{
		alert('Usuário não cadastrado ou senha Invalida');
		UValidarUsuario.invalidaUsuario(function(){});
	}
}



function escondeArea(p_id)
{
	  document.getElementById(p_id).style.left = '8000px';
}