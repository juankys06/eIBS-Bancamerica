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
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "jbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"></SCRIPT>

<SCRIPT language="javascript">
// var reason = '';
 var accOfac = '';
 var accWarn = '';

 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0525_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
 }

 function goLetterDetail(op, flag, typ, accnum){
 	if(op == "V"){
		document.forms[0].SCREEN.value = "5";
		document.forms[0].E01LCMOPT.value = flag;
		document.forms[0].E01LCMTYP.value = typ;
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

   		   page = webapp + "/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8&TRANSREFNUM="+letterOfCreditForm.E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }
 }

function showInqWarn(fieldValue){
//       document.forms[0].ACCNUM.value=fieldValue;
//	var formLength= document.forms[0].elements.length;
//   	for(n=0;n<formLength;n++)
//     	{
//      		var elementName= document.forms[0].elements[n].name;
//      		var elementValue= document.forms[0].elements[n].value;
//      		if(elementName == "ACCNUM" && elementValue== fieldValue) 
//      			{
//        		document.forms[0].elements[n].click();
//        		break;
//      		}
//      	}
	page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + fieldValue;
	CenterWindow(page,420,300,2);    
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
 
</SCRIPT>


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
	<INPUT TYPE=HIDDEN NAME="E01LCMOPT" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E01LCMTYP" VALUE="">
	<INPUT TYPE=HIDDEN NAME="reason">
	<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
	<H3 align="center">
		Aprobacion de Aperturas y Enmiendas 
		<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ELC0525_approval_letter_of_credit_list.jsp">
	</H3>
	<HR size="4">
    
	<TABLE class="tbenter" width="100%">
		<TR> 
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A name="linkApproval" href="javascript:goAction('A')"><B>Aprobar</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="javascript:goAction('D')"><B>Eliminar</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="javascript:goMsgSwift()"><B>Mensaje<BR>
			  SWIFT</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A></DIV>
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
					        jbList.initRow(); 
							boolean firstTime = true;
							String chk = "";
					        while (jbList.getNextRow()) {
								if (firstTime) {
									firstTime = false;
									chk = "checked";
								} else {
									chk = "";
								}
					           ELC052501Message msgPart = (ELC052501Message) jbList.getRecord();
					    %>               
						        <TR>
									<TD NOWRAP ><INPUT type="radio" name="ACCNUM_TEMP" value="<%=msgPart.getE01LCMACC()%>" onClick="document.forms[0].E01LCMACC.value = this.value; showAddInfo(<%=k%>);"></TD>
									<TD NOWRAP ALIGN="LEFT"><A   href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMACC())%></A>
										<%if(msgPart.getH01FLGWK2().equals("A")){%>
											<a href="javascript:showInqWarn('<%=msgPart.getE01LCMACC()%>')"><img src="../images/warning01.gif" alt="Warnings" align="middle" border="0" ></a> <%} %></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01CUSNA1())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMPRO())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMCCY())%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01LCMAMN())%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goLetterDetail('V','<%=msgPart.getE01LCMOPT()%>','<%=msgPart.getE01LCMTYP()%>','<%=msgPart.getE01LCMACC()%>');"><%=Util.formatCell(msgPart.getE01REMARK())%></A>
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
					<TR id="trdark">
					 <TH ALIGN=CENTER nowrap >Información Básica</TH>
					</TR>
				</Table>
				
				<Table id="tbAddInfo" >
					<TR id="trclear">
						<TD ALIGN="RIGHT" nowrap><B>Comentario : <BR>Moneda : <BR>Monto : <BR>Banco : <BR>Sucursal : <BR>Cuenta Contable : <BR>Centro de Costo : <BR>Lote :<BR>Operador : </B></TD>
						<TD ALIGN="LEFT" nowrap class="tdaddinfo"></TD>
					</TR>
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
