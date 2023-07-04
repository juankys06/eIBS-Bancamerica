<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Busqueda de Bancos Nacionales ACH</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0012Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code,desc) {
var form = top.opener.document.forms[0];
 	form[top.opener.fieldName].value = code;
	top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0012Help.getNoResult() ) {
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

			<table class="tableinfo" style="width:95%" ALIGN=CENTER>
			 <tr id="trdark">
			  <th ALIGN=CENTER  nowrap width=10%>Codigo</th>
			  <th ALIGN=CENTER  nowrap width=15%>Nombre del banco</th>
			  
			 </tr>
		
<%
                ewd0012Help.initRow();
                while (ewd0012Help.getNextRow()) {
                    if (ewd0012Help.getFlag().equals("")) {
                    		out.println(ewd0012Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>			
