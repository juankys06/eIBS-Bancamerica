<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Trade Ticket List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
<!--
var reason = '';
  var accOfac = '';
  var accWarn = '';

  function goApproval() {
	document.forms[0].action.value = "A";
    document.forms[0].submit();
  }

  function goAction(op) {
     
     document.forms[0].action.value = op;
     document.forms[0].reason.value = reason;
     
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ORDNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
      if ( ok ) {          
         document.forms[0].submit();
      }
      else {
 		 alert("Select order before this operation");	   
      }

 }
  
 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 function extraInfo(textfields,noField) {
 var pos=0
 var s= textfields;
 for ( var i=0; i<noField ; i++ ) {
   pos=textfields.indexOf("<br>",pos+1);
  }
 s=textfields.substring(0,pos);
 return(s);
 }
 
 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="white";
   tbAddInfo.rows[0].cells[1].innerHTML=document.forms[0]["TXTDATA"+idxRow].value; // extraInfo(document.forms[0]["TXTDATA"+idxRow].value,7);
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
   if (tbAddInfo.rows[0].cells[1].filters[0])
   tbAddInfo.rows[0].cells[1].filters[0].Play();
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->

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
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif','<%=request.getContextPath()%>/images/s/exit_over.gif')">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0140">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <input type=HIDDEN name="appCode" value="96">
  <h3 align="center">Aprobaci&oacute;n Ordenes de Compra - Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EIE0140"> 
  </h3>
<hr size="4">

  <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goAction('A')" id="linkApproval">Aprobar</a> 
      </TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:enterReason('R')" id="linkReject">Rechazar</a> 
      </TD>
      <TD ALIGN=CENTER width="34%" class=TDBKG> <a href="javascript:goAction('D')">Borrar</a> 
      </TD>
      <TD ALIGN=CENTER width="34%" class=TDBKG><a href="javascript:goExit()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
  <TABLE id="headTable" >
  <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP> </TH>
            <TH ALIGN=CENTER NOWRAP>N&uacute;mero de<br>
              Orden</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo de <br>
              Transacc.</TH>
            <TH ALIGN=CENTER NOWRAP>Cliente</TH>
            <TH ALIGN=CENTER NOWRAP>Descripci&oacute;n<br>
              Instrumento</TH>
            <TH ALIGN=CENTER NOWRAP>Fecha <br>
              Efectiva</TH>
            <TH ALIGN=CENTER NOWRAP>Marcar para<br>
              Borrar </TH>
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
   <TD nowrap ALIGN="RIGHT" valign="top">
      <Table id="tbAddInfoH"   width="100%" >
        <tr id="trdark">
            <TH ALIGN=CENTER nowrap >Informaci&oacute;n B&aacute;sica </TH>
        </tr>
      </Table>

     <Table id="tbAddInfo"  >
      <tr id="trclear" >
            <TD  ALIGN="RIGHT"  nowrap > 
              <p><b>Portafolio : <br>
                Cuenta Transaccional: <br>
                N&uacute;mero ISIN : <br>
                N&uacute;mero CUSIP : <br>
                S&iacute;mbolo : <br>
                Moneda : <br>
                Total de la Orden:</b> 
            </TD>
         <TD ALIGN="LEFT"  nowrap class="tdaddinfo"></TD>
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
     showChecked("ORDNUM");
     resizeDoc();
     tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
     window.onresize=resizeDoc;
     
</SCRIPT>

</FORM>

</BODY>
</HTML>
