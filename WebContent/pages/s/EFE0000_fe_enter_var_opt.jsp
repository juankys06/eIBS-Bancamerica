<%@ page import="datapro.eibs.master.Util"%>
<html>
<head>
<title>Entrada de Tiquetes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
<%String DscE01FESDID = "";%>

function analice(){
 if(!document.forms[0].E01FESREF.value == ""){
    document.forms[0].submit();
}
}

function RefreshList(){

  document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=3000";
  document.forms[0].submit();

}

function getFields(opt, dsc, typ) {
	document.forms[0].INPTOPT.value = opt;
	document.forms[0].INPTDSC.value = dsc;
	document.forms[0].INPTTYP.value = typ;
    
    if(!document.forms[0].INPTDSC.value == "SPOT FE" && document.forms[0].INPTOPT.value == "enabled") {
	    document.forms[0].INPTOPT.disabled=enabled;   
<%DscE01FESDID = "ID No Cliente : ";%>		
	}
}

</SCRIPT>
</head>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />
<jsp:useBean id="custList" class="datapro.eibs.beans.JBList"
	scope="session" />
<jsp:useBean id="trOption" class="datapro.eibs.beans.TrOption"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />
<jsp:useBean id="prdList" class="datapro.eibs.beans.JBObjList"
	scope="session" />

<body bgcolor="#FFFFFF">
<H3 align="center">Tesorer&iacute;a - Creación de Tiquetes<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="fe_enter_var_opt,EFE0000"></H3>

<hr size="4">

<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200"> <input type=HIDDEN
	name="E01FESTYP"> <input type=HIDDEN name="INPTOPT"
	value="<%=datapro.eibs.master.Util.addLeftChar('0', 2, trOption
					.getHeader1())%>">
<input type=HIDDEN name="INPTDSC" value=""> <input type=HIDDEN
	name="INPTTYP" value="">
<center>


<table class="tableinfo" width="73%">
	<tr id="trintrot">
		<td colspan="7"><%=currUser.getH01USR()%></td>
		<td width="20%">
		<div align="right"><%=datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),
					currUser.getE01RDD(), currUser.getE01RDY())%></div>
		</td>
	</tr>

	<%
		prdList.initRow();
		int currentCol = 0;
		String chk = "";
		boolean firstTime = true;

		while (prdList.getNextRow()) {
			datapro.eibs.beans.EWD0301DSMessage msgList = (datapro.eibs.beans.EWD0301DSMessage) prdList
			.getRecord();
			if (currentCol == 0) {
	%>
	<tr id="trintro">
		<%
				}
				
				//out.println("*hdr1*" + trOption.getHeader1() + "***");
				//out.println("*daty*" + msgList.getSWDATY() + "***");
				//*
				// if (firstTime == true) {
				// if (trOption.getHeader1().trim().equals("")) {
				// chk = "checked";
				// out.println("<SCRIPT Language=\"Javascript\">");
				// out.println("       getFields('" + msgList.getSWDACD() + "','" + msgList.getSWDDSC() + "','" + msgList.getSWDATY()+"')");
				// out.println("</SCRIPT>");
				// }
				// firstTime = false;	
				// } else {
				// chk = "";
				// }

				if (trOption.getHeader1().trim().equals("")) {
					if (msgList.getSWDATY().equals("SPOT")) {
				chk = "checked";
				out.println("<SCRIPT Language=\"Javascript\">");
				out.println("       getFields('" + msgList.getSWDACD()
						+ "','" + msgList.getSWDDSC() + "','"
						+ msgList.getSWDATY() + "')");
				out.println("</SCRIPT>");
					}
				} else if (trOption.getHeader1().equals(msgList.getSWDATY())) {
					chk = "checked";
					out.println("<SCRIPT Language=\"Javascript\">");
					out.println("       getFields('" + msgList.getSWDACD()
					+ "','" + "','" + msgList.getSWDDSC() + "','"
					+ msgList.getSWDATY() + "')");
					out.println("</SCRIPT>");
				}
		%>
		<td width="5%" height="10">
		<div align="center"><input type="radio" name="CINPTOPT"
			value="<%=msgList.getSWDACD()%>"
			onClick="getFields('<%=msgList.getSWDACD()%>','<%=msgList.getSWDDSC()%>','<%=msgList.getSWDATY()%>')"
			<%=chk%>></div>
		</td>
		<td width="20%" height="10"><%=msgList.getSWDDSC()%></td>
		<%
				currentCol++;
				if (currentCol == 4) {
					currentCol = 0;
		%>
	</tr>
	<%
	}
	%>
	<%
	}
	%>
	<tr id="trdark">
		<td colspan="8">&nbsp;</td>
	</tr>
	<tr id="trintro">
		<td colspan="2" align="right"><%=DscE01FESDID%></td>
		<td colspan="6"><input type="text" name="E01FESLN3" size="31"
			maxlength="30"></td>
	</tr>
	<tr id="trintro">
		<td colspan="2">
		<div align="right">Cliente :</div>
		</td>
		<td colspan="6">
		<div><input type="text" name="CUNCOD" size="9" maxlength="9">
		<input type="text" name="CUNDSC" size="50" maxlength="45"> <a
			href="javascript:GetCustomerDescId('CUNCOD','CUNDSC','')"><img
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="bottom" border="0"></a></div>
		</td>

	</tr>
	<tr>
		<td colspan="2">
		<div align="right">N&uacute;mero de Referencia:</div>
		</td>
		<td colspan="6">
		<div><input type="hidden" name="E01FESDID"> <input
			type="hidden" name="E01FESTYPO"> <input type="hidden"
			name="REFTYP"> <input type="text" name="E01FESREF" size="11"
			maxlength="11" onfocus="javascript:analice()" readonly> <a
			href="javascript:GetFeRef('E01FESDID','E01FESTYPO','REFTYP','E01FESREF')"><img
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="middle" border="0"></a></div>
		</td>

	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	bgcolor="#FFFFFF" bordercolor="#FFFFFF">
	<tr bgcolor="#FFFFFF">
		<td colspan="2" width="33%">&nbsp;</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td width="33%">
		<div align="center"><input id="EIBSBTN" type=submit
			name="Submit" value="Enviar"></div>
		</td>
		<td width="33%">
		<div align="center"><input id="EIBSBTN" type=button
			name="Refresh" value="Refrescar" onClick="RefreshList()"></div>
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td></td>
	</tr>
