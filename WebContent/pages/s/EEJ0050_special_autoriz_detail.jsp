<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Aprobaci&oacute;n de Autorizaciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="autoriz" class="datapro.eibs.beans.EEJ005001Message"  scope="session" />
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
<h3 align="center">Aprobacion de Autorizaciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="special_autoriz_detail.jsp, EEJ0050"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0050" >
  <input type=hidden name="SCREEN" value="1">
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Referencia :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAREF" size="14" maxlength="12" value="<%= autoriz.getE01EJAREF() %>" readonly>
			  <input type="text" name="E01EJACUN" size="45" maxlength="45" value="<%= autoriz.getE01EJACUN() %>" readonly>            
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Ejecutivo Inicial :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAIUS" size="12" maxlength="10" value="<%= autoriz.getE01EJAIUS() %>" readonly>
              <input type="text" name="E01EJACD3" size="5" maxlength="4" value="<%= autoriz.getE01EJACD3() %>" readonly>
              <input type="text" name="E01EJAIUN" size="50" maxlength="45" value="<%= autoriz.getE01EJAIUN() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Fecha/Hora Inicial :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAID1" size="2" maxlength="2" value="<%= autoriz.getE01EJAID1() %>" readonly>
              <input type="text" name="E01EJAID2" size="2" maxlength="2" value="<%= autoriz.getE01EJAID2() %>" readonly>
              <input type="text" name="E01EJAID3" size="2" maxlength="2" value="<%= autoriz.getE01EJAID3() %>" readonly>
              <input type="text" name="E01EJAITI" size="8" maxlength="2" value="<%= Util.formatTime(autoriz.getE01EJAITI()) %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Ejecutivo Solicita :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAUSR" size="12" maxlength="10" value="<%= autoriz.getE01EJAUSR() %>" readonly>
 			<input type="text" name="E01EJACD1" size="5" maxlength="4" value="<%= autoriz.getE01EJACD1() %>" readonly>
              <input type="text" name="E01EJAUSN" size="50" maxlength="45" value="<%= autoriz.getE01EJAUSN() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Fecha/Hora Solicitud :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJARD1" size="2" maxlength="2" value="<%= autoriz.getE01EJARD1() %>" readonly>
              <input type="text" name="E01EJARD2" size="2" maxlength="2" value="<%= autoriz.getE01EJARD2() %>" readonly>
              <input type="text" name="E01EJARD3" size="2" maxlength="2" value="<%= autoriz.getE01EJARD3() %>" readonly>
              <input type="text" name="E01EJARTI" size="8" maxlength="2" value="<%= Util.formatTime(autoriz.getE01EJARTI()) %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>C&oacute;digo de Operaci&oacute;n :</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJAOPE" size="3" maxlength="2" value="<%=autoriz.getE01EJAOPE()%>" readonly>
               	<input type="text" name="E01EJAOPN" size="22" maxlength="18" value="<%= autoriz.getE01EJAOPN() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>C&oacute;digo de Aplicaci&oacute;n :</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" readonly name="E01EJAACD" size="28" maxlength="24"
                  value="<% if (autoriz.getE01EJAACD().equals("CJ")) { out.print("Caja"); }
							else if (autoriz.getE01EJAACD().equals("CM")) { out.print("Camara"); }
	                    	else if (autoriz.getE01EJAACD().equals("PR")) { out.print("Propuesta"); }
							else if (autoriz.getE01EJAACD().equals("GR")) { out.print("Generales"); }
							else if (autoriz.getE01EJAACD().equals("10")) { out.print("Prestamos"); }
							else if (autoriz.getE01EJAACD().equals("11")) { out.print("Certificados de Deposito"); }
	                    	else if (autoriz.getE01EJAACD().equals("12")) { out.print("Depositos a Plazo"); }
							else if (autoriz.getE01EJAACD().equals("13")) { out.print("Inversiones"); }
							else if (autoriz.getE01EJAACD().equals("14")) { out.print("Aceptaciones Descontadas"); }
							else if (autoriz.getE01EJAACD().equals("15")) { out.print("Inversiones 24 Horas"); }
							else if (autoriz.getE01EJAACD().equals("19")) { out.print("Control de Proyectos"); }
							else { out.print(""); } %>" >
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="20%" align=right><b>C&oacute;digo de Producto :</b>
            </td>
            <td nowrap width="20%">
               	<input type="text" readonly name="E01CODPRD" size="04" maxlength="04"
                  value="<%= autoriz.getE01CODPRD()+' '%>" readonly>
                  
            <INPUT type="text" readonly name="E01DESPRD" size="45"
					maxlength="45" value="<%= autoriz.getE01DESPRD()%>" readonly></td>
            <td nowrap width="60%">
               	
            </td>
            
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Status :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJASTN" size="28" maxlength="23" value="<%= autoriz.getE01EJASTN() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Tasa Solicitada:</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJARTE" size="12" maxlength="12" value="<%= autoriz.getE01EJARTE()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Valor Aplicar :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAAMT" size="20" maxlength="20" value="<%= autoriz.getE01EJAAMT() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Porcentaje Aprobado :</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJAACR" size="12" maxlength="12" value="<%= autoriz.getE01EJAACR()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Valor Aprobado :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAACA" size="20" maxlength="20" value="<%= autoriz.getE01EJAACA() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Porcentaje Pendiente :</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" name="E01EJAPER" size="12" maxlength="12" value="<%= autoriz.getE01EJAPER()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Valor Pendiente :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJAPEA" size="20" maxlength="20" value="<%= autoriz.getE01EJAPEA() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%" align=right><b>Prioridad :</b>
            </td>
            <td nowrap width="60%">
               	<input type="text" readonly name="E01EJAPTY" size="8" maxlength="6"
                  value="<% if (autoriz.getE01EJAPTY().equals("1")) { out.print("Alta"); }
							else if (autoriz.getE01EJAPTY().equals("2")) { out.print("Normal"); }
	                    	else if (autoriz.getE01EJAPTY().equals("3")) { out.print("Baja"); }
							else { out.print(""); } %>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" align=right><b>Tipo de<BR>Aprobaci&oacute;n :</b>
            </td>
            <td nowrap width="60%">
              <input type="text" name="E01EJATYN" size="22" maxlength="20" value="<%= autoriz.getE01EJATYN() %>" readonly>
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
