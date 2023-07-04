<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Tasas de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="participant" class="datapro.eibs.beans.EDL601001Message"  scope="session" />
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
<h3 align="center">Aprobacion de Participaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ap_particip_detail.jsp, EDL6010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL6010" >
  <input type=hidden name="SCREEN" value="1">
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="25%" align=right><b>Banco :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARBNK" size="4" maxlength="2" value="<%= participant.getE01PARBNK() %>" readonly><br>
              <input type="text" name="E01PARBNN" size="30" maxlength="25" value="<%= participant.getE01PARBNN() %>" readonly>
            </td>
            <td nowrap width="25%" align=right> 
              <b>Prioridad :</b>
            </td>
            <td nowrap width="15%">
               <input type="text" name="E01PARPTY" size="5" maxlength="4" value="<%= participant.getE01PARPTY()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" align=right> 
              <b>Moneda :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARCCY" size="4" maxlength="4" value="<%= participant.getE01PARCCY()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Tipo :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARTYP" size="4" maxlength="2" value="<%= participant.getE01PARTYP() %>" readonly><br>
              <input type="text" name="E01PARTYN" size="30" maxlength="2" value="<%= participant.getE01PARTYN() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" align=right> 
              <b>Monto a Ceder :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARMCD" size="15" maxlength="4" value="<%= participant.getE01PARMCD()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Monto Reservado :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARRSV" size="15" maxlength="2" value="<%= participant.getE01PARRSV() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" align=right> 
              <b>Reserva por Vencimiento :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARRXV" size="15" maxlength="4" value="<%= participant.getE01PARRXV()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            </td>
            <td nowrap width="15%">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" align=right> 
              <b>Monto Minimo Reserva :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARMIN" size="15" maxlength="4" value="<%= participant.getE01PARMIN()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Monto Maximo Reserva :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARMAX" size="15" maxlength="2" value="<%= participant.getE01PARMAX() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" align=right> 
              <b>Monto Cedido Hoy :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARCDT" size="15" maxlength="4" value="<%= participant.getE01PARCDT()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Monto Disponible :</b>
            </td>
            <td nowrap width="15%">
            	<input type="text" name="E01PARDIS" size="15" maxlength="4" value="<%= participant.getE01PARDIS()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" align=right> 
              <b>Monto Total Cedido :</b>
            </td>
            <td nowrap width="15%">
               	<input type="text" name="E01PARTOT" size="15" maxlength="4" value="<%= participant.getE01PARTOT()%>" readonly>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Tiempo Reserva en Minutos :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARTRS" size="4" maxlength="2" value="<%= participant.getE01PARTRS() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" align=right> 
              <b>Reserva C/Cuenta :</b>
            </td>
            <td nowrap width="15%">
			  <%if(participant.getE01PARRCC().equals("Y")) out.print("Sí");%> 
              <%if(participant.getE01PARRCC().equals("N")) out.print("No");%>
            </td>
            <td nowrap width="25%" align=right>
            	<b>Status :</b>
            </td>
            <td nowrap width="15%">
				<%if(participant.getE01PARSTS().equals("A")) out.print("Abierto");%>
              	<%if(participant.getE01PARSTS().equals("C")) out.print("Cerrado");%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="50%" align=right> 
              <b>Fecha/Hora Ultima Actualizacion :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARLD1" size="3" maxlength="2" value="<%= participant.getE01PARLD1() %>" readonly>
              <input type="text" name="E01PARLD2" size="3" maxlength="2" value="<%= participant.getE01PARLD2() %>" readonly>
              <input type="text" name="E01PARLD3" size="3" maxlength="2" value="<%= participant.getE01PARLD3() %>" readonly>
              <input type="text" name="E01PARLDT" size="8" maxlength="2" value="<%= Util.formatTime(participant.getE01PARLDT()) %>" readonly>
            </td>
            <td nowrap width="50%" align=right>
            	<b>Usuario :</b>
            </td>
            <td nowrap width="15%">
              <input type="text" name="E01PARUSR" size="15" maxlength="17" value="<%= participant.getE01PARUSR() %>" readonly>
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
