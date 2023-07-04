<HTML>
<HEAD>
<TITLE>
 Lista de Lotes a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>
<%
	if(request.getParameter("refresh")!=null){
		response.sendRedirect(request.getContextPath()+"/servlet/datapro.eibs.products.JSERC0030?SCREEN=100");
	}
%>
<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"    scope="session" />

<jsp:useBean id= "error"  class= "datapro.eibs.beans.ELEERRMessage"    scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
  var reason = '';
 
	function goAction(op) {
		if (op==1) {
			document.forms[0].SCREEN.value = 200;
			document.forms[0].action.value = 'A';
			document.forms[0].submit();
  		}
  		else if (op==2) {
			document.forms[0].SCREEN.value = 400;
			document.forms[0].action.value = 'D';
			document.forms[0].submit();
  		}
	}  
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }     
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">

<SCRIPT LANGUAGE="javascript">

function showInqApprovalBatch(initial,end,bank,currency,account,glaccount,day,month,year) {
   pg = webapp + "/servlet/datapro.eibs.products.JSERC0030?SCREEN=300&E01RCIBNK=" + bank + "&E01RCICCY=" + currency + "&E01RCIGLN=" + account + "&E01RCIACC=" + glaccount + "&E01RCISD1=" + day + "&E01RCISD2=" + month + "&E01RCISD3=" +year + "&E01RCISTI=" + initial + "&E01RCISTF=" +end;
   CenterWindow(pg,650,500,2);
 
}
</SCRIPT>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0030" >
<INPUT TYPE=HIDDEN NAME="SCREEN">
<INPUT TYPE=HIDDEN NAME="action">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">

<h3 align="center">
  Aprobación de Lotes Contables	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="reconc_approval_ent.jsp,ERC030"> 
</h3>
  <hr size="4">  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(1)" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Approval" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="34%">
  			<a href="javascript:goExit()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
  </TABLE>


<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
 <TR> 
    <TD NOWRAP>
  <TABLE id="headTable"  NOWRAP>
  <TR id="trdark">  
      <TH ALIGN=CENTER nowrap></TH>
      <TH ALIGN=CENTER nowrap>Banco</TH>
      <TH ALIGN=CENTER nowrap>Moneda</TH>
      <TH ALIGN=CENTER nowrap>Cuenta<br>Contable</TH>
      <TH ALIGN=CENTER nowrap>Numero<br>Cuenta</TH>
      <TH ALIGN=CENTER nowrap>Fecha<br>Extracto</TH>
      <TH ALIGN=CENTER nowrap>Saldo<br>Inicial</TH>
      <TH ALIGN=CENTER nowrap>Saldo<br>Final</TH>
  </TR>
  </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  NOWRAP>
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
  showChecked("BTHNUM");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
