<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Reglas de Firmas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "rulesList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "categList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<% 
 boolean newRule = true;
 int row;
 datapro.eibs.beans.DataSignRule dsr = new datapro.eibs.beans.DataSignRule();
 dsr.setAccUid(userPO.getIdentifier());
 
 String error = request.getParameter("ERROR");
 
 if (error != null) {
 
    dsr.setSigRule(request.getParameter("RULE"));
    dsr.setAmount(request.getParameter("AMOUNT"));
    try {		  	
		  	row = Integer.parseInt(request.getParameter("ROW")); 
            newRule = false;
		} 
	 catch (Exception e) {
			row = (rulesList.getNoResult()) ? 1 : rulesList.getLastRec() + 2;	    
	 }
	 
 } else {

	 try {		  	
		  	row = Integer.parseInt(request.getParameter("ROW")); 
		  	rulesList.setCurrentRow(row);
	        dsr = (datapro.eibs.beans.DataSignRule) rulesList.getRecord(); 
	        row++;
	        newRule = false;
		} 
	 catch (Exception e) {
			row = (rulesList.getNoResult()) ? 1 : rulesList.getLastRec() + 2;	    
	 }
 }
 
 char[] rule = dsr.getSigRule().trim().toCharArray(); 
   
%>

//new code to sort options
function compareText (option1, option2) {
  return option1.text < option2.text ? -1 :
    option1.text > option2.text ? 1 : 0;
}

function compareValue (option1, option2) {
  return option1.value < option2.value ? -1 :
    option1.value > option2.value ? 1 : 0;
}

function compareTextAsFloat (option1, option2) {
  var value1 = parseFloat(option1.text);
  var value2 = parseFloat(option2.text);
  return value1 < value2 ? -1 :
    value1 > value2 ? 1 : 0;
}

function compareValueAsFloat (option1, option2) {
  var value1 = parseFloat(option1.value);
  var value2 = parseFloat(option2.value);
  return value1 < value2 ? -1 :
    value1 > value2 ? 1 : 0;
}

function sortSelect (select, compareFunction) {
  if (!compareFunction)
    compareFunction = compareText;
  var options = new Array (select.options.length);
  for (var i = 0; i < options.length; i++)
    options[i] = 
      new Option (
        select.options[i].text,
        select.options[i].value,
        select.options[i].defaultSelected,
        select.options[i].selected
      );
  options.sort(compareFunction);
  select.options.length = 0;
  for (var i = 0; i < options.length; i++)
    select.options[i] = options[i];
}



function addCateg(){
  		var fromList = document.forms[0]["CATEG"];
    	var toList = document.forms[0]["SELCATEG"];
  		var ln = toList.options.length;
  		var idx = fromList.selectedIndex;
        toList.options[ln] = new Option(fromList.options[idx].text,fromList.options[idx].value);
        toList.options[ln].id=idx;
        toList.selectedIndex = ln;
        sortSelect(toList);
        concatRule();
        
  }

function delCateg(){
	var toList = document.forms[0]["SELCATEG"];
	var idx = toList.selectedIndex;
	toList.options[idx]=null;
	if (idx > 0) {
       	  idx--;
		  toList.selectedIndex = idx;
	}
	sortSelect(toList);
	concatRule();
}

function concatRule() {
    var toList = document.forms[0]["SELCATEG"];
    var rule = "";
    for (var i = 0; i < toList.length; i++)
       rule = rule + toList.options[i].value;   
    document.forms[0]["SIGRULE"].value = rule;   

}

function setInfo(val) {
   if (val == 2) {   
      document.forms[0]["AMOUNT"].value = "0.00";
      document.forms[0]["AMOUNT"].style.visibility = "hidden";
   } else {
      document.forms[0]["AMOUNT"].style.visibility = "visible";
      document.forms[0]["AMOUNT"].focus();
   }
}
 
