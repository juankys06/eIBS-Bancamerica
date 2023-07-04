<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Clasificacion</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0320Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
function enter(code1) {
  top.opener.document.forms[0][top.opener.fieldName].value = code1;
  top.opener.document.forms[0][top.opener.fieldName].focus();
  top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( EWD0320Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay datos que correspondan con su criterio de búsqueda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Tipo</th>
    <th>Descripcion</th>
    <th>Moneda </th>
    <th>Clasificacion</th>
  </tr>
  <%
                EWD0320Help.initRow();
                while (EWD0320Help.getNextRow()) {
                    if (EWD0320Help.getFlag().equals("")) {
                    		out.println(EWD0320Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>