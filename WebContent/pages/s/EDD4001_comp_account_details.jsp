<html>
<head>
<title>Mantenimiento de Pagos Automáticos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.EDD400101Message"  scope="session" />

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


<H3 align="center">Mantenimiento de Pagos Automáticos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="comp_account_details.jsp, EDD4001"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4001" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">

  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Compañia :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01PAYCIA" size="5" maxlength="4" value="<%= refCodes.getE01PAYCIA().trim()%>">
                <a href="javascript:GetCodeDescCNOFC('E01PAYCIA','D01PAYCIA','BT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
                <input type="text" name="D01PAYCIA" size="37" maxlength="35" value="<%= refCodes.getD01PAYCIA().trim()%>">                
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Tipo de Proceso :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01PAYCDE" size="5" maxlength="4" value="<%= refCodes.getE01PAYCDE().trim()%>">
                <a href="javascript:GetCodeDescCNOFC('E01PAYCDE','D01PAYCDE','PT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
                <input type="text" name="D01PAYCDE" size="37" maxlength="35" value="<%= refCodes.getD01PAYCDE().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Número de Cuenta :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01PAYACC" size="14" maxlength="12" value="<%= refCodes.getE01PAYACC().trim()%>">
                <a href="javascript:GetAccount('E01PAYACC','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0"  ></a> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Valor Acreditación :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01PAYAMT" size="17" maxlength="15" value="<%= refCodes.getE01PAYAMT().trim()%>" onkeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%"> 
              <div align="right">Fecha Acreditación :</div>
            </td>
            <td nowrap width="56%"> 
              <input type="text" name="E01PAYRD1" size="2" maxlength="2" value="<%=refCodes.getE01PAYRD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01PAYRD2" size="2" maxlength="2" value="<%=refCodes.getE01PAYRD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01PAYRD3" size="2" maxlength="2" value="<%=refCodes.getE01PAYRD3().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="44%"> 
              <div align="right">Estado Registro :</div>
            </td>
            <td nowrap width="56%"> 
              <div align="left"> 
			    <select name="E01PAYSTS">
				  <option value="H" <% if (refCodes.getE01PAYSTS().equals("A")) out.print("selected"); %>>Activo</option>
				  <option value=" " <% if (refCodes.getE01PAYSTS().equals("P")) out.print("selected"); %>>Pagado</option>
				  <option value=" " <% if (refCodes.getE01PAYSTS().equals("C")) out.print("selected"); %>>Cancelado</option>				  
				</select>
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
