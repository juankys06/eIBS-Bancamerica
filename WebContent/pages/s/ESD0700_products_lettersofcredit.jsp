<html>
<head>
<title>Consulta de Productos para Cartas de Cr&eacute;dito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="prd" class="datapro.eibs.beans.ESD070001Message"  scope="session" />

<jsp:useBean id= "lnCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

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

<h3 align="center">Producto para Cartas de Credito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_lettersofcredit.jsp, ESD0700"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0700" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01APCACD" VALUE="<%= prd.getE01APCACD()%>">
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead"  align="center">
          <tr id="trdark"> 
            <td nowrap width="10%" align="right"> Banco: </td>
            <td nowrap width="12%" align="left"> 
              <input type="text"  name="E01APCBNK" size="3" maxlength="2" value="<%= prd.getE01APCBNK()%>" readonly>
            </td>
            <td nowrap width="6%" align="right"> Producto: </td>
            <td nowrap width="14%" align="left"> 
              <input type="text"  name="E01APCCDE" size="6" maxlength="4" value="<%= prd.getE01APCCDE()%>" readonly>
            </td>
            <td nowrap width="8%" align="right"> Tipo de Producto : </td>
            <td nowrap width="50%"align="left"> 
              <input type="text"  name="E01APCTYP" size="6" maxlength="4" value="<%= prd.getE01APCTYP()%>" readonly>
              - 
              <input type="text"  name="E01DSCTYP" size="40" maxlength="35" value="<%= prd.getE01DSCTYP()%>" readonly>
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
            <td> 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td> 
              <input type="text"  name="E01APCDS1" size="62" maxlength="60" value="<%= prd.getE01APCDS1()%>">
            </td>
            <td> 
              <div align="right">Nombre de Mercadeo :</div>
            </td>
            <td> 
              <input type="text"  name="E01APCDS2" size="18" maxlength="15" value="<%= prd.getE01APCDS2()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td > 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td > 
              <input type="text"  name="E01APCCCY" size="3" maxlength="3" value="<%= prd.getE01APCCCY()%>">
              <a href="javascript:GetCurrency('E01APCCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
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
              <a href="javascript:GetLedger('E01APCGLN',document.forms[0].E01APCBNK.value,document.forms[0].E01APCCCY.value,document.forms[0].E01APCACD.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
            </td>
            <td>
          		<div align="right">Tabla Documentos :</div>
          	</td>
          	<td>
          		<input type="text"  name="E01APCFTF" size="4" maxlength="2" value="<%= prd.getE01APCFTF().trim()%>">                
          		<a href="javascript:GetDocInv('E01APCFTF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a> 
          	</td>
          </tr>                 
        </table>
      </td>
  	</tr>
  </table>
  <h4>Informaci&oacute;n del Producto</h4>

  <table class="tableinfo">
    <tr > 
      <td height="213"> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
          <TBODY>
          <TR id="trdark"> 
          	<TD align="right">
          		<DIV align="right">Numero de Tarifa:</DIV>
          	</TD>
          	<TD>  
          	<INPUT type="text" name="E01APCTLN" size="4" maxlength="2" value="<%= prd.getE01APCTLN().trim()%>"><A href="javascript:GetTariffLC('E01APCTLN',document.forms[0].E01APCTYP.value)"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A></TD>
          	<TD align="right">
          		<DIV align="right">Tipo de Carta de Credito :</DIV>
          	</TD>
          	<TD>
          	<INPUT type="text" name="E01APCAMO" size="4" maxlength="2" value="<%= prd.getE01APCAMO().trim()%>"><A href="javascript:GetCode('E01APCAMO','STATIC_typ_lc.jsp')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A></TD>
          </TR>
          <TR id="trclear"> 
				<TD height="43" align="right">Retencion Impuestos</TD>
				<TD height="43"><INPUT type="text" name="E01APCTAX" size="4" maxlength="2" value="<%= prd.getE01APCTAX().trim()%>"><A href="javascript:GetCode('E01APCTAX','STATIC_ln_prod_tax_ret.jsp')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A></TD>
				<TD height="43" align="right">Tipo de Inter&eacute;s</TD>
				<TD height="43"><INPUT type="text" name="E01APCRES" size="4" maxlength="2" value="<%= prd.getE01APCRES().trim()%>"><A href="javascript:GetCode('E01APCRES','STATIC_tds_typ_int.jsp')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A></TD>
			</TR>
          <TR id="trdark"> 
			<TD height="19" align="right">
          		<DIV align="right">Base C&aacute;lculo Anual</DIV>
          	</TD>
          	<TD height="19">
          		<INPUT type="text" name="E01APCROY" size="4" maxlength="3" value="<%= prd.getE01APCROY().trim()%>">                        		
          	</TD>
          	<TD height="19" align="right">
          		<DIV align="right">Provisi&oacute;n de Intereses</DIV>
          	</TD>
          	<TD height="19">
          		<INPUT type="text" name="E01APCFRN" size="4" maxlength="2" value="<%= prd.getE01APCFRN().trim()%>">                        		
          		<A href="javascript:GetCode('E01APCFRN','STATIC_cc_prov.jsp')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
          	</TD>
          </TR>
          <TR id="trclear"> 
          	<TD align="right">
          		<DIV align="right">Confirmada</DIV>
          	</TD>
          	<TD>
          		<INPUT type="radio" name="E01APCICT" value="Y" <%if (!prd.getE01APCICT().equals("N")) out.print("checked"); %>>
              Si 
              <INPUT type="radio" name="E01APCICT" value="N" <%if (prd.getE01APCICT().equals("N")) out.print("checked"); %>>
              No
            </TD>
          	<TD align="right">
          		<DIV align="right">Comisiones por Cuenta de</DIV>
          	</TD>
          	<TD> 
              <SELECT name="E01APCITP">
                <OPTION value="B" <%if (prd.getE01APCITP().equals("B")) { out.print("selected"); }%>>Beneficiario</OPTION>
                <OPTION value="A" <%if (prd.getE01APCITP().equals("A")) { out.print("selected"); }%>>Aplicante</OPTION>
              </SELECT>
            </TD>
          </TR>
          <TR id="trdark"> 
          	<TD height="34" align="right">
          		<DIV align="right">Tenor</DIV>
          	</TD>
          	<TD height="34">
          		<INPUT type="text" name="E01APCCDT" size="4" maxlength="4" value="<%= prd.getE01APCCDT().trim()%>">
          		<A href="javascript:GetCode('E01APCCDT','STATIC_cc_tenor.jsp')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>		
          	</TD>
          	<TD height="34" align="right">
          		<DIV align="right">ALADI</DIV></TD>
          	<TD height="34">
          		<INPUT type="radio" name="E01APCFL5" value="Y" <%if (prd.getE01APCFL5().equals("Y")) out.print("checked"); %>>
              Si 
                <INPUT type="radio" name="E01APCFL5" value="N" <%if (!prd.getE01APCFL5().equals("Y")) out.print("checked"); %>>
              No</TD>
          </TR>
			<TR id="trclear">
				<TD height="32" align="right">
				<DIV align="right">
				<DIV align="right">Embarques Parciales</DIV>
				</DIV>
				</TD>
				<TD height="32"><A href="javascript:GetCode('E01APCCDT','STATIC_cc_tenor.jsp')"></A>
				<INPUT type="radio" name="E01APCMCI" value="Y" <%if (!prd.getE01APCMCI().equals("N")) out.print("checked"); %>>
              Si 
                <INPUT type="radio" name="E01APCMCI" value="N" <%if (prd.getE01APCMCI().equals("N")) out.print("checked"); %>>
              No
            </TD>
				<TD height="32" align="right">
				<DIV align="right">
				<DIV align="right">Transbordo</DIV>
				</DIV>
				</TD>
				<TD height="32">
				<INPUT type="radio" name="E01APCMCP" value="Y" <%if (prd.getE01APCMCP().equals("Y")) out.print("checked"); %>>
              Si 
                <INPUT type="radio" name="E01APCMCP" value="N" <%if (!prd.getE01APCMCP().equals("Y")) out.print("checked"); %>>
              No
				</TD>
			</TR>
          <TR id="trdark"> 
				<TD height="19" align="right">
          		<DIV align="right">Producto Familia</DIV>
          	</TD><TD height="19">
          		<INPUT type="text" name="E01APCUC1" size="6" maxlength="4" value="<%= prd.getE01APCUC1().trim()%>">                        		
          	<A href="javascript:GetCodeDescCNOFC('E01APCUC1','E01APCUC1','61')"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
						align="absbottom" border="0"></A>
			</TD><TD height="19" align="right">
          		<DIV align="right">Tipo Producto Super</DIV>
          	</TD><TD height="19">
          		<INPUT type="text" name="E01APCFL3" size="4" maxlength="1" value="<%= prd.getE01APCFL3().trim()%>">                        		
          	<A
						href="javascript:GetCode('E01APCFL3','STATIC_cc_super.jsp')"><IMG
						src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
						align="absbottom" border="0"></A></TD>
				
				
				
			</TR></TBODY></TABLE>
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
