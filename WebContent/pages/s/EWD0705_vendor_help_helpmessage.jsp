<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Pragma" content="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>
<TITLE></TITLE>
</HEAD>
<jsp:useBean id= "EWD0705Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<BODY>

<%@ page import="datapro.eibs.master.Util" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( EWD0705Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="20%">Numero</TH>
      <TH ALIGN=CENTER  nowrap width="80%">Nombre</TH>
     </TR>
    <%
                String codeflag = (String)request.getAttribute("codeflag");
                if (codeflag == null) codeflag = "";
                
                String NameSearch = (String)request.getAttribute("NameSearch");
                String FromRecord = (String)request.getAttribute("FromRecord");
             
                EWD0705Help.initRow();
                while (EWD0705Help.getNextRow()) {
                    if (EWD0705Help.getFlag().equals("")) {
                    		out.println(EWD0705Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( EWD0705Help.getShowPrev() ) {
      			int pos = EWD0705Help.getFirstRec() - 21;
      			 out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0705?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&codeflag=" + codeflag +  "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0705Help.getShowNext() ) {
      			int pos = EWD0705Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0705?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&codeflag=" + codeflag +  "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>