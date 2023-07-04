<html>
<head>
<title>Codigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"  scope="session" />

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


<H3 align="center">C&oacute;digos de Referencia del Sistema - Tipos de Garantia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_collateral_types_details.jsp, ESD0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
   <INPUT TYPE=HIDDEN NAME="E02CNOBNK" value="<%= currUser.getE01UBK()%>">
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E02CNORCD" size="6" maxlength="4" value="<%= refCodes.getE02CNORCD().trim()%>">
                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable Asociada :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCG" size="17" maxlength="16" value="<%= refCodes.getE02CNOSCG().trim()%>">
                <a href="javascript:GetLedger('E02CNOSCG',document.forms[0].E02CNOBNK.value,'','')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCY" size="4" maxlength="3" value="<%= refCodes.getE02CNOSCY().trim()%>"  >
                <a href="javascript:GetCurrency('E02CNOSCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Clase de Garant&iacute;a :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNODCC" size="3" maxlength="2" value="<%= refCodes.getE02CNODCC().trim()%>"  >
                <a href="javascript:GetCode('E02CNODCC','STATIC_cnof_coll_class_codes.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Meses a Reevaluar :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOFRP" size="3" maxlength="2" value="<%= refCodes.getE02CNOFRP().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">% de Previsi&oacute;n :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOCST" size="8" maxlength="7" value="<%= refCodes.getE02CNOCST().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">% M&iacute;nimo de Fogape :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E02CNOMID" size="7" maxlength="6" value="<%= refCodes.getE02CNOMID().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">% M&aacute;ximo de Fogape :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E02CNOMIC" size="7" maxlength="6" value="<%= refCodes.getE02CNOMIC().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">L&iacute;mite de Fogape :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left">
                <input type="text" name="E02CNOCHG" size="17" maxlength="15" value="<%= refCodes.getE02CNOCHG().trim()%>"  >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </h4>  
  </form>
</body>
</html>
