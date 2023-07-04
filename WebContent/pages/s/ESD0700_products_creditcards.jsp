<html>
<head>
<title>Consulta de Productos de Tarjetas de Credito</title>
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

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
%>

<h3 align="center">Producto de Tarjetas de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_creditcards.jsp, ESD0700"></h3>
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
    <tr> 
      <td> 
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
              <input type="text"  name="E01APCGLN" size="18" maxlength="16" value="<%= prd.getE01APCGLN().trim()%>">
              <a href="javascript:GetLedger('E01APCGLN',document.forms[0].E01APCBNK.value,document.forms[0].E01APCCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
            <td > 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCTAX" size="3" maxlength="2" value="<%= prd.getE01APCTAX()%>">
              <a href="javascript:GetCode('E01APCTAX','STATIC_ln_prod_tax_ret.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">Tabla de Documentos :</div>
            </td>
            <td > 
              	<input type="text"  name="E01APCFTF" size="4" maxlength="2" value="<%= prd.getE01APCFTF().trim()%>">
            	<a href="javascript:GetDocInv('E01APCFTF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td > 
              <div align="right">Tipos de Tarjetas:</div>
            </td>
            <td > 
              <input type="text"  name="E01APCCC1" size="4" maxlength="2" value="<%= prd.getE01APCCC1().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td > 
              <div align="right">Uso del Plastico:</div>
            </td>
            <td > 
              	<input type="text"  name="E01APCMCI" size="4" maxlength="2" value="<%= prd.getE01APCMCI().trim()%>">
            	<a href="javascript:GetCode('E01APCMCI','STATIC_cc_plastic_use.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
            </td>
            <td>
            	<div align="right">Forma de Uso:</div>
            </td>
           	<td width="18%"><INPUT type="text" name="E01APCMCP" size="4" maxlength="2" value="<%= prd.getE01APCMCP().trim()%>">
          	<a href="javascript:GetCode('E01APCMCP','STATIC_tc_form_use.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>          
          	</td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Forma de Entrega:</div>
          	</td>
          	<td width="25%"> 
              	<input type="text"  name="E01APCCRF" size="3" maxlength="1" value="<%= prd.getE01APCCRF().trim()%>">
            	<a href="javascript:GetCode('E01APCCRF','STATIC_tc_send_form.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>   
            </td>
            <td>
           		<div align="right">Limite Giros :</div>
            </td>
            <td>
            	<select name="E01APCICT">
               	 	<option value="0" <%if (prd.getE01APCICT().equals("0")) { out.print("selected"); }%>>No Aplica</option>
                	<option value="1" <%if (prd.getE01APCICT().equals("1")) { out.print("selected"); }%>>Limite Diario</option>
                	<option value="2" <%if (prd.getE01APCICT().equals("2")) { out.print("selected"); }%>>Limite Semanal</option>
              	</select>
            </td>    
          </tr>
          <tr id="trdark">
          	<td>
          		<div align="right">Empresa Distribuidora:</div>
          	</td>
          	<td>
          		<INPUT type="text" name="E01APCUC2" size="4" maxlength="2" value="<%= prd.getE01APCUC2().trim()%>">
          		<a href="javascript:GetCodeCNOFC('E01APCUC2','66')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>   
          	</td>
            <td>
           		<div align="right">Giros :</div>
            </td>
            <td>
            	<INPUT type="text" name="E01APCAMO" size="4" maxlength="2" value="<%= prd.getE01APCAMO().trim()%>">
            	<a href="javascript:GetCode('E01APCAMO','STATIC_tc_drafts.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>   
            </td>
          </tr>
          <tr id="trclear">
          	<td>
          		<div align="right">Transferencias :</div>
          	</td>
          	<td>
          		<INPUT type="text" name="E01APCITP" size="4" maxlength="2" value="<%= prd.getE01APCITP().trim()%>">
          		<a href="javascript:GetCode('E01APCITP','STATIC_tc_transfers.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>   
          	</td>
            <td>
           		<div align="right">Corporativo/Personal :</div>
            </td>
            <td>
              <select name="E01APCFL4">
                <option value="" <%if (prd.getE01APCFL4().equals(""))   { out.print("selected"); }%>>No Aplica</option>
                <option value="1" <%if (prd.getE01APCFL4().equals("1")) { out.print("selected"); }%>>Corporativo</option>
                <option value="2" <%if (prd.getE01APCFL4().equals("2")) { out.print("selected"); }%>>Personal</option>
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
</p>
 </form>
</body>
</html>
