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

<jsp:useBean id="colProdInq" class="datapro.eibs.beans.ESD071105Message"  scope="session" />

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

<h3 align="center">Consulta de Producto de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="products_inq_ln.jsp, ESD0711"></h3>
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
              <input type="text" readonly name="E05APCTYP" size="6" maxlength="4" value="<%= colProdInq.getE05APCTYP().trim()%>">
              <input type="text" readonly name="E05TYPDES" size="37" maxlength="35" value="<%= colProdInq.getE05TYPDES().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="30%"> 
              <div align="right">C&oacute;digo de Producto: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E05APCCDE" size="6" maxlength="4" value="<%= colProdInq.getE05APCCDE().trim()%>">
              <input type="text" readonly name="E05DESCRI" size="65" maxlength="63" value="<%= colProdInq.getE05DESCRI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Nombre de Mercadeo: </div>
            </td>
            <td width="70%"> 
              <input type="text" readonly name="E05MERCAD" size="17" maxlength="15" value="<%= colProdInq.getE05MERCAD().trim()%>">
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
              <input type="text" readonly name="E05APCBNK" size="4" maxlength="2" value="<%= colProdInq.getE05APCBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Moneda: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E05APCCCY" size="5" maxlength="3" value="<%= colProdInq.getE05APCCCY().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">C&oacute;digo de Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E05APCGLN" size="18" maxlength="16" value="<%= colProdInq.getE05APCGLN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Descripci&oacute;n de la Cuenta Contable: </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E05GLMDSC" size="37" maxlength="35" value="<%= colProdInq.getE05GLMDSC().trim()%>">
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
              <div align="right">C&oacute;digo de Tarifa: </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN3" size="3" maxlength="2" value="<%= colProdInq.getE05APCTLN().trim()%>">
              <input type="text" readonly name="E05TLNDSC" size="30" maxlength="25" value="<%= colProdInq.getE05TLNDSC().trim()%>">
              <a href="javascript:showCOLServCharge('<%= colProdInq.getE05APCBNK().trim()%>','<%= colProdInq.getE05APCTYP().trim()%>','<%= colProdInq.getE05APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Cobranza : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN2" size="3" maxlength="2" value="<%= colProdInq.getE05APCAMO().trim()%>">
              <input type="text" readonly name="E05APCAMO" size="35" maxlength="35"  
			   value="<% if (colProdInq.getE05APCAMO().equals("I")) out.print("Recibidas");
			    		else if (colProdInq.getE05APCAMO().equals("O")) out.print("Enviadas");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Subtipo de Cobranza :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN22" size="3" maxlength="2" value="<%= colProdInq.getE05APCITP().trim()%>">
              <input type="text" readonly name="E05APCITP" size="35" maxlength="35"  
			   value="<% if (colProdInq.getE05APCITP().equals("D")) out.print("Documentarias");
			    		else if (colProdInq.getE05APCITP().equals("C")) out.print("Simple");
						%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Entregar Documento Contra :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN222" size="3" maxlength="2" value="<%= colProdInq.getE05APCCDT().trim()%>">
              <input type="text" readonly name="E05APCCDT" size="35" maxlength="35"  
			   value="<% if (colProdInq.getE05APCCDT().equals("P")) out.print("Pago");
			    		else if (colProdInq.getE05APCCDT().equals("A")) out.print("Aceptación");
						%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="45%"> 
              <div align="right">Tabla de Documentos : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN" size="3" maxlength="2" value="<%= colProdInq.getE05APCTLN().trim()%>">
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
              <input type="text" readonly name="E05APEAUD" size="82" maxlength="80" value="<%= colProdInq.getE05APEAUD().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="50%"> 
              <div align="right">Video : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E05APEVID" size="82" maxlength="80" value="<%= colProdInq.getE05APEVID().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="50%"> 
              <div align="right">HTML : </div>
            </td>
            <td width="50%"> 
              <input type="text" readonly name="E05APEHTM" size="82" maxlength="80" value="<%= colProdInq.getE05APEHTM().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
      <p align="center">
<input id="EIBSBTN" type=button name="Submit" OnClick="top.close()" value="Cerrar">
      </p>
 </form>
</body>
</html>
