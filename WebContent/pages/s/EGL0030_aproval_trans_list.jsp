<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<TITLE>Transaction Report</TITLE>

<%@ page import="datapro.eibs.master.Util"%>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "pendingList" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "approvalList" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "totalAppr" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "totalPend" class= "datapro.eibs.beans.JBObjList"   scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>
<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/reject_over.gif')">
<h3 align="center">Solicitud de Aprobación para el Cierre
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="aproval_trans_list.jsp EGL0030"> 
</h3>
<hr size="4">
<FORM Method="post" name="form" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEGL0030" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
	<INPUT TYPE=HIDDEN ID="OPERATION" NAME="ACTION" VALUE="A">
	<INPUT TYPE=HIDDEN NAME="BATCH" VALUE="<%= request.getAttribute("batch") %>">

  	<div id="enterTo" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
		<TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
			border-color: #0b23b5; border-style : solid solid solid solid; filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
			<TR>
		  		<TD ALIGN=right nowrap>EMail : 
	          		<input type="text" maxlength=35 size=40 id="user" name="USERTO" value="<%= request.getAttribute("email") %>">
	      		</TD>
	      		<TD ALIGN=left nowrap>
		     		<img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/applicar_on.gif" onclick="javascript:goAction('A')"> 
		  		</TD>
	   		</TR>
			<TR>
		  		<TD ALIGN=left nowrap colspan="2">Texto del Mensaje : </TD>
	   		</TR>
			<TR>
		  		<TD ALIGN=right nowrap colspan="2">
	          		<TEXTAREA ROWS=25 COLS="66" id="texto" name="TEXTTO"></TEXTAREA>
	      		</TD>
	   		</TR>
	 	</TABLE>
 	</div>


	<TABLE class="tbenter">
    	<TR>
      		<TD ALIGN=CENTER class=TDBKG><a href="javascript:showEnterUser()" id="linkApproval" ><b>Aprobar</b></a></TD>
      		<TD ALIGN=CENTER class=TDBKG><a href="javascript:goExit()" ><b>Salir</b></a></TD>     
    	</TR>
  	</TABLE>

  	<TABLE  id="mainTable" class="tableinfo">
		<TR id="trclear"> 
      		<TD NOWRAP valign="middle" align="right" width="15%">Banco : </TD> 
			<TD NOWRAP width="10%" align="left"><INPUT TYPE=text align="middle" size="4" NAME="BANK" VALUE = "<%= Util.formatCell(userPO.getBank()) %>"></TD> 
      		<TD NOWRAP valign="middle" align="right" width="10%">Usuario : </TD> 
			<TD NOWRAP width="20%" align="left"><INPUT TYPE=text align="middle" size="30" NAME="BANK" VALUE = "<%= Util.formatCell(userPO.getCusNum()) %>"></TD> 
      		<TD NOWRAP valign="middle" width="10%" align="right">Nombre : </TD> 
			<TD NOWRAP width="35%" align="left"><INPUT TYPE=text align="middle" size="60" NAME="BANK" VALUE = "<%= Util.formatCell(userPO.getCusName()) %>"></TD> 
      	</TR>	
  	</TABLE>
  	
  	<H4>Transacciones Aprobadas</H4>
  	
  	<TABLE  id="mainTable1" class="tableinfo" width="100%">
    	<TR > 
    		<TD NOWRAP valign="top" width="100%" >
			    <TABLE id="headTable1" width="100%">
					<TR id="trdark"> 
						<TH ALIGN=CENTER NOWRAP>Lote</TH>
						<TH ALIGN=CENTER NOWRAP>Fecha</TH>
						<TH ALIGN=CENTER NOWRAP>Usuario</TH>
						<TH ALIGN=CENTER NOWRAP>Debito</TH>
						<TH ALIGN=CENTER NOWRAP>Credito</TH>
						<TH ALIGN=CENTER NOWRAP>Transacciones</TH>
						<TH ALIGN=CENTER NOWRAP>Estado</TH>
			      	</TR>	
			  	</TABLE>
			  	
   				<div id="dataDiv1" class="scbarcolor">              
				    <TABLE id="dataTable1" width="100%">
				    	<%
				    		if (!approvalList.isEmpty()) {
					    		approvalList.initRow();
					    		while (approvalList.getNextRow()) {
									out.println(approvalList.getRecord());
					    		}
								if (!totalAppr.isEmpty()) { 
						    		totalAppr.initRow();
						    		while (totalAppr.getNextRow()) {
										out.println(totalAppr.getRecord());
						    		}
								 }  	
					    	} else {
					    		out.println("<TR><TD NOWRAP>No Existen Transaciones Aprobadas.</TD></TR>");
					    	}	
				    	%>
				  	</TABLE>
				  	<% if (approvalList.isEmpty()) out.println("<TR><TD NOWRAP ALIGN=CENTER >No Existen Transaciones Aprobadas.</TD></TR>");  %>
				</div>

			</TD>			  	
      	</TR>	
  	</TABLE>
  	
  	<H4>Transacciones Pendientes</H4>

  	<TABLE  id="mainTable2" class="tableinfo" width="100%">
    	<TR > 
    		<TD NOWRAP valign="top" width="100%" >
			    <TABLE id="headTable2" width="100%">
					<TR id="trdark" > 
						<TH ALIGN=CENTER NOWRAP>Lote</TH>
						<TH ALIGN=CENTER NOWRAP>Fecha</TH>
						<TH ALIGN=CENTER NOWRAP>Usuario</TH>
						<TH ALIGN=CENTER NOWRAP>Debito</TH>
						<TH ALIGN=CENTER NOWRAP>Credito</TH>
						<TH ALIGN=CENTER NOWRAP>Transacciones</TH>
						<TH ALIGN=CENTER NOWRAP>Estado</TH>
			      	</TR>	
			  	</TABLE>
			  	
   				<div id="dataDiv2" class="scbarcolor">              
				    <TABLE id="dataTable2" width="100%">
				    	<%
				    		if (!pendingList.isEmpty()) {
					    		pendingList.initRow();
					    		while (pendingList.getNextRow()) {
									out.println(pendingList.getRecord());
					    		}
								if (!totalPend.isEmpty()) { 
						    		totalPend.initRow();
						    		while (totalPend.getNextRow()) {
										out.println(totalPend.getRecord());
						    		}
								}  	
					    	}	
				    	%>
				  	</TABLE>
				  	<% if (pendingList.isEmpty()) out.println("<TR><TD NOWRAP ALIGN=CENTER >No Existen Transaciones Pendientes.</TD></TR>");  %>
				</div>

			</TD>			  	
      	</TR>	
  	</TABLE>
  	
