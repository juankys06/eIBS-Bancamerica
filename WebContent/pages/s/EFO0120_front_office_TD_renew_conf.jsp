<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head> 
<title>Front Office - Confirmación de Renovación de Certificado</title>
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
	name="EIBS_GIF" alt="front_office_TD_conf.jsp,EFO0120"></h3>
</div>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="1">
<INPUT type="hidden" name="CUSTOMER" value="<%= msgCust.getE02FESCUN().trim()%>">

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
					<TD nowrap align="right" width="15%"><B>Nombre de Cliente </B>:</TD>
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
					<TD nowrap width="15%">	<H1>Depositar A :</H1></TD><TD nowrap width="35%"></TD>
					
					<TD nowrap width="15%"></TD>
					<TD nowrap width="35%"></TD>
				</TR>
			</TBODY>
		</TABLE>

		<TABLE width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD height="10" align="right" width="15%">Nombre : </TD><TD height="10" width="35%">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAP" size="35" maxlength="35" value="<%= msgCust.getE02FESCAP().trim()%>">
					</TD>
					
					<TD height="10" align="right" width="15%"></TD>
					<TD height="10" width="35%"></TD>
				</TR>
				<TR id="trintro">
					<TD height="10" align="right" width="15%">Cuenta : </TD><TD height="10" width="35%" align="left">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAC" size="15" maxlength="10" value="<%= msgCust.getE02FESCAC().trim()%>">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAD" size="50" maxlength="35" value="<%= msgCust.getE02FESCAD().trim()%>">
				</TD>
					
					<TD height="10" align="right" width="15%"></TD>
					<TD height="10" width="35%" align="left"></TD>
				</TR>
				<TR id="trintro">
					<TD height="10" align="center" width="15%" colspan="2">
					<FONT color="red">Cuenta a recibir Capital y/o Interés</FONT></TD>
					<TD height="10" align="center" width="15%" colspan="2"></TD>
				</TR>
			</TABLE>

		<TABLE width="100%" height="100" border="0">
<TR>
				<TD align="right" height="40" valign="middle" width="36%">Monto :</TD>
				<TD align="left" height="40" valign="middle" width="64%">
					<INPUT style="font: bold" type="text" readonly name="E02FESTCY" size="4" 
					value="<%= msgCust.getE02FESTCY().trim()%>"> <INPUT
					style="font: bold" type="text" readonly name="E02FESAMN" size="15"
					value="<%= Util.fcolorCCY(msgCust.getE02FESAMN().trim())%>">
				 </TD>
			</TR>
			<TR>
				<TD align="right" height="40" valign="middle" width="36%">Tasa :</TD>
				<TD align="left" height="40" valign="middle" width="64%">
					<INPUT style="font: bold" type="text" readonly name="E02FESDEX" size="15" 
					value="<%= msgCust.getE02FESDEX().trim()%>"> %</TD>
			</TR>
			<TR>
				<TD align="right" height="40" valign="middle" width="36%">Términos :</TD>
				<TD align="left" height="40" valign="middle" width="64%">
					<INPUT style="font: bold" type="text" readonly name="E02FESROY" size="5" 
					value="<%= msgCust.getE02FESROY().trim()%>"> Días</TD>
			</TR>
			<TR>
				<TD align="right" height="40" valign="middle" width="36%">Instrucciones de Renovación :</TD>
				<TD align="left" height="40" valign="middle" width="64%"><INPUT
					style="font: bold" type="text" readonly name="E02FESROD" size="50"
					value='<%if (msgCust.getE02FESROC().equals("H")) out.print("H - Renovar Capital. Pagar Interés Mensualmente");
								else if (msgCust.getE02FESROC().equals("B")) out.print("B - Renovar Capital. Pagar Interés al Vencimiento"); 
									else if (msgCust.getE02FESROC().equals("A")) out.print("A - Renovar Capital y Acumular Interés"); 
										else out.print("E - No Renovar");%>'>
				</TD>
			</TR>
			<TR>
				<TD align="right" height="40" valign="middle" width="36%"> Número de Certificado
				:</TD>
				<TD align="left" height="40" valign="middle" width="64%">
					<INPUT style="font: bold" type="text" readonly name="E02FESREF" size="15" value="<%= msgCust.getE02FESREF().trim()%>"></TD>
			</TR>
			<TR>
				<TD align="center" colspan="4">
					Instrucciones Especiales :<B> </B><INPUT style="font: bold" type="text" readonly name="E02FESREM" size="71"
					value="<%= msgCust.getE02FESREM().trim()%>">
			</TD>
			</TR>
			</TBODY>
			<TR>
				<TD align="right" width="36%"></TD>
				<TD align="left" colspan="3"></TD>
			</TR>
			<TR>
				<TD height="10" align="left" colspan="4"><B>TRANSACCION DE  <%=userPO.getHeader1()%>      VA SER PROCESADA Y UNA CONFIRMACION SERA ENVIADA UNA VEZ ESTE APROBADA.</B></TD>
			</TR>
		</TABLE>
		</td>
	</tr>
</table>

</TABLE>

<table width="100%">		
  	<tr>
  		<td width="50%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Print" value="Imprimir" onClick="eIBSPrint();">
     	  </div>	
  		</td>
  		<td width="50%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="Submit" name="Submit" value="Continuar">
     	  </div>	
  		</td>
  	</tr>	
</table>	

</form>
</body>
</html>
