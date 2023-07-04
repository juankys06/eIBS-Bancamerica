<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Approval Collateral List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 

<script language="javascript">
  var reason = '';
  var accOfac = '';

	function goAction(op) {
		if (op==1) {
			document.forms[0].SCREEN.value = 2;
			document.forms[0].action.value = 'A';
			document.forms[0].submit();
  		}
  		else if (op==2) {
			document.forms[0].SCREEN.value = 2;
			document.forms[0].action.value = 'D';
			document.forms[0].submit();
  		}
  		else if (op==3) {
			document.forms[0].SCREEN.value = 2;
			document.forms[0].action.value = 'R';
			document.forms[0].submit();
  		}
	}  
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
 
  
 

</script>
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<h3 align="center">Aprobacion Garantias</h3>
<hr size="4">
  
  
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0140">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="CHECKENC" VALUE=" ">
   <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(1)" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Approval" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="34%">
  			 <a href="javascript:goExit()"   onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top" width="100%"> 
    <TABLE id="headTable" >
    <TR id="trdark">  
      	 
      	   <TH ALIGN=CENTER rowspan="2" nowrap></TH>
           <TH ALIGN=CENTER rowspan="2" NOWRAP>Banco </TH>
           <TH ALIGN=CENTER colspan="2" NOWRAP>Cuenta</TH>
           <TH ALIGN=CENTER rowspan="2" NOWRAP>Tipo <br>Cuenta</TH>
           <TH ALIGN=CENTER colspan="2" NOWRAP>Garantia</TH>
           <TH ALIGN=CENTER rowspan="2" NOWRAP>Tipo<br> Garantia</TH>
           <TH ALIGN=CENTER colspan="2" NOWRAP>Cliente</TH>
		   <TH ALIGN=CENTER rowspan="2" NOWRAP>Nombre</TH> 
		   <TH ALIGN=CENTER rowspan="2" NOWRAP>Tipo<br>Movimiento</TH>   
    </TR>
   </TABLE>
     
  
   <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
    <%
                appList.initRow();
                int k=1;
                while (appList.getNextRow()) {
                    if (appList.getFlag().equals("")) {
                    		out.println(appList.getRecord());
                    k++;
                    }        
                }
                  if ( k > 10 ) {
      %>
                    		 <SCRIPT Language="Javascript">
                    		    document.forms[0].totalRow.value="<%= k %>";
                    		    //initTime();
   							</SCRIPT>	 
  			<%	 
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
  showChecked("ACCNUM");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
