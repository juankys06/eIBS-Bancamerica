<html>
<head>
<title>Detalle Factura</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="EBP0130Record" class="datapro.eibs.beans.EBP013001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
 
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSBillsP.jsp"> </SCRIPT>

<script language="JavaScript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
	init(opth,field,bank,ccy,field1,field2,opcod);
   		showPopupHelp();
}

function goAction(op) {
	document.forms[0].SCREEN.value = op;
    
	if (op == 4) {
		if (!confirm("Esta seguro que desea borrar este registro?")) {
			return;
		}
	}
	document.forms[0].submit();
}
</SCRIPT>

</head>

<body >

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
	String read = "";
 	String disabled = "";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))) 
		{ read = " readonly ";
		  disabled = " disabled"; }	
	if (userPO.getHeader3().equals ("A") && (userPO.getPurpose().equals("MAINTENANCE"))) {
	        read = " readonly ";
		    disabled = " disabled"; 
		  }		  
%>

<h3 align="center"><% if (userPO.getHeader1().equals("V")) { 
										out.println("Proveedor "); 
					  } 
					  else if (userPO.getHeader1().equals("C")) {
					                                     out.println("Cliente "); 
					  }			  
					  else { out.println("Orden de Compra ");
					  } 
				   %>  
				   <% if (userPO.getPurpose().equals("NEW")) { 
										out.println("  - Nueva "); 
					   } 
					   else if (userPO.getPurpose().equals("MAINTENANCE")) {
					                               out.println(" - Mantenimiento "); 
					   }			  
					   else { out.println(" - Consulta ");
					   } 
				   %> Factura				
    <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" 
	name="EIBS_GIF" ALT="bills_list.jsp, EBP0130">
