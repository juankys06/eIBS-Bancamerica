<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Camara Entrante</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="msgCK" class="datapro.eibs.beans.ECH059501Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

 <h3 align="center">Generacion de Archivo de Chequeras al Proveedor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="checkbooks_supplier_file.jsp, ECH0595"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0595">	
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    </p>
  <table id="tbenter" align="center" class="tableinfo">
  <tr id="trdark"> 
  
     			<td align="right" width="50%"> 
             		Fecha: 
				</td>  
				<td align="left" width="50%"> 
             		 <input type="text" name="E01DIA" size="3" maxlength="2" value="<%= msgCK.getE01DIA().trim()%>" onKeyPress="enterInteger()">
			         <input type="text" name="E01MES" size="3" maxlength="2" value="<%= msgCK.getE01MES().trim()%>" onKeyPress="enterInteger()">
					 <input type="text" name="E01ANO" size="3" maxlength="2" value="<%= msgCK.getE01ANO().trim()%>" onKeyPress="enterInteger()">
					 <a href="javascript:DatePicker(document.forms[0].E01DIA,document.forms[0].E01MES,document.forms[0].E01ANO)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
				</td>        
  
    </tr>
    <tr></tr>
    <tr id="trcleark"> 
      <td colspan="2" align="center"> 
           		El archivo se creara con el nombre de CHEQUES.TXT<BR>
				Presiones Enviar para ejecutar el proceso.
				</td>        
    	
    </tr>
  </table>
  
<% if (msgCK.getE01EXISTE().equals("Y")) { %> 
	<h4 style="text-align:center"><input type="checkbox" name="E01REPLACE" value="Y"><FONT
	color="#ff0000">Archivo ya Existe, Marque para reemplazar</FONT></h4>
<% } %>
  
  <p align="center"> 
	<input id="EIBSBTN" type="submit" name="Submit" value="Enviar" > 
  </p> 
	  
 </FORM>
</BODY>
</HTML>
