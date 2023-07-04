<html>
<head>
<title>Mantenimiento Plan de Estado Financiero Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>
<jsp:useBean id= "optList1" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNP5" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP070301Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<body onload=javascript:initOver()>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>
<script language="JavaScript">
function goConfirm() {

		moveto400();
	
      ok = confirm("Esta seguro que desea crear/modificar este registro?");
      
	  if (ok) 
	       {
	       document.forms[0].SCREEN.value="600";
	       document.forms[0].submit();
	        
	       }  
		  
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="200";
document.forms[0].E01DPXCFO.value=fmt;
// document.forms[0].E01DPXCLI.value=cli;
// document.forms[0].E01DPXGLN.value=gln;
document.forms[0].submit(); 
	  		  
}

//carga valores iniciales de formulas indices
function initOver() {
i=0 ;
selectedValue = "<%= brnDetails.getE01DPXFO1().trim()%>";
if (selectedValue != "" ) {
newoption = new Option("<%= brnDetails.getE01DPXDF1().trim()%>", selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}
selectedValue = "<%= brnDetails.getE01DPXFO2().trim()%>";
if (selectedValue != "" ) {
newoption = new Option("<%= brnDetails.getE01DPXDF2().trim()%>", selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}
selectedValue = "<%= brnDetails.getE01DPXFO3().trim()%>";
if (selectedValue != "" ) {
newoption = new Option("<%= brnDetails.getE01DPXDF3().trim()%>", selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}
selectedValue = "<%= brnDetails.getE01DPXFO4().trim()%>";
if (selectedValue != "" ) {
newoption = new Option("<%= brnDetails.getE01DPXDF4().trim()%>", selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
i++ ;
}
selectedValue = "<%= brnDetails.getE01DPXFO5().trim()%>";
if (selectedValue != "" ) {
newoption = new Option("<%= brnDetails.getE01DPXDF5().trim()%>", selectedValue, false, false);
document.forms[0].choiceBox.options[i] = newoption;
}
}


function moveOver()  
{
var boxLength = document.forms[0].choiceBox.length;
var selectedItem = document.forms[0].available.selectedIndex;
var selectedText = document.forms[0].available.options[selectedItem].text;
var selectedValue = document.forms[0].available.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBox.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 
if (boxLength<5) {

if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;
}
document.forms[0].available.selectedIndex=-1;

} else {
	if (document.forms[0].LAN.value == 'S') {
		alert("Seleccione maximo cinco (5) códigos de fórmulas");
	} else {
		alert("Only five (5) formula codes");
	}
}
}

function removeMe() {
var boxLength = document.forms[0].choiceBox.length;
arrSelected = new Array();
var count = 0;
for (i = 0; i < boxLength; i++) {
if (document.forms[0].choiceBox.options[i].selected) {
arrSelected[count] = document.forms[0].choiceBox.options[i].value;
}
count++;
}
var x;
for (i = 0; i < boxLength; i++) {
for (x = 0; x < arrSelected.length; x++) {
if (document.forms[0].choiceBox.options[i].value == arrSelected[x]) {
document.forms[0].choiceBox.options[i] = null;
   }
}
boxLength = document.forms[0].choiceBox.length;
   }
}



function saveMe() {
var strValues = "";
var boxLength = (document.forms[0].choiceBoxMas.length + document.forms[0].choiceBoxMas.length);
var boxLengthMas = document.forms[0].choiceBoxMas.length;
var count = 0;

if (boxLengthMas != 0) {
for (i = 0; i < boxLengthMas; i++) {
if (count == 0) {
strValues = "+" +document.forms[0].choiceBoxMas.options[i].value;
}
else {
strValues = strValues + "+" + document.forms[0].choiceBoxMas.options[i].value;
}
count++;
   }
}

var boxLengthMenos = document.forms[0].choiceBoxMenos.length;
var count = 0;

if (boxLengthMenos != 0) {
for (i = 0; i < boxLengthMenos; i++) {
if (count == 0) {
strValues = strValues + "-" + document.forms[0].choiceBoxMenos.options[i].value;
}
else {
strValues = strValues + "-" + document.forms[0].choiceBoxMenos.options[i].value;
}
count++;
   }
}

if (strValues.length == 0) {
document.forms[0].E01DPXEXP.value="";

}
else {
document.forms[0].E01DPXEXP.value=strValues;
   }

}



function moveto400() {
var boxLength = document.forms[0].choiceBox.length;

if (0 < boxLength) {
if (document.forms[0].choiceBox.options[0].value !="") {
document.forms[0].E01DPXFO1.value=document.forms[0].choiceBox.options[0].value;
}
}
if (1 < boxLength) {
if (document.forms[0].choiceBox.options[1].value !="") {
document.forms[0].E01DPXFO2.value=document.forms[0].choiceBox.options[1].value;
}
}
if (2 < boxLength) {
if (document.forms[0].choiceBox.options[2].value !="") {
document.forms[0].E01DPXFO3.value=document.forms[0].choiceBox.options[2].value;
}
}
if (3 < boxLength) {
if (document.forms[0].choiceBox.options[3].value !="") {
document.forms[0].E01DPXFO4.value=document.forms[0].choiceBox.options[3].value;
}
}
if (4 < boxLength) {
if (document.forms[0].choiceBox.options[4].value !="") {
document.forms[0].E01DPXFO5.value=document.forms[0].choiceBox.options[4].value;
}
}
}


function goList() {

	page= prefix +language + "EDP0703_financial_maintenance_sel.jsp"
	CenterWindow(page,600,500,4);
	  		  
}

 
// funciones expresion de calculo

 function goAction(op) {
   //document.forms[0].opt.value = op;
   switch (op) {
    case 1 :{
			var si = document.forms[0].E01DPXLIN.selectedIndex
			if (si == 3) {
			ShowAMT();
			stringInit();
			} else {
				document.forms[0].E01DPXEXP.value="";
			}
            break;
            }
  }
 }



 function cancelBub(){
  event.cancelBubble = true;
 }


 function ShowAMT() {	 
	 var y= opTable.offsetTop + 10;
     var x= opTable.offsetLeft + (AMT.clientWidth /2);
	 //cancelBub();
	 eval('AMT.style.pixelTop=' + y);
     eval('AMT.style.pixelLeft=' + x);
	 AMT.filters[0].apply();
     AMT.style.visibility="visible";
     AMT.filters[0].Play();
	 //document.forms[0].RUTNUM.focus();
 }

 function hideAMT(){
      AMT.style.visibility="hidden";
 }

document.onclick= hideAMT;


function suma()  
{
var boxLength = document.forms[0].choiceBoxMas.length;
var selectedItem = document.forms[0].LINES.selectedIndex;
var selectedText = document.forms[0].LINES.options[selectedItem].text;
var selectedValue = document.forms[0].LINES.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBoxMas.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 

if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBoxMas.options[boxLength] = newoption;
}
document.forms[0].LINES.selectedIndex=0;
saveMe()
}

function resta()  
{
var boxLength = document.forms[0].choiceBoxMenos.length;
var selectedItem = document.forms[0].LINES.selectedIndex;
var selectedText = document.forms[0].LINES.options[selectedItem].text;
var selectedValue = document.forms[0].LINES.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBoxMenos.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 

if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBoxMenos.options[boxLength] = newoption;
}
document.forms[0].LINES.selectedIndex=0;
saveMe()
}


function removeMas() {
var boxLength = document.forms[0].choiceBoxMas.length;
arrSelected = new Array();
var count = 0;
for (i = 0; i < boxLength; i++) {
if (document.forms[0].choiceBoxMas.options[i].selected) {
arrSelected[count] = document.forms[0].choiceBoxMas.options[i].value;
}
count++;
}
var x;
for (i = 0; i < boxLength; i++) {
for (x = 0; x < arrSelected.length; x++) {
if (document.forms[0].choiceBoxMas.options[i].value == arrSelected[x]) {
document.forms[0].choiceBoxMas.options[i] = null;
   }
}
boxLength = document.forms[0].choiceBoxMas.length;
   }
saveMe()
}


function removeMenos() {
var boxLength = document.forms[0].choiceBoxMenos.length;
arrSelected = new Array();
var count = 0;
for (i = 0; i < boxLength; i++) {
if (document.forms[0].choiceBoxMenos.options[i].selected) {
arrSelected[count] = document.forms[0].choiceBoxMenos.options[i].value;
}
count++;
}
var x;
for (i = 0; i < boxLength; i++) {
for (x = 0; x < arrSelected.length; x++) {
if (document.forms[0].choiceBoxMenos.options[i].value == arrSelected[x]) {
document.forms[0].choiceBoxMenos.options[i] = null;
   }
}
boxLength = document.forms[0].choiceBoxMenos.length;
   }
saveMe()
}




function removeMe() {
var boxLength = document.forms[0].choiceBox.length;
arrSelected = new Array();
var count = 0;
for (i = 0; i < boxLength; i++) {
if (document.forms[0].choiceBox.options[i].selected) {
arrSelected[count] = document.forms[0].choiceBox.options[i].value;
}
count++;
}
var x;
for (i = 0; i < boxLength; i++) {
for (x = 0; x < arrSelected.length; x++) {
if (document.forms[0].choiceBox.options[i].value == arrSelected[x]) {
document.forms[0].choiceBox.options[i] = null;
   }
}
boxLength = document.forms[0].choiceBox.length;
   }
}


<!-- Begin
function stringInit() {

s = "<%= brnDetails.getE01DPXEXP().trim()%>";

filteredValues = "+-/*";     // Characters stripped out
filteredExcl = "1234567890";     // Characters stripped out

var i;
var f1 = "";
var f2 = "";
var minus = "-";
var plus = "+";
var isplus;
var isminus;
var firstplus;
var firstminus;
var returnString = "";
for (i = 0; i < s.length; i++) {  // Search through string and append to unfiltered values to returnString.
var c = s.charAt(i);

// if (filteredExcl.indexOf(c) == -1) returnString += c;

if (c == plus)  {
	isplus = true;
	isminus = false;
	firstplus = true;


	// si hay valor, adiciona a arreglo +
	if (f1 != "") initPlus(f1); f1="";

	} else {
		firstplus = false;
}

if (c == minus)  {
	isminus = true;
	isplus = false;
	firstminus = true;


	// si hay valor, adiciona a arreglo +
	if (f2 != "") initMinus(f2); f2="";

	} else {
		firstminus = false;
}


if (isplus) {
	if (firstplus == false) {

		f1 = f1+c;
	}
	firstplus = false;
}



if (isminus) {
	if (firstminus == false) {

		f2 = f2+c;
	}
	firstminus = false;
}

}
// si hay valor, adiciona a arreglo +
if (f1 != "") initPlus(f1); 
if (f2 != "") initMinus(f2);
}

function initPlus(f1) {
var selectedItem = f1;
// alert("entra "+selectedItem);

var boxLength = document.forms[0].LINES.length;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
	thisitem = document.forms[0].LINES.options[i].value;
	if (thisitem == selectedItem) {
		selectedItem = i;
		break;
      }
   }
} 

