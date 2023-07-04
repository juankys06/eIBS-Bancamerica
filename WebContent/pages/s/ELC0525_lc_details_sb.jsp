<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Letter of Credit Details</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="msg040002" class="datapro.eibs.beans.ELC040002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">

	builtNewMenu(lc_apr_standby);
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
<H3 align="center"><%= (userPO.getPurpose().equals("NEW") ? "Apertura" : "Mantenimiento") %> de Carta de Credito Stand By 
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"
ALT="ELC0525_lc_details_sb.jsp"></H3> 

<HR size="4">
<FORM>
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tableinfo" id="trclear">
					<TR id="trdark">
						<TD nowrap width="16%" align="right"><B>Banco : </B></TD>
						<TD nowrap width="20%">
						<INPUT type="text" name="E02LCMBNK" size="4" maxlength="2" value="<%=msg040002.getE02LCMBNK().trim()%>" readonly></TD>
						<TD nowrap width="16%" align="right"><B>Número de Carta de Credito : </B></TD>
						<TD nowrap width="16%">
						<DIV align="left"><B> <%if (msg040002.getE02LCMACC().trim().equals("999999999999"))
				{%> <INPUT type="text" size="12" maxlength="12" value="Nuevo"
							readonly> <INPUT type="hidden" name="E02LCMACC"
							value="<%=msg040002.getE02LCMACC().trim()%>" readonly> <%}
				else
				{%> <INPUT type="text" name="E02LCMACC" size="13" maxlength="12"
							value="<%=(msg040002.getE02LCMACC().trim().equals("0") ? userPO.getIdentifier() : msg040002.getE02LCMACC().trim() )%>" readonly> <%}%> </B></DIV>
						</TD>
					</TR>
					<TR>
						<TD nowrap width="16%" align="right"><B>Nuestra Referencia : </B>
						</TD>
						<TD nowrap width="20%" align="left"><INPUT type="text" name="E02LCMORF" size="20"
							maxlength="16" value="<%=msg040002.getE02LCMORF().trim()%>" readonly> 
						</TD>
						<TD nowrap width="16%" align="right"><B>Producto : </B></TD>
						<TD nowrap width="16%" align="left"><B> <INPUT type="text" name="E02LCMPRO" size="4"
							maxlength="4" value="<%=msg040002.getE02LCMPRO().trim()%>" readonly>
						</B></TD>

					</TR>
					<TR id="trdark">
						<TD width="16%" align="right" nowrap><B>Referencia del Otro Banco : </B></TD>
						<TD nowrap width="16%"><B> <INPUT type="text" name="E02LCMTRF"
							size="20" maxlength="16" value="<%=msg040002.getE02LCMTRF().trim()%>" readonly>
						</B></TD>

						<TD nowrap width="16%" align="right"><B>Descripcion del Producto : </B></TD>
						<TD nowrap width="16%" align="left"><B><INPUT type="text" name="E02DSCPRO"
							size="40" maxlength="35" value="<%=userPO.getHeader2().trim()%>"
							readonly> </B></TD>

					</TR>
				</TABLE>

