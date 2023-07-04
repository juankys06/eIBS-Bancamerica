<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>
Lista de Cuentas a Aprobar
</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "jbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>

<script language="javascript">
 var accOfac = '';
 var accWarn = '';

 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0100_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
 }

 function goLetterDetail(x, y){
	document.forms[0].SCREEN.value = "5";
	SelectReg(x,y);
	document.forms[0].submit();
 }
 
function enter() {
    page = webapp + "/servlet/datapro.eibs.products.JSELC0100?SCREEN=8&E02LCRNUM=" + document.forms[0].E02LCRNUM.value + "&E02DRWNUM=" + document.forms[0].E02DRWNUM.value;
    CenterNamedWindow(page,'inquiry',750,650,2);
 }


 function SelectReg(fld1,fld2){
    document.forms[0].E02LCRNUM.value = fld1;
    document.forms[0].E02DRWNUM.value = fld2;
    document.forms[0].E01LCMACC.value = fld1;
 } 
 
 function goAction(op) {
	document.forms[0].E02ACTION.value = op;
	document.forms[0].submit();
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
   tbAddInfo.rows[0].cells[1].innerHTML=extraInfo(document.forms[0]["TXTDATA"+idxRow].value,8);   
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


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }%>

<form name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0100">
	<input type=HIDDEN name="E01LCMACC" value="">	
	<input type=HIDDEN name="SCREEN" value="2">
	<input type=HIDDEN name="E01LCMOPT" value="">
	<input type=HIDDEN name="E01LCMTYP" value="">
	<input type=HIDDEN name="E02ACTION" value="">
	<input type=HIDDEN name="E02LCRNUM" value="">
	<input type=HIDDEN name="E02DRWNUM" value="">
	<input type=HIDDEN name="reason">
	<input type=HIDDEN name="totalRow" value="0">
	
	<h3 align="center">
	Aprobacion de Pagos y Negociaciones
		<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ELC0100_lc_nego_apr_list.jsp">
	</h3>
	<hr size="4">
    
	<table class="tbenter" width="100%">
		<tr> 
			<td width="33%" class=TDBKG> 
				<div align="center"><a name="linkApproval" href="javascript:goAction('A')"><b>Aprobar</b></a></div>
		  </td>
			<td width="33%" class=TDBKG> 
				<div align="center"><a name="linkReject" href="javascript:goAction('D')"><b>Eliminar</b></a></div>
		  </td>
			<!--<td width="25%" class=TDBKG> 
				<div align="center"><a href="javascript:goMsgSwift()"><b>Mensaje<br>
			  SWIFT</b></a></div>
		  </td>-->
			<td width="33%" class=TDBKG> 
				<div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
		  </td>  
	  </tr>
	</table>
  
	<table  id="mainTable" class="tableinfo" >
		<tr> 
			<td nowrap valign="top">
				<table id="headTable" width="100%">
					<tr id="trdark"> 
						<th align=CENTER nowrap></th>
						<th align=CENTER nowrap>Numero Carta<BR>
				de Credito</th>
						<th align=CENTER nowrap>Nro.<br> 	Neg.</th>						
						<th align=CENTER nowrap>Tipo  Operacion</th>						
						<th align=CENTER nowrap>Monto</th>
						<th align=CENTER nowrap>Fecha</th>
						<th align=CENTER nowrap>Nombre de Cliente</th>						
					</tr>
						<% 
							String warnImg= "";
							String warnFlag= "";
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
					           ELC010001Message msgPart = (ELC010001Message) jbList.getRecord();
					           
								if (msgPart.getH01FLGWK2().trim().equals("A")) {
									warnImg= "<IMG border=\"0\" src=\"../images/warning01.gif\" ALT=\"\" onClick=\"showWarnings('" + msgPart.getE01LCRNUM() + "','ACCNUM_TEMP')\">";
									warnFlag = msgPart.getH01FLGWK2().trim();
								}else{
									warnImg= "";
									warnFlag = "";
								}
					    %>               
						        <tr>
									<td nowrap >
									   <input type="radio" name="ACCNUM_TEMP" value="<%= msgPart.getE01LCRNUM() %>" 
									   		onClick="SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>'); 
										   		showAddInfo(<%=k%>);" <%=chk%>>
									   <input type="HIDDEN" name="XXXWNUM" value="<%= msgPart.getE01DRWNUM() %>">
									</td>
									<td nowrap align="CENTER"><a href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();"><%=Util.formatCell(msgPart.getE01LCRNUM())%></a>
										<%=warnImg%></td>
									<td nowrap align="CENTER"><a href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();"><%=Util.formatCell(msgPart.getE01DRWNUM())%></a></td>
									<td nowrap align="LEFT"><a   href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();"><%=Util.formatCell(msgPart.getE01OPCDSC())%></a></td>									
									<td nowrap align="CENTER"><a href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();"><%=Util.formatCell(msgPart.getE01DRWAMN())%></a></td>
									<td nowrap align="CENTER"><a href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();">
									<%=Util.formatCell(msgPart.getE01NEGDTM() + "/" + msgPart.getE01NEGDTD() + "/" + msgPart.getE01NEGDTY())%></a></td>									
									<td nowrap align="CENTER"><a href="javascript:SelectReg('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');enter();"><%=Util.formatCell(msgPart.getE01CUSNA1())%></a></td>
								</tr>
								<%
									String ls = "";
									ls += Util.formatCell(msgPart.getE01LCRNUM()) + "<br>";
									ls += Util.formatCell(msgPart.getE01DRWNUM()) + "<br>";
									ls += Util.formatCell(msgPart.getE01OPCDSC()) + "<br>";
									ls += Util.formatCell(msgPart.getE01LCMCCY()) + "<br>";
									ls += Util.formatCell(msgPart.getE01DRWAMN()) + "<br>";
									ls += Util.formatCell(msgPart.getE01LCCBTH()) + "<br>";
									ls += Util.formatCell(msgPart.getE01LCCOPR()) + "<br>";
									ls += Util.formatCell(msgPart.getE01TRFNUM()) + "<br>";
								%>
									<input type="HIDDEN" name="TXTDATA<%=k%>" value="<%=ls%>">
									
				    	<%k++;
				    	}%>    
				</table>		
			</td>	
			<td nowrap align="RIGHT" valign="top">
				<table id="tbAddInfoH" width="100%" >
					<tr id="trdark">
					 <th align=CENTER nowrap >Información Básica</th>
					</tr>
				</table>
				
				<table id="tbAddInfo" >
					<tr id="trclear">
						<td align="RIGHT" nowrap><b>Numero de Cuenta: <br>No.  	Negociacion: <br> Tipo Operacion: <br>Moneda : <br>   Monto : <br>  Lote : <br>  Operador : <br> <b>No.
				Transferencia : </b></b></td>
						<td align="LEFT" nowrap class="tdaddinfo"></td>
					</tr>
				</table>
			</td>
		</tr>	
	</table>

	<script language="JavaScript">
	  document.forms[0].totalRow.value="<%= k %>";
	  function resizeDoc() {
		  if(document.forms[0].totalRow.value>1){
			  showAddInfo(0);
	    	  document.forms[0].E01LCMACC.value = document.forms[0].ACCNUM_TEMP[0].value;	
	    	  document.forms[0].E02LCRNUM.value = document.forms[0].ACCNUM_TEMP[0].value;	
	    	  document.forms[0].E02DRWNUM.value = document.forms[0].XXXWNUM[0].value;	
	      }else if(document.forms[0].totalRow.value == 1){
			  showAddInfo(0);
	          document.forms[0].E01LCMACC.value = document.forms[0].ACCNUM_TEMP.value;	
	          document.forms[0].E02LCRNUM.value = document.forms[0].ACCNUM_TEMP.value;	
	          document.forms[0].E02DRWNUM.value = document.forms[0].XXXWNUM.value;	
	      }
	       	  
//	       divResize(true);
//	       adjustDifTables_(headTable, dataTable, dataDiv1,2,1);
	  }
//	  showChecked("ACCNUM");
	  resizeDoc();
//	  tbAddInfoH.rows[0].cells[0].height = headTable.rows[0].cells[0].clientHeight;
//	  window.onresize=resizeDoc;
	</script>


</form>

</body>
</html>
