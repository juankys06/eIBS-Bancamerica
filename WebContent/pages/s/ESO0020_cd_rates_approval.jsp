<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Autorización de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ESO002001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Autorización
de Tasas<BR>Aprobación
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_rates_approval.jsp, ESO0020"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0020" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Usuario : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E01USERID" size="11" maxlength="10" value="<%= msgCD.getE01USERID().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cliente : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLCUN" size="11" maxlength="10" value="<%= msgCD.getE01SOLCUN().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa Sugerida : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLRTE" size="12" maxlength="11" value="<%= msgCD.getE01SOLRTE().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Nombre : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="46" maxlength="45" value="<%= msgCD.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E01SOLPRI" size="20" maxlength="19" value="<%= msgCD.getE01SOLPRI().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Plazo : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E01SOLTRM" size="3" maxlength="2" value="<%= msgCD.getE01SOLTRM().trim()%>">
              	<select name="E01SOLTRC" disabled>
                  <option value=" " <% if (!msgCD.getE01SOLTRC().equals("D") && !msgCD.getE01SOLTRC().equals("M") && !msgCD.getE01SOLTRC().equals("Y")) { out.println("selected"); }%>></option>
                  <option value="D" <% if (msgCD.getE01SOLTRC().equals("D")) { out.println("selected"); }%>>Día(s)</option>
                  <option value="M" <% if (msgCD.getE01SOLTRC().equals("M")) { out.println("selected"); }%>>Mes(es)</option>
                  <option value="Y" <% if (msgCD.getE01SOLTRC().equals("Y")) { out.println("selected"); }%>>Año(s)</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa Aprobada : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLART" size="12" maxlength="11" value="">
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Clave de Autorización : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="password" name="E01SOLCLV" size="11" maxlength="10" value="">
              </div>
            </td>
          </tr>
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