</h3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0130" >
	<input TYPE=HIDDEN name="SCREEN" value="5">
	<input TYPE=HIDDEN name="E01BPBNUM" value="<%= EBP0130Record.getE01BPBNUM().trim()%>">
	<input TYPE=HIDDEN name="E01BPBTYP" value="<%= EBP0130Record.getE01BPBTYP().trim()%>">
	<input TYPE=HIDDEN name="E01BPBSTS" value="<%= EBP0130Record.getE01BPBSTS().trim()%>">
	<input TYPE=HIDDEN name="E01BPGSEQ1" value="<%= EBP0130Record.getE01BPGSEQ1().trim()%>">
	<input TYPE=HIDDEN name="E01BPGSEQ2" value="<%= EBP0130Record.getE01BPGSEQ2().trim()%>">
	<input TYPE=HIDDEN name="E01BPGSEQ3" value="<%= EBP0130Record.getE01BPGSEQ3().trim()%>">
	<input TYPE=HIDDEN name="E01BPBPMD" value="<%= EBP0130Record.getE01BPBPMD().trim()%>">
	<input TYPE=HIDDEN name="userpox" value="<%= userPO.getPurpose()%>"> 
	<INPUT TYPE=HIDDEN name="E01REQTYP" value="<%= userPO.getHeader1().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQORD" value="<%= userPO.getHeader2().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQSTS" value="<%= userPO.getHeader3().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQFRM" value="<%= userPO.getHeader4().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQPDM" value="<%= userPO.getHeader5().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQPDD" value="<%= userPO.getHeader6().trim() %>">
	<INPUT TYPE=HIDDEN name="E01REQPDY" value="<%= userPO.getHeader7().trim() %>">


	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0" >
		<tr> <th nowrap colspan="5" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="5%" align="right">Número Interno : </td>
			<td nowrap width="5%" align="left"> <%= EBP0130Record.getE01BPBNUM().trim()%></td>
			<td nowrap width="5%" align="right">Proveedor : </td>
			<td nowrap width="60%" align="left"> 
				<input type="text" name="E01BPBCOD" maxlength="9" size="10" value="<%= EBP0130Record.getE01BPBCOD().trim()%>" <%= read %>  onkeypress="enterInteger()">
				<input type="text" name="E01BPBVCN" size="45"  maxlength="46" value="<%= EBP0130Record.getE01BPBVCN().trim()%>"  readonly>
					<a href="javascript:GetVendorBP('E01BPBCOD','E01BPBVCN')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0"></a>	
					<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatoy Field" align="bottom">
			</td>
			<td nowrap width="25%">Fecha de Ingreso : <%= datapro.eibs.master.Util.formatDate(EBP0130Record.getE01BPBRDM(),EBP0130Record.getE01BPBRDD(),EBP0130Record.getE01BPBRDY())%></td>
		</tr>
	</table>
	<h4><B>Información Basica</B></h4>  
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="4" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Referencia Factura :</td>
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBBIL" maxlength="25" size="26" <%= read %> value="<%= EBP0130Record.getE01BPBBIL().trim()%>">
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
			</td>
			<td nowrap width="10%" align="right">Tipo : </td>
			<td nowrap width="20%" align="left"> <%= EBP0130Record.getE01BPBTYPD().trim()%> 
			  <% if (userPO.getHeader1().equals("C") )  { %>	
				<INPUT type="text" name="E01BPBCUN" maxlength="9" size="10" <%= read %> value="<%= EBP0130Record.getE01BPBCUN().trim()%>" onkeypress="enterDecimal()">
			    	<A href="javascript:GetCustomerDescId('E01BPBCUN','E01BPBRM1','')" > 
			    	<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			    	</A> 
			  <%} %>  	
			</td>
		</tr>
		<tr id="trclear"> 
			<td nowrap width="10%" align="right">Banco :</td>
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBBNK" size="3" maxlength="2" <%= read %> value="<%= EBP0130Record.getE01BPBBNK().trim()%>" >
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
				Branch :       
				<input type="text" name="E01BPBBRN" size="4" maxlength="3" <%= read %> value="<%= EBP0130Record.getE01BPBBRN().trim()%>">
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
				Currency :
				<input type="text" name="E01BPBCCY" size="4" maxlength="3" <%= read %> value="<%= EBP0130Record.getE01BPBCCY().trim()%>">
					<a href="javascript:GetCurrency('E01BPBCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a>
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
			</td>
			<td nowrap width="10%" align="right"><% if (userPO.getHeader1().equals("P") )  { %> Orden de Compra : <% }%></td>
			<td nowrap width="40%" align="left" ><% if (userPO.getHeader1().equals("P") )  { %> 
				<input type="text" name="E01BPBORD" maxlength="9" size="10" <%= read %> value="<%= EBP0130Record.getE01BPBORD().trim()%>"> <% }%>
			</td>	
		</tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Fecha Factura : </td>
			<td nowrap width="40%" align="left">
				<input type="text" size="2" maxlength="2" name="E01BPBIVD" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBIVD().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBIVM" onKeypress="enterInteger()" value="<%= EBP0130Record.getE01BPBIVM().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBIVY" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBIVY().trim()%>" <%= read %>>
					<a href="javascript:DatePicker(document.forms[0].E01BPBIVD,document.forms[0].E01BPBIVM,document.forms[0].E01BPBIVY)">
					<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></a>
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom" border="0">
					<B><%=currUser.getE01DTF()%></B>
			</td>    
			<td nowrap width="10%" align="right"></td>
			<td nowrap width="40%" align="left"></td>
		</tr>
		<tr id="trclear" align="left"> 
			<td nowrap width="10%" align="right">Descripción Factura  </td>
			<td nowrap width="40%" align="left" >
				<input type="text" name="E01BPBDSC" size="30" maxlength="25" <%= read %> value="<%= EBP0130Record.getE01BPBDSC().trim()%>">
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
			<td nowrap width="10%" align="right">Monto Total :</td>
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBBAM" size="20" maxlength="19" <%= read %> value="<%= EBP0130Record.getE01BPBBAM().trim()%>" onkeypress="enterDecimal()">
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
			</td>
		</tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Observaciones : </td>
			<td nowrap width="40%" align="left"> 
				<input type="text" name="E01BPBRM1" maxlength="50" size="60" <%= read %> value="<%= EBP0130Record.getE01BPBRM1().trim()%>">
                <BR>
				<input type="text" name="E01BPBRM2" maxlength="50" size="60" <%= read %> value="<%= EBP0130Record.getE01BPBRM2().trim()%>">
			</td>
			<td nowrap width="10%" align="left"> </td>
			<td nowrap width="40%" align="left"> </td>
		</tr>
	</table> 
      
	<h4><B>Informacion para Pago</B></h4>  
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="4" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Fecha Vencimiento : </td>
			<td nowrap width="40%" align="left"> 
				<input type="text" size="2" maxlength="2" name="E01BPBPDD" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBPDD().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBPDM" onKeypress="enterInteger()" value="<%= EBP0130Record.getE01BPBPDM().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBPDY" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBPDY().trim()%>" <%= read %>>
					<a href="javascript:DatePicker(document.forms[0].E01BPBPDD,document.forms[0].E01BPBPDM,document.forms[0].E01BPBPDY)">
					<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a>
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom" border="0">
					<B><%=currUser.getE01DTF()%></B>
			</td>
			<td nowrap width="10%" align="right">Forma de Pago : </td>
			<td nowrap width="40%" align="left"> 
				<select name="E01BPBPVI" <%= read %>>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("")?"selected":"" %> value="">                   </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("A")?"selected":"" %> value="A">ACH              </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("R")?"selected":"" %> value="R">Cuenta Corriente </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("G")?"selected":"" %> value="G">Cuenta Contable  </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("C")?"selected":"" %> value="C">Cheque Gerencia  </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("S")?"selected":"" %> value="S">Swift            </option>
					<option <%= EBP0130Record.getE01BPBPVI().trim().equals("P")?"selected":"" %> value="P">Caja Menor       </option>
				</select>
			</td>
		</tr> 
		<tr id="trclear"> 
			<td nowrap width="10%" align="right">Tipo de Pago : </td>
			<td nowrap width="40%" align="left"> 
				<select name="E01BPBPTY" <%= read %>>
					<option <%= EBP0130Record.getE01BPBPTY().trim().equals("F")?"selected":"" %> value="F">Monto Fijo      </option>
					<option <%= EBP0130Record.getE01BPBPTY().trim().equals("V")?"selected":"" %> value="V">Monto Variable  </option>
				</select>
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
			</td>	
			<td nowrap width="10%" align="right">Frecuencia : </td>
			<td nowrap width="40%" align="left"> 
				<select name="E01BPBPFR" <%= read %>>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("O")?"selected":"" %> value="O">Una Vez      </option>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("W")?"selected":"" %> value="W">Semanal      </option>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("B")?"selected":"" %> value="B">Bi-Semanal   </option>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("M")?"selected":"" %> value="M">Mensual      </option>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("Q")?"selected":"" %> value="Q">Trimestral   </option>
					<option <%= EBP0130Record.getE01BPBPFR().trim().equals("Y")?"selected":"" %> value="Y">Anual        </option>
				</select>
					<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" border="0" ></td>
		</tr>	
		<tr id="trdark">
			<td nowrap width="10%" align="left"> <b>Si la Frecuencia es diferente de "Una Vez" : </b></td>
			<td nowrap width="40%" align="left"> </td>
			<td nowrap width="10%" align="left"> </td>
			<td nowrap width="40%" align="left"> </td>
		</tr>
			<tr id="trdark">
			<td nowrap width="10%" align="right"> Día de Pago : </td> 
			<td nowrap width="40%" align="left"> 
				<input type="text" size="3" maxlength="2" name="E01BPBDAY" onKeypress="enterInteger()" value="<%= EBP0130Record.getE01BPBDAY().trim()%>" <%= read %>>
					<a href="javascript:GetCode('E01BPBDAY','STATIC_rt_ciclo.jsp')">
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda Día" border="0" ></a>
			</td> 
			<td nowrap width="10%" align="right">Inicio Fecha de Pago : </td>
			<td nowrap width="40%" align="left">
				<input type="text" size="2" maxlength="2" name="E01BPBIDD" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBIDD().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBIDM" onKeypress="enterInteger()" value="<%= EBP0130Record.getE01BPBIDM().trim()%>" <%= read %>>
				<input type="text" size="2" maxlength="2" name="E01BPBIDY" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBIDY().trim()%>" <%= read %>>
					<a href="javascript:DatePicker(document.forms[0].E01BPBIDD,document.forms[0].E01BPBIDM,document.forms[0].E01BPBIDY)">
					<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0"></a>
					<B><%=currUser.getE01DTF()%></B>
			</td>
		</tr>
		<tr id="trclear"> 
			<td nowrap width="10%" align="right">Límites de Pago : </td> 
			<td nowrap width="40%" align="left"> 
				<select name="E01BPBPLI" <%= read %>>
					<option <%= EBP0130Record.getE01BPBPLI().trim().equals("T")?"selected":"" %> value="T">Factura Total</option>
					<option <%= EBP0130Record.getE01BPBPLI().trim().equals("F")?"selected":"" %> value="F">Valor Fijo</option>
					<option <%= EBP0130Record.getE01BPBPLI().trim().equals("P")?"selected":"" %> value="P">Porcentaje</option>
				</select>
			</td>
			<td nowrap width="10%" align="right"> % Tolerancia : </td> 
			<td nowrap width="40%" align="left"> 
				<input type="text" name="E01BPBPER" maxlength="9" size="10" <%= read %> value="<%= EBP0130Record.getE01BPBPER().trim()%>" onkeypress="enterDecimal()">
			<b>(Unicamente si Límite de Pago es "Porcentaje")</b></td>
		</tr>	
		<tr id="trdark">
			<td nowrap width="10%" align="left"> <b>Si Tipo de Pago es "Variable" : </b></td>
			<td nowrap width="40%" align="left"> </td>
			<td nowrap width="10%" align="left"> </td>
			<td nowrap width="40%" align="left"> </td>
		</tr>
		<tr id="trdark">
			<td nowrap width="10%" align="right">Número de Pagos : </td>
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBNOP" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPBNOP().trim()%>" onkeypress="enterInteger()">
			</td> 
			<td nowrap width="10%" align="right">Monto Mínimo de Pago : </td> 
			<td nowrap width="40%" align="left"> 
				<input type="text" name="E01BPBMAM" maxlength="15" size="20" <%= read %> value="<%= EBP0130Record.getE01BPBMAM().trim()%>" onkeypress="enterDecimal()">
			</td>
		</tr>
	</table>  

	<% if (userPO.getHeader1().equals("C") )  { %>
	<h5></h5>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="4" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Tabla Comisiones : </td>
			<td nowrap width="40%" align="left">  
				<input type="text" name="E01BPBFCO" maxlength="2" size="3" <%= read %> value="<%= EBP0130Record.getE01BPBFCO().trim()%>" onkeypress="enterInteger()">        
					<a href="javascript:GetCNTRLPRF('E01BPBFCO','E01BPBFAM')">
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="middle" border="0"></a>
			</td>
			<td nowrap width="10%" align="right">Monto Comisión : </td> 
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBFAM" maxlength="17" size="18" <%= read %> value="<%= EBP0130Record.getE01BPBFAM().trim()%>" onKeyPress="enterDecimal()">
			</td>
		</tr>
		</table>
	<% } %>

	<h4><B>Informacion de Cuenta Contable</B> </h4>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="4" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Amortización : </td>
			<td nowrap width="40%" align="left"> 
				<input type="radio" name="E01BPBAMC" <%= read %> value="Y" <% if (EBP0130Record.getE01BPBAMC().equals("Y")) out.print("checked"); %>>
				Si 
				<input type="radio" name="E01BPBAMC" <%= read %> value="N" <% if (EBP0130Record.getE01BPBAMC().equals("N")) out.print("checked"); %>>
				No          
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></td>
			<td nowrap width="10%" align="right">Amortization Number of Months : </td> 
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBAMS" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPBAMS().trim()%>">
			</td>
		</tr>
		<tr id="trclear"> 
			<td nowrap width="10%" align="right">Comenzar Amortización en  </td>
			<td nowrap width="40%"align="left"> Año: <input type="text" name="E01BPBAYY" maxlength="2" size="3" <%= read %> value="<%= EBP0130Record.getE01BPBAYY().trim()%>">
                  								Mes: <input type="text" name="E01BPBAMM" maxlength="2" size="3" <%= read %> value="<%= EBP0130Record.getE01BPBAMM().trim()%>">
			</td>	
			<td nowrap width="10%" align="right"> </td>
			<td nowrap width="40%" align="left">
			</td>   
		</tr>
	</table>
	<h6></h6>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="9" width="100%"> </th> </tr>
		<tr id="trdark">
			<td nowrap width="10%" align="center"> </td>          
			<td nowrap width="10%" align="center">Banco</td>
			<td nowrap width="10%" align="center">Oficina</td>
			<td nowrap width="10%" align="center">Moneda</td>
			<td nowrap width="10%" align="center">Cuenta Contable</td>
			<td nowrap width="10%" align="center">Cuenta Cliente</td>
			<td nowrap width="10%" align="center">Centro de Costo</td>
			<td nowrap width="10%" align="center">DB/CR</td>
			<td nowrap width="20%" align="center">Monto </td>
		</tr>
		<tr id="trclear" align="center"> 
			<td nowrap width="10%" align="right">Base</td>           
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGBNK1" size="3" maxlength="2" <%= read %> value="<%= EBP0130Record.getE01BPGBNK1().trim()%>" >
			</td>
			<td nowrap width="10%" align="center" >
				<input type="text" name="E01BPGBRN1" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGBRN1().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01BPGBNK1.value,'','','','')" value="<%= EBP0130Record.getE01BPGBRN1()%>">
			</td>	
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGCCY1" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGCCY1().trim()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01BPGBNK1.value,'','','','')">
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGGLN1" maxlength="18" size="16" <%= read %> value="<%= EBP0130Record.getE01BPGGLN1().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01BPGBNK1.value,document.forms[0].E01BPGCCY1.value,'','','')">
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGACC1" maxlength="14" size="12" <%= read %> value="<%= EBP0130Record.getE01BPGACC1().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01BPGBNK1.value,'document.forms[0].E01BPBCUN.value','','','RT')">
			</td>
			<td nowrap width="10%" align="center">  
				<input type="text" name="E01BPGCCN1" maxlength="9" size="8" <%= read %> value="<%= EBP0130Record.getE01BPGCCN1().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01BPGBNK1.value,'','','','')">
			</td>
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGDC1" maxlength="3" size="2"  readonly value="<%= EBP0130Record.getE01BPGDC1().trim()%>">
			</td> 
			<td nowrap width="20%" align="center"> 
				<input type="text" name="E01BPGAMT1" maxlength="17" size="20" <%= read %> value="<%= EBP0130Record.getE01BPGAMT1().trim()%>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr id="trdark" align="center"> 
			<td nowrap width="10%" align="right">Impuestos</td> 
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGBNK2" size="3" maxlength="2" <%= read %> value="<%= EBP0130Record.getE01BPGBNK2().trim()%>" >
			</td>
			<td nowrap width="10%" align="center" >
				<input type="text" name="E01BPGBRN2" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGBRN2().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01BPGBNK2.value,'','','','')" value="<%= EBP0130Record.getE01BPGBRN2()%>">	      
			</td>	
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGCCY2" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGCCY2().trim()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01BPGBNK2.value,'','','','')">
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGGLN2" maxlength="18" size="16" <%= read %> value="<%= EBP0130Record.getE01BPGGLN2().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01BPGBNK2.value,document.forms[0].E01BPGCCY2.value,'','','')">
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGACC2" maxlength="14" size="12" <%= read %> value="<%= EBP0130Record.getE01BPGACC2().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01BPGBNK2.value,'','','','RT')">
			</td>
			<td nowrap width="10%" align="center">  
				<input type="text" name="E01BPGCCN2" maxlength="9" size="8" <%= read %> value="<%= EBP0130Record.getE01BPGCCN2().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01BPGBNK2.value,'','','','')">
			</td> 
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGDC2"  maxlength="3" size="2" readonly  value="<%= EBP0130Record.getE01BPGDC2().trim()%>">
			</td>
			<td nowrap width="20%" align="center"> 
				<input type="text" name="E01BPGAMT2" maxlength="17" size="20" <%= read %> value="<%= EBP0130Record.getE01BPGAMT2().trim()%>" onkeypress="enterDecimal()">
			</td>
		</tr>
		<tr id="trclear" align="center">
			<td nowrap width="10%" align="right">Otros</td>  
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGBNK3" maxlength="2" size="3" <%= read %> value="<%= EBP0130Record.getE01BPGBNK3().trim()%>" >
			</td>
			<td nowrap width="10%" align="center" >
				<input type="text" name="E01BPGBRN3" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGBRN3().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01BPGBNK3.value,'','','','')" value="<%= EBP0130Record.getE01BPGBRN3()%>">
			</td>	
			<td nowrap width="10%" align="center">
				<input type="text" name="E01BPGCCY3" maxlength="3" size="4" <%= read %> value="<%= EBP0130Record.getE01BPGCCY3().trim()%>"
					oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01BPGBNK3.value,'','','','')">
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGGLN3" maxlength="18" size="16" <%= read %> value="<%= EBP0130Record.getE01BPGGLN3().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01BPGBNK3.value,document.forms[0].E01BPGCCY3.value,'','','')" >
			</td>
			<td nowrap width="10%" align="center"> 
				<input type="text" name="E01BPGACC3" maxlength="14" size="12" <%= read %> value="<%= EBP0130Record.getE01BPGACC3().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01BPGBNK3.value,'','','','RT')">
			</td>
			<td nowrap width="10%" align="center">  
				<input type="text" name="E01BPGCCN3" maxlength="7" size="6" <%= read %> value="<%= EBP0130Record.getE01BPGCCN3().trim()%>" onkeypress="enterInteger()"
					oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01BPGBNK3.value,'','','','')">
			</td> 
			<td nowrap width="10%" align="center">
				<SELECT name="E01BPGDC3" <%= read %>>
					<OPTION <%= EBP0130Record.getE01BPGDC3().trim().equals("DB")?"selected":"" %> value="DB">DB</OPTION>
					<OPTION <%= EBP0130Record.getE01BPGDC3().trim().equals("CR")?"selected":"" %> value="CR">CR</OPTION>
				</SELECT>
			</td>
			<td nowrap width="20%" align="center">
				<input type="text" name="E01BPGAMT3" maxlength="17" size="20" <%= read %> value="<%= EBP0130Record.getE01BPGAMT3().trim()%>" onkeypress="enterDecimal()">               
			</td>
		</tr>
	</table>

	<% if (userPO.getHeader3().equals("A")) { 
		if (!EBP0130Record.getE01BPBPMD().equals("0")) {%>
	<h4><B>Información Suspensión</B></h4> 
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr> <th nowrap colspan="4" width="100%"> </th> </tr>
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Suspendido en Fecha : </td>
			<td nowrap width="40%" align="left">
				<input type="text" size="2" maxlength="2" name="E01BPBSDM" onKeypress="enterInteger()" value="<%= EBP0130Record.getE01BPBSDM().trim()%> " >
				<input type="text" size="2" maxlength="2" name="E01BPBSDD" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBSDD().trim()%>" >
				<input type="text" size="2" maxlength="2" name="E01BPBSDY" onKeyPress="enterInteger()" value="<%= EBP0130Record.getE01BPBSDY().trim()%>" >
					<a href="javascript:DatePicker(document.forms[0].E01BPBSDD,document.forms[0].E01BPBSDM,document.forms[0].E01BPBSDY)">
					<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a>
			</td>
			<td nowrap width="10%" align="right">Razón de Suspensión : </td> 
			<td nowrap width="40%" align="left">
				<input type="text" name="E01BPBSDS" size="55" maxlength="50" value="<%= EBP0130Record.getE01BPBSDS().trim()%>" >
			</td>
		</tr>
	</table><% }
    } %>
    <h5></h5>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
		<tr id="trdark"> 
			<td nowrap width="10%" align="right">Estatus : </td>
			<td nowrap width="40%" align="left"><%= EBP0130Record.getE01BPBSTD().trim()%></td>
			<td nowrap width="10%" align="right"> </td> 
			<td nowrap width="40%">Aprobación/Fecha Rechazo : <%= datapro.eibs.master.Util.formatDate(EBP0130Record.getE01BPBADM(),EBP0130Record.getE01BPBADD(),EBP0130Record.getE01BPBADY())%></td>
		</tr>
	</table>
	<% if (!userPO.getPurpose().equals("INQUIRY")) {%>
	<h5></h5>
	<table width="100%">
		<tr> <th nowrap colspan="3" width="100%"> </th> </tr>		
		<tr>
			<td width="33%">
				<div align="center"> 
					<input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction(6);" >
				</div>	
			</td>
			<td width="33%"> 
				<div align="center"> 
					<input id="EIBSBTN" type=button name="Delete" value="Borrar" onClick="goAction(4);" <%= disabled %>>
				</div>	
			</td>
			<td width="34%">
				<div align="center"> 
					<input id="EIBSBTN" type=submit name="Exit" value="Salir"
							onClick="goAction(2);" >
				</div>	
			</td>
		</tr>	
	</table>	
	<% } %>
</form>
</body>
</html>
