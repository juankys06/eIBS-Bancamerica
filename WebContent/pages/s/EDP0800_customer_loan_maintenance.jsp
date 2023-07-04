<html>
<head>
<title>Detalle Prestamos con Otras Entidades</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP080001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goConfirm(opt) {

	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;
	switch (op) {
	case  "NEW": //ok = confirm(msg1 + " Ingresar " + msg2);
    	document.forms[0].SCREEN.value="500";
        document.forms[0].PURPOSE.value="NEW";
		document.forms[0].submit();		  	       	       
	 break; 
    case  "MAINTENANCE":  //ok = confirm(msg1 + " Actualizar " + msg2);
        document.forms[0].SCREEN.value="500";
        document.forms[0].PURPOSE.value="MAINTENANCE";
		document.forms[0].submit();		  	       	       
	 break;   
	case  "DELETE":  ok = confirm(msg1 + " Eliminar " + msg2);
		if (ok) {
    	    document.forms[0].SCREEN.value="500";
       		document.forms[0].PURPOSE.value="DELETE";
			document.forms[0].submit();		  	       	       
			};
	 break;
	};
}

function goCancel() {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}

</script>

</head>
<body >

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Detalle Prestamos con Otras Entidades<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="customer_loan_maintenance.EDP0800"></H3>
<P align="center">
	<INPUT type="text" name="DUECUN" size="10" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
	<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getCusName().trim()%>" readonly>
</P>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <input type=HIDDEN name="opt">
  <input type=HIDDEN name="E01DUECUN" value="<%= userPO.getCusNum()%>">
  <input type=HIDDEN name="PURPOSE">
  <input type=HIDDEN name="E01DUEFLG" value="2">
