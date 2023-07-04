<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />

<script language="JavaScript">
function enter(){
	  document.forms[0].submit();
	 }
</script>

</head>
<body>


<%if (!error.getERRNUM().equals("0")) {
	out.println("<SCRIPT Language=\"Javascript\">");
	error.setERRNUM("0");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>
<FORM METHOD="post"
	ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1060H">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2"> <INPUT TYPE=HIDDEN
	NAME="Pos" VALUE="0">

<table id="tbenter" align="center" style="width: 85%" border="1">
	<tr>
		<td>
		<table id="tbenter" width="100%" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td valign="middle" align="center" colspan="3" height=33><b>Transacciones</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">N&uacute;mero de Cliente :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHOCU" size=10
					maxlength="10" onKeyPress="enterInteger()"> <a
					href="javascript:GetCustomerDescId('E01SCHOCU','','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Fecha desde :</div>
				</td>
				<td width="70%" nowrap>
				<p><input type="text" size="2" maxlength="2" name="E01SCHFD1"
					onKeyPress="enterInteger()"> <input type="text" size="2"
					maxlength="2" name="E01SCHFD2" onKeyPress="enterInteger()"> <input
					type="text" size="2" maxlength="2" name="E01SCHFD3"
					onKeyPress="enterInteger()"> hasta : <input type="text" size="2"
					maxlength="2" name="E01SCHTD1" onKeyPress="enterInteger()"> <input
					type="text" size="2" maxlength="2" name="E01SCHTD2"
					onKeyPress="enterInteger()"> <input type="text" size="2"
					maxlength="2" name="E01SCHTD3" onKeyPress="enterInteger()"></p>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Monto de Transferencia desde :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHAMF" size=19
					maxlength="19" onKeyPress="enterDecimal()"> hasta : <input
					type="text" name="E01SCHAMT" size=19 maxlength="19"
					onKeyPress="enterDecimal()"></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Cuenta Contable de D&eacute;bito :</div>
				</td>
				<td width="70%" nowrap><input type="text" size="2" maxlength="2"
					name="E01SCHDBK" onKeyPress="enterInteger()"> / <input type="text"
					size="3" maxlength="3" name="E01SCHDCY"> <a
					href="javascript:GetCurrency('E01SCHDCY',document.forms[0].E01SCHDBK.value)"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a> <input type="text"
					name="E01SCHDGL" size=15 maxlength="16" onKeyPress="enterInteger()">
				<a
					href="javascript:GetLedger('E01SCHDGL',document.forms[0].E01SCHDBK.value,document.forms[0].E01SCHDCY.value,'')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Cuenta Contable de Cr&eacute;dito :</div>
				</td>
				<td width="70%" nowrap><input type="text" size="2" maxlength="2"
					name="E01SCHCBK" onKeyPress="enterInteger()"> / <input type="text"
					size="3" maxlength="3" name="E01SCHCCY"> <a
					href="javascript:GetCurrency('E01SCHCCY',document.forms[0].E01SCHCBK.value)"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a> <input type="text"
					name="E01SCHCGL" size=15 maxlength="16" onKeyPress="enterInteger()">
				<a
					href="javascript:GetLedger('E01SCHCGL',document.forms[0].E01SCHCBK.value,document.forms[0].E01SCHCCY.value,'')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Cuenta de D&eacute;bito:</div>
				</td>
				<td width="70%" nowrap>
				<div align="left"><input type="text" name="E01SCHDAC" size=15
					maxlength="12" onKeyPress="enterInteger()"> <a
					href="javascript:GetAccount('E01SCHDAC','','RT','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a></div>
				</td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Cuenta de Cr&eacute;dito:</div>
				</td>
				<td width="70%" nowrap>
				<div align="left"><input type="text" name="E01SCHCAC" size=15
					maxlength="12" onKeyPress="enterInteger()"> <a
					href="javascript:GetAccount('E01SCHCAC','','RT','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Help"
					align="absmiddle" border="0"></a></div>
				</td>
			</tr>
          <tr> 
            <td width="10%" align="center">&nbsp;</td>
            <td width="20%" nowrap> 
              <div align="right">Estado : </div>
            </td>
            <td width="70%" nowrap> 
              <select name="E01SCHSTS">
                <option value=" "> </option>
                <option value="*">Pdte. Aprobación</option>
					<OPTION value="P">Pdte. Aprobación Sobregiro</OPTION>
					<option value="A">Pdte. Autorización Finanzas</option>
					<option value="B">Pdte. Liberación Operaciones</option>
					<option value="R">Liberada</option>
					<option value="C">Rechazada</option>
					<option value="D">Eliminada</option>
				</select>
            </td>
          </tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Por Orden :</div>
				</td>
				<td width="70%" nowrap>
				<div align="left"><input type="hidden" name="CustID7" size=10
					maxlength="10" value="0"> <input type="text" name="E01SCHBYO"
					size=36 maxlength="35"> <a
					href="javascript:GetCustomerDescId('CustID7','E01SCHBYO','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="absbottom" border="0"></a></div>
				</td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Beneficiario:</div>
				</td>
				<td width="70%" nowrap><input type="hidden" name="CustID8" size=10
					maxlength="10" value="0"> <input type="text" name="E01SCHBNF"
					size=36 maxlength="35"> <a
					href="javascript:GetCustomerDescId('CustID8','E01SCHBNF','')"><img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="absbottom" border="0"></a></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Ingresado por :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHOPR" size=10
					maxlength="10"></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Liberado por :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHREL" size=10
					maxlength="10"></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Instituci&oacute;n Ordenante :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHORI" size=35
					maxlength="35"></td>
			</tr>
			<tr>
				<td width="10%" align="center">&nbsp;</td>
				<td width="20%" nowrap>
				<div align="right">Banco Beneficiario :</div>
				</td>
				<td width="70%" nowrap><input type="text" name="E01SCHBBK" size=35
					maxlength="35"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>


<p align="center"><input id="EIBSBTN" type=submit name="Submit"
	value="Enviar"></p>

</form>
</body>
</html>
