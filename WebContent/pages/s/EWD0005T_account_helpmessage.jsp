<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Ayuda de Cuentas de Cliente</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0005Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
function enter(acc) {
  top.opener.document.forms[0][top.opener.fieldName].value = acc;
  top.opener.document.forms[0][top.opener.fieldName].focus();
  top.close();  
 }
</script>
</head>
<body>
<%
	if ( EWD0005Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		 <TR>
      		<TD> 
      		<div align="center"> <b> No hay datos que correspondan con su criterio de búsqueda </b></div>
      		</TD>
      	 </TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Moneda</th>
    <th>Cuenta</th>
    <th>Product</th>
    <th>Saldo</th>
    <th>Nombre</th>
  </tr>
  <%
                EWD0005Help.initRow();
                while (EWD0005Help.getNextRow()) {
                    if (EWD0005Help.getFlag().equals("")) {
                    		out.println(EWD0005Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>