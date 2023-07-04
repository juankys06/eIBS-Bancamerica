<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head> 
<title>Front Office - Foreign Exchange</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" /> 
<jsp:useBean id="msgCust" class="datapro.eibs.beans.EFO012002Message" scope="session" />   
<jsp:useBean id="accList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="ccyList" class="datapro.eibs.beans.JBList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </script>
<SCRIPT Language="Javascript">
<!--- hide script from old browsers
var timeoutmessage = "Foreign Exchange Transaction Timed-Out ";
var timeoutpage = "EFO0120_front_office_FX.jsp";
//	Load Accounts for the Customer
	var ccyD = " ";
	var ccyC = " ";
	var currency = new Array(50);
	var account = new Array(50);
	var accdsc = new Array(50);
	var i=0;
	var j=0;
	<% accList.initRow();
	while(accList.getNextRow()){ 
	 	datapro.eibs.beans.EFO012006Message accDetail = (datapro.eibs.beans.EFO012006Message)accList.getRecord(); %>     
	 	i++;
     	currency[i]= "<%= accDetail.getE06CCYCDE() %>";  
     	account[i]= "<%= accDetail.getE06ACCNUM() %>";
     	accdsc[i]= "<%= " Account No." + accDetail.getE06ACCNUM() + " " + accDetail.getE06CCYCDE() + " " + accDetail.getE06PRIAMN() %>";
    <%   }   %>
	j=i;
	
function purchase(){
  document.getElementById("E02FESSBT").value = 'P'
  ccyC = document.getElementById("E02FESTCY").value;
  ccyD = "ALL";
  fillCombos();	
}	

function sell(){
  document.getElementById("E02FESSBT").value = 'S'
  ccyD = document.getElementById("E02FESTCY").value;
  ccyC = "ALL";
  fillCombos();	
}	

function fillCombos(){
  var tobox = document.forms[0].CREDITACC;
  tobox.options.length = 0;
  for(var i=1;i<j;i++){
    if(currency[i]==ccyC || (ccyC=="ALL" && currency[i]!=ccyD)){
		if (account[i] == document.getElementById("E02FESCAC").value) {
   	  		tobox.options[tobox.options.length] = new Option(accdsc[i],account[i],true,'selected');
		} else {
			tobox.options[tobox.options.length] = new Option(accdsc[i],account[i]);
		}
	}
  }  	   
  var tobox = document.forms[0].DEBITACC;
  tobox.options.length = 0;
  for(var i=1;i<j;i++){
    if(currency[i]==ccyD || (ccyD=="ALL" && currency[i]!=ccyC)){
		if (account[i] == document.getElementById("E02FESDAC").value) {
   	  		tobox.options[tobox.options.length] = new Option(accdsc[i],account[i],true,'selected');
		} else {
			tobox.options[tobox.options.length] = new Option(accdsc[i],account[i]);
		}
	}
  }
}

