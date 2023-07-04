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

<jsp:useBean id="msg040004" class="datapro.eibs.beans.ELC040004Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">
	builtNewMenu(lc_apr_standby);
	initMenu(); 
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>

</HEAD>

<BODY>
<H3 align="center">Comisiones de Cartas de Credito Stand By <IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_commissi_sb.jsp, ELC0525"></H3>
<HR size="4">


<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400">
<INPUT NAME="SCREEN" TYPE=HIDDEN VALUE="8" readonly="readonly">
<INPUT NAME="MSG" TYPE=HIDDEN VALUE="ELC040004" readonly="readonly">
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
					size="4" maxlength="2" value="<%=msg040004.getE04LCMBNK().trim()%>"></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Producto : </B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B> <INPUT type="text" name="E01LCMPRO" size="4"
					maxlength="4" value="<%=msg040004.getE04LCMPRO().trim()%>" readonly> </B>
				</DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Numero Carta de Credito:</B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B><INPUT type="text" name="E04LCMACC" size="12"
					maxlength="12" value="<%=msg040004.getE04LCMACC().trim()%>" readonly> </B>
				</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
				<DIV align="right"><B>Fecha Emision:</B></DIV>
				</TD>
				<TD nowrap width="20%" colspan=3>
				<DIV align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=
					"<%=msg040004.getE04LCMID1() + "/" + msg040004.getE04LCMID2() +
					"/"+(msg040004.getE04LCMID3().length() == 1 ? "0"+msg040004.getE04LCMID3() : msg040004.getE04LCMID3())%>"></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Tarifa de Cargos y Moneda:</B></DIV>
				</TD>
				<TD nowrap width="16%" align="left">
					<INPUT type="text" name="E04LCMTAR" size="2"
					maxlength="2" value= "<%=msg040004.getE04LCMTAR()%>" readonly>
					<INPUT type="text" name="E04LCMTCY" size="3"
					maxlength="3" value= "<%=msg040004.getE04LCMTCY()%>" readonly>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="16%">
				<DIV align="right"><B>Fecha Expiracion:</B></DIV>
				</TD>
				<TD nowrap width="20%" colspan=3>
				<DIV align="left"><INPUT type="text" name="E04LCMBNK0" readonly
					size="10" maxlength="10" value=
					"<%=msg040004.getE04LCMEX1()+"/"+msg040004.getE04LCMEX2()+"/"
					+(msg040004.getE04LCMEX3().length() == 1 ? "0"+msg040004.getE04LCMEX3() : msg040004.getE04LCMEX3())%>"></DIV></TD>
				<TD nowrap width="16%">
				<DIV align="right"><B>Monto del Credito:</B></DIV>
				</TD>
				<TD nowrap width="16%">
				<DIV align="left"><B><INPUT type="text" name="E04LCMOAM" size="12"
					maxlength="12" value="<%=msg040004.getE04LCMOAM()%>" readonly> </B></DIV>
				</TD>
			</TR>
		</TABLE>
		</TD>
	</TR>
</TABLE>
<BR>

<H4>Informacion</H4>
<TABLE class="tableinfo" border="0" cellspacing="0" cellpadding="4" width="100%">
					<TR id="trdark">
						<TD width="38%" align="right"></TD>
						<TD nowrap align="center" width="9%"><B>Monto</B></TD>
						<TD nowrap align="center" width="10%"><B>Por Cta de</B></TD>
						<TD nowrap align="center" width="31%"></TD>
						<TD nowrap align="center" width="12%"></TD>
					</TR>
					
					<%
					int size = 9;
					java.util.ArrayList name = new java.util.ArrayList();
					java.util.ArrayList field1 = new java.util.ArrayList();
					java.util.ArrayList field2 = new java.util.ArrayList();

					if(msg040004.getE04LCMOPT().equals("N"))
						{
						name.add("Comision Apertura");
						field1.add("E04LCMC01");
						field2.add("E04LCMP01");

						name.add("Comision Aviso");
						field1.add("E04LCMC02");
						field2.add("E04LCMP02");

						name.add("Comision Confirmacion");
						field1.add("E04LCMC03");
						field2.add("E04LCMP03");

						name.add("Timbres");
						field1.add("E04LCMC08");
						field2.add("E04LCMP08");

					}
					else
					{
						name.add("Comision de Enmienda");
						field1.add("E04LCMC04");
						field2.add("E04LCMP04");

						name.add("Aviso de Enmienda");
						field1.add("E04LCMC05");
						field2.add("E04LCMP05");

						name.add("Discrepencia");
						field1.add("E04LCMC06");
						field2.add("E04LCMP06");

						name.add("Extension de Validez");
						field1.add("E04LCMC07");
						field2.add("E04LCMP07");
					}
					name.add("Portes");
					field1.add("E04LCMC09");
					field2.add("E04LCMP09");
					
					name.add("Courier");
					field1.add("E04LCMC10");
					field2.add("E04LCMP10");
					
					name.add("Swift Apertura");
					field1.add("E04LCMC11");
					field2.add("E04LCMP11");
					
					name.add("Swift Adicional");
					field1.add("E04LCMC12");
					field2.add("E04LCMP12");
					
//only chile
					name.add("Gastos Notariales");
					field1.add("E04GASNOT");
					field2.add("temp1");
					
					name.add("Seguro de Desgravamen");
					field1.add("E04SEGDES");
					field2.add("temp2");

					java.util.ArrayList value2 = new java.util.ArrayList();
					for(int i = 0; i < field2.size() -2; i++)
					{
						value2.add(msg040004.getField((String)field2.get(i)).getString().trim());
					}
					value2.add("");
					value2.add("");

					for (int i = 0; i < name.size(); i++)
					{
						%>
					
						<TR id="<%=( i%2==0 ? "trclear" : "trdark" )%>">
							<TD align="right"><%=(String) name.get(i)%></TD>
							<TD nowrap width="9%" align="center">
								<INPUT name="<%=field1.get(i)%>" type="text"
									value="<%=msg040004.getField((String) field1.get(i)).getString().trim()%>" size="15" maxlength="15" readonly="readonly">
						  </TD>
							<TD nowrap width="10%">
								<SELECT name="<%=field2.get(i)%>" disabled <%if( i >= name.size()-2) out.print("disabled");%> >
									<OPTION value="A">Aplicante</OPTION>
									<%if(i < name.size()-2)
									{%>
										<OPTION value="B" <%=( msg040004.getField((String) field2.get(i)).getString().trim().equals("B") ? "selected" : "" )%>>
											Beneficiario
										</OPTION>
									<%}
									else{%>
										<OPTION value="">Beneficiario</OPTION>								
									<%}%>
						  </SELECT></TD>
							<TD nowrap width="31%"></TD>
							<TD nowrap width="12%"></TD>
						</TR>
					<%}%>
</TABLE>
</FORM>
</BODY>
</HTML>
