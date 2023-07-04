<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Foreign Exchange </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0321Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

</head>
<body>
<h3 align="center">Tesorer&iacute;a - Contratos Incompletos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_inc_deals.jsp,EWD0321"> 
</h3>
<hr size="4">
<%
	if ( EWD0321Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> <font size="3"><b>No hay resultados que correspondan 
        con su criterio de b&uacute;squeda</b></font></b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<p>&nbsp;</p>
<TABLE class="tableinfo" >
  <TR id="trdark"> 
    <th>N&uacute;mero de <BR>
      Referencia</th>
    <th>Tipo</th>
    <th>Acci&oacute;n<BR>
      Tomada</th>
    <th>Moneda</th>
    <th>Monto</th>
    <th>Identificaci&oacute;n<br>
      Dealer</th>
  </tr>
  <%
                EWD0321Help.initRow();
                while (EWD0321Help.getNextRow()) {
                    if (EWD0321Help.getFlag().equals("")) {
                    		out.println(EWD0321Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>