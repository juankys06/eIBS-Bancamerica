<html>
<head>
<title>Consulta de Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT>

 function getChkDet() {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=6";
	CenterWindow(page,600,500,2);

 }
</SCRIPT>
</head>
<body nowrap>
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
<h3 align="center">Cheques de Gerencia - Orden de No Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chk_sel.jsp,ETL0510"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSETL0510" >

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
  <INPUT TYPE=HIDDEN NAME="E01OFMBNK" value = "">
  <INPUT TYPE=HIDDEN NAME="E01OFMBRN" value = "">
  <INPUT TYPE=HIDDEN NAME="E01OFMMCH" value = "">
  	   
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr><td>&nbsp;</td></tr>
		   <tr><td>&nbsp;</td></tr>
		    <tr><td>&nbsp;</td></tr>
			 <tr><td>&nbsp;</td></tr>
		  <tr>
            <td nowrap width="50%">
              <div align="right">C&oacute;digo de Moneda : </div>
            </td>
            <td nowrap width="50%">
              <input type="text" name="E01OFMCCY" size="4" maxlength="3">
              <a href="javascript:GetCurrency('E01OFMCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="absbottom" border="0"></a> 
            </td>
          </tr>
		  <tr> 
            <td nowrap width="50%"> 
              <div align="right">Ingrese el n&uacute;mero de cheque :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01OFMNCH" size="10" maxlength="9" onKeypress="enterInteger()">
              <a href="javascript:getChkDet()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
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
<script language="JavaScript">
  document.forms[0].E01OFMNCH.focus();
  document.forms[0].E01OFMNCH.select();
</script>
  </form>
</body>
</html>
