<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language = "java" %>
<%@ page import = "datapro.eibs.master.Util, java.math.BigDecimal" %>
<html>
<head>
<title>Draft Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "collBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id= "dftList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "lstAcceptors" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT Language="Javascript">

function Enviar() {
    if (document.forms[0].ideacc.value == "") {
    	alert("Debe Ingresar Aceptante");
    }else{
		  document.forms[0].submit();
    } 
}

function showDirecciones(){

	if (document.forms[0].ideacc.value != ""){
		GetMailing('address',document.forms[0].ideacc.value)
	} else {
		alert("Primero debe ingresar Identificación de Aceptante ..");
        document.forms[0].ideacc.focus();		
	}
}

</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 

<h3 align="center">Mantención de Aceptante<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="acceptor_enter,EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3200">
  <INPUT TYPE=HIDDEN NAME="ACCION" VALUE="V">
     
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
	<TR>
	  <TD ALIGN=right nowrap>
          Ingrese Identificación de Aceptante : 
      </TD>
	  <TD ALIGN=left nowrap>
          <input type="text" maxlength=15 size=15 name="ideacc" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda1" value="">
	   <a href="javascript:GetAcceptantIdNDA('ideacc','idenda')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
      </TD>      
   </TR>
   
	<TR>
	  <TD ALIGN=right nowrap>
          Ingrese Número de Dirección : 
      </TD>
	  <TD ALIGN=left nowrap>
          <input type="text" maxlength=1 size="3" name="address"
			value="">
		<a href="javascript:showDirecciones()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
      </TD>
   </TR>
</table>
<div align="center"><INPUT id="EIBSBTN" type="button" value="Enviar"  onClick="Enviar()">  </div>  
  
</form>
<script>

</script>
</body>
</html>
