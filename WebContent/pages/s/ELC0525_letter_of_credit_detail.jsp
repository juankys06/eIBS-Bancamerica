<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letters of Credit Opening/Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="lcNew" class="datapro.eibs.beans.ELC050001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"	scope="session" />

<SCRIPT LANGUAGE="javascript">

function goMsgSwift() {
    
     
     if (document.forms[0].E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+document.forms[0].E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }


 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0525_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
 }

 function goAction(op) {
	if(op == "A"){
		document.forms[0].SCREEN.value = 1;
		document.forms[0].submit();		 
	}else if(op == "D"){
		document.forms[0].SCREEN.value = 2;
		document.forms[0].submit();
	}else if(op == "R"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}else if(op == "V"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}
 }

function goDetail(scr){
	document.forms[0].SCREEN.value = scr;
	document.forms[0].submit();
}

<%if (!userPO.getPurpose().equals("NEW")) {%>

	builtNewMenu(lc_approbal_detail);

<%}%>

	builtHPopUp();
	
	function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
		init(opth,field,bank,ccy,field1,field2,opcod);
		showPopupHelp();
	}
   
</SCRIPT>

<%
if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}

if (!userPO.getPurpose().equals("NEW")) {
	out.println("<SCRIPT> initMenu();  </SCRIPT>");
}
%>

