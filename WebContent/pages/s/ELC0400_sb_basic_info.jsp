<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letters of Credit Opening/Maintenance</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>

<jsp:useBean id="msg"  class="datapro.eibs.beans.ELC040001Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")) {%>

	builtNewMenu(sb_opening);

<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</script>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}

if (!userPO.getPurpose().equals("NEW")) {
	out.println("<SCRIPT> initMenu();  </SCRIPT>");
}
%>

</head>
<body>
<h3 align="center"><%= (userPO.getPurpose().equals("NEW") ? "Apertura" : "Mantenimiento") %> de Carta de Credito
Stand By<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400">
<input TYPE=HIDDEN NAME="SCREEN" VALUE="2"> <input TYPE=HIDDEN NAME="E01LCMATY"
	VALUE="<%=msg.getE01LCMATY().trim()%>">

<table class="tableinfo">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
			<tbody><tr id="trdark">
				<td nowrap width="16%">
				<div align="right"><b>Banco :</b></div>
				</td>
				<td nowrap width="20%">
				<div align="left"><input type="text" name="E01LCMBNK" readonly size="4" maxlength="2" value="<%=msg.getE01LCMBNK().trim()%>">
				</div></td>
				<td nowrap width="16%">
				<div align="right"><b>Número de Carta de Credito :</b></div>
				</td>
				<td nowrap width="16%"><div align="left"><b> 
				<%if (msg.getE01LCMACC().trim().equals("999999999999"))
				{%>
					<input type="text" size="12" maxlength="12" value="Nuevo" readonly>
					<input type="hidden" name="E01LCMACC" value="<%=msg.getE01LCMACC().trim()%>">
				<%}
				else
				{%>
					<input type="text" name="E01LCMACC" size="12" maxlength="12" value="<%=msg.getE01LCMACC().trim()%>" readonly>
				<%}%>
				</b></div></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="16%">
				<div align="right"><b>Nuestra Referencia :</b></div>
				</td>
					<td nowrap width="20%">
					<div align="left"><input type="text" name="E01LCMORF" size="20" maxlength="16" value="<%=msg.getE01LCMORF().trim()%>"></div>
					</td>
					<td nowrap width="16%">
				<div align="right"><b>Producto :</b></div>
				</td><td nowrap width="16%">
				<div align="left"><b> <input type="text" name="E01LCMPRO" size="4" maxlength="4" value="<%=msg.getE01LCMPRO().trim()%>" readonly> </b>
				</div>
				</td>
				
			</tr>
			<tr id="trdark">
				<td nowrap width="16%">
				<div align="right"><b>Referencia  del Otro Banco :</b></div>
				</td><td nowrap width="16%">
				<div align="left"><b> <input type="text" name="E01LCMTRF" size="20" maxlength="16" value="<%=msg.getE01LCMTRF().trim()%>"> </b></div>
				</td>
					
					<td nowrap width="16%">
				<div align="right"><b>Descripcion de Producto :</b></div>
				</td><td nowrap width="16%">
				<div align="left"><b> <input type="text" name="E01DSCPRO" size="40"
						maxlength="40" value="<%=msg.getE01DSCPRO().trim()%>" readonly> </b>
				</div>
				</td>
				
			</tr></tbody></table>
		</td>
	</tr>
</table>
<br>

