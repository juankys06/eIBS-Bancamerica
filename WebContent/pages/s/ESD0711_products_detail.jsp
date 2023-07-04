<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<%@ page import = "java.io.*,java.net.*,datapro.eibs.sockets.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>
<TITLE>Ayuda</TITLE>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<%
  	// Parameter entries
  	String bank = request.getParameter("bank");
  	String appCode = request.getParameter("appcode");
  	String typeCode = request.getParameter("typecode");
  	String generic = request.getParameter("generic");
  	String title = request.getParameter("title");
  	String sel = request.getParameter("sel"); if ( sel == null ) sel = "";
	String accnum = request.getParameter("accnum"); if ( accnum == null ) accnum = "";
	String deapac = request.getParameter("deapac"); if ( deapac == null ) deapac = "";
	int k=0; 
  	boolean displayTypes=typeCode.equals("") ? true : false;
%>
<script language="javascript">

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function closeHiddenDivParam(){
	setVisibility(document.getElementById("hiddenDivParam"), "hidden");
}

function closeHiddenDivs(){
	closeHiddenDivNew();
	closeHiddenDivParam();
}

function showHiddenDivNew(evt) {
	var show = goNew();
	if (show) {
		evt = (evt) ? evt : ((window.event) ? window.event : "");
	 	
		var hiddenDivNew = document.getElementById("hiddenDivNew");
	
		var y=evt.clientY + document.body.scrollTop;
		var x=evt.clientX + document.body.scrollLeft;
	     
		cancelBub(evt);
	
		moveElement(hiddenDivNew, y, x - 200);
		setVisibility(hiddenDivNew, "visible");
		 
		document.forms[0].accnum.focus();
	}
	else {
		goAction(10);
	}
}

function showHiddenDivParam() {

	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivParam");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivParam, y, x - 200);
	setVisibility(hiddenDivParam, "visible");
	 
	document.forms[0].deapac.focus();
}

  function goAction(s) {

	  document.forms[0].SCREEN.value = s;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "PROD_CODE") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok || (s == 12)) {
     	  switch ( s ) {
             case 2 : 
             case 4 : 
             case 6 : 
                 document.forms[0].target="detail";
                 document.forms[0].submit();
                 break;
             case 8 : 
                 document.forms[0].target="detail";
                 document.forms[0].submit();
                 break;
             case 10 : 
                 document.forms[0].target="detail";
                 document.forms[0].submit();
                 break;
             case 12 : 
                 parent.window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
                 break;
             default : 
          }
     }
     else {
			alert("Seleccione un producto antes de realizar esta operación");	   
     }

  }

function goNew(){
   <% 
        int appCod = 0;
        boolean autoNum = false;
  		try {
	  		appCod = Integer.parseInt(appCode);
  		}
  		catch (Exception e) {
  		}
		switch (appCod) {
				case 11 :
				case 12 :  
					autoNum = datapro.eibs.master.JSEIBSProp.getAutoNumCD();
					break;
				case 1 :	
				case 2 :
				case 3 :
				case 5 :
					autoNum = datapro.eibs.master.JSEIBSProp.getAutoNumRT();
					break;
				case 4 :	
					autoNum = datapro.eibs.master.JSEIBSProp.getAutoNumSV();
					break;
				case 10 :	
					autoNum = datapro.eibs.master.JSEIBSProp.getAutoNumLN();
					break;
				default :
					autoNum = false;
  		}

	if (autoNum){ %>
	  return true;
   <%} else { %>
	  return false;
   <%}%>
  }

  function goNewPAR(){
  	closeEnter();
  	if (document.forms[0].par.value == 'P') {
  		ShowEnterPar();
  	} else {
  		goAction(10);
  	}
  }
  
 function setValues(prod, bank) {
 	//document.forms[0].PROD_CODE.value = prod;
	document.forms[0].bank.value = bank;
 }
  
</script>

</HEAD>
<BODY >

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0711" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">


<div id="hiddenDivNew" class="hiddenDiv">

 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Introduzca el número de cuenta : 
          <input type="text" maxlength="12" size="13" name="accnum" onKeyPress="enterInteger()" value="<%= accnum %>">
      </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:goAction(10)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/applicar_on.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>