</head>
<body>
<h3 align="center">
Apertura de Carta de Credito
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"	name="EIBS_GIF" alt="letter_of_credit_detail.jsp,ELC0525">
</h3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%if (userPO.getPurpose().equals("NEW"))
											  	out.print("2");
											  else
											  	out.print("4");%>">
	<INPUT TYPE=HIDDEN NAME="E01LCMATY" VALUE="<%=lcNew.getE01LCMATY().trim()%>">
	<INPUT TYPE=HIDDEN NAME="reason">
	<table class="tableinfo">
		<tr>
			<td nowrap>
			<table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
				<tr id="trdark">
					<td nowrap width="16%">
						<div align="right">
							<b>Banco :</b>
						</div>
					</td>
					<td nowrap width="20%">
						<div align="left">
							<input type="text" NAME="E01LCMBNK" readonly size="4" maxlength="2" VALUE="<%=lcNew.getE01LCMBNK().trim()%>">
						</div>
					</td>
					<td nowrap width="16%">
						<div align="right">
							<b>Producto : </b>
						</div>
					</td>
					<td nowrap width="16%">
						<div align="left">
							<b> 
								<input type="text" name="E01LCMPRO" size="4" maxlength="4" value="<%=lcNew.getE01LCMPRO().trim()%>" readonly> 
							</b>
						</div>
					</td>
					<td nowrap width="16%">
						<div align="right">
							<b>Número de Carta de Credito:</b>
						</div>
					</td>
					<td nowrap width="16%">
						<div align="left">
							<b> 
								<%if (lcNew.getE01LCMACC().trim().equals("999999999999"))
								{%>
									<input type="text" size="12" maxlength="12" value="Nuevo" readonly>
									<input type="hidden" name="E01LCMACC" value="<%=lcNew.getE01LCMACC().trim()%>" readonly>
								<%}
								else
								{%>
									<input type="hidden" name="E01LCMACC" value="<%=lcNew.getE01LCMACC().trim()%>" readonly>								
									<input type="text" name="ACCNUM" size="12"
										maxlength="12" value="<%=lcNew.getE01LCMACC().trim()%>" readonly>
								<%}%>
							</b>
						</div>
					</td>
				</tr>
				<tr id="trclear">
					<td nowrap width="16%">
						<div align="right">
							<b>Nuestra Referencia:</b>
						</div>
					</td>
					<td nowrap width="20%" colspan=3>
						<div align="left">
							<input type="text" NAME="E01LCMORF" size="20" maxlength="16" VALUE="<%=lcNew.getE01LCMORF().trim()%>" readonly>
						</div>
					</td>
					<td nowrap width="16%">
						<div align="right">
							<b>Referencia  del Otro Banco:</b>
						</div>
					</td>
					<td nowrap width="16%">
						<div align="left">
							<b> 
								<input type="text" name="E01LCMTRF" size="20" maxlength="16" value="<%=lcNew.getE01LCMTRF().trim()%>" readonly> 
							</b>
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>

	<h4>Aplicante</h4>
	<table class="tableinfo">
		<tr>
			<td>
				<table cellspacing="0" cellpadding="2" width="100%" border="0">
					<tr id="trdark">
						<td align="right">
							<table border="0" cellspacing="0">
								<tr id="trdark">
									<td align="right">Numero de Cliente o de Cuenta:</td>
									<td nowrap width="25%">
										<select name="E01LCMAF2" disabled="disabled">
											<option value=" "
													<%
														if (!(lcNew.getE01LCMAF2().equals("C") || lcNew.getE01LCMAF2().equals("A")))
															out.print("selected");
													%>
													selected></option>
											<option value="C"	
													<%
														if (lcNew.getE01LCMAF2().equals("C"))
															out.print("selected");%>
													>Cliente</option>
											<option value="A" 
													<%
														if (lcNew.getE01LCMAF2().equals("A"))
															out.print("selected");%>
													>Cuenta</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table border="0" cellspacing="0">
								<tr id="trdark">
									<td align="left">
										<input type="text" name="E01LCMAPA" size="12" maxlength="12" value="<%=lcNew.getE01LCMAPA()%>" readonly> 
										<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0">
									</td>
									<td>
										<!--
										<a href="javascript: GetCustomerDetails('E01LCMAPA','E01LCMAP1','','','E01LCMAP7','E01LCMAP2','E01LCMAP3','E01LCMAP4','','E01LCMAP5','','','E01LCMAP6','','','','','')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF2.selectedIndex = 1" border="0">
										</a> Cliente o 
										<a href="javascript: GetAccByClient('E01LCMAPA','','','','E01LCMAP1')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF2.selectedIndex = 2"  border="0">
										</a> Cuenta
										-->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right">Nombre :</div>
						</td>
						<td>
							<div align="left"><input type="text" name="E01LCMAP1" size="45" maxlength="35" value="<%=lcNew.getE01LCMAP1()%>" readonly></div>
						</td>
					</tr>
					<tr id="trdark">
						<td>
							<div align="right">Dirección :</div>
						</td>
						<td>
							<div align="left"><input type="text" name="E01LCMAP2" size="45" maxlength="35" value="<%=lcNew.getE01LCMAP2()%>" readonly></div>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right"></div>
						</td>
						<td>
							<div align="left"><input type="text" name="E01LCMAP3" size="45"	maxlength="35" value="<%=lcNew.getE01LCMAP3()%>" readonly></div>
						</td>
					</tr>
					<tr id="trdark">
						<td>
							<div align="right"></div>
						</td>
						<td>
							<div align="left"><input type="text" name="E01LCMAP4" size="45" maxlength="35" value="<%=lcNew.getE01LCMAP4()%>" readonly></div>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right">Estado</div>
						</td>
						<td align="left">
							<table>
								<tr>
									<td>
										<input type="text" name="E01LCMAP5" size="4" maxlength="4" value="<%=lcNew.getE01LCMAP5()%>" readonly>  Código Postal 
										<input type="text" name="E01LCMAP6" size="11" maxlength="10" value="<%=lcNew.getE01LCMAP6()%>" readonly> País 
										<input type="text" name="E01LCMAP7" size="4" maxlength="4" value="<%=lcNew.getE01LCMAP7()%>" readonly>
										<!--
										<a href="javascript:GetCodeCNOFC('E01LCMAP7','03')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" >
										</a>
										-->
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<h4>Beneficiario</h4>
	<table class="tableinfo">
		<tr>
			<td>
				<table cellspacing="0" cellpadding="2" width="100%" border="0">
					<tr id="trdark">
						<td align="right">
							<table border="0" cellspacing="0">
								<tr id="trdark">
									<td align="right">Numero de Cliente o de Cuenta:</td>
									<td nowrap width="25%">
										<select name="E01LCMAF3" disabled="disabled">
											<option value=" "
												<%
													if (!(lcNew.getE01LCMAF3().equals("C") || lcNew.getE01LCMAF3().equals("A")))
														out.print("selected");%>
												selected></option>
											<option value="C"
												<%
													if (lcNew.getE01LCMAF3().equals("C"))
														out.print("selected");%>
													>Cliente</option>
											<option value="A"
												<%
													if (lcNew.getE01LCMAF3().equals("A"))
														out.print("selected");%>
													>Cuenta</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table border="0" cellspacing="0">
								<tr id="trdark">
									<td align="left">
										<input type="text" name="E01LCMBAC" size="12" maxlength="12" value="<%=lcNew.getE01LCMBAC()%>" readonly>
									</td>
									<td>
										<!--
										<a href="javascript: GetCustomerDetails('E01LCMBAC','E01LCMBN1','','','E01LCMBN7','E01LCMBN2','E01LCMBN3','E01LCMBN4','','E01LCMBN5','','','E01LCMBN6','','','','','')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF3.selectedIndex = 1" border="0">
										</a> Cliente o 
										<a href="javascript: GetAccByClient('E01LCMBAC','','','','E01LCMBN1')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF3.selectedIndex = 2" border="0">
										</a> Cuenta
										-->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right">Nombre :</div>
						</td>
						<td>
							<div align="left">
								<input type="text" name="E01LCMBN1" size="45" maxlength="35" value="<%=lcNew.getE01LCMBN1()%>" readonly>
								<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0">
							</div>
						</td>
					</tr>
					<tr id="trdark">
						<td>
							<div align="right">Dirección :</div>
						</td>
						<td>
							<div align="left">
								<input type="text" name="E01LCMBN2" size="45" maxlength="35" value="<%=lcNew.getE01LCMBN2()%>" readonly>
							</div>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right"></div>
						</td>
						<td>
							<div align="left">
								<input type="text" name="E01LCMBN3" size="45" maxlength="35" value="<%=lcNew.getE01LCMBN3()%>" readonly>
							</div>
						</td>
					</tr>
					<tr id="trdark">
						<td>
							<div align="right"></div>
						</td>
						<td>
							<div align="left">
								<input type="text" name="E01LCMBN4" size="45" maxlength="35" value="<%=lcNew.getE01LCMBN4()%>" readonly>
							</div>
						</td>
					</tr>
					<tr id="trclear">
						<td>
							<div align="right">Estado</div>
						</td>
						<td align="left">
							<table>
								<tr>
									<td>
										<input type="text" name="E01LCMBN5" size="2" maxlength="2" value="<%=lcNew.getE01LCMBN5()%>" readonly> Código Postal 
										<input type="text" name="E01LCMBN6" size="11" maxlength="10" value="<%=lcNew.getE01LCMBN6()%>" readonly> País 
										<input type="text" name="E01LCMBN7" size="4" maxlength="4" value="<%=lcNew.getE01LCMBN7()%>" readonly>
										<!--
										<a href="javascript:GetCodeCNOFC('E01LCMBN7','03')">
											<img src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" >
										</a>
										-->
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<h4>Banco 
		<%if (lcNew.getE01LCMTYP().trim().toUpperCase().equals("I")){%>
			Emisor</h4>
			<table class="tableinfo">
				<tr>
					<td>
						<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
							<TBODY>
								<TR id="trdark">
									<TD align="right">
										<TABLE border="0" cellspacing="0">
											<TBODY>
												<TR id="trdark">
													<TD align="right">Numero de Cliente o de Cuenta:</TD>
													<TD nowrap width="25%">
														<SELECT name="E01LCMAF1" disabled="disabled">
															<OPTION value=" "></OPTION>
															<OPTION value="C" <%if (lcNew.getE01LCMAF1().equals("C")) out.print("selected");%>>Customer</OPTION>
															<OPTION value="A" <%if (lcNew.getE01LCMAF1().equals("A")) out.print("selected");%>>Cuenta</OPTION>
														</SELECT>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
									<TD>
										<TABLE border="0" cellspacing="0">
											<TBODY>
												<TR id="trdark">
													<TD align="left">
														<INPUT type="text" name="E01LCMIBA" size="12" maxlength="12" value="<%=lcNew.getE01LCMIBA()%>" readonly>
													</TD>
													<TD>
														<!--
														<A href="javascript: GetCustomerDetails('E01LCMIBA','E01LCMIB1','','','E01LCMIB7','E01LCMIB2','E01LCMIB3','E01LCMIB4','','E01LCMIB5','','','E01LCMIB6','','','','','')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 1" border="0">
														</A> Cliente o 
														<A href="javascript: GetAccByClient('E01LCMIBA','','','','E01LCMIB1')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 2" border="0">
														</A> Cuenta
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<DIV align="right">Codigo Swift :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMIBI" size="14" maxlength="12" value="<%=lcNew.getE01LCMIBI()%>" readonly>
											<!--
											<A href="javascript:GetCorrespondentDescIdSwift('E01LCMIBI','','')">
												 <IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="help" border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right">Nombre :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMIB1" size="45" maxlength="35" value="<%=lcNew.getE01LCMIB1()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right">Dirección :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMIB2" size="45" maxlength="35" value="<%=lcNew.getE01LCMIB2()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMIB3" size="45" maxlength="35" value="<%=lcNew.getE01LCMIB3()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMIB4" size="45" maxlength="35" value="<%=lcNew.getE01LCMIB4()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right">Estado</DIV>
									</TD>
									<TD align="left">
										<TABLE>
											<TBODY>
												<TR>
													<TD>
														<INPUT type="text" name="E01LCMIB5" size="2" maxlength="2" value="<%=lcNew.getE01LCMIB5()%>" readonly> Código Postal 
														<INPUT type="text" name="E01LCMIB6" size="11" maxlength="10" value="<%=lcNew.getE01LCMIB6()%>" readonly> País 
														<INPUT type="text" name="E01LCMIB7" size="4" maxlength="4" value="<%=lcNew.getE01LCMIB7()%>" readonly>
														<!--
														<A href="javascript:GetCodeCNOFC('E01LCMIB7','03')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
														</A>
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</td>
				</tr>
			</table>
			
			<h4>Banco Corresponsal</h4>
			<table class="tableinfo">
				<tr>
					<td align="center" width="100%">
						<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
							<TBODY>
								<TR id="trdark">
									<TD width="30%">
										<DIV align="right">Cliente :</DIV>
									</TD>
									<TD width="70%">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCOR" size="12" maxlength="12" value="<%=lcNew.getE01LCMCOR()%>" readonly> 
											<!--
											<A href="javascript: GetCustomerDetails('E01LCMCOR','E01LCMCB1','','','E01LCMCB7','E01LCMCB2','E01LCMCB3','E01LCMCB4','','E01LCMCB5','','','E01LCMCB6','','','','','')">
												<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
										<DIV align="right">Codigo Swift :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMCBI" size="14" maxlength="12" value="<%=lcNew.getE01LCMCBI()%>" readonly>
											<!--
											<A href="javascript:GetCorrespondentDescIdSwift('E01LCMCBI','','')">
												 <IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="help" border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right">Nombre :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMCB1" size="45" maxlength="35" value="<%=lcNew.getE01LCMCB1()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right">Dirección :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMCB2" size="45" maxlength="35" value="<%=lcNew.getE01LCMCB2()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMCB3" size="45" maxlength="35" value="<%=lcNew.getE01LCMCB3()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMCB4" size="45" maxlength="35" value="<%=lcNew.getE01LCMCB4()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right">Estado</DIV>
									</TD>
									<TD align="left">
										<TABLE>
											<TBODY>
												<TR>
													<TD>	
														<INPUT type="text" name="E01LCMCB5" size="2" maxlength="2" value="<%=lcNew.getE01LCMCB5()%>" readonly> Código Postal 
														<INPUT type="text" name="E01LCMCB6" size="11" maxlength="10" value="<%=lcNew.getE01LCMCB6()%>" readonly> País 
														<INPUT type="text" name="E01LCMCB7" size="3" maxlength="7" value="<%=lcNew.getE01LCMCB7()%>" readonly>
														<!--
														<A href="javascript:GetCodeCNOFC('E01LCMCB7','03')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
														</A>
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</td>
				</tr>
			</table>
		
		<%}else{
		
			out.print("Avisador / Confirmador </h4>");%>
 
			<table class="tableinfo">
				<tr>
					<td>
						<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
							<TBODY>
								<TR id="trdark">
									<TD align="right">
										<TABLE border="0" cellspacing="0">
											<TBODY>
												<TR id="trdark">
													<TD align="right">Numero de Cliente o de Cuenta:</TD>
													<TD nowrap width="25%">
														<SELECT name="E01LCMAF1" disabled="disabled">
															<OPTION value=" "></OPTION>
															<OPTION value="C" <%if (lcNew.getE01LCMAF1().equals("C")) out.print("selected");%>>Customer</OPTION>
															<OPTION value="A" <%if (lcNew.getE01LCMAF1().equals("A")) out.print("selected");%>>Cuenta</OPTION>
														</SELECT>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
									<TD>
										<TABLE border="0" cellspacing="0">
											<TBODY>
												<TR id="trdark">
													<TD align="left">
														<INPUT type="text" name="E01LCMIBA" size="12" maxlength="12" value="<%=lcNew.getE01LCMIBA()%>" readonly>
													</TD>
													<TD>
														<!--
														<A href="javascript: GetCustomerDetails('E01LCMIBA','E01LCMAB1','','','E01LCMAB7','E01LCMAB2','E01LCMAB3','E01LCMAB4','','E01LCMAB5','','','E01LCMAB6','','','','','')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
														</A> Cliente o 
														<A href="javascript: GetAccByClient('E01LCMABA','','','','E01LCMAB1')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." onclick="javascript: document.forms[0].E01LCMAF1.selectedIndex = 2"  border="0">
														</A> Cuenta
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<DIV align="right">Codigo Swift :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMABI" size="12" maxlength="12" value="<%=lcNew.getE01LCMABI()%>" readonly>
											<!--
											<A href="javascript:GetCorrespondentDescIdSwift('E01LCMABI','','')">
												<IMG	src="<%//=request.getContextPath()%>/images/1b.gif" alt="help" border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right">Nombre :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMAB1" size="45" maxlength="35" value="<%=lcNew.getE01LCMAB1()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right">Dirección :</DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMAB2" size="45" maxlength="35" value="<%=lcNew.getE01LCMAB2()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMAB3" size="45" maxlength="35" value="<%=lcNew.getE01LCMAB3()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD>
										<DIV align="right"></DIV>
									</TD>
									<TD>
										<DIV align="left">
											<INPUT type="text" name="E01LCMAB4" size="45" maxlength="35" value="<%=lcNew.getE01LCMAB4()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD>
										<DIV align="right">Estado</DIV>
									</TD>
									<TD align="left">
										<TABLE>
											<TBODY>
												<TR>
													<TD>
														<INPUT type="text" name="E01LCMAB5" size="2" maxlength="2" value="<%=lcNew.getE01LCMAB5()%>" readonly> Código Postal 
														<INPUT type="text" name="E01LCMAB6" size="11" maxlength="10" value="<%=lcNew.getE01LCMAB6()%>" readonly> País 
														<INPUT type="text" name="E01LCMAB7" size="4" maxlength="4" value="<%=lcNew.getE01LCMAB7()%>" readonly>
														<!--
														<A href="javascript:GetCodeCNOFC('E01LCMAB7','03')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
														</A>
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</td>
				</tr>
			</table>


			<h4>Banco Corresponsal</h4>
			<table class="tableinfo">
				<tr>
					<td align="center" width="100%">
						<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
							<TBODY>
								<TR id="trdark">
									<TD width="25%"></TD>
									<TD width="16%">
										<DIV align="right">Cliente :</DIV>
									</TD>
									<TD width="59%">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCBC" size="12" maxlength="12" value="<%=lcNew.getE01LCMCBC()%>" readonly>
											<!--
											<A href="javascript: GetCustomerDetails('E01LCMCBC','E01LCMCA1','','','E01LCMCA7','E01LCMCA2','E01LCMCA3','E01LCMCA4','','E01LCMCA5','','','E01LCMCA6','','','','','')">
												<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt=". . ." border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR>
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right">Codigo Swift :</DIV>
									</TD>
									<TD width="507">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCAI" size="12" maxlength="12" value="<%=lcNew.getE01LCMCAI()%>" readonly>
											<!--
											<A href="javascript:GetCorrespondentDescIdSwift('E01LCMCAI','','')">
												<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="help" border="0">
											</A>
											-->
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right">Nombre :</DIV>
									</TD>
									<TD width="507">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCA1" size="45" maxlength="35" value="<%=lcNew.getE01LCMCA1()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right">Dirección :</DIV>
									</TD>
									<TD width="507">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCA2" size="45" maxlength="35" value="<%=lcNew.getE01LCMCA2()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right"></DIV>
									</TD>
									<TD width="507">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCA3" size="45" maxlength="35" value="<%=lcNew.getE01LCMCA3()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trdark">
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right"></DIV>
									</TD>
									<TD width="507">
										<DIV align="left">
											<INPUT type="text" name="E01LCMCA4" size="45" maxlength="35" value="<%=lcNew.getE01LCMCA4()%>" readonly>
										</DIV>
									</TD>
								</TR>
								<TR id="trclear">
									<TD width="216"></TD>
									<TD width="137">
										<DIV align="right">Estado</DIV>
									</TD>
									<TD align="left" width="507">
										<TABLE>
											<TBODY>
												<TR>
													<TD>
														<INPUT type="text" name="E01LCMCA5" size="2" maxlength="2" value="<%=lcNew.getE01LCMCA5()%>" readonly> Código Postal 
														<INPUT type="text" name="E01LCMCA6" size="11" maxlength="10" value="<%=lcNew.getE01LCMCA6()%>" readonly> País 
														<INPUT type="text" name="E01LCMCA7" size="3" maxlength="7" value="<%=lcNew.getE01LCMCA7()%>" readonly>
														<!--
														<A href="javascript:GetCodeCNOFC('E01LCMCA7','03')">
															<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
														</A>
														-->
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</td>
				</tr>
			</table>

		<%}%>

		<h4>Banco Reembolsador</h4>
		<table class="tableinfo">
			<tr>
				<td>
					<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
						<TBODY>
							<TR id="trdark">
								<TD align="right">Numero de Cliente o de Cuenta :</TD>
								<TD>
									<TABLE border="0" cellspacing="0">
										<TBODY>
											<TR id="trdark">
												<TD align="left">
													<INPUT type="text" name="E01LCMRBA" size="12" maxlength="12" value="<%=lcNew.getE01LCMRBA()%>" readonly>
												</TD>
												<TD>
													<!--
													<A href="javascript: GetCustomerDetails('E01LCMRBA','E01LCMRB1','','','E01LCMRB7','E01LCMRB2','E01LCMRB3','E01LCMRB4','','E01LCMRB5','','','E01LCMRB6','','','','','')">
														<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
													</A> Cliente o 
													<A href="javascript: GetAccByClient('E01LCMRBA','','','','E01LCMRB1')">
														<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
													</A> Cuenta
													-->
												</TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD>
									<DIV align="right">Codigo Swift :</DIV>
								</TD>
								<TD>
									<DIV align="left">
										<INPUT type="text" name="E01LCMRBI" size="14" maxlength="12" value="<%=lcNew.getE01LCMRBI()%>" readonly>
										<!--
										<A href="javascript:GetCorrespondentDescIdSwift('E01LCMRBI','','')">
											 <IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="help" border="0">
										</A>
										-->
									</DIV>
								</TD>
							</TR>
							<TR id="trdark">
								<TD>
