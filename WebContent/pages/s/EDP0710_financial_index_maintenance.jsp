<html>
<head>
<title>Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>
<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP071001Message"  scope="session" />
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
	
      ok = confirm("Esta seguro que desea crear/modificar este registro?");
      
	  if (ok) 
	       {
	       
	       // valida formula

			s2 = document.forms[0].E01DPIFOR.value;

			var i2;
			var j2=0
			var error = 0
			for (i2 = 0; i2 < s2.length; i2++) {  // Search through string and append to unfiltered values to returnString.
			var c2 = s2.charAt(i2);

			if (c2 == "(") {
				j2 ++;
			}
			if (c2 == ")") {
				j2 --;
			}
			}
			if (j2 != 0) {

				if (document.forms[0].LAN.value == 'S') {
					alert('Error en la fórmula. Faltan o sobran parentesis');
				} else {
					alert("Error in the formula. They lack or they exceed parenthesis");
				}
			

				error = 1;
			}

			// fin validacion formula	       
			
			if (error == 0) {
	        document.forms[0].SCREEN.value="600";
 	        document.forms[0].submit();
	        }
	       }  
}

function goCancel() {

document.forms[0].SCREEN.value="100";
document.forms[0].submit(); 
	  		  
}


//carga valores iniciales de formulas indices
<!-- Begin
function initOver() {

s = "<%= brnDetails.getE01DPIFOR().trim()%>";
// alert(s)
filteredOp = "(+-*/)";     // Characters operators
filteredExcl = "1234567890";     // Characters stripped out

var i;
var j;
var f1 = "";
var c1 = "";
var isvarf = false;
var iscons = false;
var returnString = "";

for (i = 0; i < s.length; i++) {  // Search through string and append to unfiltered values to returnString.
var c = s.charAt(i);

// if (filteredExcl.indexOf(c) == -1) returnString += c;

if (isvarf == true){
// arma codigo formula financiera
	if (c != "]") {
		f1 += c;
	}
}

// es variable financiera
if (c == "[") {
	isvarf = true; 
}
if (c == "¢") {
	isvarf = true; 
}

// es fin de variable financiera
if (c == "]") {
	if (f1 != "") {
		initVar(f1);
		f1=""
		isvarf = false; 
	}
}
if (c == "!") {
	if (f1 != "") {
		initVar(f1);
		f1=""
		isvarf = false; 
	}
}

if (iscons == true){
// arma constante
	if (c != "}") {
		c1 += c;
	}
}

// es constante
if (c == "{") {
	c1 += c;
	iscons = true; 
}

// es fin de constante
if (c == "}") {
	c1 += c;
	if (c1 != "") {
		var boxLength = document.forms[0].choiceBox.length;
		var selectedText = c1;
		var selectedValue = c1;
		// SIEMPRE ADICIONA
		newoption = new Option(selectedText, selectedValue, false, false);
		document.forms[0].choiceBox.options[boxLength] = newoption;

		c1=""
		iscons = false; 
	}
}

// if para operadores logicos
if (filteredOp.indexOf(c) > -1){
	var boxLength = document.forms[0].choiceBox.length;
	var selectedText = c;
	var selectedValue = c;
	var j;
	var isNew = true;
		newoption = new Option(selectedText, selectedValue, false, false);
		document.forms[0].choiceBox.options[boxLength] = newoption;

}else{
} // end if para operadores


} // end for
saveMe()
} //end proc



function initVar(f1) {
var selectedItem = f1;
var i = 0;
// alert ("entra "+selectedItem)
var boxLength = document.forms[0].VARIABLEF.length;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
	thisitem = document.forms[0].VARIABLEF.options[i].value;
	if (thisitem == selectedItem) {
		selectedItem = i;
		break;
      }
   }
} 
var boxLength = document.forms[0].choiceBox.length;
var selectedText = document.forms[0].VARIABLEF.options[selectedItem].text;
var selectedValue = document.forms[0].VARIABLEF.options[selectedItem].value;
// alert(selectedItem+selectedValue+selectedText);

newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;

saveMe()

}



//  End -->


function addOp()  
{
var boxLength = document.forms[0].choiceBox.length;
var selectedItem = document.forms[0].OPERATOR.selectedIndex;
var selectedText = document.forms[0].OPERATOR.options[selectedItem].text;
var selectedValue = document.forms[0].OPERATOR.options[selectedItem].value;
var i;
// SIEMPRE ADICIONA
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;
document.forms[0].OPERATOR.selectedIndex=-1;
saveMe()
}


function addVar()  
{
var boxLength = document.forms[0].choiceBox.length;
var selectedItem = document.forms[0].VARIABLEF.selectedIndex;
var selectedText = document.forms[0].VARIABLEF.options[selectedItem].text;
var selectedValue = document.forms[0].VARIABLEF.options[selectedItem].value;

newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;
document.forms[0].VARIABLEF.selectedIndex=-1;
saveMe()
}


