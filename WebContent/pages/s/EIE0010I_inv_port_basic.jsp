<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Management Portfolio System</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="invPort" class="datapro.eibs.beans.EIE001001Message"  scope="session" />

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


  builtNewMenu(inv_i_port_opt);
  initMenu();

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
  <h3>Portafolio de <%= invPort.getD01CUSNA1().trim()%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_port_basic.jsp,EIE0010"> 
  </h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0010" >
  <h4>Informaci&oacute;n B&aacute;sica 
    <input type="hidden" name="SCREEN" value="2" >
  </h4>
  <table border="0" width="100%">
    <tr id="trclear"> 
      <td width="82%"> 
        <div align="right">Status :</div>
      </td>
      <td width="18%"><% if(invPort.getE01PRFFL1().equals("P")) out.print("Pendiente");
              				else if(invPort.getE01PRFFL1().equals("A")) out.print("Activo");
							else if(invPort.getE01PRFFL1().equals("I")) out.print("Inactivo");
							else out.print("");%></td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly  name="E01PRFCUN" size="12" maxlength="9" value="<%= invPort.getE01PRFCUN().trim()%>" >
              <input type="text" readonly  name="D01CUSNA1" size="35" maxlength="35" value="<%= invPort.getD01CUSNA1().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Portafolio / Descripción :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRFNUM" size="12" maxlength="9" value="<%= invPort.getE01PRFNUM().trim()%>" >
              / 
              <input type="text" readonly  name="E01PRFDSC" size="35" maxlength="35" value="<%= invPort.getE01PRFDSC()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Oficial de Cuenta :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly  name="E01PRFOFC" size="6" maxlength="4" value="<%= invPort.getE01PRFOFC().trim()%>">
              <input type="text" readonly  name="D01OFCNME" size="35" maxlength="35" value="<%= invPort.getD01OFCNME()%>" >
            </td>
          </tr>
          <% 
  			if (userPO.getPurpose().equals("NEW")) {
		   %> 
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Cuenta Transaccional :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRFHAC" size="12" maxlength="9" value="<%= invPort.getE01PRFHAC()%>">
            </td>
          </tr>
          <tr id="trdark"> <% } else { %> 
          <tr id="trclear"> <% } %> 
            <td nowrap width="34%" > 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRFOP1" size="3" maxlength="2" value="<%= invPort.getE01PRFOP1().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly  name="E01PRFOP2" size="3" maxlength="2" value="<%= invPort.getE01PRFOP2().trim()%>" onkeypress="enterInteger()">
              <input type="text" readonly  name="E01PRFOP3" size="3" maxlength="2" value="<%= invPort.getE01PRFOP3().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Moneda del Portafolio :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01PRFVCY" size="4" maxlength="3" value="<%= invPort.getE01PRFVCY().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Tabla de Custodia :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01PRFCUT" size="4" maxlength="2" value="<%= invPort.getE01PRFCUT()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Via de Confirmaci&oacute;n:</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <select disabled  name="E01PRFCNF">
                  <option value="N" <%if (invPort.getE01PRFCNF().equals("N")) { out.print("selected"); }%>>Ninguna</option>
                  <option value="E" <%if (invPort.getE01PRFCNF().equals("E")) { out.print("selected"); }%>>Correo Electronico</option>
                  <option value="F" <%if (invPort.getE01PRFCNF().equals("F")) { out.print("selected"); }%>>Fax</option>
                  <option value="P" <%if (invPort.getE01PRFCNF().equals("P")) { out.print("selected"); }%>>Impresora</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="34%" >
              <div align="right">Tipo de Portafolio :</div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="radio" disabled  name="E01PRFTYP" value="D" onClick="document.forms[0].E01PRFTYP.value='D'" <%if(invPort.getE01PRFTYP().equals("D")) out.print("checked");%>>
                Discrecionario 
                <input type="radio" disabled  name="E01PRFTYP" value="N" onClick="document.forms[0].E01PRFTYP.value='N'" <%if(invPort.getE01PRFTYP().equals("N")) out.print("checked");%>>
                No Discrecionario</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información de Inversión</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark">
            <td nowrap colspan=2 > 
              <div align="left"><b>Invertir en :</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Renta Fija :</div>
            </td>
            <td nowrap >              
              <input type="radio" disabled  name="E01PRFFXR" value="Y" onClick="document.forms[0].E01PRFFXR.value='Y'" <%if(invPort.getE01PRFFXR().equals("Y")) out.print("checked");%>>
              Sí
              <input type="radio" disabled  name="E01PRFFXR" value="N" onClick="document.forms[0].E01PRFFXR.value='N'" <%if(invPort.getE01PRFFXR().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Renta Variable :</div>
            </td>
            <td nowrap>               
              <input type="radio" disabled  name="E01PRFVIR" value="Y" <%if(invPort.getE01PRFVIR().equals("Y")) out.print("checked");%>>
              Sí 
              <input type="radio" disabled  name="E01PRFVIR" value="N" <%if(invPort.getE01PRFVIR().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="34%" > 
              <div align="right">Productos del Banco :</div>
            </td>
            <td nowrap>               
              <input type="radio" disabled  name="E01PRFBPR" value="Y" <%if(invPort.getE01PRFBPR().equals("Y")) out.print("checked");%>>
              Sí 
              <input type="radio" disabled  name="E01PRFBPR" value="N" <%if(invPort.getE01PRFBPR().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 
  <h4>Cuentas de Pago </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"><b>Cuentas de Pago</b></div>
            </td>
            <td nowrap> 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Agencia</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap> 
              <div align="center">Cuenta Contable</div>
            </td>
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
            <td nowrap> 
              <div align="center">Centro de Costo</div>
            </td>
          </tr>
          <tr> 
            <td nowrap id="trdark"> 
              <div align="right">Intereses (Cupones) :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFIBK" size="2" maxlength="2" value="<%= invPort.getE01PRFIBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFIBR" size="3" maxlength="3" value="<%= invPort.getE01PRFIBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRFIBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFICY" size="3" maxlength="3" value="<%= invPort.getE01PRFICY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRFIBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFIGL" size="15" maxlength="13" value="<%= invPort.getE01PRFIGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRFIBK.value,document.forms[0].E01PRFICY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFIAC" size="12" maxlength="9" value="<%= invPort.getE01PRFIAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01PRFIBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFICC" size="12" maxlength="9" value="<%= invPort.getE01PRFICC()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRFIBK.value,'','','','')" >
              </div>
            </td>
          </tr>
          <tr> 
            <td nowrap id="trdark"> 
              <div align="right">Dividendos :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDBK" size="2" maxlength="2" value="<%= invPort.getE01PRFDBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDBR" size="3" maxlength="3" value="<%= invPort.getE01PRFDBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRFDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDCY" size="3" maxlength="3" value="<%= invPort.getE01PRFDCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRFDBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDGL" size="15" maxlength="13" value="<%= invPort.getE01PRFDGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRFDBK.value,document.forms[0].E01PRFDCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDAC" size="12" maxlength="9" value="<%= invPort.getE01PRFDAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01PRFDBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFDCC" size="12" maxlength="9" value="<%= invPort.getE01PRFDCC()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRFDBK.value,'','','','')" >
              </div>
            </td>
          </tr>
          <tr > 
            <td nowrap id="trdark"> 
              <div align="right">Capital :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCBK" size="2" maxlength="2" value="<%= invPort.getE01PRFCBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCBR" size="3" maxlength="3" value="<%= invPort.getE01PRFCBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRFCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCCY" size="3" maxlength="3" value="<%= invPort.getE01PRFCCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRFCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCGL" size="15" maxlength="13" value="<%= invPort.getE01PRFCGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRFCBK.value,document.forms[0].E01PRFCCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCAC" size="12" maxlength="9" value="<%= invPort.getE01PRFCAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01PRFCBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" readonly  name="E01PRFCCC" size="12" maxlength="9" value="<%= invPort.getE01PRFCCC()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRFCBK.value,'','','','')" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Adicional
  </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark">
            <td nowrap colspan="2">Nombre Legal :</td>
          </tr>
          <tr id="trdark">
            <td nowrap colspan="2">
              <div align="center">
                <textarea name="E01LEGNME" cols="40" rows="9" readonly> <%= invPort.getE01LEGNME() %> </textarea>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="left">Comentarios :</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan=2 align=center> 
              <input type="text" readonly  name="E01PRFCM1" size="80" maxlength="80" value="<%= invPort.getE01PRFCM1() %>">
              <br>
              <input type="text" readonly  name="E01PRFCM2" size="80" maxlength="80" value="<%= invPort.getE01PRFCM2() %>">
              <br>
              <input type="text" readonly  name="E01PRFCM3" size="80" maxlength="80" value="<%= invPort.getE01PRFCM3() %>">
              <br>
              <input type="text" readonly  name="E01PRFCM4" size="80" maxlength="80" value="<%= invPort.getE01PRFCM4() %>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>
