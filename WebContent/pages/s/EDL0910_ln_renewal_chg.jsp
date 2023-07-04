<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Prestámos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "lnRenew" class= "datapro.eibs.beans.EDL091001Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<% 
    String typ = request.getParameter("Type");
    String title = "";    
         
    if (typ.equals("1")) { title ="Impuestos"; }
    else if (typ.equals("2")) { title ="Comisiónes";}
    else if (typ.equals("3")) { title ="Deducciónes";}
    else if (typ.equals("4")) { title ="I.V.A";} 
    else if (typ.equals("5")) { title ="Otros Cargos";}  
 %>
 
<SCRIPT LANGUAGE="JavaScript">
 function setInfo() {
  var coll = top.opener.document.forms[0].elements("<%= typ%>"); 
  if ( coll.length > 1 ) {
    for (var i=0; i< coll.length;i++) {
      coll[i].value = document.forms[0][coll[i].name].value;
    }
  } 
  else coll.value = document.forms[0][coll.name].value;
  top.opener.UpdateAmts("<%= typ%>",document.forms[0].TOTAL.value);
  top.close();
 }
 
 function getInfo() { 
  var coll = top.opener.document.forms[0].elements("<%= typ%>"); 
  if ( coll.length > 1 ) {
    for (var i=0; i< coll.length;i++) {
      document.forms[0][coll[i].name].value = coll[i].value;
    }
  } 
  else document.forms[0][coll.name].value = coll.value;  
 }
 
 function updateTotal() { 
  var coll = document.forms[0].elements("<%= typ%>");
  var totalVal = 0.00;
  var tmpVal = 0.00;
  
  if ( coll.length > 1 ) {
   for (var i=0; i< coll.length;i++) {      
     try{
      	tmpVal= parseFloat(formatFloat(coll[i].value));
      	if (isNaN(tmpVal)) {
      	  tmpVal= 0.00;
      	  coll[i].value = "0.00";
      	 }
     }
     catch(e){
     	tmpVal=0.00;
     }
     totalVal = totalVal + tmpVal
   }
  } 
  else {
     try{
      	totalVal= parseFloat(formatFloat(coll.value));
      	if (isNaN(totalVal)) totalVal=0.00;
     }
     catch(e){
     	totalVal=0.00;
     }
  } 
  document.forms[0].TOTAL.value = formatCCY("" + totalVal); 
 }  
</SCRIPT>

</head>
<body nowrap>

<H3 align="center">Pago de <%= title %> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_renewal_chg.jsp, EDL0910"> 
</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0910">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><INPUT type="text" size="12" maxlength="12" name="E01DEAACC" value="<%= lnRenew.getE01DEAACC().trim()%>" readonly> </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b> </div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" size="45" maxlength="45" name="E01CUSNA1" value="<%= lnRenew.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="60%"> 
              <div align="center"><b>Descripción</b></div>
            </td>
            <td nowrap width="40%"> 
              <div align="center"><b>Saldo</b></div>
            </td>
          </tr>
          <% 
          String field="";
   		  int numField=25;
   		  for(int i=1;i <= numField;i++){
     		field = (i < 10) ? "0"+ i : ""+ i;
     		if ( lnRenew.getField("E01PTYP"+field).getString().trim().equals(typ)) {
   			%>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="left"><%= lnRenew.getField("E01PDSC"+field).getString().trim() %></div>
            </td>
            <td nowrap align=center> 
              <input type="text" name="E01PAMT<%= field%>" id="<%= typ%>" size="16" maxlength="16" value="" onkeypress="enterDecimal()" onchange="updateTotal()"> 
            </td>
          </tr>
		  <% }
		  } %>
		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><b>Total : </b></div>
            </td>
            <td nowrap align=center> 
              <input type="text" name="TOTAL" size="16" maxlength="16" value="" readonly> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if(userPO.getPurpose().equals("MAINTENANCE")){%>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="SetInfo()" value="Enviar">
  </div>
</p>
  <% } else { %>
  <SCRIPT Language="Javascript">
   var j=document.forms[0].elements.length;
    var felem=document.forms[0].elements;
    for(i=0;i< j;i++){
       if (felem[i].tagName!=="select"){
         if (felem[i].type=="text") felem[i].readOnly=true; else felem[i].disabled=true;
       } 
       else { felem[i].disabled=true;}
    }
 </SCRIPT> 
 <% } %>
  <SCRIPT language="JavaScript">
    getInfo();
    updateTotal();
  </SCRIPT>
</form>
</body>
</html>
