<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Tarjetas</title>
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
	var client = document.forms[0].E01CCRTYP.value;
	//var type = client.substr(0,2);
	//alert(type == "PR");
	var acc1 = document.forms[0].CCP_Subtype.value;
	var acc2 = document.forms[0].CAP_Subtype.value;
	var acc3 = document.forms[0].CFP_Subtype.value;
	var subtype1 = acc1.substr(2,2);
	//alert(subtype1);
	var subtype2 = acc2.substr(2,2);
	//alert(subtype2);
	var subtype3 = acc3.substr(2,2);
	//alert(subtype3);
	var rtcount = document.forms[0].CCORS.value;
	var svcount = document.forms[0].CAHOS.value;
	var falcount = document.forms[0].CFALS.value;

	var acc = document.getElementById("AccList");
	var E02CCRSTP = document.getElementById("E02CCRSTP");
	var key;
	var temp;
	var special;
	//for (i=0; i<acc.length; i++) {
	//	acc[i].selected = true;
	//	key = acc[i].label;
	//	temp = new Array();
	//	temp = key.split('-');
	//	E02CCRSTP.value = temp[1];
	//	if (E02CCRSTP.value.substr(2) == "PR" || E02CCRSTP.value.substr(2) == "PL") {
	//		special = E02CCRSTP.value.substr(2);
	//	}
	//}
	
	if (!document.forms[0].CC_Main[0].checked && !document.forms[0].CC_Main[1].checked && !document.forms[0].CC_Main[2].checked) {
		alert("Por favor, establezca la cuenta principal de la tarjeta.");
	}
	else {
		//if (special == "PR" || special == "PL") {
		//	alert("Hay una cuenta premium o platinum en el listado. Por favor, asígnela como cuenta principal.");
		//}
		if (document.forms[0].CC_Main[0].checked && document.forms[0].CCP.value == "") {
			alert("No se puede asignar una cuenta corriente en blanco como la cuenta principal de la tarjeta.");
		}
		//else if (!(document.forms[0].CC_Main[0].checked) && type == "PR" && subtype1 == "PR") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta premium. Por favor, seleccione la cuenta corriente.");
		//}
		//else if (!(document.forms[0].CC_Main[0].checked) && type == "PL" && subtype1 == "PL") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta platinum. Por favor, seleccione la cuenta corriente.");
		//}
		else if (document.forms[0].CC_Main[1].checked && document.forms[0].CAP.value == "") {
			alert("No se puede asignar una cuenta de ahorros en blanco como la cuenta principal de la tarjeta.");
		}
		//else if (!(document.forms[0].CC_Main[1].checked) && type == "PR" && subtype2 == "PR") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta premium. Por favor, seleccione la cuenta de ahorros.");
		//}
		//else if (!(document.forms[0].CC_Main[1].checked) && type == "PL" && subtype2 == "PL") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta platinum. Por favor, seleccione la cuenta de ahorros.");
		//}
		else if (document.forms[0].CC_Main[2].checked && document.forms[0].CFP.value == "") {
			alert("No se puede asignar una cuenta F.A.L. en blanco como la cuenta principal de la tarjeta.");
		}
		//else if (!(document.forms[0].CC_Main[2].checked) && type == "PR" && subtype3 == "PR") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta premium. Por favor, seleccione la cuenta F.A.L.");
		//}
		//else if (!(document.forms[0].CC_Main[2].checked) && type == "PL" && subtype3 == "PL") {
		//	alert("La cuenta principal para este cliente debe ser la cuenta platinum. Por favor, seleccione la cuenta F.A.L.");
		//}
		//else if (document.forms[0].CCP.value == "" && rtcount == "") {
		//	alert("El cliente sólo dispone de una sola cuenta corriente. Por favor, asígnela como cuenta corriente principal.");
		//}
		//else if (document.forms[0].CAP.value == "" && svcount == "") {
		//	alert("El cliente sólo dispone de una sola cuenta de ahorros. Por favor, asígnela como cuenta de ahorros principal.");
		//}
		//else if (document.forms[0].CFP.value == "" && falcount == "") {
		//	alert("El cliente sólo dispone de una sola cuenta F.A.L. Por favor, asígnela como cuenta F.A.L. principal.");
		//}
		else {
			for (i=0; i<document.forms[0].AccList.length; i++) {
				document.forms[0].AccList[i].selected = true;
			}
			if (document.forms[0].AccList[0].value == "X") {
				document.forms[0].AccList.remove(0);
			}
			for (j=0; j<document.forms[0].AccSList.length; j++) {
				document.forms[0].AccSList[j].selected = true;
			}
			if (document.forms[0].AccSList[0].value == "X") {
				document.forms[0].AccSList.remove(0);
			}
	   		document.getElementById("SCREEN").value = 3;
	   		document.forms[0].submit();
	   	}
   	}
}

