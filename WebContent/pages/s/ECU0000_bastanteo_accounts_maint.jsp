<html> 
<head>
<title>Mantenimiento de Cuentas Jur&iacute;dicas Relacionadas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgMTAcc" class= "datapro.eibs.beans.ECU000003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

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

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 30;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Mantenimiento de Cuentas Jur&iacute;dicas Relacionadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_accounts_maint,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="33">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E03CRLCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E03CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
			<TR> 
		  		<TD nowrap width="40%"> 
		     		<DIV align="right">Tipo de Cuenta : </DIV>        
		  		</TD>
      	 	    <TD nowrap width="60%"> 
      	   			<INPUT type="text" readonly name="E03CRLTYP" size="15" maxlength="12"
                     value="<% if (msgMTAcc.getE03CRLTYP().equals("P")) { out.print("Primaria"); }
	                    	else if (msgMTAcc.getE03CRLTYP().equals("R")) { out.print("Relacionada"); }
							else { out.print(""); } %>" > 
      	  		</TD>     
      		</TR>
      		<TR> 
	      		<TD nowrap width="40%">
					<DIV align="right">N&uacute;mero de Cuenta:</DIV>
		  		</TD>
		  		<TD nowrap width="60%">
		  			<INPUT type="text" name="E03CRLACC" size="15" maxlength="12" readonly value="<%= msgMTAcc.getE03CRLACC() %>">
		  		</TD>
     	    </TR>
 		</TABLE>
 	  </td>
   </tr>
  </table>
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E03CRLOB1" size="90" maxlength="80" value="<%= msgMTAcc.getE03CRLOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E03CRLOB2" size="90" maxlength="80" value="<%= msgMTAcc.getE03CRLOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E03CRLOB3" size="90" maxlength="80" value="<%= msgMTAcc.getE03CRLOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E03CRLOB4" size="90" maxlength="80" value="<%= msgMTAcc.getE03CRLOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E03CRLOB5" size="90" maxlength="80" value="<%= msgMTAcc.getE03CRLOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>
