<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Lista de Cuentas a Aprobar</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR"
	content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="javascript">
// var reason = '';
 var accOfac = '';
 var accWarn = '';

function goMsgSwift() {
    
     
     if (document.forms[0].E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+document.forms[0].E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
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

 function goTranser(op, num) {
	if(op == "M"){
//		document.forms[0].opCode.value = '0002';
		document.forms[0].E05LCTNUM.value = num;
		document.forms[0].submit();
	}
 }

 function showAddInfo(idxRow){
   tbAddInfo.rows[0].cells[1].style.color="blue";   
   tbAddInfo.rows[0].cells[1].innerHTML=extraInfo(document.forms[0]["TXTDATA"+idxRow].value,9);
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
 


   builtNewMenu(lc_transfer_detail );

   builtHPopUp();

  function showPopUp(opth,field,Banco,ccy,field1,field2,opcod) {
	   init(opth,field,Banco,ccy,field1,field2,opcod);
	   showPopupHelp();
  }
 
</script>
<SCRIPT> initMenu();  </SCRIPT>
</HEAD>
<BODY>

<%if (!error.getERRNUM().equals("0")) {
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="106">
<INPUT TYPE=HIDDEN NAME="pCode" value="T">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="E01LCMACC" value="<%=userPO.getIdentifier()%>">	
<INPUT TYPE=HIDDEN NAME="E05LCTNUM" VALUE="">	
	<INPUT TYPE=HIDDEN NAME="reason">
<H3 align="center">Transferencia de Carta De Credito <IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="letter_of_credit_transfer_list,ELC0525"></H3>

<HR size="4">

<TABLE id="mainTable" class="tableinfo">
	<TR>
		<TD NOWRAP valign="top">
		<TABLE id="headTable" width="100%">
			<TR id="trdark">
				<TH ALIGN=CENTER nowrap></TH>
				<TH ALIGN=CENTER nowrap>Transferencia Numero</TH>
				<TH ALIGN=CENTER nowrap>Monto Transfierido</TH>
				<TH ALIGN=CENTER nowrap>Benificiario</TH>
				<TH ALIGN=CENTER nowrap>Identificacion</TH>
			</TR>
			<%	int k = 0;
				lcList.initRow();
				boolean firstTime = true;
				String chk = "";
				String ls_E05LCTNUM = "";
				while (lcList.getNextRow()) {
				ELC051005Message msgPart = (ELC051005Message) lcList.getRecord();
				if (firstTime) {
					firstTime = false;
					chk = "checked";

				} else {
					chk = "";
				}
				
				
				%>
			<TR>
				<TD NOWRAP><input type="radio" name="E05LCTNUM_TEMP"
					value="<%=msgPart.getE05LCTNUM()%>" onClick="document.forms[0].E05LCTNUM.value = this.value;showAddInfo(<%=k%>)"
					<%=chk%>></TD>
				<TD NOWRAP ALIGN="LEFT"><a href="javascript:goTranser('M','<%=Util.formatCell(msgPart.getE05LCTNUM())%>');"><%=Util.formatCell(msgPart.getE05LCTNUM())%></a></TD>
				<TD NOWRAP ALIGN="CENTER"><a href="javascript:goTranser('M','<%=Util.formatCell(msgPart.getE05LCTNUM())%>');"><%=Util.formatCell(msgPart.getE05LCTAMT())%></a></TD>
				<TD NOWRAP ALIGN="CENTER"><a href="javascript:goTranser('M','<%=Util.formatCell(msgPart.getE05LCTNUM())%>');"><%=Util.formatCell(msgPart.getE05LCTBN1())%></a></TD>
				<TD NOWRAP ALIGN="CENTER"><a href="javascript:goTranser('M','<%=Util.formatCell(msgPart.getE05LCTNUM())%>');"><%=Util.formatCell(msgPart.getE05LCTBID())%></a>
				<%	String ls = "";
					ls += Util.formatCell(msgPart.getE05LCMBNK()) + "<br>";
					ls += Util.fcolorCCY(msgPart.getE05LCMORF()) + "<br>";
					ls += Util.formatCell(msgPart.getE05LCMTRF()) + "<br>";
					ls += Util.formatCell(msgPart.getE05LCMPRO()) + "<br>";
					ls += Util.formatCell(msgPart.getE05LCMMEB()) + "<br>";%>
				<INPUT TYPE="HIDDEN" NAME="TXTDATA<%=k%>" value="<%=ls%>"></TD>
			</TR>
				<%	k++;
				}%>
		</TABLE>
		</TD>
		<TD nowrap ALIGN="RIGHT" valign="top">
		<Table id="tbAddInfoH" width="100%">
			<tr id="trdark">
				<TH ALIGN=CENTER nowrap>Información Básica</TH>
			</tr>
		</Table>

		<Table id="tbAddInfo">
			<tr id="trclear">
				<TD ALIGN="RIGHT" valign="center" nowrap><b>Banco : <br>
				Nustra Ref : <br>
				Ref Del Otro Banco: <br>
				Producto : <br>
				Saldo Disponible : </b><BR>
				</TD>
				<TD ALIGN="LEFT" valign="center" nowrap class="tdaddinfo"></TD>
			</tr>
		</Table>
		</TD>
	</TR>
</TABLE>



<SCRIPT language="JavaScript">
	  function resizeDoc() {
		  showAddInfo(0);	  
		  if(document.forms[0].E05LCTNUM_TEMP.length == undefined){
			document.forms[0].E05LCTNUM.value = document.forms[0].E05LCTNUM_TEMP.value;		  	
		  }else{
		  	document.forms[0].E05LCTNUM.value = document.forms[0].E05LCTNUM_TEMP[0].value;
		  }
	  }
	  resizeDoc();
	</SCRIPT></FORM>
<!--<h5>Messages<BR><%
lcList.initRow();
int ii = 0;
while (lcList.getNextRow()) {
out.print("Row " + ii + "<BR>");
	ELC051005Message msgPart = (ELC051005Message) lcList.getRecord();
	String s = msgPart.toString();
	for(int i = 0; i < s.length(); i++)
	{
		if(s.charAt(i) == ':')	out.print("<BR>");
		else if (s.charAt(i) == '<') out.print("{");
			else if (s.charAt(i) == '>') out.print("}");
		else 					out.print(s.charAt(i));

	}
	out.print("<BR>****************************************<BR>");
}
%>
</h5> -->
</BODY>
</HTML>
