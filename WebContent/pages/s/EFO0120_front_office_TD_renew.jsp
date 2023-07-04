<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head> 
<title>Front Office - Renovación de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012002Message" scope="session" />   
<jsp:useBean id="accList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<SCRIPT Language="Javascript">
<!--- hide script from old browsers
//	Load Accounts for the Customer
	var currency = new Array(50);
	var account = new Array(50);
	var accdsc = new Array(50);
	var accacd = "00";
	var i=0;
	var j=0;
	<% accList.initRow();
	while(accList.getNextRow()){ 
	 	datapro.eibs.beans.EFO012006Message accDetail = (datapro.eibs.beans.EFO012006Message)accList.getRecord(); %>     
		accacd = "<%= accDetail.getE06SELACD().trim() %>";
		if (accacd == "01" || accacd == "02" || accacd == "03" || accacd == "04" || accacd == "05") {
	 		i++;
     		currency[i]= "<%= accDetail.getE06CCYCDE() %>";  
     		account[i]= "<%= accDetail.getE06ACCNUM() %>";
     		accdsc[i]= "<%= " Cuenta No." + accDetail.getE06ACCNUM() + " " + accDetail.getE06CCYCDE() + " " + accDetail.getE06PRIAMN() %>";
		}
    <%} %>
	j=i;
	
function changeAcc(acc,ccy,roc,rac){
	document.getElementById("E02FESREF").value = acc;
	document.getElementById("E02FESROC").value = roc;
	document.getElementById("ROLLOVER").value = roc;
	fillCombos(ccy,rac);
	return
}	

function changeRollover(code){
	document.getElementById("E02FESROC").value = code; 
	return;
}	

