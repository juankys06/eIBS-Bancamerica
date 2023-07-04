<html>
<head>
<title>Productos de Prestamos Nuevo/Mantenimiento</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


</head>

<jsp:useBean id="prd" class="datapro.eibs.beans.ESD070001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body onload=javascript:init()>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
%>
<SCRIPT Language="Javascript">
       
     builtNewMenu(prd_loan_opt);
     initMenu();
     
function init() {
      if ("<%= prd.getE01APCSF1().trim() %>" == 'Y') {
			Options('S') ;
      }
}
      
function Options(Type) {
	if (Type == 'S') {	
	document.forms[0].E01APCSF1.value='Y'	
	divAddOpt.style.display = "";
	};
	else {
	document.forms[0].E01APCSF1.value='N'		
	divAddOpt.style.display = "none";
	};
}		 
       
</SCRIPT>

<h3 align="center"> Producto de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_loans.jsp, ESD0700"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0700" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01APCACD" VALUE="<%= prd.getE01APCACD()%>">
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead">
          <tr id=trdark> 
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
              <input type="text"  name="E01APCDS2" size="17" maxlength="15" value="<%= prd.getE01APCDS2()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCCCY" size="3" maxlength="3" value="<%= prd.getE01APCCCY()%>">
              <a href="javascript:GetCurrency('E01APCCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
              <input type="text"  name="E01APCGLN" size="18" maxlength="16" value="<%= prd.getE01APCGLN() %>">
              <a href="javascript:GetLedger('E01APCGLN',document.forms[0].E01APCBNK.value,document.forms[0].E01APCCCY.value,document.forms[0].E01APCACD.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
           </td>
            <td > 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCTAX" size="3" maxlength="2" value="<%= prd.getE01APCTAX()%>" readonly>
              <a href="javascript:GetCode('E01APCTAX','STATIC_ln_prod_tax_ret.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">Tabla de Documentos :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCFTF" size="4" maxlength="2" value="<%= prd.getE01APCFTF() %>" onKeypress="enterInteger()">
              <a href="javascript:GetDocInv('E01APCFTF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td > 
              <div align="right">Tipo de Residencia :</div>
            </td>
            <td > 
              <select name="E01APCRES">
                <option value="1" <%if (prd.getE01APCRES().equals("1")) { out.print("selected"); }%>>Residente</option>
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
              <input type="text"  name="E01APCUC1" size="4" maxlength="2" value="<%= prd.getE01APCUC1() %>">
            </td>
            <td > 
              <div align="right">Tabla de Tramos Preferenciales :</div>
            </td>
            <td > 
              <input type="text" name="E01APCCC4" size="4" maxlength="2" value="<%= prd.getE01APCCC4() %>">
              <a href="javascript:GetLimit('E01APCCC4',document.forms[0].E01APCBNK.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Informaci&oacute;n Adicional</h4>

  <table class="tableinfo" width="779" >
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">Clase de Prest&aacute;mo : </div>
            </td>
            <td width="25%"> 
              <input type="text"  name="E01APCFRN" size="3" maxlength="1" value="<%= prd.getE01APCFRN() %>" readonly>
              <a href="javascript:GetCode('E01APCFRN','STATIC_ln_cred_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td width="26%"> 
              <div align="right">Entregas Graduales : </div>
            </td>
            <td width="18%"> 
              <input type="radio" name="E01APCCHR" value="Y"  <%if (!prd.getE01APCCHR().equals("N")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCCHR" value="N"  <%if (prd.getE01APCCHR().equals("N")) out.print("checked"); %>>
              No
			</td>
          </tr>
          <tr id="trdark"> 
            <td width="26%"> 
              <div align="right">Refinanciamiento :</div>
            </td>
            <td width="18%">
              <input type="text"  name="E01APCSF3" size="3" maxlength="1" value="<%= prd.getE01APCSF3() %>" readonly>
              <a href="javascript:GetCode('E01APCSF3','STATIC_ln_ref.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td width="31%"> 
              <div align="right">Intereses en la Cuota : </div>
            </td>
            <td width="18%"> 
              <input type="radio" name="E01APCREL" value="Y"  <%if (!prd.getE01APCREL().equals("N")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCREL" value="N"  <%if (prd.getE01APCREL().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">C&aacute;lculo de Interes Normal :</div>
            </td>
            <td width="25%"> 
              <input type="text"  name="E01APCMCI" size="3" maxlength="1" value="<%= prd.getE01APCMCI() %>" readonly>
              <a href="javascript:GetCode('E01APCMCI','STATIC_ln_prov.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td width="26%"> 
              <div align="right"> Deducciones :</div>
            </td>
            <td width="18%"> 
              <input type="radio" name="E01APCSF1" value="Y" onClick="javascript:Options('S')" <%if (prd.getE01APCSF1().equals("Y")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCSF1" value="N" onClick="javascript:Options('H')" <%if (prd.getE01APCSF1().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td width="31%"> 
              <div align="right">C&aacute;lculo de Interes Mora :</div>
            </td>
            <td width="25%"> 
              <input type="text"  name="E01APCMCP" size="3" maxlength="1" value="<%= prd.getE01APCMCP() %>" readonly>
              <a href="javascript:GetCode('E01APCMCP','STATIC_ln_mor.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
            <td width="26%"> 
              <div align="right">Garant&iacute;as :</div>
            </td>
            <td width="18%">
              <input type="radio" name="E01APCSF2" value="Y"  <%if (prd.getE01APCSF2().equals("Y")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCSF2" value="N"  <%if (prd.getE01APCSF2().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">Ciclo de Pago de Intereses :</div>
            </td>
            <td width="25%"> 
              <input type="text"  name="E01APCIPD" size="3" maxlength="3" value="<%= prd.getE01APCIPD() %>" >
              <a href="javascript:GetCode('E01APCIPD','STATIC_ln_pay_int.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
            <td width="26%"> 
              <div align="right">Control de Pagos :</div>
            </td>
            <td width="18%">
              <input type="radio" name="E01APCSF4" value="Y"  <%if (prd.getE01APCSF4().equals("Y")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCSF4" value="N"  <%if (prd.getE01APCSF4().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td width="31%"> 
              <div align="right">Ciclo de Pago Principal :</div>
            </td>
            <td width="25%"> 
              <input type="text"  name="E01APCPPD" size="3" maxlength="3" value="<%= prd.getE01APCPPD() %>" >
              <a href="javascript:GetCode('E01APCPPD','STATIC_ln_pay_int.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a></td>
            <td width="26%"> 
              <div align="right">Propuesta Cr&eacute;dito :</div>
            </td>
            <td width="18%">
              <input type="radio" name="E01APCCRF" value="1"  <%if (prd.getE01APCCRF().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCCRF" value="N"  <%if (prd.getE01APCCRF().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">Tabla de Tarifas :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01APCTLN" size="3" maxlength="2" value="<%= prd.getE01APCTLN()%>">
              <a href="javascript:GetLoanTable('E01APCTLN',document.forms[0].E01APCTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>
            <td width="26%"> 
              <div align="right">Tabla Previsi&oacute;n :</div>
            </td>
            <td width="18%">
              <input type="text" name="E01APCRLT" size="3" maxlength="2" value="<%= prd.getE01APCRLT() %>">
              <a href="javascript:GetPrevTable('E01APCRLT')">
       <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="31%"> 
              <div align="right">Estructura de Tasas :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01APCCDT" size="3" maxlength="1" value="<%= prd.getE01APCCDT() %>">
              <a href="javascript:GetRateTable('E01APCCDT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></td>
            <td width="26%"> 
              <div align="right">Libera Garant&iacute;a :</div>
            </td>
            <td width="18%">
              <input type="radio" name="E01APCRCL" value="Y"  <%if (prd.getE01APCRCL().equals("Y")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01APCRCL" value="N"  <%if (prd.getE01APCRCL().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">Tabla Flotante Lider :</div>
            </td>
            <td width="25%"> 
              <input type="text" name="E01APCFRT" size="3" maxlength="2" value="<%= prd.getE01APCFRT() %>">
               <a href="javascript:GetFloatingRate('E01APCFRT',document.forms[0].E01APCTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a>
            </td>
            <td width="26%"> 
              <div align="right">Tipo Monto de Garant&iacute;a :</div>
            </td>
            <td width="18%">
			 <select name="E01APCAMC">
                <option value="1" <%if (prd.getE01APCAMC().equals("1")) { out.print("selected"); }%>>Solo Capital</option>
                <option value="2" <%if (prd.getE01APCAMC().equals("2")) { out.print("selected"); }%>>Capital e Intereses</option>
                <option value="3" <%if (prd.getE01APCAMC().equals("3")) { out.print("selected"); }%>>Capital, Intereses y Mora</option>
              </select>
			</td>
          </tr>
          <tr id="trdark"> 
            <td width="31%"> 
              <div align="right">Tipo de Tasa :</div>
            </td>
            <td width="25%"> 
              <select name="E01APCFTY">
					<OPTION value=""></OPTION>
					<option value="FP" <%if (prd.getE01APCFTY().equals("FP")) { out.print("selected"); }%>>Flotante Primaria</option>
					<option value="FS" <%if (prd.getE01APCFTY().equals("FS")) { out.print("selected"); }%>>Flotante Secundaria</option>

				</select>
            </td>
            <td width="26%"> 
              <div align="right">Ciclo Cambio de Tasa :</div>
            </td>
            <td width="18%">
              <input type="text" name="E01APCRRD" size="3" maxlength="3" value="<%= prd.getE01APCRRD() %>">
            </td>
          </tr>

          <tr id="trclear"> 
            <td width="31%"> 
              <div align="right">Hipoteca Préstamo Preferencial :</div>
            </td>
            <td width="25%"> 
              <select name="E01APARFL">
					<OPTION value=""></OPTION>
					<option value="1" <%if (prd.getE01APARFL().equals("1")) { out.print("selected"); }%>>Vivienda Nueva</option>
					<option value="2" <%if (prd.getE01APARFL().equals("2")) { out.print("selected"); }%>>Forestal</option>
					<option value="3" <%if (prd.getE01APARFL().equals("3")) { out.print("selected"); }%>>Restauración</option>
				</select>
            </td>
            
            <td nowrap width=10% align="center"> Codigo Actividad : </td>	        
	        <td nowrap width="70%"> 
              <input type="text" name="E01APACDE" size="4" maxlength="4" value="<%= prd.getE01APACDE().trim()%>">
              <input type="text" name="D01APANME" size="40" maxlength="35" value="<%= prd.getD01APANME().trim()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E01APACDE','D01APANME','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>
            </td>
                           	
          </tr>

          <tr id="trdark"> 
            <td width="31%"> 
              <div align="right">Frecuencia de visitas por Cobranza:</div>
            </td>
            <td width="25%"> 
               <select name="E01APCFL3">
                <option value="N" <% if (!(prd.getE01APCFL3().equals("D") ||prd.getE01APCFL3().equals("W")||prd.getE01APCFL3().equals("M")||prd.getE01APCFL3().equals("Q")||prd.getE01APCFL3().equals("S")||prd.getE01APCFL3().equals("Y"))) out.print("selected"); %>> No Aplica</option>
                <option value="D" <% if(prd.getE01APCFL3().equals("D")) out.print("selected");%>>Diario</option>
                <option value="W" <% if(prd.getE01APCFL3().equals("W")) out.print("selected");%>>Semanal</option>
                <option value="M" <% if(prd.getE01APCFL3().equals("M")) out.print("selected");%>>Mensual</option>
                <option value="Q" <% if(prd.getE01APCFL3().equals("Q")) out.print("selected");%>>Trimestral</option>
                <option value="S" <% if(prd.getE01APCFL3().equals("S")) out.print("selected");%>>Semestral</option>
                <option value="Y" <% if(prd.getE01APCFL3().equals("Y")) out.print("selected");%>>Anual</option>
              </select>
            
            
             </td>
            
            <td nowrap width=10% align="center"> </td>	        
	        <td nowrap width="70%"> 
         
            </td>
                           	
          </tr>



        </table>
      </td>
    </tr>
  </table>
  
  <div id="divAddOpt" style="display:none">  
  <h4>Deducciones</h4>
  <table class="tableinfo">
  	<tr id="trdark">
  		<td align="center">Código</td>
  		<td align="center">Tipo de Cobro</td>
  		<td align="center">Forma de Pago</td>
  	</tr>
  
  	<tr id="trclear">
  		<td align="center"> <input type="text" name="E01APDDED1" size="4" maxlength="3" value="<%= prd.getE01APDDED1() %>"> 
  		<A
			href="javascript:GetTypeBroker('E01APDDED1','')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="absbottom" border="0"></A></td>
  		<td align="center"> 
  		            <select name="E01APDFL21">
						<OPTION value=""></OPTION>
						<option value="O" <%if (prd.getE01APDFL21().equals("O")) { out.print("selected"); }%>>Apertura</option>
						<option value="L" <%if (prd.getE01APDFL21().equals("L")) { out.print("selected"); }%>>Ciclo de Pago</option>
					</select>
  		
  		</td>
  		<td align="center"> <SELECT name="E01APDFL31">
			<OPTION value=""></OPTION>
			<OPTION value="1" <%if (prd.getE01APDFL31().equals("1")) { out.print("selected"); }%>>Financiado</OPTION>
			<OPTION value="2" <%if (prd.getE01APDFL31().equals("2")) { out.print("selected"); }%>>Pre-Pagado</OPTION>
		</SELECT>
  		</td>
  	</tr>
  	<tr id="trdark">
  		<td align="center"> <input type="text" name="E01APDDED2" size="4" maxlength="3" value="<%= prd.getE01APDDED2() %>"> 
  		<A
			href="javascript:GetTypeBroker('E01APDDED2','')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="absbottom" border="0"></A></td>
  		<td align="center"> 
  		            <select name="E01APDFL22">
						<OPTION value=""></OPTION>
						<option value="O" <%if (prd.getE01APDFL22().equals("O")) { out.print("selected"); }%>>Apertura</option>
						<option value="L" <%if (prd.getE01APDFL22().equals("L")) { out.print("selected"); }%>>Ciclo de Pago</option>
					</select>
  		
  		</td>
  		<td align="center"><SELECT name="E01APDFL32">
			<OPTION value=""></OPTION>
			<OPTION value="1" <%if (prd.getE01APDFL32().equals("1")) { out.print("selected"); }%>>Financiado</OPTION>
			<OPTION value="2" <%if (prd.getE01APDFL32().equals("2")) { out.print("selected"); }%>>Pre-Pagado</OPTION>
		</SELECT>
  		</td>
  	</tr>
  	<tr id="trclear">
  		<td align="center"><input type="text" name="E01APDDED3" size="4" maxlength="3" value="<%= prd.getE01APDDED3() %>"> 
  		<A
			href="javascript:GetTypeBroker('E01APDDED3','')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="absbottom" border="0"></A></td>
  		<td align="center"> 
  		            <select name="E01APDFL23">
						<OPTION value=""></OPTION>
						<option value="O" <%if (prd.getE01APDFL23().equals("O")) { out.print("selected"); }%>>Apertura</option>
						<option value="L" <%if (prd.getE01APDFL23().equals("L")) { out.print("selected"); }%>>Ciclo de Pago</option>
					</select>
  		
  		</td>
  		<td align="center"> <SELECT name="E01APDFL33">
			<OPTION value=""></OPTION>
			<OPTION value="1" <%if (prd.getE01APDFL33().equals("1")) { out.print("selected"); }%>>Financiado</OPTION>
			<OPTION value="2" <%if (prd.getE01APDFL33().equals("2")) { out.print("selected"); }%>>Pre-Pagado</OPTION>
		</SELECT>
  		</td>
  	</tr>
  	<tr id="trdark">
  		<td align="center"><input type="text" name="E01APDDED4" size="4" maxlength="3" value="<%= prd.getE01APDDED4() %>"> 
  		<A
			href="javascript:GetTypeBroker('E01APDDED4','')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="absbottom" border="0"></A></td>
  		<td align="center"> 
  		            <select name="E01APDFL24">
						<OPTION value=""></OPTION>
						<option value="O" <%if (prd.getE01APDFL24().equals("O")) { out.print("selected"); }%>>Apertura</option>
						<option value="L" <%if (prd.getE01APDFL24().equals("L")) { out.print("selected"); }%>>Ciclo de Pago</option>
					</select>
  		
  		</td>
  		<td align="center"> <SELECT name="E01APDFL34">
			<OPTION value=""></OPTION>
			<OPTION value="1" <%if (prd.getE01APDFL34().equals("1")) { out.print("selected"); }%>>Financiado</OPTION>
			<OPTION value="2" <%if (prd.getE01APDFL34().equals("2")) { out.print("selected"); }%>>Pre-Pagado</OPTION>
		</SELECT>
  		</td>
  	</tr>
  	<tr id="trclear">
  		<td align="center"><input type="text" name="E01APDDED5" size="4" maxlength="3" value="<%= prd.getE01APDDED5() %>"> 
  		<A
			href="javascript:GetTypeBroker('E01APDDED5','')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
			align="absbottom" border="0"></A></td>
  		<td align="center"> 
  		            <select name="E01APDFL25">
						<OPTION value=""></OPTION>
						<option value="O" <%if (prd.getE01APDFL25().equals("O")) { out.print("selected"); }%>>Apertura</option>
						<option value="L" <%if (prd.getE01APDFL25().equals("L")) { out.print("selected"); }%>>Ciclo de Pago</option>
					</select>
  		
  		</td>
  		<td align="center"> <SELECT name="E01APDFL35">
			<OPTION value=""></OPTION>
			<OPTION value="1" <%if (prd.getE01APDFL35().equals("1")) { out.print("selected"); }%>>Financiado</OPTION>
			<OPTION value="2" <%if (prd.getE01APDFL35().equals("2")) { out.print("selected"); }%>>Pre-Pagado</OPTION>
		</SELECT>
  		</td>
  	</tr>
  	
  </table>
  </div>
  
  <h4>Direcciones de Acceso</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">Audio : </div>
            </td>
            <td width="80%"> 
              <input type="text"  name="E01APEAUD" size="82" maxlength="80" value="<%= prd.getE01APEAUD() %>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="20%"> 
              <div align="right">Video : </div>
            </td>
            <td width="80%"> 
              <input type="text"  name="E01APEVID" size="82" maxlength="80" value="<%= prd.getE01APEVID() %>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="80%"> 
              <input type="text"  name="E01APEHTM" size="82" maxlength="80" value="<%= prd.getE01APEHTM() %>">
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
</p>
 </form>
</body>
</html>
