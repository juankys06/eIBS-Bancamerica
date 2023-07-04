<html>
<head>
<title>Mantenimiento Plan de Cuentas Propuestade Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP074701Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />



<script language="JavaScript">
function goConfirm(opt) {

	    //saveMe();
	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;
		switch (op) {
	case  "1":  //ok = confirm(msg1 + " Ingresar " + msg2);
				//document.forms[0].SCREEN.value="600";
				//alert(document.forms[0].SCREEN.value);
	 break; 
	case  "2":  ok = confirm(msg1 + " Actualizar " + msg2);
	            document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	//default : alert("something is wrong"); 
	};
	//alert(document.forms[0].SCREEN.value);	
	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="200";
//document.forms[0].E01DPXCFO.value=fmt;
// document.forms[0].E01DPXCLI.value=cli;
// document.forms[0].E01DPXGLN.value=gln;
document.forms[0].submit(); 
	  		  
}

</script>

</head>
<body>  

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

<H3 align="center">Mantenimiento Parámetros Propuesta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="parametros_maintenance.jsp, EDP0747"></H3>
<P align="center"><INPUT type="text" name="DSC" size="35" maxlength="35"
	value="<%= userPO.getHeader10().trim()%>" readonly></P>
	
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0747" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <input type=HIDDEN name="opt">
  <input type=HIDDEN name="MULTI" VALUE="1">
  
