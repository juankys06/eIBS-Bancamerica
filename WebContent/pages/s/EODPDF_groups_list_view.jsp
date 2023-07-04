<%@ taglib uri="/WEB-INF/display.tld" prefix="display" %>
<html>
<head>
<title>Groups</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>


<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "groupList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<br><br><br>


<H3 align="center">Seleccione un Grupo <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EODPDF_groups_list_view.jsp, JSEODPDF"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEODPDF" >
  	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
<%
if ( groupList.getNoResult() ) {
%>
	<TABLE class="tbenter" width=100% height=50%>
 		<TR>
      		<TD>
      			<div align="center"> <b>No existen grupos definidos</b></div>
      		</TD>
      	</TR>
   	</TABLE>
   	
<% } else { %>

	<TABLE class="tableinfo" style="width:70%" ALIGN=CENTER>
	    <TR id="trdark"> 
	      <TH ALIGN=CENTER  nowrap width="10%">[]</TH>
	      <TH ALIGN=LEFT  nowrap width="60%">Groupo ID</TH>
	    </TR>
	    <TR>
	     	<%
	        groupList.initRow(); 
	        while (groupList.getNextRow()) {
	           String msg = (String) groupList.getRecord();
	           out.print(msg);
	        }   
	     	%>               
		</TR>   		
	</TABLE>

	<TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    	<TR>
      		<TD WIDTH="50%" ALIGN=LEFT height="25"> 
      		<%
        		if ( groupList.getShowPrev() ) {
      				int pos = groupList.getFirstRec() - 50;
      			   	out.print("<A HREF=\""+request.getContextPath() +
      			   	          "/servlet/datapro.eibs.tools.JSEODPDF?SCREEN=1" + 
      			   	          "&FromRecord=" + pos + 
      			   	          "\" ><img src=\"" + 
      			   	          request.getContextPath() +
      			   	          "/images/e/previous_records.gif\" border=0></A>");
        		}
			%> 
			</TD>
 	  		<TD WIDTH="50%" ALIGN=RIGHT height="25"> 
 	  		<%       
        		if ( groupList.getShowNext() ) {
      				int pos = groupList.getLastRec();
      				out.print("<A HREF=\""+request.getContextPath() +
      			   	          "/servlet/datapro.eibs.tools.JSEODPDF?SCREEN=1" + 
      				          "&FromRecord=" + pos + 
      				          "\" ><img src=\"" +
      				          request.getContextPath() + 
      				          "/images/e/next_records.gif\" border=0></A>");

        		}
			%> 
			</TD>
	 	</TR>
	</TABLE>

<% } %>

<br>

<table class="tbenter" width=100% align=center>
	<tr> 
        <td><div align="center"><input id="EIBSBTN" type=submit name="Accept" value="Accept"></div></td>
		<td><div align="center"><input id="EIBSBTN" type=button name="Exit" value="Exit" onClick="window.location.href='<%=request.getContextPath()%>/pages/background.jsp'"></div></td>
    </tr>
</table>
  
</form>
</body>
</html>
