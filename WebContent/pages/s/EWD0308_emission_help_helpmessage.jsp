<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var form= top.opener.document.forms[0];
form[top.opener.fieldName].value = code;
form[top.opener.fieldName].focus();
form[top.opener.fieldName].onchange();
top.close();
 }
//-->
</script>
<TITLE>Emisores</TITLE>
</head>
<jsp:useBean id= "ewd0308Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<FORM>
<%
	if ( ewd0308Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"><b>No hay resultados para su busqueda</b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>CUSIP</TH>
      <TH ALIGN=CENTER nowrap>Emisor</TH>
      <TH ALIGN=CENTER nowrap>Descripción</TH>
    </TR>
    <%
                String shrType = (String)request.getAttribute("shrType");
                ewd0308Help.initRow();
                while (ewd0308Help.getNextRow()) {
                    if (ewd0308Help.getFlag().equals("")) {
                    		out.println(ewd0308Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0308Help.getShowPrev() ) {
      			int pos =ewd0308Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0308?FromRecord=" + pos +  "&shrType=" + shrType + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0308Help.getShowNext() ) {
      			int pos = ewd0308Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0308?FromRecord=" + pos +  "&shrType=" + shrType + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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