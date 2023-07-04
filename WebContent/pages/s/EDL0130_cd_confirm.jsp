<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Certificados de Déposito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="7;url='javascript:document.forms[0].submit();"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "cdFinish" class= "datapro.eibs.beans.EFT000010Message"  scope="session" />
 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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
<h3 align="center">Certificados de Dep&oacute;sito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_confirm.jsp,EDL0130"></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="48">
  <table class="tableinfo">
    <tr > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="center"> <%= cdFinish.getE10BANKNM().trim()%> </div>
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">&nbsp;</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">Certificado de Dep&oacute;sito N&uacute;mero: <%= datapro.eibs.master.Util.justifyRight(cdFinish.getE10DEAACC().trim(),12)%> 
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">&nbsp;</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="left"> A favor de : <%= cdFinish.getE10CUSNA1().trim()%> 
                ,por un valor de <%= Util.fcolorCCY(cdFinish.getE10DEAOAM().trim())%> 
              </div>
            </td>
          </tr>
          <tr>
            <td height="35" width="8%">&nbsp;</td>
            <td height="35" width="92%"> <%= cdFinish.getE10LETOAM().trim()%> 
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">con fecha de apertura: <%= Util.formatDate(cdFinish.getE10DEAOD1().trim(),cdFinish.getE10DEAOD2().trim(),cdFinish.getE10DEAOD3().trim())%> 
              y fecha de vencimiento: <%= Util.formatDate(cdFinish.getE10DEAMD1().trim(),cdFinish.getE10DEAMD2().trim(),cdFinish.getE10DEAMD3().trim())%> 
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">con fecha valor futuro: <%= Util.formatDate(cdFinish.getE10DEAMD1().trim(),cdFinish.getE10DEAMD2().trim(),cdFinish.getE10DEAMD3().trim())%> 
            </td>
          </tr>          
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">con un plazo de <%= cdFinish.getE10DEATRM().trim()%> 
              - 
              <% if(cdFinish.getE10DEATRC().equals("Y")) out.print("Años");
              				else if(cdFinish.getE10DEATRC().equals("M")) out.print("Meses");
							else out.print("Dias");%>
              y tasa de inter&eacute;s: <%= cdFinish.getE10RATE().trim()%> %</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">&nbsp;</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="left">El Certificado de Dep&oacute;sito ha sido procesado 
                satisfactoriamente. Por favor espere...</div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <div align="center">
    <p>&nbsp;</p>
    <p>&nbsp; </p>
  </div>
</form>
</body>
</html>
