<html> 
<head>
<title>Creacion de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgClas" class= "datapro.eibs.beans.EEJ000001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<% 
   if (msgClas == null) msgClas = new datapro.eibs.beans.EEJ000001Message();   
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
</head>
<body>

<H3 align="center">Creacion de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_approv_limits_new,EEJ0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Tipo de Agrupaci&oacute;n :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLGRT" size="5" maxlength="4" value="<%= msgClas.getE01EJLGRT() %>">
      	    <input type="text" name="E01EJLGRN" size="35" maxlength="35" value="<%= msgClas.getE01EJLGRN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJLGRT','E01EJLGRN','Y0')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">C&oacute;digo de Agrupaci&oacute;n :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLGRC" size="5" maxlength="4" value="<%= msgClas.getE01EJLGRC() %>">
      	    <input type="text" name="E01EJLGRM" size="35" maxlength="35" value="<%= msgClas.getE01EJLGRM() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJLGRC','E01EJLGRM',document.forms[0].E01EJLGRT.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">C&oacute;digo de Jerarqu&iacute;a :</div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EJLJRQ" size="5" maxlength="4" value="<%= msgClas.getE01EJLJRQ() %>">
      	    <input type="text" name="E01EJLJRN" size="35" maxlength="35" value="<%= msgClas.getE01EJLJRN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJLJRQ','E01EJLJRN','YY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Moneda :</div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EJLCCY" size="4" maxlength="3" value="<%= msgClas.getE01EJLCCY() %>">
      	    <input type="text" name="E01EJLCCN" size="35" maxlength="35" value="<%= msgClas.getE01EJLCCN() %>" readonly >
      	    <a href="javascript:GetCurrency('E01EJLCCY',<%=currUser.getE01UBK()%>)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right"> <h4> Operaciones Activas </h4> </div>        
		  </td>
		</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Puntos Persona Natural :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLRTN" size="12" maxlength="9" value="<%= msgClas.getE01EJLRTN() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Puntos Persona Jur&iacute;dica :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLRTJ" size="12" maxlength="9" value="<%= msgClas.getE01EJLRTJ() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Montos Persona Natural :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLAMN" size="17" maxlength="15" value="<%= msgClas.getE01EJLAMN() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Montos Persona Jur&iacute;dica :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLAMJ" size="17" maxlength="15" value="<%= msgClas.getE01EJLAMJ() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right"> <h4> Operaciones Pasivas </h4> </div>        
		  </td>
		</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Puntos Persona Natural :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLRCN" size="12" maxlength="9" value="<%= msgClas.getE01EJLRCN() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Puntos Persona Jur&iacute;dica :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLRCJ" size="12" maxlength="9" value="<%= msgClas.getE01EJLRCJ() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trdark> 
		  <td> 
		     <div align="right">Montos Persona Natural :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLACN" size="17" maxlength="15" value="<%= msgClas.getE01EJLACN() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Montos Persona Jur&iacute;dica :</div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJLACJ" size="17" maxlength="15" value="<%= msgClas.getE01EJLACJ() %>" onkeypress="enterDecimal()">
      	  </td>     
      	</tr>




     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>
  
</form>
</body>
</html>
