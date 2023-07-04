<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
function goAction() {
	document.getElementById("SCREEN").value = 400;
	document.forms[0].submit();
}
//-->
</script>

</head>
<body>
<h3 align="center">Asignación
de Tarjetas de Débito<BR>Consulta de Tarjeta Titular<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="assign_accounts_info.jsp, ECC0090"></h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090?E01CUMCID=<%= msgCD.getE01CCRCID().trim()%>">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E02TARTYP" VALUE="<%= msgCD.getE01TARTYP().trim()%>">
  <INPUT TYPE=HIDDEN NAME="accmain" VALUE="">

  <h4> Información del Cliente</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><B>ID Cliente :</B></div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E02CCRCID" size="16" maxlength="15" value="<%= userPO.getIdentifier().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02PRINA1" size="36" maxlength="35" value="<%= userPO.getCusName().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Número de Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02PRICUN" size="10" maxlength="10" value="<%= userPO.getCusNum().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Tipo de Cliente :</b></div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="E01CCRTYP" size="5" maxlength="4" value="<%= userPO.getCusType().trim()%>" readonly>
              </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Información de la Tarjeta</h4>
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
				<TBODY>
					<TR id="trdark">
						<TD nowrap width="16%">
							<DIV align="right"><B>Número de Tarjeta :</B></DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E02CCRNUM" size="21" maxlength="20" value="<%= userPO.getHeader1().trim()%>" readonly>
						        <a href="javascript:GetPlastic3('E02CCRNUM', '', '', '', '')"></a>     
							</DIV>
						</TD>
						<% if (!accList.getNoResult()) { %>
						<TD nowrap width="16%">
							<DIV align="right"><B>Agencia de Entrega :</B></DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<INPUT type="text" name="E02CCRBRN" size="5" maxlength="4" value="<%= userPO.getHeader3().trim()%>" readonly>
							</DIV>
						</TD>
						<% } %>
					</TR>
					<% if (!accList.getNoResult()) { %>
					<TR id="trclear">
						<TD nowrap width="16%">
							<DIV align="right"><B>Status :</B></DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E02CCRSTS" size="3" maxlength="2" value="<%= userPO.getHeader4().trim()%>" readonly>
								<INPUT type="text" name="E02CCRDSC" size="16" maxlength="15" value="<%= userPO.getHeader5().trim()%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right"><B>Fecha de Asignación :</B></DIV>
						</TD>
						<TD nowrap colspan="3">
							<DIV align="left">
								<INPUT type="text" name="E02CCRASD" size="3" maxlength="2" value="<%= userPO.getHeader6().trim()%>" readonly>
								<INPUT type="text" name="E02CCRASM" size="3" maxlength="2" value="<%= userPO.getHeader7().trim()%>" readonly>
								<INPUT type="text" name="E02CCRASY" size="5" maxlength="4" value="<%= userPO.getHeader8().trim()%>" readonly>
							</DIV>
						</TD>
					</TR>
					<% } %>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<h4>Cuentas Asociadas</h4>
