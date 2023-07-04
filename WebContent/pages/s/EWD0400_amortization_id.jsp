<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head>
<title>Busqueda de Moneda</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0400Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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
	if ( ewd0400Help.getNoResult() ) {
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
			  <th ALIGN=CENTER  nowrap width=5%>Banco</th>
			  <th ALIGN=CENTER  nowrap width=10%>Agencia</th>
			  <th  ALIGN=CENTER  nowrap width=20%>Act. Fijo/Amort.</th>
			  <th  ALIGN=CENTER  nowrap width=20%>Marca</th>
			  <th  ALIGN=CENTER  nowrap width=20%>Modelo</th>
			  <th  ALIGN=CENTER  nowrap width=10%>Estado</th>
			  <th  ALIGN=CENTER  nowrap width=5%>Clase</th>
			  <th  ALIGN=CENTER  nowrap width=5%>Localización</th>
			 </tr>
		
<%
                String BNK = (String)request.getAttribute("BNK");
                String BRN = (String)request.getAttribute("BRN");
                ewd0400Help.initRow();
                while (ewd0400Help.getNextRow()) {
                    if (ewd0400Help.getFlag().equals("")) {
                    		out.println(ewd0400Help.getRecord());
                    }
                }
    %> 

  </TABLE>
	<TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
		   <TR>
		      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
		        if ( ewd0400Help.getShowPrev() ) {
		      			int pos =ewd0400Help.getFirstRec() - 21;
		      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0400?FromRecord=" + pos + "&BNK=" + BNK + "&BRN=" + BRN + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
		        }
		%> </TD>
		 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
		        if ( ewd0400Help.getShowNext() ) {
		      			int pos = ewd0400Help.getLastRec();
		      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0400?FromRecord=" + pos + "&BNK=" + BNK + "&BRN=" + BRN + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
		
		        }
		%> </TD>
	 	</TR>
	 </TABLE>
<%
   }
%>
</body>
</html>			
