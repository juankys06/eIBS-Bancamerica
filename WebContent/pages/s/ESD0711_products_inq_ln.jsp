<html>
<head>
<title>Consulta de Productos de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="lnProdInq" class="datapro.eibs.beans.ESD071110Message"  scope="session" />

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

<h3 align="center">Consulta de Producto de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, ESD0711"></h3>
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
              <input type="text" readonly name="E10DESCRI" size="65" maxlength="63" value="<%= lnProdInq.getE10DESCRI().trim()%>">
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
            <td width="45%"> 
              <div align="right">Tipo de Prest&aacute;mo : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCFRN" size="3" maxlength="1" value="<%= lnProdInq.getE10APCFRN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">C&aacute;lculo de Inter&eacute;s : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCMCI" size="3" maxlength="1" value="<%= lnProdInq.getE10APCMCI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">C&aacute;lculo de Mora : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCMCP" size="3" maxlength="1" value="<%= lnProdInq.getE10APCMCP().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Ciclo de Pago Principal :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCPPD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCPPD().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Ciclo de Pago de Intereses :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCIPD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCIPD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tabla de Cargos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCTLN" size="4" maxlength="2" value="<%= lnProdInq.getE10APCTLN().trim()%>">
              <input type="text" readonly name="E10TBLDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10TBLDSC().trim()%>">
			  <a href="javascript:showLNServCharge('<%= lnProdInq.getE10APCBNK().trim()%>','<%= lnProdInq.getE10APCTYP().trim()%>','<%= lnProdInq.getE10APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tabla de Tasas / Rangos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCCDT" size="3" maxlength="1" value="<%= lnProdInq.getE10APCCDT().trim()%>">
              <a href="javascript:showCDRates('<%= lnProdInq.getE10APCCCY().trim()%>','<%= lnProdInq.getE10APCCDT().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Cliente : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCRES" size="3" maxlength="1" value="<%= lnProdInq.getE10APCRES().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tabla de Tasa Flotante : </div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E10APCFRT" size="4" maxlength="2" value="<%= lnProdInq.getE10APCFRT().trim()%>">
              <input type="text" readonly name="E10FLTDSC" size="37" maxlength="35" value="<%= lnProdInq.getE10FLTDSC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Tasa Flotante : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E10APCFTY" size="4" maxlength="2" value="<%= lnProdInq.getE10APCFTY().trim()%>">
            </td>
          </tr>
          <tr id="trdark">
            <td width="45%"> 
              <div align="right">Ciclo Cambio de Tasa :</div>
            </td>
            <td width="55%">
              <input type="text" readonly name="E10APCRRD" size="5" maxlength="3" value="<%= lnProdInq.getE10APCRRD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tabla de Documentos: </div>
            </td>
            <td width="55%"> 
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
    <tr > 
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
    <p>
  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
  </div>
</p>
 </form>
</body>
</html>
