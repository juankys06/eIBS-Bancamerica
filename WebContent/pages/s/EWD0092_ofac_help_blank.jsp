<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<TITLE></TITLE>
</head>
<body>
<script>
function goAction(op) {
      top.opener.goAction(op);
      top.close();
}
 
function goExit(){
  top.close();
}
</script>

   <% if (request.getParameter("shrCategory").equals("ALL")){  %>
  <TABLE class="tbenter" width="97" height="40">
    <TR>

       <% if (!request.getParameter("shrAction").equals("INQ")){ %>

      <TD ALIGN=CENTER width="33%" height="43"> <img class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/approve.gif" 
onmousedown="this.className='imgfilterpress'" onmouseup="this.className='imgfilter'" onClick="goAction('Z')">
</TD>

	<% } %>
      <TD ALIGN=CENTER width="34%" height="43"><img class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/EXIT.gif" 
onmousedown="this.className='imgfilterpress'" onmouseup="this.className='imgfilter'" onClick="goExit()"> </TD>


    </TR>
  </TABLE>
  <% } %>

</body>
</html>
