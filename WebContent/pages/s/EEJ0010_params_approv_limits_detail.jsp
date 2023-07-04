<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Aprobaci&oacute;n de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="clasific" class="datapro.eibs.beans.EEJ001001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }       
%>

</head>
<body>
<h3 align="center">Aprobacion de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_approv_limits_detail.jsp, EEJ0010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0010" >
  <input type=hidden name="SCREEN" value="1">
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>Tipo de Agrupaci&oacute;n :
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJLGRT" size="4" maxlength="4" value="<%= clasific.getE01EJLGRT() %>" readonly>
              <input type="text" name="E01EJLGRN" size="35" maxlength="35" value="<%= clasific.getE01EJLGRN() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right>C&oacute;digo de Agrupaci&oacute;n :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLGRC" size="4" maxlength="4" value="<%= clasific.getE01EJLGRC()%>" readonly>
               	<input type="text" name="E01EJLGRM" size="35" maxlength="35" value="<%= clasific.getE01EJLGRM() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>C&oacute;digo de Jerarqu&iacute;a :
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJLJRQ" size="4" maxlength="4" value="<%= clasific.getE01EJLJRQ()%>" readonly>
              <input type="text" name="E01EJLJRN" size="35" maxlength="35" value="<%= clasific.getE01EJLJRN() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right>Moneda :
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJLCCY" size="4" maxlength="3" value="<%= clasific.getE01EJLCCY()%>" readonly>
              <input type="text" name="E01EJLCCN" size="35" maxlength="35" value="<%= clasific.getE01EJLCCN() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><h4> Operaciones Activas </h4>
            </td>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right>Puntos Persona Natural :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLRTN" size="12" maxlength="9" value="<%= clasific.getE01EJLRTN()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>Puntos Persona Jur&iacute;dica :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLRTJ" size="12" maxlength="9" value="<%= clasific.getE01EJLRTJ()%>" readonly>
            </td>
          </tr>
          <tr id="clear"> 
            <td nowrap width="40%" align=right>Montos Persona Natural :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLAMN" size="17" maxlength="15" value="<%= clasific.getE01EJLAMN()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>Montos Persona Jur&iacute;dica :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLAMJ" size="17" maxlength="15" value="<%= clasific.getE01EJLAMJ()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><h4> Operaciones Pasivas </h4>
            </td>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>Puntos Persona Natural :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLRCN" size="12" maxlength="9" value="<%= clasific.getE01EJLRCN()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right>Puntos Persona Jur&iacute;dica :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLRCJ" size="12" maxlength="9" value="<%= clasific.getE01EJLRCJ()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right>Montos Persona Natural :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLACN" size="17" maxlength="15" value="<%= clasific.getE01EJLACN()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right>Montos Persona Jur&iacute;dica :
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJLACJ" size="17" maxlength="15" value="<%= clasific.getE01EJLACJ()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right> 
              Fecha/Hora Ultima Actualizaci&oacute;n :
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJLLD1" size="2" maxlength="2" value="<%= clasific.getE01EJLLD1() %>" readonly>
              <input type="text" name="E01EJLLD2" size="2" maxlength="2" value="<%= clasific.getE01EJLLD2() %>" readonly>
              <input type="text" name="E01EJLLD3" size="2" maxlength="2" value="<%= clasific.getE01EJLLD3() %>" readonly>
              <input type="text" name="E01EJLLDT" size="8" maxlength="2" value="<%= Util.formatTime(clasific.getE01EJLLDT()) %>" readonly>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="40%" align=right>
            	Usuario Ultima Actualizaci&oacute;n :
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJLUSR" size="12" maxlength="10" value="<%= clasific.getE01EJLUSR() %>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Regresar">
  </p>
  </form>
</body>
</html>
