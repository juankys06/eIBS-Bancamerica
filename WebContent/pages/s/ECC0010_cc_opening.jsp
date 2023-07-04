<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Credit Cards Opening</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="ccNew" class="datapro.eibs.beans.ECC001001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">
 
<%if (!userPO.getPurpose().equals("NEW")){%>

	builtNewMenu(cc_m_opt);

<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
 if (!userPO.getPurpose().equals("NEW")){
   out.println("<SCRIPT> initMenu();  </SCRIPT>");}

%> 

</head>
<body>
<h3 align="center">
<% if (userPO.getPurpose().equals("NEW")) out.print("Apertura Tarjeta de Credito");
   else if (userPO.getPurpose().equals("MAINTENANCE")) out.print("Mantenimiento Tarjeta de Credito");
   else out.print("");
%>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_opening.jsp,ECC0010"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<% if (userPO.getPurpose().equals("NEW")) out.print("2"); else out.print("4");%>">
  <INPUT TYPE=HIDDEN NAME="E01CCMBNK" VALUE="<%= ccNew.getE01CCMBNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01CCMATY" VALUE="<%= ccNew.getE01CCMATY().trim()%>">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CCMCUN" size="9" maxlength="9" value="<%= ccNew.getE01CCMCUN().trim()%>" <%if (!userPO.getPurpose().equals("NEW")) {%> readonly <%}%>>
                <%if (userPO.getPurpose().equals("NEW")) {%> 
                <a href="javascript:GetCustomerDescId('E01CCMCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a></div>
                <%}%>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= ccNew.getE01CUSNA1().trim()%>" <%if (!userPO.getPurpose().equals("NEW")) {%> readonly <%}%>>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCMACC" size="12" maxlength="9" value="<% if (ccNew.getE01CCMACC().trim().equals("999999999999")) out.print("NUEVA CUENTA"); else out.print(ccNew.getE01CCMACC().trim()); %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CCMCCY" size="3" maxlength="3" value="<%= ccNew.getE01CCMCCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01CCMPRO" size="4" maxlength="4" value="<%= ccNew.getE01CCMPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01CCMOPD" size="3" maxlength="2" value="<%= ccNew.getE01CCMOPD().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCMOPM" size="3" maxlength="2" value="<%= ccNew.getE01CCMOPM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCMOPY" size="5" maxlength="4" value="<%= ccNew.getE01CCMOPY().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DOBPicker(document.forms[0].E01CCMOPM,document.forms[0].E01CCMOPD,document.forms[0].E01CCMOPY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMEXD" size="3" maxlength="2" value="<%= ccNew.getE01CCMEXD().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCMEXM" size="3" maxlength="2" value="<%= ccNew.getE01CCMEXM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCMEXY" size="5" maxlength="4" value="<%= ccNew.getE01CCMEXY().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DOBPicker(document.forms[0].E01CCMEXM,document.forms[0].E01CCMEXD,document.forms[0].E01CCMEXY)">
              <img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a> 
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>        
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Tabla Tarifas:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMTBL" size="2" maxlength="2" value="<%= ccNew.getE01CCMTBL().trim()%>">
				<a href="javascript:GetTableFeeCod('E01CCMTBL',document.forms[0].E01CCMATY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                         
            </td>
            <td nowrap width="25%"> 
              <div align="right">Numero de Referencia:</div>
            </td>
            <td nowrap width="27%"> 
 				<input type="text" name="E01CCMNXN" size="21" maxlength="20" value="<%= ccNew.getE01CCMNXN().trim()%>">            
 			</td>
          </tr>         
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo Diseño:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMDCD" size="5" maxlength="4" value="<%= ccNew.getE01CCMDCD().trim()%>">
 				<a href="javascript:GetCodeCreditCard('E01CCMDCD','03')">
 				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Impresion:</div>
            </td>
            <td nowrap width="27%"> 
 				<input type="text" name="E01CCMECD" size="5" maxlength="4" value="<%= ccNew.getE01CCMECD().trim()%>">  
 				<a href="javascript:GetCodeCreditCard('E01CCMECD','04')">
 				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
 			</td>
          </tr> 
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Tabla de tasas:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMROT" size="3" maxlength="2" value="<%= ccNew.getE01CCMROT().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo de Renovacion:</div>
            </td>
            <td nowrap width="27%"> 
 				<input type="text" name="E01CCMRNC" size="5" maxlength="4" value="<%= ccNew.getE01CCMRNC().trim()%>">            
 				<a href="javascript:GetCodeCreditCard('E01CCMRNC','10')">
 				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
 			</td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Cobranza:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMCLT" size="3" maxlength="2" value="<%= ccNew.getE01CCMCLT().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Permite Sobregiro:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="radio" name="E01CCMODA" value="Y" <%if(ccNew.getE01CCMODA().equals("Y")) out.print("checked");%>>Si
              <input type="radio" name="E01CCMODA" value="N" <%if(ccNew.getE01CCMODA().equals("N")) out.print("checked");%>>No 
 			</td>
          </tr>            
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Ciclo Facturacion:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMCFA" size="3" maxlength="2" value="<%= ccNew.getE01CCMCFA().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo FV:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMCPM" size="5" maxlength="4" value="<%= ccNew.getE01CCMCPM().trim()%>"> 
 			</td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Estado de la Cuenta:</div>
            </td>
            <td nowrap width="23%"> 
              <select name="E01CCMAST">
                <option value=" " <% if (!(
		                	ccNew.getE01CCMAST().equals("A") || 
                			ccNew.getE01CCMAST().equals("C") || 
                			ccNew.getE01CCMAST().equals("V"))) 
                			out.print("selected"); %> selected></option>
                <option value="A" <% if(ccNew.getE01CCMAST().equals("A")) out.print("selected");%>>Activo</option>
                <option value="C" <% if(ccNew.getE01CCMAST().equals("C")) out.print("selected");%>>Cancelado</option>                			
                <option value="V" <% if(ccNew.getE01CCMAST().equals("V")) out.print("selected");%>>Vencido</option>
              </select>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Numero de Tarjeta Primaria:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMPCN" size="21" maxlength="20" value="<%= ccNew.getE01CCMPCN().trim()%>" onKeypress="enterInteger()">
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" >  
 			</td>
          </tr>                
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo de Descuento:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMRBC" size="2" maxlength="1" value="<%= ccNew.getE01CCMRBC().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cupo Nacional :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMLBC" size="17" maxlength="16" value="<%= ccNew.getE01CCMLBC().trim()%>" onKeypress="enterDecimal()"> 
 			</td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Cupo Internacional:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMLFC" size="17" maxlength="16" value="<%= ccNew.getE01CCMLFC().trim()%>" onKeypress="enterDecimal()"> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto para la Linea:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMLNL" size="17" maxlength="16" value="<%= ccNew.getE01CCMLNL().trim()%>" onKeypress="enterDecimal()"> 
 			</td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Monto de Avances:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMLAD" size="17" maxlength="16" value="<%= ccNew.getE01CCMLAD().trim()%>" onKeypress="enterDecimal()"> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto de Pagos. :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMLQT" size="17" maxlength="16" value="<%= ccNew.getE01CCMLQT().trim()%>" onKeypress="enterDecimal()"> 
 			</td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Pago Minimo:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMTPM" size="2" maxlength="1" value="<%= ccNew.getE01CCMTPM().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto de Pago Minimo:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMMPA" size="17" maxlength="16" value="<%= ccNew.getE01CCMMPA().trim()%>" onKeypress="enterDecimal()"> 
 			</td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Nacional a Aplicar:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMUNB" size="17" maxlength="16" value="<%= ccNew.getE01CCMUNB().trim()%>" onKeypress="enterDecimal()"> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto Internacional a Aplicar:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMUNF" size="17" maxlength="16" value="<%= ccNew.getE01CCMUNF().trim()%>" onKeypress="enterDecimal()"> 
 			</td>
          </tr>      
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo Cobranza :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMCC2" size="5" maxlength="4" value="<%= ccNew.getE01CCMCC2().trim()%>" > 
 				<a href="javascript:GetCodeCreditCard('E01CCMCC2','06')">
 				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Codigo Balance a Transferir:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMCTS" size="2" maxlength="1" value="<%= ccNew.getE01CCMCTS().trim()%>" > 
 			</td>
          </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Codigo VIP  :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMVIP" size="2" maxlength="1" value="<%= ccNew.getE01CCMVIP().trim()%>" > 
 				<a href="javascript:GetCodeCreditCard('E01CCMVIP','14')">
 				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
            <td nowrap width="25%"> 
              <div align="right">No. Tarjetas Adicionales:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMNCC" size="8" maxlength="7" value="<%= ccNew.getE01CCMNCC().trim()%>" onKeypress="enterInteger()"> 
 			</td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Cuenta de Repago:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMRPA" size="11" maxlength="10" value="<%= ccNew.getE01CCMRPA().trim()%>" onKeypress="enterInteger()"> 
            </td>
            <td nowrap width="25%">
            	<div align="right">Centro de Costos:</div>
            </td>
            <td nowrap width="27%">
              <input type="text" name="E01CCMCCN" size="8" maxlength="6" value="<%= ccNew.getE01CCMCCN().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetCostCenter('E01CCMCCN',document.forms[0].E01CCMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a>             
            </td>
          </tr>           
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Compra:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMCCU" size="2" maxlength="1" value="<%= ccNew.getE01CCMCCU().trim()%>" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tipo de Cliente:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMTCI" size="2" maxlength="1" value="<%= ccNew.getE01CCMTCI().trim()%>" > 
 			</td>
          </tr>           
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Numero de Estado de Cuenta:</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01CCMNST" size="7" maxlength="6" value="<%= ccNew.getE01CCMNST().trim()%>" onKeypress="enterInteger()"> 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Carpeta:</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCMFOL" size="7" maxlength="6" value="<%= ccNew.getE01CCMFOL().trim()%>" onKeypress="enterInteger()"> 
 			</td>
          </tr>             
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
