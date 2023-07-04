<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@ page import = "datapro.eibs.master.Util" %><META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Studio">
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript" >
//<!-- Hide from old browsers
  //document.write(parent.query.document.scripts["eAction"].innerText);

function enter(code,desc,id) {

  parent.query.enterAction(code,desc,id); 
	   
 }
//-->
</script>
</head>

<jsp:useBean id= "EWD0505Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( EWD0505Help.getNoResult() ) {
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
 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER >
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%">No Corresponsal</TH>
      <TH ALIGN=CENTER  nowrap width="50%">Nombre</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Identificacion</TH>
    </TR>
    <%
                String Type = (String)request.getAttribute("Type");
                String NameSearch = (String)request.getAttribute("NameSearch");
                String CusType = (String)request.getAttribute("CusType");
                EWD0505Help.initRow();
                while (EWD0505Help.getNextRow()) {
                    if (EWD0505Help.getFlag().equals("")) {
                    		out.println(EWD0505Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( EWD0505Help.getShowPrev() ) {
      			int pos =EWD0505Help.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0505?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "&CusType=" + CusType + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0505Help.getShowNext() ) {
      			int pos = EWD0505Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0505?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "&CusType=" + CusType + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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

