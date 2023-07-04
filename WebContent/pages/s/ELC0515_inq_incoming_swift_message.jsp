<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<TITLE>Incoming Swift Message</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "msgList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<script language="javascript">
//<!-- Hide from old browsers
function ok() {
	top.close();
 }
//-->
</script>
</HEAD>
<BODY>
<FORM  action="javascript:ok()">
 <%
 	String txt = ""; 
	if ( !msgList.getNoResult() ) {
		 
		 msgList.initRow();
		 while (msgList.getNextRow()) {
                 
         	datapro.eibs.beans.ELC051502Message msgLC = (datapro.eibs.beans.ELC051502Message) msgList.getRecord();
         	txt = txt + msgLC.getE02SWIMLN() + "\n";
         }
	}
 %>


<table align=center width="100%" id="tbhelp">
  <TR> 
    <TH> 
      <div align="left">Incoming Swift Message :
        <hr>
      </div>
    </TH>
  </TR>
  <TR> 
    <TD align="center">
		<textarea cols="81" rows="30" name="txtMsg" readonly ><%= txt%></textarea>
    </TD>
  </TR>
</table>
<P align="center">
<a href="javascript:ok()"><b> Cerrar </b></a>
</P>
</FORM>
</BODY>
</HTML>
