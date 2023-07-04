<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Apertura de Cobranza Documentaria</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="msg01"  class="datapro.eibs.beans.EDC014001Message" scope="session" />
<jsp:useBean id="msg02"  class="datapro.eibs.beans.EDC014002Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<SCRIPT Language="Javascript">

  var dc0140_trn_inquiry =  "<A HREF=<%=request.getContextPath()%>/pages/s/EDC0140_coll_payment_inquiry.jsp>Información Básica</A><BR>"+
					        "<A HREF=<%=request.getContextPath()%>/pages/s/EDC0140_coll_payment_conf.jsp>Aplicaciones Contables</A><BR>"+
							"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
							"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
							"<A HREF= \"javascript:goOpenerAction('D')\" >Eliminar</A><BR>"+
							"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
							"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
						  
  builtNewMenu(dc0140_trn_inquiry);	
  initMenu();					  

  function helpAcc(tipo,fldst,fld1,fld2){
    if(tipo=='A'){
        GetAccByClient(fldst,'','RT','','E01DCMCL1')
    }
    if(tipo=='G'){
       if(fld2==''){
          alert("Seleccione Moneda de Cuenta Contable");
          return;
       } 
        GetLedger(fldst,fld1,fld2,'')
    }    
  }
</SCRIPT>
<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

</head>
<body>
<h3 align="center">Liquidacion  de Cobranza ( Consulta )<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="coll_payment_inquiry.jsp, EDC0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0140">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="003"> 
<table class="tableinfo">
	<tr>
		<td nowrap>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
			<TBODY><TR id="trdark">
				<TD nowrap width="16%">
					<DIV align="right"><B>Banco :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01DCMBNK" readonly size="4" maxlength="2" value="<%=msg01.getE01DCMBNK().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Número de Cobranza   :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B> 
					<INPUT type="text" name="E01DCMACC" size="13" maxlength="12" value="<%=msg01.getE01DCMACC().trim()%>" readonly>				
					</B></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
					<DIV align="right"><B>Cliente :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01CUSNA1" size="37" maxlength="35" value="<%=msg01.getE01CUSNA1().trim()%>" readonly> 	</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Producto :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMPRO" size="4" maxlength="4" value="<%=msg01.getE01DCMPRO().trim()%>" readonly>
					</B></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="16%">
					<DIV align="right"><B>Tipo de Cobranza :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMTYP" size="20" maxlength="16" value="<% if (msg01.getE01DCMTYP().equals("I")) { out.println("IMPORTACION"); } else if (msg01.getE01DCMTYP().equals("O")) { out.println("EXPORTACION"); } %>" readonly>
					</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Descripcion de Producto :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DSCPRO" size="30" maxlength="30" value="<%=msg01.getE01DSCPRO().trim()%>" readonly>
					</B></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
					<DIV align="right"><B>Moneda :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01CUSNA1" size="5" maxlength="5" value="<%=msg01.getE01DCMCCY().trim()%>" readonly> 	</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B></B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"> 
						</DIV>
				</TD>
			</TR>
			
			</TBODY></TABLE>
		</td>
	</tr>
</table>
<h4>Banco Presentador</h4>
<table class="tableinfo">
	<tr>
		<TD align="right">Tipo de Operacion:</TD>
		<TD align="left">				    
							<select name="E01TIPOPE" disabled="disabled">
								<option value=" " selected ></option>
								<option value="1" <%= msg01.getE01TIPOPE().equals("1")?"selected":"" %>>Pago Total</option>
								<option value="2" <%= msg01.getE01TIPOPE().equals("2")?"selected":"" %>>Pago Parcial</option>
								<option value="3" <%= msg01.getE01TIPOPE().equals("3")?"selected":"" %>>Cobro de Comisiones</option>
								<option value="4" <%= msg01.getE01TIPOPE().equals("4")?"selected":"" %>>Cancelacion</option>								
							</select>
							<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0">
		</td>
		<td>
				<div align="right">Monto a Pagar :</div>
		</td>
		<td>
			<div align="left">
				<input type="text" name="E01MNTPAG" size="28" maxlength="35"  readonly="readonly"	value="<%=msg01.getE01MNTPAG()%>">
			</div>
		</td>
		<td>
			<div align="right">Condicion de la Cancelacion :</div>
		</td>					
		<TD align="left"><div align="left">
			  		 <SELECT name="E01CONDCA" disabled="disabled">
					   <OPTION value=" " selected></OPTION>
					   <OPTION value="F" <%= msg01.getE01CONDCA().equals("F")?"selected":"" %>>Libre de Pago</OPTION>
					   <OPTION value="R" <%= msg01.getE01CONDCA().equals("R")?"selected":"" %>>Devuelta Impaga</OPTION>
				     </SELECT>
				   </div>
		</td>
	</tr>
