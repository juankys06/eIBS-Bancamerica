<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "dcCodes" class= "datapro.eibs.beans.ESD000002Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">


	builtNewMenu(dc_d_inquiry);


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

<h3 align="center">Aprobaci&oacute;n de Cobranza Documentaria<br>C&oacute;digos Especiales
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="doc_codes.jsp, EDC0040" width="35" height="35"></h3>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0040" >

  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TBODY><TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>N&uacute;mero de Cobranza :</B></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E02ACC" size="14" maxlength="12" value="<%= dcCodes.getE02ACC().trim()%>" readonly>
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Producto :</B></DIV>
            </TD><TD nowrap width="16%"> 
              <DIV align="left"><B> 
                <INPUT type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </B> </DIV>
            </TD>
            
          </TR>
        </TBODY></TABLE>
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
              <input readonly  type="text" name="E02OFC" size="5" maxlength="4" value="<%= dcCodes.getE02OFC().trim()%>">
              <input readonly  type="text" name="D02OFC" size="40" maxlength="35" value="<%= dcCodes.getD02OFC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OFC','D02OFC','15')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02OF2" size="5" maxlength="4" value="<%= dcCodes.getE02OF2().trim()%>">
              <input readonly  type="text" name="D02OF2" size="40" maxlength="35" value="<%= dcCodes.getD02OF2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OF2','D02OF2','10')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02INC" size="5" maxlength="4" value="<%= dcCodes.getE02INC().trim()%>">
              <input readonly  type="text" name="D02INC" size="40" maxlength="35" value="<%= dcCodes.getD02INC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02INC','D02INC','09')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">L&iacute;nea de Negocio :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02BUC" size="5" maxlength="4" value="<%= dcCodes.getE02BUC().trim()%>">
              <input readonly  type="text" name="D02BUC" size="40" maxlength="35" value="<%= dcCodes.getD02BUC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02BUC','D02BUC','12')"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02GEC" size="5" maxlength="4" value="<%= dcCodes.getE02GEC().trim()%>">
              <input readonly  type="text" name="D02GEC" size="40" maxlength="35" value="<%= dcCodes.getD02GEC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GEC','D02GEC','03')"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02GRC" size="5" maxlength="4" value="<%= dcCodes.getE02GRC().trim()%>">
              <input readonly  type="text" name="D02GRC" size="40" maxlength="35" value="<%= dcCodes.getD02GRC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GRC','D02GRC','03')"></a> 
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Origen de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02ORG" size="5" maxlength="4" value="<%= dcCodes.getE02ORG().trim()%>">
              <input readonly  type="text" name="D02ORG" size="40" maxlength="35" value="<%= dcCodes.getD02ORG().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02ORG','D02ORG','18')"></a> 
              <%if(userPO.getType().trim().equals("I")){%>
              	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle">
              	<%}%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Destino de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02DST" size="5" maxlength="4" value="<%= dcCodes.getE02DST().trim()%>">
              <input readonly  type="text" name="D02DST" size="40" maxlength="35" value="<%= dcCodes.getD02DST().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02DST','D02DST','38')"></a> 
            <%if(!userPO.getType().trim().equals("I")){%>
              	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" align="absmiddle">
              	<%}%></td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 1 :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02UC1" size="5" maxlength="4" value="<%= dcCodes.getE02UC1().trim()%>">
              <input readonly  type="text" name="D02UC1" size="40" maxlength="35" value="<%= dcCodes.getD02UC1().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC1','D02UC1','2A')"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 2 :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02UC2" size="5" maxlength="4" value="<%= dcCodes.getE02UC2().trim()%>">
              <input readonly  type="text" name="D02UC2" size="40" maxlength="35" value="<%= dcCodes.getD02UC2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC2','D02UC2','2B')"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 3 :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02UC3" size="5" maxlength="4" value="<%= dcCodes.getE02UC3().trim()%>">
              <input readonly  type="text" name="D02UC3" size="40" maxlength="35" value="<%= dcCodes.getD02UC3().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC3','D02UC3','2C')"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 4 :</div>
            </td>
            <td nowrap width="70%"> 
              <input readonly  type="text" name="E02UC4" size="5" maxlength="6" value="<%= dcCodes.getE02UC4().trim()%>">
              <input readonly  type="text" name="D02UC4" size="40" maxlength="35" value="<%= dcCodes.getD02UC4().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC4','D02UC4','CA')"></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
</form>
</body>
</html>

