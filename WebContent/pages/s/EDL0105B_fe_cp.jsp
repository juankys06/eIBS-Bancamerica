<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Commercial Papers - Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL010502Message"  scope="session" />
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

function GetProductB(name,desc,app,bank)
{
	page= prefix +language + "EWD0008_client_help_container.jsp"
	fieldName=name;
	fieldAux1 = desc; 
	AppCode = app;
	ProductBank = bank;				
	CenterWindow(page,600,400,1);
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
<h3 align="center">Papeles Comerciales - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp.jsp,EDL0105B"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0105B" >
<input type="hidden" name="SCREEN"  value="4" >
<INPUT TYPE=HIDDEN NAME="SCRACMBNK" VALUE="<%= deal.getE02DEABNK().trim()%>">
<INPUT TYPE=HIDDEN NAME="E02DLSSBT" VALUE="<%= deal.getE02DLSSBT().trim()%>">
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
                <input type="hidden" name="E02CUSNA1"  value="<%= deal.getE02CUSNA1()%>" >
                <%= deal.getE02CUSNA1()%> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Básica 
  </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap ><%= Util.formatDate(deal.getE02DEAOD1(),deal.getE02DEAOD2(),deal.getE02DEAOD3())%></td>
            <td nowrap >
              <div align="right">Hora :</div>
            </td>
            <td nowrap >
            	<% if (deal.getE02DEAREF().length() > 6) out.print(deal.getE02DEAREF().substring(deal.getE02DEAREF().length() - 6)); %>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">No. de Referencia :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE02DEAREF().trim()%> </td>
            <td nowrap width="18%" > 
              <div align="right"> Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="20%" ><% if(deal.getE02DLSSBT().equals("PU")) out.print("Nueva Compra");
					   else if(deal.getE02DLSSBT().equals("PA")) out.print("Compra Adicional");
					    else if(deal.getE02DLSSBT().equals("SL")) out.print("Disponible a la Venta");
					     else if(deal.getE02DLSSBT().equals("S1")) out.print("Re-Venta");
					      else if(deal.getE02DLSSBT().equals("PR")) out.print("Re-Compra");
					      else if(deal.getE02DLSSBT().equals("RL")) out.print("Liberar Custodia");
						else out.print("");%> 
			</td>
          </tr>
 
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE02DEACCY().trim()%>  </td>
            <td nowrap align="right" width="18%">Valor Nominal :</td>
            <td nowrap width="20%"><%= Util.fcolorCCY(deal.getE02DEAOAM())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Precio : </div>
            </td>
            <td nowrap width="23%" > <%= deal.getE02BIDPRC()%>
            </td>
           <td nowrap align="right" width="18%"> 
              <div align="right">Interés Acumulado :</div>
            </td>
            <td nowrap width="20%"><%= deal.getE02DEAINT().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap width="23%" >
            	<%= deal.getE02DEARTE()%>
            </td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Prima :</div>
            </td>
            <td nowrap width="20%">
            	<%= Util.fcolorCCY(deal.getE02DEABAP())%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha Ultimo Cupón :</div>
            </td>
            <td nowrap width="23%" >
            	<%= Util.formatDate(deal.getE02DEALI1(),deal.getE02DEALI2(),deal.getE02DEALI3())%>
            </td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Saldo Neto :</div>
            </td>
            <td nowrap width="20%">
            	<%= Util.fcolorCCY(deal.getE02NETPRC())%>
            </td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha de Proceso/Orden&nbsp;:</div>
            </td>
            <td nowrap width="23%" ><%= Util.formatDate(deal.getE02DEAOD1(),deal.getE02DEAOD2(),deal.getE02DEAOD3())%> 
            </td>
            <td nowrap align="right" width="18%">Fecha Valor :</td>
            <td nowrap width="20%"> <%= Util.formatDate(deal.getE02DEAST1(),deal.getE02DEAST2(),deal.getE02DEAST3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE02DLSOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE02DLSOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE02DLSUSR().trim()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Adicional</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Producto :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02DEAPRO" size="5" maxlength="4" value="<%= deal.getE02DEAPRO().trim()%>" >
              <input type="hidden" name="EDSCPRO"  value="" >
               <a href="javascript:GetProductB('E02DEAPRO','EDSCPRO','13','')""><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E02DEAMD1" size="2" maxlength="2" value="<%= deal.getE02DEAMD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E02DEAMD2" size="2" maxlength="2" value="<%= deal.getE02DEAMD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E02DEAMD3" size="2" maxlength="2" value="<%= deal.getE02DEAMD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E02DEAMD1,document.forms[0].E02DEAMD2,document.forms[0].E02DEAMD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Término :</div>
            </td>
            <td nowrap width="23%" height="37"> 
             <input type="text" name="E02DEATRM" size="4" maxlength="3" value="<%= deal.getE02DEATRM().trim()%>" >
              <select  name="E02DEATRC">
                <option value=" " <% if (!(deal.getE02DEATRC().equals("D") ||deal.getE02DEATRC().equals("M")||deal.getE02DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(deal.getE02DEATRC().equals("D")) out.print("selected");%>>Día(s)</option>
                <option value="M" <% if(deal.getE02DEATRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(deal.getE02DEATRC().equals("Y")) out.print("selected");%>>Año(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Tasa de Rendimiento :</div>
            </td>
            <td nowrap width="27%" height="37">
				<input type="text" name="E02DEAMXR" size="13" maxlength="12" value="<%= deal.getE02DEAMXR().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Tipo de Cálculo de Interés :</div>
            </td>
            <td nowrap width="23%" height="37">
            <SELECT name="E02DEARRP">
					<OPTION value=" "
						<% if (!(deal.getE02DEARRP().equals("1") ||deal.getE02DEARRP().equals("2")||deal.getE02DEARRP().equals("3")||deal.getE02DEARRP().equals("4")||deal.getE02DEARRP().equals("5")||deal.getE02DEARRP().equals("6"))) out.print("selected"); %>></OPTION>
					<OPTION value="1"
						<% if (deal.getE02DEARRP().equals("1")) out.print("selected"); %>>
					Actual/actual (en periodo)</OPTION>
					<OPTION value="2"
						<% if (deal.getE02DEARRP().equals("2")) out.print("selected"); %>>
					Actual/365</OPTION>
					<OPTION value="3"
						<% if (deal.getE02DEARRP().equals("3")) out.print("selected"); %>>
					Actual/365 (366 en bisiesto)</OPTION>
					<OPTION value="4"
						<% if (deal.getE02DEARRP().equals("4")) out.print("selected"); %>>
					Actual/360</OPTION>
					<OPTION value="5"
						<% if (deal.getE02DEARRP().equals("5")) out.print("selected"); %>>
					30/360</OPTION>
					<OPTION value="6"
						<% if (deal.getE02DEARRP().equals("6")) out.print("selected"); %>>
					30E/360</OPTION>
				</SELECT>
			</td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Vía de Pago :</div>
            </td>
            <td nowrap width="27%" height="37">
                <select name="E02DEAPVI" >
                  <option value="" <% if(deal.getE02DEAPVI().equals("")) out.print("selected");%>></option>
                  <option value="F" <% if(deal.getE02DEAPVI().equals("F")) out.print("selected");%>>Fed</option>
                  <option value="1" <% if(deal.getE02DEAPVI().equals("1")) out.print("selected");%>>Swift MT-103</option>
                  <option value="2" <% if(deal.getE02DEAPVI().equals("2")) out.print("selected");%>>Swift MT-200</option>
				  <option value="3" <% if(deal.getE02DEAPVI().equals("3")) out.print("selected");%>>Swift MT-202</option>
				  <option value="G" <% if(deal.getE02DEAPVI().equals("G")) out.print("selected");%>>Cuenta Contable</option>
				  <option value="R" <% if(deal.getE02DEAPVI().equals("R")) out.print("selected");%>>Cuenta de Detalle</option>				  
                  <option value="C" <% if(deal.getE02DEAPVI().equals("C")) out.print("selected");%>>Cheque Oficial</option>
                  <option value="A" <% if(deal.getE02DEAPVI().equals("A")) out.print("selected");%>>ACH</option>
                </select>
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Periodo de Pago del Cupón :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEAROY" size="5" maxlength="4" value="<%= deal.getE02DEAROY().trim()%>" >
              <SELECT name="E02DEAODA">
					<OPTION value=" "
						<% if (!(deal.getE02DEAODA().equals("P") ||deal.getE02DEAODA().equals("I")||deal.getE02DEAODA().equals("M") || deal.getE02DEAODA().equals("D") )) out.print("selected"); %>></OPTION>
					<OPTION value="P"
						<% if (deal.getE02DEAODA().equals("P")) out.print("selected"); %>>
					Periodos Mensuales(No incluye último día)</OPTION>
					<OPTION value="I"
						<% if (deal.getE02DEAODA().equals("I")) out.print("selected"); %>>
					Periodos Mensuales(Incluye último día)</OPTION>
					<OPTION value="M"
						<% if (deal.getE02DEAODA().equals("M")) out.print("selected"); %>>
					Mensual(Ultimo Día del Mes)</OPTION>
					<OPTION value="D"
						<% if (deal.getE02DEAODA().equals("D")) out.print("selected"); %>>
					Mensual(Día del mes)</OPTION>
				</SELECT>
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0">                        
              
            </td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Confirmación :</div>
            </td>
            <td nowrap width="27%" >
              <select name="E02DEAF03">
                <option value=" " <% if (!(deal.getE02DEAF03().equals("P") ||deal.getE02DEAF03().equals("S")||deal.getE02DEAF03().equals("N"))) out.print("selected"); %>></option>
                <option value="P" <% if (deal.getE02DEAF03().equals("P")) out.print("selected"); %>>Imprimir</option>
                <option value="S" <% if (deal.getE02DEAF03().equals("S")) out.print("selected"); %>>Swift</option>
                <option value="N" <% if (deal.getE02DEAF03().equals("N")) out.print("selected"); %>>Ninguna</option>
              </select>            
            	
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Inversión :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEAUC6" size="5" maxlength="4" value="<%= deal.getE02DEAUC6().trim()%>" readonly>
            </td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Revalorización :</div>
            </td>
            <td nowrap width="27%" >
                 <select  name="E02DEARRT">
                <option value=" " <% if (!(deal.getE02DEARRT().equals("1") ||deal.getE02DEARRT().equals("2")||deal.getE02DEARRT().equals("3")||deal.getE02DEARRT().equals("4")||deal.getE02DEARRT().equals("N"))) out.print("selected"); %>></option>
                <option value="1" <% if (deal.getE02DEARRT().equals("1")) out.print("selected"); %>>Reval. Diaria Pérdidas y Ganancias</option>
                <option value="2" <% if (deal.getE02DEARRT().equals("2")) out.print("selected"); %>>Reval. Diaria Pérdidas</option>
                <option value="3" <% if (deal.getE02DEARRT().equals("3")) out.print("selected"); %>>Reval. Mensual Pérdidas y Ganancias</option>
                <option value="4" <% if (deal.getE02DEARRT().equals("4")) out.print("selected"); %>>Reval. Mensual Pérdidas</option>
                <option value="N" <% if (deal.getE02DEARRT().equals("N")) out.print("selected"); %>>No Reval.</option>
              </select>
 
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Número de Custodia :</div>
            </td>
            <td nowrap width="23%" > 
				<input type="text" name="E02SFENUM" size="11" maxlength="10" value="<%= deal.getE02SFENUM().trim()%>" onkeypress="enterInteger()">            
				<a href="javascript:GetCustomerDescId('E02SFENUM','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a>
			</td>
            <td nowrap width="25%" >
              <div align="right">Número de CUSIP/ISIN :</div>
            </td>
            <td nowrap width="27%" >
             	<INPUT type="text" name="E02DEACUI" size="13"	maxlength="12" value="<%= deal.getE02DEACUI().trim()%>">
            </td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ubicación de Documentos :</div>
            </td>
            <td nowrap width="75%" colspan="3" > 
            	<input type="text" name="E02DEALOC" size="40" maxlength="35" value="<%= deal.getE02DEALOC().trim()%>" >
            </td>
          </tr>  
           <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">País deResidencia :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEAGCD" size="5" maxlength="4" value="<%= deal.getE02DEAGCD().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E02DEAGCD','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
            <td nowrap width="25%" >
              <div align="right">País de Riesgo  :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E02DEAGRC" size="13" maxlength="12" value="<%= deal.getE02DEAGRC().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E02DEAGRC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
             </td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEAFTB" size="2" maxlength="2" value="<%= deal.getE02DEAFTB().trim()%>">
              <a href="javascript:GetFloating('E02DEAFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <select name="E02DEAFTY">
                <option value=" " <% if (!(deal.getE02DEAFTY().equals("FP") ||deal.getE02DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (deal.getE02DEAFTY().equals("FP")) out.print("selected"); %>>Primaria</option>
                <option value="FS" <% if (deal.getE02DEAFTY().equals("FS")) out.print("selected"); %>>Secundaria</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E02RATE" size="9" maxlength="9" value="<%= deal.getE02RATE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisión/Ciclo :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEARD1" size="2" maxlength="2" value="<%= deal.getE02DEARD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E02DEARD2" size="2" maxlength="2" value="<%= deal.getE02DEARD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E02DEARD3" size="2" maxlength="2" value="<%= deal.getE02DEARD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E02DEARD1,document.forms[0].E02DEARD2,document.forms[0].E02DEARD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
              / 
              <input type="text" name="E02DEARRP" size="3" maxlength="3" value="<%= deal.getE02DEARRP().trim()%>" onkeypress="enterCharNumber()" onblur="rightAlignCharNumber()">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E02DEACCN" size="8" maxlength="6" value="<%= deal.getE02DEACCN().trim()%>" >
              <A href="javascript:GetCostCenter('E02DEACCN',document.forms[0].SCRACMBNK.value)"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0"></A>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Código de Renovación :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E02DEAF02" size="2" maxlength="1" value="<%= deal.getE02DEAF02().trim()%>" readonly>
             </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Cambio Moneda Extranjera :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E02DEAEXR" size="15" maxlength="13" value="<%= deal.getE02DEAEXR().trim()%>" onkeypress="enterSignDecimal()">
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>  

               
    <h4>Cuentas de Contrapartida</h4>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable">
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >No. de Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
    <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear")> 
            <td nowrap> 
                <div align="center"> 
                  <input type="text" name="E02OFFOP<%= name %>" size="2" maxlength="2" value="<%= deal.getField("E02OFFOP"+name).getString().trim()%>" onKeypress="enterInteger()">
                  <input type="hidden" name="E02OFFGL<%= name %>" value="<%= deal.getField("E02OFFGL"+name).getString().trim()%>">
                <input type="text" name="E02OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= deal.getField("E02OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].SCRACMBNK.value,'','E02OFFGL<%= name %>','E02OFFOP<%= name %>','13'); return false;">
              </div>
            </td>
            <td nowrap width="5%" > 
              <div align="center"> 
                <input type="text" name="E02OFFBK<%= name %>" size="2" maxlength="2" value="<%= deal.getField("E02OFFBK"+name).getString().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                <input type="text" name="E02OFFBR<%= name %>" size="3" maxlength="3" value="<%= deal.getField("E02OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].SCRACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E02OFFCY<%= name %>" size="3" maxlength="3" value="<%= deal.getField("E02OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].SCRACMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                  <input type="text" name="E02OFFAC<%= name %>" size="13" maxlength="12"  value="<%= deal.getField("E02OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].SCRACMBNK.value,'','','','RT'); return false;" onKeypress="enterInteger()">
              </div>
            </td>
            <td nowrap  > 
              <div align="center"> 
                  <input type="text" name="E02OFFAM<%= name %>" size="15" maxlength="13"  value="<%= deal.getField("E02OFFAM"+name).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
     <%
    		}
    		%> 
     </table>
        </div>
    <table id="footTable" > 				
    <tr id="trdark"> 
            <td nowrap align="right"><b>Monto del Contrato en Moneda Equivalente :</b> </td>
      <td nowrap align="center"><b><i><strong> 
          <input type="text" name="E02OFFEQV" size="15" maxlength="13" readonly value="<%= deal.getE02OFFEQV().trim()%>">
          </strong></i></b>
      </td>
    </tr>
  </table>
  </TD>  
</TR>	
</TABLE>    

    <h4>Cuentas de Pago</h4>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable1">
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >No. de Referencia</td>
    </tr>
    </table> 
      
    <div id="dataDiv1" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable1" >
     <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02REPOPE"  value="<%= deal.getE02REPOPE().trim()%>" size="2" maxlength="2">
          <input type="hidden" name="E02REPGLN" value="<%= deal.getE02REPGLN().trim()%>">
          <input type="text" name="E02REPCON" size="25" maxlength="25"  value="<%= deal.getE02REPCON().trim()%>" 
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].SCRACMBNK.value,'','E02REPGLN','E02REPOPE','13'); return false;">

        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02REPBNK" size="2" maxlength="2" value="<%= deal.getE02REPBNK().trim()%>" onKeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E02REPBRN" size="3" maxlength="3" value="<%= deal.getE02REPBRN().trim()%>" 
			oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].SCRACMBNK.value,'','','',''); return false;">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E02REPCCY" size="3" maxlength="3" value="<%= deal.getE02REPCCY().trim()%>" 
          oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].SCRACMBNK.value,'','','',''); return false;">
        </div>
      </td>
      <td nowrap > 
        <div align="center">
        <INPUT type="text" name="E02REPACC" size="13" maxlength="12"
					value="<%= deal.getE02REPACC().trim()%>"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].SCRACMBNK.value,'','','','RT'); return false;"
					onkeypress="enterInteger()"></div>
      </td>
    </tr>
     </table>
        </div>
  </TD>  
</TR>	
</TABLE>    
   <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableresize();
  window.onresize=tableresize;  
  </SCRIPT>

  <h4>Límites</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto Límite </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Utilizado</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Disponible  </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>Líneas de Crédito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT1())%></div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT3())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getE02DLSAMT2())%></b></div>
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