<h4>  
      Detalle Parámetros Propuesta a 
      <% if(userPO.getHeader16().equals("1")) {out.print(" Ingresar");} %>
      <% if(userPO.getHeader16().equals("2")) {out.print(" Modificar");} %>
      <% if(userPO.getHeader16().equals("5")) {out.print(" Consultar");} %>
      <% if(userPO.getHeader16().equals("3")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo" height="235">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="100%"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td	nowrap width="30%">
									<div align="right">Banco :</div>
								</td>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPRBNK" onkeypress="enterInteger()" size="2" maxlength="2" value="<%= brnDetails.getE01DPRBNK().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="30%">
									<DIV align="right">C&oacute;digo Aplicaci&oacute;n :</DIV>
								</TD>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPRACD" size="3" maxlength="2" value="<%= brnDetails.getE01DPRACD().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Tipo de Cuenta :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								    <INPUT type="text" name="E01DPRATY" size="4" maxlength="4" value="<%= brnDetails.getE01DPRATY().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Permanencia :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRONL" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRONL().equals(" ")) { out.print("selected"); }%>></option>
									<option value="1" 
									   <%if (brnDetails.getE01DPRONL().equals("1")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias de Vencimiento</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRONL().equals("2")) { out.print("selected"); }%>>F.Aprobaci&oacute;n + dias Aprobaci&oacute;n</option>
									<option value="3"
									   <%if (brnDetails.getE01DPRONL().equals("3")) { out.print("selected"); }%>>F.Desembolso + dias Desembolso</option>
								    <option value="4"
									   <%if (brnDetails.getE01DPRONL().equals("4")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias Vencimiento y Aprobaci&oacute;n </option>
									<option value="5"
									   <%if (brnDetails.getE01DPRONL().equals("5")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias Vencimiento, Aprobaci&oacute;n Desembolso</option>
									<option value="6"
									   <%if (brnDetails.getE01DPRONL().equals("6")) { out.print("selected"); }%>>F.Aprobaci&oacute;n + dias Aprobaci&oacute;n y Desembolso</option>								      
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Dias Vencimiento :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPRDVE" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= brnDetails.getE01DPRDVE().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Dias Aprobaci&oacute;n :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPRDAP" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= brnDetails.getE01DPRDAP().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Dias Desembolso :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPRDDE" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= brnDetails.getE01DPRDDE().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Porcentaje Maximo para Avalizar :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPRPOM" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= brnDetails.getE01DPRPOM().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Ibs e-Trade :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<input type="radio" name="E01DPRIET" value="1" 
						        	<% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						        	<%if(brnDetails.getE01DPRIET().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPRIET" value="0" 
						            <% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						            <%if(!brnDetails.getE01DPRIET().equals("1")) out.print("checked");%>>
              					No 
								</td>
							</tr>														
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Cambios en productos :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRPMO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRPMO().equals(" ")) { out.print("selected"); }%>></option>
									<option value="1" 
									   <%if (brnDetails.getE01DPRPMO().equals("1")) { out.print("selected"); }%>>Cambio por Tipos</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRPMO().equals("2")) { out.print("selected"); }%>>Cambios en General</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
<%--				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Garantia Informacion Detallada :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<input type="radio" name="E01DPRDGA" value="1" 
						        	<% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						        	<%if(brnDetails.getE01DPRDGA().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPRDGA" value="0" 
						            <% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						            <%if(!brnDetails.getE01DPRDGA().equals("1")) out.print("checked");%>>
              					No 
								</td>
							</tr>														
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Seguros Garantias :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<input type="radio" name="E01DPRSGA" value="1" 
						        	<% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						        	<%if(brnDetails.getE01DPRSGA().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPRSGA" value="0" 
						            <% if(userPO.getHeader18().equals("1")){out.print("DISABLED");} %>
						            <%if(!brnDetails.getE01DPRSGA().equals("1")) out.print("checked");%>>
              					No 
								</td>
							</tr>														
						</TBODY>
					</TABLE>
					</TD>
				</TR>
--%>								
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Dias Gracia documentacion vencida:</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPRDDG" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= brnDetails.getE01DPRDDG().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Codigo de Aplicacion 1 :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRD01" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRD01().equals(" ")) { out.print("selected"); }%>></option>
									<option value="10" 
									   <%if (brnDetails.getE01DPRD01().equals("10")) { out.print("selected"); }%>>Prestamos</option>
									<option value="40"
								       <%if (brnDetails.getE01DPRD01().equals("40")) { out.print("selected"); }%>>Cartas Credito</option>
								    <option value="43" 
									   <%if (brnDetails.getE01DPRD01().equals("43")) { out.print("selected"); }%>>Fianzas Otorgadas</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRD01().equals("90")) { out.print("selected"); }%>>Lineas de Credito</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Codigo de Aplicacion 2 :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRD02" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRD02().equals(" ")) { out.print("selected"); }%>></option>
									<option value="10" 
									   <%if (brnDetails.getE01DPRD02().equals("10")) { out.print("selected"); }%>>Prestamos</option>
									<option value="40"
								       <%if (brnDetails.getE01DPRD02().equals("40")) { out.print("selected"); }%>>Cartas Credito</option>
								    <option value="43" 
									   <%if (brnDetails.getE01DPRD02().equals("43")) { out.print("selected"); }%>>Fianzas Otorgadas</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRD02().equals("90")) { out.print("selected"); }%>>Lineas de Credito</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="30%">
								<DIV align="right">Codigo de Aplicacion 3 :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRD01" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRD03().equals(" ")) { out.print("selected"); }%>></option>
									<option value="10" 
									   <%if (brnDetails.getE01DPRD03().equals("10")) { out.print("selected"); }%>>Prestamos</option>
									<option value="40"
								       <%if (brnDetails.getE01DPRD03().equals("40")) { out.print("selected"); }%>>Cartas Credito</option>
								    <option value="43" 
									   <%if (brnDetails.getE01DPRD03().equals("43")) { out.print("selected"); }%>>Fianzas Otorgadas</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRD03().equals("90")) { out.print("selected"); }%>>Lineas de Credito</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Codigo de Aplicacion 4 :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPRD01" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPRD04().equals(" ")) { out.print("selected"); }%>></option>
									<option value="10" 
									   <%if (brnDetails.getE01DPRD04().equals("10")) { out.print("selected"); }%>>Prestamos</option>
									<option value="40"
								       <%if (brnDetails.getE01DPRD04().equals("40")) { out.print("selected"); }%>>Cartas Credito</option>
								    <option value="43" 
									   <%if (brnDetails.getE01DPRD04().equals("43")) { out.print("selected"); }%>>Fianzas Otorgadas</option>
									<option value="2"
								       <%if (brnDetails.getE01DPRD04().equals("90")) { out.print("selected"); }%>>Lineas de Credito</option>
								</SELECT>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
    </tr>
  </table>
  <div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm('<%= userPO.getHeader16() %>')"
         <% if(userPO.getHeader16().equals("5")){out.print("DISABLED");} %>>								
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
</div>
          
<script language="JavaScript">

  		 <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPRBNK.focus()");} %>
  		 <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPRACD.focus()");} %>
  		  
</script>
          
</form>
</body>
</html>
