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
var form = top.opener.document.forms[0];

	form[top.opener.fieldName].value = code;
  		
top.close();
 }
//-->
</script>
<TITLE></TITLE>
</head>
<jsp:useBean id= "ewd0312Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<FORM>
<%
	if ( ewd0312Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"><b> No hay resultados para su criterio de busqueda</b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
	  ewd0312Help.initRow();
      ewd0312Help.getNextRow();
      datapro.eibs.beans.EWD0312DSMessage msgHelp = (datapro.eibs.beans.EWD0312DSMessage) ewd0312Help.getRecord();
      String shrCust = msgHelp.getSWDCUN();
      String shrTable = msgHelp.getSWDTBL();
      String shrType = msgHelp.getSWDTYP();
%>
  <h4 align="center">Tablas de Custodia/Comision</h4>
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="20%">Tipo de Tabla</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Codigo</TH>
      <TH ALIGN=CENTER  nowrap width="60%">Descripcion</TH>
    </TR>
    <%               
                ewd0312Help.initRow();
                while (ewd0312Help.getNextRow()) {
                    msgHelp = (datapro.eibs.beans.EWD0312DSMessage) ewd0312Help.getRecord();
     %>
     <TR id="trclear"> 
      <TD ALIGN=CENTER><a href="javascript:enter('<%= msgHelp.getSWDTBL()%>')"><%= msgHelp.getSWDTYP()%></a></TD>
      <TD ALIGN=LEFT>
        <div align="center"><a href="javascript:enter('<%= msgHelp.getSWDTBL()%>')"><%= msgHelp.getSWDTBL()%></a></div>
      </TD>
      <TD ALIGN=CENTER><a href="javascript:enter('<%= msgHelp.getSWDTBL()%>')"><%= msgHelp.getSWDDE0()%></a></TD>      
    </TR>
                    
     <%           }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0312Help.getShowPrev() ) {
      			int pos =ewd0312Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0312?FromRecord=" + pos +  "&shrCust=" + shrCust + "&shrType=" + shrType + "&shrTable=" + shrTable + "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0312Help.getShowNext() ) {
      			int pos = ewd0312Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0312?FromRecord=" + pos +  "&shrCust=" + shrCust + "&shrType=" + shrType + "&shrTable=" + shrTable + "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");

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