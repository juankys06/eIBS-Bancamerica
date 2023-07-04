<html>
<head>
<title>Reportes Préstamos Preferenciales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<h3 align="center">Reportes Préstamos Preferenciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="chg_selection.jsp,EDL1143"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1143" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Fechas desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRDTE1" size="2" maxlength="2">
                <input type="text" name="E01FRDTE2" size="2" maxlength="2">
                <input type="text" name="E01FRDTE3" size="2" maxlength="2">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TODTE1" size="2" maxlength="2">
                <input type="text" name="E01TODTE2" size="2" maxlength="2">
                <input type="text" name="E01TODTE3" size="2" maxlength="2">
              </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Tipo de Información :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <select name="E01INFTYP">
                <option value="1">Préstamos Preferenciales Nuevos</option>
                <option value="2">Préstamos Preferenciales Modificados</option>
                <option value="3">Crédito Fiscal</option>
              </select>
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
