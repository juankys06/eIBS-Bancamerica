<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Abonos Masivos, Proceso Manual - Aprobación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 }


 
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
<h3 align="center">Abonos Masivos, Proceso Manual - Aprobación</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_conf_sch,EDL1112"> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1112" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
  <table width="90%" class="tableinfo">
    <tr bordercolor="#FFFFFF" > 
      <td height="409">
        <table width="100%">
          <tr> 
            <td colspan="2"><div align="center"><font size="2">Las transacciones de pago han sido generadas</font></div></td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
    <p>&nbsp;</p>
  <table class="tbenter" width="50%" border="0" align="center">
    <tr> 

    <TD ALIGN=CENTER CLASS=tdbkg><a href="javascript:finish()">Salir</a></TD>

     </tr>
  </table>
    <p>&nbsp; </p>
  </form>
</body>
</html>