function add(num) {
	var acc = document.getElementById("AccList");
	var i = acc.selectedIndex;
	var E02CCRCRA = document.getElementById("E02CCRCRA");
	var E02CCRAPC = document.getElementById("E02CCRAPC");
	var E02CCRTYP = document.getElementById("E02CCRTYP");
	var E02CCRSTP = document.getElementById("E02CCRSTP");
	if (i == -1) {
		alert("Por favor, seleccione una cuenta del listado para asignar.");
	}
	else {
		var key = acc[i].value;
    	var temp = new Array();
    	temp = key.split('-');
		E02CCRCRA.value = temp[0];
    	E02CCRAPC.value = temp[1];
    	E02CCRTYP.value = temp[2];
    	E02CCRSTP.value = temp[3];
    	//alert(E02CCRCRA.value);
    	//alert(E02CCRAPC.value);
    	//alert(E02CCRTYP.value);
    	//alert(E02CCRSTP.value);
		
		if (num == 1) {
			if (E02CCRAPC.value != "01" &&
				E02CCRAPC.value != "02" &&
				E02CCRAPC.value != "03") {
				alert("La cuenta seleccionada no es una cuenta corriente.");
			}
			else {
				var apcode1 = document.getElementById("CCP_Apcode");
				var ccp = document.getElementById("CCP");
				var type1 = document.getElementById("CCP_Type");
				var subtype1 = document.getElementById("CCP_Subtype");
				if (ccp.value != "") {
					alert("Disculpe, hay una cuenta corriente asignada. Quítela primero y vuelva a intentarlo.");
				}
				else {
					apcode1.value = E02CCRAPC.value;
					ccp.value = E02CCRCRA.value;
					document.forms[0].CC_Main[0].value = E02CCRCRA.value;
					type1.value = E02CCRTYP.value;
					subtype1.value = E02CCRSTP.value;
					acc.remove(i);
				}
			}
		}
		if (num == 2) {
			if (E02CCRAPC.value != "04") {
				alert("La cuenta seleccionada no es una cuenta de ahorros.");
			}
			else {
				var apcode2 = document.getElementById("CAP_Apcode");
				var cap = document.getElementById("CAP");
				var type2 = document.getElementById("CAP_Type");
				var subtype2 = document.getElementById("CAP_Subtype");
				if (cap.value != "") {
					alert("Disculpe, hay una cuenta de ahorros asignada. Quítela primero y vuelva a intentarlo.");
				}
				else {
					apcode2.value = E02CCRAPC.value;
					cap.value = E02CCRCRA.value;
					document.forms[0].CC_Main[1].value = E02CCRCRA.value;
					type2.value = E02CCRTYP.value;
					subtype2.value = E02CCRSTP.value;
					acc.remove(i);
				}
			}
		}
		if (num == 3) {
			if (E02CCRTYP.value != "CFAL") {
				alert("La cuenta seleccionada no es una cuenta de fondo de activos líquidos.");
			}
			else {
				var apcode3 = document.getElementById("CFP_Apcode");
				var cfp = document.getElementById("CFP");
				var type3 = document.getElementById("CFP_Type");
				var subtype3 = document.getElementById("CFP_Subtype");
				if (cfp.value != "") {
					alert("Disculpe, hay una cuenta F.A.L. asignada. Quítela primero y vuelva a intentarlo.");
				}
				else {
					apcode3.value = E02CCRAPC.value;
					cfp.value = E02CCRCRA.value;
					document.forms[0].CC_Main[2].value = E02CCRCRA.value;
					type3.value = E02CCRTYP.value;
					subtype3.value = E02CCRSTP.value;
					acc.remove(i);
				}
			}
		}
		if (num == 4) {
			if (E02CCRAPC.value != "01" &&
				E02CCRAPC.value != "02" &&
				E02CCRAPC.value != "03" &&
				E02CCRAPC.value != "04" &&
				E02CCRTYP.value != "CFAL") {
				alert("La cuenta seleccionada no puede ser asignada como cuenta secundaria.");
			}
			else {
				var option = new Option(E02CCRCRA.value + " (" + E02CCRTYP.value + "/" + E02CCRSTP.value + ")",
					E02CCRCRA.value + "-" + E02CCRAPC.value + "-" + E02CCRTYP.value + "-" + E02CCRSTP.value, false);
				var accs = document.getElementById("AccSList");
				if (accs[0].value == "X") {
					accs.remove(0);
				}
				accs.options[accs.length] = option;
				accs[accs.length-1].label = E02CCRCRA.value + " (" + E02CCRTYP.value + "/" + E02CCRSTP.value + ")";
				acc.remove(i);
			}
		}
		if (acc.length == 0) {
			var option = new Option("No existen cuentas sin asignar", "X", false);
			acc.options[acc.length] = option;
		}
	}
}

