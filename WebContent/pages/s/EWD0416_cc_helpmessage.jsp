<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tipo de Comisión</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0416Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers

function enter(code,desc) {
	var form = top.opener.document.forms[0];

  	if(top.opener.fieldDesc != ""){form[top.opener.fieldDesc].value = desc;}
	form[top.opener.fieldName].value = code;
	if (form[top.opener.fieldName].type != "hidden") form[top.opener.fieldName].focus();
		else form[top.opener.fieldDesc].focus();
	form[top.opener.fieldName].select();
  		
	top.close();
}
 
//-->
</script>
</head>
<body>
<%
	if ( EWD0416Help.getNoResult() ) {
 %>
   		
<h4 align="center">Periodo de Cobro</h4>
<TABLE class="tbenter" width=100% height=100%>
   <TR>
      <TD> 
      	<div align="center"> <b> No hay resultados para su criterio de Búsqueda </b></div>
      </TD>
    </TR>
</TABLE>
<%  
	}
	else {
%>	  
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th >Código</th>
    <th> <div align="center">Descripción</div></th>
  </tr>
  <%
                EWD0416Help.initRow();
                while (EWD0416Help.getNextRow()) {
                    if (EWD0416Help.getFlag().equals("")) {
                    		out.println(EWD0416Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>