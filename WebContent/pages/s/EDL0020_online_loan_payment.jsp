<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Pago de cuotas On-Line</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgData" class= "datapro.eibs.beans.EDL002001Message"  scope="session" />
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0020" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<h3 align="center">Pago de cuotas On-Line<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="online_loan_payment.jsp,EDL0020"></h3>
<hr size="4">

  <CENTER><BR>
<BR>
<BR>
<BR>
<TABLE class="tableinfo" cellspacing="2" cellpadding="2"><CAPTION align="bottom"></CAPTION>
    <TR id="trclear" valign="middle"> 
	      <TD nowrap> 
	        <DIV align="right">Fecha de Inicio : </DIV>
	      </TD>
	      <TD nowrap>
	   	    <INPUT type="text" name="E01FRMDTM" size="3" maxlength="2" value="<%= msgData.getE01FRMDTM() %>">
	   	    <INPUT type="text" name="E01FRMDTD" size="3" maxlength="2" value="<%= msgData.getE01FRMDTD() %>">
	   	    <INPUT type="text" name="E01FRMDTY" size="3" maxlength="2" value="<%= msgData.getE01FRMDTY() %>">
	      	<A href="javascript:DatePicker(document.forms[0].E01FRMDTM,document.forms[0].E01FRMDTD,document.forms[0].E01FRMDTY)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="bottom" border="0"></A>
      	  </TD>
     	</TR>
      	<TR id="trdark"> 
	      <TD nowrap> 
	        <DIV align="right">Fecha de Fin : </DIV>
	      </TD>
	      <TD nowrap>
	   	    <INPUT type="text" name="E01TODATM" size="3" maxlength="2" value="<%= msgData.getE01TODATM() %>">
	   	    <INPUT type="text" name="E01TODATD" size="3" maxlength="2" value="<%= msgData.getE01TODATD() %>">
	   	    <INPUT type="text" name="E01TODATY" size="3" maxlength="2" value="<%= msgData.getE01TODATY() %>">
	      	<A href="javascript:DatePicker(document.forms[0].E01TODATM,document.forms[0].E01TODATD,document.forms[0].E01TODATY)"><IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="bottom" border="0"></A>
	      	
      	  </TD>
     	</TR>
  </TABLE></CENTER>
  <p align="center">
	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>