function del(num) {
	var accs = document.getElementById("AccSList");
	var i = accs.selectedIndex;
	var apcode1 = document.getElementById("CCP_Apcode");
	var apcode2 = document.getElementById("CAP_Apcode");
	var apcode3 = document.getElementById("CFP_Apcode");
	var ccp = document.getElementById("CCP");
	var cap = document.getElementById("CAP");
	var cfp = document.getElementById("CFP");
	var type1 = document.getElementById("CCP_Type");
	var type2 = document.getElementById("CAP_Type");
	var type3 = document.getElementById("CFP_Type");
	var subtype1 = document.getElementById("CCP_Subtype");
	var subtype2 = document.getElementById("CAP_Subtype");
	var subtype3 = document.getElementById("CFP_Subtype");
	var E02CCRCRA = document.getElementById("E02CCRCRA");
	var E02CCRAPC = document.getElementById("E02CCRAPC");
	var E02CCRTYP = document.getElementById("E02CCRTYP");
	var E02CCRSTP = document.getElementById("E02CCRSTP");
	if (num == 1) {
		if (ccp.value == "") {
			alert("Disculpe, no hay una cuenta corriente asignada.");
		}
		else {
			var option = new Option(ccp.value + " (" + type1.value + "/" + subtype1.value + ")",
				ccp.value + "-" + apcode1.value + "-" + type1.value + "-" + subtype1.value, false);
			var acc = document.getElementById("AccList");
			if (acc[0].value == "X") {
				acc.remove(0);
			}
			acc.options[acc.length] = option;
			acc[acc.length-1].label = ccp.value + " (" + type1.value + "/" + subtype1.value + ")";
			apcode1.value = "";
			ccp.value = "";
			document.forms[0].CC_Main[0].value = "";
			type1.value = "";
			subtype1.value = "";
		}
	}
	if (num == 2) {
		if (cap.value == "") {
			alert("Disculpe, no hay una cuenta de ahorros asignada.");
		}
		else {
			var option = new Option(cap.value + " (" + type2.value + "/" + subtype2.value + ")",
				cap.value + "-" + apcode2.value + "-" + type2.value + "-" + subtype2.value, false);
			var acc = document.getElementById("AccList");
			if (acc[0].value == "X") {
				acc.remove(0);
			}
			acc.options[acc.length] = option;
			acc[acc.length-1].label = cap.value + " (" + type2.value + "/" + subtype2.value + ")";
			apcode2.value = "";
			cap.value = "";
			document.forms[0].CC_Main[1].value = "";
			type2.value = "";
			subtype2.value = "";
		}
	}
	if (num == 3) {
		if (cfp.value == "") {
			alert("Disculpe, no hay una cuenta F.A.L. asignada.");
		}
		else {
			var option = new Option(cfp.value + " (" + type3.value + "/" + subtype3.value + ")",
				cfp.value + "-" + apcode3.value + "-" + type3.value + "-" + subtype3.value, false);
			var acc = document.getElementById("AccList");
			if (acc[0].value == "X") {
				acc.remove(0);
			}
			acc.options[acc.length] = option;
			acc[acc.length-1].label = cfp.value + " (" + type3.value + "/" + subtype3.value + ")";
			apcode3.value = "";
			cfp.value = "";
			document.forms[0].CC_Main[2].value = "";
			type3.value = "";
			subtype3.value = "";
		}
	}
	if (num == 4) {
		if (i == -1) {
			alert("Por favor, seleccione una cuenta secundaria para quitar.");
		}
		else {
			var key = accs[i].value;
			var temp = new Array();
			temp = key.split('-');
			E02CCRCRA.value = temp[0];
			E02CCRAPC.value = temp[1];
			E02CCRTYP.value = temp[2];
			E02CCRSTP.value = temp[3];
			//alert(E02CCRCRA.value);
			//alert(E02CCRAPC.value);
			//alert(E02CCRTYP.value);
			//alert(E02CCRSTP.value);
			
			var option = new Option(E02CCRCRA.value + " (" + E02CCRTYP.value + "/" + E02CCRSTP.value + ")",
				E02CCRCRA.value + "-" + E02CCRAPC.value + "-" + E02CCRTYP.value + "-" + E02CCRSTP.value, false);
			var acc = document.getElementById("AccList");
			if (acc[0].value == "X") {
				acc.remove(0);
			}
			acc.options[acc.length] = option;
			acc[acc.length-1].label = E02CCRCRA.value + " (" + E02CCRTYP.value + "/" + E02CCRSTP.value + ")";
			accs.remove(i);
		}
	}
	if (accs.length == 0) {
		var option = new Option("No hay cuentas asignadas", "X", false);
		accs.options[accs.length] = option;
	}
}
//-->
</script>

