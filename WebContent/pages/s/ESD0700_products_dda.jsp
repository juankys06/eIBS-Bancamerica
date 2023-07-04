<html>
<head>
<title>Consulta de Productos de Cuentas de Detalle</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="prd" class="datapro.eibs.beans.ESD070001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
<P><BR>
</P>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
%>

<h3 align="center">Producto de Cuentas de Detalles<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_dda.jsp, ESD0700"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0700" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01APCACD" VALUE="<%= prd.getE01APCACD()%>">
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead"  align="center">
          <tr> 
            <td nowrap width="10%" align="right"> Banco: </td>
            <td nowrap width="12%" align="left"> 
              <input type="text"  name="E01APCBNK" size="3" maxlength="2" value="<%= prd.getE01APCBNK()%>" readonly>
            </td>
            <td nowrap width="6%" align="right">Producto: </td>
            <td nowrap width="14%" align="left"> 
              <input type="text"  name="E01APCCDE" size="6" maxlength="4" value="<%= prd.getE01APCCDE()%>" readonly>
            </td>
            <td nowrap width="8%" align="right"> Tipo de Producto : </td>
            <td nowrap width="50%"align="left"> 
              <input type="text"  name="E01APCTYP" size="6" maxlength="4" value="<%= prd.getE01APCTYP()%>" readonly>
              - 
              <input type="text"  name="E01DSCTYP" size="25" maxlength="25" value="<%= prd.getE01DSCTYP()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n General</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td > 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCDS1" size="40" maxlength="40" value="<%= prd.getE01APCDS1()%>">
            </td>
            <td > 
              <div align="right">Nombre de Mercadeo :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCDS2" size="30" maxlength="25" value="<%= prd.getE01APCDS2()%>">
            </td>
			</tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCCCY" size="3" maxlength="3" value="<%= prd.getE01APCCCY()%>">
              <a href="javascript:GetCurrency('E01APCCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
            <td > 
              <div align="right">Ofrecimiento por :</div>
            </td>
            <td ><SELECT name="E01APCFTT">
					<OPTION value="1"
						<%if (prd.getE01APCFTT().equals("1")) { out.print("selected"); }%>>Internet</OPTION>
					<OPTION value="I"
						<%if (prd.getE01APCFTT().equals("I")) { out.print("selected"); }%>>Internacional</OPTION>
					<OPTION value="L"
						<%if (prd.getE01APCFTT().equals("L")) { out.print("selected"); }%>>Local</OPTION>
					<OPTION value="3"
						<%if (prd.getE01APCFTT().equals("3")) { out.print("selected"); }%>>Plataforma</OPTION>
					<OPTION value="5"
						<%if (prd.getE01APCFTT().equals("5")) { out.print("selected"); }%>>Cualquier
					Medio</OPTION>
					<OPTION value="N"
						<%if (prd.getE01APCFTT().equals("N")) { out.print("selected"); }%>>No
					Ofrecer</OPTION>
				</SELECT>
            </td>
			</tr>
          <tr id="trdark"> 
            <td > 
              <div align="right"> Cuenta Contable:</div>
            </td>
            <td > 
              <input type="text"  name="E01APCGLN" size="18" maxlength="16" value="<%= prd.getE01APCGLN().trim()%>">
              <a href="javascript:GetLedger('E01APCGLN',document.forms[0].E01APCBNK.value,document.forms[0].E01APCCCY.value,document.forms[0].E01APCACD.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
            <td > 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCTAX" size="3" maxlength="2" value="<%= prd.getE01APCTAX()%>">
              <a href="javascript:GetCode('E01APCTAX','STATIC_ln_prod_tax_ret.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
            </td>
			</tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">Tabla de Documentos :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCFTF" size="4" maxlength="2" value="<%= prd.getE01APCFTF().trim()%>">
              <a href="javascript:GetDocInv('E01APCFTF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
            </td>
            <td > 
              <div align="right">Tipo de Residencia :</div>
            </td>
            <td > 
              <select name="E01APCRES">
                <option value="1" <%if (prd.getE01APCRES().equals("1")) { out.print("selected"); }%>>Residentes</option>
                <option value="2" <%if (prd.getE01APCRES().equals("2")) { out.print("selected"); }%>>No Residente</option>
                <option value="" <%if (prd.getE01APCRES().equals(""))   { out.print("selected"); }%>>No Aplica 
                </option>
              </select>
            </td>
			</tr>
          <tr id="trdark">
            <td > 
              <div align="right">Producto Familia :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCUC1" size="4" maxlength="2" value="<%= prd.getE01APCUC1().trim()%>">
            </td>
            <td> 
              <div align="right">Producto Mayoria Edad :</div>
            </td>
            <td>   
	            <input type="text"  name="E01APCUC3" size="5" maxlength="4" value="<%= prd.getE01APCUC3().trim()%>">
	            <input type="hidden" name="E01DSCTYP" size="50" maxlength="70" value="<%=  prd.getE01DSCTYP()%>"  >
	            <a href="javascript:GetProductT('E01APCUC3','E01DSCTYP','ATAKEN','<%= prd.getE01APCTYP()%>','<%= prd.getE01APCACD().trim()%>')">
	            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a>
            </td> 
			</tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Tipos de Tarjetas ATM :</div>
          	</td>
          	<td width="25%"> 
              <input type="text"  name="E01APCCC1" size="3" maxlength="1" value="<%= prd.getE01APCCC1().trim()%>">
              <a href="javascript:GetDescATMCard('E01APCCC1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
            </td>
          	<td>
          		<div align="right">Sobregiro Permitido :</div>
          	</td>
          	<td width="18%"> 
              <input type="radio" name="E01APCAMO" value="1"  <%if (prd.getE01APCAMO().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCAMO" value="N"  <%if (prd.getE01APCAMO().equals("N")) out.print("checked"); %>>
              No</td>
			</tr>
          <tr id="trdark">
			<td> 
              <div align="right">Tipo de Persona :</div>
            </td>
            <td > 
              <select name="E01APCFRA">
                <option value="1" <%if (prd.getE01APCFRA().equals("1")) { out.print("selected"); }%>>P.JURIDICA</option>
                <option value="2" <%if (prd.getE01APCFRA().equals("2")) { out.print("selected"); }%>>P.NATURAL</option>
                <option value="N" <%if (prd.getE01APCFRA().equals("N")) { out.print("selected"); }%>>NO APLICA</option>
              </select>
            </td>
            <td>
              <div align="right">Clasificaci&oacute;n de Cuenta :</div>
            </td>
            <td width="18%">
            	<input type="text"  name="E01APCTAR" size="3" maxlength="2" value="<%= prd.getE01APCTAR()%>">
             	<a href="javascript:GetRetCod('E01APCTAR','<%= prd.getE01APCTYP()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>  
            </td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Tipo Chequera Inicial :</div>
          	</td>
          	<td width="25%"> 
              <input type="text" name="E01APCCC3" size="3" maxlength="2" value="<%=prd.getE01APCCC3()%>">
               <a href="javascript:GetTypCHKBook('E01APCCC3','','','','<%= prd.getE01APCTYP()%>','<%= prd.getE01APCCCY()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
            </td>
          	<td></td>
          	<td width="18%"></td>
			</tr>
         <tr id="trdark">
          	<td>
          		<div align="right">Intermoneda :</div>
          	</td>
          	<td width="25%"> 
              <input type="text"  name="E01APCROY" size="4" maxlength="3" value="<%= prd.getE01APCROY().trim()%>">
              <a href="javascript:GetCurrency('E01APCROY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
          	<td>       	</td>
          	<td width="18%">           </td>
			</tr>
          <%  if (!prd.getE01APCACD().equals("01")){ %>
	          <tr id="trclear">
	            <td>
	            	<div align="right">Frecuencia Pagos Reajuste:</div>
	            </td>
	            <td>
	              <select name="E01APCFRN">
	             	  <option value="N" <%if (prd.getE01APCFRN().equals("N")) { out.print("selected"); }%>>No Aplica</option>
	              	  <option value="M" <%if (prd.getE01APCFRN().equals("M")) { out.print("selected"); }%>>Mensual</option>
	              	  <option value="Q" <%if (prd.getE01APCFRN().equals("Q")) { out.print("selected"); }%>>Trimestral</option>
	              	  <option value="S" <%if (prd.getE01APCFRN().equals("S")) { out.print("selected"); }%>>Semestral</option>
	              	  <option value="Y" <%if (prd.getE01APCFRN().equals("Y")) { out.print("selected"); }%>>Anual</option>
	              </select>
	            </td> 
	            <td>
	            	<div align="right">Frecuencia Pago de Intereses :</div>
	            </td>
	            <td>
	              <select name="E01APCFL4">
	              	  <option value="M" <%if (prd.getE01APCFL4().equals("M")) { out.print("selected"); }%>>Mensual</option>
	              	  <option value="D" <%if (prd.getE01APCFL4().equals("D")) { out.print("selected"); }%>>Diario</option>
	              	  <option value="Q" <%if (prd.getE01APCFL4().equals("Q")) { out.print("selected"); }%>>Trimestral</option>
	              	  <option value="S" <%if (prd.getE01APCFL4().equals("S")) { out.print("selected"); }%>>Semestral</option>
	             	  <option value="Y" <%if (prd.getE01APCFL4().equals("Y")) { out.print("selected"); }%>>Anual</option>
	              </select>
	            </td> 
	          </tr>
	          <tr id="trdark">
	          	<td>
	          		<div align="right">Control de Chequeras :</div>
	          	</td>
	          	<td >
	          		<input type="radio" name="E01APCCBC" value="Y"  <%if (prd.getE01APCCBC().equals("Y")) out.print("checked"); %>>
	              Si 
	              <input type="radio" name="E01APCCBC" value="N"  <%if (prd.getE01APCCBC().equals("N")) out.print("checked"); %>>
	              No
	            </td>
	            <td>        	</td>
   	            <td>        	</td>
	           </tr>
          <%  } else { %>
	          <tr id="trclear">
	          	<td>
	          		<div align="right">Control de Chequeras :</div>
	          	</td>
	          	<td >
	          		<input type="radio" name="E01APCCBC" value="Y"  <%if (prd.getE01APCCBC().equals("Y")) out.print("checked"); %>>
	              Si 
	              <input type="radio" name="E01APCCBC" value="N"  <%if (prd.getE01APCCBC().equals("N")) out.print("checked"); %>>
	              No
	            </td>
	            <td>        	</td>
   	            <td>        	</td>
	            
	           </tr>
          <%  } %>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n Sobre Proteccion Sobregiros</h4>
  
  <table class="tableinfo">
  	<tr>
  	  <td>
  		<table cellspacing="0" cellpadding="2" width="100%" border="0">
  		  <tr id="trdark">
  			<td>
  			  <div align="right"><b>Proteccion de Sobregiro :</B></div>
  		    </td>
  		    <td colspan="3">
  			  <input type="radio" name="E01APCREL" value="5"  <%if (prd.getE01APCREL().equals("5")) out.print("checked"); %>>
              <b>Si</b> 
              <input type="radio" name="E01APCREL" value="N"  <%if (prd.getE01APCREL().equals("N")) out.print("checked"); %>>
              <b>No</b>
            </td>
          </tr>
          <tr id="trclear">
          	<td>
				<div align="right">Producto Credito Relacion :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APAPRD" size="3" maxlength="4" value="<%= prd.getE01APAPRD() %>" readonly>
              <a href="javascript:GetProduct('E01APAPRD','10','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
          	</td>
          	<td>
          		<div align="right">Monto Maximo del Prestamo :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APAOAM" size="18" maxlength="16" value="<%= prd.getE01APAOAM() %>">
          	</td>
          </tr>
          <tr id="trdark">
          	<td>
          		<div align="right">Tipo de Amortizacion :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APAPCR" size="3" maxlength="1" value="<%= prd.getE01APAPCR() %>" readonly>
              <a href="javascript:GetCode('E01APAPCR','STATIC_typ_amortization.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
          	</td>
          	<td>
          		<div align="right">Monto a Amortizar :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APAPMT" size="18" maxlength="16" value="<%= prd.getE01APAPMT() %>">
          	</td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Moneda del Pago :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APARCY" size="3" maxlength="3" value="<%= prd.getE01APARCY() %>" readonly>
              	<a href="javascript:GetCurrency('E01APARCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
          	</td>
          	<td>
          		<div align="right">Tipo de Pago :</div>
          	</td>
          	<td>
          		<select name="E01APAPIF">
                	<option value="F" <%if (prd.getE01APAPIF().equals("F")) { out.print("selected"); }%>>Fijo</option>
                	<option value="%" <%if (prd.getE01APAPIF().equals("%")) { out.print("selected"); }%>> % del principal</option>
                </select>
          	</td>
          </tr>
          <tr id="trdark">
          	<td>
          		<div align="right">Incluye Interes en Pago :</div>
          	</td>
          	<td>
          		<input type="radio" name="E01APAIIP" value="Y"  <%if (prd.getE01APAIIP().equals("Y")) out.print("checked"); %>>
              	Si 
              	<input type="radio" name="E01APAIIP" value="N"  <%if (prd.getE01APAIIP().equals("N")) out.print("checked"); %>>
              	No
          	</td>
          	<td>
          		<div align="right">Transferir en Multiplos de :</div>
          	</td>
          	<td>
          		
              <input type="text"  name="E01APAMUL" size="18" maxlength="16" value="<%= prd.getE01APAMUL() %>">
          	</td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Balance Minimo en Cuenta :</div>
          	</td>
          	<td>
          		
              <input type="text"  name="E01APAMIN" size="18" maxlength="16" value="<%= prd.getE01APAMIN() %>">
          	</td>
          	<td>
          		<div align="right">Tipo de Cobertura :</div>
          	</td>
          	<td>
          		<select name="E01APAGLT">
                	<option value="1" <%if (prd.getE01APAGLT().equals("1")) { out.print("selected"); }%>>Linea</option>
                	<option value="2" <%if (prd.getE01APAGLT().equals("2")) { out.print("selected"); }%>>Cierre Dia</option>
                </select>
          	</td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Ciclo de Revision :</div>
          	</td>
          	<td colspan="3"> 
              <input type="text"  name="E01APARPD" size="4" maxlength="3" value="<%= prd.getE01APARPD()%>">
              <select name="E01APARFL">
                	<option value="D" <%if (prd.getE01APARFL().equals("D")) { out.print("selected"); }%>>Diario</option>
                	<option value="M" <%if (prd.getE01APARFL().equals("M")) { out.print("selected"); }%>>Mes</option>
                	<option value="Y" <%if (prd.getE01APARFL().equals("Y"))   { out.print("selected"); }%>>Anual</option>
              	</select>
          	</td>
          </tr>
        </table>
      </td>
    </tr>   
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="submit()" value="Enviar">
  </div>
</form>
</body>
</html>
