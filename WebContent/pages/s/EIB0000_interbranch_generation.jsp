<html>
<head>
<title>Tasas de Cambio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="branch" class="datapro.eibs.beans.EIB000001Message"  scope="session" />

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


<H3 align="center">Generaci&oacute;n Masiva de Cuentas entre Banco / Agencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="interchange_generation.jsp, EIB0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIB0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="50%" > 
              <div align="center"></div>
            </td>
            <td nowrap width="25%" > 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="25%" > 
              <div align="center">Moneda</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="50%" > 
              <div align="right"><b>Origen :</b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01IBTDFB" size="3"  maxlength="2" value="<%= branch.getE01IBTDFB().trim()%>">
                </font></font></font> </b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01IBTDFC" size="4"  maxlength="3" value="<%= branch.getE01IBTDFC().trim()%>">
                <a href="javascript:GetCurrency('E01IBTDFC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></font></font></font></b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="50%" > 
              <div align="right"><b>Destino :</b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01IBTDTB" size="3"  maxlength="2" value="<%= branch.getE01IBTDTB().trim()%>" >
                </font></font></font></b></div>
            </td>
            <td nowrap width="25%" > 
              <div align="center"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="50%"> 
              <div align="right">Cuenta por Cobrar :</div>
            </td>
            <td nowrap width="50%"> 
              <div align="left"> 
                <input type="text" name="E01IBTDFA" size="17" maxlength="16" value="<%= branch.getE01IBTDFA().trim()%>">
                <a href="javascript:GetLedger('E01IBTDFA',document.forms[0].E01IBTDFB.value,document.forms[0].E01IBTDFC.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="50%"> 
              <div align="right">Cuenta por Pagar :</div>
            </td>
            <td nowrap width="50%"> 
              <input type="text" name="E01IBTDTA" size="17" maxlength="16" value="<%= branch.getE01IBTDTA().trim()%>">
              <a href="javascript:GetLedger('E01IBTDTA',document.forms[0].E01IBTDTB.value,'','')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="50%" > 
              <div align="right">Tipo de Asiento :</div>
            </td>
            <td nowrap width="50%" height="19"><b><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" name="E01IBTENT" maxlength="1" size="2" value="<%= branch.getE01IBTENT().trim()%>" >
              <a href="javascript:GetCode('E01IBTENT','STATIC_branch_ini.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a></font></font></font></b> 
            </td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="50%" > 
              <div align="right">Generar Distintas Cuentas por Sucursales</div>
            </td>
            <td nowrap width="50%"> 
              <input type="radio" name="E01INCBRN" value="Y"  <%if (branch.getE01INCBRN().equals("Y")) out.print("checked"); %>>
              S&iacute; 
              <input type="radio" name="E01INCBRN" value="N"  <%if (branch.getE01INCBRN().equals("N")) out.print("checked"); %>>
              No</td>
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
