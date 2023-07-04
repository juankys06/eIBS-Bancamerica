<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tabla de Bancos para Remesas</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0702Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">

function enter(code, desc) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
	}
	if(elementName == top.opener.fieldDesc){
  		top.opener.document.forms[0].elements[n].value = desc;
  	}	

 }
top.close();
 }

function enter2(code,amt) {
alert(code);
alert(amt);

var form= top.opener.document.forms[0];
  form[top.opener.fieldName].value = code;
  form[top.opener.fieldDesc].value = amt; 
top.close();
 }

</script>

</head>
<body>
<%
	if ( EWD0702Help.getNoResult() ) {
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
    <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
           <TR id="trdark"> 
			     <th width="20%">Banco</th>
			     <th width="20%">Suc.</th>
			     <th width="20%">Loc.</th>
     		  	 <th width="20%">Moneda</th>
			     <th width="20%">Dias</th>
			     <th width="20%">Tipo</th>
			     <th width="30%">Descripción</th>
			  </TR>

    <%
                EWD0702Help.initRow();
                while (EWD0702Help.getNextRow()) {
                    if (EWD0702Help.getFlag().equals("")) {
                    		out.println(EWD0702Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>