</FORM>

<script language="javascript">
	
	function showEnterUser() {
		var y = 200 - document.body.scrollTop;
	    var x = 200 - document.body.scrollLeft;	
     	eval('enterTo.style.pixelTop=' + y);
     	eval('enterTo.style.pixelLeft=' + x);
		document.getElementById("enterTo").style.visibility = "visible";
		document.getElementById("user").focus();
	}
	
	function hideEnterUser() {
		document.getElementById("enterTo").style.visibility = "hidden";
	}
	
	function cancelBub() {
		event.cancelBubble = true;
	}
	
	function goAction(op) {
		var allow = <%= request.getAttribute("rejected") %>
		allow = 0; //Force to Approvar
		if (allow == 0) {
			document.getElementById("OPERATION").value = op;
			document.form.submit();
		} else {
			alert("El Lote no puede ser Aprobado porque existen Transacciones Rechazadas.");
		}	
	}

	function goExit(){
    	window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
  	}
  	
   adjustEquTables(document.getElementById("headTable1"),
   				   document.getElementById("dataTable1"),
   	               document.getElementById("dataDiv1"), 1, false);
   	               
   adjustEquTables(document.getElementById("headTable2"),
   				   document.getElementById("dataTable2"),
   	               document.getElementById("dataDiv2"), 1, false);
   	               
  	
	document.onclick = hideEnterUser;

</script>

</BODY>
</HTML>
