<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tablas de Cargos en Cuentas de Detalle</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0102Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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
	if ( ewd0102Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b> No hay resultados para su criterio 
        de busqueda  </b></font></div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
      
    <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
           <TR id="trdark"> 
			     <th width="20%">Tabla</th>
			     <th width="10%">Banco</th>
				  <th width="20%">Tipo</th>
			     <th width="40%">Descripcion</th>
			  </TR>

    <%
                ewd0102Help.initRow();
                while (ewd0102Help.getNextRow()) {
                    if (ewd0102Help.getFlag().equals("")) {
                    		out.println(ewd0102Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>