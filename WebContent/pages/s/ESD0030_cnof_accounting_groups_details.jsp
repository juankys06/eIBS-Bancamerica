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


<H3 align="center">C&oacute;digos de Referencia del Sistema - Grupos Contables<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_accounting_groups_details.jsp, ESD0030"></H3>
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
            <td nowrap width="16%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E02CNORCD" size="6" maxlength="4" value="<%= refCodes.getE02CNORCD().trim()%>">
                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Clase de Cuenta de Grupo :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNOF04">
                  <option value="1" <%if (refCodes.getE02CNOF04().equals("1")) { out.print("selected"); }%>>Activo</option>
                  <option value="2" <%if (refCodes.getE02CNOF04().equals("2")) { out.print("selected"); }%>>Pasivo</option>
                  <option value="3" <%if (refCodes.getE02CNOF04().equals("3")) { out.print("selected"); }%>>Patrimonio</option>
                  <option value="4" <%if (refCodes.getE02CNOF04().equals("4")) { out.print("selected"); }%>>Ingreso</option>
                  <option value="5" <%if (refCodes.getE02CNOF04().equals("5")) { out.print("selected"); }%>>Gastos</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="16%" height="19">
              <div align="right">Digito de Verificaci&oacute;n :</div>
            </td>
            <td nowrap height="19">
              <div align="left">
                <select name="E02CNOF01">
                  <option value="" <%if (refCodes.getE02CNOF01().equals("")) { out.print("selected"); }%>>No Aplica</option>
                  <option value="0" <%if (refCodes.getE02CNOF01().equals("0")) { out.print("selected"); }%>>M&oacute;dulo 10</option>
                  <option value="1" <%if (refCodes.getE02CNOF01().equals("1")) { out.print("selected"); }%>>M&oacute;dulo 11</option>
                  <option value="C" <%if (refCodes.getE02CNOF01().equals("C")) { out.print("selected"); }%>>Versi&oacute; Chile RUT</option>
                  <option value="A" <%if (refCodes.getE02CNOF01().equals("A")) { out.print("selected"); }%>>Versi&oacute; Chile Cta/Cte</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" height="19">
              <div align="right">Tipo de Persona :</div>
            </td>
            <td nowrap height="19">
              <div align="left">
                <select name="E02CNOF03">
                  <option value="" <%if (refCodes.getE02CNOF03().equals("")) { out.print("selected"); }%>>No Aplica</option>
                  <option value="1" <%if (refCodes.getE02CNOF03().equals("1")) { out.print("selected"); }%>>Corporativo</option>
                  <option value="2" <%if (refCodes.getE02CNOF03().equals("2")) { out.print("selected"); }%>>Personal</option>
                  <option value="3" <%if (refCodes.getE02CNOF03().equals("3")) { out.print("selected"); }%>>Otros</option>
                </select>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>  
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>
