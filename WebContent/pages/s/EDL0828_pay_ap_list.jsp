<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Documentos o Giros
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "docList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
 var reason = "";

 function goAction(op) {    
     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     document.forms[0].submit();
  }  
  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 function showAccInfo(idxRow){
   var actvTb = document.forms[0].activeTable.value;
   var dataT = document.all["dataTable"+idxRow];
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.all["dataTable"+actvTb].style.display="none";
   document.all["dataTable"+idxRow].style.display="";
   document.forms[0].activeTable.value="" + idxRow;
   if (dataT.rows.length >=5){
     dataDiv2.style.pixelHeight = 100;
   }
   adjustEquTables(headTable2, dataT, dataDiv2,1,false);
   showChecked("RECNUM"+idxRow);
   
  }   
 
 function showDocInfo(loan,id,doc,type,idxTable,idxRow){
  
  var dataT = document.all["dataTable"+idxTable];
  for ( var i=0; i<dataT.rows.length; i++ ) {
       dataT.rows[i].className="trnormal";
    }
  dataT.rows[idxRow].className="trhighlight";
  document.forms[0].Loan.value=loan;
  document.forms[0].ID.value=id;
  document.forms[0].Doc.value=doc;
  document.forms[0].Type.value=type;
}  

function showDetailApproval(radio,row,sts) {
	radioClick(radio,row);
    var loan=document.forms[0].Loan.value;
    var id=document.forms[0].ID.value;
    var doc=document.forms[0].Doc.value;
    var type=document.forms[0].Type.value;
	page = webapp + "/servlet/datapro.eibs.approval.JSEDL0828?SCREEN=4&Loan="+loan+"&ID="+id+"&Doc="+doc+"&Type="+type;
	CenterWindow(page,530,460,2);
 
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
<h3 align="center">Aprobación de Documentos al Cobro	
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pay_ap_list.jsp,EDL0828">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDL0828">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="activeTable" VALUE="0">
<INPUT TYPE=HIDDEN NAME="Loan" VALUE="">
<INPUT TYPE=HIDDEN NAME="ID" VALUE="">
<INPUT TYPE=HIDDEN NAME="Type" VALUE="">
<INPUT TYPE=HIDDEN NAME="Doc" VALUE="">


 <TABLE  id="mainTable1" class="tableinfo">
 <TR > 
    <TD NOWRAP width="100%" >
    <TABLE id="headTable1" >
     <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Cuenta</TH>
	  <TH ALIGN=CENTER NOWRAP>Nombre Cliente</TH>
      <TH ALIGN=CENTER NOWRAP>Producto</TH>
	  <TH ALIGN=CENTER NOWRAP>Monto</TH>
	  <TH ALIGN=CENTER NOWRAP>Entrado Por</TH>
     </TR>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor">
    <table id="dataTable">
     	<%
                accList.initRow();
				String chk = "checked";
                while (accList.getNextRow()) {
                    if (!accList.getFlag().equals("")) {	
  		%> 
     		<TR>      			
                <TD ALIGN=CENTER NOWRAP>				
                 <input type="radio" name="RECNUM" value="<%= accList.getCurrentRow() %>" <%=chk%> onClick="showAccInfo('<%= accList.getCurrentRow() %>')">
				</TD>
      			<TD ALIGN=CENTER NOWRAP><a href="javascript:radioClick('RECNUM',<%= accList.getCurrentRow() %>)"><%= accList.getRecord(0) %></a></TD>
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('RECNUM',<%= accList.getCurrentRow() %>)"><DIV NOWRAP><%= accList.getRecord(2) %></DIV></a></TD>      
      			<TD ALIGN=CENTER NOWRAP><DIV NOWRAP><a href="javascript:radioClick('RECNUM',<%= accList.getCurrentRow() %>)"><%= accList.getRecord(3) %></a></DIV></TD>
      			<TD ALIGN=RIGHT NOWRAP><DIV NOWRAP><a href="javascript:radioClick('RECNUM',<%= accList.getCurrentRow() %>)"><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(4)) %></a></DIV></TD>
      			<TD ALIGN=CENTER NOWRAP><DIV NOWRAP><a href="javascript:radioClick('RECNUM',<%= accList.getCurrentRow() %>)"><%= accList.getRecord(5) %></a></DIV></TD>
     		</TR>
  		<% 
				chk = "";
                    }
                }
  		%>
   </table>
   </div>
   </TD>
 </tr>
