<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Información Financiera
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "optList1" class= "datapro.eibs.beans.JBList"  scope="session" />
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
	page= prefix +language + "EDP0703_financial_maintenance.jsp"
}
function goCancel(fmt) {
	page= prefix +language + "EDP0703_financial_maintenance.jsp"
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
document.forms[0].LINES.selectedIndex=-1;
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
document.forms[0].LINES.selectedIndex=-1;
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

// 

</script>
<H3 align="center">Seleccion lineas para acumular en <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="financial_maintenance.jsp, EDP0703"></H3>
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
  <h4><% if(userPO.getHeader16().equals("2")){out.print("Formulación expresión de Cálculo");} else {out.print("Formulación expresión de Cálculo"); } %> </h4> 
  <table  class="tableinfo" height="235">
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
						            <select name="LINES" size=12>
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
									<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
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
							<tr id="trdark">
								<td nowrap align="left" width="70%">

								<TABLE border="0">
									<TBODY>
										<TR>
											<TD nowrap width="35%" align="center">
											
											<TEXTAREA rows="3" cols="39" name="E01DPXEXP" readonly><%= brnDetails.getE01DPXEXP().trim()%> 
											</TEXTAREA>
											</TD>
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
  </table>
  <div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
</div>
          <script language="JavaScript">
  		  </script>
  </form>
</body>
</html>