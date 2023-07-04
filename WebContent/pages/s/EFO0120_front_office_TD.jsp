<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>  
<title>Front Office - Crear Time Deposits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012002Message" scope="session" />   
<jsp:useBean id="msgCustB" class="datapro.eibs.beans.EFO012001Message" scope="session" />   
<jsp:useBean id="accList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="prdList" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="ratList" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<SCRIPT Language="Javascript">
<!--- hide script from old browsers
//	Load Accounts for the Customer
	var currency = new Array(50);
	var account = new Array(50);
	var accdsc = new Array(50);
	var tccy = new Array(50);
	var i=0;
	var j=0;
	var k=0;
	var m=0;
	var duplicate = false;
	<% accList.initRow();
	while(accList.getNextRow()){ 
	 	datapro.eibs.beans.EFO012006Message accDetail = (datapro.eibs.beans.EFO012006Message)accList.getRecord(); %>     
	 	i++;
     	currency[i]= "<%= accDetail.getE06CCYCDE() %>";  
     	account[i]= "<%= accDetail.getE06ACCNUM() %>";
     	accdsc[i]= "<%= " Cuenta No. " + accDetail.getE06ACCNUM() + " " + accDetail.getE06CCYCDE() + " " + accDetail.getE06PRIAMN() %>";
		duplicate = false;
		if (m>0) {
			for(var k=1;k<=m;k++){
				if (tccy[k] == currency[i]) {
					duplicate = true;
				}
			}
		}
		if (!duplicate) {
			m++;
			tccy[m] = currency[i];
		}
	
    <%}%>
	j=i;
	
function changePrd(prd,ccy){
	document.getElementById("E02FESTCY").value = ccy;
	document.getElementById("E02FESPRO").value = prd;
	document.getElementById("E02FESROY").value = '0';
	document.getElementById("E02FESDEX").value = '0';
	document.forms[0].SCREEN.value = '2';
	document.forms[0].OPTION.value = '02';
	document.forms[0].submit();
}	

function changeDays(days){
	document.getElementById("E02FESROY").value = days;
	return;
}	

function changeRollover(code){
	document.getElementById("E02FESROC").value = code;
	if (document.forms[0].E02FESROC.value == "E") {
		document.forms[0].CREDITACC.disabled = true;
	}
	else {
		document.forms[0].CREDITACC.disabled = false;
	}
	return;
}	

