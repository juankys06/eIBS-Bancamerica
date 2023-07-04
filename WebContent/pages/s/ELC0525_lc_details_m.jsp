<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Mantenimiento de Carta de Credito Comercial</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<jsp:useBean id="msg051002" class="datapro.eibs.beans.ELC051002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">

	<% if (userPO.getAccOpt().equals("T") )  { %>
			builtNewMenu(lc_apr_tranfer_cc_maint);
	<% } else { %>
			builtNewMenu(lc_apr_cc_maint);
	<% } %>
	initMenu();
   
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>
</HEAD>
<BODY>
<H3 align="center">Mantenimiento de Carta de Credito Comercial<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif"
align="left" name="EIBS_GIF" ALT="ELC0525_lc_details_m.jsp"></H3>
<HR size="4">
<FORM>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tableinfo" id="trclear">
<TR id="trdark">
                <TD nowrap width="16%"align="right"><B>Banco : </B></TD>
                <TD nowrap width="20%" align="left">
                  <INPUT type="text" name="E02LCMBNK" size="4" maxlength="2"
							value="<%=msg051002.getE02LCMBNK().trim()%>" readonly>
                </TD>
                <TD nowrap width="16%"align="right"><B>Número de Carta de Credito : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <%if (msg051002.getE02LCMACC().trim().equals("999999999999"))
				{%>
                  <INPUT type="text" size="13" maxlength="12" value="Nuevo" readonly>
                  <INPUT type="hidden" name="E02LCMACC" value="<%=msg051002.getE02LCMACC().trim()%>" readonly>
                  <%}
				else
				{%>
                  <INPUT type="text" name="E02LCMACC" size="13" maxlength="12"
							value="<%=msg051002.getE02LCMACC().trim()%>" readonly>
                  <%}%>
                  </B></TD>
    </TR>
              <TR>
                <TD nowrap width="16%"align="right"><B>Nuestra Referencia : </B></TD>
                <TD nowrap width="20%" align="left">
                  <INPUT type="text" name="E02LCMORF" size="20"
							maxlength="16" value="<%=msg051002.getE02LCMORF().trim()%>" readonly></TD>
                <TD nowrap width="16%"align="right"><B>Producto : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02LCMPRO" size="4"
							maxlength="4" value="<%=msg051002.getE02LCMPRO().trim()%>" readonly>
                  </B></TD>
              </TR>
              <TR id="trdark">
                <TD nowrap width="16%"align="right"><B>Referencia del Otro Banco : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02LCMTRF" size="20" maxlength="16" value="<%=msg051002.getE02LCMTRF().trim()%>" readonly>
                  </B></TD>
                <TD nowrap width="16%" align="right"><B>Descripcion del Producto : </B></TD>
                <TD nowrap width="16%" align="left">
                  <B>
                  <INPUT type="text" name="E02DSCPRO" size="40" maxlength="35" value="<%=userPO.getHeader14()%>" readonly>
                  </B></TD>
              </TR>
  </TABLE>

  <H4>Tipo de Operación</H4>
  <TABLE class="tableinfo">
    <TBODY>
      <TR>
        <TD nowrap><TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
            <TBODY>
              <TR id="trdark">
                <TD nowrap width="16%"align="right"><B>Enmienda : </B></TD>
                <TD nowrap width="20%" align="left">
                  <% if (msg051002.getE02LCMAMF().equals("Y")) out.print("YES"); else out.print("NO");%>
                  <INPUT name="E02LCMLAN" type="HIDDEN"
							value="<%= msg051002.getE02LCMLAN().trim()%>" readonly="readonly"></TD>
                <TD nowrap width="16%"align="right"><B>Ultima Enmienda : </B></TD>
                <TD nowrap width="16%"><DIV align="left"><B>
                    <INPUT name="E02LCMLAN" type="text" disabled value="<%= msg051002.getE02LCMLAN().trim()%>" size="4" maxlength="4" readonly="readonly">
                    </B></DIV>
                  <INPUT name="E02LCMLAN" type="HIDDEN" value="<%= msg051002.getE02LCMLAN().trim()%>" readonly="readonly"></TD>
                <TD nowrap width="16%"align="right"><B>Fecha Ultima Enmienda : </B></TD>
                <TD nowrap width="16%"><DIV align="left"><B>
                    <INPUT name="E02LCMLA1" type="text" disabled value="<%= msg051002.getE02LCMLA1().trim()%>" size="3"
							maxlength="2" readonly="readonly">
                    <INPUT name="E02LCMLA2" type="text" disabled
							value="<%= msg051002.getE02LCMLA2().trim()%>" size="3" maxlength="2" readonly="readonly">
                    <INPUT name="E02LCMLA3"
							type="text" disabled
							value="<%= msg051002.getE02LCMLA3().trim()%>" size="3" maxlength="2" readonly="readonly">
                    </B></DIV>
                  <INPUT name="E02LCMLA1" type="HIDDEN"
							value="<%= msg051002.getE02LCMLA1().trim()%>" readonly="readonly">
                  <INPUT
							name="E02LCMLA2" type="HIDDEN" value="<%= msg051002.getE02LCMLA2().trim()%>" readonly="readonly">
                  <INPUT name="E02LCMLA3"
							type="HIDDEN"
							value="<%= msg051002.getE02LCMLA3().trim()%>" readonly="readonly"></TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <H4>Información de la Carta de Crédito</H4>
  <TABLE class="tableinfo">
    <TR >
      <TD nowrap>
      	<TABLE cellpadding="2" cellspacing="0" width="100%" border="0">
          <TBODY>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Fecha de Emisión: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMID1" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMID1().trim().equals("0") ? "":msg051002.getE02LCMID1().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMID1().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMID2" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMID2().trim().equals("0") ? "":msg051002.getE02LCMID2().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMID2().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMID3" type="text" onKeyPress="enterInteger()" value='<%  if(msg051002.getE02LCMID3().length() < 2 && !msg051002.getE02LCMID3().equals("0"))
							out.print("0");
					out.print((msg051002.getE02LCMID3().trim().equals("0") ? "":msg051002.getE02LCMID3().trim()));%>' size="2" maxlength="2" readonly="readonly">
        		<%if (msg051002.getF02LCMID3().equals("Y")) out.print("id=\"txtchanged\"");%>
                 <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0" > </TD>
              <TD nowrap width="25%"align="right"> Fecha de Expiración: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMEX1" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMEX1().trim().equals("0") ? "":msg051002.getE02LCMEX1().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMEX1().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMEX2" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMEX2().trim().equals("0") ? "":msg051002.getE02LCMEX2().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMEX2().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMEX3" type="text" onKeyPress="enterInteger()" value='<%  if(msg051002.getE02LCMEX3().length() < 2 && !msg051002.getE02LCMEX3().equals("0"))
							out.print("0");
					out.print((msg051002.getE02LCMEX3().trim().equals("0") ? "":msg051002.getE02LCMEX3().trim()));%>' size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMEX3().equals("Y")) out.print("id=\"txtchanged\"");%>>
                 <IMG src="<%=request.getContextPath()%>/images/Check.gif"
						alt="campo obligatorio"  border="0"></TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Ultima Fecha de Embarque: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMSD1" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMSD1().trim().equals("0") ? "":msg051002.getE02LCMSD1().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMSD1().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMSD2" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMSD2().trim().equals("0") ? "":msg051002.getE02LCMSD2().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMSD2().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMSD3" type="text" onKeyPress="enterInteger()" value='<%  if(msg051002.getE02LCMSD3().length() < 2 && !msg051002.getE02LCMSD3().equals("0"))
							out.print("0");
					out.print((msg051002.getE02LCMSD3().trim().equals("0") ? "":msg051002.getE02LCMSD3().trim()));%>' size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMSD3().equals("Y")) out.print("id=\"txtchanged\"");%>>
                 </TD>
              <TD nowrap width="25%"align="right">Fecha de Aviso/Confirmación: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMCN1" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMCN1().trim().equals("0") ? "":msg051002.getE02LCMCN1().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMCN1().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMCN2" type="text" onKeyPress="enterInteger()" value="<%= (msg051002.getE02LCMCN2().trim().equals("0") ? "":msg051002.getE02LCMCN2().trim())%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMCN2().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMCN3" type="text" onKeyPress="enterInteger()" value='<%  if(msg051002.getE02LCMCN3().length() < 2 && !msg051002.getE02LCMCN3().equals("0"))
							out.print("0");
					out.print((msg051002.getE02LCMCN3().trim().equals("0") ? "":msg051002.getE02LCMCN3().trim()));%>' size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMCN3().equals("Y")) out.print("id=\"txtchanged\"");%>>
                 </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Oficina: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMBRN" type="text" disabled value="<%= msg051002.getE02LCMBRN().trim()%>" size="4" maxlength="3" readonly="readonly">
              </TD>
              <TD nowrap width="25%"align="right">Moneda y Tipo de Cambio M/E: </TD>
              <TD nowrap width="25%">
                <INPUT name="E02LCMCCY" type="text" disabled value="<%= msg051002.getE02LCMCCY().trim()%>" size="4" maxlength="3" readonly="readonly">
                <INPUT name="E02LCMOFX" type="text" disabled value="<%= msg051002.getE02LCMOFX().trim()%>" size="12" maxlength="11" readonly="readonly"              
        		<%if (msg051002.getF02LCMOFX().equals("Y")) out.print("id=\"txtchanged\"");%>>
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Centro de Costo: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMCCN" type="text" disabled value="<%= msg051002.getE02LCMCCN().trim()%>" size="9" maxlength="8" readonly="readonly"
					<%if (msg051002.getF02LCMCCN().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
              <TD nowrap width="25%"align="right">Monto del Crédito: </TD>
              <TD nowrap width="25%"><INPUT name="E02LCMOAM" type="text" value="<%= msg051002.getE02LCMOAM().trim()%>" size="17" maxlength="16" readonly="readonly"
        		<%if (msg051002.getF02LCMOAM().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Clausula de Aproximación: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMABC" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMABC().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMABC().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                &nbsp; &nbsp; &nbsp; Porcentaje:
                <INPUT name="E02LCMABP" type="text" onKeyPress="enterInteger()"	value="<%if(msg051002.getE02LCMABP().trim().length() == 1 && !msg051002.getE02LCMABP().trim().equals("0")) out.print("0");%><%= msg051002.getE02LCMABP().trim()%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMABP().equals("Y")) out.print("id=\"txtchanged\"");%>>
                <INPUT name="E02LCMAPM" type="text" onKeyPress="enterInteger()"	value="<%if(msg051002.getE02LCMAPM().trim().length() == 1 && !msg051002.getE02LCMAPM().trim().equals("0")) out.print("0");%><%= msg051002.getE02LCMAPM().trim()%>" size="2" maxlength="2" readonly="readonly"
        		<%if (msg051002.getF02LCMAPM().equals("Y")) out.print("id=\"txtchanged\"");%>>
        	  </TD>	
              <TD nowrap width="25%"align="right">Incoterms: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMITR" type="text" value="<%= msg051002.getE02LCMITR().trim()%>" size="4" maxlength="4" readonly="readonly"
        		<%if (msg051002.getF02LCMITR().equals("Y")) out.print("id=\"txtchanged\"");%>>
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Tenor: </TD>
              <TD nowrap width="25%">
                <% 
                	String tenor = ""; 
                	switch (msg051002.getE02LCMTNR().charAt(0)) {
                		case 'S': tenor = "Pago"; break;
                		case 'A': tenor = "Aceptación"; break;
                		case 'M': tenor = "Pago Mixto"; break;
                		case 'D': tenor = "Pago Diferido"; break;
                		case 'N': tenor = "Negociación"; break;
                		default: tenor = ""; break;
                	}
                %>
              	<INPUT name="E02LCMTNR" type="TEXT" size="14" readonly="readonly"  value = "<%= tenor %>"
					<%if (msg051002.getF02LCMTNR().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
              </TD>
              <TD nowrap width="25%"align="right">Confirmada: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMCNF" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMCNF().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMCNF().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%" align="right">Agregar Confirmacion: </TD>
              <TD nowrap width="25%" align="left">
                 <% 
                	String test = msg051002.getE02LCMCNO().trim();
                	String confirm = null;
                	if(test.equalsIgnoreCase("Y")){
                		confirm =  "Confirmar (CONFIRM)";
                	}
                	else if(test.equalsIgnoreCase("N")){
                		confirm = "No Confirmar (WITHOUT)";
                	}
                	else if(test.equalsIgnoreCase("M")){
                		confirm = "Puede Confirmar (MAY ADD)";
                	}
                	else
                		confirm = ""; 
                %>
                <INPUT name="E02LCMCNO" type="TEXT" size="28" readonly="readonly"  value = "<%= confirm %>"
					<%if (msg051002.getF02LCMCNO().equals("Y")) out.print("id=\"txtchanged\"");%>>        
              </TD>
              <TD nowrap width="25%"align="right">Transferible: </TD>
              <TD nowrap width="25%" align="left">
              	<INPUT name="E02LCMTRN" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMTRN().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMTRN().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Embarques Parciales: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMPSH" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMPSH().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMPSH().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
              <TD nowrap width="25%"align="right">Transbordo: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMTSH" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMTSH().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMTSH().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Agente/Representante: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMBRK" type="text"  disabled onKeyPress="enterDecimal()" value="<%= msg051002.getE02LCMBRK().trim()%>" size="17" maxlength="16" readonly="readonly"
					<%if (msg051002.getF02LCMBRK().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
              <TD nowrap width="25%" align="right">% Garantía Efectivo: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMCPE" type="text" disabled="disabled" onKeyPress="enterDecimal()" value="<%= msg051002.getE02LCMCPE().trim()%>" size="17" maxlength="16" readonly="readonly"
					<%if (msg051002.getF02LCMCPE().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%" align="right">Monto Garantía Efectivo: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMCAM" type="text" disabled="disabled" onKeyPress="enterDecimal()" value="<%= msg051002.getE02LCMCAM().trim()%>" size="17" maxlength="16" readonly="readonly"              
					<%if (msg051002.getF02LCMCAM().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
              <TD nowrap width="25%"align="right">Cuenta Garantía Efectivo: </TD>
              <TD nowrap width="25%"><TABLE id="trclear">
                  <TBODY>
                    <TR>
                      <TD rowspan="2">
                      	<INPUT name="E02LCMCCA" type="text" disabled="disabled" value="<%= msg051002.getE02LCMCCA().trim()%>" size="17" maxlength="16" readonly="readonly"
							<%if (msg051002.getF02LCMCCA().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                      </TD>
                  </TBODY>
                </TABLE></TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Tarifa del Cliente: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMTAR" type="text" value="<%= msg051002.getE02LCMTAR().trim()%>" size="2" maxlength="2" readonly="readonly"
					<%if (msg051002.getF02LCMTAR().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0">
			 </TD>
              <TD align="right" width="25%">Nuestros Cargos por Cuenta de: </TD>
              <TD nowrap width="25%" >
                <% 
                	String charges = ""; 
                	switch (msg051002.getE02LCMAOB().charAt(0)) {
                		case 'A': charges = "Aplicante"; break;
                		case 'B': charges = "Beneficiario"; break;
                		default: charges = ""; break;
                	}
                %>
              	<INPUT name="E02LCMAOB" type="TEXT" size="14" readonly="readonly"  value = "<%= charges %>"
					<%if (msg051002.getF02LCMAOB().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"></TD>
            </TR>
            <TR id="trclear">
              <TD align="right" width="25%">Linea de Cr&eacute;dito del Cliente: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMLNR" type="text" onKeyPress="enterInteger()" value="<%= msg051002.getE02LCMLNR().trim()%>" size="10" maxlength="9" readonly="readonly"
					<%if (msg051002.getF02LCMLNR().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                <INPUT name="E02LCMCMN" type="text" onKeyPress="enterInteger()" value="<%= msg051002.getE02LCMCMN().trim()%>" size="4" maxlength="4" readonly="readonly"
					<%if (msg051002.getF02LCMCMN().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
				</TD>
              <TD nowrap width="25%"align="right">Número Referencia ALADI: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMSRF"  type="text" disabled value="<%= msg051002.getE02LCMSRF().trim()%>" size="17" maxlength="16" readonly="readonly"              
					<%if (msg051002.getF02LCMSRF().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Com.Apertura Día Emisión: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMOCI" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMOCI().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMOCI().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
              <TD nowrap width="25%"align="right">Com.Enmienda Día Enmienda: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMACI" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMACI().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					<%if (msg051002.getF02LCMACI().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Lugar de Expiración: </TD>
              <TD nowrap width="25%">
              	<INPUT name="E02LCMPLE" type="text" value="<%= msg051002.getE02LCMPLE().trim()%>" size="29" maxlength="29" readonly="readonly"
					<%if (msg051002.getF02LCMPLE().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"></TD>
			<TD width="25%" align="right" nowrap>Referencia Pre-Aviso: </TD>
			<TD nowrap width="25%">
				<INPUT type="text" name="E02LCMRF1" size="18" maxlength="16" value="<%= msg051002.getE02LCMRF1().trim()%>" readonly
					<%if (msg051002.getF02LCMRF1().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			</TD>
              
            </TR>
			<TR id="trdark">
				<TD width="25%" align="right" nowrap>Cuenta Debito Comisiones: </TD>
              	<TD nowrap width="75%" colspan="3">              
					<INPUT type="text" name="E02LCMSCA" size="17" maxlength="12" value="<%= msg051002.getE02LCMSCA().trim()%>" readonly="readonly"
					<%if (msg051002.getF02LCMSCA().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
				</TD>
			</TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Reglas a Aplicar: </TD>
              <TD nowrap width="75%" colspan="3">              
                <% 
                	String rules = ""; 
                	switch (msg051002.getE02LCMAPR().charAt(0)) {
                		case '1': rules = "UCP Latest Version"; break;
                		case '2': rules = "EUCP Latest Version"; break;
                		case '3': rules = "UCPURR Latest Version"; break;
                		case '4': rules = "EUCPURR Latest Version"; break;
                		case '5': rules = "ISP Latest Version"; break;
                		case '6': rules = "Other"; break;
                		default: rules = ""; break;
                	}
                %>
              	<INPUT name="E02LCMAPR" type="TEXT" size="24" maxlength="24" readonly="readonly"  value = "<%= rules %>"
					<% if (msg051002.getF02LCMAPR().equals("Y")) out.print("id=\"txtchanged\""); %>>              	
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
				<INPUT name="E02LCMST3" type="text" value="<%= msg051002.getE02LCMST3().trim()%>" size="40" maxlength="35" readonly="readonly">
			  </TD>
            </TR>
				<TR id="trdark">
				  <TD align="right">Lugar de&nbsp;Recepci&oacute;n: </TD>
				  <TD nowrap colspan="3"><INPUT name="E02LCMPLR" type="text" value="<%= msg051002.getE02LCMPLR().trim()%>" size="65" maxlength="65" readonly="readonly"
					<%if (msg051002.getF02LCMPLR().equals("Y")) out.print("id=\"txtchanged\"");%>>
				  </TD>
		    </TR>
				<TR id="trclear">
              <TD nowrap width="25%"align="right">Puerto/Aeropuerto de Salida: </TD>
              <TD nowrap width="27%" colspan="3">
              	<INPUT name="E02LCMPOL" type="text" value="<%= msg051002.getE02LCMPOL().trim()%>" size="65" maxlength="65" readonly="readonly"
					<%if (msg051002.getF02LCMPOL().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trdark">
              <TD nowrap width="25%"align="right">Puerto/Aeropuerto de Destino: </TD>
              <TD nowrap width="27%" colspan="3">
              	<INPUT name="E02LCMPOD" type="text" value="<%= msg051002.getE02LCMPOD().trim()%>" size="65" maxlength="65" readonly="readonly"
					<%if (msg051002.getF02LCMPOD().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trclear">
              <TD nowrap width="25%"align="right">Lugar de Entrega: </TD>
              <TD nowrap width="27%" colspan="3">
              	<INPUT name="E02LCMPLD" type="text" value="<%= msg051002.getE02LCMPLD().trim()%>" size="65" maxlength="65" readonly="readonly"
					<%if (msg051002.getF02LCMPLD().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
              </TD>
            </TR>
            <TR id="trdark">
              <TD align="right" nowrap>Credito Disponible Con: </TD>
              <TD align="left" nowrap>
              	<INPUT name="E02LCMAWI" type="text" value="<%= msg051002.getE02LCMAWI().trim()%>" size="14" maxlength="12" readonly="readonly"
					<%if (msg051002.getF02LCMAWI().equals("Y")) out.print("id=\"txtchanged\"");%>>              	            	
              </TD>
              <TD align="right" nowrap>Banco Girado: </TD>
              <TD align="left" nowrap>
              	<INPUT name="E02LCMDWI" type="text" value="<%= msg051002.getE02LCMDWI().trim()%>" size="14" maxlength="12" readonly="readonly"
					<%if (msg051002.getF02LCMDWI().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
               </TD>
            </TR>
            <TR id="trclear">
              <TD align="right" nowrap>Tarifa del Corresponsal: </TD>
              <TD align="left" nowrap>
              	<INPUT name="E02LCMCCT" type="text" value="<%= msg051002.getE02LCMCCT().trim()%>" size="3" maxlength="3" readonly="readonly" 
					<%if (msg051002.getF02LCMCCT().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
              <TD align="right" nowrap>Cargos O/B por Cuenta de: </TD>
              <TD align="left" nowrap>
                <% 
                	String other = ""; 
                	if (msg051002.getE02LCMOBC().trim().equals("A")) {
                		other = "Aplicante";
                	} else {
                		if (msg051002.getE02LCMOBC().trim().equals("B")) {
                			other = "Beneficiario";
                		}	
                	}
                %>
              	<INPUT name="E02LCMOBC" type="TEXT" size="14" readonly="readonly"  value = "<%= other %>"
					<%if (msg051002.getF02LCMOBC().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
			  </TD>
            </TR>
		     <%if(userPO.getID().equals("18")){%>
	            <TR id="trdark">
	              <TD nowrap width="25%"align="right">Tipo de Inter&eacute;s: </TD>
	              <TD nowrap width="27%">
	              	<INPUT name="E02LCMICT" type="text" value="<%= msg051002.getE02LCMICT().trim()%>" size="1" maxlength="1" readonly="readonly"              
						<%if (msg051002.getF02LCMICT().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
	              </TD>
	              <TD nowrap width="27%"align="right">Tasa de Inter&eacute;s: </TD>
	              <TD nowrap width="27%">
	              	<INPUT name="E02LCMIRT" type="text" value="<%= msg051002.getE02LCMIRT().trim()%>" size="9" maxlength="9" readonly="readonly"              
						<%if (msg051002.getF02LCMIRT().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
	              </TD>
	            </TR>
	            <TR id="trclear">
	              <TD nowrap width="25%"align="right">Tabla / Tipo de Tasa Flotante: </TD>
	              <TD nowrap width="27%"><INPUT name="E02LCMFTB" type="text" disabled="disabled" value="<%= msg051002.getE02LCMFTB().trim()%>" size="2" maxlength="2" readonly="readonly">
	                <SELECT name="E02LCMFTY" disabled="disabled">
	                  <OPTION value=" "> </OPTION>
	                  <OPTION value="FP">Tasa Primaria</OPTION>
	                  <OPTION value="FS" <% if(msg051002.getE02LCMOCI().equals("FS")) out.print("selected");%>>Tasa Secundaria</OPTION>
	                </SELECT></TD>
	              <TD nowrap width="27%"align="right">Per&iacute;odo Base Calc.Intereses: </TD>
	              <TD nowrap width="27%"><INPUT name="E02LCMBAS" type="text" disabled="disabled" onKeyPress="enterInteger()" value="<%= msg051002.getE02LCMBAS().trim()%>" size="3" maxlength="3" readonly="readonly"
	               <%if (msg051002.getF02LCMBAS().equals("Y")) out.print("id=\"txtchanged\"");%>>
	              </TD>
	             </TR>
				
	            <TR id="trdark">
					<TD nowrap width="25%" align="right">Condonar Intereses en Cancelacion: </TD>
					<TD nowrap width="25%">
				    	<INPUT name="E02LCMWIF" type="TEXT" size="3" readonly="readonly"  value = "<% if (msg051002.getE02LCMWIF().equals("Y")) { out.print("Si"); } else { out.print("No"); } %>"
					    <%if (msg051002.getF02LCMWIF().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
					</TD>
				    <TD nowrap width="25%" align="right">Cuenta de Repago de Intereses: </TD>
					<TD nowrap width="25%">
				       <INPUT type="text" name="E02LCMIPA" size="17" maxlength="12" value="<%= msg051002.getE02LCMIPA().trim()%>" readonly="readonly"
				       <%if (msg051002.getF02LCMIPA().equals("Y")) out.print("id=\"txtchanged\"");%>>
					</TD>
		        </TR>
	           <%}%> 
          </TBODY>
        </TABLE></TD>
    </TR>
  </TABLE>
  <H4>Aumento / Disminuci&oacute;n del Cr&eacute;dito</H4>
  <TABLE class="tableinfo">
    <TBODY>
      <TR>
        <TD nowrap><TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
            <TBODY>
              <TR id="trdark00">
                <TD nowrap width="16%"align="right">Incremento / Decremento: </TD>
                <TD nowrap width="20%" align="left">
                <% 
                	String action = ""; 
                	if (msg051002.getE02LCMIDF().trim().equals("I")) {
                		action = "Incremento";
                	} else {
                		if (msg051002.getE02LCMIDF().trim().equals("D")) {
                			action = "Decremento";
                		}
                	}
                %>
              	<INPUT name="E02LCMIDF" type="TEXT" size="14" readonly="readonly"  value = "<%= action %>"
					<%if (msg051002.getF02LCMIDF().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
				</TD>
                <TD nowrap width="16%"align="right">Monto: </TD>
                <TD nowrap width="16%">
                	<INPUT name="E02LCMIDA" type="text" onKeyPress="enterDecimal()" value="<%= msg051002.getE02LCMIDA().trim()%>" size="17" maxlength="16" readonly="readonly"
					<%if (msg051002.getF02LCMIDA().equals("Y")) out.print("id=\"txtchanged\"");%>>              	
                </TD>
                <TD nowrap width="15%"align="right">Saldo Actual: </TD>
                <TD nowrap width="15%">
                    <INPUT name="E02LCMMEB" type="text"  value="<%= msg051002.getE02LCMMEB().trim()%>" size="17" maxlength="16" readonly>
                </TD>
              </TR>
            </TBODY>
        </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>

</FORM>
</BODY>
</HTML>
