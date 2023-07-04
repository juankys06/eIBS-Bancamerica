<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "lnCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	builtNewMenu(dft_i_opt);
	initMenu();
</SCRIPT>

<body nowrap bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">C&oacute;digos de Clasificaci&oacute;n <%= userPO.getHeader4().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_codes.jsp,EDL0800" ></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="16">
  </p>
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>C&oacute;digos Especiales</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Principal :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= lnCodes.getE02OFC().trim()%>" readonly>
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= lnCodes.getD02OFC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= lnCodes.getE02OF2().trim()%>" readonly>
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= lnCodes.getD02OF2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="4" value="<%= lnCodes.getE02INC().trim()%>" readonly>
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= lnCodes.getD02INC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" value="<%= lnCodes.getE02GRC().trim()%>" readonly>
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= lnCodes.getD02GRC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="3%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= lnCodes.getE02GEC().trim()%>" readonly>
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= lnCodes.getD02GEC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GEC','D02GEC','03')"></a> 
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Negocio :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= lnCodes.getE02BUC().trim()%>" readonly>
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= lnCodes.getD02BUC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Origen de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02ORG" size="5" maxlength="4" value="<%= lnCodes.getE02ORG().trim()%>" readonly>
              <input type="text" name="D02ORG" size="40" maxlength="35" value="<%= lnCodes.getD02ORG().trim()%>" readonly>
           </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Destino de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02DST" size="5" maxlength="4" value="<%= lnCodes.getE02DST().trim()%>" readonly>
              <input type="text" name="D02DST" size="40" maxlength="35" value="<%= lnCodes.getD02DST().trim()%>" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Canal de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SCH" size="5" maxlength="4" value="<%= lnCodes.getE02SCH().trim()%>" readonly>
              <input type="text" name="D02SCH" size="40" maxlength="35" value="<%= lnCodes.getD02SCH().trim()%>" readonly>
           </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Fuentes de Informaci&oacute;n de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SST" size="5" maxlength="4" value="<%= lnCodes.getE02SST().trim()%>" readonly>
              <input type="text" name="D02SST" size="40" maxlength="35" value="<%= lnCodes.getD02SST().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 3 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= lnCodes.getE02UC3().trim()%>" readonly>
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= lnCodes.getD02UC3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 4 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC4" size="5" maxlength="6" value="<%= lnCodes.getE02UC4().trim()%>" readonly>
              <input type="text" name="D02UC4" size="40" maxlength="35" value="<%= lnCodes.getD02UC4().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 5 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC5" size="5" maxlength="4" value="<%= lnCodes.getE02UC5().trim()%>" readonly>
              <input type="text" name="D02UC5" size="40" maxlength="35" value="<%= lnCodes.getD02UC5().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 6 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC6" size="5" maxlength="4" value="<%= lnCodes.getE02UC6().trim()%>" readonly>
              <input type="text" name="D02UC6" size="40" maxlength="35" value="<%= lnCodes.getD02UC6().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 7 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC7" size="5" maxlength="4" value="<%= lnCodes.getE02UC7().trim()%>" readonly>
              <input type="text" name="D02UC7" size="40" maxlength="35" value="<%= lnCodes.getD02UC7().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

