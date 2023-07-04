<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Relaciones entre Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  function goAction(op) {
     document.forms[0].opt.value = op;
     var st= trim(document.forms[0].ACCNUM.value);    
	 if ( st.length > 0 ) {
          document.forms[0].submit();
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     		document.forms[0].ACCNUM.focus();
     		document.forms[0].opt.value = "0";
     }     
  }
  
  function enter() {
     if (document.forms[0].opt.value =="0") {
     	alert("Seleccione una opcion antes de continuar");
        return false;
     } else return true;
  }
  
  function goExit(){
  	window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  } 
</SCRIPT>

</head>

<body>

<h3 align="center">Relaciones entre Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_enter_acc, EDD0430"></h3>
<hr size="4">
<p>&nbsp;</p>


<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" onsubmit="return(enter());">  
<p>
  <input type=HIDDEN name="SCREEN" value="400">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="0">
</p>
<TABLE class="tbenter"> 
<TR>      
  <TD ALIGN=CENTER width="17%" class=TDBKG>
    <div align=center>
  	  <a href="javascript:goAction(1)"><b>Inversiones<br>Nocturnas</b></a>
  	<div>
  </TD>
  <TD ALIGN=CENTER width="17%" class=TDBKG>
    <div align=center>
  	  <a href="javascript:goAction(2)"><b>Concentracion<BR>de Cuentas</b></a>
  	<div>
  </TD>
  <TD ALIGN=CENTER width="17%" class=TDBKG>
    <div align=center>
  	  <a href="javascript:goAction(3)"><b>Diferir<br>Sobregiros</b></a>
  	<div>
  </TD>
  <TD ALIGN=CENTER width="17%" class=TDBKG>
    <div align=center>
  	  <a href="javascript:goAction(4)"><b>Sobregiros<br>en Línea</b></a>
  	<div>
  </TD>
  <TD ALIGN=CENTER width="16%" class=TDBKG>
  	<div align=center >
  	  <a  href="javascript:goAction(5)"><b>Proteccion<br>Sobregiros</b></a>
  	<div>
  </TD>
  <TD ALIGN=CENTER width="16%" class=TDBKG>
   <div align=center>
  	  <a  href="javascript:goExit()"><b>Salir</b></a>
  	<div>
  </TD>
 </TR>
</TABLE>

  <table width="100%" height="40%" class="tbenter">   
	<tr > 
      <td> 
        <table  class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=2>		    
            <td width="50%"> 
              <div align="right">Ingrese el N&uacute;mero de Cuenta : </div>
            </td>
            <td width="50%"> 
              <input type="text" name="ACCNUM" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('ACCNUM','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<script language="JavaScript">
  document.forms[0].ACCNUM.focus();
  document.forms[0].ACCNUM.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