function  checkValues() {

   if (document.forms[0]["SIGRULE"].value == "" ) {
      alert("No pueden existir reglas , sin categorias.");
      return false;
   }
   
   if (document.forms[0]["TYPEAMT"][0].checked == true ) {
      if (document.forms[0]["AMOUNT"].value == "" || document.forms[0]["AMOUNT"].value == "0.00" ||
          document.forms[0]["AMOUNT"].value == "0,00") {
      	alert("El monto de la regla no puede ser 0 o vacio.");
      	return false;
      }
   }
     
    if (document.forms[0]["CCYCODE"].value == "" ) {
      	alert("Codigo de Moneda incorrecto");
      	return false;
    }
    
	
    
    if (document.forms[0]["DAYFROM"].value == "" || document.forms[0]["MONTHFROM"].value == "" ||
          document.forms[0]["YEARFROM"].value == "") {
      	alert("Fecha Vigente Desde : Incorrecta");
      	return false;
    }
    
    if (document.forms[0]["DAYTO"].value == "" || document.forms[0]["MONTHTO"].value == "" ||
      document.forms[0]["YEARTO"].value == "") {
  		alert("Fecha Vigente Hasta : Incorrecta");
  		return false;
	}
	
    var isMenor = compareUTCDates(document.forms[0]["YEARFROM"].value,document.forms[0]["MONTHFROM"].value,document.forms[0]["DAYFROM"].value,
    document.forms[0]["YEARTO"].value,document.forms[0]["MONTHTO"].value, document.forms[0]["DAYTO"].value);
    
    if (!isMenor) {
    	alert("Fecha Vigente Hasta debe ser mayor a la Fecha Vigente Desde ");
  		return false;
    }
    
    if (document.forms[0]["DOCNUM"].value == "" ) {
      	document.forms[0]["DOCNUM"].value = "0";
    }
       
   return true;
}

function formatYear(y) {

	if (y.length < 3) {
		
			var aux = parseInt(y);
			if (aux == 0) {
				y = "2000";
			}
			else {
				if (aux > 70) {
					y = "19" + y;
				}
				else {
				    if (aux < 10 ) y = "200" + aux; else y = "20" + y;
				}
			}
		
	}
		
	return y;
	
}

function compareUTCDates(yearFrom,monthFrom,dayFrom,yearTo,monthTo,dayTo) {

  var isMenor = false;
  var yF = Number(formatYear(yearFrom));
  var mF = Number(monthFrom) - 1;
  var dF = Number(dayFrom);
  var yT = Number(formatYear(yearTo));
  var mT = Number(monthTo)-1;
  var dT = Number(dayTo);
  
  // UTC year,month,day
  var dFrom = Date.UTC(yF,mF,dF);
  var dTo = Date.UTC(yT,mT,dT);
  
  if ( dFrom < dTo) {
	isMenor = true
  }
  
  return isMenor
}


