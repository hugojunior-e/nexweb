<%@page import="java.io.RandomAccessFile"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.DataInputStream"%>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  

 String savePath = "/var/www/";   // o diret�rio onde os arquivos carregados ser�o salvos  

 String filename = "";     

 ServletInputStream in = request.getInputStream(); //uma referencia do objeto da solicita�ao http onde tem o conteudo do arquivo carregado  

 //o inicio do arquivo carregado  e separado pelo limite e uma sequencia de caracteres
 //de carros de retorno-alimentador de linha ledo o HttpServeltRequest linha a linha
 byte[] line = new byte[128];  // define um array de byte chamado line
 int i = in.readLine(line, 0, 128);  //usar o metodo readLine de ServeltInputStream para ler a 1a linha do conteudo do objeto http
 int boundaryLength = i - 2; //o comprimeto atual da linha -2 do que o num de bytes retornado do metodo readLine
 String boundary = new String(line, 0, boundaryLength);   //Descarta os 2 ultimos caracteres da linha  

 //tendo recuperado o limite, entao pode iniciar a extra�ao do elemento de valor de formulario, lendo o conteudo do objeto
 // http linha por linha, usando a loop while, ate q ela atinja o final, qdo o medoto readLine retorna -1
  while (i != -1) {
    String newLine = new String(line, 0, i);
    if (newLine.startsWith("Content-Disposition: form-data; name=\"")) {
      String s = new String(line, 0, i-2);//agora pode conseguir o nome de arquivo a partir da string de leitura
      int pos = s.indexOf("filename=\"");
      if (pos != -1) {
        String filepath = s.substring(pos+10, s.length()-1);
        // navegadores do Windows incluem o caminho completo do cliente,
        // mas Unix / Linux e navegadores de Mac s� enviar o arquivo de teste
        // se este � de um navegador para Windows
        pos = filepath.lastIndexOf("\\");
        if (pos != -1)
          filename = filepath.substring(pos + 1);
        else
          filename = filepath;
      }
      
      //remove acentos
      java.text.Normalizer.normalize(filename, java.text.Normalizer.Form.NFD);
      filename = filename.replaceAll("[^\\p{ASCII}]", "");     

      //este � o conte�do do arquivo
      i = in.readLine(line, 0, 128);  //depois de conseguir o nome de arquivo, notara os dois pares de caracteres
      i = in.readLine(line, 0, 128); //carro de retorno-alimentador de linha antes do inico do conteudo do arquivo carregado
      // blank line                  //pois isto chama o metodo readLine duas vezes
      i = in.readLine(line, 0, 128);     

      ByteArrayOutputStream buffer = new  //depois inicia o conteudo atual do arquivo, que e armazenado em um ByteArrayOutputStream
      ByteArrayOutputStream();
      newLine = new String(line, 0, i); // que continua lendo a linha ate encontrar um outro limite  

      while (i != -1 && !newLine.startsWith(boundary)) {
       // o problema � a �ltima linha do conte�do do arquivo cont�m o caractere nova linha.
       // Ent�o, n�s precisamos verificar se a linha atual � a �ltima linha
        buffer.write(line, 0, i);
        i = in.readLine(line, 0, 128);
        newLine = new String(line, 0, i);
      }
      try {
        // salvar o arquivo carregado               //o limite sinaliza o final do arquivo carregado
        RandomAccessFile f = new RandomAccessFile(  //sendo sua etapa e salvar o buffer em um arquivo
          savePath + filename, "rw");
        byte[] bytes = buffer.toByteArray();
        f.write(bytes, 0, bytes.length - 2);
        f.close();
      }
      catch (Exception e) {}
    }
    i = in.readLine(line, 0, 128);     

  }
%>
<style>
.style1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 26px;
	color: #000000;
}

.style2 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 16px;
	color: #000000;
}


</style>
<div align="center"><a href="downloads.jsp">Voltar</a></div>
 
