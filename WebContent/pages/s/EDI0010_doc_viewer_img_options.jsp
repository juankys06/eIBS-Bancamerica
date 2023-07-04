<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Opciones</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript">
var isize = <%= request.getParameter("IMG_SIZE")%>;
<!--
function loadPage() {
   parent.blank_viewer.window.location.href="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_blank.jsp?PAGE_NAME=<%= request.getParameter("PAGE_NAME")%>&IMG_SIZE=" + isize;
}

function viewer_exit() {
	if ( window.name !="main" ) top.close(); 
	else window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

//    zoom_in
function viewer_zoom_in() {
  isize = isize + 50;
  loadPage();
}

//    zoom_out
function viewer_zoom_out() {
  isize = isize - 50;
  loadPage();
}

//    Print
function viewer_print() {
  parent.main_viewer.focus()
  parent.main_viewer.print()
}
 
//    Delete
function viewer_delete() {
	top.parent.window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=10&DOC_NAME=<%= request.getParameter("DOC_NAME")%>&PAGE_NAME=<%= request.getParameter("PAGE_NAME")%>";
}   
   
//-->
</SCRIPT>

</head>

<body>
<% 
		String type = (request.getParameter("TYPE") == null) ? "" : request.getParameter("TYPE");
		
		if (!type.equals("PDF")) {
%>
		  <TABLE class="tbenter" width="100%">
		    <TR> 
		      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:viewer_zoom_in()"><b>Zoom In</b></a> 
		      </TD>
		      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:viewer_zoom_out()"><b>Zoom Out</b></a> 
		      </TD>
		      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:viewer_print()"><b>Imprimir</b></a> 
		      </TD>
		      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:viewer_exit()" ><b>Salir</b></a> 
		      </TD>
		    </TR>
		  </TABLE>
<%
		}
%>		   	
</body>
</html>
