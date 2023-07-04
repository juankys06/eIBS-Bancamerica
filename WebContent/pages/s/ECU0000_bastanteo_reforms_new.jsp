<html> 
<head>
<title>Creacion de Reformas Documento Constitutivo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgRef" class= "datapro.eibs.beans.ECU000004Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgRef == null) msgRef = new datapro.eibs.beans.ECU000004Message();   
%>
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

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 40;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Creacion de Reformas Documento Constitutivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_reforms_new,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="44">
  <table class="tableinfo">
   <tr>
   	  <td>
   		<TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E04CURCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E04CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
 		</TABLE>
 	  </td>
   </tr>
   </table>
   <h4></h4>
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de la Reforma 1: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURRD1" size="2" maxlength="2" value="<%= msgRef.getE04CURRD1() %>">
	      	<input type="text" name="E04CURRD2" size="2" maxlength="2" value="<%= msgRef.getE04CURRD2() %>">
	      	<input type="text" name="E04CURRD3" size="2" maxlength="2" value="<%= msgRef.getE04CURRD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CURRD1,document.forms[0].E04CURRD2,document.forms[0].E04CURRD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURRMC" size="3" maxlength="2" value="<%= msgRef.getE04CURRMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURRE1" size="2" maxlength="2" value="<%= msgRef.getE04CURRE1() %>">
	      	<input type="text" name="E04CURRE2" size="2" maxlength="2" value="<%= msgRef.getE04CURRE2() %>">
	      	<input type="text" name="E04CURRE3" size="2" maxlength="2" value="<%= msgRef.getE04CURRE3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CURRE1,document.forms[0].E04CURRE2,document.forms[0].E04CURRE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURCIR" size="45" maxlength="40" value="<%= msgRef.getE04CURCIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURDOC" size="9" maxlength="7" value="<%= msgRef.getE04CURDOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CURTOM" size="10" maxlength="8" value="<%= msgRef.getE04CURTOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CUREXP" size="15" maxlength="12" value="<%= userPO.getHeader19() %>" readonly> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CURCOD" size="5" maxlength="4" value="<%= msgRef.getE04CURCOD() %>">
      	    <input type="text" name="E04CURCON" size="40" maxlength="35" value="<%= msgRef.getE04CURCON() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E04CURCOD','E04CURCON','YM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
     </table>
         </td>
   </tr>
     
     </table>
   <h4></h4>  
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de la Reforma 2: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU1RD1" size="2" maxlength="2" value="<%= msgRef.getE04CU1RD1() %>">
	      	<input type="text" name="E04CU1RD2" size="2" maxlength="2" value="<%= msgRef.getE04CU1RD2() %>">
	      	<input type="text" name="E04CU1RD3" size="2" maxlength="2" value="<%= msgRef.getE04CU1RD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU1RD1,document.forms[0].E04CU1RD2,document.forms[0].E04CU1RD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU1RMC" size="3" maxlength="2" value="<%= msgRef.getE04CU1RMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU1RE1" size="2" maxlength="2" value="<%= msgRef.getE04CU1RE1() %>">
	      	<input type="text" name="E04CU1RE2" size="2" maxlength="2" value="<%= msgRef.getE04CU1RE2() %>">
	      	<input type="text" name="E04CU1RE3" size="2" maxlength="2" value="<%= msgRef.getE04CU1RE3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU1RE1,document.forms[0].E04CU1RE2,document.forms[0].E04CU1RE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU1CIR" size="45" maxlength="40" value="<%= msgRef.getE04CU1CIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU1DOC" size="9" maxlength="7" value="<%= msgRef.getE04CU1DOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU1TOM" size="10" maxlength="8" value="<%= msgRef.getE04CU1TOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU1EXP" size="15" maxlength="12" value="<%= userPO.getHeader19() %>" readonly> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU1COD" size="5" maxlength="4" value="<%= msgRef.getE04CU1COD() %>">
      	    <input type="text" name="E04CU1CON" size="40" maxlength="35" value="<%= msgRef.getE04CU1CON() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E04CU1COD','E04CU1CON','YM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
     </table>
         </td>
   </tr>
     
     </table>
   <h4></h4>  
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de la Reforma 3: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU2RD1" size="2" maxlength="2" value="<%= msgRef.getE04CU2RD1() %>">
	      	<input type="text" name="E04CU2RD2" size="2" maxlength="2" value="<%= msgRef.getE04CU2RD2() %>">
	      	<input type="text" name="E04CU2RD3" size="2" maxlength="2" value="<%= msgRef.getE04CU2RD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU2RD1,document.forms[0].E04CU2RD2,document.forms[0].E04CU2RD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU2RMC" size="3" maxlength="2" value="<%= msgRef.getE04CU2RMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU2RE1" size="2" maxlength="2" value="<%= msgRef.getE04CU2RE1() %>">
	      	<input type="text" name="E04CU2RE2" size="2" maxlength="2" value="<%= msgRef.getE04CU2RE2() %>">
	      	<input type="text" name="E04CU2RE3" size="2" maxlength="2" value="<%= msgRef.getE04CU2RE3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU2RE1,document.forms[0].E04CU2RE2,document.forms[0].E04CU2RE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU2CIR" size="45" maxlength="40" value="<%= msgRef.getE04CU2CIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU2DOC" size="9" maxlength="7" value="<%= msgRef.getE04CU2DOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU2TOM" size="10" maxlength="8" value="<%= msgRef.getE04CU2TOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU2EXP" size="15" maxlength="12" value="<%= userPO.getHeader19() %>" readonly> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU2COD" size="5" maxlength="4" value="<%= msgRef.getE04CU2COD() %>">
      	    <input type="text" name="E04CU2CON" size="40" maxlength="35" value="<%= msgRef.getE04CU2CON() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E04CU2COD','E04CU2CON','YM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
     </table>
         </td>
   </tr>
     
     </table>
   <h4></h4>  
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de la Reforma 4: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU3RD1" size="2" maxlength="2" value="<%= msgRef.getE04CU3RD1() %>">
	      	<input type="text" name="E04CU3RD2" size="2" maxlength="2" value="<%= msgRef.getE04CU3RD2() %>">
	      	<input type="text" name="E04CU3RD3" size="2" maxlength="2" value="<%= msgRef.getE04CU3RD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU3RD1,document.forms[0].E04CU3RD2,document.forms[0].E04CU3RD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU3RMC" size="3" maxlength="2" value="<%= msgRef.getE04CU3RMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU3RE1" size="2" maxlength="2" value="<%= msgRef.getE04CU3RE1() %>">
	      	<input type="text" name="E04CU3RE2" size="2" maxlength="2" value="<%= msgRef.getE04CU3RE2() %>">
	      	<input type="text" name="E04CU3RE3" size="2" maxlength="2" value="<%= msgRef.getE04CU3RE3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU3RE1,document.forms[0].E04CU3RE2,document.forms[0].E04CU3RE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU3CIR" size="45" maxlength="40" value="<%= msgRef.getE04CU3CIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU3DOC" size="9" maxlength="7" value="<%= msgRef.getE04CU3DOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU3TOM" size="10" maxlength="8" value="<%= msgRef.getE04CU3TOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU3EXP" size="15" maxlength="12" value="<%= userPO.getHeader19() %>" readonly> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU3COD" size="5" maxlength="4" value="<%= msgRef.getE04CU3COD() %>">
      	    <input type="text" name="E04CU3CON" size="40" maxlength="35" value="<%= msgRef.getE04CU3CON() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E04CU3COD','E04CU3CON','YM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
     </table>
         </td>
   </tr>
     
     </table>
   <h4></h4>  
   <table class="tableinfo">	
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de la Reforma 5: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU4RD1" size="2" maxlength="2" value="<%= msgRef.getE04CU4RD1() %>">
	      	<input type="text" name="E04CU4RD2" size="2" maxlength="2" value="<%= msgRef.getE04CU4RD2() %>">
	      	<input type="text" name="E04CU4RD3" size="2" maxlength="2" value="<%= msgRef.getE04CU4RD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU4RD1,document.forms[0].E04CU4RD2,document.forms[0].E04CU4RD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id=trclear> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Registro Mercantil: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU4RMC" size="3" maxlength="2" value="<%= msgRef.getE04CU4RMC() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Registro: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU4RE1" size="2" maxlength="2" value="<%= msgRef.getE04CU4RE1() %>">
	      	<input type="text" name="E04CU4RE2" size="2" maxlength="2" value="<%= msgRef.getE04CU4RE2() %>">
	      	<input type="text" name="E04CU4RE3" size="2" maxlength="2" value="<%= msgRef.getE04CU4RE3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E04CU4RE1,document.forms[0].E04CU4RE2,document.forms[0].E04CU4RE3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Circunscripci&oacute;n: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU4CIR" size="45" maxlength="40" value="<%= msgRef.getE04CU4CIR() %>"> 
		  </td>
     	</tr>    
      	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Documento: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU4DOC" size="9" maxlength="7" value="<%= msgRef.getE04CU4DOC() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap >
				<div align="right">Tomo: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU4TOM" size="10" maxlength="8" value="<%= msgRef.getE04CU4TOM() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">N&uacute;mero de Expediente: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E04CU4EXP" size="15" maxlength="12" value="<%= userPO.getHeader19() %>" readonly> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td nowrap> 
		     <div align="right">C&oacute;digo de Materia : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E04CU4COD" size="5" maxlength="4" value="<%= msgRef.getE04CU4COD() %>">
      	    <input type="text" name="E04CU4CON" size="40" maxlength="35" value="<%= msgRef.getE04CU4CON() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E04CU4COD','E04CU4CON','YM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
     </table>
     
     
     
    </td>
   </tr>

  </table>
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB1" size="90" maxlength="80" value="<%= msgRef.getE04CUROB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB2" size="90" maxlength="80" value="<%= msgRef.getE04CUROB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB3" size="90" maxlength="80" value="<%= msgRef.getE04CUROB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB4" size="90" maxlength="80" value="<%= msgRef.getE04CUROB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E04CUROB5" size="90" maxlength="80" value="<%= msgRef.getE04CUROB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
   </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>