</head>
<body>
<h3 align="center">Asignación de Tarjetas<BR>Mantenimiento de Tarjeta Titular<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="card_assign_maint.jsp, ECC0090"></h3>
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
  <INPUT TYPE=HIDDEN NAME="E02TARTYP" VALUE="<%= msgCD.getE01TARTYP().trim()%>">
  <INPUT TYPE=HIDDEN NAME="special" VALUE="">
  <input type=hidden name="new" value="<%= userPO.getHeader21()%>">

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
              	<input type="text" name="E02CCRCID" size="16" maxlength="15" value="<%= userPO.getIdentifier().trim()%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02PRINA1" size="36" maxlength="35" value="<%= userPO.getCusName().trim()%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02PRICUN" size="10" maxlength="10" value="<%= userPO.getCusNum().trim()%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cliente :</div>
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
								<INPUT type="text" name="E02CCRNUM" size="21" maxlength="20" value="<%= userPO.getHeader1().trim()%>" readonly>
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
	      <div align="center"> <font size="3"><b> El cliente no posee cuentas asociadas.</b></font></div>
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
  		<INPUT TYPE=HIDDEN NAME="E02CCRAPC" ID="E02CCRAPC" VALUE="">
  		<INPUT TYPE=HIDDEN NAME="E02CCRTYP" ID="E02CCRTYP" VALUE="">
  		<INPUT TYPE=HIDDEN NAME="E02CCRSTP" ID="E02CCRSTP" VALUE="">
      	<B>Listado de Cuentas</B><BR>
      <SELECT size="10" name="AccList" id="AccList" onClick="document.forms[0].AccList.multiple = false" onBlur="document.forms[0].AccList.multiple = true">
   	  <%
   	  	int aux1 = 0;
		int rt = 0;
		int sv = 0;
		int fal = 0;
      	accList.initRow();               
      	while (accList.getNextRow()) {
      		ECC009002Message msgAcc = (ECC009002Message) accList.getRecord();
        	if (msgAcc.getE02CCRAPC().equals("01") ||
        		msgAcc.getE02CCRAPC().equals("02") ||
        		msgAcc.getE02CCRAPC().equals("03")) {
        		rt++;
        	}
        	if (msgAcc.getE02CCRAPC().equals("04")) {
        		sv++;
        	}
        	if (msgAcc.getE02CCRTYP().equals("CFAL")) {
        		fal++;
        	}
      %>
		<% if (msgAcc.getE02CCRASG().equals("")) { %>
		<OPTION value="<%= msgAcc.getE02CCRCRA() %>-<%= msgAcc.getE02CCRAPC()%>-<%= msgAcc.getE02CCRTYP()%>-<%= msgAcc.getE02CCRSTP()%>" label="<%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)"><%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)</OPTION>
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
	  	<table cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td height="30">
					<input type="button" name="CCP_Del" value="&lt;&lt;" onClick="javascript:del(1)">		      		      
			        <input type="button" name="CCP_Add" value=">>" onClick="javascript:add(1)">			  </td>
			</tr>
			<tr>
				<td height="25">
					<input type="button" name="CAP_Del" value="&lt;&lt;" onClick="javascript:del(2)">
			        <input type="button" name="CAP_Add" value=">>" onClick="javascript:add(2)">			  </td>
			</tr>
			<tr>
				<td height="30">
					<input type="button" name="CFP_Del" value="&lt;&lt;" onClick="javascript:del(3)">
			  <input type="button" name="CFP_Add" value=">>" onClick="javascript:add(3)">			  </td>
			</tr>
		</table>	</td>
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
		        			<input type="text" name="CCP_Subtype" size="5" maxlength="4" value="<%= ccpsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">
							<INPUT type="radio" name="CC_Main" value="<%= ccp%>" <% if (accmain == 1) { %> checked <% } %>>
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
		        			<input type="text" name="CAP_Subtype" size="5" maxlength="4" value="<%= capsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">
							<INPUT type="radio" name="CC_Main" value="<%= cap%>" <% if (accmain == 2) { %> checked <% } %>>
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
		        			<input type="text" name="CFP_Subtype" size="5" maxlength="4" value="<%= cfpsubtype%>" readonly>
		        		</td>
						<TD nowrap align="left">  
							<INPUT type="radio" name="CC_Main" value="<%= cfp%>" <% if (accmain == 3) { %> checked <% } %>>
	          			    <% if (accmain == 3) {%>
	          			    <img src="<%=request.getContextPath()%>/images/cone.gif" alt="main" align="absbottom" border="0">
	          			    <%}%>
					  		<INPUT TYPE=HIDDEN NAME="CCORS" ID="CCORS" VALUE="<% if (rt >= 2) { %>X<% } %><% if (rt <= 0) { %>O<% } %>">
					  		<INPUT TYPE=HIDDEN NAME="CAHOS" ID="CAHOS" VALUE="<% if (sv >= 2) { %>X<% } %><% if (sv <= 0) { %>O<% } %>">
					  		<INPUT TYPE=HIDDEN NAME="CFALS" ID="CFALS" VALUE="<% if (fal >= 2) { %>X<% } %><% if (fal <= 0) { %>O<% } %>">  		
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
      	<input type="button" name="CS_Del" value="&lt;&lt;" onclick="javascript:del(4)">
      	<input type="button" name="CS_Add" value=">>" onclick="javascript:add(4)">
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
				<OPTION value="<%= msgAcc.getE02CCRCRA() %>-<%= msgAcc.getE02CCRAPC()%>-<%= msgAcc.getE02CCRTYP()%>-<%= msgAcc.getE02CCRSTP()%>" label="<%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)"><%= msgAcc.getE02CCRCRA() %> (<%= msgAcc.getE02CCRTYP() %>/<%= msgAcc.getE02CCRSTP() %>)</OPTION>
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
<%
    out.println("<SCRIPT Language=\"Javascript\">");
    out.println("       document.forms[0].AccList.multiple = true;");
    out.println("       document.forms[0].AccSList.multiple = true;");
    out.println("</SCRIPT>");
%>
</body>
</html>
