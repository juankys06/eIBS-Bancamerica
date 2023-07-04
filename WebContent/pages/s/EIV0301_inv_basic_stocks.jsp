<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Investments Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="inv" class="datapro.eibs.beans.EIV030101Message"  scope="session" />

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
<h3 align="center"> Portafolio - Orden de Compra / Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_basic_stocks.jsp, EIV0301"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIV0301" >
  <input type=HIDDEN name="SCREEN" value="6">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="11%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01IVSCUN"  size = "12" value="<%= inv.getE01IVSCUN()%>" readonly maxlength="12">
                <input type="text" name="E01IVSNA1"  size = "35" value="<%= inv.getE01IVSNA1()%>" readonly maxlength="35">
              </div>
            </td>
            <td nowrap > 
              <div align="right">N&uacute;mero de Portafolio :</div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" name="E01IVSPRP" size="12" maxlength="12" value="<%= inv.getE01IVSPRP()%>" readonly>
                <input type="text" name="E01IVSPRD" size="35" maxlength="35" value="<%= inv.getE01IVSPRD()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="11%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="29%" > 
              <input type="text" name="E01IVSBRK" size="4" maxlength="3" value="<%= inv.getE01IVSBRK()%>" readonly>
              <input type="text" name="E01IVSBRD" size="15" maxlength="15" value="<%= inv.getE01IVSBRD()%>" readonly>
            </td>
            <td nowrap width="20%" > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="40%" > 
              <input type="hidden" name="E01IVSTRN" value="<%= inv.getE01IVSTRN()%>">
              <input type="radio" name="CE01IVSTRN" value="1" onClick="document.forms[0].E01IVSTRN.value='1'"
			  <%if(inv.getE01IVSTRN().equals("1")) out.print("checked");%> disabled>
              Compra 
              <input type="radio" name="CE01IVSTRN" value="2" onClick="document.forms[0].E01IVSTRN.value='2'"
			  <%if(inv.getE01IVSTRN().equals("2")) out.print("checked");%> disabled>
              Venta</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSDOC" size="4" maxlength="2" value="<%= inv.getE01IVSDOC()%>" 
			  onKeyPress="enterDecimal()">
              <input type="text" name="E01IVSDSD" size="35" maxlength="35" value="<%= inv.getE01IVSDSD()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Clase de Documento :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <select name="E01IVSCLS">
                  <option value="" <%if (inv.getE01IVSCLS().equals("F")||inv.getE01IVSCLS().equals("V")||inv.getE01IVSCLS().equals("C")) { out.print("selected"); }%>></option>
                  <option value="F" <%if (inv.getE01IVSCLS().equals("F")) { out.print("selected"); }%>>Fijo</option>
                  <option value="V" <%if (inv.getE01IVSCLS().equals("V")) { out.print("selected"); }%>>Variable</option>
                  <option value="C" <%if (inv.getE01IVSCLS().equals("C")) { out.print("selected"); }%>>Combinado 
                  </option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSCUS" size="15" maxlength="12" value="<%= inv.getE01IVSCUS()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Monto Transacci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" name="E01IVSTRA" size="15" maxlength="13" value="<%= inv.getE01IVSTRA()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSISI" size="15" maxlength="12" value="<%= inv.getE01IVSISI()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Unidades / Valor Nominal :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" name="E01IVSCTI" size="8" maxlength="7" value="<%= inv.getE01IVSCTI()%>" 
			  onKeyPress="enterDecimal()">
                / 
                <input type="text" name="E01IVSVNO" size="16" maxlength="15" value="<%= inv.getE01IVSVNO()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSLID" size="3" maxlength="2" value="<%= inv.getE01IVSLID()%>" 
			  >
              <input type="text" name="E01IVSLIM" size="3" maxlength="2" value="<%= inv.getE01IVSLIM()%>" 
			  >
              <input type="text" name="E01IVSLIY" size="3" maxlength="2" value="<%= inv.getE01IVSLIY()%>" 
			  >
            </td>
            <td nowrap > 
              <div align="right">Precio :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" name="E01IVSPRC" size="15" maxlength="13" value="<%= inv.getE01IVSPRC()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSIND" size="3" maxlength="2" value="<%= inv.getE01IVSIND()%>" 
			  >
              <input type="text" name="E01IVSINM" size="3" maxlength="2" value="<%= inv.getE01IVSINM()%>" 
			  >
              <input type="text" name="E01IVSINY" size="3" maxlength="2" value="<%= inv.getE01IVSINY()%>" 
			  >
            </td>
            <td nowrap > 
              <div align="right">Comisi&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" name="E01IVSTGV" size="15" maxlength="13" value="<%= inv.getE01IVSTGV()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Orden :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01IVSTIN" size="3" maxlength="2" value="<%= inv.getE01IVSTIN()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Monto Total :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" name="E01IVSTOT" size="15" maxlength="13" value="<%= inv.getE01IVSTOT()%>" 
			  onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Compra / Venta</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo de Pago :</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left">
                <select name="E01IVSFPG">
                  <option value="" <%if (inv.getE01IVSFPG().equals("1")||inv.getE01IVSFPG().equals("2")||inv.getE01IVSFPG().equals("3")
										 ||inv.getE01IVSFPG().equals("4")||inv.getE01IVSFPG().equals("5") ||inv.getE01IVSFPG().equals("6")
										 ||inv.getE01IVSFPG().equals("7")||inv.getE01IVSFPG().equals("8") ||inv.getE01IVSFPG().equals("N")) { out.print("selected"); }%>></option>
                  <option value="1" <%if (inv.getE01IVSFPG().equals("1")) { out.print("selected"); }%>>Cuenta Crédito</option>
                  <option value="2" <%if (inv.getE01IVSFPG().equals("2")) { out.print("selected"); }%>>Cheque Oficial</option>
                  <option value="3" <%if (inv.getE01IVSFPG().equals("3")) { out.print("selected"); }%>>Cuenta Contable </option>
                  <option value="4" <%if (inv.getE01IVSFPG().equals("4")) { out.print("selected"); }%>>Transferencia FED </option>
				  <option value="5" <%if (inv.getE01IVSFPG().equals("5")) { out.print("selected"); }%>>Transferencia Telex</option>
                  <option value="6" <%if (inv.getE01IVSFPG().equals("6")) { out.print("selected"); }%>>Swift Formato MT-100</option>
				  <option value="7" <%if (inv.getE01IVSFPG().equals("7")) { out.print("selected"); }%>>Swift Formato MT-200</option>
				  <option value="8" <%if (inv.getE01IVSFPG().equals("6")) { out.print("selected"); }%>>Swift Formato MT-202</option>
				  <option value="N" <%if (inv.getE01IVSFPG().equals("N")) { out.print("selected"); }%>>Ninguno</option>
                </select>

			  </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap > 
              <div align="center">Moneda</div>
            </td>
            <td nowrap > 
              <div align="center">Cuenta Contable </div>
            </td>
            <td nowrap > 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta de Pago :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01IVSRBK" size="2" maxlength="2" value="<%= inv.getE01IVSRBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01IVSRBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01IVSRBK.value,'','','','')" value="<%= inv.getE01IVSRBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01IVSRCY" size="3" maxlength="3" value="<%= inv.getE01IVSRCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01IVSRBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01IVSRGL" size="15" maxlength="13" value="<%= inv.getE01IVSRGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01IVSRBK.value,document.forms[0].E01IVSRCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01IVSRAC" size="12" maxlength="9" value="<%= inv.getE01IVSRAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01IVSRBK.value,'','','','RT')" >
              </div>
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
          <input type="checkbox" name="H01FLGWK1" value="1" <% if(inv.getH01FLGWK1().equals("1")){ out.print("checked");} %>>
          Aceptar con Errores</div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
	      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div> 
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>
