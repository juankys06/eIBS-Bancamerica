<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import="datapro.eibs.master.*,datapro.eibs.beans.*"%>
<title>Foreign Exchange G/L Cross Reference List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="qryForexSearchParams"
	class="datapro.eibs.beans.EFE0300DSMessage" scope="session" />
<jsp:useBean id="beanListTOT" class="datapro.eibs.beans.JBObjList"
	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="beanList" class="datapro.eibs.beans.JBObjList"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />

<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


function returnToMain()
{
	document.forms[0].SCREEN.value = 1;
    document.forms[0].submit();	
}

function goAction(op) {
  	document.forms[0].SCREEN.value = 4;
    document.forms[0].submit();
}

function setInfo(index) {
    document.forms[0].OPTION.value = index;
}

 function closeEnter(){
   	  enterNew.filters[0].apply();
      enterNew.style.visibility="hidden";
      enterNew.filters[0].Play();
 }
 
 function ShowEnterCod() {	 

	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterNew.style.pixelTop=' + y);
     eval('enterNew.style.pixelLeft=' + x);
	 enterNew.filters[0].apply();
     enterNew.style.visibility="visible";
     enterNew.filters[0].Play();
}
 
function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

</SCRIPT>
</head>

<body>

<%
		if (!error.getERRNUM().equals("0")) {
		out.println("<SCRIPT Language=\"Javascript\">");
		error.setERRNUM("0");
		out.println("       showErrors()");
		out.println("</SCRIPT>");
	    }
%>

<h3 align="center">Listado de Consulta de Monedas Extranjeras<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="fx_mod_ext_list.jsp, EFE0300"></h3>
<hr size="4">

<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0300">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3"> <INPUT TYPE=HIDDEN
	NAME="OPTION" VALUE=""> <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
<INPUT TYPE=HIDDEN NAME="CCY" VALUE=""> <INPUT TYPE=HIDDEN
	NAME="TYP" VALUE=""> <INPUT TYPE=HIDDEN NAME="CLS" VALUE="">
<INPUT TYPE=HIDDEN NAME="actRow" VALUE="0"> <INPUT TYPE=HIDDEN
	NAME="totalRow" VALUE="0">

<div id="enterNew"
	style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)"
	onClick="cancelBub()"></div>

<%
if (beanList.getNoResult()) {
%>
<TABLE class="tbenter" width=100% height=30%">
	<TR>
		<TD>
		<div align="center">
		<h4 style="text-align:center">No existen resultados para los
		criterios de busqueda.</h4>
		</div>
		</TD>
	</TR>
</TABLE>

<%
} else {
%> <BR>
<table class="tbenter">
	<TR>
		<TD ALIGN=CENTER class="TDBKG" width="33%"><a
			href="javascript:goAction('actRow')">Detalle</a>
		<TD ALIGN=CENTER class="TDBKG" width="50%"><a
			href="javascript:returnToMain();">Retornar</a></TD>
	</TR>
</table>
<br>
<TABLE class="tableinfo" id="dataTable">
	<TR id="trdark">
		<TH ALIGN=CENTER nowrap width="1%"></TH>
		<TH ALIGN=CENTER nowrap>Fecha Transacci&oacute;n</TH>
		<TH ALIGN=CENTER nowrap>Trader</TH>
		<TH ALIGN=CENTER nowrap>ID</TH>
		<TH ALIGN=CENTER nowrap>Nombre</TH>
		<TH ALIGN=CENTER nowrap>Operaci&oacute;n</TH>
		<TH ALIGN=CENTER nowrap>Monto</TH>
		<TH ALIGN=CENTER nowrap>Moneda</TH>
		<TH ALIGN=CENTER nowrap>Tasa</TH>
		<TH ALIGN=CENTER nowrap>Referencia</TH>
	</TR>

	<%
			String typeOp = "";
			boolean firstTime = true;
			String chk = "";
			beanList.initRow();
			int k = 1;
			while (beanList.getNextRow()) {
			    EFE0300DSMessage msgList = (EFE0300DSMessage) beanList
		    .getRecord();
			    if (firstTime) {
		chk = "checked";
		firstTime = false;
			    } else {
		chk = "";
			    }
			    if (msgList.getE01FEIINF().equals("D")) {
		typeOp = msgList.getE01FEITYP();
		if (typeOp.equalsIgnoreCase("P"))
		    typeOp = "Compra";
		else if (typeOp.equalsIgnoreCase("S"))
		    typeOp = "Venta";
	%>
	<TR>
		<td align="center" nowrap><input type="radio" name="ROW1"
			<%=chk%> value="<%=beanList.getCurrentRow()%>"
			onClick="setInfo('<%=beanList.getCurrentRow()%>')"></td>
		<td style="text-align: center; white-space:nowrap;"><%=Util.formatDate(msgList.getE01FEIDDD(),
					msgList.getE01FEIDMM(), msgList
						.getE01FEIDYY())%></td>
		<td NOWRAP align=center><%=Util.formatCell(msgList.getE01FEIUSR())%></td>
		<td style="text-align: right; white-space:nowrap; padding-right: 5px;"><%=Util.formatCell(msgList.getE01FEICUN())%></td>
		<td style="white-space:nowrap; padding-left: 10px;"><%=Util.formatCell(msgList.getE01FEINAM())%></td>
		<td style="text-align: center; white-space:nowrap;"><%=Util.formatCell(typeOp)%></td>
		<td
			style="text-align: right; white-space:nowrap; padding-right: 10px;"><%=Util.formatCell(msgList.getE01FEIAMR())%></td>
		<td style="text-align: center; white-space:nowrap;"><%=Util.formatCell(msgList.getE01FEICCY())%></td>
		<td
			style="text-align: right; white-space:nowrap; padding-right: 10px;"><%=Util.formatCell(msgList.getE01FEIRAT())%></td>
		<td
			style="text-align: right; white-space:nowrap; padding-right: 10px;"><%=Util.formatCell(msgList.getE01FEIREF())%></td>
	</TR>

	<%
			    } // if 
			    k++;
			}
	%>
</TABLE>
<SCRIPT language="JavaScript">     
  	document.forms[0].totalRow.value="<%=k%>";
	 showChecked("ROW1");          
  </SCRIPT> <%
 }
 %>
