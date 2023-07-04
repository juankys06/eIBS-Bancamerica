<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transaction Rejected Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<h3 align="center">Confirmación de Envio.
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="email_confirm.jsp, EGL0030"></h3> 
<hr size="4">

<% String batch = (session.getAttribute("batch") == null) ? "" : (String) session.getAttribute("batch"); %>

<FORM name="form" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEGL0030?SCREEN=3" >
  <table class="tableinfo">
    <tr > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">El Lote : <%= Util.formatCell(batch) %>, ha sido Aprobado y enviado via EMail!!</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">Espere unos segundos para mostrarle el reporte.</td>
          </tr>
         <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
          <tr>
            <td width="9%">&nbsp;</td>
            <td width="91%">&nbsp;</td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  <div align="center"> </div>
</form>

<SCRIPT LANGUAGE="javascript">
	function finish(){
		document.form.submit()
	}
	
	setTimeout(finish, 7000);
</SCRIPT>

</body>
</html>