function addCons()  
{
var cons = "{"+document.forms[0].CONSTANTE.value+"}"
var boxLength = document.forms[0].choiceBox.length;
var selectedText = cons;
var selectedValue = cons;
var i;
// SIEMPRE ADICIONA
newoption = new Option(selectedText, selectedValue, false, false);
document.forms[0].choiceBox.options[boxLength] = newoption;
document.forms[0].OPERATOR.selectedIndex=-1;
saveMe()
}


function saveMe() {
var strValuesT = "";
var strValuesC = "";
var ci = "";
var cf = "";
var boxLength = document.forms[0].choiceBox.length;
var boxLengthVF = document.forms[0].VARIABLEF.length;
var count = 0;
var j;

if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {

var selectedText = document.forms[0].choiceBox.options[i].text;

if (count == 0) {

	//Evalua si es variable financiera
	j = 0;
	if (boxLength != 0) {
		for (j = 0; j < boxLengthVF; j++) {
			thisitem = document.forms[0].VARIABLEF.options[j].text;
			if (thisitem == selectedText) {
				ci = "[";
				cf = "]";
			}
		}
	}


	strValuesT = document.forms[0].choiceBox.options[i].text;
	strValuesC = ci + document.forms[0].choiceBox.options[i].value + cf;
} else {

	//Evalua si es variable financiera
	j = 0;
	ci = "";
	cf = "";
	if (boxLength != 0) {
		for (j = 0; j < boxLengthVF; j++) {
			thisitem = document.forms[0].VARIABLEF.options[j].text;
			if (thisitem == selectedText) {
				ci = "[";
				cf = "]";
			}
		}
	}

	strValuesT = strValuesT + document.forms[0].choiceBox.options[i].text;
	strValuesC = strValuesC + ci + document.forms[0].choiceBox.options[i].value + cf;
}

count++;
}
}

if (strValuesC.length == 0) {
document.forms[0].E01DPIFORW.value="";
document.forms[0].E01DPIFOR.value="";

}
else {
document.forms[0].E01DPIFORW.value=strValuesT;
document.forms[0].E01DPIFOR.value=strValuesC;
   }

}


function remove() {

var i = document.forms[0].choiceBox.selectedIndex ;


//var boxLength = document.forms[0].choiceBox.length;
arrSelected = new Array();
var count = 0;
	document.forms[0].choiceBox.options[i] = null;
	boxLength = document.forms[0].choiceBox.length;
	saveMe();
}

// -------------------------------------------------------------------
// moveOptionUp(select_object)
//  Move selected option in a select list up one
// -------------------------------------------------------------------
function moveOptionUp(obj) {
	if (!hasOptions(obj)) { return; }
	var x = document.forms[0].choiceBox.selectedIndex 
	for (i=0; i<obj.options.length; i++) {
		if (obj.options[i].selected) {
			if (i != 0 && !obj.options[i-1].selected) {
				swapOptions(obj,i,i-1);
				obj.options[i-1].selected = true;
				}
			}
		}
		saveMe();
	}

// -------------------------------------------------------------------
// moveOptionDown(select_object)
//  Move selected option in a select list down one
// -------------------------------------------------------------------
function moveOptionDown(obj) {
	if (!hasOptions(obj)) { return; }
	var x = document.forms[0].choiceBox.selectedIndex 
	for (i=obj.options.length-1; i>=0; i--) {
		if (obj.options[i].selected) {
			if (i != (obj.options.length-1) && ! obj.options[i+1].selected) {
				swapOptions(obj,i,i+1);
				obj.options[i+1].selected = true;
				}
			}
		}
		saveMe();
	}

// -------------------------------------------------------------------
// swapOptions(select_object,option1,option2)
//  Swap positions of two options in a select list
// -------------------------------------------------------------------
function swapOptions(obj,i,j) {
	var o = obj.options;
	var i_selected = o[i].selected;
	var j_selected = o[j].selected;
	var temp = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);
	var temp2= new Option(o[j].text, o[j].value, o[j].defaultSelected, o[j].selected);
	o[i] = temp2;
	o[j] = temp;
	o[i].selected = j_selected;
	o[j].selected = i_selected;
	}

// -------------------------------------------------------------------
// hasOptions(obj)
//  Utility function to determine if a select object has an options array
// -------------------------------------------------------------------
function hasOptions(obj) {
	if (obj!=null && obj.options!=null) { return true; }
	return false;
	}


