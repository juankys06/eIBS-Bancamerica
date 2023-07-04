<html>
<head>
<title>Codigos de Plásticos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="ccCodes" class="datapro.eibs.beans.ECC003002Message"  scope="session" />

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
<% 
	String isReadOnly = ""; 
	if (!userPO.getPurpose().equals("NEW")) isReadOnly = "readonly"; 
%>

<H3 align="center">C&oacute;digos de Plásticos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnofc_generic_details.jsp, ECC0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECC0030" >
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
                <input type="text" name="E02CCTTBL" size="3" maxlength="2" value="<%= ccCodes.getE02CCTTBL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E02CCTCOD" size="6" maxlength="4" value="<%= ccCodes.getE02CCTCOD().trim()%>" <%= isReadOnly %>>
                <input type="text" name="E02CCTDSC" size="36" maxlength="35" value="<%= ccCodes.getE02CCTDSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E02CCTREF" size="17" maxlength="16" value="<%= ccCodes.getE02CCTREF().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
