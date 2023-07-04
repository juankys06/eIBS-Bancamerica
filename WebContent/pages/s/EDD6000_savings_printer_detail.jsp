<html>
<head>
<title>Impresion Datos del Titular en Libreta de Ahorros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EDD600001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "R") { 
    	document.forms[0].SCREEN.value = 100;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

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

<H3 align="center">Impresion Datos del Titular en Libreta de Ahorros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="savings_printer_detail,EDD6000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD6000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="<%= msgMT.getH01FLGWK1() %>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK2" VALUE="<%= msgMT.getH01FLGWK2() %>">
  <INPUT TYPE=HIDDEN NAME="E01FMTBCC" VALUE="<%= msgMT.getE01FMTBCC() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSNA2" VALUE="<%= msgMT.getE01CUSNA2() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSNA3" VALUE="<%= msgMT.getE01CUSNA3() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSNA4" VALUE="<%= msgMT.getE01CUSNA4() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSTI1" VALUE="<%= msgMT.getE01CUSTI1() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSTI2" VALUE="<%= msgMT.getE01CUSTI2() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSTI3" VALUE="<%= msgMT.getE01CUSTI3() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSTI4" VALUE="<%= msgMT.getE01CUSTI4() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSID1" VALUE="<%= msgMT.getE01CUSID1() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSID2" VALUE="<%= msgMT.getE01CUSID2() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSID3" VALUE="<%= msgMT.getE01CUSID3() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSID4" VALUE="<%= msgMT.getE01CUSID4() %>">
  
  
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E01CUSNA1" size="50" maxlength="45" value="<%= msgMT.getE01CUSNA1() %>" readonly>  
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Cedula de Identidad :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E01CUSIDN" size="17" maxlength="15" value="<%= msgMT.getE01CUSIDN() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">N&uacute;mero de Cuenta :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E01CUSACC" size="15" maxlength="12" value="<%= msgMT.getE01CUSACC() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">N&uacute;mero de Libreta :</DIV>
				</TD>
				<TD nowrap width="60%">
				<!--
					<INPUT type="text" name="E01CUSF01" size="2" maxlength="2" value="<%= msgMT.getE01CUSF01() %>" readonly> 
				-->
					<INPUT type="text" name="E01CUSPBN" size="10" maxlength="10" value="<%= msgMT.getE01CUSPBN() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Motivo</DIV>
				</TD>
			<td nowrap width="60%"> 
              <input type="text" name="E01CUSMOT" readonly value="<% if(msgMT.getE01CUSMOT().equals("N")) out.print("Nuevo");
              				else if(msgMT.getE01CUSMOT().equals("R")) out.print("Reposicion");
							else if(msgMT.getE01CUSMOT().equals("E")) out.print("Extravio");%>" size="15">
            </td>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Sucursal :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E01CUSBRN" size="4" maxlength="3" value="<%= msgMT.getE01CUSBRN() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Fecha :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E01CUSRDD" size="3" maxlength="2" value="<%= msgMT.getE01CUSRDD() %>" readonly> 
					<INPUT type="text" name="E01CUSRDM" size="3" maxlength="2" value="<%= msgMT.getE01CUSRDM() %>" readonly> 
					<INPUT type="text" name="E01CUSRDY" size="5" maxlength="4" value="<%= msgMT.getE01CUSRDY() %>" readonly> 
				</TD>
			</TR>

 		</TABLE>
 	  </td>
   </tr>
   </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Regresar" onclick="goAction('R')">
  </p>
  
</form>
</body>
</html>
