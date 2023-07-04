<!--
	Author:		Saif Mazhar
	Created:	1/26/07
	k0d3r
	-->
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

<jsp:useBean id="lcTran" class="datapro.eibs.beans.ELC051005Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

function goMsgSwift() {
    
     
     if (document.forms[0].E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+document.forms[0].E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }


 function goAction(op) {
	if(op == "A"){
		document.forms[0].SCREEN.value = 1;
		document.forms[0].submit();		 
	}else if(op == "D"){
		document.forms[0].SCREEN.value = 2;
		document.forms[0].submit();
	}else if(op == "R"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}else if(op == "M"){
		document.forms[0].SCREEN.value = 0;
		document.forms[0].submit();
	}
 }


<%if (!userPO.getPurpose().equals("NEW")) {%>
	builtNewMenu(lc_transfer_detail );
<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

</head>
<body>
<H3 align="center">Transferencia de Carta de Credito <IMG
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="bg_enter_maint,ELC5000"></H3>

<HR size="4">

<form method="post"	action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="107">
<INPUT TYPE=HIDDEN NAME="pCode" value="T">
<INPUT TYPE=HIDDEN NAME="E01LCMACC" value="<%=lcTran.getE05LCMACC()%>">
<INPUT TYPE=HIDDEN name="E05LCTNUM" value="<%=lcTran.getE05LCTNUM()%>">
	<INPUT TYPE=HIDDEN NAME="reason">
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Banco :</B></DIV>
						</TD>
						<TD nowrap width="20%">
						<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMBNK" readonly
							size="4" maxlength="2" value="<%=lcTran.getE05LCMBNK().trim()%>"
							readonly></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="right"><B>Número de Carta de Credito :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <%if (lcTran.getE05LCMACC().trim().equals("999999999999")) {%>
						<INPUT type="text" readonly="readonly" size="12" maxlength="12" value="Nuevo" readonly>
						<INPUT type="hidden" name="E05LCMACC"
							value="<%=lcTran.getE05LCMACC().trim()%>" readonly> <%} else {%>
						<INPUT type="text" readonly="readonly" name="E05LCMACC" size="12" maxlength="12"
							value="<%=lcTran.getE05LCMACC().trim()%>" readonly> <%}%> </B></DIV>
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Nuestra Referencia :</B></DIV>
						</TD>
						<TD nowrap width="20%">
						<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMORF" size="20"
							maxlength="16" value="<%=lcTran.getE05LCMORF().trim()%>" readonly></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="right"><B>Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" readonly="readonly" name="E05LCMPRO" size="4"
							maxlength="4" value="<%=lcTran.getE05LCMPRO().trim()%>" readonly>
						</B></DIV>
						</TD>

					</TR>
					<TR id="trdark0">
						<TD nowrap width="16%">
						<DIV align="right"><B>Referencia del Otro Banco :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" readonly="readonly" name="E05LCMTRF" size="20" maxlength="16"
							value="<%=lcTran.getE05LCMTRF().trim()%>" readonly> </B></DIV>
						</TD>

						<TD nowrap width="16%">
						<DIV align="right"><B>Descripcion de Producto :</B></DIV>
						</TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <INPUT type="text" readonly="readonly" name="E05DSCPRO" size="30" maxlength="30" value="<%=lcTran.getE05DSCPRO()%>"
							readonly> </B></DIV>
						</TD>

					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<h4>Dato Del Credito</h4>
<table width="100%" border="0" class="tableinfo" cellspacing="1">
	<tr id="trdark">
		<td align="right">Fecha de Emisión:</td>
		<td align="left"><INPUT type="text" readonly="readonly" name="E05LCMID12" size="2"
			maxlength="2"
			value='<%=(lcTran.getE05LCMID1().trim().equals("0") ? "" : lcTran.getE05LCMID1().trim())%>'
			onKeyPress="enterInteger()"> <INPUT type="text" readonly="readonly" name="E05LCMID22"
			size="2" maxlength="2"
			value='<%=(lcTran.getE05LCMID2().trim().equals("0") ? "" : lcTran.getE05LCMID2().trim())%>'
			onKeyPress="enterInteger()"> <INPUT type="text" readonly="readonly" name="E05LCMID32"
			size="2" maxlength="2"
			value='<%if (lcTran.getE05LCMID3().length() < 2 && !lcTran.getE05LCMID3().equals("0"))
	out.print("0");
out.print(
	(lcTran.getE05LCMID3().trim().equals("0")
		? ""
		: lcTran.getE05LCMID3().trim()));%>'
			onKeyPress="enterInteger()"><A
			href="javascript:DatePicker(document.forms[0].E05LCMID1,document.forms[0].E05LCMID2,document.forms[0].E05LCMID3)"></A> <IMG
			src="<%=request.getContextPath()%>/images/Check.gif"
			alt="campo obligatorio" border="0"></td>
		<td align="right">Fecha de Expiración:</td>
		<td align="left"><INPUT type="text" readonly="readonly" name="E05LCMEX12" size="2"
			maxlength="2"
			value='<%=(lcTran.getE05LCMEX1().trim().equals("0") ? "" : lcTran.getE05LCMEX1().trim())%>'
			onKeyPress="enterInteger()"> <INPUT type="text" readonly="readonly" name="E05LCMEX22"
			size="2" maxlength="2"
			value='<%=(lcTran.getE05LCMEX2().trim().equals("0") ? "" : lcTran.getE05LCMEX2().trim())%>'
			onKeyPress="enterInteger()"> <INPUT type="text" readonly="readonly" name="E05LCMEX32"
			size="2" maxlength="2"
			value='<%if (lcTran.getE05LCMEX3().length() < 2 && !lcTran.getE05LCMEX3().equals("0"))
	out.print("0");
out.print(
	(lcTran.getE05LCMEX3().trim().equals("0")
		? ""
		: lcTran.getE05LCMEX3().trim()));%>'
			onKeyPress="enterInteger()">  <IMG
			src="<%=request.getContextPath()%>/images/Check.gif"
			alt="campo obligatorio" border="0"></td>
	</tr>
	<tr>
		<td align="right">UONTD Original:</td>
		<td align="left"><INPUT type="text" readonly="readonly" name="E05LCMCCN2" size="9"
			maxlength="8" value="<%=lcTran.getE05LCMOAM().trim()%>"></td>
		<td align="right">Monto Disponible</td>
		<td><INPUT type="text" readonly="readonly" name="E05LCMMEB" size="9" maxlength="8"
			value="<%=lcTran.getE05LCMMEB().trim()%>"></td>
	</tr>
</table>
<h4>Beneficiario Original</h4>
<TABLE class="tableinfo">
	<TBODY><TR id="trdark">
		<TD align="right">Numero de Cliente o de Cuenta:
		<SELECT name="E05LCMACF" disabled="disabled">
					<OPTION value=" " <%if (!(lcTran.getE05LCMACF().equals("C") || lcTran.getE05LCMACF().equals("A")))
	out.print("selected");%> selected></OPTION>
					<OPTION value="C" <%if (lcTran.getE05LCMACF().equals("C"))
	out.print("selected");%>>Cliente</OPTION>
					<OPTION value="A" <%if (lcTran.getE05LCMACF().equals("A"))
	out.print("selected");%>>Cuenta</OPTION>
				</SELECT>
		</TD>
		<TD><INPUT type="text" readonly="readonly" name="E05LCMBAC" size="12" maxlength="12"
				value='<%=(!lcTran.getE05LCMBAC().trim().equals("0") ? lcTran.getE05LCMBAC() : "")%>'
				readonly></TD>
	</TR>
	<TR>
		<TD>
		<DIV align="right">Nombre :</DIV>
		</TD><TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMBN1" size="45" maxlength="35" value="<%=lcTran.getE05LCMBN1()%>" readonly></DIV>
		</TD>
		
	</TR>
	<TR id="trdark">
		<TD>
		<DIV align="right">Direcci&oacute;n :</DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMBN2" size="45" maxlength="35" value="<%=lcTran.getE05LCMBN2()%>" readonly></DIV>
		</TD>
	</TR>
	<TR id="trclear">
		<TD>
		<DIV align="right"></DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMBN3" size="45" maxlength="35" value="<%=lcTran.getE05LCMBN3()%>" readonly></DIV>
		</TD>
	</TR>
	<TR id="trdark">
		<TD>
		<DIV align="right"></DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCMBN4" size="45" maxlength="35" value="<%=lcTran.getE05LCMBN4()%>" readonly></DIV>
		</TD>
	</TR>
	<TR id="trclear">
		<TD>
		<DIV align="right">Estado</DIV>
		</TD>
		<TD align="left">
		<TABLE>
			<TBODY><TR>
				<TD><INPUT type="text" readonly="readonly" name="E05LCMBN5" size="2" maxlength="2" value="<%=lcTran.getE05LCMBN5()%>" readonly> C&oacute;digo Postal <INPUT type="text" readonly="readonly" name="E05LCMBN6" size="11" maxlength="10" value="<%=lcTran.getE05LCMBN6()%>" readonly> Pa&iacute;s <INPUT type="text" readonly="readonly" name="E05LCMBN7" size="4" maxlength="4" value="<%=lcTran.getE05LCMBN7()%>" readonly></TD>
			</TR>
		</TBODY></TABLE>
		</TD>
	</TR>
</TBODY></TABLE>
<h4>Nuevo Beneficiario</h4>
<TABLE class="tableinfo">
	<TBODY><TR id="trdark">
		<TD >Numero de Cliente o de Cuenta:<SELECT name="E05LCTACF" disabled="disabled">
				<OPTION value=" "
					<%if (!(lcTran.getE05LCTACF().equals("C") || lcTran.getE05LCTACF().equals("A"))) out.print("selected");%>
					selected></OPTION>
				<OPTION value="C"
					<%if (lcTran.getE05LCTACF().equals("C")) out.print("selected");%>>Cliente</OPTION>
				<OPTION value="A"
					<%if (lcTran.getE05LCTACF().equals("A")) out.print("selected");%>>Cuenta</OPTION>
			</SELECT></TD>
		<TD><INPUT type="text" readonly="readonly" name="E05LCTBAC" size="12" maxlength="12"
				value='<%=(!lcTran.getE05LCTBAC().trim().equals("0") ? lcTran.getE05LCTBAC() : "")%>'><A
				href="javascript: GetCustomerDetails('E05LCTBAC','E05LCTBN1','','','E05LCTBN7','E05LCTBN2','E05LCTBN3','E05LCTBN4','','E05LCTBN5','','','E05LCTBN6','','','','','')"></A></TD>
	</TR>
	<TR>
		<TD>
		<DIV align="right">Nombre :</DIV>
		</TD><TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCTBN1" size="45" maxlength="35" value="<%=lcTran.getE05LCTBN1()%>"> 
			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></DIV>
		</TD>
		
	</TR>
	<TR id="trdark">
		<TD>
		<DIV align="right">Direcci&oacute;n :</DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCTBN2" size="45" maxlength="35" value="<%=lcTran.getE05LCTBN2()%>"></DIV>
		</TD>
	</TR>
	<TR id="trclear">
		<TD>
		<DIV align="right"></DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCTBN3" size="45" maxlength="35" value="<%=lcTran.getE05LCTBN3()%>"></DIV>
		</TD>
	</TR>
	<TR id="trdark">
		<TD>
		<DIV align="right"></DIV>
		</TD>
		<TD>
		<DIV align="left"><INPUT type="text" readonly="readonly" name="E05LCTBN4" size="45" maxlength="35" value="<%=lcTran.getE05LCTBN4()%>"></DIV>
		</TD>
	</TR>
	<TR id="trclear">
		<TD>
		<DIV align="right">Estado</DIV>
		</TD>
		<TD align="left">
		<TABLE>
			<TBODY><TR>
				<TD>
					<INPUT type="text" readonly="readonly" name="E05LCTBN5" size="2" maxlength="2" value="<%=lcTran.getE05LCTBN5()%>"> C&oacute;digo Postal 
					<INPUT type="text" readonly="readonly" name="E05LCTBN6" size="11" maxlength="10" value="<%=lcTran.getE05LCTBN6()%>"> Pa&iacute;s 
					<INPUT type="text" readonly="readonly" name="E05LCTBN7" size="4" maxlength="4" value="<%=lcTran.getE05LCTBN7()%>"> 
				</TD>
			</TR>
		</TBODY></TABLE>
		</TD>
	</TR>
</TBODY></TABLE>
<br>
<INPUT type="hidden" name="H02FLGWK1" value=""></form>
<!--<h5><%
String s = lcTran.toString();
for(int i = 0; i < s.length(); i++)
{
	if(s.charAt(i) == ':')	out.print("<BR>");
	else if (s.charAt(i) == '<') out.print("{");
		else if (s.charAt(i) == '>') out.print("}");
	else 					out.print(s.charAt(i));

}%></h5>-->
</body>
</html>
