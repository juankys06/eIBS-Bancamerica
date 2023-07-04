<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Customer Settlement Limits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0325DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


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
<h3 align="center"> Confirmaci&oacute;n - L&iacute;mites Establecidos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_lim_ basic_confirm_new.jsp,EFE0325"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0325" >
  <p>
    <input type=HIDDEN name="SCREEN" value="4">
  </p>
  <p>&nbsp; </p>
  <table  class="tableinfo" width="545">
    <tr id="trdark"> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2" >Los l&iacute;mites establecidos para el cliente<%= fex.getD01FEOCP1()%> 
              han sido establecidos</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">L&iacute;mite de Moneda :</div>
            </td>
            <td nowrap width="56%" > <%= fex.getE01FEOCCY().trim()%> - <%= Util.fcolorCCY(fex.getE01FEOLIM())%> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%" > 
              <div align="right">Monto Utilizado :</div>
            </td>
            <td nowrap width="56%" ><%= Util.fcolorCCY(fex.getE01FEOUTI())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="44%" > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap width="56%" > <%= Util.formatDate(fex.getE01FEODT1(),fex.getE01FEODT2(),fex.getE01FEODT3())%> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <BR>
  </form>
</body>
</html>
