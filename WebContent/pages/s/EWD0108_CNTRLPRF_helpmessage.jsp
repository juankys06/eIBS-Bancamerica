<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<head>
<head><title>Busqueda</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0108Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code,amt) {
var form= top.opener.document.forms[0];
  form[top.opener.fieldName].value = code;
  form[top.opener.fieldDesc].value = amt; 
top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0108Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"><b> No hay resultados para su b&uacute;squeda</b></div>
      </TD></TR>
   		</TABLE>
<%   		
		}
	else {
%>	

		
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>C&oacute;digo</th>
    <th>Descripci&oacute;n</th>
  </tr>
  <%
                ewd0108Help.initRow();
                while (ewd0108Help.getNextRow()) {
                    if (ewd0108Help.getFlag().equals("")) {
                    		out.println(ewd0108Help.getRecord());
                    }
                }
    %> 
</TABLE>

<%
   }
%>
</body>
</html>	