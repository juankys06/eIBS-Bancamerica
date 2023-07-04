<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Control de Stock</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0006DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Control de Stock
<BR>Consulta
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_stock_inq.jsp, ECD0006"> 
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0006" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E06CDRTPL" size="3" maxlength="2" value="<%= msgCD.getE06CDRTPL() %>">
                <input type="text" name="E06CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE06CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Oficina : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06CDRBRN" size="5" maxlength="4" readonly value="<%= msgCD.getE06CDRBRN() %>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Stock Mínimo : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06CDRMIN" size="8" maxlength="7" readonly value="<%= msgCD.getE06CDRMIN() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Stock Máximo : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06CDRMAX" size="8" maxlength="7" readonly value="<%= msgCD.getE06CDRMAX() %>">
              </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Usuario  : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E06CDRUSR" size="17" maxlength="15" readonly value="<%= msgCD.getE06CDRUSR() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha de Entrada : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06CDRDAY" size="3" maxlength="2" readonly value="<%= msgCD.getE06CDRDAY() %>">
                <input type="text" name="E06CDRMON" size="3" maxlength="2" readonly value="<%= msgCD.getE06CDRMON() %>">
                <input type="text" name="E06CDRYEA" size="5" maxlength="4" readonly value="<%= msgCD.getE06CDRYEA() %>">
			  </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Hora : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06CDRTIM" size="8" maxlength="7" readonly value="<%= msgCD.getE06CDRTIM() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
            </td>
            <td nowrap width="20%" > 
            </td>
          </tr> 
        </table>
      </td>
    </tr>
  </table>
  <br>
  
  </form>
</body>
</html>
