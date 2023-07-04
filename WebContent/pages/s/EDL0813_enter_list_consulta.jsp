<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
  document.forms[0].submit();
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

<h3 align="center">Consulta de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_list_consulta, EDL0813"></h3>
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.cb.products.JSEDL0813" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
     
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">

	<TR>
	  <TD ALIGN=right nowrap>
		Cliente (Cedente) 
      </TD>
	  <TD ALIGN=left nowrap>
      <div align="left">
        <input type="text" name="E01DLDDWR" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>">
        <a href="javascript:GetCustomerDescId('E01DLDDWR','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
        <input type="TEXT" maxlength=30 size=30 name="E01CUSNA1" value="">
       </div>
      </TD>      
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          Rut Aceptante 
      </TD>
	  <TD ALIGN=left nowrap>
          <input type="text" maxlength=15 size=15 name="E01DLDDID" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda1" value="">
	   <a href="javascript:GetAcceptantIdNDA('E01DLDDID','idenda')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
      </TD>      
   </TR>
	<TR>
	  <TD ALIGN=right nowrap>
          Estado
      </TD>
	  <TD ALIGN=left nowrap>
          <SELECT name="E01DLDRST" readonly>
			<OPTION value="" >Todo</OPTION>
			<OPTION value="A" >ACTIVO</OPTION>
			<OPTION value="P" >PAGADO</OPTION>
			<OPTION value="Q" >DEVUELTO/ANULADO</OPTION>
			<OPTION value="D" >ENVIADO A CAMARA</OPTION>
			<OPTION value="N" >PRORROGA</OPTION>
			<OPTION value="R" >REVISION PAGO/CHEQUE CAMARA</OPTION>
			<OPTION value="H" >PENDIENTE APROBACION</OPTION>
			<OPTION value="X" >PAGO HOY</OPTION>
			<OPTION value="T" >PROTESTADO</OPTION>
			<OPTION value="U" >COBRANZA JUDICIAL</OPTION>
	     </SELECT>     
      </TD>      
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          Fecha Desde
      </TD>
	  <TD ALIGN=left nowrap>
			<input type="text"   size="2" maxlength="2" name="E01DLDRF1" value="" >
			<input type="text"   size="2" maxlength="2" name="E01DLDRF2" value="" >
			<input type="text"   size="4" maxlength="4" name="E01DLDRF3" value="" > (dd/mm/yyyy)
      </TD>      
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          Fecha Hasta
      </TD>
	  <TD ALIGN=left nowrap>
			<input type="text"   size="2" maxlength="2" name="E01DLDMF1" value="" >
			<input type="text"   size="2" maxlength="2" name="E01DLDMF2" value="" >
			<input type="text"   size="4" maxlength="4" name="E01DLDMF3" value="" > (dd/mm/yyyy)
      </TD>      
   </TR>
   


</table>
<br>
<div align="center"><INPUT id="EIBSBTN" type="button" value="Enviar"  onClick="Enviar()">  </div>  
  
</form>
<script>

</script>
</body>
</html>
