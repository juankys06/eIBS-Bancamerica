<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtCodes" class= "datapro.eibs.beans.ESD000002Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_m_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_m_opt);
<%   
}
%>

</SCRIPT>
</head>
<body bgcolor="#FFFFFF">

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<h3 align="center">C&oacute;digos de Clasificaci&oacute;n</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_codes.jsp,EDD0000" width="35" height="35"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="14">
  </p>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= rtCodes.getE02ACC().trim()%>" readonly>
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
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
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
              <div align="right">Oficial Principal :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= rtCodes.getE02OFC().trim()%>">
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= rtCodes.getD02OFC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OFC','D02OFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" height="37"> 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%" height="37"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= rtCodes.getE02OF2().trim()%>">
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= rtCodes.getD02OF2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OF2','D02OF2','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="4" value="<%= rtCodes.getE02INC().trim()%>">
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= rtCodes.getD02INC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02INC','D02INC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Linea de Negocio :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= rtCodes.getE02BUC().trim()%>">
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= rtCodes.getD02BUC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02BUC','D02BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= rtCodes.getE02GEC().trim()%>">
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= rtCodes.getD02GEC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GEC','D02GEC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" value="<%= rtCodes.getE02GRC().trim()%>">
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= rtCodes.getD02GRC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GRC','D02GRC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Canal de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SCH" size="5" maxlength="4" value="<%= rtCodes.getE02SCH().trim()%>">
              <input type="text" name="D02SCH" size="40" maxlength="35" value="<%= rtCodes.getD02SCH().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02SCH','D02SCH','62')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Fuentes de Informacion de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SST" size="5" maxlength="4" value="<%= rtCodes.getE02SST().trim()%>">
              <input type="text" name="D02SST" size="40" maxlength="35" value="<%= rtCodes.getD02SST().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02SST','D02SST','65')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Relación SIB :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC1" size="5" maxlength="4" value="<%= rtCodes.getE02UC1().trim()%>">
              <input type="text" name="D02UC1" size="40" maxlength="35" value="<%= rtCodes.getD02UC1().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC1','D02UC1','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"><div align="right">Clasificación SIB :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC2" size="5" maxlength="4" value="<%= rtCodes.getE02UC2().trim()%>">
              <input type="text" name="D02UC2" size="40" maxlength="35" value="<%= rtCodes.getD02UC2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC2','D02UC2','2B')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Actividad :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= rtCodes.getE02UC3().trim()%>">
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= rtCodes.getD02UC3().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC3','D02UC3','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Planilla :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC4" size="5" maxlength="6" value="<%= rtCodes.getE02UC4().trim()%>">
              <input type="text" name="D02UC4" size="40" maxlength="35" value="<%= rtCodes.getD02UC4().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC4','D02UC4','CA')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
</form>
</body>
</html>

