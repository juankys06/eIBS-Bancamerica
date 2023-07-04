<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Pragma" content="No-cache">
<META http-equiv="Pragma" content="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code, desc) {
	var form = top.opener.document.forms[0];

  	if(top.opener.fieldDesc != "" && top.opener.fieldDesc != undefined){form[top.opener.fieldDesc].value = desc;}
	form[top.opener.fieldName].value = code;
	if (form[top.opener.fieldName].type != "hidden") form[top.opener.fieldName].focus();
		else form[top.opener.fieldDesc].focus();
	form[top.opener.fieldName].select();
  		
	top.close();
}
//-->
</script>
<TITLE></TITLE>
</HEAD>
<%@ page import="datapro.eibs.master.Util" %>
<jsp:useBean id= "EWD0360List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( EWD0360List.getNoResult() ) {
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
		String NameSearch = (String)request.getParameter("NameSearch");
		if(NameSearch == null) NameSearch = "";
		String Type = (String)request.getParameter("Type");
		if(Type == null) Type = "";
%>
 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="15%">Code</TH>
      <TH ALIGN=CENTER  nowrap width="50%">Descripción</TH>
    </TR>
     	<%
		boolean firstTime = true;
		String chk = "";
		EWD0360List.initRow();
        while (EWD0360List.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
			datapro.eibs.beans.EWD0360DSMessage msg = (datapro.eibs.beans.EWD0360DSMessage) EWD0360List.getRecord();
     	%>               
        <TR>
			<td NOWRAP><a href="javascript:enter('<%=msg.getSWDCDE()%>','<%=msg.getRWDNME()%>')"><%=Util.formatCell(msg.getSWDCDE())%></a></td>
			<td NOWRAP><a href="javascript:enter('<%=msg.getSWDCDE()%>','<%=msg.getSWDNME()%>')"><%=Util.formatCell(msg.getSWDNME())%></a></td>
			</TR>   		
    	<%}%>   
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
 	<%
        if ( EWD0360List.getShowPrev() ) {
      			int pos = EWD0360List.getFirstRec() - 21;
	%>
	<TD WIDTH="50%" ALIGN=LEFT height="25">
		<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0360?FromRecord=<%=pos%>&NameSearch=<%=NameSearch%>&Type=<%=Type%>" > 
			<img src="<%=request.getContextPath()%>/images/s/previous_records.gif" border=0></A>		
	</TD>
	<%  }
        if ( EWD0360List.getShowNext() ) {
      			int pos = EWD0360List.getLastRec();        
	%> 
	<TD WIDTH="50%" ALIGN=LEFT height="25">
		<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0360?FromRecord=<%=pos%>&NameSearch=<%=NameSearch%>&Type=<%=Type%>" > 
			<img src="<%=request.getContextPath()%>/images/s/next_records.gif" border=0></A>		
	</TD>
	 </TR>
<%      }
  }
%> 
	 </TABLE>
</FORM>

</BODY>
</HTML>