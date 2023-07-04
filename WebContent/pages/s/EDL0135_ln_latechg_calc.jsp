<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnLateChg" class="datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id="userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function goAction(opt){
  document.forms[0]["OPT"].value = opt;
  if (opt=="U") {
    if (document.forms[0]["ROW"].value == "0") top.close();
    else document.forms[0].submit();
  } 
  else { // opt = R recalculate
    if (document.forms[0]["TYPECALC"][0].checked) {
        var val = document.forms[0]["NUMCUO"].value;
        if (val.length == 1) val="0" + val;
    	document.forms[0]["E04NUMCUO"].value = val;
    }
    document.forms[0]["E04NEWRTE"].value = document.forms[0]["NEWRTE"].value;
    document.forms[0].submit();
  } 
}

function setNumCuote(val){ 
   if (val !== document.forms[0]["E04NUMCUO"].value ) {
   	 document.forms[0]["E04NUMCUO"].value = val;
   	 disabledOpt(1);
   }
}

function setDefaultInt(obj){ //call in onBlur event
 	if (trim(obj.value)=="" ) obj.value = "1";
 }
 
 function setDefaultDec(obj){ //call in onBlur event
 	if (trim(obj.value)=="" ) obj.value = "0.000001";
 }
 
 function clearDefault(obj) { //call in onFocus event
    var val = trim(obj.value);
 	if (val=="1" || val=="0.000001") obj.value = "";
 }
 
 function disabledOpt(id) { //call in onFocus event
    var id1 = (id == 0 )? 1: 0;
    document.all["TBOPT"].rows[0].cells[id].style.display = "none";
    document.all["TBOPT"].rows[0].cells[id1].style.display = "";
 }
 
 function setOption() { 
    if (document.forms[0]["TYPECALC"][0].checked) disabledOpt(1);
 }  
</SCRIPT>

</head>
<body nowrap>
<%
 boolean firstTime = false;
 datapro.eibs.beans.EDL013504Message msgCoute = null;
 String numcuo = "";
 String newrte = "";
 int noRow = 1;
 if ( lnLateChg == null ) {
      firstTime = true;
      noRow = 0; 
 } else {
   if (lnLateChg.getNoResult()) noRow = 0;
   else {
      lnLateChg.setCurrentRow(0);
      msgCoute = (datapro.eibs.beans.EDL013504Message) lnLateChg.getRecord();
      numcuo = msgCoute.getE04NUMCUO();
      newrte = msgCoute.getE04NEWRTE(); 
   }
 }
%>
 
<h3 align="center">Intereses por Mora 
</h3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0135" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= noRow%>">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E04NUMCUO" VALUE="<%= numcuo %>">
  <INPUT TYPE=HIDDEN NAME="E04NEWRTE" VALUE="<%= newrte %>">
  <INPUT TYPE=HIDDEN NAME="E04DEAACC" VALUE="<%= userPO.getIdentifier()%>">
            
  <h4>Datos para el C&aacute;lculo de Inter&eacute;s</h4>
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id=trdark>
            <td nowrap> 
              <input type="radio" name="TYPECALC" checked value="" onclick="setNumCuote(document.forms[0].NUMCUO.value)"> Por Cuotas
            </td>
            <td nowrap align="right">                 
				No. :		  			   
 			</td> 
 			<td nowrap >                
			  <input type="text" name="NUMCUO" size="2" maxlength="2" value="1" onKeypress="enterInteger()" onBlur="setDefaultInt(this)" onFocus="clearDefault(this)" 
			  <% if (!firstTime) out.print("onchange='setOption()'");%>>
            </td>
 			<td nowrap align="right">                 
				Tasa Mora :		  			   
 			</td>          
 			<td nowrap >                 
 			  <input type="text" name="NEWRTE" size="9" maxlength="9" value="0.000001" onKeypress="enterDecimal()" onBlur="setDefaultDec(this)" onFocus="clearDefault(this)" 
 			  <% if (!firstTime) out.print("onchange='disabledOpt(1)'");%>>
            </td>
 		  </tr>
 		  <tr id=trdark>
 		    <td nowrap>                 			               
 			  <input type="radio" name="TYPECALC" value="CV" onclick="setNumCuote(this.value)"> Cuotas Vencidas
            </td> 
            <td nowrap align="right" colspan=4>                 			               
 			</td>			
 		  </tr>                     
        </table>
      </td>
    </tr>
  </table>
  
  <TABLE class="tbenter" id="TBOPT">
    <TR> 
      <TD class="TDBKG"> <a href="javascript:goAction('R')">Calcular</a> 
      </TD>
      <TD class="TDBKG" <% if ( noRow == 0) out.print("style='display:none'");%>> <a href="javascript:goAction('U')">Enviar</a> 
      </TD>
      <TD class="TDBKG"> <a href="javascript:top.close();">Salir</a> 
      </TD>
    </TR>
  </TABLE>
   
 <% if ( !firstTime ) {
    	if (!lnLateChg.getNoResult()) {%>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="center"><b>Fecha</b></div>          
            </td>
            <td nowrap> 
              <div align="center"><b>Antes</b></div> 
            </td>            
            <td nowrap> 
              <div align="center"><b>Ahora</b></div>
            </td>         
          </tr> 
         <% 
           lnLateChg.initRow();
           java.math.BigDecimal oldmora = new java.math.BigDecimal("0.00");
           java.math.BigDecimal newmora = new java.math.BigDecimal("0.00");
           while (lnLateChg.getNextRow()) {
             msgCoute = (datapro.eibs.beans.EDL013504Message) lnLateChg.getRecord();           
		%> 
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="center"><%= Util.formatDate(msgCoute.getE04DLPDT1(),msgCoute.getE04DLPDT2(),msgCoute.getE04DLPDT3())%></div>
            </td>
            <td nowrap> 
              <div align="right"><%= Util.formatCCY(msgCoute.getE04OLDMOR())%></div> 
            </td>            
            <td nowrap> 
              <div align="right"><%= Util.formatCCY(msgCoute.getE04NEWMOR())%></div>
            </td>         
          </tr>
        <% 
            newmora = newmora.add(msgCoute.getBigDecimalE04NEWMOR());
            oldmora = oldmora.add(msgCoute.getBigDecimalE04OLDMOR());
        }%> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="center"><b>TOTAL</b></div>
            </td>
            <td nowrap> 
              <div align="right"><b><%= Util.formatCCY("" + oldmora)%></b></div> 
            </td>            
            <td nowrap> 
              <div align="right"><b><%= Util.formatCCY("" + newmora)%></b></div>
              <INPUT TYPE=HIDDEN NAME="LATECHG" VALUE="<%= newmora %>">
            </td>         
          </tr>  
        </table>
      </td>
    </tr>
  </table>
  
  <SCRIPT Language="Javascript">
     if (document.forms[0]["E04NUMCUO"].value == "CV") {
       document.forms[0]["TYPECALC"][1].checked = true;
     } else document.forms[0]["NUMCUO"].value = document.forms[0]["E04NUMCUO"].value;
     document.forms[0]["NEWRTE"].value = document.forms[0]["E04NEWRTE"].value;
     disabledOpt(0);
  </SCRIPT>
<% 		}
	}%>  
  </form>
</body>
</html>
