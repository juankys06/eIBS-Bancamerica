<html>
<head>
<title>Consulta de Carta de Crédito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>




</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body nowrap >


<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Consulta de Cartas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ELC0450_lc_inquiry.jsp, ELC0450"></h3>
<hr size="4">
<p>&nbsp;</p>

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450">
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
              <div align="right">Ingrese el N&uacute;mero de la Carta de Cr&eacute;dito
                : </div>
            </td>
            <td width="50%">
              <input type="text" name="E01LCMACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01LCMACC','','LC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
