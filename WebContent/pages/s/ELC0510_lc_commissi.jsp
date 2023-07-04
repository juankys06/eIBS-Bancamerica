<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Letters of Credit Opening/Maintenance</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="msgLC" class="datapro.eibs.beans.ELC051004Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

builtNewMenu(<%= msgLC.getH04FLGWK3().trim().equals("N") ? "lc_opening" : "lc_maint"%>);

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
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

</HEAD>

<BODY>
<H3 align="center">Comisiones de Cartas de Credito<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_commissi.jsp, ELC0510"></H3>
<HR size="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
<TABLE class="tableinfo">
	<TR>
		<TD nowrap>
		<TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
			class="tbhead">
			<TR id="trdark">
				<TD nowrap width="16%">
				<DIV align="right"><B>Banco :</B></DIV>
				</TD>
				<TD nowrap width="20%">
				<DIV align="left"><INPUT type="text" name="E04LCMBNK" readonly
					size="4" maxlength="2" value="<%=msgLC.getE04LCMBNK().trim()%>"></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Producto : </B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B> <INPUT type="text" name="E01LCMPRO" size="4"
					maxlength="4" value="<%=msgLC.getE04LCMPRO().trim()%>" readonly> </B>
				</DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Numero Carta de Credito:</B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B><INPUT type="text" name="E04LCMACC" size="12"
					maxlength="12" value="<%=msgLC.getE04LCMACC().trim()%>" readonly> </B>
				</DIV>
				</TD>
			</TR>
			
						<TR id="trclear">
				<TD nowrap width="16%">
				<DIV align="right"><B>Fecha Emision</B></DIV>
				</TD>
				<TD nowrap width="20%">
				<DIV align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10"
					value='<%=msgLC.getE04LCMID1() + "/" + msgLC.getE04LCMID2() +
					"/"+(msgLC.getE04LCMID3().length() == 1 ? "0"+msgLC.getE04LCMID3() : msgLC.getE04LCMID3())%>'></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Tarifa de Cargos y Moneda : </B></DIV>
				</TD>
				<TD nowrap width="16%">
				<INPUT type="text" name="E04LCMTAR" size="2" maxlength="2"
					value="<%=msgLC.getE04LCMTAR()%>" readonly>
				<INPUT type="text" name="E04LCMTCY" size="3" maxlength="3" value="<%=msgLC.getE04LCMTCY()%>" readonly>
				<DIV align="left"><B> </B>
				</DIV>
				</TD>
				<TD nowrap width="16%"><DIV align="right"><B>Monto Base :</B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B><INPUT type="text" name="E04TCYAMN" size="12"
					maxlength="12" value="<%=msgLC.getE04TCYAMN()%>" readonly></B>
				</DIV>
				</TD>
			</TR>
			
			<TR id="trdark">
				<TD nowrap width="16%">
				<DIV align="right"><B>Fecha Expiracion:</B></DIV>
				</TD>
				<TD nowrap width="20%" colspan=3>
				<DIV align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=
					"<%=msgLC.getE04LCMEX1()+"/"+msgLC.getE04LCMEX2()+"/"
					+(msgLC.getE04LCMEX3().length() == 1 ? "0"+msgLC.getE04LCMEX3() : msgLC.getE04LCMEX3())%>"></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Monto del Credito:</B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B><INPUT type="text" name="E04LCMOAM" size="12"
					maxlength="12" value="<%=msgLC.getE04LCMOAM()%>" readonly> </B></DIV>
				</TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>
<BR>

<h4>Informacion</h4>
<table class="tableinfo">
	<tbody>
		<tr>
			<td>
			<table border="0" cellspacing="0" width="100%">
					<tr id="trdark">
						<td align="right"></td>
						<td nowrap align="center" width="17%"><b>Monto</b></td>
						<td nowrap align="center" width="15%"><b>Por Cta de</b></td>
						<td nowrap align="center" width="26%"></td>
						<td nowrap align="center" width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Comision Emmienda</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC04" size="15" maxlength="15" value="<%=msgLC.getE04LCMC04()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP04"  >
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP04().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP04().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr><tr id="trdark">
						<td align="right">Aviso Emmienda</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC05" size="15" maxlength="15" value="<%=msgLC.getE04LCMC05()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP05">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP05().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP05().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr><tr id="trclear">
						<td align="right">Discrepancias</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC06" size="15" maxlength="15" value="<%=msgLC.getE04LCMC06()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP06">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP06().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP06().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Extension de Validez</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC07" size="15" maxlength="15" value="<%=msgLC.getE04LCMC07()%>">
						</td>
						<td nowrap width="15%">
							<select name=E04LCMP07>
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP07().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP07().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Portes</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC09" size="15" maxlength="15" value="<%=msgLC.getE04LCMC09()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP09">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP09().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP09().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Courier</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC10" size="15" maxlength="15" value="<%=msgLC.getE04LCMC10()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP10">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP10().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP10().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trclear">
						<td align="right">Swift Apertura</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC11" size="15" maxlength="15" value="<%=msgLC.getE04LCMC11()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP11">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP11().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP11().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<tr id="trdark">
						<td align="right">Swift Adicional</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E04LCMC12" size="15" maxlength="15" value="<%=msgLC.getE04LCMC12()%>">
						</td>
						<td nowrap width="15%">
							<select name="E04LCMP12">
								<option value="A">Aplicante</option>
								<option value="B" <%if(msgLC.getE04LCMP12().equals("B")) out.print("selected");%>>Beneficiario</option>
								<option value="W" <%if(msgLC.getE04LCMP12().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
					<% if (userPO.getID().equals("18")) { %>
						<tr id="trdark">
							<td align="right">Gastos Notariales</td>
							<td nowrap width="17%" align="center">
								<input type="text" name="E04GASNOT" size="15" maxlength="15" value="<%=msgLC.getE04GASNOT()%>">
							</td>
							<td nowrap width="15%">
								<select name="E04GASP13" disabled="disabled">
									<option value="A">Aplicante</option>
								</select></td>
							<td nowrap width="26%"></td>
							<td nowrap width="12%"></td>
						</tr>
					<% } %>
					
			</table>
			</td>
		</tr>
	</tbody>
</table>


<H4 style="text-align:center"><INPUT type="checkbox" name="H04FLGWK2" value="A" <% if(msgLC.getH04FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Advertencias</H4>
<DIV align="center"><INPUT id="EIBSBTN" type=submit name="Enviar"
	value="Enviar"></DIV>

</FORM>
</BODY>
</HTML>
