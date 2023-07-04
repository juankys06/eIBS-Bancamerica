<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cambio de Tasa de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnRate" class= "datapro.eibs.beans.EDL015206Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(ln_a_opt);


</SCRIPT>
</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Tasa de Cambio <%= userPO.getHeader4().trim()%></h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ap_xchg_rate.jsp,EDL0140"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150A" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="14">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" readonly name="E06DEACUN" size="9" maxlength="9"  value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" readonly name="E06CUSNA1" size="45" maxlength="45"  value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" readonly name="E06DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" readonly name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" readonly name="E06DEAPRO" size="4" maxlength="4"  value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tasas</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right">Tasa Actual :</div>
            </td>
            <td width="61%"> 
              <input type="text" readonly name="E06OLDRTE" size="15" maxlength="15" value="<%= lnRate.getE06OLDRTE().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Nueva Tasa :</div>
            </td>
            <td width="61%"> 
              <input type="text" readonly name="E06NEWRTE" size="15" maxlength="15" value="<%= lnRate.getE06NEWRTE().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td width="61%">
              <input type="text" readonly name="E06TRNVD1" size="2" maxlength="2" value="<%= lnRate.getE06TRNVD1().trim()%>">
              <input type="text" readonly name="E06TRNVD2" size="2" maxlength="2" value="<%= lnRate.getE06TRNVD2().trim()%>">
              <input type="text" readonly name="E06TRNVD3" size="2" maxlength="2" value="<%= lnRate.getE06TRNVD3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td width="61%" > 
              <input type="text" readonly name="E06DEANR1" size="30" maxlength="30" value="<%= lnRate.getE06DEANR1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"></div>
            </td>
            <td width="61%"> 
              <input type="text" readonly name="E06DEANR2" size="30" maxlength="30" value="<%= lnRate.getE06DEANR2().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
