<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "lcCodes" class= "datapro.eibs.beans.ESD000002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  builtNewMenu(lc_i_opt);

</SCRIPT>
</head>
<body  nowrap bgcolor="#FFFFFF">


 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<h3 align="center">Comisiones </h3>
<hr size="4">
 <FORM >
 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Comisiones</h4> 
  <table class="tableinfo" >
    <tr > 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="center"><b>Concepto</b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Monto</b></div>
            </td>
            <td nowrap width="25%"> 
              <div align="center"><b>M&iacute;nimo</b></div>
            </td>
            <td nowrap width="22%"> 
              <div align="center"><b>P/Cta de </b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Comisi&oacute;n por Enmienda :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02ACC2" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Aviso de Enmienda :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select2">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Discrepancia :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02ACC13" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select3">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%"> 
              <div align="right">Extensi&oacute;n de Validez :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select4">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Portes, Franqueo :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select5">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%"> 
              <div align="right">Mensajeria :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select6">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Telex- Mensaje Largo :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select7">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%"> 
              <div align="right">Telex- Mensaje Corto :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select8">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Destino de los Fondos :</div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" size="12" maxlength="12" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <select name="select9">
                  <option>Aplicante</option>
                  <option>Beneficiario</option>
                </select>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
</form>
</body>
</html>