var boxLength = document.forms[0].choiceBoxMas.length;
var selectedText = document.forms[0].LINES.options[selectedItem].text;
var selectedValue = document.forms[0].LINES.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBoxMas.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 
if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBoxMas.options[boxLength] = newoption;
}
document.forms[0].LINES.selectedIndex=-1;
}


function initMinus(f2) {
var selectedItem = f2;
// busca index a partir del codigo
var boxLength = document.forms[0].LINES.length;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
	thisitem = document.forms[0].LINES.options[i].value;
	if (thisitem == selectedItem) {
		selectedItem = i;
		break;
      }
   }
} 

var boxLength = document.forms[0].choiceBoxMenos.length;
var selectedText = document.forms[0].LINES.options[selectedItem].text;
var selectedValue = document.forms[0].LINES.options[selectedItem].value;
var i;
var isNew = true;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisitem = document.forms[0].choiceBoxMenos.options[i].text;
if (thisitem == selectedText) {
isNew = false;
break;
      }
   }
} 
if (isNew) {
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBoxMenos.options[boxLength] = newoption;
}
document.forms[0].LINES.selectedIndex=-1;
}



//  End -->



// fin funciones expresion de calculo

</script>

<H3 align="center">Mantenimiento Estado Financiero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="financial_maintenance.jsp, EDP0703"></H3>
<P align="center"><INPUT type="text" name="DSC" size="35" maxlength="35"
	value="<%= userPO.getHeader10().trim()%>" readonly></P>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0703" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="E01DPXCFO" value="<%= brnDetails.getE01DPXCFO().trim()%>">
  <input type=HIDDEN name="opt">
  <INPUT TYPE=HIDDEN NAME="E01DPXFO1">
  <INPUT TYPE=HIDDEN NAME="E01DPXFO2">
  <INPUT TYPE=HIDDEN NAME="E01DPXFO3">
  <INPUT TYPE=HIDDEN NAME="E01DPXFO4">
  <INPUT TYPE=HIDDEN NAME="E01DPXFO5">
  <INPUT TYPE=HIDDEN NAME="SEL" VALUE=""> 
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= brnDetails.getE01CNTLAN().trim()%>">

