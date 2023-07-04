<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cambio de Status</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
//-->
function goAction(op) {
    document.forms[0].opt.value = op;
	if (op == 4) {
		document.forms[0].SCREEN.value = 500;
		document.forms[0].submit();
	}
}
//-->
</script>

</head>
<body>
<h3 align="center">Cambio de Status<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="card_status_info.jsp, ECC0090"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getOption() %>">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= msgCD.getE01TARTYP().trim()%>">

  <h4>Información del Cliente</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Identificación del Cliente :</div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01CCRCID" size="16" maxlength="15" value="<%= msgCD.getE01CCRCID().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="36" maxlength="35" value="<%= msgCD.getE01PRINA1().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="10" maxlength="10" value="<%= msgCD.getE01PRICUN().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Tipo de Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRTYP" size="5" maxlength="4" value="<%= msgCD.getE01CCRTYP().trim()%>" readonly>
              </div>
             </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Información de la Tarjeta</h4>

	<% if (!msgCD.getE01CCRNUM().equals("") && error.getERRNUM().equals("0")) { %>
	<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD nowrap width="16%">
							<DIV align="right">Número de Tarjeta :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRNUM" size="21" maxlength="20" value="<%= msgCD.getE01CCRNUM().trim()%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right">Agencia de Entrega :</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRBRN" size="5" maxlength="4" value="<%= msgCD.getE01CCRBRN().trim()%>" readonly>
							</DIV>
						</TD>
					</TR>
					<TR id="trclear">
						<TD nowrap width="16%">
							<DIV align="right">Status :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRSTS" size="3" maxlength="2" value="<%= msgCD.getE01CCRSTS().trim()%>" readonly>
								<INPUT type="text" name="E01CCRDSC" size="16" maxlength="15" value="<%= msgCD.getE01CCRDSC().trim()%>" readonly>
								<a href="javascript:GetPlasticStatus('E01CCRSTS', 'E01CCRDSC')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right">Fecha de Entrega :</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRDLD" size="3" maxlength="2" value="<%= msgCD.getE01CCRDLD().trim()%>" readonly>
								<INPUT type="text" name="E01CCRDLM" size="3" maxlength="2" value="<%= msgCD.getE01CCRDLM().trim()%>" readonly>
								<INPUT type="text" name="E01CCRDLY" size="5" maxlength="4" value="<%= msgCD.getE01CCRDLY().trim()%>" readonly>
							</DIV>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<BR>
<DIV align="center"><INPUT type="button" name="EIBSBTNOK" id="EIBSBTN" value="Aceptar" onclick="javascript:goAction(4)"></DIV>
<% } else { %><TABLE class="tbenter" width="100%" height="25%">
	<TBODY>
		<TR>
			<TD>
			<DIV align="center"><FONT size="3"><B> El cliente no posee una tarjeta de débito asignada.</B></FONT></DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<%}%>
  
  </form>
</body>
</html>
