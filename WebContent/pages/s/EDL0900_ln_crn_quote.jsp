<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Calculadora Intereses Préstamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="QuoteLoans" class= "datapro.eibs.beans.EDL090004Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
    
 
%> 
<div align="center"></div>
<h3 align="center"> Cotización de Cronograma de Pagos</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0900" >
  <input type=HIDDEN name="SCREEN" value="2">
  <h4>Información Básica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Principal :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E04DEAOAM" size="16" maxlength="15" value="<%= QuoteLoans.getE04DEAOAM().trim()%>"  onkeypress="enterPrincipal()">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> Fecha Inicial :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEAOD1" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <input type="text" name="E04DEAOD2" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <input type="text" name="E04DEAOD3" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E04DEAOD1,document.forms[0].E04DEAOD2,document.forms[0].E04DEAOD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap   > 
              <div align="right">Fecha Final :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEAMD1" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <input type="text" name="E04DEAMD2" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <input type="text" name="E04DEAMD3" size="2" maxlength="2" value="0"  onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E04DEAMD1,document.forms[0].E04DEAMD2,document.forms[0].E04DEAMD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Término :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEATRM" size="4" maxlength="4" value="" onkeypress="enterInteger()">
              <select name="E04DEATRC">
                <option value=" "></option>
                <option value="D">Día(s)</option>
                <option value="M">Mes(s)</option>
                <option value="Y">Año(s)</option>
              </select>
            </td>
            <td nowrap   > 
              <div align="right">Tasa Intereses :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEARTE" size="10" maxlength="9" value="<%= QuoteLoans.getE04DEARTE().trim()%>"  onkeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right">Período Base :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEABAS" size="3" maxlength="3" value="0"  onkeypress="enterInteger()">
            </td>
            <td nowrap   > 
              <div align="right">Tipo Interés :</div>
            </td>
            <td nowrap   > 
              <input type="text" name="E04DEAICT" size="2" maxlength="1" value="" >
              <a href="javascript:GetCode('E04DEAICT','STATIC_cd_formula.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap  >&nbsp; </td>
            <td nowrap  > 
              <div align="right"></div>
            </td>
            <td nowrap  >&nbsp; </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>

  </form>
</body>
</html>
