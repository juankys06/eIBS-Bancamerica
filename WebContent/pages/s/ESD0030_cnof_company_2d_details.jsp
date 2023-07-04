<html>
<head>
<title>Codigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">C&oacute;digos de Referencia del Sistema <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_company_2d_details.jsp, ESD0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
   <INPUT TYPE=HIDDEN NAME="E02CNOBNK" value="<%= currUser.getE01UBK()%>">
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap width="80%" height="19">    
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap width="80%" height="19">    
              <div align="left"> 
                <input type="text" name="E02CNOMID" size="7" maxlength="6" value="<%= refCodes.getE02CNOMID().trim()%>">
                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Cuenta Contable Asociada :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCG" size="17" maxlength="16" value="<%= refCodes.getE02CNOSCG().trim()%>">
                <a href="javascript:GetLedger('E02CNOSCG',document.forms[0].E02CNOBNK.value,'','')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left"> 
                <input type="text" name="E02CNOSCY" size="4" maxlength="3" value="<%= refCodes.getE02CNOSCY().trim()%>"  >
                <a href="javascript:GetCurrency('E02CNOSCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a></div>
            </td>  
          </tr>      
          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Tipo de Empresa :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <input type="text" name="E02CNOCPC" size="5" maxlength="4" value="<%= refCodes.getE02CNOCPC().trim()%>">
              <a href="javascript:GetCodeCNOFC('E02CNOCPC','T9')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 		
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Porcentaje de Descuento :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02CNOCST" size="8" maxlength="7" value="<%= refCodes.getE02CNOCST().trim()%>"  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Calificaci&oacute;n :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02CNOMIC" size="4" maxlength="3" value="<%= refCodes.getE02CNOMIC().trim()%>"  >
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Código de Area :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTARE" size="4" maxlength="3" value="<%= refCodes.getE02COTARE().trim()%>"  >
                <a href="javascript:GetCodeDescCNOFC('E02COTARE','D02COTARE','AR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
                <input type="text" name="D02COTARE" size="40" maxlength="35" value="<%= refCodes.getD02COTARE().trim()%>" readonly>
              </div>
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Código de Ruta :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTRTA" size="4" maxlength="3" value="<%= refCodes.getE02COTRTA().trim()%>"  >
                <a href="javascript:GetCodeDescCNOFC('E02COTRTA','D02COTRTA','RC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
                <input type="text" name="D02COTRTA" size="40" maxlength="35" value="<%= refCodes.getD02COTRTA().trim()%>" readonly>
              </div>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Tipo/Cliente :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTTCL" size="4" maxlength="3" value="<%= refCodes.getE02COTTCL().trim()%>"  >
                <a href="javascript:GetCodeDescCNOFC('E02COTTCL','D02COTTCL','CT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
                <input type="text" name="D02COTTCL" size="40" maxlength="35" value="<%= refCodes.getD02COTTCL().trim()%>" readonly>
              </div>
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Dirección Empresa :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTDIR" size="120"
					maxlength="100" value="<%= refCodes.getE02COTDIR().trim()%>">
              </div>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Teléfonos  :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTTE1" size="16" maxlength="15" value="<%= refCodes.getE02COTTE1().trim()%>"  >
                <input type="text" name="E02COTTE2" size="16" maxlength="15" value="<%= refCodes.getE02COTTE2().trim()%>"  >
              </div>
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Persona de Contacto :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTCON" size="70"
					maxlength="50" value="<%= refCodes.getE02COTCON().trim()%>">
              </div>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Pago :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <input type="radio" name="E02COTPSN" value="Y" <% if (!refCodes.getE02COTPSN().equals("N")) out.print("checked"); %>>Sí 
              <input type="radio" name="E02COTPSN" value="N" <% if  (refCodes.getE02COTPSN().equals("N")) out.print("checked"); %>>No
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Referencia :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTREF" size="70"
					maxlength="50" value="<%= refCodes.getE02COTREF().trim()%>">
              </div>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Horario :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTHOR" size="70"
					maxlength="50" value="<%= refCodes.getE02COTHOR().trim()%>">
              </div>
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="20%" height="19"> 
              <div align="right">Dias de Cobro 1/2/3 :</div>
            </td>
            <td nowrap width="80%" height="19"> 
              <div align="left">
                <input type="text" name="E02COTDC1" size="4" maxlength="2" value="<%= refCodes.getE02COTDC1().trim()%>"  >
                <input type="text" name="E02COTDC2" size="4" maxlength="2" value="<%= refCodes.getE02COTDC2().trim()%>"  >
                <input type="text" name="E02COTDC3" size="4" maxlength="2" value="<%= refCodes.getE02COTDC3().trim()%>"  >
              </div>
            </td>
          </tr>


        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
