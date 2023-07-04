<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript">

function enter(report) {
	top.opener.document.forms[0][top.opener.fieldAux1].value = report;
  	top.close();
 }

</script>

<title>IBS Reports Help</title>
</head>
<jsp:useBean id= "EWD0901Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<body>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<%
	if ( EWD0901Help.getNoResult() ) {
%>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> Lista vacia por el momento. </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	

<INPUT TYPE=HIDDEN NAME="REPORT" VALUE="">
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th>Nombre<br>de Reporte</th>
    <th>Descripción</th>
  </tr>
  <%
                EWD0901Help.initRow();
                while (EWD0901Help.getNextRow()) {
                    if (EWD0901Help.getFlag().equals("")) {
                    		out.println(EWD0901Help.getRecord());
                    }
                }
    %> 
</TABLE>

<TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> 
       <% if ( EWD0901Help.getShowPrev() ) {
       		int pos = EWD0901Help.getFirstRec() - 21;
      		out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0901?FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	   <% if ( EWD0901Help.getShowNext() ) {
      		int pos = EWD0901Help.getLastRec();
      		out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0901?FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
	 
<%
   }  
%>
</body>
</html>