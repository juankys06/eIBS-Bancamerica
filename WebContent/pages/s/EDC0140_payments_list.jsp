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

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
 var accOfac = '';
 var accWarn = '';

 function goCollectionDetail(apc, accnum, seq){
    page = webapp + "/servlet/datapro.eibs.approval.JSEDC0140?SCREEN=7&E01DCMACD="+apc+"&E01DCMACC="+accnum+"&E01PMTNUM="+seq;
 	CenterWindow(page,820,600,2);
 }

 function goAction(op) {
 
	document.forms[0].E02ACTION.value = op;
	document.forms[0].submit();
 }

</SCRIPT>
</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0140">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="9">
	<INPUT TYPE=HIDDEN NAME="E02ACTION" value="">	
	<H3 align="center">
	Aprobación de Pago/Liquidación
		<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDC0140_payments_list.jsp">
	</H3>
	<HR size="4">
    
	<TABLE class="tbenter" width="100%">
		<TR> 
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A id="linkApproval" href="javascript:goAction('A')"><B>Aprobar</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A id="linkDelete" href="javascript:goAction('D')"><B>Eliminar</B></A></DIV>
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
						<TH ALIGN=CENTER nowrap>Cobranza</TH>
						<TH ALIGN=CENTER nowrap>Nro.</TH>
						<TH ALIGN=CENTER nowrap>Cliente</TH>						
						<TH ALIGN=CENTER nowrap>Monto</TH>
						<TH ALIGN=CENTER nowrap>Tipo</TH>		
						<TH ALIGN=CENTER nowrap>Fecha</TH>
						<TH ALIGN=CENTER nowrap>Usuario</TH>									
					</TR>
						<% 
					       	int k=0;
					        appList.initRow(); 
							boolean firstTime = true;
							String chk = "";
							String type = "";
					        while (appList.getNextRow()) {
								if (firstTime) {
									firstTime = false;
									chk = "checked";
								} else {
									chk = "";
								} 
					           EDC014001Message msgPart = (EDC014001Message) appList.getRecord();
					           
					           if (msgPart.getE01TIPOPE().equals("1"))
					           		type = "Pago Total";
					           else if (msgPart.getE01TIPOPE().equals("2"))
					           		type = "Pago Parcial";
					           else if (msgPart.getE01TIPOPE().equals("3"))
					           		type = "Cobro de Comisiones";
					           else if (msgPart.getE01TIPOPE().equals("4"))
					           		type = "Cancelación";
					           else
					           		type = "";
					    %>               
						        <TR>
									<TD NOWRAP ><INPUT type="radio" name="ITEM" value="<%= msgPart.getE01DCMACC() + "_" + msgPart.getE01PMTNUM() %>"  <%= chk %>></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getE01DCMACC())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getE01PMTNUM())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getE01CUSNA1())%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getE01MNTPAG())%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(type)%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getE01DATEDD() + "/" + msgPart.getE01DATEMM() + "/" + msgPart.getE01DATEYY())%></A></TD>									
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goCollectionDetail('<%= msgPart.getE01DCMACD() %>','<%= msgPart.getE01DCMACC() %>','<%= msgPart.getE01PMTNUM() %>');"><%=Util.formatCell(msgPart.getH01USERID())%></A></TD>									
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
