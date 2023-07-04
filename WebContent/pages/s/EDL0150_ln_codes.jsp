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

   builtNewMenu(ln_m_opt);

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
<h3 align="center">C&oacute;digos de Clasificaci&oacute;n <%= userPO.getHeader4().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_codes.jsp,EDL0150" ></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
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
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
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
              <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= lnCodes.getE02OFC().trim()%>">
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= lnCodes.getD02OFC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OFC','D02OFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= lnCodes.getE02OF2().trim()%>">
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= lnCodes.getD02OF2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OF2','D02OF2','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="4" value="<%= lnCodes.getE02INC().trim()%>">
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= lnCodes.getD02INC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02INC','D02INC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" value="<%= lnCodes.getE02GRC().trim()%>">
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= lnCodes.getD02GRC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GRC','D02GRC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="3%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= lnCodes.getE02GEC().trim()%>">
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= lnCodes.getD02GEC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GEC','D02GEC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Negocio :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= lnCodes.getE02BUC().trim()%>">
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= lnCodes.getD02BUC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02BUC','E02BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Origen de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02ORG" size="5" maxlength="4" value="<%= lnCodes.getE02ORG().trim()%>">
              <input type="text" name="D02ORG" size="40" maxlength="35" value="<%= lnCodes.getD02ORG().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02ORG','D02ORG','18')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
           </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Destino de los Fondos :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02DST" size="5" maxlength="4" value="<%= lnCodes.getE02DST().trim()%>">
              <input type="text" name="D02DST" size="40" maxlength="35" value="<%= lnCodes.getD02DST().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02DST','D02DST','38')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Canal de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SCH" size="5" maxlength="4" value="<%= lnCodes.getE02SCH().trim()%>">
              <input type="text" name="D02SCH" size="40" maxlength="35" value="<%= lnCodes.getD02SCH().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02SCH','D02SCH','62')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
           </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Fuentes de Informaci&oacute;n de Ventas :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02SST" size="5" maxlength="4" value="<%= lnCodes.getE02SST().trim()%>">
              <input type="text" name="D02SST" size="40" maxlength="35" value="<%= lnCodes.getD02SST().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02SST','D02SST','65')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>



          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Tipo de Actividad :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= lnCodes.getE02UC3().trim()%>">
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= lnCodes.getD02UC3().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC3','D02UC3','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
			  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Empresa de Cobro :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC4" size="5" maxlength="6" value="<%= lnCodes.getE02UC4().trim()%>">
              <input type="text" name="D02UC4" size="40" maxlength="35" value="<%= lnCodes.getD02UC4().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC4','D02UC4','2D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 5 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC5" size="5" maxlength="4" value="<%= lnCodes.getE02UC5().trim()%>">
              <input type="text" name="D02UC5" size="40" maxlength="35" value="<%= lnCodes.getD02UC5().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC5','D02UC5','2E')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC6" size="5" maxlength="4" value="<%= lnCodes.getE02UC6().trim()%>">
              <input type="text" name="D02UC6" size="40" maxlength="35" value="<%= lnCodes.getD02UC6().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC6','D02UC6','2F')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
               Si coloca DD actualizar EMPRESA DE COBRO
            </td>
           
            
          </tr>
           
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Otras Garant&iacute;as :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC7" size="5" maxlength="4" value="<%= lnCodes.getE02UC7().trim()%>">
              <input type="text" name="D02UC7" size="40" maxlength="35" value="<%= lnCodes.getD02UC7().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC7','D02UC7','2G')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  
</form>
</body>
</html>

