<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letters of Credit Opening/Maintenance</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>

<jsp:useBean id="msg" class="datapro.eibs.beans.ELC040004Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script LANGUAGE="javascript">

	builtNewMenu(sb_opening);
	initMenu();
</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 

%>

</head>

<body>
<h3 align="center">Comisiones de Cartas de Credito Stand By <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="sb_commissi.jsp, ELC0400"></h3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400">
<input TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<table class="tableinfo">
	<tr>
		<td nowrap>
		<table cellspacing="0" cellpadding="2" width="100%" border="0" 	class="tbhead">
			<tr id="trdark">
				<td nowrap width="16%">
				   <div align="right"><b>Banco :</b></div>
				</td>
				<td nowrap width="20%">
				   <div align="left"><input type="text" name="E04LCMBNK" readonly size="4" maxlength="2" value="<%=msg.getE04LCMBNK().trim()%>"></div>
				</td>
				<td nowrap width="16%">
				  <div align="right"><b>Producto : </b></div>
				</td>
				<td nowrap width="16%">
				  <div align="left"><b> <input type="text" name="E01LCMPRO" size="4"maxlength="4" value="<%=msg.getE04LCMPRO().trim()%>" readonly> </b></div>
				</td>
				<td nowrap width="16%">
				   <div align="right"><b>Numero Carta de Credito:</b></div>
				</td>
				<td nowrap width="16%">
				   <div align="left"><b><input type="text" name="E04LCMACC" size="12"maxlength="12" value="<%=msg.getE04LCMACC().trim()%>" readonly> </b>
				</div>
				</td>
			</tr>
		<tr id="trclear">
				<td nowrap width="16%">
				   <div align="right"><B>Fecha Emision:</B></div>
				</td>
				<td nowrap width="20%">
				   <div align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10"
					value='<%=msg.getE04LCMID1() + "/" + msg.getE04LCMID2() +
					"/"+(msg.getE04LCMID3().length() == 1 ? "0"+msg.getE04LCMID3() : msg.getE04LCMID3())%>'></div>
				</td>
				<td nowrap width="16%">
				  <div align="right"><B> Tarifa de Cargos y Moneda:</B></div>
				</td>
				<td nowrap width="16%">
				  <div align="left"><b> </b><INPUT type="text" name="E04LCMTAR"
					size="2" maxlength="2" value="<%=msg.getE04LCMTAR()%>" readonly><INPUT
					type="text" name="E04LCMTCY" size="3" maxlength="3"
					value="<%=msg.getE04LCMTCY()%>" readonly></div>
				</td>
				<td nowrap width="16%">
				   <div align="right"><b>Monto Base :</b></div>
				</td>
				<td nowrap width="16%">
				   <div align="left"><B><INPUT type="text" name="E04TCYAMN" size="12" maxlength="12" value="<%=msg.getE04TCYAMN()%>" readonly></B></div>
				</td>
			</tr>
			
  		<tr id="trdark">
				<td nowrap width="16%">
				<div align="right"><b>Fecha Expiracion:</b></div>
				</td>
				<td nowrap width="20%" colspan=3>
				<div align="left"><input type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=	"<%=msg.getE04LCMEX1()+"/"+msg.getE04LCMEX2()+"/"
					+(msg.getE04LCMEX3().length() == 1 ? "0"+msg.getE04LCMEX3() : msg.getE04LCMEX3())%>"></div></td>
				<td nowrap width="16%">
				<div align="right"><b>Monto del Credito:</b></div>
				</td>
				<td nowrap width="16%">
				<div align="left"><b><input type="text" name="E04LCMOAM" size="12"
					maxlength="12" value="<%=msg.getE04LCMOAM()%>" readonly> </b></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>

<h4>Informacion</h4>
<table class="tableinfo">
	<tbody>
		<tr>
			<td>
			<table border="0" cellspacing="0" width="100%">
				<tbody>
					<tr id="trdark">
						<td align="right"></td>
						<td nowrap align="center" width="17%"><b>Monto</b></td>
						<td nowrap align="center" width="15%"><b>Por Cta de</b></td>
						<td nowrap align="center" width="26%"></td>
						<td nowrap align="center" width="12%"></td>
					</tr>
					<tr id="trclear">
							<td align="right">Comision Apertura</td>
							<td nowrap width="17%" align="center">
								<input type="text" name="E04LCMC01" size="15" maxlength="15" value="<%=msg.getE04LCMC01()%>">
							</td>
							<td nowrap width="15%">
								<select name="E04LCMP01"  >
									<option value="A">Aplicante</option>
									<option value="B" <%if(msg.getE04LCMP01().equals("B")) out.print("selected");%>>Beneficiario</option>
									<option value="W" <%if(msg.getE04LCMP01().equals("W")) out.print("selected");%>>Condonar</option>
								</select></td>
							<td nowrap width="26%"></td>
							<td nowrap width="12%"></td>
						</tr>
					<tr id="trdark">
						<td align="right">Comision Aviso</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC02" size="15" maxlength="15" value="<%=msg.getE04LCMC02()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP02">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP02().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP02().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Comision Confirmacion</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC03" size="15" maxlength="15" value="<%=msg.getE04LCMC03()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP03">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP03().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP03().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Timbres</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC08" size="15" maxlength="15" value="<%=msg.getE04LCMC08()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP08"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP08().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP08().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Portes</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC09" size="15" maxlength="15" value="<%=msg.getE04LCMC09()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP09"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP09().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP09().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Courier</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC10" size="15" maxlength="15" value="<%=msg.getE04LCMC10()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP10"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP10().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP10().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Swift Apertura</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC11" size="15" maxlength="15" value="<%=msg.getE04LCMC11()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP11"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP11().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP11().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Swift Adicional</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC12" size="15" maxlength="15" value="<%=msg.getE04LCMC12()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP12"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msg.getE04LCMP12().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msg.getE04LCMP12().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<%if (userPO.getID().equals("18")) {%>
					<tr id="trclear">
						<td align="right">Gastos Notariales</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04GASNOT" size="15" maxlength="15" value="<%=msg.getE04GASNOT()%>">
						</td>
						<td nowrap width="15%">
							<select name="temp1"  >
								<option value="A">Aplicante</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Seguro de Desgravamen</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04SEGDES" size="15" maxlength="15" value="<%=msg.getE04SEGDES()%>">
						</td>
						<td nowrap width="15%">
							<select name="temp2"  >
								<option value="A">Aplicante</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<%}%>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>


<h4 style="text-align:center"><input type="checkbox" name="H04FLGWK2" value="A" <% if(msg.getH04FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Advertencias</h4>
<div align="center"><input id="EIBSBTN" type=submit name="Enviar"
	value="Enviar"></div>

</form>
</body>
</html>
