<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Activos Fijos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fix" class="datapro.eibs.beans.EFIX03001Message"  scope="session" />

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
<h3 align="center">Referencias Cruzadas de Activos Fijos y Amortizaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fix_cross_reference.jsp, EFIX030"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.amort.JSEFIX030" >
  <input type=HIDDEN name="SCREEN" value="400">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" >
              <div align="right">N&uacute;mero de Cuenta Contable :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="text" name="E01FIXBNK" size="3" maxlength="2" readonly value="<%= fix.getE01FIXBNK()%>">
                <input type="text" name="E01FIXCCY" size="4" maxlength="3" readonly value="<%= fix.getE01FIXCCY()%>">
                <input type="text" name="E01FIXGLN" size="17" maxlength="16" value="<%= fix.getE01FIXGLN()%>" readonly>
                <input type="text" name="E01GLNDSC" size="45" maxlength="35" value="<%= fix.getE01GLNDSC()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Depreciaci&oacute;n Acumulada</h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Cuenta Contable a Debitar :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left">
                <input type="text" name="E01FIXDRA" size="17" maxlength="16" value="<%= fix.getE01FIXDRA()%>" >
                <a href="javascript:GetLedger('E01FIXDRA',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
                <input type="text" name="E01DRADSC" size="45" maxlength="35" value="<%= fix.getE01DRADSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right">Cuenta Contable a Acreditar :</div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="text" name="E01FIXCRA" size="17" maxlength="16" value="<%= fix.getE01FIXCRA()%>" >
              <a href="javascript:GetLedger('E01FIXCRA',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <input type="text" name="E01CRADSC" size="45" maxlength="35" value="<%= fix.getE01CRADSC()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Ajuste Costo por Inflaci&oacute;n </h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Cuenta Contable a Debitar :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="text" name="E01FIXDAI" size="17" maxlength="16" value="<%= fix.getE01FIXDAI()%>" >
                <a href="javascript:GetLedger('E01FIXDAI',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
                <input type="text" name="E01DAIDSC" size="45" maxlength="35" value="<%= fix.getE01DAIDSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">Cuenta Contable a Acreditar :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="text" name="E01FIXCAI" size="17" maxlength="16" value="<%= fix.getE01FIXCAI()%>" >
              <a href="javascript:GetLedger('E01FIXCAI',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <input type="text" name="E01CAIDSC" size="45" maxlength="35" value="<%= fix.getE01CAIDSC()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Ajuste Depreciaci&oacute;n Acumulada por Inflaci&oacute;n </h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Cuenta Contable a Debitar :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="text" name="E01FIXADD" size="17" maxlength="16" value="<%= fix.getE01FIXADD()%>" >
                <a href="javascript:GetLedger('E01FIXADD',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
                <input type="text" name="E01ADDDSC" size="45" maxlength="35" value="<%= fix.getE01ADDDSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">Cuenta Contable a Acreditar :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="text" name="E01FIXADC" size="17" maxlength="16" value="<%= fix.getE01FIXADC()%>" >
              <a href="javascript:GetLedger('E01FIXADC',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <input type="text" name="E01ADCDSC" size="45" maxlength="35" value="<%= fix.getE01ADCDSC()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Activos en Arrendamiento</h4>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Per Contra Activo Fijo :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="text" name="E01FIXALD" size="17" maxlength="16" value="<%= fix.getE01FIXALD()%>" >
                <a href="javascript:GetLedger('E01FIXALD',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
                <input type="text" name="E01ALDDSC" size="45" maxlength="35" value="<%= fix.getE01ALDDSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">Contra Depreciaci&oacute;n :</div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="text" name="E01FIXALC" size="17" maxlength="16" value="<%= fix.getE01FIXALC()%>" >
              <a href="javascript:GetLedger('E01FIXALC',document.forms[0].E01FIXBNK.value,document.forms[0].E01FIXCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <input type="text" name="E01ALCDSC" size="45" maxlength="35" value="<%= fix.getE01ALCDSC()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%">
 <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
  </table>
  </form>
</body>
</html>