<H4>Información de la Carta de Crédito</H4> 
  <TABLE class="tableinfo">
    <TR > 
      <TD nowrap> 
        <TABLE cellpadding="2" cellspacing="0" width="100%" border="0">
           <TBODY><TR id="trdark"> 
            <TD nowrap width="25%" align="right">Fecha de Emisión: </TD>
            <TD nowrap width="25%"> 
              <INPUT name="E02LCMID1" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMID1().trim().equals("0") ? "":msg040002.getE02LCMID1().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMID2" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMID2().trim().equals("0") ? "":msg040002.getE02LCMID2().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMID3" type="text" onKeyPress="enterInteger()" value='<%  if(msg040002.getE02LCMID3().length() < 2 && !msg040002.getE02LCMID3().equals("0"))
							out.print("0");
					out.print((msg040002.getE02LCMID3().trim().equals("0") ? "":msg040002.getE02LCMID3().trim()));%>' size="2" maxlength="2" readonly="readonly">
              
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">  			</TD>
            <TD nowrap width="25%" align="right">Fecha de Expiración: </TD>
            <TD nowrap width="27%"> 
              <INPUT name="E02LCMEX1" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMEX1().trim().equals("0") ? "":msg040002.getE02LCMEX1().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMEX2" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMEX2().trim().equals("0") ? "":msg040002.getE02LCMEX2().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMEX3" type="text" onKeyPress="enterInteger()" value='<%  if(msg040002.getE02LCMEX3().length() < 2 && !msg040002.getE02LCMEX3().equals("0"))
							out.print("0");
					out.print((msg040002.getE02LCMEX3().trim().equals("0") ? "":msg040002.getE02LCMEX3().trim()));%>' size="2" maxlength="2" readonly="readonly">
               
            <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
          </TR>   
           <TR id="trclear"> 
            <TD nowrap width="25%" align="right">Fecha de Aviso/Confirmación: </TD><TD nowrap width="25%"> 
              <INPUT name="E02LCMCN1" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMCN1().trim().equals("0") ? "":msg040002.getE02LCMCN1().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMCN2" type="text" onKeyPress="enterInteger()" value='<%= (msg040002.getE02LCMCN2().trim().equals("0") ? "":msg040002.getE02LCMCN2().trim())%>' size="2" maxlength="2" readonly="readonly">
              <INPUT name="E02LCMCN3" type="text" onKeyPress="enterInteger()" value='<%  if(msg040002.getE02LCMCN3().length() < 2 && !msg040002.getE02LCMCN3().equals("0"))
							out.print("0");
					out.print((msg040002.getE02LCMCN3().trim().equals("0") ? "":msg040002.getE02LCMCN3().trim()));%>' size="2" maxlength="2" readonly="readonly">
               
            </TD>
            
            <TD nowrap width="25%" align="right">Centro de Costo: </TD><TD nowrap width="27%"> 
				<INPUT type="text" name="E02LCMCCN" size="9" maxlength="8" value="<%= msg040002.getE02LCMCCN().trim()%>" readonly> 
				
            </TD>
          </TR>         
           <TR id="trdark"> 
           <!-- <TD nowrap width="25%" align="right">Clausula de Aproximación:</TD>
            <TD nowrap width="25%"> 
                	<INPUT type="radio" name="E02LCMABC" value="Y" <% if(msg040002.getE02LCMABC().equals("Y")) out.print("checked");%>>Si
                	<INPUT type="radio" name="E02LCMABC" value="N" <% if(msg040002.getE02LCMABC().equals("N")) out.print("checked");%>>No
    	        &nbsp; &nbsp; &nbsp; Porcentaje: <INPUT type="text" name="E02LCMABP" size="3" maxlength="3" value="<%= msg040002.getE02LCMABP().trim()%>" onKeyPress="enterInteger()"></TD>-->
			<TD nowrap width="25%" align="right">Oficina: </TD>
            <TD nowrap width="25%">
              <INPUT type="text" name="E02LCMBRN" size="4" maxlength="3" value="<%= msg040002.getE02LCMBRN().trim()%>" readonly>
            </TD>
            <TD nowrap width="25%" align="right">Monto del Crédito: </TD>
            <TD nowrap width="27%">
              <INPUT type="text" name="E02LCMCCY" size="4" maxlength="3" value="<%= msg040002.getE02LCMCCY().trim()%>" readonly>
              <INPUT type="text" name="E02LCMOAM" size="17" maxlength="16" value="<%= msg040002.getE02LCMOAM().trim()%>" 
			  <%= ( userPO.getPurpose().equals("N") ? "onKeyPress=\"enterDecimal()\"" : "disabled=\"disabled\"" ) %> > 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
            </TD>
          </TR>           
          <TR id="trclear">
            <TD nowrap width="25%" align="right">Confirmada: </TD>
            <TD nowrap width="25%" align="left">
              <INPUT name="E02LCMCNF" type="radio" disabled value="Y" readonly="readonly" <% if(msg040002.getE02LCMCNF().equals("Y")) out.print("checked");%>>
              Si
              <INPUT name="E02LCMCNF" type="radio" disabled value="N" readonly="readonly" <% if(msg040002.getE02LCMCNF().equals("N")) out.print("checked");%>>
              No </TD> 
            <TD nowrap width="25%" align="right">Agregar Confirmacion: </TD>
            <TD nowrap width="25%" align="left">
                <% 
                	String test = msg040002.getE02LCMCNO().trim();
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
              	<INPUT name="E02LCMCNO" type="TEXT" size="28" readonly="readonly"  value = "<%= confirm %>">
			</TD>
          </TR>   
          <TR id="trdark">
            <TD nowrap align="right">Tenor: </TD>
            <TD nowrap><SELECT name="E02LCMTNR" disabled="disabled">
                <OPTION value="S" <% if(msg040002.getE02LCMTNR().equals("S")) out.print("selected");%>>Pago</OPTION>
                <OPTION value="A" <% if(msg040002.getE02LCMTNR().equals("A")) out.print("selected");%>>Aceptaci&oacute;n</OPTION>
                <OPTION value="M" <% if(msg040002.getE02LCMTNR().equals("M")) out.print("selected");%>>Pago Mixto</OPTION>
                <OPTION value="D" <% if(msg040002.getE02LCMTNR().equals("D")) out.print("selected");%>>Pago Diferido</OPTION>
                <OPTION value="N" <% if(msg040002.getE02LCMTNR().equals("N")) out.print("selected");%>>Negociaci&oacute;n</OPTION>
              </SELECT>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD> 
            <TD nowrap width="25%" align="right">% Garantía Efectivo: </TD><TD nowrap width="27%"> 
  			<INPUT name="E02LCMCPE" type="text" onKeyPress="enterDecimal()" value="<%= msg040002.getE02LCMCPE().trim()%>" size="17" maxlength="16" readonly="readonly"></TD>
          </TR>   
          <TR id="trclear"> 
            <TD nowrap width="25%" align="right">Monto Garantía Efectivo: </TD>
            <TD nowrap width="25%"> 
				<INPUT name="E02LCMCAM" type="text" onKeyPress="enterDecimal()" value="<%= msg040002.getE02LCMCAM().trim()%>" size="17" maxlength="16" readonly="readonly">            </TD>
            <TD nowrap width="25%" align="right">Cuenta Garantía Efectivo: </TD><TD nowrap width="27%">
					<TABLE id="trclear">
						<TBODY>
							<TR>
								<TD rowspan="2"><INPUT name="E02LCMCCA" type="text" value="<%= msg040002.getE02LCMCCA().trim()%>" size="17" maxlength="16" readonly="readonly"></TD>
								<!--<TD>Cuenta<BR>Contable</TD>
								<TD rowspan="2"><A href="javascript:GetLedger('E02LCMCCA',document.forms[0].E02LCMBNK.value,'','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>
								<TD>Cuenta<BR>Cliente</TD>
								<TD rowspan="2"><A href="javascript: GetAccByClient('E02LCMCCA','','RT','','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A></TD>-->
						</TR></TBODY>
					</TABLE>
					</TD>
          </TR>
				<TR id="trdark"> 
            <TD nowrap width="25%" align="right">Tarifa del Cliente: </TD>
            <TD nowrap width="25%"> 
				<INPUT name="E02LCMTAR" type="text" value="<%= msg040002.getE02LCMTAR().trim()%>" size="2" maxlength="2" readonly="readonly">
				 
            <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
            <TD nowrap align="right">Nuestros Cargos por Cuenta de: </TD>
            <TD nowrap><SELECT name="E02LCMAOB" disabled>
                <OPTION value=" "></OPTION>
                <OPTION value="A" <% if(msg040002.getE02LCMAOB().equals("A")) out.print("selected");%>>Aplicante</OPTION>
                <OPTION value="B" <% if(msg040002.getE02LCMAOB().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
              </SELECT>
                <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
			 </TR>   
          <TR id="trclear">
            <TD nowrap align="right">Linea  de Cr&eacute;dito del Cliente: </TD>
            <TD nowrap><INPUT name="E02LCMLNR" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMLNR().trim()%>" size="10" maxlength="9" readonly="readonly">
                
                <INPUT name="E02LCMCMN" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMCMN().trim()%>" size="4" maxlength="4" readonly="readonly">
				</TD> 
            <TD nowrap width="25%" align="right">Reglas a Aplicar: </TD><TD nowrap width="27%">
            	<SELECT name="E02LCMAPR" disabled>
                	<OPTION value="9">NONE</OPTION>
                	<OPTION value="7" <% if(msg040002.getE02LCMAPR().equals("1")) out.print("selected");%>>IIC URCG</OPTION>
                	<OPTION value="8" <% if(msg040002.getE02LCMAPR().equals("2")) out.print("selected");%>>IIC URDG</OPTION>
                	<OPTION value="6" <% if(msg040002.getE02LCMAPR().equals("3")) out.print("selected");%>>Other</OPTION>
              	</SELECT>  
  			<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"><INPUT name="E02LCMST3" type="text" value="<%= msg040002.getE02LCMST3().trim()%>"
						size="40" maxlength="35" readonly="readonly"></TD>
          </TR>        
          <TR id="trdark"> 
            <TD nowrap width="25%" align="right">Com.Apertura Día Emisión: </TD>
            <TD nowrap width="25%">
                	<INPUT name="E02LCMOCI" type="radio" disabled value="Y" readonly="readonly" <% if(msg040002.getE02LCMOCI().equals("Y")) out.print("checked");%>>Si
                	<INPUT name="E02LCMOCI" type="radio" disabled value="N" readonly="readonly" <% if(msg040002.getE02LCMOCI().equals("N")) out.print("checked");%>>No  
                	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
            <TD nowrap width="25%" align="right">Com.Enmienda Día Enmienda: </TD><TD nowrap width="27%">
                	<INPUT name="E02LCMACI" type="radio" disabled value="Y" readonly="readonly" <% if(msg040002.getE02LCMACI().equals("Y")) out.print("checked");%>>Si
                	<INPUT name="E02LCMACI" type="radio" disabled value="N" readonly="readonly" <% if(msg040002.getE02LCMACI().equals("N")) out.print("checked");%>>No
  			        <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"></TD>
          </TR>
			<TR id="trclear">
			  <TD align="right" nowrap>Tarifa del Corresponsal: </TD>
			  <TD align="left" nowrap><INPUT name="E02LCMCCT" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMCCT().trim()%>" size="3" maxlength="3" readonly="readonly"> 
			  </TD>
				<TD align="right" nowrap>Cargos O/B por Cuenta de: </TD>
				<TD align="left" nowrap><SELECT name="E02LCMOBC" disabled>
                    <OPTION value=" "></OPTION>
                    <OPTION value="A" <% if(msg040002.getE02LCMOBC().equals("A")) out.print("selected");%>>Aplicante</OPTION>
                    <OPTION value="B" <% if(msg040002.getE02LCMOBC().equals("B")) out.print("selected");%>>Beneficiario</OPTION>
                  </SELECT>                </TD>
			</TR>
			<TR id="trdark">
				  <TD nowrap width="25%" align="right">Lugar de Expiraci&oacute;n: </TD>
				  <TD nowrap width="25%"><INPUT name="E02LCMPLE" type="text" value="<%= msg040002.getE02LCMPLE().trim()%>" size="31" maxlength="29" readonly="readonly"></TD>
					<TD nowrap width="25%" align="right">&nbsp;</TD>
					<TD nowrap width="27%">&nbsp;</TD>
			</TR>
			<TR id="trclear">
				  <TD nowrap width="25%" align="right">Tipo de Inter&eacute;s: </TD>
				  <TD nowrap width="25%"><INPUT name="E02LCMICT" type="text" value="<%= msg040002.getE02LCMICT().trim()%>" size="1" maxlength="1" readonly="readonly"></TD>
				  <TD nowrap width="25%" align="right">Tasa de Inter&eacute;s:</TD>
				  <TD nowrap width="27%"><INPUT name="E02LCMIRT" type="text" value="<%= msg040002.getE02LCMIRT().trim()%>" size="9" maxlength="9" readonly="readonly"></TD>
			</TR>
			<TR id="trdark">
				  <TD nowrap width="25%" align="right">Tabla / Tipo de Tasa Flotante: </TD>
				  <TD nowrap width="25%"><INPUT name="E02LCMFTB" type="text" value="<%= msg040002.getE02LCMFTB().trim()%>" size="2" maxlength="2" readonly="readonly">
                      <SELECT name="E02LCMFTY" disabled="disabled">
                        <OPTION value=" "> </OPTION>
                        <OPTION value="FP">Tasa Primaria</OPTION>
                        <OPTION value="FS" <% if(msg040002.getE02LCMFTY().equals("FS")) out.print("selected");%>>Tasa Secundaria</OPTION>
                    </SELECT></TD>
				  <TD nowrap width="25%" align="right">Per&iacute;odo Base Calc.Intereses: </TD>
				  <TD nowrap width="27%"><INPUT name="E02LCMBAS" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMBAS().trim()%>" size="3" maxlength="3" readonly="readonly"></TD>
		    </TR>
		
		    <TR id="trclear">
					<TD nowrap width="25%" align="right">Ciclo de Pago de Intereses: </TD>
					<TD nowrap width="25%" align="left">
                       <INPUT type="text" name="E02LCMIPC" size="3" maxlength="3" value="<%= msg040002.getE02LCMIPC().trim()%>" onKeyPress="enterInteger()" readonly="readonly"> 
                    </TD>
					<TD nowrap width="25%" align="right">Fecha Prox.Pago Intereses: </TD>
					<TD nowrap width="25%">
                       <INPUT type="text" name="E02LCMNP1" size="2" maxlength="2" value='<%= (msg040002.getE02LCMNP1().trim().equals("0") ? "":msg040002.getE02LCMNP1().trim())%>' onKeyPress="enterInteger()" readonly="readonly">
                       <INPUT type="text" name="E02LCMNP2" size="2" maxlength="2" value='<%= (msg040002.getE02LCMNP2().trim().equals("0") ? "":msg040002.getE02LCMNP2().trim())%>' onKeyPress="enterInteger()" readonly="readonly">
                       <INPUT type="text" name="E02LCMNP3" size="2" maxlength="2" value='<% if(msg040002.getE02LCMNP3().length() < 2 && !msg040002.getE02LCMNP3().equals("0"))
							out.print("0");
				        	out.print((msg040002.getE02LCMNP3().trim().equals("0") ? "":msg040002.getE02LCMNP3().trim()));%>' onKeyPress="enterInteger()" readonly="readonly">
                    </TD>	
		    </TR>
				
	        <TR id="trdark">
					<TD nowrap width="25%" align="right">Condonar Intereses en Cancelacion: </TD>
					<TD nowrap width="25%">
					   <INPUT type="radio" name="E02LCMWIF" disabled value="Y" readonly="readonly" <% if(msg040002.getE02LCMWIF().equals("Y")) out.print("checked");%>>Si
                	   <INPUT type="radio" name="E02LCMWIF" disabled value="N" readonly="readonly" <% if(msg040002.getE02LCMWIF().equals("N")) out.print("checked");%>>No  
					</TD>
				    <TD nowrap width="25%" align="right">Cuenta de Repago de Intereses: </TD>
					<TD nowrap width="25%">
				       <INPUT type="text" name="E02LCMIPA" size="17" maxlength="12" value="<%= msg040002.getE02LCMIPA().trim()%>" readonly="readonly">
					</TD>
		     
			</TR>
			<TR id="trclear">
				  <TD align="right" nowrap>Renovacion Automatica: </TD>
				  <TD align="left" nowrap>
				    <INPUT name="E02LCMEVF" type="radio" disabled value="Y" readonly="readonly" <% if(!msg040002.getE02LCMEVF().equals("N")) out.print("checked");%>>
				    Si
				    <INPUT name="E02LCMEVF" type="radio" disabled value="N" readonly="readonly" <% if( msg040002.getE02LCMEVF().equals("N")) out.print("checked");%>>
				    No 
				  </TD>
				  <TD nowrap align="right">&nbsp;</TD>
				  <TD nowrap>&nbsp;</TD>
			</TR>
			<TR id="trdark">
				  <TD align="right" nowrap>Dias Limite Aviso No Renovacion: </TD>
				  <TD align="left" nowrap><INPUT name="E02LCMNRD" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMNRD().trim()%>" size="3" maxlength="3" readonly="readonly"></TD>
				  <TD align="right" nowrap>Periodo de Prorroga / Renovacion: </TD>
				  <TD align="left" nowrap><INPUT name="E02LCMRDY" type="text" onKeyPress="enterInteger()" value="<%= msg040002.getE02LCMRDY().trim()%>" size="3" maxlength="3" readonly="readonly">
				<SELECT name="E02LCMFL1" disabled>
                    <OPTION value="D" <% if(msg040002.getE02LCMFL1().equals("D")) out.print("selected");%>>Dias</OPTION>
                    <OPTION value="M" <% if(msg040002.getE02LCMFL1().equals("M")) out.print("selected");%>>Meses</OPTION>
                    <OPTION value="Y" <% if(msg040002.getE02LCMFL1().equals("Y")) out.print("selected");%>>Anos</OPTION>
                  </SELECT></TD>
			</TR>
			<TR id="trclear">
				  <TD align="right" nowrap>Generar Swift: </TD>
				  <TD align="left" nowrap>
				    <INPUT name="E02LCMSWF" type="radio" disabled value="Y" readonly="readonly" <% if(!msg040002.getE02LCMSWF().equals("N")) out.print("checked");%>>
				    Si
				    <INPUT name="E02LCMSWF" type="radio" disabled value="N" readonly="readonly" <% if( msg040002.getE02LCMSWF().equals("N")) out.print("checked");%>>
				    No </TD>
				  <TD align="right" nowrap>Tipo de Mensaje Swift: </TD>
				  <TD align="left" nowrap><SELECT name="E02LCMSMT" disabled>
                      <OPTION value="760" <% if(msg040002.getE02LCMSMT().equals("760")) out.print("selected");%>>MT-760</OPTION>
                      <OPTION value="700" <% if(msg040002.getE02LCMSMT().equals("700")) out.print("selected");%>>MT-700</OPTION>
                  </SELECT></TD>
			</TR>
									
			<TR>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="25%"></TD>
					<TD nowrap width="27%"></TD>
			</TR>
	    </TBODY></TABLE>
      </TD>
    </TR>
  </TABLE>  
  <H4>Aumento / Disminuci&oacute;n del Cr&eacute;dito</H4>
  <TABLE class="tableinfo">
    <TBODY>
      <TR>
        <TD nowrap><TABLE cellspacing="0" cellpadding="2" width="100%" border="0"
				class="tbhead">
            <TBODY>
              <TR id="trdark">
                <TD nowrap width="16%"align="right">Incremento / Decremento: </TD>
                <TD nowrap width="20%" align="left"><SELECT name="E02LCMIDF" disabled>
                  <OPTION value=""> </OPTION>
                  <OPTION value="I" <% if(msg040002.getE02LCMIDF().equals("I")) out.print("selected");%>>Incremento</OPTION>
                  <OPTION value="D" <% if(msg040002.getE02LCMIDF().equals("D")) out.print("selected");%>>Decremento</OPTION>
                </SELECT></TD>
                <TD nowrap width="16%"align="right">Monto: </TD>
                <TD nowrap width="16%"><INPUT name="E02LCMIDA" type="text" value="<%= msg040002.getE02LCMIDA().trim()%>" size="17" maxlength="16" readonly></TD>
                <TD nowrap width="15%"align="right">Saldo Actual: </TD>
                <TD nowrap width="15%"><INPUT name="E02LCMMEB" type="text"  value="<%= msg040002.getE02LCMMEB().trim()%>" size="17" maxlength="16" readonly></TD>
              </TR>
            </TBODY>
        </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <BR>
</BODY>
</HTML>
