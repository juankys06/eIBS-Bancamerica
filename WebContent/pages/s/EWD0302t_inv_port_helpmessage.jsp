<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code,desc) {
var form = top.opener.document.forms[0];

	form[top.opener.fieldName].value = code;
  	if(top.opener.fieldDesc !== ""){form[top.opener.fieldDesc].value = desc;}
  		
top.close();
 }
//-->
</script>
<TITLE></TITLE>
</head>
<jsp:useBean id= "ewd0302Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<FORM>
<%
	if ( ewd0302Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"><b> No hay resultados para su criterio de búsqueda</b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
	  ewd0302Help.initRow();
      ewd0302Help.getNextRow();
      datapro.eibs.beans.EWD0302DSMessage msgHelp = (datapro.eibs.beans.EWD0302DSMessage) ewd0302Help.getRecord();
      String shrCust = msgHelp.getSWDCUN();
%>
  <TABLE class="tableinfo" align="center"  >
    <TR id="trdark"> 
      <td ALIGN=RIGHT  nowrap ><b>Customer : </b></td>
      <td ALIGN=CENTER  nowrap ><b><%= msgHelp.getSWDCUN()%> - <%= msgHelp.getSWDNME()%></b></td>
    </TR>
  </TABLE>
  <BR>
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="20%">Número de Portafolio</TH>
      <TH ALIGN=CENTER  nowrap width="80%">Descripción</TH>
    </TR>
    <%               
                ewd0302Help.initRow();
                while (ewd0302Help.getNextRow()) {
                    msgHelp = (datapro.eibs.beans.EWD0302DSMessage) ewd0302Help.getRecord();
     %>
     <TR id="trclear"> 
      <TD ALIGN=CENTER><a href="javascript:enter('<%= msgHelp.getSWDNUM()%>','<%= msgHelp.getSWDDSC()%>')"><%= msgHelp.getSWDNUM()%></a></TD>
      <TD ALIGN=LEFT><a href="javascript:enter('<%= msgHelp.getSWDNUM()%>','<%= msgHelp.getSWDDSC()%>')"><%= msgHelp.getSWDDSC()%></a></TD>
     </TR>
                    
     <%           }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0302Help.getShowPrev() ) {
      			int pos =ewd0302Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0302?FromRecord=" + pos +  "&shrCust=" + shrCust + "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0302Help.getShowNext() ) {
      			int pos = ewd0302Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0302?FromRecord=" + pos +  "&shrCust=" + shrCust + "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");

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