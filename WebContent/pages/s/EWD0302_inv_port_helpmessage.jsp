<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Portafolio</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0302Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1, code2) {

  top.opener.document.forms[0][top.opener.fieldName].value = code1;
  top.opener.document.forms[0][top.opener.fieldDesc].value = code2;
  top.opener.document.forms[0][top.opener.fieldDesc].focus();
  top.close();
 }
//-->
</script>
</head>
<body>
<h4 align="center">Lista de Portafolios</h4>
<%
	if ( EWD0302Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay resultados para su criterio de busqueda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th> 
      <div align="center">Principal<br>
        Customer </div>
    </th>
    <th> 
      <div align="center">Portafolio </div>
    </th>
    <th> 
      <div align="center"> Tipo</div>
    </th>
    <th> 
      <div align="center">Nombre Legal</div>
    </th>
  </tr>
  <%
                EWD0302Help.initRow();
                while (EWD0302Help.getNextRow()) {
                    if (EWD0302Help.getFlag().equals("")) {
                    		out.println(EWD0302Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>