<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "clCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function setOptMenu(purpose) {
	if (purpose == "INQUIRY") {
    	builtNewMenu(colla_i_opt);
	} else {
		if (purpose == "APPROVAL_INQ") {
    		builtNewMenu(colla_A_opt);
    	} else {	
    		builtNewMenu(colla_M_opt);
    	}
    }		
    initMenu();
}
      
</SCRIPT>

<SCRIPT Language="Javascript">
	setOptMenu("<%= userPO.getPurpose() %>");
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

<h3 align="center">C&oacute;digos de Clasificaci&oacute;n
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_ap_codes.jsp,ERA0080" width="35" ></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0010 ">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">
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
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                </font></font></font></div>
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
              <input type="text" name="E02OFC" size="5" maxlength="4" readonly value="<%= clCodes.getE02OFC().trim()%>">
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= clCodes.getD02OFC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Secundario :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" readonly value="<%= clCodes.getE02OF2().trim()%>">
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= clCodes.getD02OF2().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" readonly value="<%= clCodes.getE02GEC().trim()%>">
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= clCodes.getD02GEC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Pa&iacute;s de Riesgo :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" readonly value="<%= clCodes.getE02GRC().trim()%>">
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= clCodes.getD02GRC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 1 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC1" size="5" maxlength="4"  readonly value="<%= clCodes.getE02UC1().trim()%>">
              <input type="text" name="D02UC1" size="40" maxlength="35" value="<%= clCodes.getD02UC1().trim()%>" readonly>
            </td>
          </tr>
          
          
            
          
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 2 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC2" size="5" maxlength="4" readonly value="<%= clCodes.getE02UC2().trim()%>">
              <input type="text" name="D02UC2" size="40" maxlength="35" value="<%= clCodes.getD02UC2().trim()%>" readonly>
            </td>
          </tr>
          
   
          
          
          
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 3 :</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" readonly value="<%= clCodes.getE02UC3().trim()%>">
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= clCodes.getD02UC3().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