<div id="hiddenDivParam" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Enter Participated Loan Number : 
          <input type="text" maxlength=9 size=11 name="deapac" onKeyPress="enterInteger()" value="<%= accnum %>">
          <a href="javascript:GetParticipation('deapac')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
      </TD>
      <TD ALIGN=Left nowrap>
	     <a href="javascript:goAction(10)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/apply_on.gif"></a> 
	  </TD>
   </TR>
 </TABLE>
</div>
<%
   try {
            if ( !error.getERRNUM().equals("0") ) {
                out.println("<SCRIPT Language=\"Javascript\">");
                out.println("       showErrors()");
                error.setERRNUM("0");
                out.println("</SCRIPT>");
            }
            else {
               	String action = null;
               	try {
               	 			action = userPO.getHeader2();
               	 			userPO.setHeader2("");
               	}
               	catch (Exception e) {
               	 			action = "";
               	}
               	String mypage = null;
               	try {
               	 			mypage = userPO.getHeader3();
               	 			userPO.setHeader3("");
               	}
               	catch (Exception e) {
               	 			mypage = "";
               	}
               
                  if ( action.equals("DO_INQ") ) {
                  	 if ( !mypage.equals("") ) {
%>
				       	<SCRIPT Language="Javascript">
					   		CenterWindow('<%= mypage %>',620,500,2);
				       	</SCRIPT>
<% 
                    	 }
                  }
                  else if ( action.equals("DO_NEW") ) {
                      out.println("<SCRIPT Language=\"Javascript\">");
                  	 if ( !mypage.equals("") ) {
               		 			out.println("parent.window.location.href=\"" + mypage + "\";");
                    	 }
                      out.println("</SCRIPT>");
                  }
                  else if ( action.equals("DO_HTML") ) {
                  	 if ( !mypage.equals("") ) {
%>
		       			<SCRIPT Language="Javascript">	
							CenterWindow('<%= mypage %>',620,500,2);
              			</SCRIPT>

<%
                    	 }
                  }
            }
   }
   catch (Exception e) {
   }
%>

<INPUT TYPE=HIDDEN NAME="bank" VALUE="<%= bank %>">
<INPUT TYPE=HIDDEN NAME="appcode" VALUE="<%= appCode %>">
<INPUT TYPE=HIDDEN NAME="typecode" VALUE="<%= typeCode %>">
<INPUT TYPE=HIDDEN NAME="generic" VALUE="<%= generic %>">
<INPUT TYPE=HIDDEN NAME="title" VALUE="<%= title %>">
<INPUT TYPE=HIDDEN NAME="sel" VALUE="<%= sel %>">
<INPUT TYPE=HIDDEN NAME="par">

