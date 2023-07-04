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
<h3 align="center">Papeles Comerciales - Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp_mod.jsp,EDL0105B"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0105B" >
<input type="hidden" name="SCREEN"  value="4" >
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
            <td nowrap width="21%" ><div align="right">No. de Referencia :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE02DEAREF().trim()%> </td>
            <td nowrap width="18%" > 
              <div align="right"> Tipo de Operaci&oacute;n:</div>
            </td>
            <td nowrap width="20%" ><% if(deal.getE02DLSSBT().equals("PU")) out.print("Nueva Compra");
					   else if(deal.getE02DLSSBT().equals("PA")) out.print("Compra Adicional");
					    else if(deal.getE02DLSSBT().equals("SL")) out.print("Disponible a la Venta");
					     else if(deal.getE02DLSSBT().equals("S1")) out.print("Re-Venta");
					      else if(deal.getE02DLSSBT().equals("PR")) out.print("Re-Compra");
					      else if(deal.getE02DLSSBT().equals("RL")) out.print("Liberar Custodia");
						else out.print("");%> </td>
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
            <td nowrap width="20%"><%= deal.getE02DEAINT().trim()%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Tasa del Cupón :</div>
            </td>
            <td nowrap width="23%" ><%= deal.getE02DEAMIR()%></td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Plusvalía/Descuento :</div>
            </td>
            <td nowrap width="20%"><%= Util.fcolorCCY(deal.getE02DEABAP())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha Ultimo Cupón :</div>
            </td>
            <td nowrap width="23%" ><%= Util.formatDate(deal.getE02DEALI1(),deal.getE02DEALI2(),deal.getE02DEALI3())%>  </td>
            <td nowrap align="right" width="18%">Saldo Neto :</td>
            <td nowrap width="20%"><%= Util.fcolorCCY(deal.getE02NETPRC())%> </td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Fecha de Proceso/Orden  :</div>
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
              <%= deal.getE02DEAPRO().trim()%>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="27%"> 
              <%= Util.formatDate(deal.getE02DEAMD1(),deal.getE02DEAMD2(),deal.getE02DEAMD3())%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Término :</div>
            </td>
            <td nowrap width="23%" height="37"> 
             <%= deal.getE02DEATRM().trim()%>
             &nbsp;
                 <% if(deal.getE02DEATRC().equals("D")) out.print("Dia(s)");
                 else if(deal.getE02DEATRC().equals("M")) out.print("Mes(es)");
                else if(deal.getE02DEATRC().equals("Y")) out.print("Año(s)");%>
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Tasa de Rendimiento :</div>
            </td>
            <td nowrap width="27%" height="37">
				<%= deal.getE02DEAMXR().trim()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Base de Cálculo :</div>
            </td>
            <td nowrap width="23%" height="37"> 
             <%= deal.getE02DEAICT().trim()%>&nbsp;
             <%= deal.getE02DEABAS().trim()%>
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Vía de Pago :</div>
            </td>
            <td nowrap width="27%" height="37">
               <% if(deal.getE02DEAPVI().equals("F")) out.print("Fed");
                  else if(deal.getE02DEAPVI().equals("1")) out.print("Swift MT-103");
                  else if(deal.getE02DEAPVI().equals("2")) out.print("Swift MT-200");
				  else if(deal.getE02DEAPVI().equals("3")) out.print("Swift MT-202");
				  else if(deal.getE02DEAPVI().equals("G")) out.print("Cuenta Contable");
				  else if(deal.getE02DEAPVI().equals("R")) out.print("Cuenta Corriente");			  
                  else if(deal.getE02DEAPVI().equals("C")) out.print("Cheque Oficial");
                  else if(deal.getE02DEAPVI().equals("A")) out.print("ACH");%>

            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Período de Pago del Cupón :</div>
            </td>
            <td nowrap width="23%" > 
             <%= deal.getE02DEAROY().trim()%>&nbsp;
			 <% if(deal.getE02DEAODA().equals("P")) out.print(" Periodos Mensuales(No incluye último día)");
				   else if(deal.getE02DEAODA().equals("I")) out.print(" Periodos Mensuales(Incluye último día)");
					else if(deal.getE02DEAODA().equals("M")) out.print(" Mensual(Ultimo Día del Mes)");
						else if(deal.getE02DEAODA().equals("D")) out.print(" Mensual(Día del mes)");
							else out.print(" None");%>               
            </td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Confirmación  :</div>
            </td>
            <td nowrap width="27%" >
                
                <% if (deal.getE02DEAF03().equals("P")) out.print("Impresión"); 
                 else if (deal.getE02DEAF03().equals("S")) out.print("Swift"); 
				else if (deal.getE02DEAF03().equals("N")) out.print("Ninguna"); %>

             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Inversión&nbsp;:</div>
            </td>
            <td nowrap width="23%" > 
              <%= deal.getE02DEAUC6().trim()%>
            </td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Revalorización :</div>
            </td>
            <td nowrap width="27%" >
				<% if (deal.getE02DEARRT().equals("1")) out.print("Reval. Diaria Pérdidas y Ganancias"); 
                else if (deal.getE02DEARRT().equals("2")) out.print("Reval. Diaria Pérdidas"); 
                else if (deal.getE02DEARRT().equals("3")) out.print("Reval. Mensual Pérdidas y Ganancias"); 
                else if (deal.getE02DEARRT().equals("4")) out.print("Reval. Mensual Pérdidas");
                else if (deal.getE02DEARRT().equals("N")) out.print("No Reval."); %>

             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Número de Custodia :</div>
            </td>
            <td nowrap width="23%" > 
				<%= deal.getE02SFENUM().trim()%>         
			</td>
            <td nowrap width="25%" >
              <div align="right">No.  de CUSIP/ISIN  :</div>
            </td>
            <td nowrap width="27%" >
             <%= deal.getE02DEACUI().trim()%>
            </td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ubicación de Documentos :</div>
            </td>
            <td nowrap width="75%" colspan="3" > 
            	<%= deal.getE02DEALOC().trim()%>
            </td>
          </tr>  
           <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">País de Residencia :</div>
            </td>
            <td nowrap width="23%" > 
              <%= deal.getE02DEAGCD().trim()%>
              
            </td>
            <td nowrap width="25%" >
              <div align="right">País de Riesgo :</div>
            </td>
            <td nowrap width="27%" >
              <%= deal.getE02DEAGRC().trim()%>
             </td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante  :</div>
            </td>
            <td nowrap width="23%" > 
              <%= deal.getE02DEAFTB().trim()%>&nbsp;
                 <% if (deal.getE02DEAFTY().equals("FP")) out.print("Primary"); 
                else if (deal.getE02DEAFTY().equals("FS")) out.print("Secondary"); %>

            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap width="27%" > 
              <%= deal.getE02RATE().trim()%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisión/Ciclo :</div>
            </td>
            <td nowrap width="23%" > 
              <%= Util.formatDate(deal.getE02DEARD1(),deal.getE02DEARD2(),deal.getE02DEARD3())%>
               / 
              <%= deal.getE02DEARRP().trim()%>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%" > 
              <%= deal.getE02DEACCN().trim()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Código de Renovación :</div>
            </td>
            <td nowrap width="23%" > 
              <%= deal.getE02DEAF02().trim()%>
             </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Cambio Moneda Extranjera :</div>
            </td>
            <td nowrap width="27%" > 
              <%= deal.getE02DEAEXR().trim()%>
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
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <%= deal.getField("E02OFFOP"+name).getString().trim()%>
          <%= deal.getField("E02OFFCO"+name).getString().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getField("E02OFFBK"+name).getString().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getField("E02OFFBR"+name).getString().trim()%>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <%= deal.getField("E02OFFCY"+name).getString().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getField("E02OFFAC"+name).getString().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
             <%= deal.getField("E02OFFAM"+name).getString().trim()%>
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
          <%= deal.getE02OFFEQV().trim()%>
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
          <%= deal.getE02REPOPE().trim()%>
          <%= deal.getE02REPCON().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getE02REPBNK().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getE02REPBRN().trim()%>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <%= deal.getE02REPCCY().trim()%>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <%= deal.getE02REPACC().trim()%>
        </div>
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
              <div align="center"><b>Límite Disponible</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto Límite Final </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>Líneas de Crédito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT1())%></div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getE02DLSAMT2())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getE02DLSAMT3())%></b></div>
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
