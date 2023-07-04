<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "lnChgSts" class= "datapro.eibs.beans.EDL013501Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
 var pass = false;
	if ( document.forms[0].E01DEAACC.value.length < 1) {
  		alert("Teclee una cuenta valida");
  		document.forms[0].E01DEAACC.value='';
  		document.forms[0].E01DEAACC.focus();
	}
	else {
  		for (var i=0; i< 4;i++){
   			if (document.forms[0].E01FLGOPE[i].checked) {
     			pass = true;
     			break;
   			}
  		}
  		if (!pass) alert("Seleccione una operacion antes de continuar");
  	}
  return(pass);
}

</SCRIPT>

</head>

<body>

<h3 align="center">Condiciones del Pr&eacute;stamo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_enter_chg_status,EDL0135"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0135" onsubmit="return(CheckACC())">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" height="80%" border="0">
		  <tr> 
            <td nowrap colspan=2 align="center"> 
              Número de Cuenta :            
              <INPUT type="text" name="E01DEAACC" size="15" maxlength="12" onkeypress="enterInteger()" value="<%= lnChgSts.getE01DEAACC() %>">
              <a href="javascript:GetAccount('E01DEAACC','','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr>            
            <td nowrap colspan=2 align="center">  
              
        <p align="center"> <b>Seleccione la condici&oacute;n que desee modificar</b> 
        </p> 
 			</td>
 		  </tr>
 		  <tr>
 		    <td nowrap width="40%" align="right">              
            </td>
 			<td nowrap >                
				<INPUT type="radio" name="E01FLGOPE" value="1" <% if (lnChgSts.getE01FLGOPE().equals("1")) out.print("checked"); %>><b>Cambio de Estado</b>		  			  
 			</td> 
 		 </tr>
          <tr>
            <td nowrap align="right">               
            </td>
 			<td nowrap >                
				<INPUT type="radio" name="E01FLGOPE" value="2" <% if (lnChgSts.getE01FLGOPE().equals("2")) out.print("checked"); %>>
        <b>Gesti&oacute;n Cobranza</b> </td>
 		  </tr>
          <tr>
 			<td nowrap align="right">               
            </td>
 			<td nowrap >                 
				<INPUT type="radio" name="E01FLGOPE" value="3" <% if (lnChgSts.getE01FLGOPE().equals("3")) out.print("checked"); %>>
        <b>Suspenci&oacute;n de Devengos</b> </td>
 		  </tr>
          <tr>
 			<td nowrap align="right">               
            </td>
 			<td nowrap >                 
				<INPUT type="radio" name="E01FLGOPE" value="4" <% if (lnChgSts.getE01FLGOPE().equals("4")) out.print("checked"); %>>
        <b>Aceleraci&oacute;n</b> </td>
 		  </tr>
          <tr> 
            <td nowrap colspan =2 align="center"> 		 
                <p align="center"> 
					<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        		</p> 
 			</td>
          </tr>
        </table>
     
  
  <script language="JavaScript">
  document.forms[0].E01DEAACC.focus();
  document.forms[0].E01DEAACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
