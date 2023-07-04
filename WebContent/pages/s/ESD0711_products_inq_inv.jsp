<html>
<head>
<title>Consulta de Productos de Inversiones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="invProdInq" class="datapro.eibs.beans.ESD071113Message"  scope="session" />

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

<h3 align="center">Consulta de Producto de Inversiones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, ESD0711"></h3>
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
              <input type="text" readonly name="E13APCTYP" size="6" maxlength="4" value="<%= invProdInq.getE13APCTYP().trim()%>">
              <input type="text" readonly name="E13TYPDES" size="37" maxlength="35" value="<%= invProdInq.getE13TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E13APCCDE" size="6" maxlength="4" value="<%= invProdInq.getE13APCCDE().trim()%>">
              <input type="text" readonly name="E13DESCRI" size="65" maxlength="63" value="<%= invProdInq.getE13DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E13MERCAD" size="17" maxlength="15" value="<%= invProdInq.getE13MERCAD().trim()%>">
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
              <input type="text" readonly name="E13APCBNK" size="4" maxlength="2" value="<%= invProdInq.getE13APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Moneda: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E13APCCCY" size="5" maxlength="3" value="<%= invProdInq.getE13APCCCY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E13APCGLN" size="18" maxlength="16" value="<%= invProdInq.getE13APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Descripci&oacute;n de la Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E13GLMDSC" size="37" maxlength="35" value="<%= invProdInq.getE13GLMDSC().trim()%>">
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
              <div align="right">C&aacute;lculo de Intereses : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCIPD23" size="3" maxlength="3" value="<%= invProdInq.getE13APCICT().trim()%>">
              <input type="text" readonly name="E13APCICT" size="30" maxlength="1"  
			   value="<% if (invProdInq.getE13APCICT().equals("1")) out.print("Actual/actual[en período]");
			    		else if (invProdInq.getE13APCICT().equals("2")) out.print("Actual/365");
						else if (invProdInq.getE13APCICT().equals("3")) out.print("Actual/365[366 año bisiesto]");
						else if (invProdInq.getE13APCICT().equals("4")) out.print("Actual/360");
						else if (invProdInq.getE13APCICT().equals("5")) out.print("30/360");
						else if (invProdInq.getE13APCICT().equals("2")) out.print("30E/360");%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Inversi&oacute;n : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCINV" size="5" maxlength="4" value="<%= invProdInq.getE13APCINV().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Per&iacute;odo de Pago de Cup&oacute;n:</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCROY" size="4" maxlength="3" value="<%= invProdInq.getE13APCROY().trim()%>">
              (Per&iacute;odo expresado en meses) </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Amortizaci&oacute;n :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCIPD2" size="3" maxlength="3" value="<%= invProdInq.getE13APCAMO().trim()%>">
              <input type="text" readonly name="E13APCAMO" size="15" maxlength="1"  
			   value="<% if (invProdInq.getE13APCAMO().equals("S")) out.print("Straight Line");
			    		else if (invProdInq.getE13APCAMO().equals("L")) out.print("Level Yield");%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tipo de Portafolio :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCIPD22" size="3" maxlength="3" value="<%= invProdInq.getE13APCITP().trim()%>">
              <input type="text" readonly name="E13APCITP" size="35" maxlength="1"  
			   value="<% if (invProdInq.getE13APCITP().equals("1")) out.print("Retener hasta Vencimiento");
			    		else if (invProdInq.getE13APCITP().equals("2")) out.print("Disponible a la Venta");
						else if (invProdInq.getE13APCITP().equals("3")) out.print("Intercambio(Trading)");
						%>">
            </td>
          </tr>
          <tr id="trclear">
            <td width="45%">
              <div align="right">Tipo de Revaluaci&oacute;n :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCIPD222" size="3" maxlength="3" value="<%= invProdInq.getE13APCFRN().trim()%>">
              <input type="text" readonly name="E13APCFRN" size="35" maxlength="1"  
			   value="<% if (invProdInq.getE13APCFRN().equals("1")) out.print("Reevalúa Pérdida y Ganancia");
			    		else if (invProdInq.getE13APCFRN().equals("2")) out.print("Reevalúa sólo Pérdidas");
						else if (invProdInq.getE13APCFRN().equals("N")) out.print("No Reevalúa");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">D&iacute;as Cambio a Vencido</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E13APCIPD" size="3" maxlength="3" value="<%= invProdInq.getE13APCIPD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tabla de Documentos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E13APCFTF" size="3" maxlength="2" value="<%= invProdInq.getE13APCFTF().trim()%>">
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
              <input type="text" readonly name="E13APEAUD" size="82" maxlength="80" value="<%= invProdInq.getE13APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E13APEVID" size="82" maxlength="80" value="<%= invProdInq.getE13APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E13APEHTM" size="82" maxlength="80" value="<%= invProdInq.getE13APEHTM().trim()%>">
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
</p>
 
</form>
</body>
</html>
