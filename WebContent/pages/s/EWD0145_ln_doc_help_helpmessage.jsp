<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Documentos del Prestamo</title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "ewd0145Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="javascript">
//<!-- Hide from old browsers

function enter(account, num) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = num;
  		break;
	}
 }
top.close();
 }

//-->
</script>
</head>

<body>
<FORM>
<%
	if ( ewd0145Help.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	} else {
		String opt = (String)request.getAttribute("OPT");
		if (opt == null) opt = "LN";
%>

  	<TABLE class="tbenter" width=100%>
 		<TR>
      		<TD> 
        		<div align="center"><h4 style="text-align:center">Documentos del Préstamo : </h4>
      			</div>
      		</TD>
     	</TR>
   	</TABLE>
   	
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap width="5%">No. Doc.</TH>
      <TH ALIGN=CENTER nowrap width="25%">Cuenta</TH>
      <TH ALIGN=CENTER nowrap width="15%"><%if (opt.equals("LN")) {
      											out.println("Aceptante");
      										} else {
      											out.println("Cliente");
      										} %></TH>
      <TH ALIGN=CENTER nowrap width="15%">Monto</TH>
      
<%
    	String Account = (String)request.getAttribute("Account");
    	String id = (String)request.getAttribute("id");
       	String Status = (String)request.getAttribute("Status");
		
     	ewd0145Help.initRow();               
      	while (ewd0145Help.getNextRow()) {
        	EWD0145DSMessage msgHelp = (EWD0145DSMessage) ewd0145Help.getRecord(); 
%>
    </TR>
                    
          <TR>
          <td NOWRAP >
          	<a href="javascript:enter('<%=msgHelp.getE01DLDNLN()%>',
          							  '<%=msgHelp.getE01DLDNDR()%>')">
										<%=Util.formatCell(msgHelp.getE01DLDNDR())%></a>
		  </td>
		  <td NOWRAP >
			<a href="javascript:enter('<%=msgHelp.getE01DLDNLN()%>',
          							  '<%=msgHelp.getE01DLDNDR()%>')">
										<%=Util.formatCell(msgHelp.getE01DLDNLN())%></a>
		  </td>
		  <td NOWRAP >
          	<a href="javascript:enter('<%=msgHelp.getE01DLDNLN()%>',
          							  '<%=msgHelp.getE01DLDNDR()%>')">
		          <%if (opt.equals("LN")) {
		          		out.println(Util.formatCell(msgHelp.getE01DLDNME()));
		          	} else {
		          		out.println(Util.formatCell(msgHelp.getE01CUSNA1()));
		          	} %></a>
		  </td>
		  <td NOWRAP >
          	<a href="javascript:enter('<%=msgHelp.getE01DLDNLN()%>',
          							  '<%=msgHelp.getE01DLDNDR()%>')">
										<%=Util.formatCell(msgHelp.getE01DLDOAM())%></a>
		  </td>
		</TR>
        <%        }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0145Help.getShowPrev() ) {
      			int pos =ewd0145Help.getFirstRec() - 21;
      			   out.print("<A HREF=\"" + request.getContextPath() + "/servlet/datapro.eibs.helps.JSEWD0145?Account=" + Account + "&id=" + id + "&Status=" + Status + "&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0145Help.getShowNext() ) {
      			int pos = ewd0145Help.getLastRec();
      			out.print("<A HREF=\"" + request.getContextPath()+ "/servlet/datapro.eibs.helps.JSEWD0145?Account=" + Account + "&id=" + id + "&Status=" + Status + "&FromRecord=" + pos + "\" ><img src=\""+ request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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