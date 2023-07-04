<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Inversiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invComm" class="datapro.eibs.beans.EIE000001Message"  scope="session" />

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
  <h3>Bonds Commissions Inquiry<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_bonds.jsp,EIE0000"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0000" >
  <h4>Informaci�n B�sica 
    <input type="hidden" name="SCREEN"  value="2" >
  </h4>
  <table  class="tableinfo" width="546">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">N�mero de Tabla :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="hidden" name="E01COMTBL"  value="<%= invComm.getE01COMTBL()%>" >
              <%= invComm.getE01COMTBL()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="hidden" name="E01COMTYP"  value="<%= invComm.getE01COMTYP()%>" >
              <%= invComm.getE01COMTYP()%> - <% if(invComm.getE01COMTYP().equals("BND")) out.print("BONOS");
              				else if(invComm.getE01COMTYP().equals("EQT")) out.print("ACCIONES");
							else if(invComm.getE01COMTYP().equals("PFS")) out.print("ACCIONES PREFERENTES");
							else if(invComm.getE01COMTYP().equals("CST")) out.print("CUSTODIOS");
							else out.print("FONDOS MUTUALES");%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">N�mero de Cliente :</div>
            </td>
            <td nowrap width="66%" >
              <input type="hidden" name="E01COMCUN"  value="<%= invComm.getE01COMCUN().trim()%>" >
              <%= invComm.getE01COMCUN().trim()%> - <%= Util.fcolorCCY(invComm.getD01DESCDE())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Moneda de Comisi�n :</div>
            </td>
            <td nowrap width="66%" ><%= invComm.getE01COMCCY()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Descripci�n de Tabla :</div>
            </td>
            <td nowrap width="66%" > <%= invComm.getE01COMDE0()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci�n Adicional</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center">Monto</div>
            </td>
            <td nowrap >Factor</td>
            <td nowrap >Frec.</td>
            <td nowrap > 
              <div align="center">M�nimo </div>
            </td>
            <td nowrap > 
              <div align="center">M�ximo </div>
            </td>
            <td nowrap > 
              <div align="center">Banco</div>
            </td>
            <td nowrap > 
              <div align="center">CCY</div>
            </td>
            <td nowrap > 
              <div align="center">C/Contable</div>
            </td>
            <td nowrap > 
              <div align="center">Narrativa</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA1" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA1" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR1" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI1" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA1" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK1" size="2" maxlength="2" value="<%= invComm.getE01COMBK1()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY1" size="3" maxlength="3" value="<%= invComm.getE01COMCY1()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK1.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL1" size="15" maxlength="13" value="<%= invComm.getE01COMGL1()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK1.value,document.forms[0].E01COMCY1.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE1" size="25" maxlength="15" value="<%= invComm.getE01COMDE1()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA2" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA2" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR2" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI2" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA2" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK2" size="2" maxlength="2" value="<%= invComm.getE01COMBK2()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY2" size="3" maxlength="3" value="<%= invComm.getE01COMCY2()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK2.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL2" size="15" maxlength="13" value="<%= invComm.getE01COMGL2()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK2.value,document.forms[0].E01COMCY2.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE2" size="25" maxlength="15" value="<%= invComm.getE01COMDE2()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA3" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA3" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR3" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI3" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA3" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK3" size="2" maxlength="2" value="<%= invComm.getE01COMBK3()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY3" size="3" maxlength="3" value="<%= invComm.getE01COMCY3()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK3.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL3" size="15" maxlength="13" value="<%= invComm.getE01COMGL3()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK3.value,document.forms[0].E01COMCY3.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE3" size="25" maxlength="15" value="<%= invComm.getE01COMDE3()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA4" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA4" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR4" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI4" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA4" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK4" size="2" maxlength="2" value="<%= invComm.getE01COMBK4()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY4" size="3" maxlength="3" value="<%= invComm.getE01COMCY4()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK4.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL4" size="15" maxlength="13" value="<%= invComm.getE01COMGL4()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK4.value,document.forms[0].E01COMCY4.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE4" size="25" maxlength="15" value="<%= invComm.getE01COMDE4()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA5" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA5" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR5" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI5" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA5" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK5" size="2" maxlength="2" value="<%= invComm.getE01COMBK5()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY5" size="3" maxlength="3" value="<%= invComm.getE01COMCY5()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK5.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL5" size="15" maxlength="13" value="<%= invComm.getE01COMGL5()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK5.value,document.forms[0].E01COMCY5.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE5" size="25" maxlength="15" value="<%= invComm.getE01COMDE5()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA6" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA6" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR6" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI6" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA6" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK6" size="2" maxlength="2" value="<%= invComm.getE01COMBK6()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY6" size="3" maxlength="3" value="<%= invComm.getE01COMCY6()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK6.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL6" size="15" maxlength="13" value="<%= invComm.getE01COMGL6()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK6.value,document.forms[0].E01COMCY6.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE6" size="25" maxlength="15" value="<%= invComm.getE01COMDE6()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA7" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA7" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR7" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI7" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA7" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK7" size="2" maxlength="2" value="<%= invComm.getE01COMBK7()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY7" size="3" maxlength="3" value="<%= invComm.getE01COMCY7()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK7.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL7" size="15" maxlength="13" value="<%= invComm.getE01COMGL7()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK7.value,document.forms[0].E01COMCY7.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE7" size="25" maxlength="15" value="<%= invComm.getE01COMDE7()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA8" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA8" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR8" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI8" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA8" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK8" size="2" maxlength="2" value="<%= invComm.getE01COMBK8()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY8" size="3" maxlength="3" value="<%= invComm.getE01COMCY8()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK8.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL8" size="15" maxlength="13" value="<%= invComm.getE01COMGL8()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK8.value,document.forms[0].E01COMCY8.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE8" size="25" maxlength="15" value="<%= invComm.getE01COMDE8()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMVA9" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMVA9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFA9" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFactHelp,this.name)" value="<%= invComm.getE01COMFA9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMFR9" size="3" maxlength="1"
                oncontextmenu="showPopUp(staticFrecHelp,this.name)" value="<%= invComm.getE01COMFR9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMI9" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMI9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMMA9" size="16" maxlength="15" onKeyPress="enterDecimal()" value="<%= invComm.getE01COMMA9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMBK9" size="2" maxlength="2" value="<%= invComm.getE01COMBK9()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMCY9" size="3" maxlength="3" value="<%= invComm.getE01COMCY9()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01COMBK9.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01COMGL9" size="15" maxlength="13" value="<%= invComm.getE01COMGL9()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01COMBK9.value,document.forms[0].E01COMCY9.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01COMDE9" size="25" maxlength="15" value="<%= invComm.getE01COMDE9()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>
