<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tabla de Causales de Devolucion </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0110Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
//function enter(code,desc) {
//var formLength= top.opener.document.forms[0].elements.length;
//for(n=0;n<formLength;n++){
//var elementName= top.opener.document.forms[0].elements[n].name;
//	if(elementName == top.opener.fieldName){
//  		top.opener.document.forms[0].elements[n].value = code;
//  		break;
//	}
// }
//top.close();
// }
 
function enter(code,desc) {
var form = top.opener.document.forms[0];

	form[top.opener.fieldName].value = code;
	if(top.opener.fieldDesc !== ""){form[top.opener.fieldDesc].value = desc;}  		  		
	top.close();
}
 
//-->
</script>
</head>
<body>

<%
	if ( ewd0110Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b>No hay resultados para su criterio 
        de busqueda </b></font></div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
       <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
           <TR id="trdark"> 
			     <th width="10%">Causal de Devolucion </th>
			     <th width="20%">Moneda</th>
			     <th width="20%">Tipo de Devolucion</th>
			     <th width="50%">Descripcion</th>
			  </TR>

    <%
                ewd0110Help.initRow();
                while (ewd0110Help.getNextRow()) {
                    if (ewd0110Help.getFlag().equals("")) {
                    		out.println(ewd0110Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>