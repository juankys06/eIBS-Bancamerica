<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head><title>Origen de los Fondos, Concepto</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0002pHelp" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1) {
//n-1 code2; n-2 code3
 var form= top.opener.document.forms[0];
 form[top.opener.fieldName].value = code1;
 form[top.opener.fieldName].focus();
 top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0002pHelp.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b> No hay resultados para su criterio 
        de busqueda   </b></font></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   <table class="tableinfo" style="width:95%" ALIGN=CENTER>
   		<tr id="trdark">
			 <th>Codigo de Producto</th>
			 <th>Descripcion</th>
			</tr>	
<%
                ewd0002pHelp.initRow();
                while (ewd0002pHelp.getNextRow()) {
                    if (ewd0002pHelp.getFlag().equals("")) {
                    		out.println(ewd0002pHelp.getRecord());
                    }
                }
    %> 

  </TABLE>
<%
   }  
%>
</body>
</html>