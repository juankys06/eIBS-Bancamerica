
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Formulario de Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage" scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	var myhtml = 
		 "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=900 TARGET=_blank>Formulario</A><BR> " +	
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
<h3 align="center">Cliente</h3>
<font size="3"><i><b><font size="3"><i><b><font face="Times New Roman" size="4"> 
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_report_summary.jsp,ESD0080"></font></b></i></font></b></i></font> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="38">
  <table class="tableinfo">
    <tr  > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%"> 
              <div align="center"><font face="Times New Roman, Times, serif" size="3"><i><b> 
                </b></i></font></div>
            </td>
          </tr>
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="42%">
              <div align="right"><font face="Arial" size="2">Cliente N&uacute;mero:</font></div>
            </td>
            <td width="58%"><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= userPO.getIdentifier() %> </b></i></font></td>
          </tr>
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="42%">
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td width="58%"> 
              <div align="left"><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= userPO.getHeader2() %> </b></i></font>
            </td>
          </tr>
          <tr> 
            <td height="35" width="42%">&nbsp;</td>
            <td height="35" width="58%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="42%">
              <div align="right">Nombre : </div>
            </td>
            <td width="58%"><font face="Times New Roman, Times, serif" size="3"><i><b> 
              <%= userPO.getHeader3() %> </b></i></font></td>
          </tr>
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%">&nbsp;</td>
          </tr>
          <tr> 
            <td width="42%">&nbsp;</td>
            <td width="58%"> 
              <div align="right"><font face="Times New Roman, Times, serif" size="3"><i><b> 
                </b></i></font></div>
            </td>
          </tr>
        </table>
        <p align="center"></p>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
