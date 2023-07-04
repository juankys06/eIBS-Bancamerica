<html>
<head>
<title>OFAC Match List</title>

<!-- frames -->
</head>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)


</SCRIPT>
<%
  String shrAcc = request.getParameter("shrAcc");
  String shrCategory = request.getParameter("shrCategory"); 
  String shrAction = request.getParameter("shrAction"); %>

<frameset  rows="190,573*" cols="*"> 
  <FRAME name="query" <% out.println("src=\""+request.getContextPath()+"/pages/s/EWD0092_ofac_help_helpmessage.jsp?shrAcc="+shrAcc+"&shrCategory="+shrCategory+"&FromRecord=0&shrAction="+shrAction+" \""); %> marginwidth="0" marginheight="0" scrolling="no" frameborder="0" border="0" src="file:///C:/Documents and Settings/ealmonte/My Documents/newpage1.jspl">
  <FRAME name="results" <% out.println("src=\""+request.getContextPath()+"/pages/s/EWD0092_ofac_help_blank.jsp?shrAcc="+shrAcc+"&shrCategory="+shrCategory+"&FromRecord=0&shrAction="+shrAction+" \""); %> marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" border="0" noresize src="file:///C:/Documents and Settings/ealmonte/My Documents/newpage1.jspl">
<NOFRAMES><P>To view this page, you need a browser that supports frames.</P></NOFRAMES></frameset>
<noframes></noframes>







</html>



