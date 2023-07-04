<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Tabla Parametros</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="7;url='javascript:finish();"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,java.math.*,com.datapro.eibs.parameters.loans.access.jdbc.bean.*,com.datapro.generic.beanutil.*" %>

<jsp:useBean id="insTbl" class="com.datapro.eibs.parameters.loans.access.jdbc.bean.CNTRLINS"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 function finish(){
  document.forms[0].submit();
  close();
 }
 function setLayout() {
		setCenterSize(470,420);
  }

 setTimeout("document.forms[0].submit();", 7000);
  
</SCRIPT>
</head>

<body onLoad="setLayout()">
 
<h3 align="center"><% if (request.getParameter("OPT").equals("M")) out.print("Mantenimiento"); else out.print("Nueva");%> Tabla de Cargos de Seguro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Loans_parameters_maint.jsp"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSInsurance" target="SELFRAME">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="OPT" value="">
  <INPUT TYPE=HIDDEN NAME="SELTYP" value="<%= insTbl.getINSCLS() %>">
  <INPUT TYPE=HIDDEN NAME="SELTBL" value="<%= insTbl.getINSTYP() %>">
  <table class="tableinfo">
    <tr > 
      <td> 
        <p align="center">&nbsp; </p>
        <table width="100%">
          
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">Tabla : <%= insTbl.getINSTYP() %>
            </td>
          </tr>
          <tr>
            <td width="8%">&nbsp;</td>
            <td width="92%">Descripcion : <%= insTbl.getINSDSC() %></td>
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
            <td width="92%">&nbsp;La <% if (request.getParameter("OPT").equals("N")) out.print("Nueva ");%>Tabla ha sido procesada satisfactoriamente.</td>
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