</table>
<BR>
<H4>Liquidar con Remitente</H4>
<TABLE class="tableinfo">
  <TR>
    <TD WIDTH="15%"><DIV align="right">Via de Reembolso:</DIV></TD>
    <TD WIDTH="25%">
        
        <SELECT name="E01REMVIA" disabled="disabled">
			<OPTION value=" " selected></OPTION>
			<OPTION value="1" <%= msg01.getE01REMVIA().equals("1")?"selected":"" %>>Cheque de Oficial</OPTION>
			<OPTION value="2" <%= msg01.getE01REMVIA().equals("2")?"selected":"" %>>Cuenta Corriente</OPTION>
			<OPTION value="3" <%= msg01.getE01REMVIA().equals("3")?"selected":"" %>>Cuenta Contable</OPTION>
			<OPTION value="4" <%= msg01.getE01REMVIA().equals("4")?"selected":"" %>>FED ( USA )</OPTION>
			<OPTION value="5" <%= msg01.getE01REMVIA().equals("5")?"selected":"" %>>SWIFT</OPTION>
			<OPTION value="6" <%= msg01.getE01REMVIA().equals("6")?"selected":"" %>>Telex</OPTION>
		</SELECT><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0">    
    </TD>
    <TD WIDTH="15%"><DIV align="right">Moneda de Reembolso : </DIV></TD>
    <TD WIDTH="25%"><DIV align="left"><INPUT type="text" name="E01REMCCY" size="4" maxlength="3" value="<%=msg01.getE01REMCCY()%>" readonly="readonly"></DIV></TD>
    <TD WIDTH="15%"><DIV align="right">	Cuenta de Reembolso:</DIV></TD>
    <TD WIDTH="25%"><DIV align="left"><INPUT type="text" name="E01REMACC" size="17" maxlength="16" value="<%=msg01.getE01REMACC()%>" readonly="readonly"></DIV></TD>
    <TD ><BR></TD>
  </TR>
</TABLE>
<H4>Liquidar con Pagador</H4>
<TABLE class="tableinfo">
  <TR>
    <TD WIDTH="15%"><DIV align="right">Via de Pago:</DIV></TD>
    <TD WIDTH="15%"><DIV align="left">
       <INPUT type="hidden" name="E01PAGVIA" size="30" maxlength="30" value="<%= msg01.getE01PAGVIA() %>"> 
       <SELECT name="E01PAGVIAXX" disabled="disabled" >
			<OPTION value=" " selected></OPTION>
			<OPTION value="2" <%= msg01.getE01PAGVIA().equals("2")?"selected":"" %>>Cuenta Corriente</OPTION>
			<OPTION value="3" <%= msg01.getE01PAGVIA().equals("3")?"selected":"" %>>Cuenta Contable</OPTION>
	  </SELECT> <IMG	src="<%=request.getContextPath()%>/images/Check.gif"	alt="mandatory field" border="0"></DIV>
	</TD>
    <TD WIDTH="15%"><DIV align="right">Moneda de Pago :</DIV></TD>
    <TD>
		<DIV align="left"><INPUT type="text" name="E01PAGCCY" size="4" maxlength="3" value="<%=msg01.getE01PAGCCY()%>" readonly="readonly"></DIV>
	</TD>
    <TD WIDTH="15%"><DIV align="right">No. Cuenta Pago Principal :</DIV></TD>
	<TD>
	   <DIV align="left"><INPUT type="text" name="E01PAGACCP"  readonly="readonly"	size="18" maxlength="16" value="<%=msg01.getE01PAGACCP()%>"> </DIV>
	</TD>	
	<TD WIDTH="15%"><DIV align="right">No. Cuenta Pago Comision/Impuestos :</DIV></TD>
	<TD WIDTH="15%">
	   <DIV align="left"><INPUT type="text" name="E01PAGACCC"  readonly="readonly" size="18" maxlength="16" value="<%=msg01.getE01PAGACCC()%>"> </DIV>
	</TD>		
  </TR>
