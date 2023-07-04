<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento de Cobranza Simple</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="dcNew"  class="datapro.eibs.beans.EDC001001Message" scope="session" />
<jsp:useBean id="dcNew2"  class="datapro.eibs.beans.EDC001002Message" scope="session" />
<jsp:useBean id="error"  class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

<%if (!userPO.getPurpose().equals("NEW")) {%>

	builtNewMenu(dc_s_maint);
 
<%}%>

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
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
<h3 align="center">Mantenimiento de Cobranza Simple<br>Comisiones y Cargos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dc_commissions.jsp, EDC0010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
<input type="hidden" name="E01DCMBNK" value="<%= dcNew.getE01DCMBNK().trim() %>">

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
						<INPUT type="text" name="E01DCMBNK" readonly size="4" maxlength="2" value="<%=dcNew.getE01DCMBNK().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Número de Cobranza :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B> 
				<%if (dcNew.getE01DCMACC().trim().equals("999999999999"))
				{%>
					<INPUT type="text" size="12" maxlength="12" value="Nuevo" readonly>
					<INPUT type="hidden" name="E01DCMACC" value="<%=dcNew.getE01DCMACC().trim()%>">
				<%}
				else
				{%>
					<INPUT type="text" name="E02DCMACC" size="12" maxlength="12" value="<%=dcNew.getE01DCMACC().trim()%>" readonly>
				<%}%>
					</B></DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
					<DIV align="right"><B>Nuestra Referencia :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E01DCMORF" size="20" maxlength="16" value="<%=dcNew.getE01DCMORF().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Producto :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMPRO" size="4" maxlength="4" value="<%=dcNew.getE01DCMPRO().trim()%>" readonly>
					</B><INPUT type="text" name="E01DSCPRO" size="30" maxlength="30"
						value="<%=dcNew.getE01DSCPRO().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="16%">
					<DIV align="right"><B>Tipo de Cobranza :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E01DCMTYP" size="20" maxlength="16" value="<%=dcNew.getE01DCMTYP().trim()%>">
					</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="right"><B>Tabla de Tarifas y Comisiones :</B></DIV>
				</TD>
				<TD nowrap width="16%">
					<DIV align="left"><B>
						<INPUT type="text" name="E02DCMTAR" size="4" maxlength="4" value="<%=dcNew2.getE02DCMTAR().trim()%>" readonly></B>
					</DIV>
				</TD>
			</TR>
			<TR id="trclear">
				<TD nowrap width="16%">
					<DIV align="right"><B>Moneda Tarifa :</B></DIV>
				</TD>
				<TD nowrap width="20%">
					<DIV align="left">
						<INPUT type="text" name="E02DCMTCY" size="20" maxlength="16" value="<%=dcNew2.getE02DCMTCY().trim()%>">
					</DIV>
				</TD>
				<TD nowrap width="16%">
				</TD>
				<TD nowrap width="16%">

				</TD>
			</TR>				
			</TBODY></TABLE>
		</td>
	</tr>
</table>

  <br>  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr> 
            <th nowrap width="30%"> 
             	<div>Concepto</div>
            </th>
            <th nowrap width="20%">  
             	<div>Monto/Por cuenta de</div>
			</th>
			<th nowrap width="30%">
             	<div>Concepto</div>
			</th>
            <th nowrap width="20%">
             	<div>Monto/Por cuenta de</div>
            </th>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
             	<div align="right">Nuestra Comisión :</div>
            </td>
            <td nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC01" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC01().trim()%>">
				<SELECT name="E02DCMF01">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF01().equals("P") && !dcNew2.getE02DCMF01().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF01().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF01().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</td>
			<td nowrap width="30%">
             	<div align="right">Servicio de Courier :</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E02DCMC03" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC03().trim()%>">
				<SELECT name="E02DCMF03">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF03().equals("P") && !dcNew2.getE02DCMF03().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF03().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF03().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
             	<div align="right">Swift Acuse de Recibo:</div>
            </td>
            <td nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC04" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC04().trim()%>">
				<SELECT name="E02DCMF04">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF04().equals("P") && !dcNew2.getE02DCMF04().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF04().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF04().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</td>
			<td nowrap width="30%">
             	<div align="right">Gastos Banco Remitente:</div>
			</td>
            <td nowrap width="20%">
            	<INPUT type="text" name="E02DCMC05" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC05().trim()%>">
				<SELECT name="E02DCMF05">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF05().equals("P") && !dcNew2.getE02DCMF05().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF05().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF05().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
             	<div align="right">Mensaje Swift :</div>
            </td>
            <td nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC06" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC06().trim()%>">
				<SELECT name="E02DCMF06">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF06().equals("P") && !dcNew2.getE02DCMF06().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF06().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF06().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</td>
			<TD nowrap width="30%">
             	<DIV align="right">Gastos Banco Corresponsal:</DIV>
			</TD><TD nowrap width="20%">
            	<INPUT type="text" name="E02DCMC12" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC12().trim()%>">
				<SELECT name="E02DCMF12">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF12().equals("P") && !dcNew2.getE02DCMF12().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF12().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF12().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
            </TD>
            
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="30%"> 
             	<DIV align="right">Enmiendas :</DIV>
            </TD><TD nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC10" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC10().trim()%>">
				<SELECT name="E02DCMF10">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF10().equals("P") && !dcNew2.getE02DCMF10().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF10().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF10().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</TD>
            
			<TD nowrap width="30%"> 
             	<DIV align="right">Servicio de Fax/Telefono :</DIV>
            </TD><TD nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC07" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC07().trim()%>">
				<SELECT name="E02DCMF07">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF07().equals("P") && !dcNew2.getE02DCMF07().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF07().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF07().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</TD>
            
          </tr>
          <tr id="trclear"> 
            <TD nowrap width="30%"> 
             	<DIV align="right">Swift Aviso de Pago :</DIV>
            </TD><TD nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC09" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC09().trim()%>">
				<SELECT name="E02DCMF09">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF09().equals("P") && !dcNew2.getE02DCMF09().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF09().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF09().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</TD>
            
			<TD nowrap width="30%"> 
             	<DIV align="right">Swift de  Remesa :</DIV>
            </TD><TD nowrap width="20%">  
            	<INPUT type="text" name="E02DCMC08" maxlength="13" size="14" value="<%= dcNew2.getE02DCMC08().trim()%>">
				<SELECT name="E02DCMF08">
					<OPTION value=" " <% if (!dcNew2.getE02DCMF08().equals("P") && !dcNew2.getE02DCMF08().equals("D")) out.println("selected"); %>></OPTION>
					<OPTION value="P" <% if (dcNew2.getE02DCMF08().equals("P")) out.println("selected"); %>>Principal</OPTION>
					<OPTION value="D" <% if (dcNew2.getE02DCMF08().equals("D")) out.println("selected"); %>>Girado</OPTION>
				</SELECT>
			</TD>
            
          </tr>          
        </table>
      </td>
    </tr>
  </table>
<BR>  

<DIV align="center">
	<INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar">
</DIV>

</form>
</body>
</html>
