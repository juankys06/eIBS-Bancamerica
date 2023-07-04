<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Ayuda CNOFC</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function a(code,desc) {
	var form = top.opener.document.forms[0];	
  	if(top.opener.fieldDesc != ""){	form[top.opener.fieldDesc].value = desc;}
	form[top.opener.fieldName].value = code;
	if (form[top.opener.fieldName].type != "hidden") {
		form[top.opener.fieldName].focus();
	} else {
		form[top.opener.fieldDesc].focus();
	}
	if (form[top.opener.fieldName].onchange) {
		form[top.opener.fieldName].onchange();
	}
	form[top.opener.fieldName].select();
  		
	top.close();
}
 
function goSearch() {
  window.location.href="<%=request.getContextPath()%>/pages/s/EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + document.forms[0].codFlag.value + "&FromRecord=0&SelNew=" + document.forms[0].SelNew.value + "&SelOld=" + document.forms[0].SelOld.value; 
}
//-->
</script>
</HEAD>
<BODY>
<form>
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="SelOld" VALUE="">
<INPUT TYPE=HIDDEN NAME="codFlag" VALUE="">
<%
            	MessageContext mc = null;
            	String codeflag = request.getParameter("codeflag");
              	int rows = 0;
              	int posIni = 0;
              	int posEnd = 0;
              	String marker = "";
              	String selNew = "";
              	String selOld = "";
              	String fromRec = "0";
              	boolean firstTime = true;
 				try
               	{
					mc = new MessageContext(new MessageProcessor("EWD0002").getMessageHandler());
               	}
               	catch (Exception e)
               	{
               	  out.println("Connection error " + e);
               	  System.exit(1);
               	}

               	MessageRecord newmessage = null;
               	EWD0002DSMessage msgHelp = null;
               
                try {
                	selNew = request.getParameter("SelNew");
                	if (selNew == null) selNew="";
                }
                catch (Exception e)
               	{
               	   selNew = "";              	  
               	}
               	try {
                	selOld = request.getParameter("SelOld");
                	if (selOld == null) selOld="";
                }
                catch (Exception e)
               	{
               	   selOld = "";              	  
               	}
               	try {
                	fromRec = request.getParameter("FromRecord");
                	if (fromRec == null) fromRec="0";
                }
                catch (Exception e)
               	{
               	   fromRec = "0";              	  
               	}
               	// Send Request
               	try
               	{
               		msgHelp = (EWD0002DSMessage)mc.getMessageRecord("EWD0002DS");
               	 	msgHelp.setEWDTBL(codeflag);
               	 	
               	 	msgHelp.setEWDSHO(selOld);
					msgHelp.setEWDSHN(selNew);
					msgHelp.setEWDREC(fromRec);
               	 	
               	 	msgHelp.send();	
               	 	msgHelp.destroy();
               	}		
               	catch (Exception e)
               	{
               	  e.printStackTrace();
               	  out.println("Send Client Header Information error " + e);
               	}
               		
               	// Receive Help
               	try
               	{
                 	   newmessage = mc.receiveMessage();
                 	  
                 	   if (newmessage.getFormatName().equals("EWD0002DS")) {
                        msgHelp =  (EWD0002DSMessage)newmessage;
                        out.println("<h4>"+msgHelp.getEWDDSC().trim()+"</h4>");
			%>
			  <table id="TBHELP">
				<tr>
				<td nowrap><b>Busqueda Rapida : </b></td>
			  	<td nowrap>
					<input type="text" name="SelNew"  size=20 maxlength=20>
        			&nbsp;&nbsp;<a href="javascript:goSearch();"><img src="<%=request.getContextPath()%>/images/search1.gif" align="absbottom" border="0" ></a> 
      			</td>
    		  </tr>
			</table>
			  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			   <TR> 
    			     <TD NOWRAP width="100%" >
  				<TABLE id="headTable" >
  				   <TR id="trdark">  
      					<TH ALIGN=CENTER NOWRAP>Código</TH>
      					<TH ALIGN=CENTER NOWRAP>Descripción</TH>
          			   </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
			<% 
                 	}
                  	
                  	while(true) {
                  	   newmessage = mc.receiveMessage();
                  	  
                  	   if (newmessage.getFormatName().equals("EWD0002DS")) {

                              msgHelp =  (EWD0002DSMessage)newmessage;
                              
                              marker = msgHelp.getEWDOPE();
                              
           					   if ( marker.equals("*") ) {
   										break;
								}
							  
							  if (firstTime) {
								firstTime = false;
						        posIni= Integer.parseInt(msgHelp.getEWDREC().trim());
					     		}
                              String myCode = null;
                              String myDesc = null;
                             
                              myCode = msgHelp.getEWDCOD().trim();
                              myDesc = msgHelp.getEWDDSC().trim();
                              selOld = msgHelp.getEWDSHO().trim();
                              
           					  out.println("<tr><td nowrap >" + myCode + "</td>");
           					  out.println("<td nowrap><NOBR><A HREF=\"javascript:a('" + myCode +"', '" + myDesc + "')\">"  + myDesc + "</a></td></tr>");
           					  rows++;
           						
           					  if (marker.equals("+")) {
           					  		posEnd= Integer.parseInt(msgHelp.getEWDREC().trim());
									break;
							  }   
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
			document.forms[0].codFlag.value="<%= codeflag %>";
			document.forms[0].SelNew.value="<%= selNew %>";
			document.forms[0].SelOld.value="<%= selOld %>";
			function resizeDoc() {
      		 	divResize();
     		    adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      		}
	 		resizeDoc();   			
     		window.onresize=resizeDoc;        
     </SCRIPT>
		    
	 <TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
   	 <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( posIni > 30 ) {
      			int pos = posIni - 31;
      			   out.print("<A HREF=\""+request.getContextPath()+"/pages/s/EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + codeflag + "&FromRecord=" + pos + "&SelNew=" + selNew + "&SelOld=" + selOld + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        } %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if (marker.equals("+")) {
      			int pos = posEnd;
      			out.print("<A HREF=\""+request.getContextPath()+"/pages/s/EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + codeflag + "&FromRecord=" + pos + "&SelNew=" + selNew + "&SelOld=" + selOld + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        } %>
      </TD>
	 </TR>
	 </TABLE>
                    <%
               	}
               	catch (Exception e)
               	{
               	  out.println("Read error " + e);
               	}	

                	try
                	{
                	   mc.close();
                	}
                	catch (Exception e) {
                	  out.println("Error closing socket connection " + e);
                	}
                	
%>
</form>
</BODY>
</HTML>
