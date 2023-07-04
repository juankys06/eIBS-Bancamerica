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

<jsp:useBean id="msg051005" class="datapro.eibs.beans.ELC051005Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_approval_transfer);
   	builtHPopUp();

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
<H3 align="center">Comisiones de Transferencias a Cartas de Credito
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_commissi.jsp, ELC0525"></H3>
<HR size="4">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525" >
  <table class="tableinfo">
    <tbody>
      <tr>
        <td nowrap><table cellspacing="0" cellpadding="2" width="100%" border="0">
            <tbody>
              <tr id="trdark0">
                <td nowrap width="16%"><div align="right"><b>Banco :</b></div></td>
                <td nowrap width="20%"><input type="text" name="E05LCMBNK" readonly size="4" maxlength="2" value="<%=msg051005.getE05LCMBNK().trim()%>">
                </td>
                <td nowrap width="16%"><div align="right"><b>Número de Carta de Credito :</b></div></td>
                <td nowrap width="16%"><div align="left"><b>
                    <%if (msg051005.getE05LCMACC().trim().equals("999999999999")) {%>
                    <input type="text" size="12" maxlength="12" value="Nuevo" readonly>
                    <input type="hidden" name="E05LCMACC" value="<%=msg051005.getE05LCMACC().trim()%>" readonly>
                    <%} else {%>
                    <input type="text" name="E05LCMACC" size="12" maxlength="12" value="<%=msg051005.getE05LCMACC().trim()%>" readonly>
                    <%}%>
                    </b></div></td>
              </tr>
              <tr id="trclear0">
                <td nowrap width="16%"><div align="right"><b>Nuestra Referencia :</b></div></td>
                <td nowrap width="20%"><div align="left">
                    <input type="text" name="E05LCMORF" size="20" maxlength="16" value="<%=msg051005.getE05LCMORF().trim()%>" readonly>
                  </div></td>
                <td nowrap width="16%"><div align="right"><b>Producto :</b></div></td>
                <td nowrap width="16%"><div align="left"><b>
                    <input type="text" name="E05LCMPRO" size="4" maxlength="4" value="<%=msg051005.getE05LCMPRO().trim()%>" readonly>
                    </b></div></td>
              </tr>
              <tr id="trdark0">
                <td nowrap width="16%"><div align="right"><b>Referencia del Otro Banco :</b></div></td>
                <td nowrap width="16%"><div align="left"><b>
                    <input type="text" name="E05LCMTRF" size="20" maxlength="16" value="<%=msg051005.getE05LCMTRF().trim()%>" readonly>
                    </b></div></td>
                <td nowrap width="16%"><div align="right"><b>Descripcion de Producto :</b></div></td>
                <td nowrap width="16%"><div align="left"><b>
                    <input type="text" name="E05DSCPRO" size="40" maxlength="35" value="<%=msg051005.getE05DSCPRO()%>"
							readonly>
                    </b></div></td>
              </tr>
            </tbody>
          </table></td>
      </tr>
    </tbody>
  </table>
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
						<td align="right">Comision por Transferencia</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E05LCTC01" size="15" maxlength="15" value="<%=msg051005.getE05LCTC01()%>" readonly="readonly">
						</td>
						<td nowrap width="15%">
							<select name="E05LCTP01" disabled="disabled" >
								<option value="A">Primer Beneficiario</option>
								<option value="B" <%if(msg051005.getE05LCTP01().equals("B")) out.print("selected");%>>Segundo Beneficiario</option>
								<option value="W" <%if(msg051005.getE05LCTP01().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr><tr id="trdark">
						<td align="right">Comision por Swift</td>
						<td nowrap width="17%" align="center">
							<input type="text" name="E05LCTC03" size="15" maxlength="15" value="<%=msg051005.getE05LCTC03()%>" readonly="readonly">
						</td>
						<td nowrap width="15%">
							<select name="E05LCTP03" disabled="disabled">
								<option value="A">Primer Beneficiario</option>
								<option value="B" <%if(msg051005.getE05LCTP03().equals("B")) out.print("selected");%>>Segundo Beneficiario</option>
								<option value="W" <%if(msg051005.getE05LCTP03().equals("W")) out.print("selected");%>>Condonar</option>
							</select></td>
						<td nowrap width="26%"></td>
						<td nowrap width="12%"></td>
					</tr>
			</table>
			</td>
		</tr>
	</tbody>
</table>


</FORM>
</BODY>
</HTML>
