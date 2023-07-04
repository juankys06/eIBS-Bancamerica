<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Busqueda de Inventario de Documentos</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0165Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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
	if ( ewd0165Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <font size="3"><b>No hay resultados para su criterio 
        de busqueda </b></font></div>
      </TD></TR>
   		</TABLE>
<%   		
		}
	else {
%>	

			<table class="tableinfo" style="width:95%" ALIGN=CENTER>
			 <tr id="trdark">
			  <th ALIGN=CENTER  nowrap width=10%>Código</th>
			  <th ALIGN=CENTER  nowrap width=85%>Descripción</th>
			 </tr>
		
<%
                ewd0165Help.initRow();
                while (ewd0165Help.getNextRow()) {
                    if (ewd0165Help.getFlag().equals("")) {
                    		out.println(ewd0165Help.getRecord());
                    }
                }
    %> 

  </TABLE>
  
    <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0165Help.getShowPrev() ) {
      			int pos =ewd0165Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0165?FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0165Help.getShowNext() ) {
      			int pos = ewd0165Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0165?FromRecord=" + pos + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
  

<%
   }
%>

</body>
</html>			
