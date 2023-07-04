<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cliente
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "pdfList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="javascript">
  var opened = "";

  function showPDF() {

	CenterNamedWindow('','pdf',600,500,7);
	document.forms[0].submit();

  }
  
  function goAction(pdf, action, copies) {

    document.forms[0].pdfName.value = pdf;
    document.forms[0].action.value = action;
    document.forms[0].copies.value = copies;
	if (opened.indexOf(pdf) != -1) 
		alert("No puede abrir el documento nuevamente sin hacer otra petición");
	else
		showPDF();
    opened = opened + pdf;
  }
  
<%
boolean option = true;
if ( !userPO.getPurpose().equals("APPROVAL_INQ") ) {

if ( userPO.getOption().equals("RT") ) {
%>
	   builtNewMenu(rt_m_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_m_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	builtNewMenu(cd_m_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
%>
	builtNewMenu(ln_m_opt);
   <%   
 }
else if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_personal_opt);
  <%      
 }
else if ( userPO.getOption().equals("CLIENT_C") ) {
   %>
		builtNewMenu(client_corp_opt);
  <%      
 }
else if ( userPO.getOption().equals("LC") ) {
   %>
		builtNewMenu(lc_i_opt);
  <%      
 }
else if ( userPO.getOption().equals("DV") ) {
   %>
		builtNewMenu(coll_i_opt);
   <%
   }
 else {
     option = false;
   }  
   }

else
{   
   if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_a_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_a_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	builtNewMenu(cd_a_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
%>
	builtNewMenu(ln_a_opt);
   <%   
 }
else if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_ap_personal_opt);
  <%      
 }
else if ( userPO.getOption().equals("CLIENT_C") ) {
   %>
		builtNewMenu(client_ap_corp_opt);
  <%      
 }
 else {
     option = false;
   }  
   }

%>

</script>

</HEAD>

<BODY >

<%
 if ( option ) {
%>
<SCRIPT Language="Javascript"> 
   initMenu(); 
</SCRIPT>
<%
}
%>
  

<h3 align="center">Formularios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pdf_list.jsp,EFRM000"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000FDF" target="pdf">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
<INPUT TYPE=HIDDEN NAME="pdfName" VALUE="">
<INPUT TYPE=HIDDEN NAME="action" VALUE="">
<INPUT TYPE=HIDDEN NAME="copies" VALUE="">


<% if (pdfList.getNoResult()) { %>
   		<TABLE class="tbenter" width=100% height=90%>
   		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay resultado que corresponda a su criterio de búsqueda. 
        </b></font> </div>
      </TD></TR>
   		</TABLE>
<% } else {	%>
	<TABLE class="tableinfo" ALIGN=CENTER >
          <% 
          		datapro.eibs.beans.EFRM00001Message msg = null;
				boolean firstTime = true;
				String chk = "";
                pdfList.initRow();
                while (pdfList.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                 	msg = (datapro.eibs.beans.EFRM00001Message) pdfList.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="ROW" value="<%= pdfList.getCurrentRow() %>" <%=chk%> >
            </td>
            <td NOWRAP align=LEFT width="80%"><A HREF="javascript:goAction('<%= msg.getE01APFPTH() %>','<%= msg.getE01APFOPE() %>','<%= msg.getE01APFCPI() %>','<%= msg.getE01APFDDS() %>')"><%= msg.getE01APFDSC() %></A></td>
          </tr>
		<% } %>
	</TABLE>
	
<SCRIPT language="JavaScript">
     if(<%= pdfList.getLastRec()%> == 0) 
     	goAction('<%= msg.getE01APFPTH() %>','<%= msg.getE01APFOPE() %>','<%= msg.getE01APFCPI() %>','<%= msg.getE01APFDDS() %>');
     
</SCRIPT>
<% } %>


</FORM>

</BODY>
</HTML>