`									<DIV align="right">Nombre :</DIV>
								</TD>
								<TD>
									<DIV align="left">
										<INPUT type="text" name="E01LCMRB1" size="45" maxlength="35" value="<%=lcNew.getE01LCMRB1()%>" readonly>
									</DIV>
								</TD>
							</TR>
							<TR id="trclear">
								<TD>
									<DIV align="right">Dirección :</DIV>
								</TD>
								<TD>
									<DIV align="left">
										<INPUT type="text" name="E01LCMRB2" size="45" maxlength="35" value="<%=lcNew.getE01LCMRB2()%>" readonly>
									</DIV>
								</TD>
							</TR>
							<TR id="trdark">
								<TD>
									<DIV align="right"></DIV>
								</TD>
								<TD>
									<DIV align="left">
										<INPUT type="text" name="E01LCMRB3" size="45" maxlength="35" value="<%=lcNew.getE01LCMRB3()%>" readonly>
									</DIV>
								</TD>
							</TR>
							<TR id="trclear">
								<TD>
									<DIV align="right"></DIV>
								</TD>
								<TD>
									<DIV align="left">
										<INPUT type="text" name="E01LCMRB4" size="45" maxlength="35" value="<%=lcNew.getE01LCMRB4()%>" readonly>
									</DIV>
								</TD>
							</TR>
							<TR id="trdark">
								<TD>
									<DIV align="right">Estado</DIV>
								</TD>
								<TD align="left">
									<TABLE id="trdark">
										<TBODY>
											<TR>
												<TD>
													<INPUT type="text" name="E01LCMRB5" size="2" maxlength="2" value="<%=lcNew.getE01LCMRB5()%>" readonly> Código Postal 
													<INPUT type="text" name="E01LCMRB6" size="11" maxlength="10" value="<%=lcNew.getE01LCMRB6()%>" readonly> País 
													<INPUT type="text" name="E01LCMRB7" size="4" maxlength="4" value="<%=lcNew.getE01LCMRB7()%>" readonly>
													<!--
													<A href="javascript:GetCodeCNOFC('E01LCMRB7','03')">
														<IMG src="<%//=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
													</A>
													-->
												</TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</td>
			</tr>
		</table>
		<BR>
</form>
</body>
</html>
