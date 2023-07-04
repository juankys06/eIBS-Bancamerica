<html>
<head>
<title>Consulta de Productos de Cartas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="lcProdInq" class="datapro.eibs.beans.ESD071104Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
%>

<h3 align="center">Consulta de Producto de Cartas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, ESD0711" width="32" height="32"></h3>
<hr size="4">

<form>
  
  
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

  <table class="tableinfo">
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
              <a href="javascript:showLCServCharge('<%= lcProdInq.getE04APCBNK().trim()%>','<%= lcProdInq.getE04APCTYP().trim()%>','<%= lcProdInq.getE04APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Carta de Cr&eacute;dito : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCAMO" size="3" maxlength="2" value="<%= lcProdInq.getE04APCAMO().trim()%>">
              <input type="text" readonly name="E04APCAMO" size="35" maxlength="35"  
			   value="<% if (lcProdInq.getE04APCAMO().equals("I")) out.print("Exportación");
			    		else if (lcProdInq.getE04APCAMO().equals("O")) out.print("Importación");
						else if (lcProdInq.getE04APCAMO().equals("S")) out.print("Garantía Emitida");
						else if (lcProdInq.getE04APCAMO().equals("R")) out.print("Garantía Recibida");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Confirmada :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E04APCICT" size="3" maxlength="1"  
			   value="<% if (lcProdInq.getE04APCICT().equals("Y")) out.print("Sí");
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
			    		else if (lcProdInq.getE04APCCDT().equals("T")) out.print("Término");
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
			   value="<% if (lcProdInq.getE04APCMCI().equals("Y")) out.print("Sí");
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
			   value="<% if (lcProdInq.getE04APCMCP().equals("Y")) out.print("Sí");
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
  <table class="tableinfo">
    <tr > 
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
     <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
  </div>
</form>
</body>
</html>
