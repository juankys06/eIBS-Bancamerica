<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import = "datapro.eibs.master.Util" %>

<html>
<head>
<title>Información Básica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="accOthMant"	class= "datapro.eibs.beans.EAA002001Message"	scope="session"/>
<jsp:useBean id="error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session"/>
<jsp:useBean id="userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function goAction(op) {
	if (op == 1) {
 		document.forms[0].SCREEN.value = '2';
 	}
 	else {
 		document.forms[0].SCREEN.value = '4';
	}
	document.forms[0].submit();
}

</SCRIPT>

</head>
<%
 	if ( !error.getERRNUM().equals("0")  ) {
    	error.setERRNUM("0");
	    out.println("<SCRIPT Language=\"Javascript\">");
    	out.println("       showErrors()");
    	out.println("</SCRIPT>");
 	}
%>

<body bgcolor="#FFFFFF">
<h3 align="center">Otras Transacciones Análisis de Cuentas 
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="acc_analysis_other_trans.jsp,EAA0020"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEAA0020" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<table id="headTable" width="100%">
	<tr id="trdark">
		<th align="center" width="15%">Número de Cuenta</th>
		<th align="center" width="10%">Código Tr</th>
		<th align="center" width="10%">Contador</th>
		<th align="center" width="15%">Fecha</th>
		<th align="center" width="25%">Nombre de Cliente</th>
		<th align="center" width="25%">Descripción de la Transacción</th>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN01" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN01().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN01','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC01" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC01().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC01','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN01" size="5" maxlength="5" value="<%= accOthMant.getE01COUN01().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM01" size="2" maxlength="2" value="<%= accOthMant.getE01DATM01().trim()%>" >
          		<input type="text" name="E01DATD01" size="2" maxlength="2" value="<%= accOthMant.getE01DATD01().trim()%>" >
          		<input type="text" name="E01DATY01" size="2" maxlength="2" value="<%= accOthMant.getE01DATY01().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM01,document.forms[0].E01DATD01,document.forms[0].E01DATY01)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN01" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN01().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC01" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC01().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN02" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN02().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN02','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC02" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC02().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC02','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN02" size="5" maxlength="5" value="<%= accOthMant.getE01COUN02().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM02" size="2" maxlength="2" value="<%= accOthMant.getE01DATM02().trim()%>" >
          		<input type="text" name="E01DATD02" size="2" maxlength="2" value="<%= accOthMant.getE01DATD02().trim()%>" >
          		<input type="text" name="E01DATY02" size="2" maxlength="2" value="<%= accOthMant.getE01DATY02().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM02,document.forms[0].E01DATD02,document.forms[0].E01DATY02)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN02" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN02().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC02" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC02().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN03" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN03().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN03','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC03" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC03().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC03','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN03" size="5" maxlength="5" value="<%= accOthMant.getE01COUN03().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM03" size="2" maxlength="2" value="<%= accOthMant.getE01DATM03().trim()%>" >
          		<input type="text" name="E01DATD03" size="2" maxlength="2" value="<%= accOthMant.getE01DATD03().trim()%>" >
          		<input type="text" name="E01DATY03" size="2" maxlength="2" value="<%= accOthMant.getE01DATY03().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM03,document.forms[0].E01DATD03,document.forms[0].E01DATY03)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN03" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN03().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC03" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC03().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN04" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN04().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN04','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC04" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC04().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC04','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN04" size="5" maxlength="5" value="<%= accOthMant.getE01COUN04().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM04" size="2" maxlength="2" value="<%= accOthMant.getE01DATM04().trim()%>" >
          		<input type="text" name="E01DATD04" size="2" maxlength="2" value="<%= accOthMant.getE01DATD04().trim()%>" >
          		<input type="text" name="E01DATY04" size="2" maxlength="2" value="<%= accOthMant.getE01DATY04().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM04,document.forms[0].E01DATD04,document.forms[0].E01DATY04)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN04" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN04().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC04" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC04().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN05" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN05().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN05','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC05" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC05().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC05','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN05" size="5" maxlength="5" value="<%= accOthMant.getE01COUN05().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM05" size="2" maxlength="2" value="<%= accOthMant.getE01DATM05().trim()%>" >
          		<input type="text" name="E01DATD05" size="2" maxlength="2" value="<%= accOthMant.getE01DATD05().trim()%>" >
          		<input type="text" name="E01DATY05" size="2" maxlength="2" value="<%= accOthMant.getE01DATY05().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM05,document.forms[0].E01DATD05,document.forms[0].E01DATY05)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN05" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN05().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC05" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC05().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN06" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN06().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN06','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC06" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC06().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC06','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN06" size="5" maxlength="5" value="<%= accOthMant.getE01COUN06().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM06" size="2" maxlength="2" value="<%= accOthMant.getE01DATM06().trim()%>" >
          		<input type="text" name="E01DATD06" size="2" maxlength="2" value="<%= accOthMant.getE01DATD06().trim()%>" >
          		<input type="text" name="E01DATY06" size="2" maxlength="2" value="<%= accOthMant.getE01DATY06().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM06,document.forms[0].E01DATD06,document.forms[0].E01DATY06)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN06" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN06().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC06" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC06().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN07" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN07().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN07','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC07" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC07().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC07','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN07" size="5" maxlength="5" value="<%= accOthMant.getE01COUN07().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM07" size="2" maxlength="2" value="<%= accOthMant.getE01DATM07().trim()%>" >
          		<input type="text" name="E01DATD07" size="2" maxlength="2" value="<%= accOthMant.getE01DATD07().trim()%>" >
          		<input type="text" name="E01DATY07" size="2" maxlength="2" value="<%= accOthMant.getE01DATY07().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM07,document.forms[0].E01DATD07,document.forms[0].E01DATY07)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN07" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN07().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC07" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC07().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN08" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN08().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN08','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC08" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC08().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC08','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN08" size="5" maxlength="5" value="<%= accOthMant.getE01COUN08().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM08" size="2" maxlength="2" value="<%= accOthMant.getE01DATM08().trim()%>" >
          		<input type="text" name="E01DATD08" size="2" maxlength="2" value="<%= accOthMant.getE01DATD08().trim()%>" >
          		<input type="text" name="E01DATY08" size="2" maxlength="2" value="<%= accOthMant.getE01DATY08().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM08,document.forms[0].E01DATD08,document.forms[0].E01DATY08)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN08" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN08().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC08" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC08().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN09" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN09().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN09','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC09" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC09().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC09','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN09" size="5" maxlength="5" value="<%= accOthMant.getE01COUN09().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM09" size="2" maxlength="2" value="<%= accOthMant.getE01DATM09().trim()%>" >
          		<input type="text" name="E01DATD09" size="2" maxlength="2" value="<%= accOthMant.getE01DATD09().trim()%>" >
          		<input type="text" name="E01DATY09" size="2" maxlength="2" value="<%= accOthMant.getE01DATY09().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM09,document.forms[0].E01DATD09,document.forms[0].E01DATY09)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN09" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN09().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC09" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC09().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN10" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN10().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN10','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC10" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC10().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC10','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN10" size="5" maxlength="5" value="<%= accOthMant.getE01COUN10().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM10" size="2" maxlength="2" value="<%= accOthMant.getE01DATM10().trim()%>" >
          		<input type="text" name="E01DATD10" size="2" maxlength="2" value="<%= accOthMant.getE01DATD10().trim()%>" >
          		<input type="text" name="E01DATY10" size="2" maxlength="2" value="<%= accOthMant.getE01DATY10().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM10,document.forms[0].E01DATD10,document.forms[0].E01DATY10)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN10" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN10().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC10" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC10().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN11" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN11().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN11','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC11" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC11().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC11','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN11" size="5" maxlength="5" value="<%= accOthMant.getE01COUN11().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM11" size="2" maxlength="2" value="<%= accOthMant.getE01DATM11().trim()%>" >
          		<input type="text" name="E01DATD11" size="2" maxlength="2" value="<%= accOthMant.getE01DATD11().trim()%>" >
          		<input type="text" name="E01DATY11" size="2" maxlength="2" value="<%= accOthMant.getE01DATY11().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM11,document.forms[0].E01DATD11,document.forms[0].E01DATY11)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN11" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN11().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC11" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC11().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN12" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN12().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN12','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC12" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC12().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC12','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN12" size="5" maxlength="5" value="<%= accOthMant.getE01COUN12().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM12" size="2" maxlength="2" value="<%= accOthMant.getE01DATM12().trim()%>" >
          		<input type="text" name="E01DATD12" size="2" maxlength="2" value="<%= accOthMant.getE01DATD12().trim()%>" >
          		<input type="text" name="E01DATY12" size="2" maxlength="2" value="<%= accOthMant.getE01DATY12().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM12,document.forms[0].E01DATD12,document.forms[0].E01DATY12)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN12" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN12().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC12" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC12().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN13" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN13().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN13','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC13" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC13().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC13','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN13" size="5" maxlength="5" value="<%= accOthMant.getE01COUN13().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM13" size="2" maxlength="2" value="<%= accOthMant.getE01DATM13().trim()%>" >
          		<input type="text" name="E01DATD13" size="2" maxlength="2" value="<%= accOthMant.getE01DATD13().trim()%>" >
          		<input type="text" name="E01DATY13" size="2" maxlength="2" value="<%= accOthMant.getE01DATY13().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM13,document.forms[0].E01DATD13,document.forms[0].E01DATY13)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN13" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN13().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC13" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC13().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN14" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN14().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN14','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC14" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC14().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC14','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN14" size="5" maxlength="5" value="<%= accOthMant.getE01COUN14().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM14" size="2" maxlength="2" value="<%= accOthMant.getE01DATM14().trim()%>" >
          		<input type="text" name="E01DATD14" size="2" maxlength="2" value="<%= accOthMant.getE01DATD14().trim()%>" >
          		<input type="text" name="E01DATY14" size="2" maxlength="2" value="<%= accOthMant.getE01DATY14().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM14,document.forms[0].E01DATD14,document.forms[0].E01DATY14)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN14" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN14().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC14" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC14().trim()%>" >
		</td>
	</tr>
	<tr id="trclear">
		<td align="center" width="15%">
			<input type="text" name="E01ACCN15" size="10" maxlength="9" value="<%= accOthMant.getE01ACCN15().trim()%>" >
			<a href="javascript:GetAccount('E01ACCN15','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01TRNC15" size="2" maxlength="2" value="<%= accOthMant.getE01TRNC15().trim()%>" >
			<a href="javascript:GetCodeCNOFC('E01TRNC15','20')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0"></a>
		</td>
		<td align="center" width="10%">
			<input type="text" name="E01COUN15" size="5" maxlength="5" value="<%= accOthMant.getE01COUN15().trim()%>" >
		</td>
		<td align="center" width="15%">
          		<input type="text" name="E01DATM15" size="2" maxlength="2" value="<%= accOthMant.getE01DATM15().trim()%>" >
          		<input type="text" name="E01DATD15" size="2" maxlength="2" value="<%= accOthMant.getE01DATD15().trim()%>" >
          		<input type="text" name="E01DATY15" size="2" maxlength="2" value="<%= accOthMant.getE01DATY15().trim()%>" >
          		<a href="javascript:DatePicker(document.forms[0].E01DATM15,document.forms[0].E01DATD15,document.forms[0].E01DATY15)">
          		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01CUSN15" size="25" readonly maxlength="22" value="<%= accOthMant.getE01CUSN15().trim()%>" >
		</td>
		<td align="left" width="25%">
			<input type="text" name="E01DESC15" size="25" readonly maxlength="25" value="<%= accOthMant.getE01DESC15().trim()%>" >
		</td>
	</tr>
</table>

<table class="tbenter">
  	<tr>
  		<td class=tdBKG>
        	<div align="center">
        		<a href="javascript:goAction('1')">Validar</a>
        	</div>
		</td>
		<td class=tdBKG>
        	<div align="center">
        		<a href="javascript:goAction(2)">Actualizar</a>
        	</div>
      	</td>
	</tr>
</table>

</form>
</body>
</html>
