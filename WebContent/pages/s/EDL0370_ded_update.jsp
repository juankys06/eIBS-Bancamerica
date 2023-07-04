<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Close</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<jsp:useBean id="deduct" class="datapro.eibs.beans.EDL037001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>
<%
   int row;
   String act = "";
   try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
   catch (Exception e) {
      row = 0;
    }
   try {
      act = request.getParameter("ACT");
    }
   catch (Exception e) {
      act = "0";
    }
 %>
<SCRIPT LANGUAGE="JavaScript">
 
  var tb = top.opener.document.all["TBDEDUCT"];
  var row = <%= row %>;
  var actCode = "<%= act %>";
  var loan = "<%= deduct.getE01DEAACC().trim() %>";
  var cod = "<%= deduct.getE01DLICDE().trim() %>";
  
  if (tb != null) { 
    
    if (actCode == "N") {
      if (tb.rows.length == 2) {
       if (tb.rows[1].cells.length < 5) tb.deleteRow(1);
      } 
      tb.insertRow();
      var idx= tb.rows.length - 1;      
      for(i=0; i<5; i++) {
        tb.rows[idx].insertCell();
       }
      tb.rows[idx].cells[0].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLICDE().trim() %></a></div>";
      tb.rows[idx].cells[1].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLINME().trim() %></a></div>";
      tb.rows[idx].cells[2].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIREF().trim() %></a></div>";
      tb.rows[idx].cells[3].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIDPM().trim() %></a></div>";
      tb.rows[idx].cells[4].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIFAC().trim() %></a></div>";            
      tb.rows[idx].id = "trclear";      
    } 
    else if (actCode == "D") {
      tb.deleteRow(row);
      if (tb.rows.length == 1) {
       tb.insertRow();
       tb.rows[1].insertCell();
       tb.rows[1].cells[0].colSpan = 5;
       tb.rows[1].cells[0].innerHTML = "<h5 align=center>No existen Deducciones asignadas</h5>";
       tb.rows[1].id = "trclear";
      }
    } 
    else if (actCode == "M") { 
      tb.rows[row].cells[0].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLICDE().trim() %></a></div>";
      tb.rows[row].cells[1].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLINME().trim() %></a></div>";
      tb.rows[row].cells[2].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIREF().trim() %></a></div>";
      tb.rows[row].cells[3].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIDPM().trim() %></a></div>";
      tb.rows[row].cells[4].innerHTML = "<div align=center><a href='javascript:getDeduct(&#39;" + loan + "&#39;,&#39;" + cod + "&#39;" + ")'><%= deduct.getE01DLIFAC().trim() %></a></div>";            
      }
     tb.refresh();
  }
  top.close();
</SCRIPT>

</body>
</html>
