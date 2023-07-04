<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Report Confirm</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">



<!--meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=1&approv=A'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
 self.window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
 
 }
 setTimeout("document.forms[0].submit();", 7000);
 
</SCRIPT>

</head>

<body>

 
<h3 align="center">Priorización de Transferencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_confirm, EPR1080" ></h3>
<hr size="4">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 } else {
%>
	<table class="tableinfo" width="700" height="300">
	  <tr >       
    	<td> 
	      <table   width="100%">
    	    <tr > 
        	  <td nowrap>
            	<div align="center"></div>
		            <p align="center">La transferencia fue modificada con éxito ...</p>
        	  </td>
	        </tr>
    	  </table>
	      <div align="center"></div>
    	  </td>
	    </tr>
	  </table>
<% 
 }
%>
</body>
</html>
