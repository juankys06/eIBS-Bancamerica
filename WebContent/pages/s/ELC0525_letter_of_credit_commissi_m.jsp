<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letters of Credit Opening/Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">


<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="lcComm" class="datapro.eibs.beans.ELC051004Message"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />

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


function goDetail(scr){
	document.forms[0].SCREEN.value = scr;
	document.forms[0].submit();
}

 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0525_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
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
	}else if(op == "V"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}
 }


<%if (!userPO.getPurpose().equals("NEW")) {%>
	builtNewMenu(lc_maint_detail);
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
<h3 align="center">Comisiones de Cartas de Credito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="letter_of_credit_commissi_m.jsp,ELC0525"></h3>
<hr size="4">


<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<INPUT type="hidden" name="E01LCMACC" value="<%=lcComm.getE04LCMACC().trim()%>" readonly> 															
<INPUT TYPE=HIDDEN NAME="E01LCMBNK" value="<%= lcComm.getE04LCMBNK().trim()%>">
	<INPUT TYPE=HIDDEN NAME="reason">
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
					size="4" maxlength="2" value="<%=lcComm.getE04LCMBNK().trim()%>"></div>
				</td>
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
				<div align="left"><b><INPUT type="text" name="E04LCMACC" size="12"
					maxlength="12" value="<%=lcComm.getE04LCMACC().trim()%>" readonly>
					<input type="hidden" name="E01LCMACC" value="<%=lcComm.getE04LCMACC().trim()%>" readonly>					
				</b></div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="16%">
				<div align="right"><b>Fecha Emision:</b></div>
				</td>
				<td nowrap width="20%" colspan=3>
				<div align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10"
					value="<%=lcComm.getE04LCMID1()
	+ "/"
	+ lcComm.getE04LCMID2()
	+ "/"
	+ (lcComm.getE04LCMID3().length() == 1
		? "0" + lcComm.getE04LCMID3()
		: lcComm.getE04LCMID3())%>"></div>
				</td>
				<td nowrap width="16%">
				<div align="right"><b>Tarifa de Cargos y Moneda:</b></div>
				</td>
				<td nowrap width="16%" align="left"><INPUT type="text"
					name="E04LCMTAR" size="2" maxlength="2"
					value="<%=lcComm.getE04LCMTAR()%>" readonly> <INPUT type="text"
					name="E04LCMTCY" size="3" maxlength="3"
					value="<%=lcComm.getE04LCMTCY()%>" readonly></td>
			</tr>
			<tr id="trclear">
				<td nowrap width="16%">
				<div align="right"><b>Fecha Expiracion:</b></div>
				</td>
				<td nowrap width="20%" colspan=3>
				<div align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10"
					value="<%=lcComm.getE04LCMEX1()
	+ "/"
	+ lcComm.getE04LCMEX2()
	+ "/"
	+ (lcComm.getE04LCMEX3().length() == 1
		? "0" + lcComm.getE04LCMEX3()
		: lcComm.getE04LCMEX3())%>"></div>
				</td>
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
			<TABLE border="0" cellspacing="0" width="100%">
				<TBODY>
					<TR id="trdark">
						<TD align="right"></TD>
						<TD nowrap align="center" width="17%"><B>Monto</B></TD>
						<TD nowrap align="center" width="15%"><B>Por Cta de</B></TD>
						<TD nowrap align="center" width="26%"></TD>
						<TD nowrap align="center" width="12%"></TD>
					</TR>

					<%int size = 9;
java.util.ArrayList name = new java.util.ArrayList();
java.util.ArrayList field1 = new java.util.ArrayList();
java.util.ArrayList field2 = new java.util.ArrayList();

name.add("Comision Apertura");
field1.add("E04LCMC01");
field2.add("E04LCMP01");

name.add("Comision Aviso");
field1.add("E04LCMC02");
field2.add("E04LCMP02");

name.add("Comision Confirmacion");
field1.add("E04LCMC03");
field2.add("E04LCMP03");

name.add("Timbres");
field1.add("E04LCMC08");
field2.add("E04LCMP08");

name.add("Portes");
field1.add("E04LCMC09");
field2.add("E04LCMP09");

name.add("Courier");
field1.add("E04LCMC10");
field2.add("E04LCMP10");

name.add("Swift Apertura");
field1.add("E04LCMC11");
field2.add("E04LCMP11");

name.add("Swift Adicional");
field1.add("E04LCMC12");
field2.add("E04LCMP12");

name.add("Gastos Notariales");
field1.add("E04GASNOT");
field2.add("temp1");

name.add("Seguro de Desgravamen");
field1.add("E04SEGDES");
field2.add("temp2");

java.util.ArrayList value2 = new java.util.ArrayList();
for (int i = 0; i < field2.size() - 2; i++) {
	value2.add(lcComm.getField((String) field2.get(i)).getString().trim());
}
value2.add("");
value2.add("");

for (int i = 0; i < name.size(); i++) {%>

					<TR id="<%=(i % 2 == 0 ? "trclear" : "trdark")%>">
						<TD align="right"><%=(String) name.get(i)%></TD>
						<TD nowrap width="17%" align="center"><INPUT type="text"
							name="<%=field1.get(i)%>" size="15" maxlength="15"
							value="<%=lcComm.getField((String) field1.get(i)).getString().trim()%>"
							readonly="readonly">
						</TD>
						<TD nowrap width="15%"><SELECT name="<%="bla" + i%>" disabled="disabled">
							<OPTION value="A">Aplicante</OPTION>
							<%if (i < name.size() - 2) {%>
							<OPTION value="B" <%=(value2.equals("B") ? "selected" : "")%>>
							Beneficiario</OPTION>
							<%}%>
						</SELECT></TD>
						<TD nowrap width="26%"></TD>
						<TD nowrap width="12%"></TD>
					</TR>

					<%}%>


				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>


<h4 style="text-align: center"><input type="checkbox" name="H04FLGWK2"
	value="A"
	<%if (lcComm.getH04FLGWK2().equals("A")) {
	out.print("checked");
}%>
	disabled="disabled"> Aceptar con Advertencias</h4>
</form>
</body>
</html>
