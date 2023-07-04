<html>
<head>
<title>Tasas de Cambio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

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


<H3 align="center">Mantenimiento de Cuentas entre Banco / Agencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="exchange_rate_details.jsp, EFE0220"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIB0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right"><b></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b>Banco</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b>Agencia</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b>Moneda</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" > 
              <div align="right"><b>Origen :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFB" size="3"  maxlength="2"  >
                </font></font></font> </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFR" size="3"  maxlength="4" >
                <a href="javascript:GetBranch('E02IBTDFR',document.forms[0].E02IBTDFB.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
                </font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFC" size="4"  maxlength="3" >
                <a href="javascript:GetCurrency('E02IBTDFC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></font></font></font></b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right"><b>Destino :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDTB" size="3"  maxlength="2" >
                </font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDTR" size="3"  maxlength="4" >
                <a href="javascript:GetBranch('E02IBTDTR',document.forms[0].E02IBTDTB.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a></font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>  
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
