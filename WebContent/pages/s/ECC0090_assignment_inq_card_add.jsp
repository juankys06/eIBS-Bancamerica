<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Asignaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009003Message"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
function goAction() {
	document.getElementById("SCREEN").value = 1000;
	document.forms[0].submit();
}
//-->
</script>

</head>
<body>
<h3 align="center">Consulta de Asignaciones<BR>Detalle - Tarjeta Adicional<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="assignment_inq_card_add.jsp, ECC0090"></h3>
<hr size="4">
<% 
if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
    out.println("       showErrors()");
    out.println("</SCRIPT>");
}
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E04TARTYP" VALUE="<%= msgCD.getE03TARTYPA().trim()%>">
  <INPUT TYPE=HIDDEN NAME="special" VALUE="">

  <h4> Información del Cliente Titular</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Identificación del Cliente :</div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E03CCRCID" size="16" maxlength="15" value="<%= msgCD.getE03CCRCID().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03PRINA1" size="36" maxlength="35" value="<%= msgCD.getE03PRINA1().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03PRICUN" size="10" maxlength="10" value="<%= msgCD.getE03PRICUN().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cliente :</div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="E03CCRTYP" size="5" maxlength="4" value="<%= msgCD.getE03CCRTYP().trim()%>" readonly>
              </div>
            </td>
          </tr>            
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Número de Tarjeta Principal :</div>
            </td>
            <td nowrap width="16%">
              <div align="left"> 
            	<INPUT type="text" name="E03CCRNUM" size="21" maxlength="20" value="<%= msgCD.getE03CCRNUM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"></td>
            <td nowrap width="20%"></td>
          </tr>    
        </table>
      </td>
    </tr>
  </table>
  <h4> Información del Cliente Adicional</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Identificación del Cliente :</div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E03CCRCIDA" size="16" maxlength="15" value="<%= msgCD.getE03CCRCIDA().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03PRINA1A" size="36" maxlength="35" value="<%= msgCD.getE03PRINA1A().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03PRICUNA" size="10" maxlength="10" value="<%= msgCD.getE03PRICUNA().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%"></td>
            <td nowrap colspan="3"></td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Información de la Tarjeta Adicional</h4>
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
								<INPUT type="text" name="E04CCRNUMA" size="21" maxlength="20" value="<%= msgCD.getE03CCRNUMA().trim()%>" readonly>
						        <a href="javascript:GetPlastic3('E02CCRNUM', '', '', '', '')"></a>     
							</DIV>
						</TD>
						<% if (!accList.getNoResult()) { %>
						<TD nowrap width="16%">
							<DIV align="right">Agencia de Entrega :</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="left">
								<INPUT type="text" name="E04CCRBRN" size="5" maxlength="4" value="<%= userPO.getHeader3().trim()%>" readonly>
							</DIV>
						</TD>
						<% } %>
					</TR>
					<% if (!accList.getNoResult()) { %>
					<TR id="trclear">
						<TD nowrap width="16%">
							<DIV align="right">Status :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E04CCRSTS" size="3" maxlength="2" value="<%= userPO.getHeader4().trim()%>" readonly>
								<INPUT type="text" name="E04CCRDSC" size="16" maxlength="15" value="<%= userPO.getHeader5().trim()%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right">Fecha de Asignación :</DIV>
						</TD>
						<TD nowrap colspan="3">
							<DIV align="left">
								<INPUT type="text" name="E04CCRASD" size="3" maxlength="2" value="<%= userPO.getHeader6().trim()%>" readonly>
								<INPUT type="text" name="E04CCRASM" size="3" maxlength="2" value="<%= userPO.getHeader7().trim()%>" readonly>
								<INPUT type="text" name="E04CCRASY" size="5" maxlength="4" value="<%= userPO.getHeader8().trim()%>" readonly>
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

<h4>Cuentas Asociadas al Cliente Titular</h4>
<%
	if ( accList.getNoResult() ) {
%>
	<TABLE class="tbenter" width=100% height=25%>
	<TR>
      <TD> 
	      <div align="center"><font size="3"><b>El cliente no posee cuentas asociadas.</b></font></div>
      </TD>
     </TR>
   	</TABLE>
<%
	} else {
%>
  <table class="tableinfo">
    <tr>
      <td align="center">
		<TABLE width="100%" border="1" cellspacing="0" bordercolor="#0b23b5">
			<TBODY>
				<TR>
					<TD nowrap>
					<TABLE width="100%" border="0" cellpadding="2" cellspacing="0"
						class="tbhead">
						<%
						int accmain = 0;
						String ccpapcode = "";
						String capapcode = "";
						String cfpapcode = "";
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
						ECC009004Message msgAcc = (ECC009004Message) accList.getRecord();
						    if (msgAcc.getE04CCRASG().equals("P")) {
						        if (msgAcc.getE04CCRAPC().equals("01") ||
						        	msgAcc.getE04CCRAPC().equals("02") ||
						        	msgAcc.getE04CCRAPC().equals("03")) {
						        	ccpapcode = msgAcc.getE04CCRAPC().trim();
						        	ccp = msgAcc.getE04CCRCRA().trim();
						        	ccptype = msgAcc.getE04CCRTYP().trim();
						        	ccpsubtype = msgAcc.getE04CCRSTP().trim();
						        	if (msgAcc.getE04CCRPRI().equals("*"))
						        		accmain = 1;
						        }
				        		if (msgAcc.getE04CCRAPC().equals("04")) {
						        	capapcode = msgAcc.getE04CCRAPC().trim();
						        	cap = msgAcc.getE04CCRCRA().trim();
						        	captype = msgAcc.getE04CCRTYP().trim();
						        	capsubtype = msgAcc.getE04CCRSTP().trim();
						        	if (msgAcc.getE04CCRPRI().equals("*"))
				        				accmain = 2;
				        		}
				        		if (msgAcc.getE04CCRTYP().equals("CFAL")) {
						        	cfpapcode = msgAcc.getE04CCRAPC().trim();
						        	cfp = msgAcc.getE04CCRCRA().trim();
						        	cfptype = msgAcc.getE04CCRTYP().trim();
						        	cfpsubtype = msgAcc.getE04CCRSTP().trim();
						        	if (msgAcc.getE04CCRPRI().equals("*"))
				        				accmain = 3;
				        		}
				        	}
				        }
				        %>
						<TBODY>
							<TR id="trdark">
								<TH colspan="2">Cuentas Principales de la Tarjeta</TH>
								<TH>Principal</TH>
							</TR>
							<TR id="trclear">
								<TD nowrap align="right">
								<DIV align="right">Cuenta Corriente Principal :</DIV>
								</TD>
								<TD nowrap>
				          			<input type="hidden" name="CCP_Apcode" value="<%= ccpapcode%>">
									<INPUT type="text" name="CCP" size="21"	maxlength="20" value="<%= ccp%>" readonly>
									<INPUT type="text" name="CCP_Type" size="5" maxlength="4" value="<%= ccptype%>" readonly>
									<INPUT type="text" name="CCP_Subtype" size="5" maxlength="4" value="<%= ccpsubtype%>" readonly>
								</TD>
								<TD nowrap align="left">
									<INPUT type="radio" name="CC_Main" value="<%= ccp%>" <% if (accmain == 1) { %> checked <% } %> disabled>
									<% if (accmain == 1) {%>
									<IMG src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
									<%}%>
								</TD>
							</TR>
							<TR id="trdark">
								<TD nowrap align="right">
								<DIV align="right">Cuenta de Ahorros Principal :</DIV>
								</TD>
								<TD nowrap>
				          			<input type="hidden" name="CAP_Apcode" value="<%= capapcode%>">
				          			<input type="text" name="CAP" size="21" maxlength="20" value="<%= cap%>" readonly>
				          			<input type="text" name="CAP_Type" size="5" maxlength="4" value="<%= captype%>" readonly>
				        			<input type="text" name="CAP_Subtype" size="5" maxlength="4" value="<%= capsubtype%>" readonly>
				        		</td>
								<TD nowrap align="left">
									<INPUT type="radio" name="CC_Main" value="<%= cap%>" <% if (accmain == 2) { %> checked <% } %> disabled>
			          			    <% if (accmain == 2) {%>
			          			    <img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
			          			    <%}%>
								</TD>
							</TR>
							<TR id="trclear">
								<TD nowrap align="right">
								<DIV align="right">Cuenta F.A.L. Principal :</DIV>
								</TD>
				        		<td nowrap>
				          			<input type="hidden" name="CFP_Apcode" value="<%= cfpapcode%>">
				          			<input type="text" name="CFP" size="21" maxlength="20" value="<%= cfp%>" readonly>
				          			<input type="text" name="CFP_Type" size="5" maxlength="4" value="<%= cfptype%>" readonly>
				        			<input type="text" name="CFP_Subtype" size="5" maxlength="4" value="<%= cfpsubtype%>" readonly>
				        		</td>
								<TD nowrap align="left">  
									<INPUT type="radio" name="CC_Main" value="<%= cfp%>" <% if (accmain == 3) { %> checked <% } %> disabled>
			          			    <% if (accmain == 3) {%>
			          			    <img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
			          			    <%}%>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	<td align="center">
      	
      <B>Cuentas Secundarias</B><br><SELECT size="5" name="AccSList" id="AccSList"
			onclick="document.forms[0].AccSList.multiple = false"
			onblur="document.forms[0].AccSList.multiple = true">
			<%
			int aux2 = 0;
      		accList.initRow();               
      		while (accList.getNextRow()) {
      			ECC009004Message msgAcc = (ECC009004Message) accList.getRecord();
      		%>
			<% if (msgAcc.getE04CCRASG().equals("S")) { %>
			<OPTION value="<%= msgAcc.getE04CCRCRA() %>-<%= msgAcc.getE04CCRAPC()%>-<%= msgAcc.getE04CCRTYP()%>-<%= msgAcc.getE04CCRSTP()%>" label="<%= msgAcc.getE04CCRCRA() %> (<%= msgAcc.getE04CCRTYP() %>/<%= msgAcc.getE04CCRSTP() %>)"><%= msgAcc.getE04CCRCRA() %> (<%= msgAcc.getE04CCRTYP() %>/<%= msgAcc.getE04CCRSTP() %>)</OPTION>
			<% 
					aux2++;
				} %>
			<% } %>
			<% if (aux2 == 0) { %>
			<OPTION value="X">No hay cuentas asignadas</OPTION>
			<% } %>
		</SELECT></td>
    </tr>
  </table>
  <%      
  }
%>

<br>
  <div align="center"> 
	   <INPUT type="button" name="EIBSBTN" id="EIBSBTN" value="Regresar" onclick="javascript:goAction()">
  </div>
</form>
</body>
</html>
