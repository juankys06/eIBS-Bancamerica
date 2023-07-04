<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion </title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="fra" class="datapro.eibs.beans.ETR0120DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "trOption" class= "datapro.eibs.beans.TrOption"  scope="session" />


</head>

<body>
<% 
String sTitle = "FRA";
try {
   sTitle = trOption.getHeader2();
} catch (Exception e) {
   sTitle = "FRA";
}   
if (sTitle == null) sTitle = "FRA";

%>

<h3 align="center">Confirmación - <%= sTitle%></h3>
<hr size="4">
<form>
<table class="tableinfo" width="314">
  <tr > 
    <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%" border="0" >
        <tr id="trclear"> 
          <td nowrap > 
            <div align="right"><b>Contraparte :</b></div>
          </td>
          <td nowrap colspan="3" > 
            <div align="left"> 
              <input type="hidden" name="D01WFRCP1"  value="<%= fra.getD01WFRCP1()%>" readonly>
              <%= fra.getD01WFRCP1()%> </div>
          </td>
        </tr>
        <tr id="trclear"> 
          <td nowrap > 
            <div align="right"><b>Localización :</b></div>
          </td>
          <td nowrap colspan="3" > 
            <div align="left"> 
              <input type="hidden" name="D01WFRCP2"  value="<%= fra.getD01WFRCP2()%>" readonly>
              <%= fra.getD01WFRCP2()%> </div>
          </td>
        </tr>
        <tr id="trclear"> 
          <td nowrap >&nbsp;</td>
          <td nowrap colspan="3" > 
            <input type="hidden" name="D01WFRCP3"  value="<%= fra.getD01WFRCP3()%>" readonly>
            <%= fra.getD01WFRCP3()%> </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br><table  class="tableinfo">
  <tr > 
    <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear">
            <td nowrap >
              <div align="right">Fecha :</div>
            </td>
            <td nowrap >
              <div align="left"><%= Util.formatDate(fra.getE01WFRVD1(),fra.getE01WFRVD2(),fra.getE01WFRVD3())%></div>
            </td>
            <td nowrap >
              <div align="right">Hora :</div>
            </td>
            <td nowrap colspan="2">
              <div align="left"><%= userPO.getHeader8()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo Contrato :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRITP().trim()%></td>
            <td nowrap > 
              <div align="right">Action Taken :</div>
            </td>
            <td nowrap colspan="2">
              <% if(fra.getE01WFRSBT().equals("PU")) out.print("Compra");
						else if(fra.getE01WFRSBT().equals("SL")) out.print("Venta");%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRCCY().trim()%></td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Monto Teórico :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fra.getE01WFROAM())%> </td>
            <td nowrap > 
              <div align="right">Tasa Contrato : </div>
            </td>
            <td nowrap colspan="2"><%= fra.getE01WFRRTE().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Contrato :</div>
            </td>
            <td nowrap ><%= Util.formatDate(fra.getE01WFRDD1(),fra.getE01WFRDD2(),fra.getE01WFRDD3())%> 
            </td>
            <td nowrap align="right">Value Date :</td>
            <td nowrap colspan="2"><%= Util.formatDate(fra.getE01WFRVD1(),fra.getE01WFRVD2(),fra.getE01WFRVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">No. Referencia :</div>
            </td>
            <td nowrap ><%= fra.getE01WFRREF().trim()%> </td>
            <td nowrap > 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap colspan="2"> <%= Util.formatDate(fra.getE01WFRMA1(),fra.getE01WFRMA2(),fra.getE01WFRMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01WFROT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01WFROT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Por :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getH01USERID().trim()%> - <%= fra.getD01USRDSC()%></td>
          </tr>
        </table>
    </td>
  </tr>
</table>
  <br>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Límite Disponible </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Final </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right"><b>Línea de Crédito</b></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMAMT())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMEND())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
<SCRIPT Language="javascript">
  eIBSPrint();
</SCRIPT>
</body>
</html>