</TABLE>

<BR>  

<H4>Tipos de Cambio</H4>
<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
			<TD>Moneda</TD>
			<TD>T/C Compra</TD>
			<TD>T/C Venta</TD>
			<TD>Moneda</TD>
			<TD>T/C Compra</TD>
			<TD>T/C Venta</TD>
		</TR>
		<TR>
    <TD><INPUT type="text" name="E01OCCY01" value="<%=msg01.getE01OCCY01()%>" size="9" maxlength="3" readonly></TD>
    <TD><INPUT type="text" name="E01OXRP01" value="<%=msg01.getE01OXRP01() %>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OXRS01" value="<%=msg01.getE01OXRS01()%>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OCCY02" value="<%=msg01.getE01OCCY02()%>" size="9" maxlength="3" readonly></TD>
    <TD><INPUT type="text" name="E01OXRP02" value="<%=msg01.getE01OXRP02()%>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OXRS02" value="<%=msg01.getE01OXRS02()%>" size="20" maxlength="11" readonly></TD>
		</TR>
		<TR>
    <TD><INPUT type="text" name="E01OCCY03" value="<%=msg01.getE01OCCY03()%>" size="9" maxlength="3" readonly></TD>
    <TD><INPUT type="text" name="E01OXRP03" value="<%=msg01.getE01OXRP03()%>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OXRS03" value="<%=msg01.getE01OXRS03()%>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OCCY04" value="<%=msg01.getE01OCCY04()%>" size="9" maxlength="3" readonly></TD>
    <TD><INPUT type="text" name="E01OXRP04" value="<%=msg01.getE01OXRP04()%>" size="20" maxlength="11" readonly></TD>
    <TD><INPUT type="text" name="E01OXRS04" value="<%=msg01.getE01OXRS04()%>" size="20" maxlength="11" readonly></TD>
		</TR>
</TABLE>

<H4>Retenciones</H4>
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trclear">
						<TD>
						<DIV align="right">Impuestos :</DIV>
						</TD>
						<TD>
						<DIV align="left"><INPUT type="text" name="E01RETIMP" size="45"
							maxlength="35" value="<%=msg01.getE01RETIMP()%>" readonly></DIV>
						</TD>
					</TR>
					<TR id="trdark">
						<TD>
						<DIV align="right">Otros :</DIV>
						</TD>
						<TD>
						<DIV align="left"><INPUT type="text" name="E01RETOTR" size="45"
							maxlength="35" value="<%=msg01.getE01RETOTR()%>" readonly></DIV>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>


