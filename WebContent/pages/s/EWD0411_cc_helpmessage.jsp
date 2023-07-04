<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tarjetas</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0411Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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
	if ( EWD0411Help.getNoResult() ) {
 %>
   		
<h4 align="center">Tarjetas</h4>
<TABLE class="tbenter" width=100% height=100%>
   <TR>
      <TD> 
      	<div align="center"> <b> <FONT size="3">No hay resultados para su
		criterio de busqueda</FONT> </b></div>
      </TD>
    </TR>
</TABLE>
<%  
	}
	else {
%>	  
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th >Tarjetas </th>
   	<th >Nombre </th>
  </tr>
  <%
                EWD0411Help.initRow();
                while (EWD0411Help.getNextRow()) {
                    if (EWD0411Help.getFlag().equals("")) {
                    		out.println(EWD0411Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>