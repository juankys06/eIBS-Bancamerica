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
function enter(code,type,apc) {
	var form = top.opener.document.forms[0];

  	if(top.opener.fieldAux1 != ""){form[top.opener.fieldAux1].value = type;}
  	if(top.opener.fieldAux2 != ""){form[top.opener.fieldAux2].value = apc;}
	form[top.opener.fieldName].value = code;
	if (form[top.opener.fieldName].type != "hidden") form[top.opener.fieldName].focus();
		else form[top.opener.fieldAux1].focus();
	form[top.opener.fieldName].select();
  		
	top.close();
}
//-->
</script>
<TITLE></TITLE>
</HEAD>
<jsp:useBean id= "EDP0752Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<BODY>

<%@ page import="datapro.eibs.master.Util" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( EDP0752Help.getNoResult() ) {
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
      <TH ALIGN=CENTER  nowrap width="10%">Codigo</TH>
      <TH ALIGN=CENTER  nowrap width="30%">Descripcion Forma</TH>
      <TH ALIGN=CENTER  nowrap width="30%">Nombre Archivo Word</TH>
    </TR>
     	<%
        EDP0752Help.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (EDP0752Help.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.EDP075201Message msg = (datapro.eibs.beans.EDP075201Message) EDP0752Help.getRecord();
     	%>               
        <TR>
			<td NOWRAP><a href="javascript:enter('<%=msg.getE01APFFCD()%>','<%=msg.getE01APFDSC()%>','<%=msg.getE01APFPTH()%>')"><%=Util.formatCell(msg.getE01APFFCD())%></a></td>
			<td NOWRAP><a href="javascript:enter('<%=msg.getE01APFFCD()%>','<%=msg.getE01APFDSC()%>','<%=msg.getE01APFPTH()%>')"><%=Util.formatCell(msg.getE01APFDSC())%></a></td>
			<td NOWRAP><a href="javascript:enter('<%=msg.getE01APFFCD()%>','<%=msg.getE01APFDSC()%>','<%=msg.getE01APFPTH()%>')"><%=Util.formatCell(msg.getE01APFPTH())%></a></td>
			</TR>   		
    	<%}%>   
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
 	<%
        if ( EDP0752Help.getShowPrev() ) {
      			int pos = EDP0752Help.getFirstRec() - 21;
	%>
	<TD WIDTH="50%" ALIGN=LEFT height="25">
		<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEDP0752?FromRecord=<%=pos%>" > 
			<img src="<%=request.getContextPath()%>/images/s/previous_records.gif" border=0></A>		
	</TD>
	<%  }
        if ( EDP0752Help.getShowNext() ) {
      			int pos = EDP0752Help.getLastRec();        
	%> 
	<TD WIDTH="50%" ALIGN=LEFT height="25">
		<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEDP0752?FromRecord=<%=pos%>" > 
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