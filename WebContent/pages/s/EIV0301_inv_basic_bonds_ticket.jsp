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
<h3 align="center"> Confirmaci&oacute;n Orden Compra - Venta</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.EIV0301" >
  <input type=HIDDEN name="SCREEN" value="4">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="11%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= inv.getE01IVSCUN()%> - <%= inv.getE01IVSNA1()%> 
              </div>
            </td>
            <td nowrap > 
              <div align="right">N&uacute;mero de Portafolio :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= inv.getE01IVSPRP()%> - <%= inv.getE01IVSPRD()%> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="11%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="29%" > <%= inv.getE01IVSBRK()%> - <%= inv.getE01IVSBRD()%> 
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
  
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap > <%= inv.getE01IVSDOC()%> - <%= inv.getE01IVSDSD()%> </td>
            <td nowrap > 
              <div align="right">Clase de Documento :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left">
                <select name="E01IVSCLS" disabled>
                  <option value="" <%if (inv.getE01IVSCLS().equals("F")||inv.getE01IVSCLS().equals("V")||inv.getE01IVSCLS().equals("C")) { out.print("selected"); }%>></option>
                  <option value="F" <%if (inv.getE01IVSCLS().equals("F")) { out.print("selected"); }%>>Fijo</option>
                  <option value="V" <%if (inv.getE01IVSCLS().equals("V")) { out.print("selected"); }%>>Variable</option>
                  <option value="C" <%if (inv.getE01IVSCLS().equals("C")) { out.print("selected"); }%>>Combinado </option>
                  
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap > <%= inv.getE01IVSCUS()%> </td>
            <td nowrap > 
              <div align="right">Monto de Transacci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= Util.fcolorCCY(inv.getE01IVSTRA())%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap > <%= inv.getE01IVSISI()%> </td>
            <td nowrap > 
              <div align="right">Unidades / Valor Nominal :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= inv.getE01IVSCTI()%> / <%= Util.fcolorCCY(inv.getE01IVSVNO())%> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap > <%= inv.getE01IVSRTE()%> </td>
            <td nowrap > 
              <div align="right">Precio :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= inv.getE01IVSPRC()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Ultimo Pago de Intereses :</div>
            </td>
            <td nowrap > <%= Util.formatDate(inv.getE01IVSLPD(),inv.getE01IVSLPM(),inv.getE01IVSLPY())%> 
            </td>
            <td nowrap > 
              <div align="right">Intereses Acumulados :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= Util.fcolorCCY(inv.getE01IVSIAL())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap > <%= Util.formatDate(inv.getE01IVSLID(),inv.getE01IVSLIM(),inv.getE01IVSLIY())%> 
            </td>
            <td nowrap > 
              <div align="right">Comisi&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= Util.fcolorCCY(inv.getE01IVSTGV())%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap > <%= Util.formatDate(inv.getE01IVSIND(),inv.getE01IVSINM(),inv.getE01IVSINY())%> 
            </td>
            <td nowrap > 
              <div align="right">Monto Total :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> <%= Util.fcolorCCY(inv.getE01IVSTOT())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Orden :</div>
            </td>
            <td nowrap > <%= inv.getE01IVSTIN()%> </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2"> 
              <div align="left"> </div>
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
                <select name="E01IVSFPG" disabled>
                  <option value="" <%if (inv.getE01IVSFPG().equals("1")||inv.getE01IVSFPG().equals("2")||inv.getE01IVSFPG().equals("3")
										 ||inv.getE01IVSFPG().equals("4")||inv.getE01IVSFPG().equals("5") ||inv.getE01IVSFPG().equals("6")
										 ||inv.getE01IVSFPG().equals("7")||inv.getE01IVSFPG().equals("8") ||inv.getE01IVSFPG().equals("N")) { out.print("selected"); }%>></option>
                  <option value="1" <%if (inv.getE01IVSFPG().equals("1")) { out.print("selected"); }%>>Cuenta de Crédito</option>
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
              <div align="center">Cuenta Contable</div>
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
              <div align="center"> <%= inv.getE01IVSRBK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= inv.getE01IVSRBR()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= inv.getE01IVSRCY()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= inv.getE01IVSRGL()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= inv.getE01IVSRAC()%> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  </form>
</body>
</html>
