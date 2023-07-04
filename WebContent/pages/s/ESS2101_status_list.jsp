<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Customer List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "CCRStatusList" class= "com.datapro.generics.BeanList" scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
<!--

 function goSubmit(opt) {
    document.forms[0].ACTION.value = opt;
    document.forms[0].submit();
  }
  
</script>

</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } else {
   if (userPO.getRedirect().equals("DO_PRINT")) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showReceipt()");
     out.println("</SCRIPT>"); 
     userPO.setRedirect("");    
   }
 } 
%>

<BODY>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2101">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <h3 align="center">Estatus Tarjeta de Credito Bloqueado Online<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="status_list.jsp,ESS2101"> 
  </h3>
<hr size="4">

 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('N')">Nuevo</a></TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('U')">Modificar</a></TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('D')">Eliminar</a></TD>
    </TR>
  </TABLE>
  
 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
		      <TH ALIGN=CENTER width=10%>[ ] </TH>
		      <TH ALIGN=CENTER width=20%>Estatus Cuenta</TH>
		      <TH ALIGN=CENTER width=20%>Estado Tarjeta</TH>
		      <TH ALIGN=CENTER width=20%>Descripción</TH>
		  </TR>
<%
   datapro.eibs.beans.ESS210101Message msg = new datapro.eibs.beans.ESS210101Message();
   CCRStatusList.initRow();
   while(CCRStatusList.getNextRow()){
    	msg = (datapro.eibs.beans.ESS210101Message) CCRStatusList.getRecord();
%>
		  <TR>
		     <TD ALIGN=CENTER><INPUT TYPE="RADIO" NAME="STS" VALUE="<%= msg.getE01ACCSTS() + "|" + msg.getE01CCRSTS() %>"></TD>
		     <TD ALIGN=CENTER><%= msg.getE01ACCSTS() %></TD>
		     <TD ALIGN=CENTER><%= msg.getE01CCRSTS() %></TD>
		     <TD ALIGN=CENTER><%= msg.getE01CCDSC() %></TD>
		  </TR>
<%    	
   }
%> 
 		</table>
  </table>
</FORM>
</BODY>
</HTML>
