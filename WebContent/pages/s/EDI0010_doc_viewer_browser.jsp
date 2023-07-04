<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Documentos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "listImg" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>

<SCRIPT language="javascript">

  
  function loadPage(num, typ) {
  		var params = "DOC_NAME=<%= request.getParameter("DOC_NAME")%>&PAGE_NAME=" + num + "&IMG_SIZE=370" + "&TYPE=" + typ;
		parent.blank_viewer.window.location.href="<%=request.getContextPath()%>/pages/s/EDI0010_doc_viewer_blank.jsp?"+params;
  }

// Delete
	function deletePage(name, uid) {
		if (confirm("Esta seguro que desea eliminar este documento?")) {
			parent.window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=10&DOC_NAME=<%= request.getParameter("DOC_NAME")%>&PAGE_NAME=" + name + "&UID=" + uid;
		} else {
			return;
		}
	}

</SCRIPT>

</HEAD>

<BODY>

<h3 align="center">Documentos</h3>
<FORM >
<%
  listImg.setCurrentRow(0);
%> 
  <TABLE class="tbenter" align="center" width="190" border="1" cellpadding="2" cellspacing="2">
    <TR bgcolor="#CCCCCC"> 
      <TD ALIGN=CENTER width="95%"> <b><%= listImg.getRecord(1)%>
        </b> <b> </b></TD>
    </TR>
  </TABLE>

  <TABLE width="190" border="0" class="tbenter" align="center">
    <%
          listImg.initRow();
		  int pagenum = 0;
		  String imgName = "";
		  String pagename = "";
		  String pagedate = "";
		  String seqnum = "";
		  String uid = "";
          while (listImg.getNextRow()) {
			pagename = listImg.getRecord(0);
			uid = listImg.getRecord(5);
			if (!seqnum.equals(listImg.getRecord(4))) {
				seqnum = listImg.getRecord(4);
				pagedate = listImg.getRecord(3);
				pagenum = 0;

    %> 
    <TR bgcolor="#bbbbbb" > 
      <TD ALIGN=LEFT> 
        <table width="100%" border="1" cellspacing="0" cellpadding="2">
          <tr>
            <td>Secuencia<b> <%= seqnum + "" %> </b>: <%= pagedate %></td>
          </tr>
        </table>
      </TD>
    </TR>
    <%
			} 
			imgName = listImg.getRecord(5) + "_" + seqnum + "." + listImg.getRecord(2);
			pagenum++;
    %> 
    <TR> 
      <TD ALIGN=CENTER>Página<b> <%= pagenum + "" %> </b></TD>
    </TR> 
    <TR> 
      <TD ALIGN=CENTER> <br>
   		<a href="javascript:loadPage('<%=imgName%>', '<%=listImg.getRecord(2)%>')">'<b>Archivo <%= listImg.getRecord(2)%></b>'</a><br>
        <br>
        <a href="javascript:deletePage('<%=pagename%>', '<%=uid%>')"><font color="#ff0000"><b>Eliminar</b></font></a>
        <br>
        <br>
      </TD>
    </TR>
    <%
        }
        listImg.setCurrentRow(0);
    	pagename = listImg.getRecord(0);
    	String type = listImg.getRecord(2);
    	uid = listImg.getRecord(5);
  %> 
  </TABLE>
</FORM>
  <SCRIPT language="javascript">
		loadPage("<%= imgName %>", "<%=type%>");
  </SCRIPT>
</BODY>
</HTML>