</table>

</center>
<center>
<%
String Type = (String) session.getAttribute("Type");
%>
<table class="tableinfo" width="28%">
	<tr id="trintro">
		<td width="25%"><input type="radio" name="SOURCE" value="T"
			<%if (Type.equals("T"))
				out.print("checked");%>>Tesorería</td>
		<td width="25%"><input type="radio" name="SOURCE" value="F"
			<%if (Type.equals("F"))
				out.print("checked");%>>Fideicomiso</td>
		<td width="25%"><input type="radio" name="SOURCE" value="E"
			<%if (Type.equals("E"))
				out.print("checked");%>>FEM</td>
		<td width="25%"><input type="radio" name="SOURCE" value="R"
			<%if (Type.equals("R"))
				out.print("checked");%>>Terceros</td>

	</tr>

</table>
<br>
<table id="mainTable" class="tableinfo" height="30" width="14%">
	<tr>
		<td nowrap valign="top" width="100%" height="60">
		<table id="headTable">
			<tbody>
				<tr id="trintrot">
					<th align="CENTER" nowrap></th>
					<th align="CENTER" nowrap>N&uacute;mero</th>
					<th align="CENTER" nowrap>Nombre</th>
					<th align="CENTER" nowrap>Id</th>
				</tr>
			</tbody>
		</table>
		<div id="dataDiv1" class="scbarcolor"
			style="overflow-Y:scroll;height:100">
		<table id="dataTable" style="font-size:7pt">
			<%
				custList.initRow();
				int k = 1;
				while (custList.getNextRow()) {
					if (custList.getFlag().equals("")) {
						out.println(custList.getRecord());
						k++;
					}
				}
			%>
		</table>
		</div>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</center>


<%
		if (!error.getERRNUM().equals("0")) {
		error.setERRNUM("0");
%> <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT> <%
 }
 %> <b> <input type=HIDDEN name="totalRow" value="1"> </b> <SCRIPT
	language="JavaScript">
     document.forms[0].totalRow.value="11";
     function resizeDoc() {
       //divResize2(true);
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
     }
     showChecked("ACCNUM");
     resizeDoc();
     window.onresize = resizeDoc;
function divResize2() {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 30;
  var h = 400 - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 20; 
  
  if (totalrow > 6) {
     dataDiv1.style.height= maxHeight + ""; 
     dataDiv1.style.overflowY="scroll";
  } else {
	dataDiv1.style.height= maxHeight + ""; 
   	dataDiv1.style.overflowY="";
  } 
}
     
</SCRIPT></form>
</body>
</html>
