<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tarjetas</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0413Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers

function enter(code) {
var form = top.opener.document.forms[0];
 form[top.opener.fieldName].value = code;
 top.close();
 }
 
//-->
</script>
</head>
<body>
<%
	if ( EWD0413Help.getNoResult() ) {
 %>
   		
<h4 align="center">Tarjetas</h4>
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
    <th >Tarjeta</th>
    <th> <div align="left">Fecha de Entrada </div></th>
    <th> <div align="left">Fecha de Vencimiento </div></th>
  </tr>
  <%
                EWD0413Help.initRow();
                while (EWD0413Help.getNextRow()) {
                    if (EWD0413Help.getFlag().equals("")) {
                    		out.println(EWD0413Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>