<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Retenciones y Diferidos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if(document.forms[0].E01UNCACC.value.length < 1){
alert("Please enter a valid account number.");
document.forms[0].E01UNCACC.value='';
document.forms[0].E01UNCACC.focus();
}
else {
 document.forms[0].submit();
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

<h3 align="center">Mantenimiento de Retenciones y Diferidos
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="hold_uncollected_enter.jsp , EDD0300"> 
</h3><hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0300" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  
  <table class="tbenter" height="75%" width="100%" border="0">
    <tr>
          
      <td nowrap align="center"> N&uacute;mero de Cuenta : 
        <input type="text" name="E01UNCACC" size="12" maxlength="12" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01UNCACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
        <p>
    	  <div align="center"> 
   		   <input id="EIBSBTN" type=button name="Submit" OnClick="CheckACC()" value="Enviar">
    	  </div>
  		</p> 
      </td>
    </tr>
  </table>
<script language="JavaScript">
  document.forms[0].E01UNCACC.focus();
  document.forms[0].E01UNCACC.select();
</script>

</form>
</body>
</html>
