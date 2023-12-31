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

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Consulta de Asignaciones<BR>Detalle - Tarjeta Titular<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="assignment_inq_card.jsp, ECC0090"></h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1000">
  <INPUT TYPE=HIDDEN NAME="E02TARTYP" VALUE="<%= msgCD.getE01TARTYP().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01CCRCID" VALUE="<%= msgCD.getE01CCRCID().trim()%>">
  <INPUT TYPE=HIDDEN NAME="accmain" VALUE="">

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
              	<input type="text" name="E02CCRCID" size="16" maxlength="15" value="<%= msgCD.getE01CCRCID().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02PRINA1" size="36" maxlength="35" value="<%= msgCD.getE01PRINA1().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02PRICUN" size="10" maxlength="10" value="<%= msgCD.getE01PRICUN().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cliente :</div>
            </td>
            <td nowrap colspan="3"> 
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
								<INPUT type="text" name="E02CCRNUM" size="21" maxlength="20" value="<%= msgCD.getE01CCRNUM().trim()%>" readonly>
						        <a href="javascript:GetPlastic3('E02CCRNUM', '', '', '', '')"></a>     
							</DIV>
						</TD>
						<% if (!accList.getNoResult()) { %>
						<TD nowrap width="16%">
							<DIV align="right">Agencia de Entrega :</DIV>
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
							<DIV align="right">Status :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E02CCRSTS" size="3" maxlength="2" value="<%= userPO.getHeader4().trim()%>" readonly>
								<INPUT type="text" name="E02CCRDSC" size="16" maxlength="15" value="<%= userPO.getHeader5().trim()%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right">Fecha de Asignación :</DIV>
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
	      <div align="center"> <font size="3"><b>El cliente no posee cuentas asociadas.</b></font></div>
      </TD>
     </TR>
   	</TABLE>
<%
	} else {
%>
  <table class="tableinfo">
    <tr>
      <td>
      	<table width="100%" border="1" cellspacing="0" bordercolor="#0b23b5">
    		<tr>
    		  <td nowrap> 
       			  <table width="100%" border="0" cellpadding="2" cellspacing="0" class="tbhead">
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
						ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
						    if (msgAcc.getE02CCRASG().equals("P")) {
						        if (msgAcc.getE02CCRAPC().equals("01") ||
						        	msgAcc.getE02CCRAPC().equals("02") ||
						        	msgAcc.getE02CCRAPC().equals("03")) {
						        	ccpapcode = msgAcc.getE02CCRAPC().trim();
						        	ccp = msgAcc.getE02CCRCRA().trim();
						        	ccptype = msgAcc.getE02CCRTYP().trim();
						        	ccpsubtype = msgAcc.getE02CCRSTP().trim();
						        	if (msgAcc.getE02CCRPRI().equals("*"))
						        		accmain = 1;
						        }
				        		if (msgAcc.getE02CCRAPC().equals("04")) {
						        	capapcode = msgAcc.getE02CCRAPC().trim();
						        	cap = msgAcc.getE02CCRCRA().trim();
						        	captype = msgAcc.getE02CCRTYP().trim();
						        	capsubtype = msgAcc.getE02CCRSTP().trim();
						        	if (msgAcc.getE02CCRPRI().equals("*"))
				        				accmain = 2;
				        		}
				        		if (msgAcc.getE02CCRTYP().equals("CFAL")) {
						        	cfpapcode = msgAcc.getE02CCRAPC().trim();
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
						<div align="right">Cuenta Corriente Principal :</div>
						</td>
		        		<td nowrap>
		          			<input type="hidden" name="CCP_Apcode" value="<%= ccpapcode%>">
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
						<div align="right">Cuenta de Ahorros Principal :</div>
						</td>
		        		<td nowrap>
		          			<input type="hidden" name="CAP_Apcode" value="<%= capapcode%>">
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
						<div align="right">Cuenta F.A.L. Principal :</div>
						</td>
		        		<td nowrap>
		          			<input type="hidden" name="CFP_Apcode" value="<%= cfpapcode%>">
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
      <td align="center"><b>Cuentas Secundarias</b><br>
        <select size="5" name="AccSList" id="AccSList" onclick="document.forms[0].AccSList.multiple = false" onBlur="document.forms[0].AccSList.multiple = true">
          <%
			int aux2 = 0;
      		accList.initRow();               
      		while (accList.getNextRow()) {
      			ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
      		%>
          <% if (msgAcc.getE02CCRASG().equals("S")) { %>
			<OPTION value="<%= msgAcc.getE02CCRCRA() %>-<%= msgAcc.getE02CCRAPC()%>-<%= msgAcc.getE02CCRTYP()%>-<%= msgAcc.getE02CCRSTP()%>" label="<%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)"><%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)</OPTION>
          <% 
					aux2++;
				} %>
          <% } %>
          <% if (aux2 == 0) { %>
          <option value="X">No hay cuentas asignadas</option>
          <% } %>
        </select></td>
    </tr>
  </table>
  <%      
  }
%>

<br>
  <div align="center"> 
	   <INPUT type="submit" name="EIBSBTN" id="EIBSBTN" value="Regresar">
  </div>
</form>
</body>
</html>