<h4>Aplicante</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%"><select name="E01LCMAF2">
							<option value=" "
								<%if (!(msg.getE01LCMAF2().equals("C") || msg.getE01LCMAF2().equals("A")))
	out.print("selected");%>
								selected></option>
							<option value="C"
								<%if (msg.getE01LCMAF2().equals("C"))
	out.print("selected");%>>Cliente</option>
							<option value="A"
								<%if (msg.getE01LCMAF2().equals("A"))
	out.print("selected");%>>Cuenta</option>
						</select><%if (!msg.getE01LCMTYP().trim().toUpperCase().equals("I"))
					{%><img src="<%=request.getContextPath()%>/images/Check.gif"
					alt="campo obligatorio" border="0"><%}%></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="left"><input type="text" name="E01LCMAPA" size="12"
							maxlength="12" value="<%=(!msg.getE01LCMAPA().trim().equals("0") ? msg.getE01LCMAPA() : "")%>"></td>
						<td><a
							href="javascript: GetCustomerDetails('E01LCMAPA','E01LCMAP1','','','E01LCMAP7','E01LCMAP2','E01LCMAP3','E01LCMAP4','','E01LCMAP5','','','E01LCMAP6','','','','','')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
							onclick="javascript: document.forms[0].E01LCMAF2.selectedIndex = 1"
							border="0"></a> Cliente o <a
							href="javascript: GetAccByClient('E01LCMAPA','','RT','','E01LCMAP1')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
							onclick="javascript: document.forms[0].E01LCMAF2.selectedIndex = 2"  border="0"></a> Cuenta</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Nombre :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMAP1" size="45"
					maxlength="35" value="<%=msg.getE01LCMAP1()%>">
					<%if (msg.getE01LCMTYP().trim().toUpperCase().equals("I"))
					{%><img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"> <%}%>
				</div></td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Dirección :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMAP2" size="45"
					maxlength="35" value="<%=msg.getE01LCMAP2()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMAP3" size="45"
					maxlength="35" value="<%=msg.getE01LCMAP3()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMAP4" size="45"
					maxlength="35" value="<%=msg.getE01LCMAP4()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Estado</div>
				</td>
				<td align="left">
				<table>
					<tr>
						<td><input type="text" name="E01LCMAP5" size="4" maxlength="4"
							value="<%=msg.getE01LCMAP5()%>"> Código Postal <input type="text"
							name="E01LCMAP6" size="11" maxlength="10"
							value="<%=msg.getE01LCMAP6()%>"> País <input type="text"
							name="E01LCMAP7" size="4" maxlength="4"
							value="<%=msg.getE01LCMAP7()%>"><a href="javascript:GetCodeCNOFC('E01LCMAP7','03')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<h4>Beneficiario</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%"><select name="E01LCMAF3">
							<option value=" "
								<%if (!(msg.getE01LCMAF3().equals("C") || msg.getE01LCMAF3().equals("A")))
	out.print("selected");%>
								selected></option>
							<option value="C"
								<%if (msg.getE01LCMAF3().equals("C"))
	out.print("selected");%>>Cliente</option>
							<option value="A"
								<%if (msg.getE01LCMAF3().equals("A"))
	out.print("selected");%>>Cuenta</option>
						</select></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tr id="trdark">
						<td align="left"><input type="text" name="E01LCMBAC" size="12"
							maxlength="12" value="<%=(!msg.getE01LCMBAC().trim().equals("0") ? msg.getE01LCMBAC() : "")%>"></td>
						<td><a
							href="javascript: GetCustomerDetails('E01LCMBAC','E01LCMBN1','','','E01LCMBN7','E01LCMBN2','E01LCMBN3','E01LCMBN4','','E01LCMBN5','','','E01LCMBN6','','','','','')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
							onclick="javascript: document.forms[0].E01LCMAF3.selectedIndex = 1"
							 border="0"></a> Cliente o <a
							href="javascript: GetAccByClient('E01LCMBAC','','RT','','E01LCMBN1')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
							onclick="javascript: document.forms[0].E01LCMAF3.selectedIndex = 2"
							 border="0"></a> Cuenta</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Nombre :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMBN1" size="45"
					maxlength="35" value="<%=msg.getE01LCMBN1()%>"><img
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="campo obligatorio"  border="0"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Dirección :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMBN2" size="45"
					maxlength="35" value="<%=msg.getE01LCMBN2()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMBN3" size="45"
					maxlength="35" value="<%=msg.getE01LCMBN3()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMBN4" size="45"
					maxlength="35" value="<%=msg.getE01LCMBN4()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Estado</div>
				</td>
				<td align="left">
				<table>
					<tr>
						<td><input type="text" name="E01LCMBN5" size="2" maxlength="2"
							value="<%=msg.getE01LCMBN5()%>"> Código Postal <input type="text"
							name="E01LCMBN6" size="11" maxlength="10"
							value="<%=msg.getE01LCMBN6()%>"> País <input type="text"
							name="E01LCMBN7" size="4" maxlength="4"
							value="<%=msg.getE01LCMBN7()%>"><a href="javascript:GetCodeCNOFC('E01LCMBN7','03')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<h4>Banco 
<%//Incoming
if (msg.getE01LCMTYP().trim().toUpperCase().equals("R"))
{%>
Emisor</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tbody><tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tbody><tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%"><select name="E01LCMAF1">
							<option value=" "></option>
							<option value="C" <%if (msg.getE01LCMAF1().equals("C")) out.print("selected");%>>Cliente</option>
							<option value="A" <%if (msg.getE01LCMAF1().equals("A")) out.print("selected");%>>Cuenta</option>
						</select></td>
					</tr>
				</tbody></table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tbody><tr id="trdark">
						<td align="left"><input type="text" name="E01LCMIBA" size="12" maxlength="12"
						value="<%=(!msg.getE01LCMIBA().trim().equals("0") ? msg.getE01LCMIBA() : "")%>"></td>
						<td><a href="javascript: GetCustomerDetails('E01LCMIBA','E01LCMIB1','','','E01LCMIB7','E01LCMIB2','E01LCMIB3','E01LCMIB4','','E01LCMIB5','','','E01LCMIB6','','','','','')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onClick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 1" border="0"></a> Cliente o <a href="javascript: GetAccByClient('E01LCMIBA','','RT','','E01LCMIB1')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onClick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 2" border="0"></a> Cuenta</td>
					</tr>
				</tbody></table>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Codigo Swift :</div>
				</td><td>
				<div align="left"><input type="text" name="E01LCMIBI" size="14" maxlength="12" value="<%=msg.getE01LCMIBI()%>">
				 <a href="javascript:GetCorrespondentDescIdSwift('E01LCMIBI','','')">
				 <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></a></div>
				</td>
				
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Nombre :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMIB1" size="45" maxlength="35" value="<%=msg.getE01LCMIB1()%>"><img
						src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio" border="0"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Dirección :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMIB2" size="45" maxlength="35" value="<%=msg.getE01LCMIB2()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMIB3" size="45" maxlength="35" value="<%=msg.getE01LCMIB3()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMIB4" size="45" maxlength="35" value="<%=msg.getE01LCMIB4()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Estado</div>
				</td>
				<td align="left">
				<table id="trdark">
					<tbody><tr>
						<td><input type="text" name="E01LCMIB5" size="2" maxlength="2" value="<%=msg.getE01LCMIB5()%>"> Código Postal <input type="text" name="E01LCMIB6" size="11" maxlength="10" value="<%=msg.getE01LCMIB6()%>"> País <input type="text" name="E01LCMIB7" size="4" maxlength="4" value="<%=msg.getE01LCMIB7()%>"><a href="javascript:GetCodeCNOFC('E01LCMIB7','03')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a></td>
					</tr>
				</tbody></table>
				</td>
			</tr>
		</tbody></table>
		</td>
	</tr>
</table>


<h4>Banco Corresponsal</h4>
<table class="tableinfo">
	<tr>
		<td align="center" width="100%">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tbody><tr id="trdark">
				<td width="30%">
				<div align="right">Cliente :</div>
				</td>
				<td width="70%">
				<div align="left">
					<input type="text" name="E01LCMCBA" size="12" maxlength="12"
						value="<%=(!msg.getE01LCMCBA().trim().equals("0") ? msg.getE01LCMCBA() : "")%>">
					<a href="javascript: GetCustomerDetails('E01LCMCBA','E01LCMCB1','','','E01LCMCB7','E01LCMCB2','E01LCMCB3','E01LCMCB4','','E01LCMCB5','','','E01LCMCB6','','','','','')">
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." border="0"></a></div>
				</td>
			</tr>
			<tr id="trclear">
				<td><div align="right">Codigo Swift :</div></td>
				<td><div align="left"><input type="text" name="E01LCMCBI" size="14" maxlength="12" value="<%=msg.getE01LCMCBI()%>">
					 <a href="javascript:GetCorrespondentDescIdSwift('E01LCMCBI','','')">
					 <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></a></div></td>
				
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Nombre :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMCB1" size="45" maxlength="35" value="<%=msg.getE01LCMCB1()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right">Dirección :</div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMCB2" size="45" maxlength="35" value="<%=msg.getE01LCMCB2()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMCB3" size="45" maxlength="35" value="<%=msg.getE01LCMCB3()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td>
				<div align="right"></div>
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMCB4" size="45" maxlength="35" value="<%=msg.getE01LCMCB4()%>"></div>
				</td>
			</tr>
			<tr id="trdark">
				<td>
				<div align="right">Estado</div>
				</td>
				<td align="left">
				<table id="trdark">
					<tbody><tr>
						<td><input type="text" name="E01LCMCB5" size="2" maxlength="2" value="<%=msg.getE01LCMCB5()%>"> Código Postal <input type="text" name="E01LCMCB6" size="11" maxlength="10" value="<%=msg.getE01LCMCB6()%>"> País <input type="text" name="E01LCMCB7" size="3" maxlength="7" value="<%=msg.getE01LCMCB7()%>"><a href="javascript:GetCodeCNOFC('E01LCMCB7','03')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a></td>
					</tr>
				</tbody></table>
				</td>
			</tr></tbody></table>
		</td>
	</tr>
</table>

<%}//Outgoing A/C
else if (msg.getE01LCMTYP().trim().toUpperCase().equals("S"))
{
out.print("Avisador / Confirmador </h4>");%>
 
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tbody><tr id="trdark">
				<td align="right">
				<table border="0" cellspacing="0">
					<tbody><tr id="trdark">
						<td align="right">Numero de Cliente o de Cuenta:</td>
						<td nowrap width="25%"><select name="E01LCMAF1">
							<option value=" "></option>
							<option value="C" <%if (msg.getE01LCMAF1().equals("C")) out.print("selected");%>>Cliente</option>
							<option value="A" <%if (msg.getE01LCMAF1().equals("A")) out.print("selected");%>>Cuenta</option>
						</select></td>
					</tr>
				</tbody></table>
				</td>
				<td>
				<table border="0" cellspacing="0">
					<tbody><tr id="trdark">
						<td align="left"><input type="text" name="E01LCMIBA" size="12" maxlength="12"
							value="<%=(!msg.getE01LCMIBA().trim().equals("0") ? msg.getE01LCMIBA() : "")%>"></td>
						<td><a href="javascript: GetCustomerDetails('E01LCMIBA','E01LCMAB1','','','E01LCMAB7','E01LCMAB2','E01LCMAB3','E01LCMAB4','','E01LCMAB5','','','E01LCMAB6','','','','','')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" onClick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 1"  border="0"></a> Cliente o <a href="javascript: GetAccByClient('E01LCMABA','','RT','','E01LCMAB1')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." onClick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 2"  border="0"></a> Cuenta</td>
					</tr>
				</tbody></table>
				</td>
			</tr>
			<tr>
				<td align="right">
				Codigo Swift :
				</td><td align="left"><input type="text" name="E01LCMABI" size="12" maxlength="12" value="<%=msg.getE01LCMABI()%>"><a
						href="javascript:GetCorrespondentDescIdSwift('E01LCMABI','','')"><img
						src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
						border="0"></a>
				</td>
				
			</tr>
			<tr id="trdark">
				<td align="right">Nombre :
				</td>
				<td>
				<div align="left"><input type="text" name="E01LCMAB1" size="45" maxlength="35" value="<%=msg.getE01LCMAB1()%>"></div>
				</td>
			</tr>
			<tr id="trclear">
				<td align="right">Dirección :
				</td>
				<td>
				<input type="text" name="E01LCMAB2" size="45" maxlength="35" value="<%=msg.getE01LCMAB2()%>">
				</td>
			</tr>
			<tr id="trdark">
				<td>
				</td>
				<td>
				<input type="text" name="E01LCMAB3" size="45" maxlength="35" value="<%=msg.getE01LCMAB3()%>">
				</td>
			</tr>
			<tr id="trclear">
				<td></td>
				<td align="left"><input type="text" name="E01LCMAB4" size="45" maxlength="35" value="<%=msg.getE01LCMAB4()%>">
				</td>
			</tr>
			<tr id="trdark">
				<td align="right">Estado
				</td>
				<td align="left">
				<table id="trdark">
					<tbody><tr>
						<td><input type="text" name="E01LCMAB5" size="2" maxlength="2" value="<%=msg.getE01LCMAB5()%>"> Código Postal <input type="text" name="E01LCMAB6" size="11" maxlength="10" value="<%=msg.getE01LCMAB6()%>"> País <input type="text" name="E01LCMAB7" size="4" maxlength="4" value="<%=msg.getE01LCMAB7()%>"><a href="javascript:GetCodeCNOFC('E01LCMAB7','03')">
							<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a></td>
					</tr>
				</tbody></table>
				</td>
			</tr>
		</tbody></table>
		</td>
	</tr>
</table>
<%}%>
<br>
<div align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></div>
</form>
<!--<h5><%
String s = msg.toString();
for(int i = 0; i < s.length(); i++)
{
	if(s.charAt(i) == ':')	out.print("<BR>");
	else if (s.charAt(i) == '<') out.print("{");
		else if (s.charAt(i) == '>') out.print("}");
	else 					out.print(s.charAt(i));

}%></h5>-->
</body>
</html>