</script>
<H3 align="center">Mantenimiento Fórmulas Estados Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="financial_index_maintenance.jsp, EDP0710"></H3>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0710" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="E01DPIFOR" VALUE="<%= brnDetails.getE01DPIFOR().trim()%>">
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= brnDetails.getE01CNTLAN().trim()%>">

  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
      
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Código:</div>
            </td>
            <td nowrap width="55%"> 
             <INPUT type="text" name="E01DPIIND" maxlength="4" size="4"
					value="<%= brnDetails.getE01DPIIND().trim()%>" ;
					<% if(userPO.getHeader16().equals("2")){out.print("readonly");} %> >					
			</td>
			<TD width="14"></td>
            <td nowrap width="28%"></td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Descripción:</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 
      			<INPUT type="text" name="E01DPIDSC" size="35" maxlength="35"
					value="<%= brnDetails.getE01DPIDSC().trim()%>"
					;
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %> >
			</td>
            <td nowrap height="23" colspan="1" width="14"> 
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
            </td>
            <td nowrap height="23" colspan="1"> 
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap height="19" colspan="3">
		 </tr>
		 
 		 <tr id="trdark"> 
            <td nowrap width="5%" height="19">
            </td>
            <td nowrap height="19" width="55%">
			</td>
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
         </tr>
        </table>
        
        <table cellspacing="0" cellpadding="2" width="100%" border="1">
          <tr id="trdark"> 
            	<td nowrap width="10%" align="right"> 
				</td>
				<td nowrap width="40%" align="right" valign="top">Seleccione
				Operador Lógico: <SELECT name="OPERATOR" size="6">
					<OPTION value="(">(</OPTION>
					<OPTION value="+">+</OPTION>
					<OPTION value="-">-</OPTION>
					<OPTION value="*">*</OPTION>
					<OPTION value="/">/</OPTION>
					<OPTION value=")">)</OPTION>
				</SELECT>
				<TD nowrap width="6%" align="center">
				<BR>
				<INPUT type="button" value="&gt;&gt;" onclick="addOp();"></td>
            	<td nowrap width="39%">
            	</td>
          </tr>
          
          <tr id="trclear"> 
            
            <td align="right"> 
            	Seleccione<BR>Variable <BR>Financiera:
			</td>
			
            <td align="right"> 
				<SELECT name="VARIABLEF" size="10">
					<%
                optLP4.initRow();
                int j=1;
                while (optLP4.getNextRow()) {
                    if (optLP4.getFlag().equals("")) {
                    		out.println(optLP4.getRecord());
                    j++;
                    }        
                 }
    			%>
				</SELECT>
			</td>
			
			<TD width="57" align="center">
				<INPUT type="button" value="&gt;&gt;" onclick="addVar();">
			</td>
			
            <td align="center"  border="1" cellspacing="1" cellpadding="0" width="366">
            	<SELECT multiple name="choiceBox" style="width: 250" size="10">
				</SELECT>
				<BR>
				<INPUT type="button" value="Remover" onclick="remove();">
			</td>
			
            <td nowrap align="right" width="5%" valign="middle">
            	<INPUT TYPE="button" VALUE="&nbsp;Up&nbsp;" onClick="moveOptionUp(this.form['choiceBox'])">
				<BR><BR>
				<INPUT TYPE="button" VALUE="Down" onClick="moveOptionDown(this.form['choiceBox'])">
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="10%" align="right"></td>
            <td nowrap width="40%" align="right"> 
				 
            Entre Valor Constante:<INPUT type="text" name="CONSTANTE" size="13" maxlength="15"
					value="<%= "" %>"
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>>
			<TD nowrap width="6%" align="center">
			<INPUT type="button" value="&gt;&gt;" onclick="addCons();"></td>
            <td nowrap width="39%">
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="10%"> 
            <td nowrap width="40%"> 
			<TD nowrap width="6%">
            <td nowrap width="39%">
            </td>
          </tr>
          
        </table>
  
  		<table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="100%" align="center"> 
			<TEXTAREA rows="4" cols="81" name="E01DPIFORW" readonly> 
			</TEXTAREA></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="100%" align="center"> 
			</td>
          </tr>
       </table>
       
       <table cellspacing="0" cellpadding="2" width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Aplica a Tipo de Formato:</div>
            </td>
            <td nowrap height="19" colspan="3">
			<SELECT name="E01DPILGT" >
					<OPTION value=""
						<%if (brnDetails.getE01DPILGT().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="1"
						<%if (brnDetails.getE01DPILGT().equals("1")) { out.print("selected"); }%>>Cliente Persona Juridica</OPTION>
					<OPTION value="2"
						<%if (brnDetails.getE01DPILGT().equals("2")) { out.print("selected"); }%>>Cliente Persona Natural</OPTION>
			</SELECT>
			</TD>	
		  </tr>
  		</table> 
  		
  		</TD>      
      </TR> 
  </table>
  
  <div align="left">
  </div>
  
  <div align="center"> 
	  <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
      <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
  </div>
          <script language="JavaScript">
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPIIND.focus()");} %>
   		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPIIND.select()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPIDSC.focus()");} %>
  		  </script>
</form>
</body>
</html>