<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>CNOFC Help</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page language="Java" %>
<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function a(code1, desc1) {
var form = top.opener.document.forms[0];

	   form[top.opener.fieldName].value = code1;
  	   form[top.opener.fieldDesc].value = desc1;
  	   
top.close();
 }

//-->
</script>
</HEAD>
<BODY>
<form>
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<%
	Socket s = null;
	MessageContext mc = null;
	String codeflag = request.getParameter("codeflag");
	int rows = 0;

	try {
		s = new Socket(JSEIBSProp.getHostIP(), JSEIBSProp.getIniSocket() + 31);
		s.setSoTimeout(JSEIBSProp.getSocketTimeOut());
		mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
					      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");

		MessageRecord newmessage = null;
		EWD0158DSMessage msgHelp = null;
               
		// Send Request
		msgHelp = (EWD0158DSMessage)mc.getMessageRecord("EWD0158DS");
		msgHelp.send();	
		msgHelp.destroy();

		// Receive Help
                 	  // newmessage = mc.receiveMessage();
                 	  
                 	  // if (newmessage.getFormatName().equals("EWD0158DS")) {
                      //  msgHelp =  (EWD0158DSMessage)newmessage;
                      //  out.println("<h4>Tabla de Convenios</h4>");
			%>
			  <h4>Tabla de Convenios</h4>
			  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			   <TR> 
    			     <TD NOWRAP width="100%" >
  				<TABLE id="headTable" >
  				   <TR id="trdark">  
      					<TH ALIGN=CENTER NOWRAP>Código</TH>
      					<TH ALIGN=CENTER NOWRAP>Descripción</TH>
      					<TH ALIGN=CENTER NOWRAP>Tipo</TH>
          			   </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
			<% 
                 	//}
                  	
		int ct = 0;
		while (ct++ < datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
			if (ct == datapro.eibs.master.JSEIBSProp.getMaxIterations()) {
				System.out.println("MAX_ITERATION_REACHED_ERROR class:" + this.getClass().getName());
			}
			newmessage = mc.receiveMessage();
			if (newmessage.getFormatName().equals("EWD0158DS")) {
				msgHelp =  (EWD0158DSMessage)newmessage;
				if ( msgHelp.getEWDOPE().equals("*") ) {
					break;
				}
				String myCode = null;
				String myDesc = null;
				String myType = null;
				myCode = msgHelp.getEWDCDE().trim();
				myDesc = msgHelp.getEWDDES().trim();
				myType = msgHelp.getEWDCTY().trim();
				out.println("<tr>");
				out.println("<td nowrap >" + myCode + "</td>");
				out.println("<td nowrap><NOBR><A HREF=\"javascript:a('" + myCode +"', '" + myDesc + "')\">"  + myDesc + "</a></td>");
				out.println("<td nowrap><NOBR><A HREF=\"javascript:a('" + myCode +"', '" + myDesc + "')\">"  + myType + "</a></td>");
				out.println("</tr>");
				rows++;
			} else {
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
