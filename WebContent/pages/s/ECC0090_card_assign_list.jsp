<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Tarjetas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "cardList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
//-->
function goAction(op) {
    document.forms[0].opt.value = op;
	var i;
	var key;
	var temp;
	if (document.forms[0].card.length == null) {
	    key = document.forms[0].card.value;
	    temp = new Array();
	    temp = key.split('-');
	}
	else {
		for (i=0; i<document.forms[0].card.length; i++) {
			if (document.forms[0].card[i].checked) {
		    	key = document.forms[0].card[i].value;
		    	temp = new Array();
		    	temp = key.split('-');
		    }
		}
	}
	
	if (op == 1) { // New Assignment
    	document.getElementById("SCREEN").value = 1;
    	document.forms[0].submit();
	}
    else if (op == 2 || op == 3) { // Inquiry & Maintenance
    	document.getElementById("E01CCRNUM").value = temp[0];
    	document.getElementById("E01CCRTPI").value = temp[1];
    	//alert (temp[1]);
    	if (document.getElementById("E01CCRTPI").value == "T") {
    		document.getElementById("SCREEN").value = 4;
    	}
    	else {
    		document.getElementById("SCREEN").value = 8;
    	}
    	document.forms[0].submit();
	}
	else if (op == 4) { // Additional Card
    	document.getElementById("E01CCRNUM").value = temp[0];
    	document.getElementById("E01CCRTPI").value = temp[1];
    	if (document.getElementById("E01CCRTPI").value == "T") {
			document.getElementById("SCREEN").value = 5;
			document.forms[0].submit();
		}
		else {
			alert("No se puede asignar una tarjeta adicional a un cliente no titular.");
		}
	}
	else if (op == 5) { // Status Change
    	document.getElementById("E01CCRNUM").value = temp[0];
    	document.getElementById("opt").value = "A";
    	document.getElementById("SCREEN").value = 400;
		document.forms[0].submit();
	}
	else if (op == 6) {	// Card Replace
    	document.getElementById("E01CCRNUM").value = temp[0];
    	document.getElementById("E01CCRTPI").value = temp[1];
    	document.getElementById("SCREEN").value = 9;
    	document.forms[0].submit();
	}
	else if (op == 7) { // Card Print
    	document.getElementById("E01CCRNUM").value = temp[0];
    	document.getElementById("E01CCRTPI").value = temp[1];
		document.getElementById("SCREEN").value = 600;
		document.forms[0].submit();
	}
}

function goNew() {
    document.getElementById("SCREEN").value = 1;
    document.forms[0].submit();
}
//-->
</script>

</head>
<body>
<h3 align="center">Asignación de Tarjetas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="card_assign_list.jsp, ECC0090"> 
</h3>
<hr size="4">
<% 
if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
}

	int atg = 0;	// Flag de aprobación pendiente
	int tit = 0;	// Flag de asignación titular
	cardList.initRow();
	cardList.getNextRow();
	boolean firstTime = true;
	String chk = "";
	datapro.eibs.beans.ECC009001Message msgPart0 = (datapro.eibs.beans.ECC009001Message) cardList.getRecord();
	if (msgPart0.getE01CCRATG().equals("*")) atg = 1;
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01CCRNUM" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01CCRTPI" VALUE="">

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
              	<input type="text" name="E01CCRCID" size="16" maxlength="15" value="<% if(msgPart0.getE01CCRTPI().equals("T")) { out.println(userPO.getIdentifier()); } else if (msgPart0.getE01CCRTPI().equals("A")) { out.println(userPO.getHeader9()); } else { out.println(userPO.getIdentifier()); }%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="36" maxlength="35" value="<% if (msgPart0.getE01CCRTPI().equals("T")) { out.println(userPO.getCusName()); } else if (msgPart0.getE01CCRTPI().equals("A")) { out.println(userPO.getHeader11()); } else { out.println(userPO.getCusName()); }%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="16%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="10" maxlength="10" value="<% if (msgPart0.getE01CCRTPI().equals("T")) { out.println(userPO.getCusNum()); } else if (msgPart0.getE01CCRTPI().equals("A")) { out.println(userPO.getHeader10()); } else { out.println(userPO.getCusNum()); }%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CCRTYP" size="5" maxlength="4" value="<% if (msgPart0.getE01CCRTPI().equals("T")) { out.println(userPO.getCusType()); } else if (msgPart0.getE01CCRTPI().equals("A")) { out.println(userPO.getHeader12()); } else { out.println(userPO.getCusType()); }%>" readonly>
              </div>
             </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4>Tarjetas Asociadas</h4>