function fillCombos(prd,ccy){
  var tobox = document.forms[0].CREDITACC;
  tobox.options.length = 0;
  for(var i=1;i<=j;i++){
	if(currency[i]==ccy){
		if (account[i] == document.getElementById("E02FESCAC").value) {
   	  		tobox.options[tobox.options.length] = new Option(accdsc[i],account[i],true,'selected');
		} else {
			tobox.options[tobox.options.length] = new Option(accdsc[i],account[i]);
		}
	}
  }  	   
  var tobox = document.forms[0].DEBITACC;
  tobox.options.length = 0;
  for(var i=1;i<=j;i++){
    if(currency[i]==ccy){
		if (account[i] == document.getElementById("E02FESDAC").value) {
   	  		tobox.options[tobox.options.length] = new Option(accdsc[i],account[i],true,'selected');
		} else {
			tobox.options[tobox.options.length] = new Option(accdsc[i],account[i]);
		}
	}
  }
  var tobox = document.forms[0].CURRENCY;
  tobox.options.length = 0;
  for(var i=1;i<=m;i++){
	if (tccy[i] == document.getElementById("E02FESTCY").value) {
   	  		tobox.options[tobox.options.length] = new Option(tccy[i],tccy[i],true,'selected');
	} else {
			tobox.options[tobox.options.length] = new Option(tccy[i],tccy[i]);
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
 	
	if (document.getElementById("E02FESTCY").value == "" && !(op == 3)) {
		alert("Favor seleccionar una moneda.");
		return;	 
	}
	if (document.getElementById("E02FESPRO").value == "" && !(op == 3)) {
		alert("Favor seleccionar un Producto.");
		return;	 
	}

	if (document.getElementById("E02FESAMN").value <= 0 && !(op == 3)) {
		alert("Favor ingresar Monto.");
		return;	 
	}
	if (document.getElementById("E02FESROY").value <= 0 && !(op == 3)) {
		alert("Favor seleccionar Días.");
		return;	 
	}
   	switch (op){
	// Validate & Generate CD	 
  	case 2:  {
    	document.forms[0].SCREEN.value = '4';
		document.forms[0].OPTION.value = '02';
		document.forms[0].submit();
       	break;
        }
	// Return to Previus Screen
	case 3:  {
    	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=1&CUSTOMER=" + cun;
		window.location.href=pg;
        break;
        }
	// Validate & get Rate
  	default:  {
		document.forms[0].SCREEN.value = '3';
		document.forms[0].OPTION.value = '02';
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
<h3>Front Office - Certificado de Depósito Nuevo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="front_office_TD.jsp,EFO0120"></h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="3">
<INPUT type="hidden" name="OPTION" value="02">
<INPUT type="hidden" name="CUSTOMER" value="<%= msgCust.getE02FESCUN().trim()%>">
<INPUT type="hidden" name="E02FESDAC" value="<%= msgCust.getE02FESDAC().trim()%>">
<INPUT type="hidden" name="E02FESCAC" value="<%= msgCust.getE02FESCAC().trim()%>">
<INPUT type="hidden" name="E02FESREF" value="<%= msgCust.getE02FESREF().trim()%>">
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
<INPUT type="hidden" name="E02FESTCY" value="<%= msgCust.getE02FESTCY().trim()%>">
<INPUT type="hidden" name="E02FESDAM" value="<%= msgCust.getE02FESDAM().trim()%>">
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
					<TD nowrap align="right" width="25%"><B>Tipo - Estado : </B></TD>
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

		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
			<TBODY>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Moneda :</TD>
					<TD height="10" align="left" width="25%">
						<SELECT class="inputs" name="CURRENCY" size="1" onchange="changePrd('<%= msgCust.getE02FESPRO().trim()%>',this.value)"></SELECT>
						<INPUT type="text" readonly name="E02FESTCD" size="30" value="<%= msgCust.getE02FESTCD().trim()%>">
					</TD>	
					<TD height="10" align="right" width="25%">Fecha de Apertura :</TD>
					<TD height="10" width="25%">
						<%= datapro.eibs.master.Util.formatDate(msgCust.getE02FESVDM(),msgCust.getE02FESVDD(),msgCust.getE02FESVDY())%> 
						<%= currUser.getE01DTF()%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Monto :</TD>
					<TD height="10" align="left" width="25%">
						<INPUT type="text" name="E02FESAMN" size="15" maxlength="10" value="<%= msgCust.getE02FESAMN().trim()%>" onkeypress="enterDecimal()">
					</TD>
					<TD height="10" align="right" width="25%">Tasa :
					</TD>
					<TD height="10" width="25%" align="left">
						<INPUT style="font: bold" type="text" name="E02FESDEX" size="15" value="<%= msgCust.getE02FESDEX().trim()%>">
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Instrucciones de Renovación - Pago :</TD>
					<TD height="10" align="left" width="25%"><SELECT class="inputs"
						name="ROLLOVER" onchange="changeRollover(this.value)">
						<OPTION value="E"
							<% if (msgCust.getE02FESROC().equals("E") || msgCust.getE02FESROC().equals(" ")) out.print("selected"); %>>E- No
						Renovar</OPTION>
						<OPTION value="H"
							<% if (msgCust.getE02FESROC().equals("H")) out.print("selected"); %>>H - Renovar
						Capital. Pagar Interés Mensualmente</OPTION>
						<OPTION value="B"
							<% if (msgCust.getE02FESROC().equals("B")) out.print("selected"); %>>B - Renovar
						Capital. pagar Interés al Vencimeinto</OPTION>
						<OPTION value="A"
							<% if (msgCust.getE02FESROC().equals("A")) out.print("selected"); %>>A - Renovar
						Capital y Acumular Interés</OPTION>
					</SELECT>
					</TD>
					<TD height="10" align="right" width="25%"> Días : </TD>
					<TD height="10" width="25%">
						<INPUT style="font: bold" type="text" name="E02FESROY" size="15" maxlength="10" value="<%= msgCust.getE02FESROY().trim()%>">
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right"></TD>
					<TD height="10" align="center" width="25%">
					</TD>
					<TD width="25%" align="right">Clave de Tesorería :</TD>
					<TD width="25%">
					<INPUT type="password" name="E02DTP1" size="11" maxlength="10" value="<%= msgCust.getE02DTP1().trim()%>"><br>
					* Solo si ingresa tasa manualmente</TD>
				</TR>

				<TR>
					<TD height="10" width="25%" align="right">Cuenta Debito :</TD>
					<TD height="10" align="left" width="25%" colspan="3"> 
						<SELECT class="inputs" name="DEBITACC" size="1"></SELECT>
					(Cuenta usada para crear el certificado)</TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right"> Cuenta Crédito :</TD>
					<TD height="10" align="left" width="25%" colspan="3" >
						<SELECT class="inputs" name="CREDITACC" size="1" ></SELECT>
					(Cuenta usada para pagos del certificado)</TD>
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
			<% if (msgCust.getE02FESREF().equals("999999999999")) {%>
  		  		<div align="center">
			 		<input id="EIBSBTN" type="button" name="Submit" value="Aceptar" onClick="javascript:goAction(2);">
     	  	 	</div>	
				<% msgCust.setE02FESREF("0"); 
			} %>
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Return" value="Retornar" onClick="javascript:goAction(3);">
     	  </div>	
  		</td>
  	</tr>	
</table>	

<h4> Productos</h4>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
    	<TABLE id="headTable" >
     		<TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP></TH>
      			<TH ALIGN=CENTER NOWRAP>Código</TH>
     			<TH ALIGN=CENTER NOWRAP>Moneda</TH>
     			<TH ALIGN=CENTER NOWRAP>Descripción</TH>
  	 		</TR>
		</TABLE> 
        <div id="dataDiv1" class="scbarcolor"> 
           	<table id="dataTable" >
             	<%
					prdList.initRow();
               		int k=1;
               		while (prdList.getNextRow()) {
                   		if (prdList.getFlag().equals("")) {
                   			out.println(prdList.getRecord());
                   		k++;
                   		}        
               		}
           		%>
           	</table>
          </div>
       </td>
  	</tr>
</table>

<h4> Tasas</h4>
<TABLE  id="mainTable1" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
    	<TABLE id="headTable1" >
     		<%
                ratList.initRow();
				if (ratList.getNextRow()) {
					if (ratList.getFlag().equals("")) {
						out.println(ratList.getRecord());
					}
				}
			%>
		</TABLE> 
        <div id="dataDiv2" class="scbarcolor"> 
           	<table id="dataTable1" >
             	<%
               		k=1;
               		while (ratList.getNextRow()) {
                   		if (ratList.getFlag().equals("")) {
                   			out.println(ratList.getRecord());
                   		k++;
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
			adjustEquTables(headTable1, dataTable1, dataDiv2,1,false);
    	}	
    	resizeDoc();
    	window.onresize=resizeDoc;
		fillCombos('<%= msgCust.getE02FESPRO().trim()%>','<%= msgCust.getE02FESTCY().trim()%>');	
	</SCRIPT>
</B>
</form>
</body>
</html>
