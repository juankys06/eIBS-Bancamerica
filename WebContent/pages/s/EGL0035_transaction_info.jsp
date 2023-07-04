<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head>
<title>Transacciones</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript" >
 function enterInfo() {
 <% 
    String m = request.getParameter("CurrRow");
    if (m.equals("")){
          out.println("var row = \"\"; ");
   }
  else {  
    out.println("var row="+request.getParameter("CurrRow")+";");
 } %>
 var form = top.opener.document.forms[0];
 if ( row !== "" ) {
   row = "_"+ row;

 form["E01WRKEXR"+ row].value = trim(document.forms[0].E01WRKEXR.value);
 form["E01WRKCUN"+ row].value = trim(document.forms[0].E01WRKCUN.value);
 form["E01WRKMD1"+ row].value = trim(document.forms[0].E01WRKMD1.value);
 form["E01WRKMD2"+ row].value = trim(document.forms[0].E01WRKMD2.value);
 form["E01WRKMD3"+ row].value = trim(document.forms[0].E01WRKMD3.value);
 form["E01WRKCKN"+ row].value = trim(document.forms[0].E01WRKCKN.value);
 
  }
 form["E01WRKTDS"+ row].value = trim(document.forms[0].E01WRKTDS.value);
 form["E01WRKVD1"+ row].value = trim(document.forms[0].E01WRKVD1.value);
 form["E01WRKVD2"+ row].value = trim(document.forms[0].E01WRKVD2.value);
 form["E01WRKVD3"+ row].value = trim(document.forms[0].E01WRKVD3.value);
 
top.close();
 }

function setInfo() {
 <% 
//    String m = request.getParameter("CurrRow");
    if (m.equals("")){
          out.println("var row = \"\"; ");
   }
  else {  
    out.println("var row="+request.getParameter("CurrRow")+";");
 } %>
 var form = top.opener.document.forms[0];
 if ( row !== "" ) {
   row = "_"+ row;
   
 document.forms[0].E01WRKEXR.value=form["E01WRKEXR"+ row].value;
 document.forms[0].E01WRKCUN.value=form["E01WRKCUN"+ row].value;
 document.forms[0].E01WRKMD1.value=form["E01WRKMD1"+ row].value;
 document.forms[0].E01WRKMD2.value=form["E01WRKMD2"+ row].value;
 document.forms[0].E01WRKMD3.value=form["E01WRKMD3"+ row].value;
 document.forms[0].E01WRKCKN.value=form["E01WRKCKN"+ row].value;
  }
 document.forms[0].E01WRKTDS.value=form["E01WRKTDS"+ row].value;
 document.forms[0].E01WRKVD1.value=form["E01WRKVD1"+ row].value;
 document.forms[0].E01WRKVD2.value=form["E01WRKVD2"+ row].value;
 document.forms[0].E01WRKVD3.value=form["E01WRKVD3"+ row].value;
 
 
 }
  
function checkLength(field) {
   if ( field.value.length < 300 ) {
     field.value = field.value.toUpperCase();
     return true;
	}
	else {
     return false;
	}
}
 </SCRIPT>
 
</head>
<body>
<form method="post">

  <h3 align="left">Información</h3>

  <table width="100%" class="tableinfo" cellpadding="2" cellspacing="2">
    <tr> 
      <td id=trdark width="25%" nowrap valign="top"> 
        <div align="right">Descripción : </div>
      </td>
      <td width="75%" nowrap > 
        <textarea name="E01WRKTDS" cols="35" rows="10" id="PHYSICAL" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW"))){ out.print("readonly");}%>></textarea>
      </td>
    </tr>
    <tr> 
      <td id=trdark width="25%" nowrap > 
        <div align="right">Fecha Valor : </div>
      </td>
      <td width="75%" nowrap> 
        <input type="text" name="E01WRKVD1" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        <input type="text" name="E01WRKVD2" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        <input type="text" name="E01WRKVD3" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>
	<% 
     if (!(m.equals(""))){
    %>
	<tr> 
      <td id=trdark width="25%" nowrap > 
        <div align="right">Tasa de Cambio : </div>
      </td>
      <td width="75%" nowrap> 
        <input type="text" name="E01WRKEXR" size="12" maxlength="11" onKeypress="enterRate()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>
	<tr> 
      <td id=trdark width="25%" nowrap > 
        <div align="right">Número de Cliente : </div>
      </td>
      <td width="75%" nowrap> 
        <input type="text" name="E01WRKCUN" size="10" maxlength="9" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        <% if (userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW")){%>
			<a href="javascript:GetCustomerDescId('E01WRKCUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
        <% } %>
	  </td>
    </tr>

    <tr> 
      <td id=trdark width="25%" nowrap > 
        <div align="right">Fecha Vencimiento : </div>
      </td>
      <td width="75%" nowrap> 
        <input type="text" name="E01WRKMD1" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        <input type="text" name="E01WRKMD2" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        <input type="text" name="E01WRKMD3" size="3" maxlength="2" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>

    <tr> 
      <td id=trdark width="25%" nowrap > 
        <div align="right">N&uacute;mero de Cheque : </div>
      </td>
      <td width="75%" nowrap> 
        <input type="text" name="E01WRKCKN" size="10" maxlength="9" onKeypress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>
    <tr> 
      <td id=trdark width="14%" nowrap > 
        <div align="right">Cheques Locales :</div>
      </td>
      <td nowrap> 
        <input type="text" name="E01WRKCR2" size="17" maxlength="15" onKeyPress="enterSignDecimal()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        Dias :
        <input type="text" name="E01WRKUN2" size="3" maxlength="2" onKeyPress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>
    <tr> 
      <td id=trdark width="14%" nowrap > 
        <div align="right">Cheques No Locales :</div>
      </td>
      <td nowrap>
        <input type="text" name="E01WRKCR3" size="17" maxlength="15" onKeyPress="enterSignDecimal()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
        Dias : 
        <input type="text" name="E01WRKUN3" size="3" maxlength="2" onKeyPress="enterInteger()" value="" <% if (!(userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW") )){ out.print("readonly");}%>>
      </td>
    </tr>
  <%  } %>
  </table> 
  <BR>
  <SCRIPT>
    setInfo();
  </SCRIPT>
  <%
   if (userPO.getPurpose().equals("MAINTENANCE") || userPO.getPurpose().equals("NEW")){
   %>
  
  <div align="center"> 
    <p><input id="EIBSBTN" type=button name="Submit" OnClick="enterInfo()" value="Enviar"></p>
  </div>

  <SCRIPT>
   document.forms[0].E01WRKTDS.focus();
  </SCRIPT>
 <% }  %> 
</form>
</body>
</html>			