function fillCombos(ccy,rac){
  var tobox = document.forms[0].CREDITACC;
  tobox.options.length = 0;
  for(var i=1;i<j;i++){
	if(currency[i]==ccy){
		if (account[i] == rac) {
   	  		tobox.options[tobox.options.length] = new Option(accdsc[i],account[i],true,'selected');
   	  		document.forms[0].CREDITACC.value==account[i];
   	  	} else {
			tobox.options[tobox.options.length] = new Option(accdsc[i],account[i]);
		}
	}
  }  	   
}

 //  Process according with user selection
 function goAction(op) {
	
	var cun = "0";
	var pg = "";
    cun = document.getElementById("E02FESCUN").value;
	var formLength= document.forms[0].elements.length;
    var ok = false;
 	
	if (document.getElementById("E02FESREF").value == 0 && !(op == 3)) {
		alert("Favor Seleccionar una Cuenta.");
		return;	 
	}

   	switch (op){
	// Validate & Generate Renewal Maintenance	 
  	case 2:  {
    	document.forms[0].SCREEN.value = '4';
		document.forms[0].OPTION.value = '03';
		document.forms[0].submit();
       	break;
        }
	// Return to Previus Screen
	case 3:  {
    	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=1&CUSTOMER=" + cun;
		window.location.href=pg;
        break;
        }
	// Validate 
  	default:  {
		document.forms[0].SCREEN.value = '3';
		document.forms[0].OPTION.value = '03';
		document.forms[0].submit();
       	break;
		}

    }  
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
<h3>From Office - Renovación de Certificados de Déposito</h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="3">
<INPUT type="hidden" name="OPTION" value="02">
<INPUT type="hidden" name="CUSTOMER" value="<%= msgCust.getE02FESCUN().trim()%>">
<INPUT type="hidden" name="E02FESDAC" value="<%= msgCust.getE02FESDAC().trim()%>">
<INPUT type="hidden" name="E02FESCAC" value="<%= msgCust.getE02FESCAC().trim()%>">
<INPUT type="hidden" name="E02FESDCY" value="<%= msgCust.getE02FESDCY().trim()%>">
<INPUT type="hidden" name="E02FESCCY" value="<%= msgCust.getE02FESCCY().trim()%>">
<INPUT type="hidden" name="E02FESDXR" value="<%= msgCust.getE02FESDXR().trim()%>">
<INPUT type="hidden" name="E02FESCXR" value="<%= msgCust.getE02FESCXR().trim()%>">
<INPUT type="hidden" name="E02FESEAM" value="<%= msgCust.getE02FESEAM().trim()%>">
<INPUT type="hidden" name="E02FESDAD" value="<%= msgCust.getE02FESDAD().trim()%>">
<INPUT type="hidden" name="E02FESCAD" value="<%= msgCust.getE02FESCAD().trim()%>">
<INPUT type="hidden" name="E02FESDAP" value="<%= msgCust.getE02FESDAP().trim()%>">
<INPUT type="hidden" name="E02FESCAP" value="<%= msgCust.getE02FESCAP().trim()%>">
<INPUT type="hidden" name="E02FESPRO" value="<%= msgCust.getE02FESPRO().trim()%>">
<INPUT type="hidden" name="E02FESSBT" value="<%= msgCust.getE02FESSBT().trim()%>">
<INPUT type="hidden" name="E02FESROC" value="<%= msgCust.getE02FESROC().trim()%>">
<INPUT type="hidden" name="E02FESROY" value="<%= msgCust.getE02FESROY().trim()%>">
<INPUT type="hidden" name="E02FESTCY" value="<%= msgCust.getE02FESTCY().trim()%>">
<INPUT type="hidden" name="E02FESDAM" value="<%= msgCust.getE02FESDAM().trim()%>">
<INPUT type="hidden" name="E02FESDEX" value="<%= msgCust.getE02FESDEX().trim()%>">
<INPUT type="hidden" name="E02FESAMN" value="<%= msgCust.getE02FESAMN().trim()%>">
<INPUT type="hidden" name="E02FESEAM" value="<%= msgCust.getE02FESEAM().trim()%>">
<INPUT type="hidden" name="E02FESDAP" value="<%= msgCust.getE02FESDAP().trim()%>">
<INPUT type="hidden" name="E02FESCAP" value="<%= msgCust.getE02FESCAP().trim()%>">

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

<table class="tableinfo">
	<tr>
		<td nowrap width="100%">
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"><B>Número de Cliente :</B></TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E02FESCUN" size="15" maxlength="10" value="<%= msgCust.getE02FESCUN().trim()%>">
					</TD>
					<TD nowrap align="right" width="25%"><B> Tipo - Status : </B></TD>
					<TD nowrap width="25%">
						<%if (msgCust.getE02FESCCL().equals("1")) out.print("JURIDICO - ");
									else if (msgCust.getE02FESCCL().equals("2")) out.print("PERSONAL - ");
									else out.print("OTRO - ");%>
						<%if (msgCust.getE02FESSTS().equals("3")) out.print("Lista Negra");
									else if (msgCust.getE02FESSTS().equals("1")) out.print("Activo");
									else if (msgCust.getE02FESSTS().equals("2")) out.print("Inactivo");
									else out.print("");%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"><B>Nombre del Cliente :</B><B></B></TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E02FESNA1" size="35" value="<%= msgCust.getE02FESNA1().trim()%>"></TD>
					<TD nowrap align="right" width="25%"></TD>
					<TD nowrap width="25%"></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
				</TR>
			</TBODY>
		</TABLE>
		<br>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Cuenta :</TD>
					<TD height="10" align="left" width="25%">
						<INPUT type="text" name="E02FESREF" size="13" value="<%= msgCust.getE02FESREF().trim()%>" readonly>
					</TD>	
					<TD height="10" align="right" width="25%">Fecha de Transacción : </TD>
					<TD height="10" width="25%">
						<%= datapro.eibs.master.Util.formatDate(msgCust.getE02FESVDM(),msgCust.getE02FESVDD(),msgCust.getE02FESVDY())%> 
						<%= currUser.getE01DTF()%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Instrucciones de Renovación :</TD><TD height="10" align="left" width="25%">
					<SELECT class="inputs" name="ROLLOVER" onchange="changeRollover(this.value)">
						<OPTION value="E" <% if (msgCust.getE02FESROC().equals("E") || msgCust.getE02FESROC().equals(" ")) out.print("selected"); %>>E - No
						Renovar</OPTION>
						<OPTION value="H" <% if (msgCust.getE02FESROC().equals("H")) out.print("selected"); %>>H - Renovar
						Capital. Pagar Interés Mensualmente</OPTION>
						<OPTION value="B" <% if (msgCust.getE02FESROC().equals("B")) out.print("selected"); %>>B - Renovar	
						Capital. pagar Interés al Vencimeinto</OPTION>
						<OPTION value="A" <% if (msgCust.getE02FESROC().equals("A")) out.print("selected"); %>>A - Renovar
						Capital y Acumular Interés</OPTION>
					</SELECT></TD>
					
					<TD height="10" align="right" width="25%">
					</TD>
					<TD height="10" width="25%" align="left">
					</TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right"> Cuenta a recibir Interés y/o Capital :</TD>
					<TD height="10" align="left" width="25%" colspan="3">
						<SELECT class="inputs" name="CREDITACC" size="1"></SELECT>
					</TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right">Instrucciones Especiales :</TD>
					<TD height="10" align="left" width="25%" colspan="3">
						<INPUT type="text" name="E02FESREM" size="71" value="<%= msgCust.getE02FESREM().trim()%>" maxlength="70">
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
</table>

<table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Getrate" value="Validar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<td width="34%">
		  <div align="center">
			<input id="EIBSBTN" type="button" name="Submit" value="Aceptar" onClick="javascript:goAction(2);">
		  </div>
     	</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Return" value="Retornar" onClick="javascript:goAction(3);">
     	  </div>	
  		</td>
  	</tr>	
</table>	

<h4> Cuentas</h4>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
    	<TABLE id="headTable" >
     		<TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP></TH>
				<TH ALIGN=CENTER NOWRAP>Número de<BR>Cuenta</TH>
      			<TH ALIGN=CENTER NOWRAP>Producto</TH>
     			<TH ALIGN=CENTER NOWRAP>Moneda</TH>
     			<TH ALIGN=CENTER NOWRAP>Descripcción</TH>
     			<TH ALIGN=CENTER NOWRAP>Fecha de<BR>Apertura</TH>
     			<TH ALIGN=CENTER NOWRAP>Fecha de<BR>Vencimiento</TH>
     			<TH ALIGN=CENTER NOWRAP>Tasa<BR>%</TH>
     			<TH ALIGN=CENTER NOWRAP>Saldo de<BR>Capital</TH>
     			<TH ALIGN=CENTER NOWRAP>Interés<BR>al Vencimiento</TH>
  	 		</TR>
		</TABLE> 
        <div id="dataDiv1" class="scbarcolor"> 
           	<table id="dataTable" >
          	<%
								
			boolean firstTime = true;
			String dark = "Y";
			String acc = "";
			int k=1;
			accList.initRow();
			while(accList.getNextRow()){ 
	 			datapro.eibs.beans.EFO012006Message accDetail = (datapro.eibs.beans.EFO012006Message)accList.getRecord();      
				if (accDetail.getE06SELACD().equals("11")) {
					k++;
					if (dark == "Y") {
						out.println("<TR id=\"trdark\">");
						dark = "N";
					} else {
						out.println("<TR id=\"trclear\">");
						dark = "Y";
					}

		 			acc = "onClick=\"changeAcc(\'" + accDetail.getE06ACCNUM().trim() + "\',\'" + accDetail.getE06CCYCDE() + "\',\'" 
						+ accDetail.getE06DEAROC() + "\',\'" + accDetail.getE06DEARAC() + "\')\" ";
					if (msgCust.getE02FESREF() == accDetail.getE06ACCNUM()) {
						acc = acc + " selected";
					}
					out.println("<TD NOWRAP align=center><input type=\"radio\" name=\"ACCOUNT\" value=\"" + accDetail.getE06ACCNUM() + "\" " + acc + "></TD>");
					out.println("<TD NOWRAP align=center><A HREF=\"javascript:showInqAcc('" + accDetail.getE06ACCNUM() + "')\">" + Util.formatCell(accDetail.getE06ACCNUM()) + "</TD>");
					out.println("<TD NOWRAP align=center>" + Util.formatCell(accDetail.getE06PROCDE()) + "</TD>");
					out.println("<TD NOWRAP align=center>" + Util.formatCell(accDetail.getE06CCYCDE()) + "</TD>"); 
					out.println("<TD NOWRAP>" + Util.formatCell(accDetail.getE06PRODSC()) + "</TD>");
					out.println("<TD NOWRAP align=center>" +Util.formatDate(accDetail.getE06OPEDT1(),accDetail.getE06OPEDT2(),accDetail.getE06OPEDT3())+ "</TD>");
					out.println("<TD NOWRAP align=center>" +Util.formatDate(accDetail.getE06MATDT1(),accDetail.getE06MATDT2(),accDetail.getE06MATDT3())+ "</TD>");
					out.println("<TD NOWRAP align=right>" + Util.fcolorCCY(accDetail.getE06INTRTE()) + "</TD>");
					out.println("<TD NOWRAP align=right>" + Util.fcolorCCY(accDetail.getE06PRIAMN()) + "</TD>");					
					out.println("<TD NOWRAP align=right>" + Util.fcolorCCY(accDetail.getE06INTAMN()) + "</TD>");
					out.println("</TR>");
				}
			}

           	%>
           	</table>
          </div>
       </td>
  	</tr>
</table>


<B> 
	<SCRIPT language="JavaScript">
    	function resizeDoc() {
    		adjustEquTables(headTable, dataTable, dataDiv1,1,false);
		}	
    	resizeDoc();
    	window.onresize=resizeDoc;
		fillCombos('<%= msgCust.getE02FESTCY()%>','E');	 
	</SCRIPT>
</B>
</form>
</body>
</html>
