<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Inversiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invBrok" class="datapro.eibs.beans.EIE000501Message"  scope="session" />

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
  <h3>Consulta de Brokers <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_brokers_basic.jsp,EIE0005"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0005" >
  <h4>Información Básica 
    <input type="hidden" name="SCREEN"  value="2" >
    <input type="hidden" name="E01FEBTYP"  value="2" >
  </h4>
  <table  class="tableinfo" width="715">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Código :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBNUM" size="5" maxlength="3" value="<%= invBrok.getE01FEBNUM()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Número de Impuestos :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBBID" size="17" maxlength="15" value="<%= invBrok.getE01FEBBID()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBNM1" size="35" maxlength="30" value="<%= invBrok.getE01FEBNM1()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Nombre Corto :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBSNM" size="20" maxlength="15" value="<%= invBrok.getE01FEBSNM()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Dirección :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBNM2" size="35" maxlength="30" value="<%= invBrok.getE01FEBNM2()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBCTY" size="20" maxlength="15" value="<%= invBrok.getE01FEBCTY()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right"> </div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBNM3" size="35" maxlength="30" value="<%= invBrok.getE01FEBNM3()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBSTE" size="4" maxlength="2" value="<%= invBrok.getE01FEBSTE()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBNM4" size="35" maxlength="30" value="<%= invBrok.getE01FEBNM4()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">País :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBCTR" size="4" maxlength="3" value="<%= invBrok.getE01FEBCTR()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Apartado Postal :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBZPC" size="20" maxlength="15" value="<%= invBrok.getE01FEBZPC()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Código Postal :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBZIP" size="20" maxlength="15" value="<%= invBrok.getE01FEBZIP()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Telefono 1 :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBPH1" size="20" maxlength="15" value="<%= invBrok.getE01FEBPH1()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Telefono 2 :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBPH2" size="20" maxlength="15" value="<%= invBrok.getE01FEBPH2()%>">
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="17%" >
              <div align="right">Facsimil :</div>
            </td>
            <td nowrap width="36%" >
              <input type="text" readonly  name="E01FEBFA1" size="20" maxlength="15" value="<%= invBrok.getE01FEBFA1()%>">
            </td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="33%" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Correo Electronico 1 :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01FEBIA1" size="40" maxlength="40" value="<%= invBrok.getE01FEBIA1()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Correo Electronico 2 :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01FEBIA2" size="40" maxlength="40" value="<%= invBrok.getE01FEBIA2()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Adicional</h4>
  <table  class="tableinfo" width="546">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Retenciones :</div>
            </td>
            <td nowrap width="34%" > 
              <input type="text" name="E01FEBWTH" size="16" 
				  value="<% if (invBrok.getE01FEBWTH().equals("W")) out.print("Retenciones");
							else if (invBrok.getE01FEBWTH().equals("F")) out.print("Forma 1099");
							else if (invBrok.getE01FEBWTH().equals("B")) out.print("Ambas");
							else out.print("Ninguna");%>" 
				readonly>
            </td>
            <td nowrap width="34%" > 
              <div align="right">Via de Pago :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="text" name="E01FEBPVI" size="25" 
				  value="<% if (invBrok.getE01FEBPVI().equals("A")) out.print("Cuenta de Detalle");
							else if (invBrok.getE01FEBPVI().equals("G")) out.print("Cuenta Contable");
							else if (invBrok.getE01FEBPVI().equals("F")) out.print("Transferencia Via FED");
						    else if (invBrok.getE01FEBPVI().equals("T")) out.print("Transferencia Via Telex");
							else if (invBrok.getE01FEBPVI().equals("1")) out.print("Swift MT-100");
							else if (invBrok.getE01FEBPVI().equals("2")) out.print("Swift MT-200");
							else out.print("Ninguno");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Cuenta de Pago :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01FEBCBK" size="2" maxlength="2" value="<%= invBrok.getE01FEBCBK()%>">
                <input type="text" readonly  name="E01FEBCBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01FEBCBK.value,'','','','')" value="<%= invBrok.getE01FEBCBR()%>">
                <input type="text" readonly  name="E01FEBCCY" size="3" maxlength="3" value="<%= invBrok.getE01FEBCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01FEBCBK.value,'','','','')">
                <input type="text" readonly  name="E01FEBCGL" size="15" maxlength="13" value="<%= invBrok.getE01FEBCGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01FEBCBK.value,document.forms[0].E01FEBCCY.value,'','','')" >
                <input type="text" readonly  name="E01FEBCAC" size="13" maxlength="12" value="<%= invBrok.getE01FEBCAC().trim()%>"
			oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01FEBCBK.value,'','','','RT')"  onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01FEBCCN" size="7" maxlength="6"  value="<%= invBrok.getE01FEBCCN().trim()%>"
			oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01FEBCBK.value,'','','','')">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Cargos Fijos :</div>
            </td>
            <td nowrap width="34%" > 
              <input type="text" readonly  name="E01FEBFFA" size="13" maxlength="11" value="<%= invBrok.getE01FEBFFA()%>">
            </td>
            <td nowrap width="34%" > 
              <div align="right">Tasa de Cargos Fijos :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="text" readonly  name="E01FEBPRC" size="13" maxlength="11" value="<%= invBrok.getE01FEBPRC()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Tipo de Cálculo de Cargos :</div>
            </td>
            <td nowrap width="34%" >
              <input type="hidden" name="E01FEBFCT" value="<%= invBrok.getE01FEBFCT()%>">
              <input type="radio" name="CE01FEBFCT" value="F" onClick="document.forms[0].E01FEBFCT.value='F'"
			  <%if(invBrok.getE01FEBFCT().equals("F")) out.print("checked");%> disabled>
              Cargos Fijos 
              <input type="radio" name="CE01FEBFCT" value="P" onClick="document.forms[0].E01FEBFCT.value='P'"
			  <%if(invBrok.getE01FEBFCT().equals("P")) out.print("checked");%> disabled>
              Porciento </td>
            <td nowrap width="34%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="66%" >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Monto Pagado :</div>
            </td>
            <td nowrap width="34%" > 
              <input type="text" readonly  name="E01FEBPYT" size="16" maxlength="15" value="<%= invBrok.getE01FEBPYT()%>" 
			onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="34%" > 
              <div align="right">Monto Retenido :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="text" readonly  name="E01FEBWYT" size="16" maxlength="15" value="<%= invBrok.getE01FEBWYT()%>" 
			onKeyPress="enterDecimal()">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>