</form>
<table class="tableinfo">
	<tr>
		<td nowrap style="text-align:center;">
		<table>
			<tr>
				<td nowrap colspan="11">
				<div align="center"><b>Resumen</b></div>
				</td>
			</tr>
			<tr id="trdark">
				<th
					style="white-space:nowrap;width:60px;text-align: center;font-size:9px;">Moneda</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Balance
				Inicial</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Tasa
				Inicial</th>
				<th
					style="white-space:nowrap;width:60px;text-align: center;font-size:9px;">Total
				Compras M.E.</th>
				<th
					style="white-space:nowrap;width:60px;text-align: center;font-size:9px;">Total
				Compras M.B.</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Tasa
				Ponderada Compras</th>
				<th
					style="white-space:nowrap;width:60px;text-align: center;font-size:9px;">Total
				Ventas M.E.</th>
				<th
					style="white-space:nowrap;width:60px;text-align: center;font-size:9px;">Total
				Ventas M.B.</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Tasa
				Ponderada Ventas</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Balance
				Final</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Tasa
				Final</th>
				<th
					style="white-space:nowrap;width:80px;text-align: center;font-size:9px;">Ganancia<br>
				o P&eacute;rdida</th>
			</tr>
			<%
			if (beanListTOT.getNoResult()) {
			%>
			<tr id="trdark0">
				<td nowrap colspan="7">
				<div align="center"><b>No hay registros de Totales de
				Monedas.</b></div>
				</td>
			</tr>
			<%
					} else {
					beanListTOT.initRow();
					int k = 1;
					while (beanListTOT.getNextRow()) {
					    EFE0300DSMessage msgList = (EFE0300DSMessage) beanListTOT
				    .getRecord();
					    if (msgList.getE01FEIINF().equals("R")) {
			%>
			<tr id="trclear00">
				<td style="white-space: nowrap; text-align: left;"><%=Util.formatCell(msgList.getE01FEIRCY())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIINI())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIINR())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIRCO())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEITOC())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEITPC())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIRVE())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEITOV())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEITPV())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIFIN())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIENR())%></td>
				<td style="white-space: nowrap; text-align: right;"><%=Util.formatCell(msgList.getE01FEIGAN())%></td>
			</tr>
			<%
					    } // if 
					    k++;
					} // while
				    } // if else
			%>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
