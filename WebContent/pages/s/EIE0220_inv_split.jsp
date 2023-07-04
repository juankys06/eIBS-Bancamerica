<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Split</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invSplit" class="datapro.eibs.beans.EIE022001Message"  scope="session" />

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
  <h3>Informaci&oacute;n del Split<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_split.jsp,EIE0220"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0220" >
  <h4> 
    <input type="hidden" name="SCREEN"  value="2" >
    Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].E01ISIIIC.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento </a> :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01ISIIIC" readonly size="4" maxlength="3" value="<%= invSplit.getE01ISIIIC()%>" onKeyPress="enterDecimal()">
              <input type="text" name="E01ISIDSC" readonly size="35" maxlength="30" value="<%= invSplit.getE01ISIDSC()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01ISINUM" readonly size="17" maxlength="15" value="<%= invSplit.getE01ISINUM()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="17%" >
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01ISIPTY" readonly size="15" maxlength="15" value="<%= invSplit.getE01ISIPTY()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Cantidad :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01QUANTY" readonly size="17" maxlength="15" value="<%= invSplit.getE01QUANTY()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Precio por Unidad :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01UNTPRI" readonly size="17" maxlength="15" value="<%= invSplit.getE01UNTPRI()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" name="E01NOMVAL" readonly size="17" maxlength="15" value="<%= invSplit.getE01NOMVAL()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Factor :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" name="E01SCRFAC"  size="11" maxlength="10" value="<%= invSplit.getE01SCRFAC()%>" onKeyPress="enterDecimal()">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>
    
    <br>
  </h4>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
        </div>
      </td>
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
