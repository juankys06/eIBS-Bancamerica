<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Banesco - Abonos Masivos, opcion automática (Aprobación)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet"> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "lnScheme" class= "datapro.eibs.beans.EDL111101Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT> 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="javascript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

function CheckACC(){

if ( document.forms[0].E01CNOMID.value.length < 1) {
  alert("Debe entrar un código de empresa válido");
  document.forms[0].E01CNOMID.value='';
  document.forms[0].E01CNOMID.focus();
  return false;
}
else {

if ( document.forms[0].E01OFFOPC.value.length < 1) {
//  alert("Debe entrar un concepto válido");
//  document.forms[0].E01OFFOPC.value='';
//  document.forms[0].E01OFFOPC.focus();
  return true;
}
else {
  return true;
  }


//  return true;
  }



}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

</head>

<body bgcolor="#FFFFFF">

<h3 align="center">Abonos Masivos, Proceso Automático - Aprobación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_enter_sch_app,EDL1111"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1111">
  <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="700"></p>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
			<tr id="trdark"> 
			  <td nowrap width="32%"> 
			    <div align="right"><b>Compañia de Pago :</b></div>
			  </td>
			  <td nowrap width="3%">&nbsp;</td>
			  <td nowrap width="23%"> 
			    <div align="left"> 
			        <input type="radio" name="E01CNOMID" value="1SS&" 
			        	<%if (userPO.getHeader7().equals("") 
			        		|| userPO.getHeader7().equals("1SS&")) out.print("checked"); %>>Seguro Social Empleados 
			        <input type="radio" name="E01CNOMID" value="2SS&"
			        	<%if (userPO.getHeader7().equals("2SS&")) out.print("checked"); %>>Seguro Social Jubilados
			        <input type="radio" name="E01CNOMID" value="3CO&"
			        	<%if (userPO.getHeader7().equals("3CO&")) out.print("checked"); %>>Contraloría General
			  </td>
			</tr>

  			<tr id="trclear"> 
    			<td nowrap width="32%"><div align="right"><b>Clave de Descuento :</b></div></td>
    			<td nowrap width="3%">&nbsp;</td>
       			<td nowrap width="23%">
       			<input type="text" readonly name="E01DESCTO" size="8" maxlength="6" value="<%= userPO.getHeader6().trim()%>">
    			<a href="javascript:GetCodeCNOFC('E01DESCTO','4D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
    			</td>
  			</tr>


        </table>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>

     
  
</form>
</body>
</html>