<div id="AMT" style="position:absolute; visibility:hidden; left : 0px; top : -50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)}" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="500"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">

							        <td nowrap align="left" width="39%">  
						            <select name="LINES" size=14>
             							<%
						                optList1.initRow();
                						int k=1;
                						while (optList1.getNextRow()) {
                    						if (optList1.getFlag().equals("")) {
                    							out.println(optList1.getRecord());
                    							k++;
							                    }        
							                }                 	
						    			%> 
						            </select>
									</td>

								<td nowrap align="center" width="10%">
								<BR>
								<INPUT type="button" value=">> +" onclick="suma();" ><BR>
								<BR>
								<BR>
								<BR>
								<BR>
								<BR>
								<INPUT type="button" value=">> - " onclick="resta();"> <BR>
								<BR>
								 <DIV align="left"></DIV>
								<td nowrap width="30%">
								
								
							
									<TABLE id="tbenter" width="100%" border="1" cellspacing="1" cellpadding="0">
									<TBODY>
										<TR>
											<TD align="left">Suman a este renglón:</TD>
										</TR>
										<TR>
											<TD align="center"><SELECT multiple name="choiceBoxMas"
												style="width: 150" size="5">
											</SELECT><BR>
											<INPUT type="button" value="Remover"
												onclick="removeMas();">
											</TD>
										</TR>
										<TR>
											<TD>Restan a este renglón:</TD>
										</TR>
										<TR>
											<TD align="center"><SELECT multiple name="choiceBoxMenos" style="width: 150"
												size="5">
											</SELECT><BR>
											<INPUT type="button" value="Remover"
												onclick="removeMenos();">
											</TD>
										</TR>
									</TBODY>
									</TABLE>
								</TD>

								<td nowrap align="left" width="70%">
					</TABLE>
					</TD>
				</TR>


				<TR>
					<TD width="100%">
					
				
					
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right"> </DIV>
								</td>
								<td nowrap align="left" width="70%"><TABLE border="1">
									<TBODY>
									</TBODY>
								</TABLE>
								</td>
							</tr>
						</TBODY>
					</TABLE>

					</TD>
				</TR>


			</TBODY>
		</TABLE>
		</td>
    </tr>

 </TABLE>
