<html>
<head>
<title>Tasas de Cambio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="branch" class="datapro.eibs.beans.EIB000002Message"  scope="session" />

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


<H3 align="center">Mantenimiento de Cuentas entre Banco / Agencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="interbranch_maintenance.jsp, EIB0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIB0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="center"></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="20%" > 
              <div align="center">Agencia</div>
            </td>
            <td nowrap width="20%" > 
              <div align="center">Moneda</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" > 
              <div align="right"><b>Origen :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFB" size="3"  maxlength="2" value="<%= branch.getE02IBTDFB().trim()%>" readonly>
                </font></font></font> </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFR" size="3"  maxlength="4" value="<%= branch.getE02IBTDFR().trim()%>" readonly>
                </font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDFC" size="4"  maxlength="3" value="<%= branch.getE02IBTDFC().trim()%>" readonly>
                </font></font></font></b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right"><b>Destino :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDTB" size="3"  maxlength="2" value="<%= branch.getE02IBTDTB().trim()%>" readonly>
                </font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02IBTDTR" size="3"  maxlength="4" value="<%= branch.getE02IBTDTR().trim()%>" readonly>
                </font></font></font></b></div>
            </td>
            <td nowrap width="20%" > 
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
            <td nowrap width="40%" > 
              <div align="right">Cuenta por Cobrar :</div>
            </td>
            <td nowrap width="60%"> 
              <div align="left"> 
                <input type="text" name="E02IBTDFA" size="17" maxlength="16" value="<%= branch.getE02IBTDFA().trim()%>">
                <a href="javascript:GetLedger('E02IBTDFA',document.forms[0].E02IBTDFB.value,document.forms[0].E02IBTDFC.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                <input type="text" name="E02GLDDFA" size="30" maxlength="35" value="<%= branch.getE02GLDDFA().trim()%>">
				<input type="text" name="E02IBTDFF" maxlength="1" size="2" value="<%= branch.getE02IBTDFF().trim()%>" >
              	<a href="javascript:GetCode('E02IBTDFF','STATIC_par_acc_types.jsp')">
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
              </div>
              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" > 
              <div align="right">Cuenta por Pagar :</div>
            </td>
            <td nowrap width="60%" > 
              <input type="text" name="E02IBTDTA" size="17" maxlength="16" value="<%= branch.getE02IBTDTA().trim()%>">
              <a href="javascript:GetLedger('E02IBTDTA',document.forms[0].E02IBTDTB.value,'','')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <input type="text" name="E02GLDDTA" size="30" maxlength="35" value="<%= branch.getE02GLDDTA().trim()%>">
				<input type="text" name="E02IBTDTF" maxlength="1" size="2" value="<%= branch.getE02IBTDTF().trim()%>" >
              	<a href="javascript:GetCode('E02IBTDTF','STATIC_par_acc_types.jsp')">
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right">Tipo de Asiento :</div>
            </td>
            <td nowrap width="60%" ><b><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" name="E02IBTENT" maxlength="1" size="2" value="<%= branch.getE02IBTENT().trim()%>" >
              <a href="javascript:GetCode('E02IBTENT','STATIC_branch_ini.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a></font></font></font></b> 
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
