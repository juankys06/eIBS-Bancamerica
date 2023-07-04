<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       
     builtNewMenu(client_personal_opt);
       
   </SCRIPT>

<%
}
%>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Rechazo de Cr&eacute;ditos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  <h4> Informaci&oacute;n B&aacute;sica</h4>
    
  <table class="tableinfo">
    <tr id="trdark"> 
      <td nowrap width="25%"> 
        <div align="right">No Cliente :</div>
      </td>
      <td nowrap colspan="3"> 
        <input type="hidden" name="E01CUN" size="15" maxlength="10" value="<%= client.getE01CUN().trim()%>">
        <%= client.getE01CUN().trim()%> </td>
    </tr>
    <tr id="trclear"> 
      <td nowrap width="25%"> 
        <div align="right">Nombre :</div>
      </td>
      <td nowrap colspan=3> 
        <input type="text" name="E01FNA" size="35" maxlength="30" value="<%= client.getE01FNA().trim()%>">
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap width="25%" > 
        <div align="right">Identificaci&oacute;n :</div>
      </td>
      <td nowrap colspan=3> 
        <input type="text" name="E01LN1" size="35" maxlength="30" value="<%= client.getE01LN1().trim()%>">
        <input type="text" name="E01LN2" size="5" maxlength="30" value="<%= client.getE01LN2().trim()%>">
      </td>
    </tr>
  </table>
        
  <h4>Informaci&oacute;n Adicional</h4>
  <div align="left"> 
    <table class="tableinfo">
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Credito con Excepci&oacute;n :</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="checkbox" name="checkbox" value="checkbox">
          </div>
        </td>
        <td nowrap width="9%" >&nbsp;</td>
        <td nowrap width="23%" >&nbsp;</td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Monto Disponible :</div>
        </td>
        <td nowrap width="43%" > 
          <input type="text" name="E01UC92" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
        </td>
        <td nowrap width="9%" > 
          <div align="right">Cupo Cr&eacute;dito:</div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"> 
            <input type="text" name="E01UC923" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Cupo Tarjeta</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="text" name="E01UC924" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
        <td nowrap width="9%" > 
          <div align="right">Riesgo/Zona :</div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"> 
            <input type="text" name="E01UC9232" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Propietario de Casa :</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="text" name="E01UC9242" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
        <td nowrap width="9%" > 
          <div align="right">Nivel de Riesgo :</div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"> 
            <input type="text" name="E01UC9243" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Cr&eacute;dito Empleado :</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="checkbox" name="checkbox2" value="checkbox">
          </div>
        </td>
        <td nowrap width="9%" > 
          <div align="right"></div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Renta L&iacute;quida :</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="text" name="E01UC92422" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
        <td nowrap width="9%" > 
          <div align="right">Avaluo Comercial :</div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"> 
            <input type="text" name="E01UC92423" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td nowrap width="25%"> 
          <div align="right">Flujo M&aacute;ximo :</div>
        </td>
        <td nowrap width="43%" > 
          <div align="left"> 
            <input type="text" name="E01UC92424" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
        <td nowrap width="9%" > 
          <div align="right">Flujo Externo :</div>
        </td>
        <td nowrap width="23%" > 
          <div align="left"> 
            <input type="text" name="E01UC92425" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trclear">
        <td nowrap width="25%">
          <div align="right">Flujo Interno :</div>
        </td>
        <td nowrap width="43%" >
          <div align="left">
            <input type="text" name="E01UC92426" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
        <td nowrap width="9%" >
          <div align="right">Flujo Disponible :</div>
        </td>
        <td nowrap width="23%" >
          <div align="left">
            <input type="text" name="E01UC92427" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
          </div>
        </td>
      </tr>
    </table>
    <h4>Producto</h4>
    <div align="left"> 
      <table class="tableinfo">
        <tr id="trclear"> 
          <td nowrap width="25%"> 
            <div align="right">Moneda :</div>
          </td>
          <td nowrap width="43%" > 
            <input type="text" name="E01UC922" size="4" maxlength="3" value="<%= client.getE01UC9().trim()%>">
          </td>
          <td nowrap width="9%" > 
            <div align="right">Fecha Ingreso :</div>
          </td>
          <td nowrap width="23%" > 
            <div align="left"> 
              <input type="text" name="E01UC9233" size="3" maxlength="2" value="<%= client.getE01UC9().trim()%>">
              <input type="text" name="E01UC92332" size="3" maxlength="2" value="<%= client.getE01UC9().trim()%>">
              <input type="text" name="E01UC92333" size="3" maxlength="2" value="<%= client.getE01UC9().trim()%>">
            </div>
          </td>
        </tr>
        <tr id="trclear"> 
          <td nowrap width="25%"> 
            <div align="right">L&iacute;quido Solicitado :</div>
          </td>
          <td nowrap width="43%" > 
            <div align="left"> 
              <input type="text" name="E01UC9244" size="15" maxlength="15" value="<%= client.getE01UC9().trim()%>">
            </div>
          </td>
          <td nowrap width="9%" > 
            <div align="right"></div>
          </td>
          <td nowrap width="23%" > 
            <div align="left"> </div>
          </td>
        </tr>
        <tr id="trclear"> 
          <td nowrap width="25%"> 
            <div align="right">Producto :</div>
          </td>
          <td nowrap colspan="3" > 
            <div align="left"> </div>
            <div align="right"></div>
            <div align="left"> 
              <input type="text" name="E01UC92432" size="4" maxlength="3" value="<%= client.getE01UC9().trim()%>">
              <input type="text" name="E01UC924322" size="35" maxlength="35" value="<%= client.getE01UC9().trim()%>">
            </div>
          </td>
        </tr>
        <tr id="trclear">
          <td nowrap width="25%">
            <div align="right">Motivo de Rechazo :</div>
          </td>
          <td nowrap colspan="3" >
            <input type="text" name="E01UC9243222" size="35" maxlength="35" value="<%= client.getE01UC9().trim()%>">
          </td>
        </tr>
        <tr id="trclear"> 
          <td nowrap width="25%"> 
            <div align="right">Observaciones :</div>
          </td>
          <td nowrap colspan="3" > 
            <div align="left"> 
              <input type="text" name="E01UC924262" size="45" maxlength="35" value="<%= client.getE01UC9().trim()%>">
            </div>
          </td>
        </tr>
      </table>
    </div>
    
  </div>
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>

