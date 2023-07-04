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

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
 function ReturnData(code1,code2){
  if(top.opener.fieldAux1 !== ""){top.opener.document.forms[0][top.opener.fieldAux1].value = code1}
  if(top.opener.fieldAux2 !== ""){top.opener.document.forms[0][top.opener.fieldAux2].value = code2}
  top.close();     	
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

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0100">
	<INPUT TYPE=HIDDEN NAME="E01LCMACC" value="">	
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
	<INPUT TYPE=HIDDEN NAME="E01LCMOPT" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E01LCMTYP" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E02ACTION" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E02LCRNUM" VALUE="">
	<INPUT TYPE=HIDDEN NAME="E02DRWNUM" VALUE="">
	<INPUT TYPE=HIDDEN NAME="reason">
	<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
	
	<H3 align="center"> Pagos y Negociaciones
		<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ELC0100_lc_nego_help_list.jsp">
	</H3>
	<HR size="4">
  
	<TABLE  id="mainTable" class="tableinfo">
		<TR> 
			<TD NOWRAP valign="top">
				<TABLE id="headTable" width="100%">
					<TR id="trdark"> 
						<TH ALIGN=CENTER nowrap>Carta de Credito</TH>
						<TH ALIGN=CENTER nowrap>Negociacion</TH>	
						<TH ALIGN=CENTER nowrap>Nombre Cliente</TH>						
						<TH ALIGN=CENTER nowrap>Tipo de Operacion</TH>						
						<TH ALIGN=CENTER nowrap>Monto</TH>
						<TH ALIGN=CENTER nowrap>Fecha</TH>											
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
					           ELC010001Message msgPart = (ELC010001Message) jbList.getRecord();
					    %>               
						        <TR>
									<TD NOWRAP ALIGN="CENTER"><A   href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01LCRNUM())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01DRWNUM())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01CUSNA1())%></A></TD>
									<TD NOWRAP ALIGN="LEFT"><A   href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01OPCDSC())%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01DRWAMN())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:ReturnData('<%= msgPart.getE01LCRNUM() %>','<%= msgPart.getE01DRWNUM() %>');"><%=Util.formatCell(msgPart.getE01NEGDTM() + "/" + msgPart.getE01NEGDTD() + "/" + msgPart.getE01NEGDTY())%></A></TD>																		
								</TR>								
				    	<%k++;
				    	}%>    
				</TABLE>		
			</TD>	
		</TR>	
	</TABLE>
</FORM>

</BODY>
</HTML>
