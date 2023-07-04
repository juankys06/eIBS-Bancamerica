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


<jsp:useBean id= "ESS0035List" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "ESS0035LastWord" class= "java.lang.String"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
<!--

 function goSubmit(op) {
    document.forms[0].SCREEN.value = 200;
    document.forms[0].ACTION.value = op;
    document.forms[0].submit();
  }
  
 function goNextList() {
    document.forms[0].SCREEN.value = 100;
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

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS0035">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="">
  <h3 align="center">Palabras Reservadas para Claves de Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="reserved_word_list,ESS0035"> 
  </h3>
<hr size="4">

 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:goSubmit('D')">Borrar</a></TD>
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:goSubmit('A')">Agregar</a></TD>
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:goSubmit('U')">Cargar</a></TD>
    </TR>
  </TABLE>
  
 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
              <TH ALIGN=CENTER width=2%>[ ]</TH>
		      <TH ALIGN=CENTER width=10%>Palabra</TH>
		      <TH ALIGN=CENTER width=2%>[ ]</TH>
		      <TH ALIGN=CENTER width=10%>Palabra</TH>
		      <TH ALIGN=CENTER width=2%>[ ]</TH>
		      <TH ALIGN=CENTER width=10%>Palabra</TH>
		      <TH ALIGN=CENTER width=2%>[ ]</TH>
		      <TH ALIGN=CENTER width=10%>Palabra</TH>
		      <TH ALIGN=CENTER width=2%>[ ]</TH>
		      <TH ALIGN=CENTER width=10%>Palabra</TH>
		  </TR>

<%
			ESS0035List.initRow(); 
            while (ESS0035List.getNextRow()) {
           		out.println(ESS0035List.getRecord());
             }        
%> 
 		</table>
  </table>
     
<INPUT TYPE=HIDDEN NAME="IWord" VALUE="<%= ESS0035LastWord %>">
<% if(ESS0035List.getShowNext()){ %>
 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=right class=TDBKG> <a href="javascript:goNextList()">Mas..</a> 
      </TD>
    </TR>
  </TABLE>
<% } %>  
</FORM>

</BODY>
</HTML>