<h4>  
      Detalle Prestamo con otras entidades a 
      <% if(userPO.getPurpose().equals("NEW")) {out.print(" Ingresar");} %>
      <% if(userPO.getPurpose().equals("MAINTENANCE")) {out.print(" Modificar");} %>
      <% if(userPO.getPurpose().equals("INQUIRY")) {out.print(" Consultar");} %>
      <% if(userPO.getPurpose().equals("DELETE")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Código Institucion o Banco :</div>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEINS" size="5" maxlength="4" value="<%= brnDetails.getE01DUEINS().trim()%>" <%  if(!userPO.getPurpose().equals("NEW")){out.print("readonly");} %> > 
					<INPUT type="text" name="D01DUEINS" size="30" maxlength="30" value="<%= brnDetails.getD01DUEINS().trim()%>" <%  if(!userPO.getPurpose().equals("NEW")){out.print("readonly");} %>> 
						<%  if(!userPO.getPurpose().equals("INQUIRY")){ %>
						<A href="javascript:GetCodeDescCNOFC('E01DUEINS','D01DUEINS','Y1')">
						<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
						<%}; %>
				</td>
			</tr>
<%--
			<tr id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Tipo de Operación :</DIV>
				</td>
				<td nowrap align="left" width="67%">
				    <input type="radio" name="E01DUETYO"  value="A" <%if(brnDetails.getE01DUETYO().equals("A")) out.print("checked");%>>
  						Activa 
				    <input type="radio" name="E01DUETYO"  value="P" <%if(brnDetails.getE01DUETYO().equals("P")) out.print("checked");%>>
  						Pasiva 
				</td>
			</tr>
--%>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Código de Producto :</DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEPRD" size="5" maxlength="4" value="<%= brnDetails.getE01DUEPRD().trim()%>" <%  if(!userPO.getPurpose().equals("NEW")){out.print("readonly");} %> >
					<INPUT type="text" name="E01DUEPDS" size="30" maxlength="30" value="<%= brnDetails.getE01DUEPDS().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %> >
				      <% if(userPO.getPurpose().equals("NEW")) {; %>
						<a href="javascript:GetProposalsProducts('E01DUEPRD','E01DUEPDS','')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
				      <% }; %>
				</td>
			</tr>

			<tr id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Numero Referencia :</DIV>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEREF" size="21" maxlength="20" value="<%= brnDetails.getE01DUEREF().trim()%>" <% if(!userPO.getPurpose().equals("NEW")){out.print("readonly");} %> >
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Monto otorgado : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEAMT" size="17" maxlength="15" value="<%= brnDetails.getE01DUEAMT().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %> <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Monto Deuda (Saldo): </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEBAL" size="17" maxlength="15" value="<%= brnDetails.getE01DUEBAL().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %> <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
				</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Tasa : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUERTE" size="10" maxlength="9" value="<%= brnDetails.getE01DUERTE().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>  <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Plazo : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUETRM" size="5" maxlength="4" value="<%= brnDetails.getE01DUETRM().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>  <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterInteger()");} %>>
					Termino:
				    <input type="radio" name="E01DUETRC"  value="D" <%if(brnDetails.getE01DUETRC().equals("D")) out.print("checked");%>>
  						Dias 
				    <input type="radio" name="E01DUETRC"  value="M" <%if(brnDetails.getE01DUETRC().equals("M")) out.print("checked");%>>
  						Meses 
				    <input type="radio" name="E01DUETRC"  value="Y" <%if(brnDetails.getE01DUETRC().equals("Y")) out.print("checked");%>>
  						Años
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Periodicidad Pago Capital : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				<SELECT name="E01DUEPPR"
					<% if(userPO.getPurpose().equals("INQUIRY")){out.print("DISABLED");} %>>
					<OPTION value=""></OPTION>
					<OPTION value="2"<%if (brnDetails.getE01DUEPPR().equals("2")) { out.print("selected"); }%>> Mensual</OPTION>
					<OPTION value="3"<%if (brnDetails.getE01DUEPPR().equals("3")) { out.print("selected"); }%>> Bimestral</OPTION>
					<OPTION value="4"<%if (brnDetails.getE01DUEPPR().equals("4")) { out.print("selected"); }%>> Trimestral</OPTION>
					<OPTION value="9"<%if (brnDetails.getE01DUEPPR().equals("9")) { out.print("selected"); }%>> Cuatrimestral</OPTION>
					<OPTION value="5"<%if (brnDetails.getE01DUEPPR().equals("5")) { out.print("selected"); }%>> Semestral</OPTION>
					<OPTION value="6"<%if (brnDetails.getE01DUEPPR().equals("6")) { out.print("selected"); }%>> Anual</OPTION>
					<OPTION value="7"<%if (brnDetails.getE01DUEPPR().equals("7")) { out.print("selected"); }%>> Irregular</OPTION>
					<OPTION value="8"<%if (brnDetails.getE01DUEPPR().equals("8")) { out.print("selected"); }%>> Al Vencimiento</OPTION>
				</SELECT>
				</TD>
			</tr>
			<TR id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Periodicidad Pago Intereses : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				<SELECT name="E01DUEPIN"
					<% if(userPO.getPurpose().equals("INQUIRY")){out.print("DISABLED");} %>>
					<OPTION value=""></OPTION>
					<OPTION value="2"<%if (brnDetails.getE01DUEPIN().equals("2")) { out.print("selected"); }%>> Mensual</OPTION>
					<OPTION value="3"<%if (brnDetails.getE01DUEPIN().equals("3")) { out.print("selected"); }%>> Bimestral</OPTION>
					<OPTION value="4"<%if (brnDetails.getE01DUEPIN().equals("4")) { out.print("selected"); }%>> Trimestral</OPTION>
					<OPTION value="9"<%if (brnDetails.getE01DUEPIN().equals("9")) { out.print("selected"); }%>> Cuatrimestral</OPTION>
					<OPTION value="5"<%if (brnDetails.getE01DUEPIN().equals("5")) { out.print("selected"); }%>> Semestral</OPTION>
					<OPTION value="6"<%if (brnDetails.getE01DUEPIN().equals("6")) { out.print("selected"); }%>> Anual</OPTION>
					<OPTION value="7"<%if (brnDetails.getE01DUEPIN().equals("7")) { out.print("selected"); }%>> Irregular</OPTION>
					<OPTION value="8"<%if (brnDetails.getE01DUEPIN().equals("8")) { out.print("selected"); }%>> Al Vencimiento</OPTION>
				</SELECT>
				</TD>
			</tr>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Fecha de Otorgamiento : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				<INPUT type="text" name="E01DUEOP1" size="3" maxlength="2" value="<%= brnDetails.getE01DUEOP1().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>/
				<INPUT type="text" name="E01DUEOP2" size="3" maxlength="2"  value="<%= brnDetails.getE01DUEOP2().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>/
				<INPUT type="text" name="E01DUEOP3" size="5" maxlength="4"  value="<%= brnDetails.getE01DUEOP3().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>
				<% if(!userPO.getPurpose().equals("INQUIRY")) { %> 
				<A 	href="javascript:DOBPicker(document.forms[0].E01DUEOP2,document.forms[0].E01DUEOP1,document.forms[0].E01DUEOP3)"><IMG
					src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
				</A>
				<% };%>
				</TD>
			</tr>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Fecha de Vencimiento : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				<INPUT type="text" name="E01DUEMA1" size="3" maxlength="2" value="<%= brnDetails.getE01DUEMA1().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>/
				<INPUT type="text" name="E01DUEMA2" size="3" maxlength="2"  value="<%= brnDetails.getE01DUEMA2().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>/
				<INPUT type="text" name="E01DUEMA3" size="5" maxlength="4"  value="<%= brnDetails.getE01DUEMA3().trim()%>"
				<% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>
				>
				<% if(!userPO.getPurpose().equals("INQUIRY")) { %> 
				<A 	href="javascript:DOBPicker(document.forms[0].E01DUEMA2,document.forms[0].E01DUEMA1,document.forms[0].E01DUEMA3)"><IMG
					src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
				</A>
				<% };%>
				</TD>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Tipo de Garantía :</div>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUETYG" size="5" maxlength="4" value="<%= brnDetails.getE01DUETYG().trim()%>" <%  if(!userPO.getPurpose().equals("NEW")){out.print("readonly");} %> > 
					<INPUT type="text" name="D01DUETYG" size="30" maxlength="30" value="<%= brnDetails.getD01DUETYG().trim()%>" readonly>
						<%  if(!userPO.getPurpose().equals("INQUIRY")){ %>
						<A href="javascript:GetCodeDescCNOFC('E01DUETYG','D01DUETYG','05')">
						<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
						<%}; %>
				</td>
			</tr>
<%--
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">% Participación : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DUEPPA" size="10" maxlength="9" value="<%= brnDetails.getE01DUEPPA().trim()%>" <% if(userPO.getPurpose().equals("INQUIRY")){out.print("readonly");} %>  <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal(2)");} %>>
				</td>
			</tr>
--%>
		</TABLE>
		</td>
    </tr>
  </table>
  
<div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm('<%= userPO.getPurpose() %>')"
         <% if(userPO.getPurpose().equals("INQUIRY")){out.print("DISABLED");} %> >
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
</div>
      <script language="JavaScript">
	  </script>
</form>
</body>
</html>