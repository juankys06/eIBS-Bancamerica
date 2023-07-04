<html>
<head>
<title>Consulta de Productos de Cartas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcProdInq" class="datapro.eibs.beans.ESD071104Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(lc_i_opt);

 
</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Consulta de Producto de Cartas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_inq_products.jsp, ELC0450"></h3>
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
              <input type="text" readonly name="E04APCTYP" size="6" maxlength="4" value="<%= lcProdInq.getE04APCTYP().trim()%>">
              <input type="text" readonly name="E04TYPDES" size="37" maxlength="35" value="<%= lcProdInq.getE04TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E04APCCDE" size="6" maxlength="4" value="<%= lcProdInq.getE04APCCDE().trim()%>">
              <input type="text" readonly name="E04DESCRI" size="65" maxlength="63" value="<%= lcProdInq.getE04DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E04MERCAD" size="17" maxlength="15" value="<%= lcProdInq.getE04MERCAD().trim()%>">
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
              <input type="text" readonly name="E04APCBNK" size="4" maxlength="2" value="<%= lcProdInq.getE04APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Moneda: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04APCCCY" size="5" maxlength="3" value="<%= lcProdInq.getE04APCCCY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04APCGLN" size="18" maxlength="16" value="<%= lcProdInq.getE04APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Descripci&oacute;n de la Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04GLMDSC" size="37" maxlength="35" value="<%= lcProdInq.getE04GLMDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Informaci&oacute;n Financiera</h4>

  <table  class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">N&uacute;mero de Tarifa: </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCTLN" size="3" maxlength="2" value="<%= lcProdInq.getE04APCTLN().trim()%>">
              <input type="text" readonly name="E04TLNDSC" size="30" maxlength="25" value="<%= lcProdInq.getE04TLNDSC().trim()%>">
              <a href="javascript:showLCServCharge('<%= lcProdInq.getE04APCBNK().trim()%>','<%= lcProdInq.getE04APCTYP().trim()%>','<%= lcProdInq.getE04APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" width="18" height="22" ></a> 
            </td>                                   
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Carta de Cr&eacute;dito : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCAMO" size="3" maxlength="2" value="<%= lcProdInq.getE04APCAMO().trim()%>">
              <input type="text" readonly name="E04APCAMO" size="10" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCAMO().equals("I")) out.print("Importaci�n");
			    		else if (lcProdInq.getE04APCAMO().equals("O")) out.print("Exportaci�n");
						else if (lcProdInq.getE04APCAMO().equals("S")) out.print("Garant�a");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Confirmada :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCICT" size="3" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCICT().equals("Y")) out.print("S�");
			    		else if (lcProdInq.getE04APCICT().equals("N")) out.print("No");
						%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Comisiones por Cuenta de :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCFRN122" size="3" maxlength="2" value="<%= lcProdInq.getE04APCITP().trim()%>">
              <input type="text" readonly name="E04APCITP" size="15" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCITP().equals("B")) out.print("Beneficiario");
			    		else if (lcProdInq.getE04APCITP().equals("A")) out.print("Aplicante");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tenor :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCFRN12" size="3" maxlength="2" value="<%= lcProdInq.getE04APCCDT().trim()%>">
              <input type="text" readonly name="E04APCCDT" size="15" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCCDT().equals("S")) out.print("A la vista");
			    		else if (lcProdInq.getE04APCCDT().equals("T")) out.print("T�rmino");
						else if (lcProdInq.getE04APCCDT().equals("M")) out.print("Mixto");
						else if (lcProdInq.getE04APCCDT().equals("D")) out.print("Diferido");
						%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Embarques Parciales :</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E04APCMCI" size="3" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCMCI().equals("Y")) out.print("S�");
			    		else if (lcProdInq.getE04APCMCI().equals("N")) out.print("No");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Transbordo :</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E04APCMCP" size="3" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCMCP().equals("Y")) out.print("S�");
			    		else if (lcProdInq.getE04APCMCP().equals("N")) out.print("No");
						%>">
            </td>
          </tr>
          <tr id="trclear">
            <td width="45%">
              <div align="right">Provisi&oacute;n de Intereses :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCFRN1" size="3" maxlength="2" value="<%= lcProdInq.getE04APCFRN().trim()%>">
              <input type="text" readonly name="E04APCFRN" size="35" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCFRN().equals("1")) out.print("Calculo sobre Capital Vigente");
			    		else if (lcProdInq.getE04APCFRN().equals("2")) out.print("Calculo sobre Capital Original");
						else if (lcProdInq.getE04APCFRN().equals("3")) out.print("Calculo sobre Capital Vigente menos Capital Vencido");
						else if (lcProdInq.getE04APCFRN().equals("N")) out.print("No Calcula Intereses");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%">
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E04APCFRN13" size="3" maxlength="2" value="<%= lcProdInq.getE04APCRES().trim()%>">
              <input type="text" readonly name="E04APCRES" size="35" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCRES().equals("1")) out.print("Calculo sobre Capital Vigente");
			    		else if (lcProdInq.getE04APCRES().equals("2")) out.print("Calculo sobre Capital Original");
						else if (lcProdInq.getE04APCRES().equals("3")) out.print("Calculo sobre Capital Vigente menos Capital Vencido");
						else if (lcProdInq.getE04APCRES().equals("N")) out.print("No Calcula Intereses");
						else out.print("");%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%">
              <div align="right">Base C&aacute;lculo Anual :</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E04APCROY" size="4" maxlength="3" value="<%= lcProdInq.getE04APCROY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tabla de Documentos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCFTF" size="3" maxlength="2" value="<%= lcProdInq.getE04APCFTF().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Direcciones de Acceso</h4>
  <table cellspacing="0" cellpadding="2" width="100%" border="1" bordercolor="#333333" bgcolor="#FFFFFF" align="center">
    <tr bordercolor="#FFFFFF"> 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">Audio : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04APEAUD" size="82" maxlength="80" value="<%= lcProdInq.getE04APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04APEVID" size="82" maxlength="80" value="<%= lcProdInq.getE04APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E04APEHTM" size="82" maxlength="80" value="<%= lcProdInq.getE04APEHTM().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
     </form>
</body>
</html>
