<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tablas de Cargos para Moneda Extranjera</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0111Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0111Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b>  No hay resultados para su criterio 
        de busqueda </b></font></div>
      </TD></TR>
   		</TABLE>
<%   		
		}
	else {
%>	
   <table class="tableinfo" style="width:95%" ALIGN=CENTER>
      <tr id="trdark">
			<th>Table Code</th>
			<th>Name</th>
			<th>Type</th>
			<th>Currency</th>
		</tr>
<%
                ewd0111Help.initRow();
                while (ewd0111Help.getNextRow()) {
                    if (ewd0111Help.getFlag().equals("")) {
                    		out.println(ewd0111Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>		