<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Credit Cards Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh"CONTENT="7;url='<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010?SCREEN=3'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "ccNew" class= "datapro.eibs.beans.ECC001001Message" scope="session" />
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
<h3 align="center">Confirmación Tarjetas de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_confirm.jsp,ECC0010"></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
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
            <td width="91%">Número de Cuenta : <%= datapro.eibs.master.Util.justifyRight(ccNew.getE01CCMACC().trim(),9)%> 
              <input type=HIDDEN name="E01CCMACC" value="<%= ccNew.getE01CCMACC()%>">
            </td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="left"> A favor de  : <%= ccNew.getE01CUSNA1().trim()%> </div>
            </td>
          </tr>
          <tr>
            <td height="35" width="9%">&nbsp;</td>
            <td height="35" width="91%">
            </td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"> con día de apertura : <%= Util.formatDate(ccNew.getE01CCMOPM().trim(),ccNew.getE01CCMOPD().trim(),ccNew.getE01CCMOPY().trim())%> 
               y día de expiración : <%= Util.formatDate(ccNew.getE01CCMEXM().trim(),ccNew.getE01CCMEXD().trim(),ccNew.getE01CCMEXY().trim())%> 
            </td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"></td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="center">La Tarjeta de Crédito ha sido procesada satisfactoriamente, <BR>
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
