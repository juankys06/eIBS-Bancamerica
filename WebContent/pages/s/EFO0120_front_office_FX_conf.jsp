<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util"%>
<head> 
<title>Front Office - Confirmación de Transferencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012002Message" scope="session" />   
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<SCRIPT Language="Javascript">
<!--- hide script from old browsers
//  Process according with user selection

function doEmail(mail,subject,body) {
	locationstring = 'mailto:' + mail + "?subject=" + escape(subject) + "&body=" + escape(body);
  	window.location.replace(locationstring);
	return;
}

function doContinue(cun) {
	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=1&CUSTOMER=" + cun;
	window.location.href=pg;
}
// end hiding from old browsers -->
</SCRIPT>

</head>
<body>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>
<div align="center">
<h3>From Office - <%=userPO.getHeader1()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="front_office_FX_conf.jsp,EFO0120"></h3>
</div>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="1">
<INPUT type="hidden" name="CUSTOMER" value="<%= msgCust.getE02FESCUN().trim()%>">
<INPUT type="hidden" name="E02FESIAD" value="<%= msgCust.getE02FESIAD().trim()%>">

<TABLE class="tbenter" width="100%">
 	<tr id="trintrot"> 
    	<td colspan="7"><%= currUser.getH01USR()%></td>
        <td width="20%"> 
          <div align="right"><%= datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),currUser.getE01RDD(),currUser.getE01RDY())%></div>
        </td>
    </tr>    
	<TR> 
	</TR>
</TABLE>

<table class="tableinfo" width="100%">
	<tr>
		<td nowrap width="100%">
		<TABLE width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD nowrap align="right" width="15%"><B>Nomber del Cliente </B>:</TD>
					<TD nowrap width="35%">
						<INPUT type="text" readonly name="E02FESNA1" size="35" value="<%= msgCust.getE02FESNA1().trim()%>">
					</TD>
					<TD nowrap align="left" width="15%"><B></B></TD>
					<TD nowrap width="35%"></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="15%"></TD>
					<TD nowrap width="35%"></TD>
					<TD nowrap align="right" width="15%"></TD>
					<TD nowrap width="35%"></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="left" width="15%"><B></B><H1>Desde :</H1></TD>
					<TD nowrap width="35%"></TD>
					<TD nowrap width="15%">	<H1>A:</H1></TD>
					<TD nowrap width="35%"></TD>
				</TR>
			</TBODY>
		</TABLE>

		<TABLE width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD height="10" width="15%" align="right">Nombre : </TD>
					<TD height="10" align="left" width="35%">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAP" size="35" maxlength="35"
							value="<%= msgCust.getE02FESDAP().trim()%>">
					</TD>
					<TD height="10" align="right" width="15%">Nombre : </TD>
					<TD height="10" width="35%">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAP" size="35" maxlength="35"
							value="<%= msgCust.getE02FESCAP().trim()%>">
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="15%" align="right">Cuenta : </TD>
					<TD height="10" align="left" width="35%">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAC" size="13" value="<%= msgCust.getE02FESDAC().trim()%>">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAD" size="50" value="<%= msgCust.getE02FESDAD().trim()%>">
					</TD>
					<TD height="10" align="right" width="15%">Cuenta : </TD>
					<TD height="10" width="35%" align="left">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAC" size="13" value="<%= msgCust.getE02FESCAC().trim()%>">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAD" size="50" value="<%= msgCust.getE02FESCAD().trim()%>">
				</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="15%" align="right">Monto :</TD>
					<TD height="10" align="left" width="35%">
						<INPUT style="font: bold" type="text" name="E02FESDCY" size="4" maxlength="3" value="<%= msgCust.getE02FESDCY().trim()%>" readonly>
						<INPUT style="font: bold" type="text" name="E02FESAMN" size="15" value="<%= msgCust.getE02FESAMN().trim()%>">
					</Td>
					<TD height="10" align="right" width="15%"> Monto : </TD>
					<TD height="10" width="35%">
						<INPUT style="font: bold"type="text" name="E02FESCCY" size="4" maxlength="3" value="<%= msgCust.getE02FESCCY().trim()%>" readonly>
						<INPUT style="font: bold" type="text" readonly name="E02FESDAM" size="15" value="<%= Util.fcolorCCY(msgCust.getE02FESDAM().trim())%>"></TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="15%" align="center" colspan="2">
					<FONT color="red">Esta es la cuenta donde salen los fondos.</FONT></TD>
					<TD height="10" align="center" width="15%" colspan="2">
					<FONT color="red">Esta es la cuenta donde se depositaran los fondos.</FONT></TD>
				</TR>
			</TABLE>

		<TABLE width="100%" height="100" border="0">
			<TR>
				<TD width="50%" align="right" height="80" valign="middle">Tasa de Cambio :</TD>
				<TD align="left" width="50%" height="80" valign="middle"><INPUT style="font: bold"
					type="text" readonly name="E02FESDEX" size="15" maxlength="10"
					value="<%= Util.fcolorCCY(msgCust.getE02FESDEX().trim())%>"></TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="80" valign="middle">Número de Referencia Interna
				:</TD>
				<TD align="left" width="50%" height="80" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESREF" size="15" value="<%= msgCust.getE02FESREF().trim()%>"></TD>
			</TR>
			<TR>
				<TD width="50%" align="center" colspan="4">
					<INPUT style="font: bold" type="text" readonly name="E02FESREM" size="71"
					value="<%= msgCust.getE02FESREM().trim()%>">
			</TD>
			</TR>
			<TR>
				<TD width="50%" align="right"></TD>
				<TD align="left" width="50%" colspan="3"></TD>
			</TR>
			<TR>
				<TD height="10" width="50%" align="left" colspan="4"><B>
				<%=userPO.getHeader1()%> Transaccións estan sujetas a aprobación del Banco. Una confirmatión será emitida una vez aprobada.</B></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
</table>

<table width="100%">		
  	<tr>
  		<td width="50%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Print" value="Imprimir" onClick="eIBSPrint();">
     	  </div>	
  		</td>
		<td width="50%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Continue" value="Continuar"
				onClick="doContinue('<%= msgCust.getE02FESCUN().trim()%>')">
     	  </div>	
  		</td>
  	</tr>	
</table>	

</form>
</body>
</html>
