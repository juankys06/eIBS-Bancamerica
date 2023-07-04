<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letters of Credit Opening/Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="lcComm" class="datapro.eibs.beans.ELC050004Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id="lcNew" class="datapro.eibs.beans.ELC050001Message"  scope="session" />

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")){%>
	builtNewMenu(lc_opening);
<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

</head>

<body>
<h3 align="center">Comisiones de Cartas de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_78_info.jsp, ELC0500"></h3>
<hr size="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<table class="tableinfo">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0"
			class="tbhead">
			<tr id="trdark">
				<td nowrap width="16%">
				<div align="right"><b>Banco :</b></div>
				</td>
				<td nowrap width="20%">
				<div align="left"><INPUT type="text" name="E04LCMBNK" readonly
					size="4" maxlength="2" value="<%=lcComm.getE04LCMBNK().trim()%>"></div></td>
				<td nowrap width="16%">
				<div align="right"><b>Producto : </b></div>
				</td>
				<td nowrap width="16%">
				<div align="left"><b> <input type="text" name="E01LCMPRO" size="4"
					maxlength="4" value="<%=lcComm.getE04LCMPRO().trim()%>" readonly> </b>
				</div>
				</td>
				<td nowrap width="16%">
				<div align="right"><b>Numero Carta de Credito:</b></div>
				</td>
				<td nowrap width="16%">
				<div align="left"><b><INPUT type="text" name="E04LCMACC" size="10"
					maxlength="9" value="<%=lcComm.getE04LCMACC().trim()%>" readonly> </b>
				</div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="16%">
				<div align="right"><b>Fecha Emision:</b></div>
				</td>
				<td nowrap width="20%" colspan=3>
				<div align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=
					"<%=lcComm.getE04LCMID1() + "/" + lcComm.getE04LCMID2() +
					"/"+(lcComm.getE04LCMID3().length() == 1 ? "0"+lcComm.getE04LCMID3() : lcComm.getE04LCMID3())%>"></div></td>
				<td nowrap width="16%">
				<div align="right"><b>Tarifa de Cargos y Moneda:</b></div>
				</td>
				<td nowrap width="16%">
				<div align="left"><b><INPUT type="text" name="E04LCMTARTCY" size="12"
					maxlength="12" value=
						"<%	if(lcComm.getE04LCMTAR().length() < 2 && !lcComm.getE04LCMTAR().equals("0"))
								out.print("0");
							out.print(lcComm.getE04LCMTAR() + "/" + lcComm.getE04LCMTCY());%>"
					readonly> </b></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="16%">
				<div align="right"><b>Fecha Expiracion:</b></div>
				</td>
				<td nowrap width="20%" colspan=3>
				<div align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=
					"<%=lcComm.getE04LCMEX1()+"/"+lcComm.getE04LCMEX2()+"/"
					+(lcComm.getE04LCMEX3().length() == 1 ? "0"+lcComm.getE04LCMEX3() : lcComm.getE04LCMEX3())%>"></div></td>
				<td nowrap width="16%">
				<div align="right"><b>Monto del Credito:</b></div>
				</td>
				<td nowrap width="16%">
				<div align="left"><b><INPUT type="text" name="E04LCMOAM" size="12"
					maxlength="12" value="<%=lcComm.getE04LCMOAM()%>" readonly> </b></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>

<h4>Informacion</h4>
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD>
			<TABLE border="0" cellspacing="0" width="123%">
				<TBODY>
					<TR id="trdark0">
						<TD align="right"></TD>
						<TD nowrap align="center" width="17%"><B>Monto</B></TD>
						<TD nowrap align="center"><B>Por Cta de</B></TD>
						<TD nowrap align="center" width="26%"></TD>
						<TD nowrap align="center" width="12%"></TD>
					</TR>

					<%
					String name[] = {"Comision Apertura :"};
					for (int i = 0; i < %>

					<TR id="trclear0">
						<TD align="right">Comision Apertura :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC01" size="33" maxlength="15"
							value="<%=lcComm.getE04LCMC01()%>"></TD>
						<TD nowrap><SELECT name="E04LCMP01">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B" <%if(%>>Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark0">
						<TD align="right">Comision Aviso:</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC02" size="24" maxlength="15"
							value="<%=lcComm.getE04LCMC02()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear0">
						<TD align="right">Comision Confirmacion :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC03" size="29" maxlength="15"
							value="<%=lcComm.getE04LCMC03()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark0">
						<TD align="right">Portes :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC09" size="29" maxlength="15"
							value="<%=lcComm.getE04LCMC09()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear0">
						<TD align="right">Courier :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC10" size="29" maxlength="15"
							value="<%=lcComm.getE04LCMC10()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark0">
						<TD align="right">Swift Apertura :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC11" size="30" maxlength="15"
							value="<%=lcComm.getE04LCMC11()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear0">
						<TD align="right">Swift Adicional :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04LCMC12" size="29" maxlength="15"
							value="<%=lcComm.getE04LCMC12()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark0">
						<TD align="right">Gastos Notariales :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04GASNOT" size="25" maxlength="15"
							value="<%=lcComm.getE04GASNOT()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear0">
						<TD align="right">Seguro de Desgravamen :</TD>
						<TD nowrap align="center" width="17%"><INPUT type="text"
							name="E04SEGDES" size="25" maxlength="15"
							value="<%=lcComm.getE04SEGDES()%>"></TD>
						<TD nowrap><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>

				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>


<h4 style="text-align:center"><input type="checkbox" name="H04FLGWK2" value="A" <% if(lcComm.getH04FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Advertencias</h4>
<div align="center"><input id="EIBSBTN" type=submit name="Enviar"
	value="Enviar"></div>

</form>
</body>
</html>
