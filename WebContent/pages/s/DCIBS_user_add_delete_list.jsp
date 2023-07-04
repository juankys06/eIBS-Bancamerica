<%@ taglib uri="/WEB-INF/display.tld" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-/W3C/DTD HTML 3.2 FINAL/EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="Author" CONTENT="Datapro">
<META NAME="Generator" CONTENT="NetObjects Fusion 4.0.1 for Windows">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<TITLE>Internet Banking System</TITLE>
<SCRIPT SRC="<%= request.getContextPath() %>/pages/javascripts/dibs.jsp"> </SCRIPT>

</HEAD>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<BODY topmargin="0" leftmargin="0">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<%
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

%>
<H3 align="center">DCIBS Entity User Inquiry <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_user_add_delete_list.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSUserFilter" >
<INPUT TYPE=HIDDEN NAME="PB" VALUE="I">
<br>
 <table  class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="3" width="100%" border="0" class="tbhead">
           <tr id="trdark">
            <td nowrap height="23" width="30%">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Entity ID: <%= request.getParameter("ENTITYID") %></div>
            </td>
		   </tr>
		</table>
     </td>
   </tr>
</table>     		   


<br>	

<table cellpadding=0 cellspacing=0 border=1 width=100% bordercolor="#6684A3" style="border-collapse: collapse">
  <tr>
<td>
<display:table width="90%" name="lsUsers" property="list" scope="session" decorator="com.datapro.eibs.internet.wrappers.UsersWrapper">
  <display:column property="userid"    align="center"  title="Select"/>
  <display:column property="USERID"    align="center"  title="User Id"/>
  <display:column property="FIRSTNAME" align="left"  title="First Name" />
  <display:column property="LASTNAME"  align="left"   title="Last Name"/>
  <display:column property="OUSER"     align="center"  title="Created By" />
  <display:column property="ODATE"     align="center"  title="Created Date"/>
  <display:column property="usersts"   align="center"  title="Status"/>
</display:table> 
</td>
</tr>
</table>

<BR>



  <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Submit" >
  </p>

</FORM>
</BODY>
</HTML>
 