<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Treasury</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0326Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
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
	if ( EWD0326Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay resultados para su criterio de busqueda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Counterparty <br>
      Name </th>
    <th>Account</th>
    <th>Type</th>
    <th>CCY</th>
    <th>Amount</th>
    <th>Product</th>
  </tr>
  <%
                EWD0326Help.initRow();
                while (EWD0326Help.getNextRow()) {
                    if (EWD0326Help.getFlag().equals("")) {
                    		out.println(EWD0326Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>