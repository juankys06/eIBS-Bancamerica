<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Prev Transacciones Salientes ACH</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "ewd0012Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">
function CheckACC(){

	
	document.forms[0].submit();


}
function Validate(){
	var ok=true;
	if(document.forms[0].ACCFRM.value == ""){
		alert("Debe Seleccionar Una Cuenta a Debitar");
		ok=false
	}
	if(document.forms[0].TIPTRN.value == "0"){
		alert("Debe Seleccionar Un tipo de Transaccion");
		ok=false
	}
	if(document.forms[0].destino.value == "0"){
		alert("Debe Seleccionar Un destino para la transferencia");
		ok=false
	}
	if(ok){
		CheckACC();
	}

}

</SCRIPT>

</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">
<% 

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<H3 align="center"> Transacciones Salientes ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="salientes_ACH, JSEACH001"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH001">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="310">
  </p>

<p>
  <table id="TBHELP" align="center" width="100%" height="10%"  border="0">
    <tr> 
      <td nowrap ALIGN="right">
      		N&uacute;mero de Cuenta a Debitar: </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="ACCFRM" size="12" maxlength="12" 
				onclick="document.forms[0].search[0].click()" onkeypress="enterInteger()">
        <a href="javascript:GetAccount('ACCFRM','','RT','')">
        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a>  
      </td>
    </tr> 
    <tr> 
   	 <td nowrap ALIGN="right">
      		Tipo de Transaccion: </td>
	    <td nowrapALIGN="left" >
	  <select name="TIPTRN">
	  <option value="0"></option>		
	  <%
                ewd0012Help.initRow();
                while (ewd0012Help.getNextRow()) {
                    if (ewd0012Help.getFlag().equals("")) {
                    		out.println(ewd0012Help.getRecord());
                    }
                }
    %> 
	 
		</select>              
	     </td>
    </tr>    
        <tr> 
      <td nowrap ALIGN="right">Destino de la Transferencia:  </td>
      <td nowrap ALIGN="left"> 
      <select name="destino">
	  <option value="0"></option>				
	  <option value="1">Transferencia Nacional</option>
	  <option value="2">Transferencia Internacional</option>
	 
		</select>    
      </td>
    </tr>  
    
  </table>  

<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="Validate()">
  </div> 
</p> 
  


</form>
</body>
</html>
