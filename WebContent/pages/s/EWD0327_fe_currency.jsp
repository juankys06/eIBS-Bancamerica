<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tesoreria</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0327Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

</head>
<body>
<h3 align="center">Consulta Detallada - Posici&oacute;n de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_currency.jsp,EWD0327"> 
</h3>
<hr size="4">
<%
	if ( EWD0327Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay resultados que correspondan 
          con su criterio de b&uacute;squeda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<p>&nbsp;</p>
<TABLE class="tableinfo" >
  <TR id="trdark"> 
    <th>Descripción</th>
    <th>Monto </th>
    <th>Monto<BR>
      Equivalente</th>
    <th>Tasa</th>
    <th>Fecha <BR> Valor</th>
    <th>Negociador<br>
      ID</th>
    <th>Referencia</th>
	<th>Nombre</th>
  </tr>
  <%
                EWD0327Help.initRow();
                while (EWD0327Help.getNextRow()) {
                    if (EWD0327Help.getFlag().equals("")) {
                    		out.println(EWD0327Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>