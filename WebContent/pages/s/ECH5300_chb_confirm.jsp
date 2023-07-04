<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Credit Cards Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh"CONTENT="7;url='<%=request.getContextPath()%>/pages/background.jsp'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
<h3 align="center">Confirmacion Solicitud  a Dispensadora<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_confirm.jsp,ECC0010"></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH5300" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
     <%
                chkbList.initRow();
                chkbList.getNextRow();
                datapro.eibs.beans.ECH530501Message msgCHKBSend = (datapro.eibs.beans.ECH530501Message) chkbList.getRecord();
     %>   
  
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
            <td width="91%">Numero de Cuenta: <%= datapro.eibs.master.Util.justifyRight(msgCHKBSend.getE01CHPACC().trim(),9)%> 
              <input type=HIDDEN name="E01CHPACC" value="<%= msgCHKBSend.getE01CHPACC()%>">
            </td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="left">A nombre de: <%= msgCHKBSend.getE01CHPNO1().trim()%> </div>
            </td>
          </tr>
          <tr>
            <td height="35" width="9%">&nbsp;</td>
            <td height="35" width="91%">
            </td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%"> 
              <div align="center">La solicitud ha sido procesada, favor espere...</div>
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
