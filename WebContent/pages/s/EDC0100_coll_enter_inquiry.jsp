<html>
<head>
<title>Consulta de Cobranzas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body nowrap >


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Consulta de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="coll_enter_inquiry.jsp, EDC0100"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
 <!--<h4 align="center">Por favor Ingrese el n&uacute;mero del Certificado de Dep&oacute;sito</h4>-->
  <table width="100%" border="0" bordercolor="#000000" cellspacing=0 cellpadding=0>
   
	<tr bordercolor="#FFFFFF"> 
      <td> 
        <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>
          
		    <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
	  <tr><td>&nbsp;</td></tr>
		  <tr><td>&nbsp;</td></tr>
		  <tr> 
            <td width="50%"> 
              <div align="right">Ingrese el N&uacute;mero de la Cobranza : </div>
            </td>
            <td width="50%"> 
              <input type="text" name="E01DCMACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01DCMACC','','CL','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
 <div align="center"> 
 	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
 </div>
</form>
</body>
</html>
