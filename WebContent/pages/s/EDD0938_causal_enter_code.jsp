<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Ordenes de Pago</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="prBasic" class="datapro.eibs.beans.EDD067001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

</SCRIPT>
</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<h3 align="center">Tabla de Cargos Camara/Devoluciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="causal_enter_code,EDD0938"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0938">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">

  <h4>Informaci&oacute;n B&aacute;sica</h4>
  
     
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap align="center" width="40%"> 
        <div align="right">C&oacute;digo de Causal :</div>
      </td>
      <td nowrap align="center" width="60%"> 
        <div align="left"> 
          <input type="text" name="CAUSAL" size="5" maxlength="4" value="">
          <a href="javascript:GetCasualTable('CAUSAL','')">
          <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="absbottom" border="0"  ></a>
          
        </div>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>
