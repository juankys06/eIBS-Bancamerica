<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
User List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"   scope="session"/>
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

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2040">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="">
  <h3 align="center">Aprobar Usuarios de  Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="activate_list.jsp,ESS2040"> 
  </h3>
<hr size="4">

 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goSubmit('Y')">Aprobar</a> </TD>
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goSubmit('N')">Rechazar</a> </TD>
    </TR>
  </TABLE>
  
 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
		      <TH ALIGN=CENTER width=10%> </TH>
		      <TH ALIGN=CENTER width=20%>Usuario</TH>
		      <TH ALIGN=CENTER width=20%>Numero de Cliente</TH>
		      <TH ALIGN=CENTER width=30%>Nombre</TH>
		      <TH ALIGN=CENTER width=10%>Estatus</TH>
		      <TH ALIGN=CENTER width=10%>Tipo</TH>
		  </TR>

<%
                appList.initRow();
                int k=1;
                while (appList.getNextRow()) {
                    if (appList.getFlag().equals("")) {
                    		out.println(appList.getRecord());
                    k++;
                    }        
                }
%> 
 		</table>
  </table>
     
<SCRIPT language="JavaScript">
	showChecked("CURRCODE");
</SCRIPT>

</FORM>

</BODY>
</HTML>