<% if (!msgPart0.getE01CCRNUM().equals("")) { %>
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
	<TABLE id="headTable" width="100%">
	<TBODY>
		<TR id="trdark">
			<TH align="CENTER" nowrap width="2%">&nbsp;</TH>
			<TH align="CENTER" nowrap width="20%">Número de Tarjeta</TH>
			<TH align="CENTER" nowrap width="20%">Agencia de Entrega</TH>
			<TH align="CENTER" nowrap width="18%">Status</TH>
			<TH align="CENTER" nowrap width="20%">Fecha de Entrega</TH>
			<TH align="CENTER" nowrap width="20%">Tipo de Asignación</TH>
		</TR>
		<%
		cardList.initRow(); 
        while (cardList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
            datapro.eibs.beans.ECC009001Message msgPart = (datapro.eibs.beans.ECC009001Message) cardList.getRecord();
            if (msgPart.getE01CCRTPI().equals("T")) tit = 1;
     	%>
		<TR>
			<TD nowrap><INPUT type="radio" name="card" id="card" value="<%= msgPart.getE01CCRNUM()%>-<%= msgPart.getE01CCRTPI()%>" <%=chk%>></TD>
			<TD nowrap align="CENTER"><%=Util.formatCell(msgPart.getE01CCRNUM())%></TD>
			<TD nowrap align="CENTER"><%=Util.formatCell(msgPart.getE01CCRBRN())%></TD>
			<TD nowrap align="CENTER"><%=Util.formatCell(msgPart.getE01CCRDSC())%></TD>
			<TD nowrap align="CENTER"><%=Util.formatDate(msgPart.getE01CCRDLD(),msgPart.getE01CCRDLM(),msgPart.getE01CCRDLY()) %></TD>
			<TD nowrap align="CENTER"><% if (msgPart.getE01CCRTPI().equals("T")) { out.println("Titular"); } else { out.println("Adicional"); }%></TD>
		</TR>
		<%}%>
	</TBODY>
	</TABLE>
    </table>
<% if (atg == 1) {%>
	<TABLE class="tbenter" width=100% height=10%>
	<TR>
      <TD> 
	      <div align="center"><font size="3"><b>Este cliente tiene asignaciones por aprobar.</b></font></div>
      </TD>
     </TR>
   	</TABLE>
<%}%>
<BR>
<div id="opts">
<TABLE class="tbenter" width="100%">
	<TBODY>
		<TR>
			<% if (atg == 1 || tit != 1) { %>
			<TD class="TDBKG">
				<DIV id="btnNew" align="center">
					<A href="javascript:goAction(1)"><B>Nueva Asignación</B></A>
				</DIV>
			</TD>
			<% } %>
			<TD class="TDBKG">
				<DIV id="btnInquiry" align="center">
					<A href="javascript:goAction(2)"><B>Consulta</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnMaint" align="center">
					<A href="javascript:goAction(3)"><B>Mantenimiento</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnAdditional" align="center">
					<A href="javascript:goAction(4)"><B>Tarjeta<br>Adicional</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnStatus" align="center">
					<A href="javascript:goAction(5)"><B>Cambio de<br>Status</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnReplace" align="center">
					<A href="javascript:goAction(6)"><B>Reemplazo de<br>Tarjeta</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnPrint" align="center">
					<A href="javascript:goAction(7)"><B>Impresión</B></A>
				</DIV>
			</TD>
			<TD class="TDBKG">
				<DIV id="btnExit" align="center">
					<A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A>
				</DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</div>
<% } else { %>
	<% if (atg != 1) { %>
	<TABLE class="tbenter" width=100% height=10%>
	<TR>
      <TD> 
	      <div align="center"><font size="3"><b>Este cliente no tiene asignaciones de cuentas.</b></font></div>
      </TD>
     </TR>
   	</TABLE>
	<% } else {%>
	<TABLE class="tbenter" width=100% height=10%>
	<TR>
      <TD> 
	      <div align="center"><font size="3"><b>Este cliente tiene asignaciones por aprobar.</b></font></div>
      </TD>
     </TR>
   	</TABLE>
   	<%}%>
	<TABLE class="tbenter" width="100%">
		<TBODY>
			<TR>
				<TD class="TDBKG" width="50%">
					<DIV id="btnNew" align="center">
						<A href="javascript:goNew()"><B>Nueva Asignación</B></A>
					</DIV>
				</TD>
				<TD class="TDBKG" width="50%">
					<DIV id="btnExit" align="center">
						<A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A>
					</DIV>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
<%}%>
  
  <br>
  
  </form>
</body>
</html>
