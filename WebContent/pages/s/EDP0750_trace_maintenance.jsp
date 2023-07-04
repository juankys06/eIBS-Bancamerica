<html>
<head>
<title>Consulta Seguimiento Actividad Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP075001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goConfirm(opt) {

	//moveto400();
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
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	//default : alert("something is wrong"); 
	};
//	alert(document.forms[0].SCREEN.value);	
	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {
document.forms[0].SCREEN.value="300";
document.forms[0].PROP.value="<%=brnDetails.getE01DPSNPR().trim()%>";
document.forms[0].submit(); 
 		  
}

function init()
{
document.forms[0].E01DPSCOM.value = "<%=brnDetails.getE01DPSCOM().trim()%>";
}

</script>
</head>

<body onload=javascript:init()>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Detalle Auditable de Propuesta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="trace_maintenance.jsp, EDP0750"></H3>
<P align="center"><INPUT type="text" name="DSC" size="35" maxlength="35"
	value="<%= userPO.getHeader10().trim()%>" readonly></P>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <input type=HIDDEN name="opt"> 
  <input type=HIDDEN name="PROP"> 
<h4>  
      Detalle Propuesta a 
      <% if(userPO.getHeader16().equals("1")) {out.print(" Ingresar");} %>
      <% if(userPO.getHeader16().equals("2")) {out.print(" Modificar");} %>
      <% if(userPO.getHeader16().equals("5")) {out.print(" Consultar");} %>
      <% if(userPO.getHeader16().equals("3")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo" height="235">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="500"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
					<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="33%">
									<DIV align="right">Propuesta:</DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSNPR" size="18" maxlength="16"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSNPR().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
								<td	nowrap width="33%">
									<div align="right">Código Ruta:</div>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSRUT" size="3" maxlength="4" 
									value="<%= brnDetails.getE01DPSRUT().trim()%>" 
									<% if(userPO.getHeader17().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPSRUD" size="30" maxlength="30" readonly value="<%= brnDetails.getE01DPSRUD().trim()%>">
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
								<td nowrap width="33%">
									<DIV align="right">Secuencia Actividad:</DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSSEC" size="4" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSSEC().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
								<td nowrap width="33%">
								<DIV align="right">Codigo Actividad</DIV>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSACT" size="3" maxlength="4" 
									value="<%= brnDetails.getE01DPSACT().trim()%>" 
									<% if(userPO.getHeader17().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPSACD" size="30" maxlength="30" readonly value="<%= brnDetails.getE01DPSACD().trim()%>">
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
								<td nowrap width="33%">
								<DIV align="right">Monto Recomendado</DIV>
								</TD>
							<td nowrap align="left" width="67%">
								<INPUT type="text" name="E01DPSAMN" size="16" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSAMN().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
								<td nowrap width="33%">
								<DIV align="right">OFC EJE :</DIV>
								</td>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSEJE" size="16" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSEJE().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
									<INPUT type="text" name="E01XXXEJE" size="35" maxlength="30"
									readonly value="<%= brnDetails.getE01XXXEJE().trim()%>">
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
								<td nowrap width="33%">
									<DIV align="right">RUT:</DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSIDN" size="4" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSIDN().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
							<TR id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Fecha Inicial </DIV>
								</TD>
								<td nowrap align="left" width="67%">
								<INPUT type="text" name="E01DPSFIN" size="28" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSFIN().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
								<td nowrap width="33%">
								<DIV align="right">Fecha Final :</DIV>
								</td>
								<td nowrap align="left" width="67%">
								
								<INPUT type="text" name="E01DPSFFI" size="26" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSFFI().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>></td>
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
							<TR id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Horas Transcurridas </DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSTIM" size="16" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSTIM().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
							<TR id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Revisiones </DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSREV" size="16" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSREV().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
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
								<td nowrap width="33%">
								<DIV align="right">Estado </DIV>
								</TD>
								<td nowrap align="left" width="67%">
									<INPUT type="text" name="E01DPSEST" size="16" maxlength="6"
									onkeypress="enterInteger()"
									value="<%= brnDetails.getE01DPSEST().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
									<INPUT type="text" name="E01XXXEST" size="37" maxlength="30"
									readonly value="<%= brnDetails.getE01XXXEST().trim()%>">
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
								<td nowrap width="33%">Comentarios:
								</td>
								<td nowrap align="left" width="67%">
									<TEXTAREA name="E01DPSCOM" rows="8" cols="66" Value="<%= brnDetails.getE01DPSCOM().trim()%>"
									<%  if(userPO.getHeader17().equals("1")){out.print("readonly");}  
									%>>
									</TEXTAREA>
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
  <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
</div>
 </form>
</body>
</html>
