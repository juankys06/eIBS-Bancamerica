<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<html>
<head><title>Moneda Extranjera</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0306Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1, code2) {
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
  top.opener.document.forms[0][top.opener.fieldAux1].value = code2;
  top.opener.document.forms[0][top.opener.fieldAux1].focus();
  top.opener.document.forms[0][top.opener.fieldAux1].onchange();
  top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( EWD0306Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay datos que correspondan con su criterio de busqueda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th colspan="3">Agentes de Custodia</th>
  </tr>
  <TR id="trdark"> 
    <th>
      <div align="left">Código</div>
    </th>
    <th>
      <div align="center">Nombre</div>
    </th>
    <th>
      <div align="left">Número de <BR>Teléfono</div>
    </th>
  </tr>
  <%
                EWD0306Help.initRow();
                while (EWD0306Help.getNextRow()) {
                    if (EWD0306Help.getFlag().equals("")) {
                    		out.println(EWD0306Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>