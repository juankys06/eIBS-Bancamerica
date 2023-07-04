<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Instrumentos </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0308Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1, code2,code3) {
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
  top.opener.document.forms[0][top.opener.fieldAux2].value = code3;
  top.opener.document.forms[0][top.opener.fieldAux1].focus();
  top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( EWD0308Help.getNoResult() ) {
 %>
   		
<h4 align="center">Lista de Instrumentos</h4>
<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay resultados para su busqueda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th >Code </th>
    <th> 
      <div align="left">Descripción </div>
    </th>
    <th>Moneda</th>
    <th> 
      <div align="center">Tipo</div>
    </th>
    <th> 
      <div align="left">Símbolo </div>
    </th>
    <th> 
      <div align="left">ISIN </div>
    </th>
    <th> 
      <div align="left">CUSIP </div>
    </th>
    <th> 
      <div align="left">Estado </div>
    </th>
    <th> 
      <div align="left">Emisor </div>
    </th>
    <th> 
      <div align="left">Fecha de <br>
        Emisión </div>
    </th>
  </tr>
  <%
                EWD0308Help.initRow();
                while (EWD0308Help.getNextRow()) {
                    if (EWD0308Help.getFlag().equals("")) {
                    		out.println(EWD0308Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>