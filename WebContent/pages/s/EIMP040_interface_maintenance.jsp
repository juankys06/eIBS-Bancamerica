<html>
<head>
<title>Maestro de Interfaces</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="intDetails" class="datapro.eibs.beans.EIMP04001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.js"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.js"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Mantenimiento del Maestro de Interfaces <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="interface_maintenance.jsp, EIMP040"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIMP040" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01IMIBNK" size="3" maxlength="2"  value="<%= intDetails.getE01IMIBNK().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Proceso :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01IMICDE" size="6"  maxlength="4" value="<%= intDetails.getE01IMICDE().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de la Interface</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Sistema de Origen :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01IMIORG" maxlength="8" size="9" value="<%= intDetails.getE01IMIORG().trim()%>" >
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Frecuencia :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E01IMIFRQ">
                <option value=D <% if (intDetails.getField("E01IMIFRQ").getString().equals("D")) out.print("selected"); %>>Diario(a)</option>
                <option value=M <% if (intDetails.getField("E01IMIFRQ").getString().equals("M")) out.print("selected"); %>>Mensual</option>
              </select>
              </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Interface Requerida :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E01IMIRQR">
                <option value=R <% if (intDetails.getField("E01IMIRQR").getString().equals("R")) out.print("selected"); %>>Requerido(a)</option>
                <option value=O <% if (intDetails.getField("E01IMIRQR").getString().equals("O")) out.print("selected"); %>>Opcional</option>
              </select>
              </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Tipo de Proceso :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E01IMIPRT">
                <option value=D <% if (intDetails.getField("E01IMIPRT").getString().equals("D")) out.print("selected"); %>>Solo Débitos</option>
                <option value=C <% if (intDetails.getField("E01IMIPRT").getString().equals("C")) out.print("selected"); %>>Solo Créditos</option>
                <option value=B <% if (intDetails.getField("E01IMIPRT").getString().equals("B")) out.print("selected"); %>>Archivo Balanceado</option>
              </select>
              </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Proceso de Rechazos :</div>
            </td>
            <td width="58%" nowrap > 
              <select name="E01IMIPRE">
                <option value=I <% if (intDetails.getField("E01IMIPRE").getString().equals("I")) out.print("selected"); %>>Ignora Rechazos</option>
                <option value=S <% if (intDetails.getField("E01IMIPRE").getString().equals("S")) out.print("selected"); %>>Registra en Cuenta de Suspenso</option>
              </select>
              </td>
          </tr>

<%--
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Posición Area de Datos :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01IMIDTP" maxlength="2" size="3" value="<%= intDetails.getE01IMIDTP().trim()%>" >
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Nombre Archivo :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01IMIFI1" maxlength="10" size="11" value="<%= intDetails.getE01IMIFI1().trim()%>" >
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Nombre Archivo Resumen :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01IMIFI2" maxlength="10" size="11" value="<%= intDetails.getE01IMIFI2().trim()%>" >
            </td>
          </tr>
--%>


        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>
