<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Approval Customer List
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
  
     var op2 = '';
     if (op == 'Z') {op2 = 'A';} else {op2 = op;}
     

	
     document.forms[0].action.value = op2;
     document.forms[0].reason.value = reason;
     
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
      if ( ok ) {
          if (accOfac != "" && op != 'Z' && op != 'D' && op != 'R') {

             page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + accOfac + "&shrCategory=ALL" + "&FromRecord=0&shrAction=APV";
	
	     CenterWindow(page,600,500,2);

          } else {
				document.forms[0].submit();
          }
      }
      else {
 	alert("Select customer before this operation");	   
      }

 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
 function showAddInfo(idxRow){
   if (document.forms[0]["STSOFAC"+idxRow].value == "3") {
      var formLength = document.forms[0].elements.length;
      for (var n=0;n<formLength;n++){
        var elemt = document.forms[0].elements[n];
        if ( elemt.checked ) {
              accOfac=elemt.value;
              break;
        }
      }      
   } else {
      accOfac = "";
   }
   
  // --------- End Of Changes --------------

   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if(tbAddInfo.rows[0].cells[1].filters[0]){
	   tbAddInfo.rows[0].cells[1].filters[0].apply();   
   }
   tbAddInfo.rows[0].cells[1].style.color="";
   if(tbAddInfo.rows[0].cells[1].filters[0]){
	   tbAddInfo.rows[0].cells[1].filters[0].Play();   
   } 
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }    
  
function showInqWarn(fieldValue){
       document.forms[0].ACCNUM.value=fieldValue;
	var formLength= document.forms[0].elements.length;
   	for(n=0;n<formLength;n++)
     	{
      		var elementName= document.forms[0].elements[n].name;
      		var elementValue= document.forms[0].elements[n].value;
      		if(elementName == "ACCNUM" && elementValue== fieldValue) 
      			{
        		document.forms[0].elements[n].click();
        		break;
      		} 
      	}
	page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + fieldValue;
	CenterWindow(page,420,300,2);    
 }
 
 function showInqOFAC(fieldValue){
        document.forms[0].ACCNUM.value=fieldValue;
	
	var formLength= document.forms[0].elements.length;
   	for(n=0;n<formLength;n++)
     	{
      		var elementName= document.forms[0].elements[n].name;
      		var elementValue= document.forms[0].elements[n].value;
      		if(elementName == "ACCNUM" && elementValue== fieldValue) 
      			{
        		document.forms[0].elements[n].click();
        		break;
      		}
      	}
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + fieldValue + "&shrCategory=ALL" + "&FromRecord=0&shrAction=INQ";
	
	CenterWindow(page,600,500,2);    
 }

function checkEnc(obj){
	if (obj.checked) 
	    {
	    obj.value="Y"; 
	    document.forms[0].CHECKENC.value = "Y";
	    }
	    else
	    { 
	    obj.value="N";
   	    document.forms[0].CHECKENC.value = "N";
	    }
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
<h3 align="center">Aprobaci&oacute;n de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,ESD0100"> 
</h3>
<hr size="4">
  
  
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0100">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="CHECKENC" VALUE=" ">
   <TABLE class="tbenter">
    <TR>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction('A')" id="linkApproval"><b>Aprobar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:enterReason('R')" id="linkReject"><b>Rechazar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction('D')" id="linkDelete"><b>Eliminar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top" width="100%"> 
    <TABLE id="headTable" >
    <TR id="trdark">  
      <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>N&uacute;mero</TH>
            <TH ALIGN=CENTER NOWRAP>Cliente</TH>
            <TH ALIGN=CENTER NOWRAP>Identificaci&oacute;n</TH>
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
      %> 
   </table>
   </div>
   </TD>
      <TD nowrap ALIGN="RIGHT" valign="top" > 
        <Table id="tbAddInfoH"  width="100%" >
        <tr id="trdark">
            <TH ALIGN=CENTER nowrap > Informati&oacute;n B&aacute;sica</TH>
        </tr>
      </Table>

     <Table id="tbAddInfo" >
      <tr id="trclear" >
            <TD  ALIGN="RIGHT"  valign="center" nowrap ><b>Comentario : <br>
              Tipo : <br>
              Pa&iacute;s : <br>
              Lote :<br>
              Operador : </b></TD>
         <TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
      </tr>
     </Table>
  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
 document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(true);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     showChecked("ACCNUM");
     resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>


</FORM>

</BODY>
</HTML>