</div>



  
  <h4><% if(userPO.getHeader16().equals("2")){out.print("Codigo a Modificar:");} else {out.print("Codigo a Crear:"); } %> </h4> 
  
  
  <table  class="tableinfo" height="235" width="661">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="600"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
									<div align="right">Código Agrupación:</div>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPXCLI" size="3" maxlength="4" value="<%= brnDetails.getE01DPXCLI().trim()%>" <% if(userPO.getHeader16().equals("2")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DSCLIN" size="30" maxlength="30" readonly value="<%= brnDetails.getE01DSCLIN().trim()%>">
									<% if(!userPO.getHeader16().equals("2")){ %>
									<A href="javascript:GetCodeDescCNOFC('E01DPXCLI','E01DSCLIN','06')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
									<%}%>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="30%">
									<DIV align="right">Número de Codigo:</DIV>
								</TD>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPXGLN" size="18" maxlength="16" onkeypress="enterInteger()" value="<%= brnDetails.getE01DPXGLN().trim()%>" <% if(userPO.getHeader16().equals("2")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Descripción Codigo:</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPXDSG" size="35" maxlength="35" value="<%= brnDetails.getE01DPXDSG().trim()%>"; >
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Secuencia Cálculo:</DIV>
								</TD>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPXSEC" size="5" maxlength="6" onkeypress="enterInteger()" value="<%= brnDetails.getE01DPXSEC().trim()%>">
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Tipo Linea:</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPXLIN">
									<option value=" " > </option>
									<option value="1"
										<%if (brnDetails.getE01DPXLIN().equals("1")) { out.print("selected"); }%>>1 Titulo</option>
									<option value="2"
										<%if (brnDetails.getE01DPXLIN().equals("2")) { out.print("selected"); }%>>2 Detalle</option>
									<option value="3"
										<%if (brnDetails.getE01DPXLIN().equals("3")) { out.print("selected"); }%>>3 Calculo</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="100%">
					<TABLE id="opTable" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="clear">
								<td nowrap width="9%">
      				   		<TD ALIGN=LEFT class=tdbkg width="119"><a href="javascript:goAction(1)"><b>Fórmula de cálculo</b></a>
							</td>
								<td nowrap align="left" width="70%">
									<TEXTAREA rows="3" cols="39"
										name="E01DPXEXP" readonly><%= brnDetails.getE01DPXEXP().trim()%> 
									</TEXTAREA>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
					<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="17%">
								<DIV align="right">Seleccione <BR>
								Variable<BR>
								del Indice <BR>
								Financiero:</DIV>
								</td>
								<td nowrap align="left" width="100%">

								<TABLE border="0" width="529">
									<TBODY>
										<TR>
											<TD nowrap width="32%"><select name="available" size=10
												onchange="moveOver();">
												<%
                						optCNP5.initRow();
                						while (optCNP5.getNextRow()) {
                    					if (optCNP5.getFlag().equals("")) {
                    						out.println(optCNP5.getRecord());
                    						}        
						                 }
						    			%>
											</SELECT></TD>
											<TD nowrap width="28%"><input type="button" value="<<Remover"
												onclick="removeMe();"> <BR>
											</TD>
											<TD nowrap width="40%"><select multiple name="choiceBox"
												style="width: 150" size="5">
											</select> <BR>
											Variable <BR>
											seleccionadas</TD>
										</TR>
									</TBODY>
								</TABLE>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					
					
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Integra Patrimonio a eIBS :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<input type="radio" name="E01DPXIBS" value="1"   
								<%if(brnDetails.getE01DPXIBS().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPXIBS" value="0"        	 
              					<%if(!brnDetails.getE01DPXIBS().equals("1")) out.print("checked");%>>
              					No 
								</td>
							</tr>
						</TBODY>
					</TABLE>
				
					<%-- <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %> --%>
	
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Clasificacion Cuenta :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPXCAC">
									<option value=" " 
									   <%if (brnDetails.getE01DPXCAC().equals(" ")) { out.print("selected"); }%>></option>								
									<option value="1" 
									   <%if (brnDetails.getE01DPXCAC().equals("1")) { out.print("selected"); }%>>Activos</option>
									<option value="2"
										<%if (brnDetails.getE01DPXCAC().equals("2")) { out.print("selected"); }%>>Pasivos</option>
									<option value="3" 
										<%if (brnDetails.getE01DPXCAC().equals("3")) { out.print("selected"); }%>>Patrimonio</option>	
									<option value="4" 
										<%if (brnDetails.getE01DPXCAC().equals("4")) { out.print("selected"); }%>>Ingresos</option>
									<option value="5" 
										<%if (brnDetails.getE01DPXCAC().equals("5")) { out.print("selected"); }%>>Egresos</option>
									<option value="6" 
										<%if (brnDetails.getE01DPXCAC().equals("6")) { out.print("selected"); }%>>Deudora</option>	
									<option value="7" 
										<%if (brnDetails.getE01DPXCAC().equals("7")) { out.print("selected"); }%>>Acredora</option>	
									<option value="8" 
										<%if (brnDetails.getE01DPXCAC().equals("8")) { out.print("selected"); }%>>Otros DB</option>	
									<option value="9" 
										<%if (brnDetails.getE01DPXCAC().equals("9")) { out.print("selected"); }%>>Otros CR</option>	
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					
				
				
				
				
				
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
									<DIV align="right">Numero lineas memos:</DIV>
								</TD>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPXLME" size="5" maxlength="6" onkeypress="enterInteger()" value="<%= brnDetails.getE01DPXLME().trim()%>">
								</td>
							</tr>
						</TBODY>
					</TABLE>
				
				
										
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right"></DIV>
								</td>
								<td nowrap align="left" width="70%"><TABLE border="1">
									<TBODY>
									</TBODY>
								</TABLE>
								</td>
							</tr>
						</TBODY>
					</TABLE>

					</TD>
				</TR>


			</TBODY>
		</TABLE>
		</td>
    </tr>
  </table>
  
  <div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
</div>
          
          <script language="JavaScript">
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPXCLI.focus()");} %>
   		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPXCLI.select()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPXDSG.focus()");} %>
   		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPXDSG.select()");} %>
  		  
  		  </script>
          
  </form>
</body>
</html>
