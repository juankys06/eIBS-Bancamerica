<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008010Message"  scope="session"/>
 
<jsp:useBean id= "clientList" class= "datapro.eibs.beans.JBListRec"  scope="session"/>

<style type="text/css">
#celdas {font-family:Arial; font-size:8pt;text-align:justify;}
#caption {font-family:Arial; font-size:6pt;text-align:justify;}
p{text-align:justify;}
</STYLE>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<body bgcolor="#FFFFFF">

<form >
  <table width="650" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <div align="center"><img src="<%=request.getContextPath()%>/images/logo.gif" alt="DATAPRO, INC" align="absbottom" border="0" height="32" ></div>
      </td>
    </tr>
  </table>
  <table width="650" border="0">
    <tr> <hr>
      <td>
        <div align="center"><font face="Arial, Helvetica, sans-serif" size="3"><b>Informaci&oacute;n 
          del Cliente</b></font></div>
      </td>
    </tr>
  </table>
  <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
    <tr>
	<hr>
      <td width="538"> 
        <div align="right"></div>
      </td>
      <td width="150"> 
        <div align="right"><%= client.getXDATE() %></div>
      </td>
    </tr>
  </table>

  <table width="650" border="0" id="celdas" cellspacing="0" cellpadding="3">
    <tr><hr>
      <td> 
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td width="105"><b>_<% if ( client.getE10NOF().equals("N") ) out.print("X"); %>_Cliente 
              Nuevo </b></td>
            <td width="166"><b>_<% if ( client.getE10NOF().equals("O") ) out.print("X"); %>_Actualizaci&oacute;n 
              de Cliente </b></td>
            <td width="97"><b>Tipo de Cliente:</b></td>
            <td width="60"><% if ( client.getE10LGT().equals("2") ) out.print("PERSONAL"); else out.print("CORPORATIVE"); %></td>
            <td width="112"><b>N&uacute;mero de Cliente:</b></td>
            <td width="74"><%= client.getE10CUN().trim() %></td>
          </tr>
        </table>
        
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <HR>
            <td colspan="2"><font size="2"><b>Personal Information</b></font></td>
          </tr>
        </table>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td width="115"><b>Nombre </b></td>
            <td colspan="5"><%= client.getE10NA1().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>Direcci&oacute;n:</b></td>
            <td colspan="5"><%= client.getE10NA2().trim() %></td>
          </tr>
          <tr>
            <td width="115">&nbsp;</td>
            <td colspan="3"><%= client.getE10NA3().trim() %></td>
            <td width="96" ><b>Colonia</b></td>
            <td width="104" ><%= client.getE10STE().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>Ciudad:</b></td>
            <td width="102"><%= client.getE10CTY().trim() %></td>
            <td width="101"><b>Pa&iacute;s:</b></td>
            <td width="96"><%= client.getE10CTR().trim() %></td>
            <td width="96"><b>C&oacute;digo Postal:</b></td>
            <td width="104"><%= client.getE10ZPC().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>No. de Identificaci&oacute;n:</b></td>
            <td width="102"><%= (client.getE10IDN().indexOf("-")<0) ? out.print("client.getE10IDN().trim()") : out.print("client.getE10LN3().trim()") %></td>
            <td width="101"><b>Tipo de Identific.</b></td>
            <td width="96"><%= client.getE10TID().trim() %></td>
            <td width="96"><b>Pa&iacute;s de Identific.:</b></td>
            <td width="104"><%= client.getE10PID().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>DOB:</b></td>
            <td width="102"><%= client.getE10BDM().trim() %>/<%= client.getE10BDD().trim() %>/<%= client.getE10BDY().trim() %></td>
            <td width="101"><b>Sexo:</b></td>
            <td width="96"><% if ( client.getE10NA3().equals("M") ) out.print("Male"); else out.print("Female"); %></td>
            <td width="96"><b>Estado Civil:</b></td>
            <td width="104"><%= client.getE10MST().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>Tel&eacute;fona Particular:</b></td>
            <td width="102"><%= client.getE10HPN().trim() %></td>
            <td width="101"><b>Tel&eacute;fono Trabajo:</b></td>
            <td width="96"><%= client.getE10PHN().trim() %></td>
            <td width="96"><b>Fax:</b></td>
            <td width="104"><%= client.getE10FAX().trim() %></td>
          </tr>
          <tr> 
            <td width="115"><b>Tel&eacute;fono Celular:</b></td>
            <td width="102"><%= client.getE10PH1().trim() %></td>
            <td width="101"><b>e-Mail:</b></td>
            <td colspan="2"><%= client.getE10IAD().trim() %></td>
            <td width="104">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Deducci&oacute;n :</b></td>
            <td width="102">&nbsp;</td>
            <td width="101"><b>Referido por:</b></td>
            <td width="96"><%= client.getD10RBY().trim() %></td>
            <td width="96"><b>Residente:</b></td>
            <td width="104"><%= client.getE10FL1().trim() %></td>
          </tr>
        </table>
        <% if (client.getE10LGT().equals("2")) { %>
		<table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <HR>
            <td colspan="2"><font size="2"><b>Datos de Empleo</b></font></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Compa&ntilde;&iacute;a :</b></td>
            <td width="110"><%= client.getE10CP1().trim() %></td>
            <td width="100"><b>Direcci&oacute;n:</b></td>
            <td colspan="2"><%= client.getE10CP2().trim() %></td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>T&iacute;tulo/Cargo:</b></td>
            <td width="110"><%= client.getE10CP3().trim() %></td>
            <td colspan="2"><b>A&ntilde;os como empleado:</b></td>
            <td width="100"><%= client.getE10TIM().trim() %></td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Salario:</b></td>
            <td width="110"><%= client.getE10CAI().trim() %></td>
            <td colspan="2"><b>Otros, Ingresos Anuales:</b></td>
            <td width="100"><%= client.getE10CAS().trim() %></td>
            <td width="110">&nbsp;</td>
          </tr>
        </table>
		<% 
			} 
		    if (client.getXFLAG1().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("4")) {
                    		// out.println(clientList.getRecord());
		%>
        <table width="650" border="0" cellspacing="0" cellpadding="3"id="celdas">
          <tr> 
            <HR>
            <td colspan="2"><font size="2"><b>Beneficiario(s)</b></font></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Nombre Legal1:</b></td>
            <td colspan="5"><%= clientList.getRecord(0) %></td>
          </tr>
          <tr> 
            <td width="120"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr>
            <td width="120">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Ciudad:</b></td>
            <td width="110"><%= clientList.getRecord(3) %></td>
            <td width="100"><b>Pa&iacute;s:</b></td>
            <td width="110"><%= clientList.getRecord(7) %></td>
            <td width="100"><b>C&oacute;digo Postal:</b></td>
            <td width="110"><%= clientList.getRecord(5) %></td>
          </tr>
          <tr> 
            <td width="120"><b>No. de Identificaci&oacute;n</b>:</td>
            <td width="110"><%= clientList.getRecord(10) %></td>
            <td width="100"><b>Tipo de Identific.</b></td>
            <td width="110"><%= clientList.getRecord(13) %></td>
            <td width="100"><b>Pa&iacute;s de Identific.</b></td>
            <td width="110"><%= clientList.getRecord(14) %></td>
          </tr>
          <tr> 
            <td width="120"><b>Sexo:</b></td>
            <td width="110"><%= clientList.getRecord(11) %></td>
            <td width="100"><b>Estado Civil:</b></td>
            <td width="110"><%= clientList.getRecord(12) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG0().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("1")) {
                    		// out.println(cifList.getRecord());
		%>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <HR>
            <td colspan="2"><b><font size="2">Direcci&oacute;n de Correo</font></b></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Nombre Legal:</b></td>
            <td colspan="3"><%= clientList.getRecord(0) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="100"><b> </b></td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr>
            <td width="120">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Ciudad:</b></td>
            <td width="110"><%= clientList.getRecord(3) %></td>
            <td width="100"><b>Pa&iacute;s:</b></td>
            <td width="110"><%= clientList.getRecord(7) %></td>
            <td width="100"><b>C&oacute;digo Postal:</b></td>
            <td width="110"><%= clientList.getRecord(5) %></td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG4().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("6")) {
                    		// out.println(cifList.getRecord());
		%>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> <hr>
            <td colspan="2"><font size="2"><b>Referencia Bancarias</b></font></td>
            <td width="94">&nbsp;</td>
            <td width="102">&nbsp;</td>
            <td width="95">&nbsp;</td>
            <td width="105">&nbsp;</td>
          </tr>
          <tr> 
            <td width="126" height="17"><b>Instituci&oacute;n financiera:</b></td>
            <td width="92" height="17"><%= clientList.getRecord(0) %></td>
            <td width="94" height="17"> 
              <div align="left"><b>Tel&eacute;fono:</b></div>
            </td>
            <td width="102" height="17"><%= clientList.getRecord(8) %></td>
            <td width="95" height="17"><b>Saldo:</b></td>
            <td width="105" height="17"><%= clientList.getRecord(15) %></td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG5().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("7")) {
                    		// out.println(cifList.getRecord());
		%>
		<table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td colspan="2"><font size="2"><b>Referencias Comerciales:</b></font></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td colspan="2"><b>Name and Com. Reference 1:</b></td>
            <td colspan="2"><%= clientList.getRecord(0) %></td>
            <td width="100"><b>Tel&eacute;fono:</b></td>
            <td width="110"><%= clientList.getRecord(8) %></td>
          </tr>
          <tr> 
            <td width="120"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr>
            <td width="120">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Ciudad:</b></td>
            <td width="110"><%= clientList.getRecord(3) %></td>
            <td width="100"><b>Pa&iacute;s:</b></td>
            <td width="110"><%= clientList.getRecord(7) %></td>
            <td width="100"><b>C&oacute;digo Postal:</b></td>
            <td width="110"><%= clientList.getRecord(5) %></td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG6().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("8")) {
                    		// out.println(cifList.getRecord());
		%>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <hr>
            <td colspan="2"><b><font size="2">Referencias Personales</font></b></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td colspan="2"><b>Nombre y Referencia Personal 1:</b></td>
            <td colspan="2"><%= clientList.getRecord(0) %></td>
            <td width="100"><b>Tel&eacute;fono:</b></td>
            <td width="110"><%= clientList.getRecord(8) %></td>
          </tr>
          <tr> 
            <td width="120"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr>
            <td width="120">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Ciudad:</b></td>
            <td width="110"><%= clientList.getRecord(3) %></td>
            <td width="100"><b>Pa&iacute;s:</b></td>
            <td width="110"><%= clientList.getRecord(7) %></td>
            <td width="100"><b>C&oacute;digo Postal:</b></td>
            <td width="110"><%= clientList.getRecord(5) %></td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG7().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("9")) {
                    		// out.println(cifList.getRecord());
		%>
		<table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td colspan="2"><font size="2"><b>Activos:</b></font></td>
            <td width="95">&nbsp;</td>
            <td width="96">&nbsp;</td>
            <td width="102">&nbsp;</td>
            <td width="104">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Descripci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(0) %></td>
            <td width="102">&nbsp;</td>
            <td width="104">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="102">&nbsp;</td>
            <td width="104">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="102">&nbsp;</td>
            <td width="104">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Ciudad:</b></td>
            <td width="102"><%= clientList.getRecord(3) %></td>
            <td width="95"><b>Pa&iacute;s:</b></td>
            <td width="96"><%= clientList.getRecord(7) %></td>
            <td width="102"><b>C&oacute;digo Postal:</b></td>
            <td width="104"><%= clientList.getRecord(5) %></td>
          </tr>
          <tr> 
            <td width="115"><b>Valor Original:</b></td>
            <td width="102"><%= clientList.getRecord(15) %></td>
            <td width="95"><b>Valor Actual:</b></td>
            <td width="96"><%= clientList.getRecord(16) %></td>
            <td width="102"><b>Fecha de Compra:</b></td>
            <td width="104"><%= clientList.getRecord(17) %></td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG8().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("A")) {
                    		// out.println(cifList.getRecord());
		%>
		<table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td colspan="2"><font size="2"><b>Pasivos:</b></font></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td><b>Prestamista:</b></td>
            <td colspan="3"><%= clientList.getRecord(0) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
          <tr> 
            <td width="120"><b>Tipo:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="100"><b>Referencia:</b></td>
            <td width="110"><%= clientList.getRecord(2) %></td>
          </tr>
          <tr> 
            <td width="120"><b>Fecha:</b></td>
            <td width="110"><%= clientList.getRecord(17) %></td>
            <td width="100"><b>Valor:</b></td>
            <td width="110"><%= clientList.getRecord(15) %></td>
            <td width="100">&nbsp;</td>
            <td width="110">&nbsp;</td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		    if (client.getXFLAG3().equals("Y")) { 
                clientList.initRow();
                while (clientList.getNextRow()) {
                    if (clientList.getFlag().equals("5")) {
                    		// out.println(cifList.getRecord());
		%>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <HR>
            <td colspan="2"><font size="2"><b> Representantes Legales:</b></font></td>
            <td width="101">&nbsp;</td>
            <td width="98">&nbsp;</td>
            <td width="111">&nbsp;</td>
            <td width="86">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Nombre Legal:</b></td>
            <td colspan="3"><%= clientList.getRecord(0) %></td>
            <td width="111">&nbsp;</td>
            <td width="86">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Direcci&oacute;n:</b></td>
            <td colspan="3"><%= clientList.getRecord(1) %></td>
            <td width="111">&nbsp;</td>
            <td width="86">&nbsp;</td>
          </tr>
          <tr>
            <td width="115">&nbsp;</td>
            <td colspan="3"><%= clientList.getRecord(2) %></td>
            <td width="111">&nbsp;</td>
            <td width="86">&nbsp;</td>
          </tr>
          <tr> 
            <td width="115"><b>Ciudad:</b></td>
            <td width="103"><%= clientList.getRecord(3) %></td>
            <td width="101"><b>Pa&iacute;s:</b></td>
            <td width="98"><%= clientList.getRecord(7) %></td>
            <td width="111"><b>C&oacute;digo Postal:</b></td>
            <td width="86"><%= clientList.getRecord(5) %></td>
          </tr>
          <tr> 
            <td width="115"><b>No. de Identificaci&oacute;n:</b></td>
            <td width="103"><%= clientList.getRecord(10) %></td>
            <td width="101"><b>Tipo de Identific.:</b></td>
            <td width="98"><%= clientList.getRecord(13) %></td>
            <td width="111"><b>Pa&iacute;s de Identificac.:</b></td>
            <td width="86"><%= clientList.getRecord(14) %></td>
          </tr>
          <tr> 
            <td width="115"><b>Sexo</b>:</td>
            <td width="103"><%= clientList.getRecord(11) %></td>
            <td width="101"><b>Estado Civil:</b></td>
            <td width="98"><%= clientList.getRecord(12) %></td>
            <td width="111">&nbsp;</td>
            <td width="86">&nbsp;</td>
          </tr>
        </table>
		<% 
                    }
                }
			} 
		%>
       
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr><hr>
            <td><font size="2"></font></td>
          </tr>
        </table>
        <table width="650" border="0" cellspacing="0" cellpadding="3" id="celdas">
          <tr> 
            <td><font size="2"></font></td>
          </tr>
        </table>
       
        </td>
    </tr>
  </table>
  <p align="center">&nbsp;</p>
  <table width="500" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td> 
        <div align="left"><b><font face="Arial, Helvetica, sans-serif" size="2"> 
          </font> </b></div>
      </td>
    </tr>
    <tr>
      <td> 
        <table width="500" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="94" valign="bottom" height="88"><b><font face="Arial, Helvetica, sans-serif" size="2"> 
              </font></b></td>
            <td width="238" height="88"> 
              <div align="center"><img src="<%=request.getContextPath()%>/signatures/<%= client.getE10CUN().trim() %>.jpg"> 
              </div>
            </td>
            <td width="168" height="88">&nbsp;</td>
          </tr>
          <tr> 
            <td width="94" height="30" valign="top"><b><font face="Arial, Helvetica, sans-serif" size="2">Firma: 
              </font></b></td>
            <td width="238" height="30"> 
              <hr size="2" align="center">
            </td>
            <td width="168" height="30">&nbsp;</td>
          </tr>
          <tr> 
            <td width="94">&nbsp;</td>
            <td width="238"><%= client.getE10NA1().trim() %></td>
            <td width="168">&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
<p>&nbsp;</p>
</body>
</html>
