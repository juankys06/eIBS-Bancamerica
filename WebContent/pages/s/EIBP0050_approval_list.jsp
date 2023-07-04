<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Aprobación de Proveedores</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<%@ page import ="datapro.eibs.master.Util"%>
<%@ page import ="datapro.eibs.beans.EIBP005001Message"%>

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

 function goAction(op) {
	if (op == "A") {
		document.forms[0].SCREEN.value = 5;
		document.forms[0].submit();		 
	}
	else if (op == "D") {
		document.forms[0].SCREEN.value = 9;
		document.forms[0].submit();
	}    
 }
 
 function goVendorDetail(row){
    page = webapp + "/servlet/datapro.eibs.params.JSEIBP0050?SCREEN=2&ROW"+row;
 	CenterWindow(page,820,600,2);
 }

 function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
 }
 
</script>
</HEAD>

<% 

 if (!error.getERRNUM().equals("0")) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY>
<h3 align="center">Aprobaci&oacute;n de Proveedores de Internet
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp, EIBP0050">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEIBP0050">

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">

	<TABLE  id="mainTable" class="tableinfo" >
		<TR> 
			<TD NOWRAP valign="top">
				<TABLE id="headTable" width="100%">
					<TR id="trdark"> 
						<TH ALIGN=CENTER nowrap></TH>
						<TH ALIGN=CENTER nowrap>Cuenta</TH>
						<TH ALIGN=CENTER nowrap>Nombre</TH>						
						<TH ALIGN=CENTER nowrap>Provedor</TH>
						<TH ALIGN=CENTER nowrap>Fecha de Pago</TH>
						<TH ALIGN=CENTER nowrap>Monto</TH>
						<TH ALIGN=CENTER nowrap>Código</TH>
					</TR>
						<%
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
					           EIBP005001Message msg = (EIBP005001Message) appList.getRecord();
					    %>            
						        <TR>
									<TD NOWRAP >
										<SELECT name="E01INASEL" >
											<OPTION value="" <% if (msg.getE01INASEL().equals("")) out.print("selected"); %>></OPTION>
											<OPTION value="P" <% if (msg.getE01INASEL().equals("P")) out.print("selected"); %>>Pagar</OPTION>
											<OPTION value="R" <% if (msg.getE01INASEL().equals("R")) out.print("selected"); %>>Rechazar</OPTION>
										</SELECT>
									</TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatCell(msg.getE01INAACC()) %></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatCell(msg.getE01CUSSHN()) %></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatCell(msg.getE01INVNM1()) %></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatDate(msg.getE01INAPDM(), msg.getE01INAPDD(), msg.getE01INAPDY()) %></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatCCY(msg.getE01INAIAM()) %></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:goVendorDetail('<%= appList.getCurrentRow() %>');"><%= Util.formatCell(msg.getE01INAERR()) %></A></TD>
								</TR>   
						<% } %>		
				</TABLE>		
			</TD>	
		</TR>	
	</TABLE>

  	<DIV align="center"> 
  		<INPUT id="EIBSBTN" type=submit name="Submit" value="Enviar" > &nbsp; &nbsp; &nbsp;
  		<INPUT id="EIBSBTN" type="button" name="Cancelar" value="Cancelar" onClick="" > &nbsp; &nbsp; &nbsp;
  	</DIV>

</FORM>
</BODY>
</HTML>
