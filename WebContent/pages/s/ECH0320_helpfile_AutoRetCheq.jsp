<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>CNOFC Help</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function a(desc1,desc2) {
var form = top.opener.document.forms[0];

  	   form[top.opener.fieldName].value =  desc1;
   	   form[top.opener.fieldRut].value = desc2;

  	   
top.close();
 }

function goSearch() {
  window.location.href="<%=request.getContextPath()%>/pages/s/EWD0152_helpfile_CNOFC.jsp?codeflag=" + document.forms[0].codFlag.value + "&FromRecord=0&SelNew=" + document.forms[0].SelNew.value + "&SelOld=" + document.forms[0].SelOld.value; 
}

//-->
</script>
</HEAD>
<BODY>
<form>
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="SelOld" VALUE="">
<INPUT TYPE=HIDDEN NAME="codFlag" VALUE="">
<h4>TERCEROS AUTORIZADOS</h4>
<%
//servlet/datapro.eibs.products.JSECH80101?SCREEN=38

             	Socket s = null;
            	MessageContext mc = null;
            	String codeflag = request.getParameter("codeflag");
              	int rows = 0;
              	int posIni = 0;
              	int posEnd = 0;
              	String marker = "";
              	String ctacte = "";
              	String selOld = "";
              	String fromRec = "0";
              	boolean firstTime = true;
 				try
               	{
		            s = new Socket(JSEIBSProp.getHostIP(), JSEIBSProp.getIniSocket() + 1);
	  	            mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
					      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())), "datapro.eibs.beans");
               	}
               	catch (Exception e)
               	{
               	  out.println("Connection error " + e);
               	  System.exit(1);
               	}

				MessageRecord newmessage = null;
				ESD000006Message rtTit = null;
				ELEERRMessage msgError = null;
				UserPos	userPO = null;	
               	
               	// Send Request
               	try
               	{
               		rtTit = (ESD000006Message)mc.getMessageRecord("ESD000006");
//				 	rtTit.setH06USR(user.getH01USR());
				 	rtTit.setH06PGM("EDD0000");
//				 	rtTit.setH06TIM(getTimeStamp());
				 	rtTit.setH06SCR("01");
				 	rtTit.setH06OPE("0004");
				 	rtTit.setE06ACC(request.getParameter("ctacte"));
				 	rtTit.setE06RTP("V");
					rtTit.send();	
				 	rtTit.destroy();
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
                 	   rows = 1;
                 	   if (newmessage.getFormatName().equals("ESD000006")) 
                 	   {
    	                   rtTit =  (ESD000006Message)newmessage;%>
						  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			  			  <TR> 
			   			     <TD NOWRAP width="100%" >
				  				<TABLE id="headTable">
 	 							   <TR id="trdark">  
	     		 						<TH ALIGN=CENTER NOWRAP>Nombre</TH>
 	     								<TH ALIGN=CENTER NOWRAP>Rut</TH>
   				 	  			   </TR>
  	    			 			</TABLE>
  				 			    <div id="dataDiv1" class="scbarcolor">
    							<table id="dataTable" > 
					<% }
  					   int amount = 9;
 					   String name;
  						for ( int i=1; i<=amount; i++ ) 
  						{
   						  name = i + "";
   						  

                          String myDes1 = null;
                          String myDes2 = null;
                          String myDes3 = null;
                             
                          myDes1 = rtTit.getField("E06MA"+name).getString().trim();
                          myDes2 = rtTit.getField("E06ID"+name).getString().trim();
//                          myDes3 = rtTit.getField("E06PR"+name).getString().trim();
   						  if (!(myDes1.trim().equals("")))
   						  {
   						  %> 
   						  
				          <tr id="trclear"> 
				            <td nowrap> 
				              <div align="center"> 
                   				<A HREF="javascript:a('<%=myDes1%>','<%=myDes2%>')"><%=myDes1%></a>
				              </div>
				            </td>
				            <td nowrap> 
				              <div align="center"> 
                   				<A HREF="javascript:a('<%=myDes1%>','<%=myDes2%>')"><%=myDes2%></a>
				              </div>
				            </td>
				            
<!--				            <td nowrap> 
				              <div align="center"> 
                   				<A HREF=javascript:a('<%=myDes1%>','<%=myDes2%>','<%=myDes3%>')><%=myDes3%></a>
				              </div>
				            </td>-->
				            
				          </tr>
				          <%
				          }
				    		}
				    		%> 
			
			 </table>
   			</div>
   			</TD>
   		      </TR>	
		    </TABLE>

			 <SCRIPT language="JavaScript">
					function resizeDoc() {
		      		 	divResize();
		     		    adjustEquTables(headTable, dataTable, dataDiv1,1,false);
		      		}
			 		resizeDoc();   			
		     		window.onresize=resizeDoc;        
		     </SCRIPT>
         <%
              	}
               	catch (Exception e)
               	{
               	  out.println("Read error " + e);
               	}	

                	try
                	{
                	  s.close();
                	}
                	catch (Exception e) {
                	  out.println("Error closing socket connection " + e);
                	}
                	
%>
</form>
</BODY>
</HTML>
