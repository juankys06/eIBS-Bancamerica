<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Treasury </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "ewd0332Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

</head>
<body>
<form>

  <h3 align="center">Consulta - Cuentas FRA<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_acc.jsp,EWD0332"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <%
	if ( ewd0332Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center"> <b> No hay resultados que correspondan con su criterio 
          de b&uacute;squeda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<p>&nbsp;</p>
<TABLE  id="mainTable" class="tableinfo">
<TR > 
    <TD NOWRAP valign="top" width="100%" >
<TABLE id="headTable" width="100%" >
  <TR id="trdark"> 
            <th>N&uacute;mero de <BR>
              Cuenta</th>
            <th>Contraparte</th>
	        <th>Acci&oacute;n<BR>
              Tomada</th>
            <th>Tipo de <br>
              Contrato</th>
	        <th>Moneda</th>
            <th>Saldo<br>
              Principal</th>
            <th>Monto<br>
              Nominal </th>
            <th>Tasa</th>
	        <th>Fecha<br>
              Valor</th>
	        <th>Estado</th>
  </tr>
  </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                ewd0332Help.initRow();
				int k = 1;
                while (ewd0332Help.getNextRow()) {
                    if (ewd0332Help.getFlag().equals("")) {
                    		out.println(ewd0332Help.getRecord());
							k ++;
                    }
                }
    %> 
</TABLE>

</div>
</td>
</tr>
</table>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
</SCRIPT>
<%
   }  
%>

</form>
</body>
</html>

