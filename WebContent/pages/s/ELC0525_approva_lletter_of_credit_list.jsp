<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
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

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
// var reason = '';
 var accOfac = '';
 var accWarn = '';

 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0525_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
 }

 function goLetterDetail(op, flag, accnum){
 	if(op == "V"){
		document.forms[0].SCREEN.value = "5";
		document.forms[0].H01FLGWK3.value = flag;
		document.forms[0].E01LCMACC.value = accnum;
		document.forms[0].submit();
 	}
 }

 function goAction(op) {
	if(op == "A"){
		document.forms[0].SCREEN.value = 1;
		document.forms[0].submit();		 
	}else if(op == "D"){
		document.forms[0].SCREEN.value = 2;
		document.forms[0].submit();
	}else if(op == "R"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}else if(op == "M"){
		document.forms[0].SCREEN.value = 0;
		document.forms[0].submit();
	}
 }

 function goMsgSwift() {
    
     
     if (letterOfCreditForm.E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+letterOfCreditForm.E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }



 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="blue";   
   tbAddInfo.rows[0].cells[1].innerHTML=extraInfo(document.forms[0]["TXTDATA"+idxRow].value,9);   

//   if (document.forms[0]["STSOFAC"+idxRow].value == "3") {
//      var formLength = document.forms[0].elements.length;
//      for (var n=0;n<formLength;n++){
//        var elemt = document.forms[0].elements[n];
//        if ( elemt.checked ) {
//              accOfac=elemt.value;
//              break;
//        }
//      }      
//   } else {
//      accOfac = "";
//   }

// if (document.forms[0]["STSWARN"+idxRow].value == "A") {
//      var formLength = document.forms[0].elements.length;
//      for (var n=0;n<formLength;n++){
//        var elemt = document.forms[0].elements[n];
//        if ( elemt.checked ) {
//              accWarn=elemt.value;
//              break;
//        }
//      }      
//   } else {
//      accWarn = "";
//   }
//
//   for ( var i=0; i<dataTable.rows.length; i++ ) {
//       dataTable.rows[i].className="trnormal";
//    }
//   dataTable.rows[idxRow].className="trhighlight";
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
 
</script>


</HEAD>

<BODY>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }/* else {
   if (userPO.getThereIsMsg()) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showReceipt('"+userPO.getOption()+"')");
     out.println("</SCRIPT>");     
   }
 }
*/ 
%>

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
	<INPUT TYPE=HIDDEN NAME="E01LCMACC" value="">	
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
	<INPUT TYPE=HIDDEN NAME="H01FLGWK3" VALUE="">	
	<INPUT TYPE=HIDDEN NAME="reason">
	<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
	<h3 align="center">
		<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approva_lletter_of_credit_list.jsp,ELC0525">
	</h3>
	<hr size="4">
    
	<TABLE class="tbenter">
		<TR> 
			<TD class=TDBKG> 
				<div align="center"><a name="linkApproval" href="javascript:goAction('A')"><b>Aprobar</b></a></div>
			</TD>
			<TD class=TDBKG> 
				<div align="center"><a name="linkReject" href="javascript:setReason('R','reason')"><b>Rechazar</b></a></div>
			</TD>
			<TD class=TDBKG> 
				<div align="center"><a href="javascript:goAction('D')"><b>Eliminar</b></a></div>
			</TD>
			<TD class=TDBKG> 
				<div align="center"><a href="javascript:goMsgSwift()"><b>Mensaje<BR>SWIFT</b></a></div>
			</TD>
			<TD class=TDBKG> 
				<div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
			</TD>  
			</TR>
	</TABLE>
  
	<TABLE  id="mainTable" class="tableinfo" >
		<TR> 
			<TD NOWRAP valign="top">
				<TABLE id="headTable" width="100%">
					<TR id="trdark"> 
						<TH ALIGN=CENTER nowrap></TH>
						<TH ALIGN=CENTER nowrap>Cuenta</TH>
						<TH ALIGN=CENTER nowrap>Nombre</TH>						
						<TH ALIGN=CENTER nowrap>Producto</TH>
						<TH ALIGN=CENTER nowrap>Moneda</TH>
						<TH ALIGN=CENTER nowrap>Monto</TH>
						<TH ALIGN=CENTER nowrap>Comentario</TH>						
					</TR>
						<%
					       	int k=0;
					        appList.initRow(); 
							boolean firstTime = true;
							String chk = "";
					        while (appList.getNextRow()) {
								if (firstTime) {
									firstTime = false;
									chk = "checked";
								} else {
									chk = "";
								}
					           ELC052501Message msgPart = (ELC052501Message) appList.getRecord();
					    %>               
						        <TR>
									<TD NOWRAP ><input type="radio" name="ACCNUM_TEMP" value="<%=msgPart.getE01LCMACC()%>" onclick="document.forms[0].E01LCMACC.value = this.value; showAddInfo(<%=k%>);" <%=chk%>></TD>
									<TD NOWRAP ALIGN="LEFT"><a   href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMACC())%></a></TD>
									<TD NOWRAP ALIGN="CENTER"><a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01CUSNA1())%></a></TD>
									<TD NOWRAP ALIGN="CENTER"><a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMPRO())%></a></TD>
									<TD NOWRAP ALIGN="CENTER"><a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMCCY())%></a></TD>									
									<TD NOWRAP ALIGN="CENTER"><a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMAMN())%></a></TD>									
									<TD NOWRAP ALIGN="CENTER"><a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01REMARK())%></a>
									<%
										String ls = "";
										ls = ls + Util.formatCell(msgPart.getE01REMARK()) + "<br>";
										ls = ls + Util.fcolorCCY(msgPart.getE01LCMCCY())  + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMAMN()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMBNK()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMBRN()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMGLN()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMCCN()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMUBT()) + "<br>";
										ls = ls + Util.formatCell(msgPart.getE01LCMOPR()) + "<br>";
									%>
										<INPUT TYPE="HIDDEN" NAME="TXTDATA<%=k%>" value="<%=ls%>">
									</TD>
								</TR>    		
				    	<%
				    			k++;
				    		}
				    	%>    
				</TABLE>		
			</TD>	
			<TD nowrap ALIGN="RIGHT" valign="top">
				<Table id="tbAddInfoH" width="100%" >
					<tr id="trdark">
					 <TH ALIGN=CENTER nowrap >Información Básica</TH>
					</tr>
				</Table>
				
				<Table id="tbAddInfo" >
					<tr id="trclear">
						<TD ALIGN="RIGHT" valign="center" nowrap><b>Comentario : <br>Moneda : <br>Monto : <br>Banco : <br>Sucursal : <br>Cuenta Contable : <br>Centro de Costo : <br>Lote :<br>Operador : </b></TD>
						<TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
					</tr>
				</Table>
			</TD>
		</TR>	
	</TABLE>

	<SCRIPT language="JavaScript">
//	  document.forms[0].totalRow.value="<%= k %>";
	  function resizeDoc() {
		  showAddInfo(0);
    	  document.forms[0].E01LCMACC.value = document.forms[0].ACCNUM_TEMP[0].value;	  
//	       divResize(true);
//	       adjustDifTables_(headTable, dataTable, dataDiv1,2,1);
	  }
//	  showChecked("ACCNUM");
	  resizeDoc();
//	  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
//	  window.onresize=resizeDoc;
	</SCRIPT>


</FORM>

</BODY>
</HTML>
