<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Negociación de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ESO000002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Negociación de Tasas
<BR>Mantenimiento
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_rates_maint.jsp, ESO0000"> 
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Cliente : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02SOLCUN" size="11" maxlength="10" value="<%= msgCD.getE02SOLCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Nombre : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02CUSNA1" size="46" maxlength="45" value="<%= msgCD.getE02CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E02SOLRTE" size="12" maxlength="11" value="<%= msgCD.getE02SOLRTE().trim()%>">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E02SOLPRI" size="20" maxlength="19" value="<%= msgCD.getE02SOLPRI().trim()%>" readonly>
              </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Plazo : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E02SOLTRM" size="4" maxlength="3" value="<%= msgCD.getE02SOLTRM().trim()%>">
              	<select name="E02SOLTRC">
                  <option value=" " <% if (!msgCD.getE02SOLTRC().equals("D") && !msgCD.getE02SOLTRC().equals("M") && !msgCD.getE02SOLTRC().equals("Y")) { out.println("selected"); }%>></option>
                  <option value="D" <% if (msgCD.getE02SOLTRC().equals("D")) { out.println("selected"); }%>>Día(s)</option>
                </select>
              </div>
            </td>
            <td nowrap width="25%" ></td>
            <td nowrap width="25%" ></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>
