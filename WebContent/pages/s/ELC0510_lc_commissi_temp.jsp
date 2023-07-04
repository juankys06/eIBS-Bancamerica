<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letter of Credit Details</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="lcComm" class="datapro.eibs.beans.ELC051004Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")  && !userPO.getHeader18().equals("NO_MENU")){%>
	builtNewMenu(lc_maint);
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
<h3 align="center">Mantenimiento
 Carta de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_details.jsp,ELC0510"></h3> 

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" >
				<TBODY>
					<TR id="trdark0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Banco :</B></DIV>
						</TD>
						<TD nowrap width="20%">
						<DIV align="left"><INPUT type="text" name="E04LCMBNK" readonly
							size="4" maxlength="2" value="<%=lcComm.getE04LCMBNK().trim()%>" readonly>
						</DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="right"><B>Número de Carta de Credito :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <%if (lcComm.getE04LCMACC().trim().equals("999999999999"))
				{%> <INPUT type="text" size="12" maxlength="12" value="Nuevo"
							readonly> <INPUT type="hidden" name="E04LCMACC"
							value="<%=lcComm.getE04LCMACC().trim()%>" readonly> <%}
				else
				{%> <INPUT type="text" name="E04LCMACC" size="12" maxlength="12"
							value="<%=lcComm.getE04LCMACC().trim()%>" readonly> <%}%> </B></DIV>
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Nuestra Referencia :</B></DIV>
						</TD>
						<TD nowrap width="20%">
						<DIV align="left"><INPUT type="text" name="" size="20"
							maxlength="16" value="<%=userPO.getHeader10().trim()%>" readonly></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="right"><B>Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" name="" size="4"
							maxlength="4" value="<%=userPO.getHeader13().trim()%>" readonly>
						</B></DIV>
						</TD>

					</TR>
					<TR id="trdark0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Referencia del Otro Banco :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" name=""
							size="20" maxlength="16" value="<%=userPO.getHeader10().trim()%>" readonly>
						</B></DIV>
						</TD>

						<TD nowrap width="16%">
						<DIV align="right"><B>Descripcion de Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" name=""
							size="30" maxlength="30" value="<%=userPO.getHeader14().trim()%>"
							readonly> </B></DIV>
						</TD>

					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<H4>Informacion</H4>

<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD>
			<TABLE border="0" cellspacing="0" width="100%">
				<TBODY>
					<TR id="trdark">
						<TD align="right"></TD>
						<TD nowrap align="center" width="17%"><B>Monto</B></TD>
						<TD nowrap align="center" width="15%"><B>Por Cta de</B></TD>
						<TD nowrap align="center" width="26%"></TD>
						<TD nowrap align="center" width="12%"></TD>
					</TR>
					<TR id="trclear">
						<TD align="right">Comision Apertura :</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC01" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC01()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark">
						<TD align="right">Comision Aviso:</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC02" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC02()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear">
						<TD align="right">Comision Confirmacion :</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC03" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC03()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark">
						<TD align="right">Portes :</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC09" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC09()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear">
						<TD align="right">Courier :</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC10" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC10()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trdark">
						<TD align="right">Swift Apertura:</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC11" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC11()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>
					<TR id="trclear">
						<TD align="right">Swift Adicional:</TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="E04LCMC12" size="15" maxlength="15"
							value="<%=lcComm.getE04LCMC12()%>"></TD>
						<TD nowrap width="15%"><SELECT name="">
							<OPTION value="A">Aplicante</OPTION>
							<OPTION value="B">Beneficiario</OPTION>
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

<DIV align="center"><INPUT id="EIBSBTN" type="submit" name="Enviar"
	value="Enviar"></DIV><div align="center"></div>
  </form>
</body>
</html>
