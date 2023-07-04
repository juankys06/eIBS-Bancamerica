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

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0100?SCREEN=0&E01LCMACC="+letterOfCreditForm.E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }



 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="blue";   
   tbAddInfo.rows[0].cells[1].innerHTML=extraInfo(document.forms[0]["TXTDATA"+idxRow].value,6);   

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

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0100">
	<INPUT TYPE=HIDDEN NAME="E01LCMACC" value="">
	<INPUT TYPE=HIDDEN NAME="E01DRWNUM" value="">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
	<INPUT TYPE=HIDDEN NAME="H01FLGWK3" VALUE="">	
	<INPUT TYPE=HIDDEN NAME="reason">
	<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
	<h3 align="center">Aperturas y Enmiendas de Cartas de Credito
		<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_negociation_list.jsp,ELC0100">
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
						<TH ALIGN=CENTER nowrap>Numero Carta<br>Credito</TH>
						<TH ALIGN=CENTER nowrap>Numero<br>Negociacion</TH>						
						<TH ALIGN=CENTER nowrap>Tipo de Operacion</TH>
						<TH ALIGN=CENTER nowrap>Cliente</TH>
						<TH ALIGN=CENTER nowrap>Fecha</TH>
						<TH ALIGN=CENTER nowrap>Monto</TH>						
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
					           ELC010001Message msgPart = (ELC010001Message) appList.getRecord();
					    %>               
						        <TR>
									<TD NOWRAP >
										<input 	type="radio" 
												name="ACCNUM_TEMP" 
												value="<%=msgPart.getE01LCRNUM()%>" 
												onclick="	document.forms[0].E01LCMACC.value = this.value;
															document.forms[0].E01DRWNUM.value = document.forms[0].E01DRWNUM_TEMP[<%k;%>].value;"
												<%=chk%>>
																						
										<input type="hidden" name="E01DRWNUM_TEMP" value="<%msgPart.getE01LCRNUM()%>"/>			
									</TD>
									<TD NOWRAP ALIGN="LEFT">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01LCRNUM())%>
										</a>
									</TD>
									<TD NOWRAP ALIGN="CENTER">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01DWRNUM())%>
										</a>
									</TD>
									<TD NOWRAP ALIGN="CENTER">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01OPCDSC())%>
										</a>
									</TD>
									<TD NOWRAP ALIGN="CENTER">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01CUSNA1())%>
										</a>
									</TD>									
									<TD NOWRAP ALIGN="CENTER">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01NEGDTM())%>
										</a>
									</TD>									
									<TD NOWRAP ALIGN="CENTER">
										<a href="javascript:goLetterDetail('V','<%=msgPart.getH01FLGWK3()%>','<%=msgPart.getE01LCRNUM()%>');">
											<%=Util.formatCell(msgPart.getE01DRWAMN())%>
										</a>
									</TD>
								</TR>    		
				    	<%
				    			k++;
				    		}
				    	%>    
				</TABLE>		
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
