<html>
<head>
<title>Stop payments Issuance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckACC(){
if(document.forms[0].E01STPACC.value.length < 1){
alert("A valid account number must be entered");
document.forms[0].E01STPACC.value='';
document.forms[0].E01STPACC.focus();
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

<h3 align="center">Emisi&oacute;n Orden de no Pago</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="off_enter_stop_pay.jsp , EFE01000"> 
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  
  <table class="tbenter" height="75%" width="100%" border="0">
    <tr>
          
      <td nowrap align="center"> N&uacute;mero de Cuenta : 
        <input type="text" name="E01STPACC" size="12" maxlength="9" onkeypress="enterInteger()">
              <a href="javascript:GetAccount('E01STPACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
				<br>
 <div align="center"> 
	      <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckACC()">
  </div>        
            </td>
          </tr>
        </table>
      
  </form>
</body>
</html>