<H4>Comisiones en : <INPUT type="text" name="E01COMCCY" size="4" maxlength="4" value="<%=msg01.getE01COMCCY()%>" readonly> 	</H4>
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
	<TBODY>
		<TR id="trclear">
			<TH nowrap width="30%">
			<DIV>Concepto</DIV>
			</TH>
			<TH nowrap width="20%">
			<DIV>Monto/Por cuenta de</DIV>
			</TH>
			<TH nowrap width="30%">
			<DIV>Concepto</DIV>
			</TH>
			<TH nowrap width="20%">
			<DIV>Monto/Por cuenta de</DIV>
			</TH>
		</TR>
		<TR id="trdark">
			<TD nowrap width="30%">
			<DIV align="right">Nuestra Comisión :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC01" maxlength="13" size="14" value="<%= msg01.getE01DCMC01().trim()%>" readonly> 
			<SELECT name="E01PYBY01" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY01().equals("P") || !msg01.getE01PYBY01().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY01().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY01().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">Servicio de Courrier :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC03" 	maxlength="13" size="14" value="<%= msg01.getE01DCMC03().trim()%>" readonly>
			<SELECT name="E01PYBY03" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY03().equals("P") || !msg01.getE01PYBY03().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY03().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY03().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="30%">
			<DIV align="right">Swift Acuse de Recibo :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC04" 	maxlength="13" size="14" value="<%= msg01.getE01DCMC04().trim()%>" readonly>
			<SELECT name="E01PYBY04" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY04().equals("P") || !msg01.getE01PYBY04().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY04().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY04().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">
						<DIV align="right">Gastos Banco Remitente
						:</DIV></DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC05" 	maxlength="13" size="14" value="<%= msg01.getE01DCMC05().trim()%>" readonly>
			<SELECT name="E01PYBY05" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY05().equals("P") || !msg01.getE01PYBY05().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY05().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY05().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trdark">
			<TD nowrap width="30%"><DIV align="right">Mensaje(s) Swift :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC06" 	maxlength="13" size="14" value="<%= msg01.getE01DCMC06().trim()%>" readonly>
			<SELECT name="E01PYBY06" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY06().equals("P") || !msg01.getE01PYBY06().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY06().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY06().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">Servicio de Fax/Telefono :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC07" maxlength="13" size="14" value="<%= msg01.getE01DCMC07().trim()%>" readonly>
			<SELECT name="E01PYBY07" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY07().equals("P") || !msg01.getE01PYBY07().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY07().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY07().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="30%">
			<DIV align="right">Swift de Remesa :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC08" maxlength="13" size="14" value="<%= msg01.getE01DCMC08().trim()%>" readonly>
			<SELECT name="E01PYBY08" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY08().equals("P") || !msg01.getE01PYBY08().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY08().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY08().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">Gastos Banco Corresponsal :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC12" 	maxlength="13" size="14" value="<%= msg01.getE01DCMC12().trim()%>" readonly>
			<SELECT name="E01PYBY12" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY12().equals("P") || !msg01.getE01PYBY12().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY12().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY12().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trdark">
			<TD nowrap width="30%">
			<DIV align="right">Swift Aviso Aceptacion :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC13" maxlength="13" size="14" value="<%= msg01.getE01DCMC13().trim()%>" readonly>
			<SELECT name="E01PYBY13" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY13().equals("P") || !msg01.getE01PYBY13().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY13().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY13().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">
						<DIV align="right">Tracers
						:</DIV></DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC14"	maxlength="13" size="14" value="<%= msg01.getE01DCMC14().trim()%>" readonly>
			<SELECT name="E01PYBY14" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY14().equals("P") || !msg01.getE01PYBY14().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY14().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY14().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="30%">
			<DIV align="right">Portes :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC02"	maxlength="13" size="14" value="<%= msg01.getE01DCMC02().trim()%>" readonly>
			<SELECT name="E01PYBY02" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY02().equals("P") || !msg01.getE01PYBY02().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY02().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY02().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">Intereses     :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC11" maxlength="13" size="14" value="<%= msg01.getE01DCMC11().trim()%>" readonly>
			<SELECT name="E01PYBY11" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY11().equals("P") || !msg01.getE01PYBY11().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY11().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY11().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
		<TR id="trdark">
			<TD nowrap width="30%">
			<DIV align="right">Swift Aviso de Pago :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC09" maxlength="13" size="14" value="<%= msg01.getE01DCMC09().trim()%>" readonly>
			<SELECT name="E01PYBY09" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY09().equals("P") || !msg01.getE01PYBY09().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY09().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY09().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
			<TD nowrap width="30%">
			<DIV align="right">Enmienda :</DIV>
			</TD>
			<TD nowrap width="20%"><INPUT type="text" name="E01DCMC10" maxlength="13" size="14" value="<%= msg01.getE01DCMC10().trim()%>" readonly>
			<SELECT name="E01PYBY10" disabled>
				<OPTION value=" " <% if (!msg01.getE01PYBY10().equals("P") || !msg01.getE01PYBY10().equals("D")) out.println("selected"); %>></OPTION>
				<OPTION value="P" <% if (msg01.getE01PYBY10().equals("P")) out.println("selected"); %>>Principal</OPTION>
				<OPTION value="D" <% if (msg01.getE01PYBY10().equals("D")) out.println("selected"); %>>Girado</OPTION>
			</SELECT></TD>
		</TR>
	</TBODY>
</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

</form>
</body>
</html>
