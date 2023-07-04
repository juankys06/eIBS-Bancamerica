<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head> 
<title>Front Office - Confirmaci�n de Certificado de D�posito</title>
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
					<TD nowrap align="right" width="15%"><B>Nombre del Cliente </B>:</TD>
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
					<TD nowrap align="left" width="15%"><B></B><H1>Debitar de :</H1></TD>
					<TD nowrap width="35%"></TD>
					<TD nowrap width="15%">	<H1>Depositar en :</H1></TD>
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
						<INPUT style="font: bold" type="text" readonly name="E02FESDAC" size="15" value="<%= msgCust.getE02FESDAC().trim()%>">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAD" size="50" maxlength="35" 
							value="<%= msgCust.getE02FESDAD().trim()%>">
					</TD>
					<TD height="10" align="right" width="15%">Cuenta : </TD>
					<TD height="10" width="35%" align="left">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAC" size="15" value="<%= msgCust.getE02FESCAC().trim()%>">
						<INPUT style="font: bold" type="text" readonly name="E02FESCAD" size="50" maxlength="35"
							value="<%= msgCust.getE02FESCAD().trim()%>">
				</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="15%" align="center" colspan="2">
					<FONT color="red">Esta es la cuenta donde se van a retirar los fondos.</FONT></TD>
					<TD height="10" align="center" width="15%" colspan="2">
					<FONT color="red">Cuenta donde se recibir� el Capital y/o Interes.</FONT></TD>
				</TR>
			</TABLE>

		<TABLE width="100%" height="100" border="0">
<TR>
				<TD width="50%" align="right" height="40" valign="middle">Monto :</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESTCY" size="4" 
					value="<%= msgCust.getE02FESTCY().trim()%>"> <INPUT
					style="font: bold" type="text" readonly name="E02FESAMN" size="15"
					value="<%= Util.fcolorCCY(msgCust.getE02FESAMN().trim())%>">
				 </TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="40" valign="middle">Tasa :</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESDEX" size="15" 
					value="<%= msgCust.getE02FESDEX().trim()%>"> %</TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="40" valign="middle">T�rminos :</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESROY" size="5" 
					value="<%= msgCust.getE02FESROY().trim()%>"> D�as</TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="40" valign="middle">Producto :</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESPRD" size="50" 
					value="<%= msgCust.getE02FESPRD().trim()%>"> 
				</TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="40" valign="middle">Instrucciones de Renovaci�n :</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESROD" size="50" 
					value='<%if (msgCust.getE02FESROC().equals("H")) out.print("H - Renovar Capital. Pagar Inter�s Mensualmente");
								else if (msgCust.getE02FESROC().equals("B")) out.print("B - Renovar Capital. Pagar Inter�s al Vencimiento"); 
									else if (msgCust.getE02FESROC().equals("A")) out.print("A- Renovar Capital y Acumular Inter�s"); 
										else out.print("E - No Renovar");%>'>
				</TD>
			</TR>
			<TR>
				<TD width="50%" align="right" height="40" valign="middle"> N�mero de Referencia Interna
				:</TD>
				<TD align="left" width="50%" height="40" valign="middle">
					<INPUT style="font: bold" type="text" readonly name="E02FESREF" size="15" value="<%= msgCust.getE02FESREF().trim()%>"></TD>
			</TR>
			<TR>
				<TD width="50%" align="center" colspan="4">
					Instrucciones Especiales :<B> </B><INPUT style="font: bold" type="text" readonly name="E02FESREM" size="71"
					value="<%= msgCust.getE02FESREM().trim()%>">
			</TD>
			</TR>
			</TBODY>
			<TR>
				<TD width="50%" align="right"></TD>
				<TD align="left" width="50%" colspan="3"></TD>
			</TR>
			<TR>
				<TD height="10" width="50%" align="left" colspan="4"><B>CUANDO USTED ACEPTA ESTA TRANSACCION DE <%=userPO.getHeader1()%>     , ESTA SERA PROCESADA Y UNA CONFIRMACION VA A SER GENERADA UNA VEZ ESTA ESTE APROBADA..</B></TD>
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
