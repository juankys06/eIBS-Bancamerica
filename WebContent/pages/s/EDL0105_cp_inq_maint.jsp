<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Commercial Paper Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cdMant" class="datapro.eibs.beans.EDL010501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cp_i_opt);
	builtHPopUp();

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
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Información Básica<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_inq_maint.jsp,EDL0105"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I" onsubmit="javascript:this.disabled = true;">

  <INPUT TYPE=HIDDEN NAME="SCRDEABNK"  value="<%= cdMant.getE01DEABNK().trim()%>">
  <input type=HIDDEN name="E01DEAACD"  value="<%= cdMant.getE01DEAACD().trim()%>">
  <input type=HIDDEN name="E01DEAGLN"  value="<%= cdMant.getE01DEAGLN().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01DEACUN" readonly  size="9" maxlength="9" value="<%= cdMant.getE01DEACUN().trim()%>" >
               </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= cdMant.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="9" value="<%= cdMant.getE01DEAACC().trim()%>" readonly>
                
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY2" size="3" maxlength="3" value="<%= cdMant.getE01DEACCY().trim()%>" readonly>
                
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= cdMant.getE01DEAPRO().trim()%>" readonly>
                 
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Proceso/Orden :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdMant.getE01DEAOD3().trim()%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Pago <BR>
				Ultimo Cupón/Emisión :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEALI1" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEALI1().trim()%>" >
              <input type="text" name="E01DEALI2" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEALI2().trim()%>" >
              <input type="text" name="E01DEALI3" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEALI3().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Valor  :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAST1" size="2" maxlength="2" value="<%= cdMant.getE01DEAST1().trim()%>" readonly>
              <input type="text" name="E01DEAST2" size="2" maxlength="2" value="<%= cdMant.getE01DEAST2().trim()%>" readonly>
              <input type="text" name="E01DEAST3" size="2" maxlength="2" value="<%= cdMant.getE01DEAST3().trim()%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEAMD1"  size="2" maxlength="2" value="<%= cdMant.getE01DEAMD1().trim()%>" >
              <input type="text" name="E01DEAMD2"  size="2" maxlength="2" value="<%= cdMant.getE01DEAMD2().trim()%>" >
              <input type="text" name="E01DEAMD3"  size="2" maxlength="2" value="<%= cdMant.getE01DEAMD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Término :</div>
            </td>
            <td nowrap width="23%" height="37"> 
             <input type="text" name="E01DEATRM" size="4" maxlength="3" value="<%= cdMant.getE01DEATRM().trim()%>" readonly>
             <input type="text" name="E01DEATRC" size="10" readonly 
             	value="<% if(cdMant.getE01DEATRC().equals("D")) out.print("Día(s)");
             			   else if (cdMant.getE01DEATRC().equals("M")) out.print("Mes(s)");
             			   else if (cdMant.getE01DEATRC().equals("Y")) out.print("Año(s)");%>" >             
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Precio de Oferta :</div>
            </td>
            <td nowrap width="27%" height="37">
              <input type="text" name="E01BIDPRC" size="15" maxlength="13" value="<%= cdMant.getE01BIDPRC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Valor Nominal  :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAOAM" size="15" maxlength="13" value="<%= cdMant.getE01DEAOAM().trim()%>" readonly>
             <td nowrap width="25%" >
              <div align="right">Tasa del Cupón :</div>
            </td>
            <td nowrap width="27%" >
 				<input type="text" name="E01DEARTE" size="13" maxlength="12" value="<%= cdMant.getE01DEARTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Plusvalía/Descuento  </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEATDM" size="15" maxlength="13" value="<%= cdMant.getE01DEATDM().trim()%>" readonly>
            </td>
            <td nowrap width="25%" >
              <div align="right">Tasa de Rendimiento:</div>
            </td>
            <td nowrap width="27%" >
				<input type="text" name="E01DEAMXR" size="13" maxlength="12" value="<%= cdMant.getE01DEAMXR().trim()%>" readonly>
			</td>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Saldo de Principal/Monto Total :</DIV>
            </TD><TD nowrap width="23%"> 
              <INPUT type="text" name="E01DEABAP" size="15" maxlength="13" value="<%= cdMant.getE01DEABAP().trim()%>" readonly>
            </TD>
            
            <td nowrap width="25%" >
              <div align="right">Periodo de Pago del Cupón :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEAROY" size="5" maxlength="4" value="<%= cdMant.getE01DEAROY().trim()%>" readonly>
				<% if(cdMant.getE01DEAODA().equals("P")) out.print(" Periodos Mensuales(No incluye último día)");
					   else if(cdMant.getE01DEAODA().equals("I")) out.print(" Periodos Mensuales(Incluye último día)");
						else if(cdMant.getE01DEAODA().equals("M")) out.print(" Mensual(Ultimo Día del Mes)");
							else if(cdMant.getE01DEAODA().equals("D")) out.print(" Mensual(Día del mes)");
								else out.print(" None");%>               
			<input type="hidden" name="E01DEAODA" value="<%= cdMant.getE01DEAODA().trim()%>">  
            </td>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Interés Acumulado :</DIV>
            </TD><TD nowrap width="23%"> 
              <INPUT type="text" name="E01DEAINT" size="15" maxlength="13" value="<%= cdMant.getE01DEAINT().trim()%>" readonly>
            </TD>
            
            <td nowrap width="25%" >
              <div align="right">Tipo de Confirmación :</div>
            </td>
            <td nowrap width="27%" >
            	<input type="text" name="E01DEAF02" size="10" readonly <% if (cdMant.getF01DEAF02().equals("Y")) out.print("id=\"txtchanged\""); %>
             	value="<% if(cdMant.getE01DEAF02().equals("P")) out.print("Imprimir");
             			   else if (cdMant.getE01DEAF02().equals("S")) out.print("Swift");
             			   else if (cdMant.getE01DEAF02().equals("N")) out.print("Ninguno");%>" >             
             </td>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Saldo + Interés :</DIV>
            </TD><TD nowrap width="23%"> 
              <INPUT type="text" name="E01NETPRC" size="15" maxlength="13" value="<%= cdMant.getE01NETPRC().trim()%>" readonly>
            </TD>
            
            <td nowrap width="25%" >
              <div align="right">Tipo de Revaloración :</div>
            </td>
            <td nowrap width="27%" >
            	<input type="text" name="E01DEARRT" size="30" readonly <% if (cdMant.getF01DEARRT().equals("Y")) out.print("id=\"txtchanged\""); %>
             	value="<% if(cdMant.getE01DEARRT().equals("1")) out.print("Reval. Diaria Pérdidas y Ganancias");
             			   else if (cdMant.getE01DEARRT().equals("2")) out.print("Reval. Diaria Pérdidas");
             			   else if (cdMant.getE01DEARRT().equals("3")) out.print("Reval. Mensual Pérdidas y Ganancias");
             			   else if (cdMant.getE01DEARRT().equals("4")) out.print("Reval. Mensual Pérdidas");
             			   else if (cdMant.getE01DEARRT().equals("N")) out.print("No Reval.");%>" >             
              </td>
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Tipo de Inversión :</DIV>
            </TD><TD nowrap width="23%"> 
              <INPUT type="text" name="E01DEAUC6" readonly size="5" maxlength="4" value="<%= cdMant.getE01DEAUC6().trim()%>">
            </TD>
            
            <td nowrap width="25%" >
              <div align="right">No. de CUSIP/ISIN :</div>
            </td>
            <td nowrap width="27%" >
             <INPUT type="text" name="E01DEACUI" readonly  size="13"	maxlength="12" value="<%= cdMant.getE01DEACUI().trim()%>">
            </td>
          </tr>     
          <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Tipo de Custodia :</DIV>
            </TD><TD nowrap width="23%"> 
				<INPUT type="text" name="E01SFENUM" readonly size="11" maxlength="10" value="<%= cdMant.getE01SFENUM().trim()%>"> 
           </TD>
            
            <td nowrap width="25%" >
              <div align="right">Línea de Crédito :</div>
            </td>
            <td nowrap width="27%" >
			  <input type="text" name="E01DEACMC" size="9" maxlength="9" value="<%= cdMant.getE01DEACMC().trim()%>"  readonly>
              <input type="text" name="E01DEACMN" size="4" maxlength="4" value="<%= cdMant.getE01DEACMN().trim()%>"  readonly>
            </td>
		</tr>
               
        <tr id="trclear"> 
            <TD nowrap width="25%"> 
              <DIV align="right">No. de Common Code  :</DIV>
            </TD><TD nowrap width="75%"> 
				<INPUT type="text" name="E01DEAREF" readonly size="13" maxlength="12" value="<%= cdMant.getE01DEAREF().trim()%>">            
			</TD>
            
            <td nowrap width="25%" >
            </td>
            <td nowrap width="27%" >
            </td>
         </tr>
         <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Agente de Bolsa :</DIV>
            </TD><TD nowrap width="23%"> 
              <INPUT type="text" name="E01DEAUC7" readonly size="5" maxlength="4" value="<%= cdMant.getE01DEAUC7().trim()%>">
              <INPUT type="text" name="D01DEAUC7" readonly size="40" maxlength="35" value="<%= cdMant.getE01DEAUC7().trim()%>">
            </TD>
            
            <td nowrap width="25%" align="right">
            País de Residencia :</td>
            <td nowrap width="27%" >
            <INPUT type="text" name="E01DEAGCD" readonly size="5"
					maxlength="4" value="<%= cdMant.getE01DEAGCD().trim()%>"></td>
          </tr>  
           <tr id="trclear"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Ubicación de Documento :</DIV>
            </TD><TD nowrap width="75%"> 
            	<INPUT type="text" name="E01DEALOC" readonly size="40" maxlength="35" value="<%= cdMant.getE01DEALOC().trim()%>">
            </TD>
            
            <td nowrap width="25%" >
              <div align="right">País de Riesgo :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEAGRC" readonly  size="13" maxlength="12" value="<%= cdMant.getE01DEAGRC().trim()%>" >
             </td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAFTB" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEAFTB().trim()%>">
            	<input type="text" name="E01DEAFTY" size="10" readonly 
             	value="<% if(cdMant.getE01DEAFTY().equals("FP")) out.print("Primary");
              			   else if (cdMant.getE01DEAFTY().equals("FS")) out.print("Secondary");%>" >             
             </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Flotante </div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01FLTRTE" size="9" maxlength="9" value="<%= cdMant.getE01FLTRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisión/Ciclo :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARD1" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEARD1().trim()%>" >
              <input type="text" name="E01DEARD2" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEARD2().trim()%>" >
              <input type="text" name="E01DEARD3" readonly  size="2" maxlength="2" value="<%= cdMant.getE01DEARD3().trim()%>" >
              / 
              <input type="text" name="E01DEARRP" readonly  size="3" maxlength="3" value="<%= cdMant.getE01DEARRP().trim()%>"  >
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACCN" size="8" maxlength="6" value="<%= cdMant.getE01DEACCN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%">
              <div align="right">Banco/Agencia :</div>
            </td>
            <td nowrap width="23%">
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdMant.getE01DEABNK().trim()%>" readonly>
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdMant.getE01DEABRN().trim()%>" readonly>
            </td>
             <td nowrap width="25%"> 
              <div align="right">Tasa de Cambio : </div>
            </td>
            <td nowrap width="27%" >
            	<input type="text" name="E01DEAEXR" size="10" maxlength="9" value="<%= cdMant.getE01DEAEXR().trim()%>" readonly>
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
      <td nowrap align="center" >Descripción</td>
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
          <input type="hidden" name="E01OFFGL<%= name %>" value="<%= cdMant.getField("E01OFFGL"+name).getString().trim()%>">
          <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= cdMant.getField("E01OFFCO"+name).getString().trim()%>">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= cdMant.getField("E01OFFBK"+name).getString().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFBR"+name).getString().trim()%>" readonly>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= cdMant.getField("E01OFFCY"+name).getString().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="9"  value="<%= cdMant.getField("E01OFFAC"+name).getString().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
                  <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="13"  value="<%= cdMant.getField("E01OFFAM"+name).getString().trim()%>" readonly>
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
          <input type="text" name="E01OFFEQV" size="15" maxlength="13" readonly value="<%= cdMant.getE01OFFEQV().trim()%>">
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
      <td nowrap align="center" >Descripción</td>
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
          <input type="hidden" name="E01REPGLN" value="<%= cdMant.getE01REPGLN().trim()%>">
          <input type="text" name="E01REPCON" size="25" maxlength="25" readonly value="<%= cdMant.getE01REPCON().trim()%>" >
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPBNK" size="2" maxlength="2" value="<%= cdMant.getE01REPBNK().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPBRN" size="3" maxlength="3" value="<%= cdMant.getE01REPBRN().trim()%>" readonly>
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01REPCCY" size="3" maxlength="3" value="<%= cdMant.getE01REPCCY().trim()%>" readonly>
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPACC" size="12" maxlength="9"  value="<%= cdMant.getE01REPACC().trim()%>" readonly>
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
  </form>
</body>
</html>
