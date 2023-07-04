<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>
<h3 align="center">Confirmaci&oacute;n - Papel Comercial - Back Office </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120D" >
<%
String ogen = "";
if (deal.getE01DLSSTS().equals("T")) {
	ogen = "Tesorería";
} else if (deal.getE01DLSSTS().equals("F")) {
	ogen = "Fideicomiso";
}  else if (deal.getE01DLSSTS().equals("E")) {
	ogen = "FEM";
}  else if (deal.getE01DLSSTS().equals("R")) {
	ogen = "Terceros";
}
%>
  <table class="tableinfo" width="100%" >
    <tr id="trclear"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>" >
                <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" >
              <%= deal.getD01DLSCP2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>" >
              <%= deal.getD01DLSCP3()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>N&uacute;mero de Cuenta :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01DLSACC"  value="<%= deal.getE01DLSACC()%>" >
              <%= deal.getE01DLSACC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" >
              <%= ogen%> </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Pacto :</div>
            </td>
            <td nowrap >
              <div align="left"><%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></div>
            </td>
            <td nowrap >
              <div align="right">Hora :</div>
            </td>
            <td nowrap ><%= userPO.getHeader8()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" >  
            </td>
            <td nowrap width="18%" > 
              <div align="right"> Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="20%" ><% if(deal.getE01DLSSBT().equals("PU")) out.print("New Purchase");
					   else if(deal.getE01DLSSBT().equals("AD")) out.print("Additional Purchase");
						else out.print("Sale");%> </td>
          </tr>
          <% if(!deal.getE01DLSSBT().equals("PU")){ %> 
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" >&nbsp; </td>
            <td nowrap width="18%" > 
              <div align="right">N&uacute;mero de Contrato :</div>
            </td>
            <td nowrap width="20%"><%= deal.getE01DLSHEM() %> </td>
          </tr>
          <% }%> 
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE01DLSREF().trim()%> </td>
            <td nowrap width="18%" > 
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= deal.getE01DLSACC() %> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="23%" > <%= deal.getE01DLSCCY().trim()%> </td>
            <td nowrap align="right" width="18%">&nbsp;</td>
            <td nowrap width="20%">&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Valor Nominal : </div>
            </td>
            <td nowrap width="23%" ><%= Util.fcolorCCY(deal.getE01DLSAMN())%> 
            </td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Tasa de Descuento :</div>
            </td>
            <td nowrap width="20%"><%= deal.getE01DLSRA1().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Monto Neto :</div>
            </td>
            <td nowrap width="23%" > <%= Util.fcolorCCY(deal.getE01DLSAM1())%></td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Rendimiento :</div>
            </td>
            <td nowrap width="20%"><%= deal.getE01DLSRA2().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha del Contrato :</div>
            </td>
            <td nowrap width="23%" ><%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%> 
            </td>
            <td nowrap align="right" width="18%">Fecha Valor :</td>
            <td nowrap width="20%"> <%= Util.formatDate(deal.getE01DLSVD1(),deal.getE01DLSVD2(),deal.getE01DLSVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap width="23%" > <%= deal.getE01DLSTHR().trim()%> </td>
            <td nowrap align="right" width="18%">Fecha de Vencimiento :</td>
            <td nowrap width="20%"> <%= Util.formatDate(deal.getE01DLSMA1(),deal.getE01DLSMA2(),deal.getE01DLSMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE01DLSOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE01DLSOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE01DLSDID().trim()%> - <%= deal.getD01USRDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Producto </div>
            </td>
            <td nowrap width="23%" > <%= deal.getE01DLSPRO()%> - <%= deal.getD01PRDDSC()%> 
            </td>
            <td nowrap width="18%" > 
              <div align="right">Tasa Moneda Extranjera :</div>
            </td>
            <td nowrap width="20%" > <%= deal.getE01DLSRA3().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Per&iacute;odo de Cup&oacute;n :</div>
            </td>
            <td nowrap width="23%" > <%= deal.getE01DLSROY().trim()%> -
			 <% if(deal.getE01DLSODA().equals("P")) out.print(" Periodos Mensuales(No incluye último día)");
				   else if(deal.getE01DLSODA().equals("I")) out.print(" Periodos Mensuales(Incluye último día)");
					else if(deal.getE01DLSODA().equals("M")) out.print(" Mensual(Ultimo Día del Mes)");
						else if(deal.getE01DLSODA().equals("D")) out.print(" Mensual(Día del mes)");
							else out.print(" None");%>               
			</td>
            <td nowrap width="18%" > 
              <div align="right">Ultima Fecha Pago Cup&oacute;n :</div>
            </td>
            <td nowrap width="20%"> <%= Util.formatDate(deal.getE01DLSLI1(),deal.getE01DLSLI2(),deal.getE01DLSLI3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tasa de Cup&oacute;n :</div>
            </td>
            <td nowrap width="23%" > <%= deal.getE01DLSRA4().trim()%> </td>
            <td nowrap width="18%" > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> <% if(deal.getE01DLSRRT().equals("1")) out.print("Reevaluate Gain and Losses Daily ");
				 else if(deal.getE01DLSRRT().equals("2")) out.print("Reevaluate Only Losses Daily");
				 else if(deal.getE01DLSRRT().equals("3")) out.print("Reevaluate Gain and Losses Monthly");
				 else if(deal.getE01DLSRRT().equals("4")) out.print("Reevaluate Only Losses Monthly");
				 else out.print("No Reevaluation");%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="23%" > <% if(deal.getE01DLSDLI().equals("P")) out.print("Print Notification");
				 else if(deal.getE01DLSDLI().equals("S")) out.print("Send Notification Via Swift");
				 else if(deal.getE01DLSDLI().equals("H")) out.print("Send Notification Via Chip");
				 else if(deal.getE01DLSDLI().equals("T")) out.print("Send Notification Via Telex");
				 else if(deal.getE01DLSDLI().equals("F")) out.print("Send Notification Via Fax");
				 else out.print("No Notification");%> </td>
            <td nowrap align="right" width="18%">Forma de Pago :</td>
            <td nowrap width="20%"> <% if(deal.getE01DLSPVI().equals("F")) out.print("Payment Via Fed");
				 else if(deal.getE01DLSPVI().equals("1")) out.print("Payment Via Swift MT-100");
				 else if(deal.getE01DLSPVI().equals("2")) out.print("Payment Via Swift MT - 200");
				 else if(deal.getE01DLSPVI().equals("3")) out.print("Payment Via Swift MT- 202");
				 else if(deal.getE01DLSPVI().equals("T")) out.print("Payment Via Telex");
				 else out.print("None");%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cuenta Contrapartida</h4>
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="14%"> 
        <div align="center">Banco</div>
      </td>
      <td nowrap width="17%"> 
        <div align="center">Sucursal</div>
      </td>
      <td nowrap width="22%"> 
        <div align="center">Moneda</div>
      </td>
      <td nowrap width="15%"> 
        <div align="center">Cuenta Contable </div>
      </td>
      <td nowrap width="15%"> 
        <div align="center">Referencia</div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="14%" > 
        <div align="center"> <%= deal.getE01DLSOFB().trim()%> </div>
      </td>
      <td nowrap width="17%" > 
        <div align="center"> <%= deal.getE01DLSOCR().trim()%> </div>
      </td>
      <td nowrap width="22%" > 
        <div align="center"> <%= deal.getE01DLSOCY().trim()%> </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"><%= deal.getE01DLSOFA().trim()%></div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> <%= deal.getE01DLSOAC().trim()%></div>
      </td>
    </tr>
  </table>
  <h4>Cuenta de Repago</h4>
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="14%"> 
        <div align="center">Banco</div>
      </td>
      <td nowrap width="17%"> 
        <div align="center">Sucursal</div>
      </td>
      <td nowrap width="22%"> 
        <div align="center">Moneda</div>
      </td>
      <td nowrap width="15%">
        <div align="center">Cuenta Contable</div>
      </td>
      <td nowrap width="15%"> 
        <div align="center">Referencia</div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="14%" > 
        <div align="center"> <%= deal.getE01DLSREB().trim()%> </div>
      </td>
      <td nowrap width="17%" > 
        <div align="center"> <%= deal.getE01DLSRPR().trim()%> </div>
      </td>
      <td nowrap width="22%" > 
        <div align="center"> <%= deal.getE01DLSRPC().trim()%> </div>
      </td>
      <td nowrap width="15%" >
        <div align="center"><%= deal.getE01DLSRAC().trim()%></div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"><%= deal.getE01DLSRCC().trim()%> </div>
      </td>
    </tr>
  </table>
  <h4>L&iacute;mites</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto L&iacute;mite</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Disponible</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Final</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>L&iacute;neas de Cr&eacute;dito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAMT())%>:</div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getD01LIMEND())%></b></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
