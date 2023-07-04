<html>
<head>
<title>Consulta de Productos de Cartas de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="colProdInq" class="datapro.eibs.beans.ESD071105Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(coll_i_opt);

 
</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     error.setERRNUM("0");
     out.println("</SCRIPT>");
     }
out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Consulta de Producto de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="coll_inq_products, EDC0100"></h3>
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
              <a href="javascript:showCOLServCharge('<%= colProdInq.getE05APCBNK().trim()%>','<%= colProdInq.getE05APCTYP().trim()%>','<%= colProdInq.getE05APCTLN().trim()%>');"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Tipo de Cobranza : </div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN2" size="3" maxlength="2" value="<%= colProdInq.getE05APCAMO().trim()%>">
              <input type="text" readonly name="E05APCAMO" size="10" maxlength="1"  
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
              <input type="text" readonly name="E05APCITP" size="10" maxlength="1"  
			   value="<% if (colProdInq.getE05APCITP().equals("D")) out.print("Documentarias");
			    		else if (colProdInq.getE05APCITP().equals("C")) out.print("Simple");
						%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="45%"> 
              <div align="right">Entregar Documento :</div>
            </td>
            <td width="55%"> 
              <input type="text" readonly name="E05APCTLN222" size="3" maxlength="2" value="<%= colProdInq.getE05APCCDT().trim()%>">
              <input type="text" readonly name="E05APCCDT" size="10" maxlength="1"  
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
  <p align="center">&nbsp;</p>
 
</form>
</body>
</html>
