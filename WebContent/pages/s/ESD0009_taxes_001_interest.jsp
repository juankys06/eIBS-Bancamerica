<html>
<head>
<title>Retenciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="taxes" class="datapro.eibs.beans.ESD000901Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Mantenimiento de Retenciones e Impuestos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="taxes_001_interest.jsp, ESD0009"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0009" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
   
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01TAXBNK" size="3" maxlength="2" value="<%= taxes.getE01TAXBNK().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo de Impuesto :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01TAXTTP" size="4" maxlength="3" value="<%= taxes.getE01TAXTTP().trim()%>">
                <input type="text" name="E01TAXDSC" size="41" maxlength="40" value="<%= taxes.getE01TAXDSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tasa de Retenci&oacute;n de Impuestos :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXPRC" size="6" maxlength="5" value="<%= taxes.getE01TAXPRC().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable de Retenci&oacute;n :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXGL1" size="17" maxlength="16" value="<%= taxes.getE01TAXGL1().trim()%>">
                <a href="javascript:GetLedger('E01TAXGL1',document.forms[0].E01TAXBNK.value,'','')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Monto del L&iacute;mite Superior :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01TAXUPL" size="17" maxlength="15" value="<%= taxes.getE01TAXUPL().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Monto del L&iacute;mite Inferior :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXLLM" size="17" maxlength="15" value="<%= taxes.getE01TAXLLM().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tasa L&iacute;mite de Control :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01TAXRTL" size="7" maxlength="6" value="<%= taxes.getE01TAXRTL().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">% de Retenci&oacute;n Menor al L&iacute;mite 
                :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXART" size="7" maxlength="6" value="<%= taxes.getE01TAXART().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tasa Adicional No Residentes :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXADR" size="7" maxlength="6" value="<%= taxes.getE01TAXADR().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">C&oacute;digo de Pa&iacute;s (Instalaci&oacute;n) 
                :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E01TAXCTY" size="5" maxlength="4" value="<%= taxes.getE01TAXCTY().trim()%>"  >
				<a href="javascript:GetCodeCNOFC('E01TAXCTY','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF">
      <td width="33%">&nbsp;</td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
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
