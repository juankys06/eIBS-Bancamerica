<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento Parametrizacion Rango de Tasas de Cambio en Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
function CheckACC(){
	var ok=true;
	
	if(ok){
		document.forms[0].submit();
	}
		
	
}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center"> Mantenimiento Parametrizacion Rango de Tasas de Cambio en Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="previewMantParamRT, JSEFE0501"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0501">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="21">
    <INPUT TYPE=HIDDEN NAME="OPECOD" VALUE="0001">
     <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  </p>

<p>
  <table id="TBHELP" align="center" width="100%" height="10%"  border="0">
    <tr> 
      <td nowrap ALIGN="right">
      		Moneda:  </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01FEICCY" size="4" maxlength="3" >
        <a href="javascript:GetCurrency('E01FEICCY','')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a>  
      </td>
    </tr> 
      
     <tr> 
      
      <td nowrap ALIGN="right">
      		Transaccion :  </td>
      <td nowrap ALIGN="left"> 
        <select name="TIPTRN">
		  <option value=""></option>					
		  <option value="PU">Compra</option>				
		  <option value="SL">Venta</option>    
 
		</select> 
      </td> 			
	
    </tr>  
     <tr> 
      
      <td nowrap ALIGN="right">
      		Sucursal   </td>    			
	
    </tr>  
        <tr> 
      
      <td nowrap ALIGN="right">
      		Desde :  </td>
      <td nowrap ALIGN="left"> 
       <INPUT type="text" name="E01FEIBR1" size="3" maxlength="3" >
      </td> 			
	
    </tr> 
        <tr> 
      
      <td nowrap ALIGN="right">
      		Hasta :  </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="E01FEIBR2" size="3" maxlength="3" >
      </td> 			
	
    </tr> 
    
  </table> 

<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
  </div> 
</p> 
  

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