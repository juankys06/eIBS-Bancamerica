<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Certificados de Déposito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="7;url='javascript:finish();"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "gLedger" class= "datapro.eibs.beans.EGL034001Message"  scope="session" />
 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/s/EGL0340_gledger_enter.jsp";
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
<h3 align="center">Maestro de Contabilidad General <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ledger_confirm.jsp,EGL0340"></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0340" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <table class="tableinfo">
    <tr > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">&nbsp;</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">Cuenta Contable : <%= gLedger.getE01GLMBNK().trim()%> - <%= gLedger.getE01GLMCCY().trim()%> - <%= gLedger.getE01GLMGLN().trim()%>
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">Descripción : <%= gLedger.getE01GLMDSC().trim()%> </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="left"></div>
            </td>
          </tr>
                    <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"></td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"></td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">&nbsp;El Maestro de Contabilidad General ha sido procesado satisfactoriamente.</td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%"> 
              <div align="left"> Por favor espere...</div>
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  
</form>
</body>
</html>
