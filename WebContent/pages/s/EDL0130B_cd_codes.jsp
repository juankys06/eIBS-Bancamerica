<html>
<head>
<title>Special Codes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "cdCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader3().equals("CDS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(cd_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("TDS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(td_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("FFS") && !(userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ff_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("TPS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(tp_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("ACS")&& !(userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ac_bo_act_opt);
<%   
}

else if ( userPO.getHeader3().equals("CDS") && (userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(cd_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("TDS") && (userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(td_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("FFS") && (userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ff_bo_act_opt);
<%   
}
else if ( userPO.getHeader3().equals("TPS") && !(userPO.getHeader16().equals("N"))) {
%>
	builtNewMenu(tp_bo_opt);
<%   
}
else if ( userPO.getHeader3().equals("ACS")&& (userPO.getHeader16().equals("N")) ) {
%>
	builtNewMenu(ac_bo_act_opt);
<%   
}
%>   


</SCRIPT>

<body nowrap bgcolor="#FFFFFF">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu();  </SCRIPT>");

%> 
<h3 align="center">C&oacute;digos Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_codes.jsp,EDL0130B" width="35" ></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="62">
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
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
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>C&oacute;digos Especiales</h4>
  <table class="tableinfo" >
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Principal :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" size="5" maxlength="3" value="<%= cdCodes.getE02OFC().trim()%>">
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= cdCodes.getD02OFC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OFC','D02OFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OF2" size="5" maxlength="3" value="<%= cdCodes.getE02OF2().trim()%>">
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= cdCodes.getD02OF2().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02OF2','D02OF2','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="3" value="<%= cdCodes.getE02GEC().trim()%>">
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= cdCodes.getD02GEC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GEC','D02GEC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="3" value="<%= cdCodes.getE02GRC().trim()%>">
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= cdCodes.getD02GRC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02GRC','D02GRC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="3" value="<%= cdCodes.getE02INC().trim()%>">
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= cdCodes.getD02INC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02INC','D02INC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">C&oacute;digo de Negocio :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="3" value="<%= cdCodes.getE02BUC().trim()%>">
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= cdCodes.getD02BUC().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02BUC','E02BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 3 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="3" value="<%= cdCodes.getE02UC3().trim()%>">
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= cdCodes.getD02UC3().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC3','D02UC3','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 5 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC5" size="5" maxlength="3" value="<%= cdCodes.getE02UC5().trim()%>">
              <input type="text" name="D02UC5" size="40" maxlength="35" value="<%= cdCodes.getD02UC5().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC5','D02UC5','2E')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 6 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC6" size="5" maxlength="3" value="<%= cdCodes.getE02UC6().trim()%>">
              <input type="text" name="D02UC6" size="40" maxlength="35" value="<%= cdCodes.getD02UC6().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC6','D02UC6','2F')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 7 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC7" size="5" maxlength="3" value="<%= cdCodes.getE02UC7().trim()%>">
              <input type="text" name="D02UC7" size="40" maxlength="35" value="<%= cdCodes.getD02UC7().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E02UC7','D02UC7','2G')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
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

