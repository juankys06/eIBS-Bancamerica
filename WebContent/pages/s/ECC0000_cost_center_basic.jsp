<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Cost Center Descriptions Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cc" class="datapro.eibs.beans.ECC000001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body bgcolor="#FFFFFF">

 <%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Mantenimiento de Centros de Costo<BR>
Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cost_center_basic, ECC0000" ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECC0000" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
    <INPUT TYPE=HIDDEN NAME="OPT" VALUE="<%= userPO.getPurpose()%>">
  </p>
  <table  class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">

          <tr id="trdark">
            <td nowrap>
              <div align="right">Código de Banco :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CCDBNK" size="3" maxlength="2" value="<%= cc.getE01CCDBNK().trim()%>" <% if (!userPO.getPurpose().equals("N")) out.print("readonly");%>>
             </td>
          </tr>
          <tr id="trclear">
            <td nowrap>
              <div align="right">Número de Centro de Costo :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CCDCCN" size="10" maxlength="8" value="<%= cc.getE01CCDCCN().trim()%>" <% if (!userPO.getPurpose().equals("N")) out.print("readonly");%>>

            </td>
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Descripción :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CCDCCD" size="40" maxlength="35" value="<%= cc.getE01CCDCCD().trim()%>">

            </td>
          </tr>
<%--          <tr id="trclear">
            <td nowrap>
              <div align="right">Grupo :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CCDGRP" size="5" maxlength="4" value="<%= cc.getE01CCDGRP().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01CCDGRP','(')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap>
              <div align="right">Sub-Grupo :</div>
            </td>
            <td nowrap>
              <input type="text" name="E01CCDSBR" size="5" maxlength="4" value="<%= cc.getE01CCDSBR().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01CCDSBR',')')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
            </td>
          </tr>
--%>    


    </table>
      </td>
    </tr>
  </table>

  <br>
   <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>