</SCRIPT>
</HEAD> 
<BODY >
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000F" onsubmit="return(checkValues())">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="<% if (newRule) out.print("1"); else out.print("2"); %>">
<INPUT TYPE=HIDDEN NAME="SIGRULE" VALUE="<%= dsr.getSigRule().trim() %>">
<INPUT TYPE=HIDDEN NAME="ACCUID" VALUE="<%= dsr.getAccUid().trim() %>">
 <h3 align="center">
  <% if (newRule) out.print("Nuevo"); else out.print("Mantenimiento"); %> de Regla de Firma 
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_sign_rules_maint.jsp,EDD0000"> 
    </h3>
   <hr size="4">
   
  
  <BR>
  
 <TABLE  id="mainTable" class="tableinfo"  > 
 <TR id="trdark">	
  	<td nowrap > 
      <div align="right">Regla : </div>
    </td>
	<td nowrap colspan=2> 
	  <input type="text" name="ROW" size="15" maxlength="15" value="<%= row%>" readonly>
	</td>
 </TR>
 <TR>	
  	<td nowrap > 
      <div align="center">Categorias </div>
    </td>
	<td nowrap > 
      <div align="right"></div>
    </td>
    <td nowrap > 
      <div align="center">Seleccion </div>
    </td>
 </TR>	
 
 <TR > 
  <TD NOWRAP valign="top" width="45%" align="center">
   
              <select name="CATEG" size="6">
               <%
		         categList.initRow();		         
		         while ( categList.getNextRow()) {		            
            		datapro.eibs.beans.EWD0002DSMessage msgHelp = (datapro.eibs.beans.EWD0002DSMessage) categList.getRecord();
     			    if (!msgHelp.getEWDCOD().trim().equals("")) {
     			%>
		              <option value="<%= msgHelp.getEWDCOD().trim() %>">  <%= msgHelp.getEWDCOD().trim() %>  </option>
		        <% } 		      	        
		        }
		        %>                
              </select>  
  </TD>
  <TD width="10%" align="center">
    <input type="button" name="add" value="&gt;&gt;" onclick="addCateg()"> <BR><BR>
    <input type="button" name="remove" value="&lt;&lt;" onclick="delCateg()">
  </TD>
  
  <TD nowrap width="45%" align="center"> 
              <select name="SELCATEG" size="6">
              <%                
                 if (!newRule) {
                  for (int i=0;i< rule.length;i++) {          
              %>
		           <option value="<%= rule[i] %>">  <%= rule[i] %>  </option>
              <%   
                  }
                 }                
              %>       
              </select>
  </TD>
  </TR>	
  <TR id="trdark">	
  	<td nowrap > 
      <div align="right">Monto :</div>
    </td>
	<td nowrap colspan=2>
	   <input type="radio" name="TYPEAMT" value="1" <% if (!(dsr.getAmount().equals("0.00") || dsr.getAmount().equals("0"))) out.print("checked");%> onclick="setInfo(this.value)"> Valor&nbsp;&nbsp;  
	   <input type="text" name="AMOUNT" size="15" maxlength="15" value="<%= Util.formatCCY(dsr.getAmount()) %>" onKeypress="enterDecimal()"><br>
	   <input type="radio" name="TYPEAMT" value="2" <% if (dsr.getAmount().equals("0.00") || dsr.getAmount().equals("0")) out.print("checked");%> onclick="setInfo(this.value)"> Sin Limite 
	</td>
 </TR>
 <TR>	
  	<td nowrap > 
      <div align="right">Moneda : </div>
    </td>
	<td nowrap colspan=2> 
	  <input type="text" name="CCYCODE" size="4" maxlength="3" value="<%= dsr.getCCYCode()%>" >
	  <a href="javascript:GetCurrency('CCYCODE','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>             
	</td>
 </TR>
 <TR id="trdark">
	<td nowrap > 
      <div align="right">Fecha de Vigencia </div>
    </td>
	<td nowrap > 
      <div align="right">Desde : </div>
    </td>
    <td>
      <input type="text" name="DAYFROM" size="2" maxlength="2" value="<%= dsr.getDayFrom()%>" onKeypress="enterInteger()">
      <input type="text" name="MONTHFROM" size="2" maxlength="2" value="<%= dsr.getMonthFrom()%>" onKeypress="enterInteger()">
      <input type="text" name="YEARFROM" size="4" maxlength="4" value="<%= dsr.getYearFrom()%>" onKeypress="enterInteger()">   	   
	  <a href="javascript:DatePicker(document.forms[0].DAYFROM,document.forms[0].MONTHFROM,document.forms[0].YEARFROM)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></a>
    </td> 
 </TR>
 <TR id="trdark"> 
    <td nowrap >       
    </td>                  
    <td nowrap > 
      <div align="right">Hasta : </div>
    </td>
    <td>
      <input type="text" name="DAYTO" size="2" maxlength="2" value="<%= dsr.getDayTo()%>" onKeypress="enterInteger()">
      <input type="text" name="MONTHTO" size="2" maxlength="2" value="<%= dsr.getMonthTo()%>" onKeypress="enterInteger()">
      <input type="text" name="YEARTO" size="4" maxlength="4" value="<%= dsr.getYearTo()%>" onKeypress="enterInteger()">   	   
	  <a href="javascript:DatePicker(document.forms[0].DAYTO,document.forms[0].MONTHTO,document.forms[0].YEARTO)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></a>
    </td>
 </TR>
 <TR >	
  	<td nowrap > 
      <div align="right">Status : </div>
    </td>
	<td nowrap colspan=2> 
	  <select name="STATUS" >
	  		<option value="1" <% if (!dsr.getStatus().equals("0")) out.print("selected");%>>  Activa  </option> 
		    <option value="0" <% if (dsr.getStatus().equals("0")) out.print("selected");%>>  InActiva  </option> 
      </select>
	</td>
 </TR>
 <TR id="trdark">                    
    <td nowrap > 
      <div align="right">Restringida hasta Nro. Documento : </div>
    </td>
    <td COLSPAN=2>
      <input type="text" name="DOCNUM" size="10" maxlength="8" value="<%= dsr.getDocNum()%>" onKeypress="enterInteger()">
    </td>
 </TR>
 <TR >	
  	<td nowrap > 
      <div align="right">Restringia a la Agencia : </div>
    </td>
	<td nowrap colspan=2> 
      <input type="text" name="BRANCH" size="4" maxlength="3" value="<%= dsr.getBranch()%>">
	  <a href="javascript:GetBranch('BRANCH','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
    </td>
 </TR>	        	
</TABLE>
	<br>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>

<SCRIPT language="JavaScript">

 showChecked("TYPEAMT");    
 
 <%                
 if ( error != null ) {
 
     if (!error.equals("1")) {
      	out.println("alert(\" Error de actualizacion. Por favor contacte con el Administardor de sistema\")");
     } else {
        out.println("alert(\" Regla ya existente. Por favor haga otra seleccion\" )");   
 	 }
 	 
 }
 %>
 
</SCRIPT>                

</FORM>

</BODY>
</HTML>
