<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Busqueda de Sucursales</title>
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0013Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code,desc) {
var form = top.opener.document.forms[0];
 	form[top.opener.fieldName].value = code;
	if (top.opener.fieldDesc !== ""){
	  	if(form[top.opener.fieldDesc])
	  		form[top.opener.fieldDesc].value = desc;
	}
 	top.close();
}
//-->
</script>
</head>
<body>
<%
if ( ewd0013Help.getNoResult() ) {
 %>
   		<TABLE  id="mainTable" class="tbenter" width=100% height=100%>
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

			<table class="tableinfo" style="width:95%" ALIGN=CENTER>
			 <tr id="trdark">
			  <th  ALIGN=CENTER nowrap width=50>Numero</th>
			  <th  ALIGN=CENTER nowrap width=250>Nombre</th>
			  <th  ALIGN=CENTER nowrap width=250>Ciudad</th>
			 </tr>
		
<%		
	 ewd0013Help.initRow();
                while (ewd0013Help.getNextRow()) {
                    if (ewd0013Help.getFlag().equals("")) {
                    		out.println(ewd0013Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>

</body>
</html>			
		