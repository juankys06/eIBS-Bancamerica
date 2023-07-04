<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Treasury Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "trOption" class= "datapro.eibs.beans.TrOption"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT> 
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
<script language="JavaScript">
function changeOpe(ope){
	if (ope == 'PU') {
		document.getElementById("E01DLSHEM").value = '0';
		document.getElementById("E01DLSHEM").disabled=true; 
		document.getElementById("E01DLSTHR").disabled=false;
		document.getElementById("E01DLSCCY").disabled=false;
		document.getElementById("E01DLSRA1").disabled=false;
		document.getElementById("E01DLSEQV").disabled=false;
		document.getElementById("E01DLSMA1").disabled=false;
		document.getElementById("E01DLSMA2").disabled=false;
		document.getElementById("E01DLSMA3").disabled=false;
		document.getElementById("E01DLSLI1").disabled=false;
		document.getElementById("E01DLSLI2").disabled=false;
		document.getElementById("E01DLSLI3").disabled=false;
		document.getElementById("E01DLSROY").disabled=false;
		document.forms[0].E01DLSODA.disabled = false;
		document.forms[0].E01DEARRP.disabled = false;
        document.getElementById("CONTRACT").style.visibility="hidden";
        document.getElementById("CURRENCY").style.visibility="visible";
        document.getElementById("DATECPN").style.visibility="visible";
	} else {
		document.getElementById("E01DLSHEM").disabled=false;
		document.getElementById("E01DLSTHR").disabled=true;
		document.getElementById("E01DLSCCY").disabled=true;
		document.getElementById("E01DLSRA1").disabled=true;
		document.getElementById("E01DLSEQV").disabled=true;
		document.getElementById("E01DLSMA1").disabled=true;
		document.getElementById("E01DLSMA2").disabled=true;
		document.getElementById("E01DLSMA3").disabled=true;
		document.getElementById("E01DLSLI1").disabled=true;
		document.getElementById("E01DLSLI2").disabled=true;
		document.getElementById("E01DLSLI3").disabled=true;
		document.getElementById("E01DLSROY").disabled=true;
		document.forms[0].E01DLSODA.disabled = true; 
		document.forms[0].E01DEARRP.disabled = true;
        document.getElementById("CONTRACT").style.visibility="visible";
        document.getElementById("CURRENCY").style.visibility="hidden";
        document.getElementById("DATECPN").style.visibility="hidden";
	}
}	

function Calculate(){
  document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=4300";
  document.forms[0].submit();
}

function getContract(){
  document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=4350";
  document.forms[0].submit();
}

function GetProductB(name,desc,app,bank)
{
	page= prefix +language + "EWD0008B_client_help_container.jsp"
	fieldName=name;
	fieldAux1 = desc; 
	AppCode = app;
	ProductBank = bank;				
	CenterWindow(page,600,400,1);
}

</script>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
String sTitle = "Papel Comercial";
try {
   sTitle = trOption.getHeader2();
} catch (Exception e) {
   sTitle = "Papel Comercial";
}   
if (sTitle == null) sTitle = "Papel Comercial";

