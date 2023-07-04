<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Portafolio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invTrade" class="datapro.eibs.beans.EIE007001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<div align="center"> 
  <h3>Esquema de Pago de Cupones <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_quote_basic.jsp,EIE0060"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0070" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n del Instrumento</h4>
  <table  class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01SCHIIC.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
          Instrumento :</a></div>
      </td>
      <td nowrap width="71%" >
        <input type="text" name="E01SCHIIC" size="9" maxlength="9" value="<%=invTrade.getE01SCHIIC()%>" readonly>
        <input type="text" name="D01ISIDSC" size="35" maxlength="30" value="<%= invTrade.getD01ISIDSC()%>" readonly>
        </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right">Tipo de Instrumento : </div>
      </td>
      <td nowrap width="71%" >
        <input type="text" name="D01ISIPTY" size="5" maxlength="3" value="<%=invTrade.getD01ISIPTY()%>" readonly>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right">Moneda del Instrumento :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="D01ISICCY" size="4" maxlength="3" value="<%= invTrade.getD01ISICCY()%>" readonly>
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right"><a href="javascript:GetSymbolInfo(document.forms[0].D01ISISYM.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
          S&iacute;mbolo :</a></div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="D01ISISYM" size="17" maxlength="15" value="<%= invTrade.getD01ISISYM()%>"readonly>
        </div>
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="29%" > 
        <div align="right">ISIN :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="D01ISINUM" size="14" maxlength="12" value="<%= invTrade.getD01ISINUM()%>"readonly>
        </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="29%" > 
        <div align="right">CUSIP :</div>
      </td>
      <td nowrap width="71%" > 
        <div align="left"> 
          <input type="text" name="D01ISICUS" size="14" maxlength="12" value="<%= invTrade.getD01ISICUS()%>"readonly>
        </div>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de Cup&oacute;n</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="18%" > 
              <div align="right">Tipo de Pago :</div>
            </td>
            <td nowrap colspan="3" width="82%" > 
              <input type="radio" name="E01SCHTYP" value="I" <% if (invTrade.getE01SCHTYP().equals("I")) out.print("checked"); %>>
              Interes 
              <input type="radio" name="E01SCHTYP" value="C" <% if (invTrade.getE01SCHTYP().equals("C")) out.print("checked"); %>>
              Capital 
              <input type="radio" name="E01SCHTYP" value="D" <% if (invTrade.getE01SCHTYP().equals("D")) out.print("checked"); %>>
              Dividendos <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap colspan="3" width="82%" > 
              <input type="text" name="E01SCHAM1" size="15" maxlength="15" value="<%= invTrade.getE01SCHAM1()%>" 
				onKeyPress="enterDecimal()">
              <input type="radio" name="E01SCHPFO" value="F" <% if (invTrade.getE01SCHPFO().equals("F")) out.print("checked"); %>>
              Fijo 
              <input type="radio" name="E01SCHPFO" value="%" <% if (invTrade.getE01SCHPFO().equals("%")) out.print("checked"); %>>
              Porciento <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="18%" > 
              <div align="right">Fecha de Pago :</div>
            </td>
            <td nowrap colspan="3" width="82%" > 
              <input type="text" name="E01SCHPA1" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA1()%>">
              <input type="text" name="E01SCHPA2" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA2()%>">
              <input type="text" name="E01SCHPA3" size="3" maxlength="2" value="<%= invTrade.getE01SCHPA3()%>">
              <a href="javascript:DatePicker(document.forms[0].E01SCHPA1,document.forms[0].E01SCHPA2,document.forms[0].E01SCHPA3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></td>
          </tr>
          <%if (invTrade.getD01ISIPTY().equals("EQT")){%> 
          <tr id="trclear"> 
            <td nowrap width="18%" > 
              <div align="right">Fecha Declarada :</div>
            </td>
            <td nowrap colspan="3" width="82%" > 
              <input type="text" name="E01SCHDE1" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE1()%>">
              <input type="text" name="E01SCHDE2" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE2()%>">
              <input type="text" name="E01SCHDE3" size="3" maxlength="2" value="<%= invTrade.getE01SCHDE3()%>">
              <a href="javascript:DatePicker(document.forms[0].E01SCHDE1,document.forms[0].E01SCHDE2,document.forms[0].E01SCHDE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="18%" > 
              <div align="right">Fecha de Grabaci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" width="82%" > 
              <input type="text" name="E01SCHRE1" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE1()%>">
              <input type="text" name="E01SCHRE2" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE2()%>">
              <input type="text" name="E01SCHRE3" size="3" maxlength="2" value="<%= invTrade.getE01SCHRE3()%>">
              <a href="javascript:DatePicker(document.forms[0].E01SCHRE1,document.forms[0].E01SCHRE2,document.forms[0].E01SCHRE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
		  <%}%> 
          <tr id="trclear">
            <td nowrap width="18%" >
              <div align="right">Tipo de Evento :</div>
            </td>
            <td nowrap colspan="3" width="82%" >
              <div align="left">
                <input type="radio" name="E01SCHFL0" value="" <% if (invTrade.getE01SCHFL0().equals("")) out.print("checked"); %> checked>
                Pago Normal 
                <input type="radio" name="E01SCHFL0" value="M" <% if (invTrade.getE01SCHFL0().equals("M")) out.print("checked"); %>>
                Al Vencimiento 
                <input type="radio" name="E01SCHFL0" value="C" <% if (invTrade.getE01SCHFL0().equals("C")) out.print("checked"); %>>
                Call</div>
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
  <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
 
  </form>
</body>
</html>
