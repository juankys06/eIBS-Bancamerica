<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code1,code2) {
  top.opener.document.forms[0][top.opener.fieldName].value = code1;
  //top.opener.document.forms[0][top.opener.fieldAux1].value = code2;
  top.close();
 }

//-->
</script>
<TITLE>Códigos de Referencia</TITLE>
</head>
<jsp:useBean id= "EWD0710Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<FORM>
<%
	if ( EWD0710Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"><b>No hay criterio de busqueda</b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>Código</TH>
      <TH ALIGN=CENTER nowrap>Descripcion</TH>
    </TR>
    <%
                String usr = (String)session.getAttribute("shrType");
                EWD0710Help.initRow();
                while (EWD0710Help.getNextRow()) {
                    if (EWD0710Help.getFlag().equals("")) {
                    		out.println(EWD0710Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( EWD0710Help.getShowPrev() ) {
      			int pos =EWD0710Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0710?FromRecord=" + pos +  "&USR=" + usr + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0710Help.getShowNext() ) {
      			int pos = EWD0710Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0710?FromRecord=" + pos +  "&USR=" + usr + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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