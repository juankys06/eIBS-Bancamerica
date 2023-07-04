<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Inversiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invParameters" class="datapro.eibs.beans.EIE001501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<div align="center"> 
  <h3>Parametros de Inversiones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_orders_input.jsp,EIE110"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0015" >
  <h4>
    <input type="hidden" name="SCREEN"  value="2" >
  </h4>
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap height="236"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="45%" > 
              <input type="text" name="E01IVSBNK" size="3" maxlength="3" value="<%= invParameters.getE01IVSBNK()%>" readonly>
              <input type="text" name="E01BNKDSC" size="35" maxlength="30" value="<%= invParameters.getE01BNKDSC()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right">M&eacute;todo Contable :</div>
            </td>
            <td nowrap width="45%" > 
              <select name="E01IVSBKM">
                <option value="1" <%if (invParameters.getE01IVSBKM().equals("1")) { out.print("selected"); }%>>FIFO</option>
                <option value="2" <%if (invParameters.getE01IVSBKM().equals("2")) {  out.print("selected"); }%>>LIFO</option>
                <option value="3" <%if (invParameters.getE01IVSBKM().equals("3")) {  out.print("selected"); }%>>Costo Promedio</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Tipo de Orden por Defecto :</div>
            </td>
            <td nowrap width="45%" > 
              <input type="text" name="E01IVSOTY" size="5" maxlength="3" value="<%= invParameters.getE01IVSOTY().trim()%>">
              <input type="text" name="E01DSCOTY" size="40" maxlength="35"  value="<%= invParameters.getE01DSCOTY().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E01IVSOTY','E01DSCOTY','+')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right">D&iacute;as por Adelantado para Cancelar Ordenes 
                Pendientes :</div>
            </td>
            <td nowrap width="45%" > 
              <input type="text" name="E01IVSDYS" size="4" maxlength="3" value="<%= invParameters.getE01IVSDYS()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Moneda por Defecto para Estado de Cuentas :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left"> 
                <select name="E01IVSDCY">
                  <option value="B" <%if (invParameters.getE01IVSDCY().equals("B")) { out.print("selected"); }%>>Moneda Base 
                 </option>
                  <option value="I" <%if (invParameters.getE01IVSDCY().equals("I")) {  out.print("selected"); }%>>Moneda del Instrumento 
                  </option>
                  <option value="P" <%if (invParameters.getE01IVSDCY().equals("P")) {  out.print("selected"); }%>>Moneda del Portafolio 
                  </option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right">Tabla de Cargos para Custodia :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left"> 
                <input type="text" name="E01IVSCUT" size="4" maxlength="2" value="<%= invParameters.getE01IVSCUT()%>">
                <a href="javascript:GetCommCustodyTable('E01IVSCUT','CST','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Per&iacute;odo para Cargo de Custodio :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left"> 
                <input type="text" name="E01IVSCFV" size="4" maxlength="3" value="<%= invParameters.getE01IVSCFV()%>">
                <select name="E01IVSCFC">
                  <option value="D" <%if (invParameters.getE01IVSCFC().equals("D")) { out.print("selected"); }%>>Dia(s)</option>
                  <option value="M" <%if (invParameters.getE01IVSCFC().equals("M")) {  out.print("selected"); }%>>Mes(es)</option>
                  <option value="Y" <%if (invParameters.getE01IVSCFC().equals("Y")) {  out.print("selected"); }%>>Año(s)</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right">Margen del Precio del Cliente :</div>
            </td>
            <td nowrap width="45%" > 
              <input type="text" name="E01IVSMRG" size="17" maxlength="15" value="<%= invParameters.getE01IVSMRG()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="55%" >
              <div align="right">M&aacute;ximo Porciento del Valor del Mercado 
                :</div>
            </td>
            <td nowrap width="45%" >
              <div align="left">
                <input type="text" name="E01IVSFL7" size="17" maxlength="15" value="<%= invParameters.getE01IVSFL7()%>">
                (Solo para participaciones)</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Retener Fondos antes de Aprobarlos :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left">
                <input type="hidden" name="E01IVSHLD" value="<%= invParameters.getE01IVSHLD()%>">
                <input type="radio" name="CE01IVSHLD" value="Y" onClick="document.forms[0].E01IVSHLD.value='Y'"
			  <%if(invParameters.getE01IVSHLD().equals("Y")) out.print("checked");%>>
                S&iacute; 
                <input type="radio" name="CE01IVSHLD" value="N" onClick="document.forms[0].E01IVSHLD.value='N'"
			  <%if(invParameters.getE01IVSHLD().equals("N")) out.print("checked");%>>
                No </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right">Permitir Actualizaci&oacute;n de la Tasa de Cambio 
                :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left">
                <input type="hidden" name="E01IVSUPX" value="<%= invParameters.getE01IVSUPX()%>">
                <input type="radio" name="CE01IVSUPX" value="Y" onClick="document.forms[0].E01IVSUPX.value='Y'"
			  <%if(invParameters.getE01IVSUPX().equals("Y")) out.print("checked");%>>
                S&iacute; 
                <input type="radio" name="CE01IVSUPX" value="N" onClick="document.forms[0].E01IVSUPX.value='N'"
			  <%if(invParameters.getE01IVSUPX().equals("N")) out.print("checked");%>>
                No </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="55%" > 
              <div align="right">Incluir Futuras Transacciones en Estado de Cuentas 
                :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left">
                <input type="hidden" name="E01IVSFUT" value="<%= invParameters.getE01IVSFUT()%>">
                <input type="radio" name="CE01IVSFUT" value="Y" onClick="document.forms[0].E01IVSFUT.value='Y'"
			  <%if(invParameters.getE01IVSFUT().equals("Y")) out.print("checked");%> disabled>
                S&iacute; 
                <input type="radio" name="CE01IVSFUT" value="N" onClick="document.forms[0].E01IVSFUT.value='N'"
			  <%if(invParameters.getE01IVSFUT().equals("N")) out.print("checked");%> disabled>
                No </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="55%" > 
              <div align="right"> Permitir Reversar Ordenes ya Procesadas :</div>
            </td>
            <td nowrap width="45%" > 
              <div align="left">
                <input type="hidden" name="E01IVSREV" value="<%= invParameters.getE01IVSREV()%>">
                <input type="radio" name="CE01IVSREV" value="Y" onClick="document.forms[0].E01IVSFUT.value='Y'"
			  <%if(invParameters.getE01IVSFUT().equals("Y")) out.print("checked");%> disabled>
                S&iacute; 
                <input type="radio" name="CE01IVSREV" value="N" onClick="document.forms[0].E01IVSFUT.value='N'"
			  <%if(invParameters.getE01IVSFUT().equals("N")) out.print("checked");%> disabled>
                No </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
 
  </form>
</body>
</html>
