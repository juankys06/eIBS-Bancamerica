<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Approval List Account
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<script language="javascript">
  var reason = '';
  var accOfac = '';
  var accWarn = '';

  function goApproval() {
	document.forms[0].action.value = "A";
	document.forms[0].submit();
  }

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
          if (accOfac != "" && op != 'Z' && op != 'D') {

             page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + accOfac + "&shrCategory=ALL" + "&FromRecord=0&shrAction=APV";
	
	     CenterWindow(page,600,500,2);

          } else {
            if (accWarn != "" && op != 'D') {
   		 page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + accWarn+"&APP=1";
 	        CenterWindow(page,420,300,2);
            } else {
              document.forms[0].submit();
            }
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
   if (document.forms[0]["STSWARN"+idxRow].value == "A") {
      var formLength = document.forms[0].elements.length;
      for (var n=0;n<formLength;n++){
        var elemt = document.forms[0].elements[n];
        if ( elemt.checked ) {
              accWarn=elemt.value;
              break;
        }
      }      
   } else {
      accWarn = "";
   }

   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value;
   if (tbAddInfo.rows[0].cells[1].filters[0])
	   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
	   tbAddInfo.rows[0].cells[1].filters[0].Play();
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustDifTables(headTable, dataTable, dataDiv1,2,1);
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

</script>
<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD1000" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
 <h3 align="center">
  Aprobación de 
<%
		  if (userPO.getOption().equals("RT")) {
		%>
		          Cuentas Corrientes
		  			<INPUT TYPE=HIDDEN NAME="appCode" VALUE="RT">
		<%
		  }
		  else {
		%>
		         Cuentas de Ahorro
		  			<INPUT TYPE=HIDDEN NAME="appCode" VALUE="04">
		<%
		  }
		%>
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EDD1000"> 
    </h3>
   <hr size="4">
   
   <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goAction('A')" id="linkApproval" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/approve_over.gif',1)"><img name="Image1" alt="Aprobar" border="0" src="<%=request.getContextPath()%>/images/s/approve.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/reject_over.gif',1)"><img name="Image2" alt="Rechazar" border="0" src="<%=request.getContextPath()%>/images/s/reject.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
            <a href="javascript:goAction('D')" id="linkDelete" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image11" alt="Eliminar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo"  > 
 
 <TR > 
  <TD NOWRAP valign="top" width="100%" >
   <TABLE id="headTable" >
    <TR id="trdark">  
      <TH ALIGN=CENTER rowspan="2" nowrap></TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Cuenta</TH>
      <TH ALIGN=CENTER colspan="2" nowrap>Cliente</TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>C&oacute;digo de Producto</TH>
    </TR>
    <TR id="trdark">  
      <TH ALIGN=CENTER nowrap>Numero</TH>
      <TH ALIGN=CENTER nowrap>Nombre</TH>
    </TR>
   </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable"  >
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
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH" width="100%" >
        <tr id="trdark">
         <TH ALIGN=CENTER nowrap >Información Básica</TH>
        </tr>
      </Table>

     <Table id="tbAddInfo" >
      <tr id="trclear" >
         <TD  ALIGN="RIGHT"  valign="center" nowrap ><b>Comentario : <br>Monto : <br>Moneda : <br>Banco : <br>Sucursal : <br>Cuenta Contable : <br>Centro de Costo : <br>Lote :<br>Operador : </b></TD>
         <TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
      </tr>
     </Table>

  </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  function resizeDoc() {
       divResize(true);
       adjustDifTables(headTable, dataTable, dataDiv1,2,1);
  }
  showChecked("ACCNUM");
  resizeDoc();
  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
  window.onresize=resizeDoc;
</SCRIPT>

</FORM>

</BODY>
</HTML>
