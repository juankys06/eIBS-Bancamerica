<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "rtCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

</SCRIPT>

<body bgcolor="#FFFFFF">

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<h3 align="center">C&oacute;digos de Clasificaci&oacute;n</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_codes.jsp,EDD0000"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="35">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>C&oacute;digos de Clasificaci&oacute;n</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="43"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" height="118">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Principal:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= rtCodes.getE02OFC().trim()%>" readonly>
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= rtCodes.getD02OFC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" height="37"> 
              <div align="right">Oficial Secundario:</div>
            </td>
            <td nowrap width="70%" height="37"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= rtCodes.getE02OF2().trim()%>" readonly>
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= rtCodes.getD02OF2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="4" value="<%= rtCodes.getE02INC().trim()%>" readonly>
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= rtCodes.getD02INC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Linea de Negocio:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= rtCodes.getE02BUC().trim()%>" readonly>
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= rtCodes.getD02BUC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= rtCodes.getE02GEC().trim()%>" readonly>
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= rtCodes.getD02GEC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Riesgo:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" value="<%= rtCodes.getE02GRC().trim()%>" readonly>
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= rtCodes.getD02GRC().trim()%>" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Canal de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SCH" size="5" maxlength="4" value="<%= rtCodes.getE02SCH().trim()%>" readonly>
              <input type="text" name="D02SCH" size="40" maxlength="35" value="<%= rtCodes.getD02SCH().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Fuentes de Informacion de Venta :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SST" size="5" maxlength="4" value="<%= rtCodes.getE02SST().trim()%>" readonly>
              <input type="text" name="D02SST" size="40" maxlength="35" value="<%= rtCodes.getD02SST().trim()%>" readonly>
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Relación SIB :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC" size="5" maxlength="6" value="<%= rtCodes.getE02UC1().trim()%>" readonly>
              <input type="text" name="D02UC1" size="40" maxlength="35" value="<%= rtCodes.getD02UC1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Clasificación SIB :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC2" size="5" maxlength="4" value="<%= rtCodes.getE02UC2().trim()%>" readonly>
              <input type="text" name="D02UC2" size="40" maxlength="35" value="<%= rtCodes.getD02UC2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Actividad :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= rtCodes.getE02UC3().trim()%>" readonly>
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= rtCodes.getD02UC3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Planilla:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC4" size="5" maxlength="6" value="<%= rtCodes.getE02UC4().trim()%>" readonly>
              <input type="text" name="D02UC4" size="40" maxlength="35" value="<%= rtCodes.getD02UC4().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

