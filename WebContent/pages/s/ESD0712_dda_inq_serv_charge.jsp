<html>
<head>
<title>Consulta de Productos de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>


</head>

<jsp:useBean id="ddaServCharge" class="datapro.eibs.beans.ESD0712DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     error.setERRNUM("0");
     out.println("       top.close();");
     out.println("</SCRIPT>");
     }
%>

<h3 align="center">Consulta de Tabla de Cargos en Cuentas de Detalles<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dda_inq_serv_charge, ESD0712"></h3>
<hr size="4">
<form >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <p></p>
  
  <h4>Informaci&oacute;n General</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="49%"> 
              <div align="right">C&oacute;digo del Banco:</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELBNK" size="4" maxlength="2" value="<%= ddaServCharge.getE01SELBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="49%"> 
              <div align="right">Tipo de Cuenta:</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELTYP" size="6" maxlength="4" value="<%= ddaServCharge.getE01SELTYP().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="49%"> 
              <div align="right">N&uacute;mero de Tabla:</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELTBL" size="4" maxlength="2" value="<%= ddaServCharge.getE01SELTBL().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <p>&nbsp;</p>

  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="20%" height="22"> 
              <div align="right">N&uacute;mero de Tabla</div>
            </td>
            <td width="40%" height="22"> 
              <input type="text" name="E01RTEDSC" size="27" maxlength="25" value="<%= ddaServCharge.getE01RTEDSC().trim()%>">
            </td>
            <td width="21%" height="22"> 
              <div align="right">Moneda Comisi&oacute;n:</div>
            </td>
            <td width="19%" height="22"> 
              <input type="text" name="E01RTEFCY" size="5" maxlength="3" value="<%= ddaServCharge.getE01RTEFCY().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Cargos/Comisiones</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td width="18%" height="22">&nbsp;</td>
            <td width="16%" height="22">&nbsp;</td>
            <td width="16%" height="22">&nbsp;</td>
            <td width="16%" height="22">&nbsp;</td>
            <td width="16%" height="22">&nbsp;</td>
            <td width="18%" height="22">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width="18%" height="22"> 
              <div align="right">Env&iacute;o Correspondencia</div>
            </td>
            <td width="16%" height="22"> 
              <input type="text" name="E01RTESTC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTESTC().trim()%>">
            </td>
            <td width="16%" height="22"> 
              <div align="right">Balance L&iacute;mite::</div>
            </td>
            <td width="16%" height="22"> 
              <input type="text" name="E01RTEMBM" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEMBM().trim()%>">
            </td>
            <td width="16%" height="22"> 
              <div align="right">Tipo de Balance:</div>
            </td>
            <td width="18%" height="22"> 
              <input type="text" name="E01RTEBT1" size="5" maxlength="3" value="<%= ddaServCharge.getE01RTEBT1().trim()%>">
            </td>
          </tr>
        </table>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td width="34%">&nbsp;</td>
            <td width="17%">&nbsp;</td>
            <td width="32%">&nbsp;</td>
            <td width="17%">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Saldo M&iacute;nimo Libros:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTECH1" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTECH1().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Saldo M&iacute;nimo en Libros:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEBL1" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEBL1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">Saldo M&iacute;nimo/M&iacute;nimo/ Disponible:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTECH2" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTECH2().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Saldo M&iacute;nimo Disponible</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEBL2" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEBL2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Saldo M&iacute;nimo/Promedio/ Libros:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTECH3" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTECH3().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Saldo M&iacute;nimo /Promedio/Libros</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEBL3" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEBL3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">Saldo M&iacute;nimo/Promedio/ Disponible:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTECH4" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTECH4().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Saldo M&iacute;nimo /Promedio/Disponible</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEBL4" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEBL4().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Exceso de Cheques:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEMKC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEMKC().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">L&iacute;mite N&uacute;mero de Cheques:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEMKK" size="5" maxlength="3" value="<%= ddaServCharge.getE01RTEMKK().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">Inactivas Tipo Uno:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEICH" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEICH().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">N&uacute;mero D&iacute;as Inactiva Tipo Uno:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEIDL" size="6" maxlength="4" value="<%= ddaServCharge.getE01RTEIDL().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Inactivas Tipo Dos:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEDCH" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEDCH().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">N&uacute;mero D&iacute;as Inactiva Tipo Dos:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEDDL" size="6" maxlength="4" value="<%= ddaServCharge.getE01RTEDDL().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">Aprobaci&oacute;n de Sobregiros:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENSC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTENSC().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Aprobaci&oacute;n de Diferidos:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEUNC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEUNC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Suspensi&oacute;n de Pagos de Cheques:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTESPC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTESPC().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Comisi&oacute;n de Apertura:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEOPC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOPC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">Comisi&oacute;n Cheques No Locales:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENCP" size="7" maxlength="5" value="<%= ddaServCharge.getE01RTENCP().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Comisi&oacute;n Anual:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEYRC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEYRC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">M&iacute;nimo por Cheque No Local:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENCM" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTENCM().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Cheques Depositados:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEPDC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEPDC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">M&aacute;ximo por Cheque No Local:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENCX" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTENCX().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Retenci&oacute;n de Correo:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEHCH" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEHCH().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">D&iacute;as Retener por Conformaci&oacute;n:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENDH" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTENDH().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Balance M&iacute;nimo Confirmaci&oacute;n:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEMAC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEMAC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%" height="19"> 
              <div align="right">Importe M&iacute;nimo de Cheques:</div>
            </td>
            <td width="17%" height="19"> 
              <input type="text" name="E01RTEMAK" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEMAK().trim()%>">
            </td>
            <td width="32%" height="19"> 
              <div align="right">Monto M&iacute;nimo Apertura de Cuenta:</div>
            </td>
            <td width="17%" height="19"> 
              <input type="text" name="E01RTEMOA" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEMOA().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Cargo M&iacute;nimo por Sobregiro:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEOMC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOMC().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">D&iacute;as Diferidos Local/No Local:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEWLC" size="3" maxlength="1" value="<%= ddaServCharge.getE01RTEWLC().trim()%>">
              <input type="text" name="E01RTEWNL" size="3" maxlength="1" value="<%= ddaServCharge.getE01RTEWNL().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">N&uacute;mero de Unidades Cr&eacute;dito:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTESCU" size="7" maxlength="5" value="<%= ddaServCharge.getE01RTESCU().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">M&iacute;nimo D&iacute;as por Cancelaci&oacute;n:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTENDC" size="5" maxlength="3" value="<%= ddaServCharge.getE01RTENDC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="34%"> 
              <div align="right">Monto de Unidades Cr&eacute;dito:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTESCA" size="7" maxlength="5" value="<%= ddaServCharge.getE01RTESCA().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Balance Usado por Unidad/Cr&eacute;dito:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEBUS" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEBUS().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="34%"> 
              <div align="right">D&iacute;as Vigencia Cheques Certificados:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTECAB" size="5" maxlength="3" value="<%= ddaServCharge.getE01RTECAB().trim()%>">
            </td>
            <td width="32%"> 
              <div align="right">Meses de Historia:</div>
            </td>
            <td width="17%"> 
              <input type="text" name="E01RTEHIS" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEHIS().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Tasas Activas</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td width="31%"> 
              <div align="right">Sobregiros:</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTEOR1" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOR1().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right">D&iacute;as L&iacute;mite 1:</div>
            </td>
            <td width="19%">
              <input type="text" name="E01RTEDO1" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEDO1().trim()%>">
            </td>
          </tr>
          <tr id="trclear">
            <td width="31%"> 
              <div align="right">Sobregiros:</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTEOR2" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOR2().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right">D&iacute;as L&iacute;mite 2:</div>
            </td>
            <td width="19%">
              <input type="text" name="E01RTEDO2" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEDO2().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td width="31%"> 
              <div align="right">Sobregiros:</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTEOR3" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOR3().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right">D&iacute;as L&iacute;mite 3:</div>
            </td>
            <td width="19%">
              <input type="text" name="E01RTEDO3" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEDO3().trim()%>">
            </td>
          </tr>
          <tr id="trclear">
            <td width="31%"> 
              <div align="right">Sobregiros:</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTEOR4" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEOR4().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right">D&iacute;as L&iacute;mite 4:</div>
            </td>
            <td width="19%">
              <input type="text" name="E01RTEDO4" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEDO4().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td width="31%"> 
              <div align="right">Tipo C&aacute;lculo Inter&eacute;s::</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTECAM" size="3" maxlength="1" value="<%= ddaServCharge.getE01RTECAM().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right">Monto de Comisi&oacute;n:</div>
            </td>
            <td width="19%"> 
              <input type="text" name="E01RTEODC" size="11" maxlength="9" value="<%= ddaServCharge.getE01RTEODC().trim()%>">
            </td>
          </tr>
          <tr id="trclear">
            <td width="31%"> 
              <div align="right">Comisi&oacute;n de Sobregiro:</div>
            </td>
            <td width="23%">
              <input type="text" name="E01RTEODF" size="3" maxlength="1" value="<%= ddaServCharge.getE01RTEODF().trim()%>">
            </td>
            <td width="27%"> 
              <div align="right"></div>
            </td>
            <td width="19%">&nbsp; </td>
          </tr>
        </table>

        <table class="tableinfo">
          <tr id="trdark">
            <td width="17%"> 
              <div align="right">Tasa Variable:</div>
            </td>
            <td width="13%">
              <input type="text" name="E01RTEFTA" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEFTA().trim()%>">
              <input type="text" name="E01RTEFYA" size="4" maxlength="2" value="<%= ddaServCharge.getE01RTEFYA().trim()%>">
            </td>
            <td width="17%"> 
              <div align="right">N&uacute;mero de Veces:</div>
            </td>
            <td width="13%"> 
              <input type="text" name="E01RTEFCA" size="7" maxlength="5" value="<%= ddaServCharge.getE01RTEFCA().trim()%>">
            </td>
            <td width="15%"> 
              <div align="right">Operaci&oacute;n:</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01RTEFFA" size="3" maxlength="1" value="<%= ddaServCharge.getE01RTEFFA().trim()%>">
            </td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
  </div>
</p>
  </form>
</body>
</html>
