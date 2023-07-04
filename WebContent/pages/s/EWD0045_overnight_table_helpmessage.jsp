<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head><title>Overnight  Rate Table Search</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0045Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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
	if ( ewd0045Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"><b> No hay resultados para su criterio de busqueda </b></div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
    <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
			     <th width="30%">Código</th>
				  <th width="30%">Moneda</th>
			     <th width="40%">Fecha</th>
			  </TR>

    <%
                ewd0045Help.initRow();
                while (ewd0045Help.getNextRow()) {
                    if (ewd0045Help.getFlag().equals("")) {
                    		out.println(ewd0045Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>