</table>

  <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="24%"> <a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Approval" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a></TD>
      <TD ALIGN=CENTER width="25%"> <a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Reject" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a> 
      </TD>
      <TD ALIGN=CENTER width="24%"><a href="javascript:goAction('D')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image11" alt="Delete" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" width="70" height="55" ></a></TD>
      <TD ALIGN=CENTER width="27%"> <a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a> 
      </TD>
    </TR>
    <TR> 
      <TD colspan="4" align="center"><b>Documentos asociados al préstamo seleccionado</b></TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
      
  <TABLE id="headTable2" >
  	<TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>No. Doc</TH>
	        <TH ALIGN=CENTER NOWRAP>Aceptante</TH>
	  <TH ALIGN=CENTER NOWRAP>Valor</TH>
      <!--<TH ALIGN=CENTER NOWRAP>Tot. Pagado</TH>-->
      <TH ALIGN=CENTER NOWRAP>Status</TH>
    </TR>
   </TABLE>
  
   <div id="dataDiv2" class="scbarcolor">
    <%
                accList.initRow();
				docList.initRow();
                int j=0;
                while (accList.getNextRow()) {
    %>                
    <table id="dataTable<%=j%>" style="display:none;">
    <%
  
                int k=0;
    			chk="checked";
                while (docList.getNextRow()) {
                    if (docList.getFlag().equals(accList.getFlag())) {
                     %> 
     		<TR> 
      			
              <TD ALIGN=Left NOWRAP> 
                <input type="radio" name="RECNUM<%=j%>" value="<%= docList.getCurrentRow() %>" <%=chk%> onClick="showDocInfo('<%= docList.getRecord(7)%>','<%= docList.getRecord(1)%>','<%= docList.getRecord(0)%>','<%= docList.getRecord(6)%>','<%=j%>',<%=k%>)"> 
			  </TD>
      			<TD ALIGN=CENTER NOWRAP><div nowrap><a href="javascript:showDetailApproval('RECNUM<%=j%>',<%= k %>,'<%= docList.getRecord(5) %>')"><%= docList.getRecord(0) %></a></DIV></TD>
      			<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:showDetailApproval('RECNUM<%=j%>',<%= k %>,'<%= docList.getRecord(5) %>')"><%= docList.getRecord(2) %></a></DIV></TD>      
      			<TD ALIGN=RIGHT NOWRAP><div nowrap><a href="javascript:showDetailApproval('RECNUM<%=j%>',<%= k %>,'<%= docList.getRecord(5) %>')"><%= datapro.eibs.master.Util.fcolorCCY(docList.getRecord(3)) %></a></DIV></TD>
      			<!--<TD ALIGN=RIGHT NOWRAP><div nowrap><a href="javascript:showDetailApproval('RECNUM<%=j%>',<%= k %>)"><%= datapro.eibs.master.Util.fcolorCCY(docList.getRecord(4)) %></a></DIV></TD>-->
      			<TD ALIGN=CENTER NOWRAP><div nowrap><a href="javascript:showDetailApproval('RECNUM<%=j%>',<%= k %>,'<%= docList.getRecord(5) %>')"><%= docList.getRecord(5) %></a></DIV></TD>
     		</TR>
  		<% 
                    k++;
					chk="";
                    }
                   else{
					 docList.setCurrentRow(docList.getCurrentRow()-1);
                     break;
					}        
                }
    	%> 
   </table>
   <%  
               j++;      
            }
    %> 
   </div>
   </TD>
   
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
     if (dataTable.rows.length >=5){
	   dataDiv1.style.height="120"; 
   	   dataDiv1.style.overflowY="scroll";
   	 }
     function resizeDoc() {
      var actvTb = document.forms[0].activeTable.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
       adjustEquTables(headTable2, dataT, dataDiv2,1,false);
      }
	 showChecked("RECNUM");
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
