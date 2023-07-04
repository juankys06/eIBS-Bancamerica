<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head><title>Origen de los Fondos, Concepto</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0035Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1, code2, code3) {
//n-1 code2; n-2 code3
 var form= top.opener.document.forms[0];
 form[top.opener.fieldName].value = code1;
 form[top.opener.fieldAux1].value = code2;
 form[top.opener.fieldAux2].value = code3;
 form[top.opener.fieldAux2].focus();
 top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( ewd0035Help.getNoResult() ) {
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
			 <th>Codigo de Operacion</th>
			 <th>Descripcion</th>
			 <th>Cuenta Contable</th>
			</tr>	
			<TR>
			<td><a href="javascript:enter('','','')">NINGUNO</a></td>
			<td><a href="javascript:enter('','','')">NINGUNO</a></td>
			<td><a href="javascript:enter('','','')">NINGUNO</a></td>
			</TR>
<%
                ewd0035Help.initRow();
                while (ewd0035Help.getNextRow()) {
                    if (ewd0035Help.getFlag().equals("")) {
                    		out.println(ewd0035Help.getRecord());
                    }
                }
    %> 

  </TABLE>
<%
   }  
%>
</body>
</html>