function changeCcy(ccy,dsc) {
	if (ccy != '999') {
		document.getElementById("E02FESTCY").value=ccy;
		document.getElementById("E02FESTCD").value=dsc;
	} else {
		document.getElementById("E02FESSBT").value ="<%= msgCust.getE02FESSBT().trim() %>";	
	}
	if (document.getElementById("E02FESSBT").value == 'P') {
		purchase();
	}
	if (document.getElementById("E02FESSBT").value == 'S') {
		sell();
	}
}
 //  Process according with user selection
 function goAction(op) {
	
	var cun = "0";
	var pg = "";
    cun = document.getElementById("E02FESCUN").value;
	var formLength= document.forms[0].elements.length;
    var ok = false;
 	
	for(n=0;n<formLength;n++) {
     	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "E02FESSBT") {
			if (document.forms[0].elements[n].checked == true) {
				ok = true;
        		break;
			}
      	}
    }
	if ( !ok && !(op == 3)) {
		alert("Please select Purchase or Sell.");
		return;	 
	}
	if (document.getElementById("E02FESTCY").value == "" && !(op == 3)) {
		alert("Please select Currency.");
		return;	 
	}
	if (document.getElementById("E02FESAMN").value <= 0 && !(op == 3)) {
		alert("Please enter Amount.");
		return;	 
	}
   	switch (op){
	// Validate & Generate FX Transaction
  	case 2:  {
    	document.forms[0].SCREEN.value = '4';
		document.forms[0].submit();
       	break;
        }
	// Return to Previus Screen
	case 3:  {
    	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120?SCREEN=1&CUSTOMER=" + cun;
		window.location.href=pg;
        break;
        }
	// Validate & get FX Rate
  	default:  {
		document.forms[0].SCREEN.value = '3';
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
<h3>From Office - Foreign Exchange<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" alt="front_office_FX.jsp,EFO0120"></h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFO0120">
<INPUT type="hidden" name="SCREEN" value="3">
<INPUT type="hidden" name="OPTION" value="01">
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
<INPUT type="hidden" name="E02FESROC" value="<%= msgCust.getE02FESROC().trim()%>">
<INPUT type="hidden" name="E02FESROY" value="<%= msgCust.getE02FESROY().trim()%>">
<INPUT type="hidden" name="E02FESPRO" value="<%= msgCust.getE02FESPRO().trim()%>">

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
					<TD nowrap align="right" width="25%"><B>Customer Number :</B></TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E02FESCUN" size="15" maxlength="10" value="<%= msgCust.getE02FESCUN().trim()%>">
					</TD>
					<TD nowrap align="right" width="25%"><B>Relationship Type - Status :</B></TD>
					<TD nowrap width="25%">
						<INPUT type="text" readonly name="E02FESCCL" size="15"
						value='<%if (msgCust.getE02FESCCL().equals("I")) out.print("International");
									else if (msgCust.getE02FESCCL().equals("P")) out.print("Premier"); 
									else if (msgCust.getE02FESCCL().equals("E")) out.print("Elite"); 
									else out.print("");%>'>
					- <%if (msgCust.getE02FESSTS().equals("3")) out.print("Prospect");
									else if (msgCust.getE02FESSTS().equals("1")) out.print("Active");
									else if (msgCust.getE02FESSTS().equals("2")) out.print("Inactive");
									else out.print("");%></TD>
				</TR>
				<TR id="trintro">
					<TD nowrap align="right" width="25%"><B>Customer Name </B>:</TD>
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
					<TD height="10" width="25%" align="right">Client Wants to :</TD>
					<TD height="10" align="left" width="25%">
						<INPUT type="radio" name="E02FESSBT" value="P" onclick="JavaScript:purchase()"
							<% if (msgCust.getE02FESSBT().equals("P")) out.print(" checked"); %>
						>Purchase 
						<INPUT type="radio" name="E02FESSBT" value="S" onclick="JavaScript:sell()"
							<% if (msgCust.getE02FESSBT().equals("S")) out.print(" checked"); %>
						>Sell
					
					<TD height="10" align="right" width="25%">Transaction Value Date :
					<TD height="10" width="25%">
						<%= datapro.eibs.master.Util.formatDate(msgCust.getE02FESVDM(),msgCust.getE02FESVDD(),msgCust.getE02FESVDY())%> 
						<%= currUser.getE01DTF()%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Foreign Currency :</TD>
					<TD height="10" align="left" width="25%">
						<INPUT type="text" name="E02FESTCY" size="4" maxlength="3" value="<%= msgCust.getE02FESTCY().trim()%>" readonly>
						<INPUT type="text" readonly name="E02FESTCD" size="30" value="<%= msgCust.getE02FESTCD().trim()%>"></TD>
					<TD height="10" align="right" width="25%">F/X Rate :
					</TD>
					<TD height="10" width="25%" align="left">
						<INPUT style="font: bold" type="text" readonly name="E02FESDEX" size="15" maxlength="10" value="<%= msgCust.getE02FESDEX().trim()%>">
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right">Amount :</TD>
					<TD height="10" align="left" width="25%">
						<INPUT type="text" name="E02FESAMN" size="15" maxlength="10" value="<%= msgCust.getE02FESAMN().trim()%>" onKeyPress="enterDecimal()">
					</Td>
					<TD height="10" align="right" width="25%">Equivalent Amount : </TD>
					<TD height="10" width="25%">
						<INPUT style="font: bold" type="text" readonly name="E02FESDAM" size="15" maxlength="10" value="<%= Util.fcolorCCY(msgCust.getE02FESDAM().trim())%>">
						<%  if (msgCust.getE02FESSBT().equals("P")) out.print(msgCust.getE02FESDCY()); 
					   		if (msgCust.getE02FESSBT().equals("S")) out.print(msgCust.getE02FESCCY()); 
						%>
					</TD>
				</TR>
				<TR id="trintro">
					<TD height="10" width="25%" align="right"></TD>
					<TD height="10" align="center" width="25%">
					</TD>
					<TD height="10" align="center" width="25%">
					</TD>
					<TD height="10" width="25%"></TD>
				</TR>

				<TR>
					<TD height="10" width="25%" align="right">Debit Account :</TD>
					<TD height="10" align="left" width="25%" colspan="3">
						<SELECT class="inputs" name="DEBITACC" size="1"></SELECT>
					</TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right">Credit Account :</TD>
					<TD height="10" align="left" width="25%" colspan="3">
						<SELECT class="inputs" name="CREDITACC" size="1"></SELECT>
					</TD>
				</TR>
				<TR>
					<TD height="10" width="25%" align="right">Remarks :</TD>
					<TD height="10" align="left" width="25%" colspan="3"><INPUT
						type="text" name="E02FESREM" size="71"
						value="<%= msgCust.getE02FESREM().trim()%>" maxlength="70"></TD>
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
     		<input id="EIBSBTN" type="button" name="Getrate" value="Get Rate" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<td width="34%">
			<% if (msgCust.getE02FESREF().equals("999999999")) {%>
  		  		<div align="center">
			 	<script language="javascript" src="<%= request.getContextPath() %>/pages/e/javascripts/percent_bar.js"></script>
				<br>
     			<input id="EIBSBTN" type="button" name="Submit" value="Accept" onClick="javascript:goAction(2);">
     	  	 	</div>	
				<% msgCust.setE02FESREF("0"); 
			} %>
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Return" value="Return" onClick="javascript:goAction(3);">
     	  </div>	
  		</td>
  	</tr>	
</table>	

<h4> Foreign Exchange Rates</h4>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER >
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
    	<TABLE id="headTable" >
     		<TR id="trdark"> 
				<TH ALIGN=CENTER NOWRAP></TH>
      			<TH ALIGN=CENTER NOWRAP>Currency</TH>
     			<TH ALIGN=CENTER NOWRAP>Description</TH>
     			<TH ALIGN=CENTER NOWRAP>Purchase<br>Rate</TH>
     			<TH ALIGN=CENTER NOWRAP>Purchase<br>Maximum<br>Amount</TH>
				<TH ALIGN=CENTER NOWRAP>Purchase<br>Status</TH>
     			<TH ALIGN=CENTER NOWRAP>Sell<br>Rate</TH>
     			<TH ALIGN=CENTER NOWRAP>Sell<br>Maximum<br>Amount</TH>
     			<TH ALIGN=CENTER NOWRAP>Sell<br>Status</TH>
  	 		</TR>
		</TABLE> 
        <div id="dataDiv1" class="scbarcolor"> 
           	<table id="dataTable" >
             	<%
               		ccyList.initRow();
               		int k=1;
               		while (ccyList.getNextRow()) {
                   		if (ccyList.getFlag().equals("")) {
                   			out.println(ccyList.getRecord());
                   		k++;
                   		}        
               		}
           		%>
           	</table>
          </div>
       </td>
  	</tr>
  </table>

<SCRIPT language="JavaScript">
     function resizeDoc() {
      adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
	 changeCcy('999',' ');
</SCRIPT>

</form>
</body>
</html>