<%
  	if ( displayTypes ) {  // Types Detail
 %>
         <table class="tbenter" width=100% align=center>
	     <tr>
              <td width="25%">
                <div align="center"><a href="javascript:goAction(2)"><img src="<%=request.getContextPath()%>/images/s/prod_audio.gif" align="middle" border="0"></a></div>
              </td>
              <td width="25%">
                <div align="center"><a href="javascript:goAction(4)"><img src="<%=request.getContextPath()%>/images/s/prod_video.gif" align="middle" border="0"></a></div>
              </td>
              <td width="25%">
                <div align="center"><a href="javascript:goAction(6)"><img src="<%=request.getContextPath()%>/images/s/prod_html.gif" align="middle" border="0"></a></div>
              </td>
              <td width="25%">
                <div align="center"><a href="javascript:goAction(12)"><img src="<%=request.getContextPath()%>/images/s/prod_exit.gif" align="middle" border="0"></a></div>
            </tr>
         </table>
         
         <p> 
         <table cellspacing="0" cellpadding="2" width="100%" border="1">
           <tr > 
             <td nowrap> 
               <table cellspacing="0" cellpadding="2" class="tbhead" width="100%"  align="center">
         			<tr><td align=left colspan=5 NOWRAP> <%= title %> </td></tr>
                </table>
              </td>
            </tr>
         </table>
         </p>
         
         
	 <TABLE  id="mainTable" class="tableinfo" NOWRAP>
      <TR> <TD >
      
      <TABLE id="headTable" NOWRAP>
       <TR id="trdark">  
			<TD align="center" nowrap></TD>
			<TD align="center" nowrap>Tipo</TD>
			<TD align="center" nowrap>Descripción</TD>
		 </TR>
     </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" NOWRAP>		
            
<%
   }
   else {  // Products Detail
 %>
         <table class="tbenter" width=100% align=center>
           <tr>
              <td width="2%">
          	</td>
              <td width="16%">
                <div align="center"><a href="javascript:goAction(2)"><img src="<%=request.getContextPath()%>/images/s/prod_audio.gif" align="middle" border="0"></a></div>
              </td>
              <td width="16%">
                <div align="center"><a href="javascript:goAction(4)"><img src="<%=request.getContextPath()%>/images/s/prod_video.gif" align="middle" border="0"></a></div>
              </td>
              <td width="16%">
                <div align="center"><a href="javascript:goAction(6)"><img src="<%=request.getContextPath()%>/images/s/prod_html.gif" align="middle" border="0"></a></div>
              </td>
              <td width="16%">
                <div align="center"><a href="javascript:goAction(8)"><img src="<%=request.getContextPath()%>/images/s/prod_inquiry.gif" align="middle" border="0"></a></div>
              </td>
              <td width="16%">
                <div id="eibsNew" align="center"><img src="<%=request.getContextPath()%>/images/s/prod_new.gif" align="middle" border="0" style="cursor:hand" ></div>
              </td>
              <td width="16%">
                <div align="center"><a href="javascript:goAction(12)"><img src="<%=request.getContextPath()%>/images/s/prod_exit.gif" align="middle" border="0"></a></div>
              </td>
              <td width="2%">
          	</td>
           </tr>
         </table>
          
         <p> 
         <table cellspacing="0" cellpadding="2" width="100%" border="1">
           <tr> 
             <td nowrap> 
               <table cellspacing="0" cellpadding="2" class="tbhead" width="100%"  align="center">
						<tr><td align=left colspan=5 NOWRAP> <%=typeCode %>  - <%= title %> </td></tr>
                </table>
              </td>
            </tr>
         </table>
         
         
  	   <TABLE  id="mainTable" class="tableinfo" NOWRAP>
		 <TR><TD >
  			<TABLE id="headTable" NOWRAP>
 			 <TR id="trdark">  
				<TD align="center" nowrap></TD>
		      <TD align="center" nowrap>Producto</TD>
		      <TD align="center" nowrap>Banco</TD>
		      <TD align="center" nowrap>Moneda</TD>
		      <TD align="center" nowrap>Descripción</TD>
		      <TD align="center" nowrap><div nowrap>Nombre de Mercadeo</div></TD>
	     </TR>
		</TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" NOWRAP>		
<%
   }

	// HttpSession session;
  	Socket s = null;
  	MessageContext mc = null;

  	MessageRecord newmessage = null;
  	ESD071102Message msgHelp = null;
	ESS0030DSMessage msgUser = null;
   
	session = (HttpSession)request.getSession(false);
	
	if (session != null) {

					msgUser = (datapro.eibs.beans.ESS0030DSMessage)session.getValue("currUser");
					
              	try
               	{
					s = new Socket(datapro.eibs.master.JSEIBSProp.getHostIP(), datapro.eibs.master.JSEIBSProp.getIniSocket() + 1);
               	  	mc = new MessageContext(new DataInputStream(new BufferedInputStream(s.getInputStream())),
               					      	    new DataOutputStream(new BufferedOutputStream(s.getOutputStream())),
               							    "datapro.eibs.beans");
               	}
               	catch (Exception e)
               	{
               	  out.println("Connection error " + e);
               	  System.exit(1);
               	}

               	// Send Request
               	try
               	{
               		msgHelp = (ESD071102Message)mc.getMessageRecord("ESD071102");
            		msgHelp.setE02USERID(msgUser.getH01USR());
            	 	msgHelp.setE02SELACD(appCode); 
           			msgHelp.setE02GENERI(generic);
           			msgHelp.setE02SELTYP(typeCode);
           			//if (userPO.getPurpose().equals("MAINTENANCE"))
           			  //msgHelp.setE02APCTYP("M");
           		      
               	 	msgHelp.send();
               	 	//System.out.println ("Producto mantenimiento message:"+msgHelp.getFormatName()+ " ,"+JSEIBSProp.getHostIP()+ "-"+ JSEIBSProp.getIniSocket());	
               	 	msgHelp.destroy();
               	}		
               	catch (Exception e)
               	{
               	  e.printStackTrace();
               	  out.println("Send Client Header Information error " + e);
               	}
						
						boolean firstItem = true;
						String chk = "";
						String col = "";
										
               	
               	// Receive Help
               	try
               	{         
                  	while(true) {
                  	   newmessage = mc.receiveMessage();
                  	  
                  	   if (newmessage.getFormatName().equals("ESD071102")) {

                              msgHelp =  (ESD071102Message)newmessage;
                             
           						   if ( msgHelp.getE02OPERAC().equals("*") ) {
   										break;
										}
										
           							out.println("<tr id=\"trclear\">");
           							if ( displayTypes ) {
           								col = msgHelp.getE02APCTYP().trim(); if ( col.equals("") ) col = "&nbsp;";
    										if ( sel.equals("") ) {
    											chk = firstItem?"checked":"";
               							firstItem = false;
                                  }
                                  else if ( sel.equals(col) ) {
    											chk = firstItem?"checked":"";
               							firstItem = false;
                                  }
                                  else {
                                    chk = "";
                                  }
           								out.println("<td NOWRAP>" +
           								"<input type=\"radio\" name=\"PROD_CODE\" value=\"" + col + "\" " +
           								"onclick=\"setValues('"+ col + "','" + msgHelp.getE02APCBNK() + "')\"" + chk + "></td>"); // radio button
           								out.println("<td NOWRAP>" + col + "</td>"); // Type Code
           								col = msgHelp.getE02DESCRI().trim(); if ( col.equals("") ) col = "&nbsp;";
           							   out.println("<td NOWRAP><div nowrap>"  + col + "</div></td>"); // Description
                              }
                              else {
           								col = msgHelp.getE02APCCDE().trim(); if ( col.equals("") ) col = "&nbsp;";
    										if ( sel.equals("") ) {
    											chk = firstItem?"checked":"";
               							firstItem = false;
                                  }
                                  else if ( sel.equals(col) ) {
    											chk = firstItem?"checked":"";
                                  }
                                  else {
                                    chk = "";
                                  }
           								out.println("<td NOWRAP>" +
           								"<input type=\"radio\" name=\"PROD_CODE\" value=\"" + col + "\" " + 
										"onclick=\"setValues('"+ col + "','" + msgHelp.getE02APCBNK() + "')\"" + chk + "></td>"); // radio button
           								out.println("<td NOWRAP>" + col + "</td>"); // Prod Code
           								col = msgHelp.getE02APCBNK().trim(); if ( col.equals("") ) col = "&nbsp;";
           								out.println("<td NOWRAP>" + col + "</td>"); // Bank Code
           								col = msgHelp.getE02CURREN().trim(); if ( col.equals("") ) col = "&nbsp;";
            							out.println("<td NOWRAP>" + col + "</td>"); // Currency Code
            							col = msgHelp.getE02DESCRI().trim(); if ( col.equals("") ) col = "&nbsp;";
           							   out.println("<td NOWRAP><div nowrap>"  + col + "</div></td>"); // Description
           								col = msgHelp.getE02NICNME().trim(); if ( col.equals("") ) col = "&nbsp;";
            							out.println("<td NOWRAP><div nowrap>" + col + "</div></td>"); // Nick Name
                              }
        								out.println("</tr>");
           							k++;
           						   
                 		   }
                  	   else {
                  		     out.println("Message " + newmessage.getFormatName() + " received.");
                  		     break;
                  		}
               		}
               			
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
	}
                	
%>
   </table>
  </div>
   
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
    document.forms[0].totalRow.value="<%= k %>";
    function resizeDoc() {
       divResize();
       adjustEquTables(
       	document.getElementById("headTable"), 
       	document.getElementById("dataTable"), 
       	document.getElementById("dataDiv1"),1,false);
    }
	applyTrans(document.getElementById("hiddenDivNew"));
	
     resizeDoc();
     window.onresize=resizeDoc;
     
   	document.onclick= closeHiddenDivs;
     
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
 	if (document.getElementById("eibsNew")) 
 		document.getElementById("eibsNew").onclick=showHiddenDivNew;
 		
 	document.getElementById("hiddenDivParam").onclick=cancelBub;
 	if (document.getElementById("eibsParam")) 
 		document.getElementById("eibsParam").onclick=showHiddenDivParam;
     
</SCRIPT>

</FORM>
</BODY>
</HTML>
