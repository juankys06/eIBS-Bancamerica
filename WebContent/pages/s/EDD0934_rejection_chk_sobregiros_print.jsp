<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="javascript"> 
function goPrint()
{ if ((navigator.appName == "Netscape")) { window.print() ; 
} 
else
{ 
var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>'; 
document.body.insertAdjacentHTML('beforeEnd', WebBrowser); WebBrowser1.ExecWB(6, -1); WebBrowser1.outerHTML = "";
location= prefix + language + "EDD0934_rejection_chk_sobregiros_list.jsp";
}
}
</SCRIPT> 
</head>

<body>
<h3 align="center">Sobregiros Monto Mayor<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rejection_chk_sobregiros_list.jsp, EDD0934"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD0934">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="FlagMov" VALUE="0">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
  
<TABLE  id="mainTable" class="tableinfo"  NOWRAP>
 <TR>
            <TD NOWRAP valign="top" height="100" width="625">
            <TABLE id="headTable"  NOWRAP width="100%">
         <TR id="trdark"> 
            <TH ALIGN=CENTER width="60" NOWRAP>RUT</TH>
            <TH ALIGN=CENTER width="120" NOWRAP>Nombre Cliente</TH>
            <TH ALIGN=CENTER width="60" NOWRAP style="cursor:hand" onClick="ShowSearchAcc()">Cuenta</TH>
            <TH ALIGN=CENTER width="100" NOWRAP>Monto Sobregiro</TH>
            <TH ALIGN=CENTER width="100" NOWRAP>Saldo Contable</TH>
            <TH ALIGN=CENTER width="60" NOWRAP>Ultimo Sobregiro</TH>
            <TH ALIGN=CENTER width="40" NOWRAP>Dias SGiro</TH>
			<TH ALIGN=CENTER width="30" NOWRAP>Suc</TH>
			<TH ALIGN=CENTER width="30" NOWRAP>Ofic</TH>
            </TR>
	</TABLE>
            <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
            <table id="dataTable"  NOWRAP width="100%">
    <%
               int row;
		  			try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
               chkList.initRow();
                int k=1;
               while (chkList.getNextRow()) {
     %> 
    <TR>
            <TD ALIGN=right  width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(2) %>-<%= chkList.getRecord(3) %></a></TD>
            <TD ALIGN=LEFT   width="120" NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(1) %></a></TD>
            <TD ALIGN=right  width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(0) %></a></TD>
            <TD ALIGN=RIGHT  width="100"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(4)) %></a></TD>
            <TD ALIGN=RIGHT  width="100"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.formatCCY(chkList.getRecord(5)) %></a></TD>
            <TD ALIGN=CENTER width="60"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(6) %>/<%= chkList.getRecord(7) %>/<%= chkList.getRecord(8) %></a></TD>
            <TD ALIGN=CENTER width="40"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(17) %></a></TD>
			<TD ALIGN=CENTER width="30"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(9) %></a></TD>
            <TD ALIGN=CENTER width="30"  NOWRAP><a href="javascript:radioClick('RECNUM',<%= chkList.getCurrentRow() %>)"><%= chkList.getRecord(10) %></a></TD>
                    </TR>
	
    <%
                k++;
                }
      %>
  </table>
            </div>
   </TD>
        </TR>	
</TABLE>
<BR>
</FORM>
<script language="javascript">
goPrint();
</script>
</BODY>
</HTML>
