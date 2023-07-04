<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Conciliación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0015DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
function validate() {
	if (document.forms[0].SEL[0].checked == false &&
		document.forms[0].SEL[1].checked == false &&
		document.forms[0].SEL[2].checked == false &&
		document.forms[0].SEL[3].checked == false &&
		document.forms[0].SEL[4].checked == false &&
		document.forms[0].SEL[5].checked == false) {
		alert("Por favor, ingrese uno de los métodos de consulta.");
		return false;
	}
	else if (document.forms[0].E01CCRDDE.value == "" ||
		document.forms[0].E01CCRMDE.value == "" ||
		document.forms[0].E01CCRADE.value == "" ||
		document.forms[0].E01CCRDDH.value == "" ||
		document.forms[0].E01CCRMDH.value == "" ||
		document.forms[0].E01CCRADH.value == "") {
		alert("Por favor, introduzca el intervalo de fechas completo.");
		return false;
	}
	else {
		if (document.forms[0].SEL[0].checked == true) {
			document.forms[0].E01CCRTAR.value = "0";
			document.getElementById("blank").selected = true;
			document.forms[0].E01CCRRED.value = "";
			document.getElementById("blank2").selected = true;
			document.getElementById("blank3").selected = true;
			document.forms[0].OPT.value = "ATM";
		}
		if (document.forms[0].SEL[1].checked == true) {
			document.forms[0].E01CCRATM.value = "";
			document.getElementById("blank").selected = true;
			document.forms[0].E01CCRRED.value = "";
			document.getElementById("blank2").selected = true;
			document.getElementById("blank3").selected = true;
			document.forms[0].OPT.value = "TAR";
		}
		if (document.forms[0].SEL[2].checked == true) {
			document.forms[0].E01CCRATM.value = "";
			document.forms[0].E01CCRTAR.value = "0";
			document.forms[0].E01CCRRED.value = "";
			document.getElementById("blank2").selected = true;
			document.getElementById("blank3").selected = true;
			document.forms[0].OPT.value = "STS";
		}
		if (document.forms[0].SEL[3].checked == true) {
			document.forms[0].E01CCRATM.value = "";
			document.forms[0].E01CCRTAR.value = "0";
			document.forms[0].E01CCRRED.value = "";
			document.getElementById("blank").selected = true;
			document.getElementById("blank3").selected = true;
			document.forms[0].OPT.value = "STP";
		}
		if (document.forms[0].SEL[4].checked == true) {
			document.forms[0].E01CCRATM.value = "";
			document.forms[0].E01CCRTAR.value = "0";
			document.getElementById("blank").selected = true;
			document.getElementById("blank2").selected = true;
			document.getElementById("blank3").selected = true;
			document.forms[0].OPT.value = "RED";
		}
		if (document.forms[0].SEL[5].checked == true) {
			document.forms[0].E01CCRATM.value = "";
			document.forms[0].E01CCRTAR.value = "0";
			document.getElementById("blank").selected = true;
			document.forms[0].E01CCRRED.value = "";
			document.getElementById("blank2").selected = true;
			document.forms[0].OPT.value = "CCY";
		}
		return true;
	}
}

function goDetail(){
	if (validate()) {
		document.forms[0].SCREEN.value = 102;
		document.forms[0].submit();
	}
}

function goGeneral(){
	if (validate()) {
		document.forms[0].SCREEN.value = 101;
		document.forms[0].submit();
	}
}

</script>

