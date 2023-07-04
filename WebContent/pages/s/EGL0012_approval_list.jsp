<HTML>
<HEAD>
<TITLE>
 Lista de Lotes a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"    scope="session" />

<jsp:useBean id= "error"  class= "datapro.eibs.beans.ELEERRMessage"    scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
  var reason = '';
 
  function goAction(op) {

     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "BTHNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
     }
     else {
			alert(" Seleccione un lote antes de realizar esta operación");	   
     }

 }  
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }  
  
 function showAddInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
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

function showInqApprovalBatch(batch,bank,branch) {
   var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "BTHNUM" && elementValue== batch) 
      	{
        		document.forms[0].elements[n].checked = true;
        		break;
      	}
      }
   
   pg = webapp + "/servlet/datapro.eibs.products.JSEGL0035I?SCREEN=200&BATCH=" + batch + "&BANK=" + bank + "&BRANCH=" + branch;
   CenterWindow(pg,650,500,2);
 
}
</SCRIPT>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0012" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">

<h3 align="center">
  Aprobación de Lotes Contables	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EGL0012"> 
</h3>
  <hr size="4">  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Approval" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="33%">
  			<a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Reject" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
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
      <TH ALIGN=CENTER nowrap>Lote</TH>
      <TH ALIGN=CENTER nowrap>Banco</TH>
      <TH ALIGN=CENTER nowrap>Suc.</TH>
      <TH ALIGN=CENTER nowrap>Fecha</TH>
      <TH ALIGN=CENTER nowrap>Operador</TH>
      <TH ALIGN=CENTER nowrap>Status</TH>
      <TH ALIGN=CENTER nowrap>Total Debitos</TH>
      <TH ALIGN=CENTER nowrap>Total Creditos</TH>
      <TH ALIGN=CENTER nowrap>No.Trans.</TH>
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
