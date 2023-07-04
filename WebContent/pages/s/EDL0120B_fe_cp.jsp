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
<h3 align="center">Papel Comercial - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp.jsp,EDL0120B"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120B" >
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
  <h4>Informaci&oacute;n B&aacute;sica 
    <input type="hidden" name="SCREEN"  value="4" >
    <input type="hidden" name="E01DLSTYP"  value="<%= deal.getE01DLSTYP()%>" >
  </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="right">Fecha Pacto :<%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></div>
            </td>
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
						else out.print("Sale");%> 
              <input type="hidden" name="E01DLSSBT"  value="<%= deal.getE01DLSSBT()%>" >
            </td>
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
         <%} %>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE01DLSREF().trim()%> </td>
            <td nowrap width="18%" >&nbsp;</td>
            <td nowrap width="20%"> 
              <div align="left"> </div>
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
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSPRO" size="3" maxlength="1" value="<%= deal.getE01DLSPRO()%>" 
			  >
              <input type="text" name="D01PRDDSC" size="30" maxlength="35" value="<%= deal.getD01PRDDSC()%>" 
			  >
              <a href="javascript:GetProductT('E01DLSPRO','D01PRDDSC',document.forms[0].E01DLSSBT.value,document.forms[0].E01DLSTYP.value,'13')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap width="18%" > 
              <div align="right">Tasa Moneda Extranjera :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01DLSRA3" size="11" maxlength="11" value="<%= deal.getE01DLSRA3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Per&iacute;odo Cup&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSROY" size="3" maxlength="2" value="<%= deal.getE01DLSROY().trim()%>">
       			<SELECT name="E01DLSODA">
					<OPTION value=" "
						<% if (!(deal.getE01DLSODA().equals("P") ||deal.getE01DLSODA().equals("I")||deal.getE01DLSODA().equals("M") || deal.getE01DLSODA().equals("D") )) out.print("selected"); %>></OPTION>
					<OPTION value="P"
						<% if (deal.getE01DLSODA().equals("P")) out.print("selected"); %>>
					Periodos Mensuales(No incluye último día)</OPTION>
					<OPTION value="I"
						<% if (deal.getE01DLSODA().equals("I")) out.print("selected"); %>>
					Periodos Mensuales(Incluye último día)</OPTION>
					<OPTION value="M"
						<% if (deal.getE01DLSODA().equals("M")) out.print("selected"); %>>
					Mensual(Ultimo Día del Mes)</OPTION>
					<OPTION value="D"
						<% if (deal.getE01DLSODA().equals("D")) out.print("selected"); %>>
					Mensual(Día del mes)</OPTION>
				</SELECT>
			</td>
            <td nowrap width="18%" > 
              <div align="right">Ultima Fecha Pago Cup&oacute;n:</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01DLSLI1" size="3" maxlength="2" value="<%= deal.getE01DLSLI1().trim()%>" 
			  >
              <input type="text" name="E01DLSLI2" size="3" maxlength="2" value="<%= deal.getE01DLSLI2().trim()%>" 
			  >
              <input type="text" name="E01DLSLI3" size="3" maxlength="2" value="<%= deal.getE01DLSLI3().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tasa Cup&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSRA4" size="11" maxlength="11" value="<%= deal.getE01DLSRA4().trim()%>" >
            </td>
            <td nowrap width="18%" > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DLSRRT" size="3" maxlength="1" value="<%= deal.getE01DLSRRT()%>" 
			  >
                <a href="javascript:GetCode('E01DLSRRT','STATIC_fe_plp_rev.jsp')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSDLI" size="3" maxlength="1" value="<%= deal.getE01DLSDLI()%>" 
			  >
              <a href="javascript:GetCode('E01DLSDLI','STATIC_fe_bo_not.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap align="right" width="18%">Forma de Pago :</td>
            <td nowrap width="20%"> 
              <input type="text" name="E01DLSPVI" size="3" maxlength="1" value="<%= deal.getE01DLSPVI()%>" 
			  >
              <a href="javascript:GetCode('E01DLSPVI','STATIC_fe_bo_pay.jsp')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
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
        <div align="center">Cuenta Contable</div>
      </td>
      <td nowrap width="15%"> 
        <div align="center">Referencia</div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="14%" > 
        <div align="center"> 
          <input type="text" name="E01DLSOFB" size="2" maxlength="2" value="<%= deal.getE01DLSOFB().trim()%>" onKeyPress="enterInteger()">
        </div>
      </td>
      <td nowrap width="17%" > 
        <div align="center"> 
          <input type="text" name="E01DLSOCR" size="3" maxlength="3" value="<%= deal.getE01DLSOCR().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DLSOFB.value,'','','','')">
        </div>
      </td>
      <td nowrap width="22%" > 
        <div align="center"> 
          <input type="text" name="E01DLSOCY" size="3" maxlength="3" value="<%= deal.getE01DLSOCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DLSOFB.value,'','','','')">
        </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> 
          <input type="text" name="E01DLSOFA" size="17" maxlength="16" value="<%= deal.getE01DLSOFA()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01DLSOFB.value,document.forms[0].E01DLSOCY.value,'','','')" >
        </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> 
          <input type="text" name="E01DLSOAC" size="13" maxlength="12"  value="<%= deal.getE01DLSOAC().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DLSOFB.value,'','','','RT')" >
        </div>
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
        <div align="center"> 
          <input type="text" name="E01DLSREB" size="2" maxlength="2" value="<%= deal.getE01DLSREB().trim()%>" onKeyPress="enterInteger()">
        </div>
      </td>
      <td nowrap width="17%" > 
        <div align="center"> 
          <input type="text" name="E01DLSRPR" size="3" maxlength="3" value="<%= deal.getE01DLSRPR().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DLSREB.value,'','','','')">
        </div>
      </td>
      <td nowrap width="22%" > 
        <div align="center"> 
          <input type="text" name="E01DLSRPC" size="3" maxlength="3" value="<%= deal.getE01DLSRPC().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DLSREB.value,'','','','')">
        </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> 
          <input type="text" name="E01DLSRAC" size="17" maxlength="16" value="<%= deal.getE01DLSRAC()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01DLSREB.value,document.forms[0].E01DLSRPC.value,'','','')" >
        </div>
      </td>
      <td nowrap width="15%" > 
        <div align="center"> 
          <input type="text" name="E01DLSRCC" size="13" maxlength="12"  value="<%= deal.getE01DLSRCC().trim()%>" onKeyPress="enterInteger()"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DLSREB.value,'','','','RT')" >
        </div>
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
              <div align="center"><b>Monto L&iacute;mite <br>
                Disponible</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>
                Final</b></div>
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
  <BR>
  </form>
</body>
</html>
