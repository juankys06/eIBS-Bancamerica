<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>CNOFC Help</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:directive.page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" />
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.js"> </SCRIPT>
<SCRIPT language="JavaScript">
	setTimeout("self.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function a(code1, desc1, code2, desc2) {
	var formLength= opener.document.forms[0].elements.length;
	for (n = 0; n < formLength; n++) {
		var elementName= opener.document.forms[0].elements[n].name;
		if (elementName == opener.fieldName) {
			opener.document.forms[0].elements[n].value = code1;
			opener.document.forms[0].elements[n + 1].value = desc1;
			opener.document.forms[0].elements[n + 2].value = code2;
			opener.document.forms[0].elements[n + 3].value = desc2;
			break;
		}
	}
	self.close();
}
//-->
</script>
</HEAD>
<BODY>
<form>
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<%
	java.net.Socket s = null;
	MessageContext mc = null;
	String codeflag = request.getParameter("codeflag");
	int rows = 0;

	try {
		s = new java.net.Socket(JSEIBSProp.getHostIP(), JSEIBSProp.getIniSocket() + 1);
		s.setSoTimeout(JSEIBSProp.getSocketTimeOut());
		mc = new MessageContext(new java.io.DataInputStream(new java.io.BufferedInputStream(s.getInputStream())),
					   new java.io.DataOutputStream(new java.io.BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");

		datapro.eibs.sockets.MessageRecord newmessage = null;
		datapro.eibs.beans.EWD0152DSMessage msgHelp = null;
               
		// Send Request
		msgHelp = (EWD0152DSMessage)mc.getMessageRecord("EWD0152DS");
		msgHelp.setEWDTBL(codeflag);
		msgHelp.send();	
		msgHelp.destroy();

		// Receive Help
		newmessage = mc.receiveMessage();
                 	  
		if (newmessage.getFormatName().equals("EWD0152DS")) {
			msgHelp =  (datapro.eibs.beans.EWD0152DSMessage)newmessage;
			%>
			  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			   <TR> 
    			     <TD NOWRAP width="100%" >
  				<TABLE id="headTable" >
  				   <TR id="trdark">  
      					<TH ALIGN=CENTER NOWRAP>Código</TH>
			<% if (codeflag.equals("80")) { %>
      					<TH ALIGN=CENTER NOWRAP>Comuna</TH>
					<TH ALIGN=CENTER NOWRAP>Ciudad</TH>
			<% } else { %>
					<TH ALIGN=CENTER NOWRAP>Ciudad</TH>
      					<TH ALIGN=CENTER NOWRAP>Región</TH>
			<% } %>
          			   </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
			<% 
		}
                  	
		int ct = 0;
		while (true) {
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EWD0152DS")) {
				msgHelp =  (datapro.eibs.beans.EWD0152DSMessage)newmessage;
				if ( msgHelp.getEWDOPE().equals("*") ) {
					break;
				}

				String myCode1 = msgHelp.getEWDCD1().trim();
				String myDesc1 = msgHelp.getEWDDS1().trim();
				String myCode2 = msgHelp.getEWDCD2().trim();
				String myDesc2 = msgHelp.getEWDDS2().trim();

				out.println("<tr><td nowrap >" + myCode1 + "</td>");
				out.println("<td nowrap><A HREF=\"javascript:a('" + myCode1 +"', '" + myDesc1 + "', '" + myCode2 + "', '" + myDesc2 + "')\">"  + myDesc1 + "</a></td>");
				out.println("<td nowrap><A HREF=\"javascript:a('" + myCode1 +"', '" + myDesc1 + "', '" + myCode2 + "', '" + myDesc2 + "')\">"  + myDesc2 + "</a></td></tr>");
				rows++;
			}
			else {
				out.println("Message " + newmessage.getFormatName() + " received.");
				break;
			}
		}
			%>
			 </table>
   			</div>
   			</TD>
   		      </TR>	
		    </TABLE>

		    <SCRIPT language="JavaScript">
			document.forms[0].totalRow.value="<%= rows %>";
			divResize();
     			adjustEquTables(headTable, dataTable, dataDiv1,1,false);        
		    </SCRIPT>
                    <%
	} catch (Exception e) {
		out.print("Error : " + e);
	} finally {
		s.close();
	}
                	
%>
</form>
</BODY>
</HTML>