<%
	if ( accList.getNoResult() ) {
%>
	<TABLE class="tbenter" width=100% height=25%>
	<TR>
      <TD> 
	      <div align="center"> <font size="3"><b> El cliente no posee cuentas asociadas</b></font></div>
      </TD>
     </TR>
   	</TABLE>
<%
	} else {
%>
  <table class="tableinfo">
    <tr>
      <td rowspan="2" align="center">
  		<INPUT TYPE=HIDDEN NAME="E02CCRCRA" ID="E02CCRCRA" VALUE="">
  		<INPUT TYPE=HIDDEN NAME="E02CCRTYP" ID="E02CCRTYP" VALUE="">
  		<INPUT TYPE=HIDDEN NAME="E02CCRSTP" ID="E02CCRSTP" VALUE="">
      	<B>Listado de Cuentas</B><BR>
      <SELECT size="10" name="AccList" id="AccList" onClick="document.forms[0].AccList.multiple = false" onBlur="document.forms[0].AccList.multiple = true">
   	  <%
   	  int aux1 = 0;
      accList.initRow();               
      while (accList.getNextRow()) {
      	ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
      %>
		<% if (msgAcc.getE02CCRASG().equals("")) { %>
		<OPTION value="<%= msgAcc.getE02CCRCRA() %>" label="<%= msgAcc.getE02CCRTYP()%>-<%= msgAcc.getE02CCRSTP()%>"><%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)</OPTION>
	  	<%
	  		aux1++;
	  	} %>
	  <% } %>
	  <% if (aux1 == 0) { %>
		<OPTION value="X">No existen cuentas sin asignar</OPTION>
	  <% } %>
	  </SELECT>
	  </td>
      <td align="center" valign="bottom">
	  		</td>
	<td>
      	<table width="100%" border="1" cellspacing="0" bordercolor="#0b23b5">
    		<tr>
    		  <td nowrap> 
       			  <table width="100%" border="0" cellpadding="2" cellspacing="0" class="tbhead">
		           	  <%
						int accmain = 0;
						String ccp = "";
						String cap = "";
						String cfp = "";
						String ccptype = "";
						String captype = "";
						String cfptype = "";
						String ccpsubtype = "";
						String capsubtype = "";
						String cfpsubtype = "";
						accList.initRow();               
						while (accList.getNextRow()) {
						ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
						    if (msgAcc.getE02CCRASG().equals("P")) {
						        if (msgAcc.getE02CCRTYP().equals("CCTE") ||
						        	msgAcc.getE02CCRTYP().equals("CINL") ||
						        	msgAcc.getE02CCRTYP().equals("CTEI")) {
						        	ccp = msgAcc.getE02CCRCRA().trim();
						        	ccptype = msgAcc.getE02CCRTYP().trim();
						        	ccpsubtype = msgAcc.getE02CCRSTP().trim();
						        	if (msgAcc.getE02CCRPRI().equals("*"))
						        		accmain = 1;
						        }
				        		if (msgAcc.getE02CCRTYP().equals("CAHO")) {
						        	cap = msgAcc.getE02CCRCRA().trim();
						        	captype = msgAcc.getE02CCRTYP().trim();
						        	capsubtype = msgAcc.getE02CCRSTP().trim();
						        	if (msgAcc.getE02CCRPRI().equals("*"))
				        				accmain = 2;
				        		}
				        		if (msgAcc.getE02CCRTYP().equals("CFAL")) {
						        	cfp = msgAcc.getE02CCRCRA().trim();
						        	cfptype = msgAcc.getE02CCRTYP().trim();
						        	cfpsubtype = msgAcc.getE02CCRSTP().trim();
						        	if (msgAcc.getE02CCRPRI().equals("*"))
				        				accmain = 3;
				        		}
				        	}
				        }
				        %>
		      		  <tr id="trdark">
		        		  <th colspan="2">Cuentas Principales de la Tarjeta</th>
						<TH>Principal</TH>
					</tr>
		      		  <tr id="trclear">
		        		<td nowrap align="right">
						<div align="right"><B>Cuenta Corriente Principal :</B></div>
						</td>
		        		<td nowrap>
		          			<input type="text" name="CCP" size="21" maxlength="20" value="<%= ccp%>" readonly>
		          			<input type="text" name="CCP_Type" size="5" maxlength="4" value="<%= ccptype%>" readonly>
		          			<input type="text" name="CCP_Subype" size="5" maxlength="4" value="<%= ccpsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">
							<INPUT type="radio" name="CC_Main" value="<%= ccp%>" <% if (accmain == 1) { %> checked <% } %> disabled>
		          			<% if (accmain == 1) {%>
		          			<img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
		          			<%}%>
						</TD>
					</tr>
		      		  <tr id="trdark">
		        		<td nowrap align="right">
						<div align="right"><B>Cuenta de Ahorros Principal :</B></div>
						</td>
		        		<td nowrap>
		          			<input type="text" name="CAP" size="21" maxlength="20" value="<%= cap%>" readonly>
		          			<input type="text" name="CAP_Type" size="5" maxlength="4" value="<%= captype%>" readonly>
		          			<input type="text" name="CAP_Subype" size="5" maxlength="4" value="<%= capsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">
							<INPUT type="radio" name="CC_Main" value="<%= cap%>" <% if (accmain == 2) { %> checked <% } %> disabled>
	          			    <% if (accmain == 2) {%>
	          			    <img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
	          			    <%}%>
						</TD>
					</tr>
		      		  <tr id="trclear">
		        		<td nowrap align="right">
						<div align="right"><B>Cuenta F.A.L. Principal :</B></div>
						</td>
		        		<td nowrap>
		          			<input type="text" name="CFP" size="21" maxlength="20" value="<%= cfp%>" readonly>
		          			<input type="text" name="CFP_Type" size="5" maxlength="4" value="<%= cfptype%>" readonly>
		          			<input type="text" name="CFP_Subype" size="5" maxlength="4" value="<%= cfpsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">
							<INPUT type="radio" name="CC_Main" value="<%= cfp%>" <% if (accmain == 3) { %> checked <% } %> disabled>
	          			    <% if (accmain == 3) {%>
	          			    <img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
	          			    <%}%>
						</TD>
					</tr>
	      	    </table>
	      	  </td>
			</tr>
      	</table>
      </td>
    </tr>
    <tr>
      <td align="center">
      </td>
      <td align="center">      
      	<B>Cuentas Secundarias</B><BR>
		<SELECT size="5" name="AccSList" id="AccSList" onClick="document.forms[0].AccSList.multiple = false" onBlur="document.forms[0].AccSList.multiple = true">
			<%
			int aux2 = 0;
      		accList.initRow();               
      		while (accList.getNextRow()) {
      			ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
      		%>
				<% if (msgAcc.getE02CCRASG().equals("S")) { %>
				<OPTION value="<%= msgAcc.getE02CCRCRA() %>" label="<%= msgAcc.getE02CCRTYP()%>-<%= msgAcc.getE02CCRSTP()%>"><%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)</OPTION>
				<% 
					aux2++;
				} %>
			<% } %>
			<% if (aux2 == 0) { %>
				<OPTION value="X">No hay cuentas asignadas</OPTION>
			<% } %>
		</SELECT>
	  </td>
    </tr>
  </table>
  <%      
  }
%>

<br>
  <div align="center"> 
	   <INPUT type="button" name="EIBSBTN" id="EIBSBTN" value="Aceptar" onclick="javascript:goAction()">
  </div>
</form>
</body>
</html>
