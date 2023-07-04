<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  var reason = '';
  var accOfac = '';
  var accWarn = '';

  function showInqApprovalBG(account,type) {
   var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.bolgaran.JSELC5250?SCREEN=3&ACCNUM=" + account + "&typeCode=" + type;
	CenterWindow(page,600,500,2);

  }

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
 	alert("Select account before this operation");
      }

 }

 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.htm";
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
   tbAddInfo.rows[0].cells[1].innerHTML=extraInfo(document.forms[0]["TXTDATA"+idxRow].value,9);
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


   tbAddInfo.rows[0].cells[1].filters[0].apply();
   tbAddInfo.rows[0].cells[1].style.color="";
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

</HEAD>

<BODY >

<%
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5250" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="appCode" VALUE="BG">
<h3 align="center">Aprobación de Boletas de Garantia
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,ELC5250">
</h3>
<hr size="4">


<%
	if ( appList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=80%>
 		<TR>
      <TD>

      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font>
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>

  <TABLE class="tbenter">
    <TR>
      <TD class="TDBKG" width="25%"> <a href="javascript:goAction('A')" id="linkApproval">Aprobar</a></TD>
      <TD class="TDBKG" width="25%"> <a href="javascript:enterReason('R')" id="linkReject">Rechazar</a></TD>
      <TD class="TDBKG" width="25%"><a href="javascript:goAction('D')" id="linkDelete">Eliminar</a></TD>
      <TD class="TDBKG" width="25%"> <a href="javascript:goExit()" >Salir</a></TD>
    </TR>
  </TABLE>

 <TABLE  id="mainTable" class="tableinfo" >
 <TR>
    <TD NOWRAP valign="top" width="100%">
  <TABLE id="headTable" >
  <TR id="trdark">
    <TH ALIGN=CENTER rowspan="2" nowrap></TH>
    <TH ALIGN=CENTER rowspan="2"  nowrap>Boleta</TH>
    <TH ALIGN=CENTER colspan="2"  nowrap>Cliente</TH>
    <TH ALIGN=CENTER rowspan="2"  nowrap>Código de Producto</TH>
  </TR>
  <TR id="trdark">
    <TH ALIGN=CENTER nowrap>Número</TH>
    <TH ALIGN=CENTER nowrap>Nombre</TH>
  </TR>
  </TABLE>

   <div id="dataDiv1" class="scbarcolor" style="padding:0">
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
      <tr id="trclear">
         <TD ALIGN="RIGHT" valign="center" nowrap><b>Comentario : <br>Principal : <br>Moneda : <br>Banco : <br>Sucursal : <br>Mayor General : <br>Centro de Costo : <br>Lote :<br>Operador : </b></TD>
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
       adjustDifTables(headTable, dataTable, dataDiv1,2,1);
  }
  showChecked("ACCNUM");
  resizeDoc();
  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
  window.onresize=resizeDoc;

</SCRIPT>

<%
  }
%>
</FORM>

</BODY>
</HTML>
