<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Users</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0330Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1) {
//var formLength= top.opener.document.forms[0].elements.length;
//for(n=0;n<formLength;n++){
//var elementName= top.opener.document.forms[0].elements[n].name;
//	if(elementName == top.opener.fieldName){
//  		top.opener.document.forms[0].elements[n].value = code1;
//  		top.opener.document.forms[0].elements[n-1].value = code2;
//  		top.opener.document.forms[0].elements[n-2].value = code3;
//  		break;
//	}
// }
  top.opener.document.forms[0][top.opener.fieldName].value = code1;
  top.opener.document.forms[0][top.opener.fieldName].focus();
  top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( EWD0330Help.getNoResult() ) {
 %> 
<div align="center"></div>
<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b>  No hay resultados para su
		criterio de busqueda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Dealer<BR>
      ID</th>
    <th>Nombre </th>
    <th>Centro de <BR>
      Costo</th>
  </tr>
  <%
                EWD0330Help.initRow();
                while (EWD0330Help.getNextRow()) {
                    if (EWD0330Help.getFlag().equals("")) {
                    		out.println(EWD0330Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>