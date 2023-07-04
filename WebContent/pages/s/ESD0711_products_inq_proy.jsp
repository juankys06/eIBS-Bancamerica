<html>
<head>
<title>Consulta de Productos de Control de Proyectos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/dynlayer.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/pop_out.js"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/nav_aid.js"> </SCRIPT>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="pryProdInq" class="datapro.eibs.beans.ESD071119Message"  scope="session" />

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

<h3 align="center">Consulta de Producto de Control de Proyectos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, ESD0711"></h3>
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
              <input type="text" readonly name="E19APCTYP" size="6" maxlength="4" value="<%= pryProdInq.getE19APCTYP().trim()%>">
              <input type="text" readonly name="E19TYPDES" size="37" maxlength="35" value="<%= pryProdInq.getE19TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E19APCCDE" size="6" maxlength="4" value="<%= pryProdInq.getE19APCCDE().trim()%>">
              <input type="text" readonly name="E19DESCRI" size="65" maxlength="63" value="<%= pryProdInq.getE19DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E19MERCAD" size="17" maxlength="15" value="<%= pryProdInq.getE19MERCAD().trim()%>">
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
              <input type="text" readonly name="E19APCBNK" size="4" maxlength="2" value="<%= pryProdInq.getE19APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Moneda: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E19APCCCY" size="5" maxlength="3" value="<%= pryProdInq.getE19APCCCY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E19APCGLN" size="18" maxlength="16" value="<%= pryProdInq.getE19APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Descripci&oacute;n de la Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E19GLMDSC" size="37" maxlength="35" value="<%= pryProdInq.getE19GLMDSC().trim()%>">
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
              <div align="right">Clase de Prest&aacute;mo : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E19APCFRN" size="3" maxlength="2" value="<%= pryProdInq.getE19APCFRN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tabla de Documentos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E19APCFTF" size="3" maxlength="2" value="<%= pryProdInq.getE19APCFTF().trim()%>">
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
              <input type="text" readonly name="E19APEAUD" size="82" maxlength="80" value="<%= pryProdInq.getE19APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E19APEVID" size="82" maxlength="80" value="<%= pryProdInq.getE19APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E19APEHTM" size="82" maxlength="80" value="<%= pryProdInq.getE19APEHTM().trim()%>">
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
