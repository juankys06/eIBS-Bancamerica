<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tramos y Tasas Preferenciales</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0380Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code1) {

  top.opener.document.forms[0][top.opener.fieldName].value = code1;
  top.opener.document.forms[0][top.opener.fieldName].focus();
  top.close();
 }
//-->
</script>
</head>
<body>
<%
	if ( EWD0380Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay datos correspondientes a su criterio de búsqueda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Tabla</th>
    <th>Descripción</th>
    <th>Fecha</th>
  </tr>
  <%
                EWD0380Help.initRow();
                while (EWD0380Help.getNextRow()) {
                    if (EWD0380Help.getFlag().equals("")) {
                    		out.println(EWD0380Help.getRecord());
                    }
                }
    %> 
</TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( EWD0380Help.getShowPrev() ) {
      			int pos =EWD0380Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0380?FromRecord=" + pos  + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0380Help.getShowNext() ) {
      			int pos = EWD0380Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0380?FromRecord=" + pos +   "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%
   }  
%>
</body>
</html>