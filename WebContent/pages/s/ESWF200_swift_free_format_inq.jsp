<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Swift Free Format Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="swff" class="datapro.eibs.beans.ESWF20002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {

switch (op) {
		case 8 : // Logs
 			document.forms[0].SCREEN.value = '18';
	 		document.forms[0].submit();
	 		break
		default :
 			document.forms[0].submit();
	}
	      
 }
</script>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Copia Formatos Libres SWIFT <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="swift_free_format_inq.jsp,ESWF200"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0200" >

    <input type=HIDDEN name="SCREEN" value="5">
    <INPUT TYPE=HIDDEN NAME="SWIFTFREE" VALUE="Y">
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESW2SWI" size="15" maxlength="15" value="<%= swff.getESW2SWI()%>" readonly>
            </td>
            <td nowrap width="26%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap > 
              <input type="text" name="ESW2REF" size="15" maxlength="15" value="<%= swff.getESW2REF()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Formato Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESW2FTM" size="15" maxlength="15" value="<%= swff.getESW2FTM()%>" readonly>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Identificaci&oacute;n de Usuario :</div>
            </td>
            <td nowrap >
              <input type="text" name="ESW2USR" size="15" maxlength="15" value="<%= swff.getESW2USR()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="center">Comentarios</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <textarea name="ESW2REC" cols="60" readonly="readonly" rows="35"><%= swff.getESW2REC()%></textarea>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Copiar">
        </div>
      </td>
      <td width="33%">
        <div align="center">
          <input id="EIBSBTN" type=button name="Submit3" value="Logs" onClick ="goAction(8);">
        </div>
      </td>
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Submit2" value="Salir" onClick ="top.close();">
        </div>
      </td>
    </tr>
  </table>

</form>
</body>
</html>
