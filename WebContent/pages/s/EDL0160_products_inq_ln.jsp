<html>
<head>
<title>Productos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="lnProdInq" class="datapro.eibs.beans.ESD071110Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>

</SCRIPT>
<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%> 

<h3 align="center">Consulta de Producto de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, EDL0160" width="32" height="32"></h3>
<hr size="4">

<form>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Clasificaci&oacute;n del Producto</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Tipo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E10APCTYP" size="6" maxlength="4" value="<%= lnProdInq.getE10APCTYP().trim()%>">
              <input type="text" readonly name="E10TYPDES" size="37" maxlength="35" value="<%= lnProdInq.getE10TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E10APCCDE" size="6" maxlength="4" value="<%= lnProdInq.getE10APCCDE().trim()%>">
              <input type="text" readonly name="E10DESCRI" size="37" maxlength="63" value="<%= lnProdInq.getE10DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E10MERCAD" size="17" maxlength="15" value="<%= lnProdInq.getE10MERCAD().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Informaci&oacute;n Contable</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Banco: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APCBNK" size="4" maxlength="2" value="<%= lnProdInq.getE10APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Moneda: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APCCCY" size="5" maxlength="3" value="<%= lnProdInq.getE10APCCCY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APCGLN" size="18" maxlength="16" value="<%= lnProdInq.getE10APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Descripci&oacute;n de la Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10GLMDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10GLMDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Informaci&oacute;n Financiera</h4>

  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="41%"> 
              <div align="right">Tipo de Prest&aacute;mo : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCFRN" size="3" maxlength="1" value="<%= lnProdInq.getE10APCFRN().trim()%>">
              <input type="text" readonly name="E10APCRES2" size="35" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCFRN().equals("A")) out.print("Arrendamiento Financiero");
			    		else if (lnProdInq.getE10APCFRN().equals("C")) out.print("Prestamos de Consumo");
						else if (lnProdInq.getE10APCFRN().equals("D")) out.print("Prestamos Sobre Saldo Disoluto");
						else if (lnProdInq.getE10APCFRN().equals("L")) out.print("Prestamos Regular");
						else if (lnProdInq.getE10APCFRN().equals("H")) out.print("Hipotecarios");
						else if (lnProdInq.getE10APCFRN().equals("P")) out.print("Politica Habitacional");
						else if (lnProdInq.getE10APCFRN().equals("G")) out.print("Factoring[Descuento documentos]");
						else if (lnProdInq.getE10APCFRN().equals("V")) out.print("Valores al Cobro");
						else if (lnProdInq.getE10APCFRN().equals("O")) out.print("Para Control de Sobregiros");
						else if (lnProdInq.getE10APCFRN().equals("R")) out.print("Prestamos para Refinanciar Otro");
						else if (lnProdInq.getE10APCFRN().equals("I")) out.print("Prestamo Credilinea");
						else out.print("Proyectos de Constructor");%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">C&aacute;lculo de Inter&eacute;s : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCMCI" size="3" maxlength="1" value="<%= lnProdInq.getE10APCMCI().trim()%>">
              <input type="text" readonly name="E10APCRES22" size="35" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCMCI().equals("1")) out.print("Calculo sobre Capital Vigente");
			    		else if (lnProdInq.getE10APCMCI().equals("2")) out.print("Calculo sobre Capital Original");
						else if (lnProdInq.getE10APCMCI().equals("3")) out.print("Calculo sobre Capital Vigente menos Capital Vencido");
						else if (lnProdInq.getE10APCMCI().equals("N")) out.print("No Calcula Intereses");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="41%"> 
              <div align="right">C&aacute;lculo de Mora : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCMCP" size="3" maxlength="1" value="<%= lnProdInq.getE10APCMCP().trim()%>">
              <input type="text" readonly name="E10APCRES222" size="35" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCMCP().equals("1")) out.print("Calculo sobre Capital Vencido");
			    		else if (lnProdInq.getE10APCMCP().equals("2")) out.print("Calculo sobre Capital Original");
						else if (lnProdInq.getE10APCMCP().equals("3")) out.print("Calculo sobre Capital Vigente");
						else if (lnProdInq.getE10APCMCP().equals("4")) out.print("Calculo sobre Capital Vencido mas Intereses Vencidos");
						else if (lnProdInq.getE10APCMCI().equals("N")) out.print("No Calcula Intereses");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">Ciclo de Pago Principal :</div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCPPD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCPPD().trim()%>">
              <input type="text" readonly name="E10APCRES2222" size="45" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCPPD().equals("nnn")) out.print("Pagos Ciclicos cada nnn dias [030,090,180]");
			    		else if (lnProdInq.getE10APCPPD().equals("MAT")) out.print("Pago al Vencimiento del Prestamo");
						else if (lnProdInq.getE10APCPPD().equals("SCH")) out.print("Cuotas Irregulares [Creadas Manualmente]");
						else if (lnProdInq.getE10APCPPD().equals("SC1")) out.print("Cuotas Niveladas y Pagos Extraordinarios");
						else if (lnProdInq.getE10APCPPD().equals("SC2")) out.print("Doble Cuota Nivelada según % de Capital");
						else if (lnProdInq.getE10APCPPD().equals("SC3")) out.print("Cuota Nivelada para Prestamo de Consumo");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="41%"> 
              <div align="right">Ciclo de Pago de Intereses :</div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCIPD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCIPD().trim()%>">
              <input type="text" readonly name="E10APCRES22222" size="45" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCIPD().equals("nnn")) out.print("Pagos Ciclicos cada nnn dias [030,090,180]");
			    		else if (lnProdInq.getE10APCIPD().equals("MAT")) out.print("Pago al Vencimiento del Prestamo");
						else if (lnProdInq.getE10APCIPD().equals("SCH")) out.print("Cuotas Irregulares [Creadas Manualmente]");
						else if (lnProdInq.getE10APCIPD().equals("SC1")) out.print("Cuotas Niveladas y Pagos Extraordinarios");
						else if (lnProdInq.getE10APCIPD().equals("SC2")) out.print("Doble Cuota Nivelada según % de Capital");
						else if (lnProdInq.getE10APCIPD().equals("SC3")) out.print("Cuota Nivelada para Prestamo de Consumo");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">Tabla de Cargos : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCTLN" size="4" maxlength="2" value="<%= lnProdInq.getE10APCTLN().trim()%>">
              <input type="text" readonly name="E10TBLDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10TBLDSC().trim()%>">
			  <%--<a href="javascript:showLNServCharge('<%= lnProdInq.getE10APCBNK().trim()%>','<%= lnProdInq.getE10APCTYP().trim()%>','<%= lnProdInq.getE10APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>--%> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="41%"> 
              <div align="right">Tabla de Cargos / Rangos : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCCDT" size="3" maxlength="1" value="<%= lnProdInq.getE10APCCDT().trim()%>">
              <%--<a href="javascript:showCDRates('<%= lnProdInq.getE10APCCCY().trim()%>','<%= lnProdInq.getE10APCCDT().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>--%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">Tipo de Cliente : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCRES" size="15" maxlength="1"  
			   value="<% if (lnProdInq.getE10APCRES().equals("1")) out.print("Residente");
			    		else if (lnProdInq.getE10APCRES().equals("2")) out.print("No Residente");
						else out.print("N/A");%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="41%"> 
              <div align="right">Tabla de Tasa Flotante : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCFRT" size="4" maxlength="2" value="<%= lnProdInq.getE10APCFRT().trim()%>">
              <input type="text" readonly name="E10FLTDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10FLTDSC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">Tipo de Tasa Flotante : </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCFTY" size="15" maxlength="2" 
				value="<% if (lnProdInq.getE10APCFTY().equals("FP")) out.print("Primaria");
			    		else if (lnProdInq.getE10APCFTY().equals("FS")) out.print("Secundaria");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trdark">
            <td width="41%"> 
              <div align="right">Ciclo Cambio de Tasa :</div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCRRD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCRRD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="41%"> 
              <div align="right">Tabla de Documentos: </div>
            </td>
            <td width="59%"> 
              <input type="text" readonly name="E10APCFTF" size="4" maxlength="2" value="<%= lnProdInq.getE10APCFTF().trim()%>">
              <input type="text" readonly name="E10DOCDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10DOCDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Direcciones de Acceso</h4>
  <table class="tableinfo">
    <tr> 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">Audio : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APEAUD" size="82" maxlength="80" value="<%= lnProdInq.getE10APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APEVID" size="82" maxlength="80" value="<%= lnProdInq.getE10APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E10APEHTM" size="82" maxlength="80" value="<%= lnProdInq.getE10APEHTM().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
     
  <p align="center">&nbsp;</p>
 
</form>
</body>
</html>
