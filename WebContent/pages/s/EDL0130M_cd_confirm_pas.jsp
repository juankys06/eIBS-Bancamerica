<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Deals</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh"CONTENT="7;url='<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEWD0326BO?SCREEN=1'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "cdMant" class= "datapro.eibs.beans.EDL013001Message" scope="session" />
 <jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 }

 setTimeout("document.forms[0].submit();", 7000);
 
</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>
<h3 align="center">Confirmaci&oacute;n - Contrato <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_confirm.jsp,EDL0130M"></h3> 
<hr size="4">
 <FORM >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="48">
  <table class="tableinfo">
    <tr > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="center"> </div>
            </td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">N&uacute;mero de Contrato : <%= cdMant.getE01DEAACC().trim()%> 
            </td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">
              <div align="left">A favor de : <%= cdMant.getE01CUSNA1().trim()%> 
                , y un valor nominal de $ <%= Util.fcolorCCY(cdMant.getE01DEAOAM().trim())%> 
              </div>
            </td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> con fecha de apertura : <%= Util.formatDate(cdMant.getE01DEAOD1().trim(),cdMant.getE01DEAOD2().trim(),cdMant.getE01DEAOD3().trim())%> 
              y fecha de vencimiento: <%= Util.formatDate(cdMant.getE01DEAMD1().trim(),cdMant.getE01DEAMD2().trim(),cdMant.getE01DEAMD3().trim())%> 
            </td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="center">El contrato ha sido procesado satisfactoriamente, 
                por favor espere...</div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  <div align="center"> </div>
</form>
</body>
</html>
