<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Certificados de Déposito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "cdFinish" class= "datapro.eibs.beans.EFT000010Message"  scope="session" />
 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	var myhtml = 
		 "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=42&E01DEAACC=<%= userPO.getIdentifier()%> TARGET=_blank>Contrato</A><BR> " +	
         "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
  builtNewMenu(myhtml);

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
 out.println("<SCRIPT> initMenu(); </SCRIPT>");  
%> 
<h3 align="center">Certificados de Dep&oacute;sito</h3>
<font size="3"><i><b><font size="3"><i><b><font face="Times New Roman" size="4"> 
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_finis.jsp,EDL0130"></font></b></i></font></b></i></font> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="38">
  <table class="tableinfo">
    <tr  > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="center"><font face="Times New Roman, Times, serif" size="3"><i><b> 
                <%= cdFinish.getE10BANKNM().trim()%> </b></i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"><font face="Arial" size="2">Certificado de Dep&oacute;sito 
              N&uacute;mero:</font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= cdFinish.getE10DEAACC().trim()%> </b></i></font></td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="left"><font face="Arial" size="2"> A favor de :</font><font face="Times New Roman, Times, serif" size="3"><i><b> 
                <%= cdFinish.getE10CUSNA1().trim()%> ,</b></i>por un valor de<i><b> 
                <%= Util.fcolorCCY(cdFinish.getE10DEAOAM().trim())%> </b></i></font></div>
            </td>
          </tr>
          <tr> 
            <td height="35" width="9%">&nbsp;</td>
            <td height="35" width="91%"><font face="Arial" size="2"> </font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              </b></i></font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= cdFinish.getE10LETOAM().trim()%> </b></i><i><b> </b></i></font></td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"><font face="Arial" size="2">con fecha de apertura:<i><b> 
              </b></i></font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= Util.formatDate(cdFinish.getE10DEAOD1().trim(),cdFinish.getE10DEAOD2().trim(),cdFinish.getE10DEAOD3().trim())%> 
              </b></i></font> <font face="Arial" size="2">y fecha de vencimiento:</font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= Util.formatDate(cdFinish.getE10DEAMD1().trim(),cdFinish.getE10DEAMD2().trim(),cdFinish.getE10DEAMD3().trim())%> 
              </b></i></font></td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"><font face="Arial" size="2">con un plazo de </font><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= cdFinish.getE10DEATRM().trim()%> - 
              <% if(cdFinish.getE10DEATRC().equals("Y")) out.print("Años");
              				else if(cdFinish.getE10DEATRC().equals("M")) out.print("Meses");
							else out.print("Dias");%>
              </b></i><font face="Arial" size="2">y tasa de inter&eacute;s:</font><i><b> 
              <%= cdFinish.getE10RATE().trim()%> </b></i></font> %</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="right"><font face="Times New Roman, Times, serif" size="3"><i><b>(<b><%= cdFinish.getE10STATUS().trim()%></b>) 
                </b></i></font></div>
            </td>
          </tr>
        </table>
        <p align="center"></p>
      </td>
    </tr>
  </table>
  <% 
    // This goes at the end of the page
    if ( (userPO.getPurpose().equals("NEW")) || (userPO.getPurpose().equals("MAINTENANCE")) ) {
		out.println("<p>");
		out.println("  <center>");
		out.println("    <input type=\"submit\" name=\"Enviar\" value=\"Aceptar\">");
		out.println("  </center>");
		out.println("<p>");
 	 }
 	 %> 
  <div align="center"> </div>
</form>
</body>
</html>