</head>
<body>
<h3 align="center">Consulta de Conciliación
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="settlement_inq_enter.jsp, ECD0015"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0015">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
          <TBODY><TR id="trdark"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="ATM" checked>
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					ATM/POS ID :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01CCRATM" size="9" maxlength="8" value="<%= msgCD.getE01CCRATM() %>" onclick="document.forms[0].SEL[0].click()">
              </DIV>
            </TD>
            </TR>
          <TR id="trclear"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="TAR">
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					Número de Tarjeta :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01CCRTAR" size="20" maxlength="19" value="<%= msgCD.getE01CCRTAR() %>" onclick="document.forms[0].SEL[1].click()">
              </DIV>
            </TD>
            </TR>
          <TR id="trdark"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="STS">
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					Estado de Conciliación :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left">
              	<SELECT name="E01CCRSTS" onclick="document.forms[0].SEL[2].click()">
					<OPTION value=" " id="blank" <% if (!msgCD.getE01CCRSTS().equals("P") && !msgCD.getE01CCRSTS().equals("C")) { out.println("selected"); }%>></OPTION>
					<OPTION value="P" <% if (msgCD.getE01CCRSTS().equals("P")) { out.println("selected"); }%>>Pendiente</OPTION>
					<OPTION value="C" <% if (msgCD.getE01CCRSTS().equals("C")) { out.println("selected"); }%>>Conciliada</OPTION>
				</SELECT> 
			  </DIV>
             
            </TD>
            </TR>
          <TR id="trclear"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="STP">
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					Estado de Compensación :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left">
              	<SELECT name="E01CCRSTP" onclick="document.forms[0].SEL[3].click()">
					<OPTION value=" " id="blank2" <% if (!msgCD.getE01CCRSTP().equals("P") && !msgCD.getE01CCRSTP().equals("C") && !msgCD.getE01CCRSTP().equals("A")) { out.println("selected"); }%>></OPTION>
					<OPTION value="P" <% if (msgCD.getE01CCRSTP().equals("P")) { out.println("selected"); }%>>Pendiente</OPTION>
					<OPTION value="C" <% if (msgCD.getE01CCRSTP().equals("C")) { out.println("selected"); }%>>Compensada</OPTION>
					<OPTION value="A" <% if (msgCD.getE01CCRSTP().equals("A")) { out.println("selected"); }%>>Ajustada</OPTION>
				</SELECT> 
			  </DIV>
             
            </TD>
            </TR>
          <TR id="trdark"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="RED">
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					Red Adquiriente :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01CCRRED" size="4" maxlength="3" value="<%= msgCD.getE01CCRRED() %>" onclick="document.forms[0].SEL[4].click()">
              </DIV>
            </TD>
            </TR>
          <TR id="trclear"> 
            <TD nowrap width="25%">
				<DIV align="right">
					<INPUT type="radio" name="SEL" value="CCY">
				</DIV>
			</TD>
			<TD nowrap width="25%">
				<DIV align="left">
					Moneda de la Transacción :
				</DIV>
			</TD>
			<TD nowrap width="50%" colspan="2"> 
              <DIV align="left">
              	<SELECT name="E01CCRCCY" onclick="document.forms[0].SEL[5].click()">
					<OPTION value=" " id="blank3" <% if (!msgCD.getE01CCRCCY().equals("188") && !msgCD.getE01CCRCCY().equals("840")) { out.println("selected"); }%>></OPTION>
					<OPTION value="188" <% if (msgCD.getE01CCRCCY().equals("188")) { out.println("selected"); }%>>Colones</OPTION>
					<OPTION value="840" <% if (msgCD.getE01CCRCCY().equals("840")) { out.println("selected"); }%>>Dólares</OPTION>
				</SELECT> 
			  </DIV>
             
            </TD>
            </TR>
          <TR id="trdark">
            <TD nowrap width="25%"> 
              <DIV align="right">Fecha de Trs. Desde :</DIV>
            </TD>
            <TD nowrap width="25%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01CCRDDE" size="3" maxlength="2" value="" onkeypress="enterInteger()">
                <INPUT type="text" name="E01CCRMDE" size="3" maxlength="2" value="" onkeypress="enterInteger()">
                <INPUT type="text" name="E01CCRADE" size="5" maxlength="4" value="" onkeypress="enterInteger()">
              </DIV>
            </TD>
            <TD nowrap width="25%"> 
              <DIV align="right">Fecha de Trs. Hasta :</DIV>
            </TD>
            <TD nowrap width="25%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01CCRDDH" size="3" maxlength="2" value="" onkeypress="enterInteger()">
                <INPUT type="text" name="E01CCRMDH" size="3" maxlength="2" value="" onkeypress="enterInteger()">
                <INPUT type="text" name="E01CCRADH" size="5" maxlength="4" value="" onkeypress="enterInteger()">
              </DIV>
            </TD>
          </TR>
         </TBODY></TABLE>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
		<INPUT id="EIBSBTN" type="button" name="Detail" value="Detalle" onclick="goDetail()">
		<INPUT id="EIBSBTN" type="button" name="General" value="General" onclick="goGeneral()">
  </div>
  </form>
</body>
</html>