%> 
<h3 align="center"><%= sTitle%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_cp, EDL0120"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120" >
  <input type="hidden" name="E01DLSCMM"  value="<%= deal.getE01DLSCMM()%>" >
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
            <td nowrap width="85%" > 
              <div align="left"> 
                <input type="hidden" name="E01DLSCUN"  value="<%= deal.getE01DLSCUN()%>" >  
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>" >
                <%= deal.getE01DLSCUN()%> - <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" >
              <%= deal.getD01DLSCP2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">
                <input type=HIDDEN name="SCREEN" value="6">
              </div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>" >
              <%= deal.getD01DLSCP3()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" >
              <%= ogen%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <br>
  <table  class="tableinfo" width="100%">
    <tr > 
     	<td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
       		<tr id="trdark">
				<td nowrap width="25%" align="right"></td>
            	<td nowrap width="15%" align="left"></td>
				<td nowrap width="10%" align="left"></td>
            	<td nowrap width="25%" align="right"></td>
				<td nowrap width="25%" align="right">
					Fecha Pacto : <%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%>
				</td>
			</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" align="right"> </td>
            	<td nowrap width="15%" align="right"></td>
				<td nowrap width="10%" align="left"> </td>
				<td nowrap width="25%" align="center"> 
					<B>Tipo de Operaci&oacute;n</B><br>
              		<SELECT name="E01DLSSBT" onchange="changeOpe(this.value)">
                		<OPTION value="PU" <% if (deal.getE01DLSSBT().equals("PU")) out.print("selected"); %>>Nueva Compra</OPTION>
                		<OPTION value="PA" <% if (deal.getE01DLSSBT().equals("PA")) out.print("selected"); %>>Compra Adicional</OPTION>
                		<OPTION value="SL" <% if (deal.getE01DLSSBT().equals("SL")) out.print("selected"); %>>Venta</OPTION>
 		           		<!--  
						<OPTION value="S1" <% if (deal.getE01DLSSBT().equals("S1")) out.print("selected"); %>>Re-Venta</OPTION>
                		<OPTION value="PR" <% if (deal.getE01DLSSBT().equals("PR")) out.print("selected"); %>>Re-Compra</OPTION>
                		<OPTION value="RL" <% if (deal.getE01DLSSBT().equals("RL")) out.print("selected"); %>>Liberar Custodia</OPTION>
						-->
              		</SELECT>
            	</td>
            	<td nowrap width="25%" align="left">
					<Div id="CONTRACT">
					 <H5>N&uacute;mero de Contrato<BR>
					 <INPUT type="text" name="E01DLSHEM" size="13" maxlength="12" onblur="getContract()"
						onkeypress="enterInteger()" value="<%= deal.getE01DLSHEM() %>"> <A
						href="javascript:GetAccount('E01DLSHEM','','IN','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
						align="bottom" border="0"></A></H5>
					</Div>
				</td>
          	</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">C&oacute;digo Instrumento :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSTHR" size="15" maxlength="15" value="<%= deal.getE01DLSTHR()%>">
            	</td>
				<TD nowrap width="10%"></TD>
				<td nowrap width="25%" align="right"></td>
            	<td nowrap width="25%" align="left"></td>
          	</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" align="right">Moneda : </td>
            	<td nowrap width="15%" align="left"> 
	            	<input type="text" name="E01DLSCCY" size="4" maxlength="3" value="<%= deal.getE01DLSCCY().trim()%>" > 
            	</td>
				<TD nowrap width="10%" align="left">
			  		<div id="CURRENCY">  	 
						<A href="javascript:GetCurrency('E01DLSCCY','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" border="0"></A>
			  		</div>	
				</TD>
				<td nowrap width="25%" align="right">Precio : </td>
            	<td nowrap width="25%" align="left"> 
              		<input type="text" name="E01DLSRA3" size="18" maxlength="17" value="<%= deal.getE01DLSRA3().trim()%>" >
              		<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            	</td>
          	</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Valor Nominal :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSAMN" size="15" maxlength="13" value="<%= deal.getE01DLSAMN()%>" 
			  			onKeyPress="enterDecimal()">
            	</td>
				<TD nowrap width="10%" align="left"></TD>
				<td nowrap width="25%" align="right">Rendimiento :</td>
            	<td nowrap width="25%" align="left"> 
              		<input type="text" name="E01DLSRA2" size="18" maxlength="17" value="<%= deal.getE01DLSRA2().trim()%>" >
            	</td>
          	</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" align="right">Tasa :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSRA1" size="11" maxlength="11" value="<%= deal.getE01DLSRA1().trim()%>" >
            	</td>
				<TD nowrap width="10%" align="left"></TD>
				<td nowrap width="25%" align="right">Monto Neto :</td>
            	<td nowrap width="25%" align="left"> 
              		<input type="text" name="E01DLSAM1" size="15" maxlength="13" value="<%= deal.getE01DLSAM1()%>" 
			  			onKeyPress="enterDecimal()">
            	</td>
          	</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Fecha de Pago Ultimo Cupón :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSLI1" size="3" maxlength="2" value="<%= deal.getE01DLSLI1().trim()%>">
              		<input type="text" name="E01DLSLI2" size="3" maxlength="2" value="<%= deal.getE01DLSLI2().trim()%>">
              		<input type="text" name="E01DLSLI3" size="3" maxlength="2" value="<%= deal.getE01DLSLI3().trim()%>"> 
            	</td>
				<TD nowrap width="10%" align="left">
					<Div id="DATECPN">
					<A href="javascript:DatePicker(document.forms[0].E01DLSLI1,document.forms[0].E01DLSLI2,document.forms[0].E01DLSLI3)"><IMG
						src="<%=request.getContextPath()%>/images/calendar.gif" alt="help"
						align="middle" border="0">
					</A>
					</Div>
				</TD>
				<td nowrap width="25%" align="right">Prima/Descuento :</td>
            	<td nowrap width="25%" align="left"> 
	              <input type="text" name="E01DLSEQV" size="15" maxlength="13" value="<%= deal.getE01DLSEQV()%>" readonly>
    	        </td>
        	</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" align="right">Fecha Valor :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSVD1" size="3" maxlength="2" value="<%= deal.getE01DLSVD1().trim()%>">
              		<input type="text" name="E01DLSVD2" size="3" maxlength="2" value="<%= deal.getE01DLSVD2().trim()%>">
              		<input type="text" name="E01DLSVD3" size="3" maxlength="2" value="<%= deal.getE01DLSVD3().trim()%>"> 
            	</td>
				<TD nowrap width="10%" align="left">
					<A href="javascript:DatePicker(document.forms[0].E01DLSVD1,document.forms[0].E01DLSVD2,document.forms[0].E01DLSVD3)"><IMG
						src="<%=request.getContextPath()%>/images/calendar.gif" alt="help"
						align="middle" border="0"></A>
				</TD>
				<td nowrap width="25%" align="right">Fecha Vencimiento :</td>
            	<td nowrap width="25%" align="left"> 
	              	<input type="text" name="E01DLSMA1" size="3" maxlength="2" value="<%= deal.getE01DLSMA1().trim()%>">
              		<input type="text" name="E01DLSMA2" size="3" maxlength="2" value="<%= deal.getE01DLSMA2().trim()%>">
              		<input type="text" name="E01DLSMA3" size="3" maxlength="2" value="<%= deal.getE01DLSMA3().trim()%>">
              		<a href="javascript:DatePicker(document.forms[0].E01DLSMA1,document.forms[0].E01DLSMA2,document.forms[0].E01DLSMA3)"> 
              			<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
            	</td>
          	</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Plazo :</td>
            	<td nowrap width="15%" align="left"> 
              		<input type="text" name="E01DLSROY" size="4" maxlength="3" value="<%= deal.getE01DLSROY().trim()%>" >
              			<select class="inputs" name="E01DLSODA">
                			<option value="D" <% if (deal.getE01DLSODA().equals("D")) out.print("selected"); %>>Dia</option>
		                	<option value="M" <% if (deal.getE01DLSODA().equals("M")) out.print("selected"); %>>Mes</option>
		                	<option value="Y" <% if (deal.getE01DLSODA().equals("Y")) out.print("selected"); %>>Año</option>
              			</select>
            	</td>
				<TD nowrap width="10%" align="left"></TD>
				<td nowrap width="25%" align="right">Tipo de Cálculo de Interés :</td>
            	<td nowrap width="25%"> 
              		<select class="inputs" name="E01DEARRP">
                		<option value=" " <% if (!(deal.getE01DEARRP().equals("1") ||deal.getE01DEARRP().equals("2")||deal.getE01DEARRP().equals("3")||deal.getE01DEARRP().equals("4")||deal.getE01DEARRP().equals("5")||deal.getE01DEARRP().equals("6"))) out.print("selected"); %>></option>
                		<option value="1" <% if (deal.getE01DEARRP().equals("1")) out.print("selected"); %>>Actual/actual (en periodo)</option>
		                <option value="2" <% if (deal.getE01DEARRP().equals("2")) out.print("selected"); %>>Actual/365</option>
		                <option value="3" <% if (deal.getE01DEARRP().equals("3")) out.print("selected"); %>>Actual/365 (366 en bisiesto)</option>
		                <option value="4" <% if (deal.getE01DEARRP().equals("4")) out.print("selected"); %>>Actual/360</option>
		                <option value="5" <% if (deal.getE01DEARRP().equals("5")) out.print("selected"); %>>30/360</option>
		                <option value="6" <% if (deal.getE01DEARRP().equals("6")) out.print("selected"); %>>30E/360</option>
              		</select>
            	</td>
          	</tr>
          <tr id="trclear"> 
          <% if(deal.getH01FLGWK3().equals("Y")){%>
          	<td nowrap width="21%" > 
              <div align="right">Producto : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DLSPRO" size="5" maxlength="4" value="<%= deal.getE01DLSPRO()%>">
              <a href="javascript:GetProduct('E01DLSPRO','13','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <input type="text" name="D01PRDDSC" size="30" value="<%= deal.getD01PRDDSC()%>" readonly>
            </td>
           <%} else {%>
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
           <%}%> 
            <td nowrap > 
              <div align="right"> </div>
            </td>
            <td nowrap > 
            </td>
          </tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Notas :</td>
				<td nowrap width="15%" align="left" colspan=4>
					<input type="text" name="E01DLSOT1" size="70" maxlength="60" value="<%= deal.getE01DLSOT1().trim()%>">
				</td>
			</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" align="right"></td>
				<td nowrap width="15%" align="left" colspan=4>
					<input type="text" name="E01DLSOT2" size="70" maxlength="60" value="<%= deal.getE01DLSOT2().trim()%>">
				</td>
			</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Tesorero :</td>
				<td nowrap width="15%" align="left" colspan=4>
					<%= deal.getE01DLSDID().trim()%> - <%= deal.getD01USRDSC().trim()%></td>
			</tr>
          	<tr id="trclear"> 
            	<td nowrap width="25%" ></td>
				<td nowrap width="15%" align="left" colspan=4></td>
			</tr>
          	<tr id="trdark"> 
            	<td nowrap width="25%" align="right">Interes :</td>
            	<td nowrap width="15%" align="left">
                	<input type="text" name="E01DLSAM5" size="15" maxlength="13" value="<%= deal.getE01DLSAM5()%>" readonly>
            	</td>
				<TD nowrap width="10%" align="left"></TD>
				<td nowrap width="25%" align="right">Capital + Interes :</td>
            	<td nowrap width="25%" align="left">
              		<input type="text" name="E01DLSAM12" size="15" maxlength="13" value="<%= deal.getE01DLSAM1()%>" readonly>
            	</td>
          	</tr>
        </table>
      </td>
    </tr>

  </table>
  <br>
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
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input type="checkbox" name="H01FLGWK1" value="1" >
          Aceptar con Errores</div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="16%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
      <td width="17%">
        <div align="center">
          <input id="EIBSBTN" type=button name="Submit2" value="Calcular" onClick="Calculate()">
        </div>
      </td>
    </tr>
  </table>
  <B> 
	<SCRIPT language="JavaScript">
		changeOpe('<%= deal.getE01DLSSBT().trim()%>');	
	</SCRIPT>
 </B>
 </form>
</body>
</html>
