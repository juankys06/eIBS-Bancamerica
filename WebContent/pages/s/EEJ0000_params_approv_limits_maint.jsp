<html> 
<head>
<title>Mantenimiento de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.EEJ000001Message msgMT = (datapro.eibs.beans.EEJ000001Message) mtList.getRecord();
   
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

<H3 align="center">Mantenimiento de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_approv_limits,EEJ0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">

<table class="tableinfo" height="97">
	<tr>
		<td height="125">
		<table cellspacing=0 cellpadding=2 width="100%" border="0">
			<tr id=trdark>
				<td nowrap width="20%">
				<div align="right">Tipo de Agrupaci&oacute;n :</div>
				</td>
				<td nowrap><input type="text" name="E01EJLGRT" size="4"
					maxlength="4" readonly value="<%= msgMT.getE01EJLGRT() %>"> <input
					type="text" name="E01EJLGRN" size="35" maxlength="35" readonly
					value="<%= msgMT.getE01EJLGRN() %>"></td>
			</tr>
			<tr id=trclear>
				<td nowrap width="20%">
				<div align="right">C&oacute;digo de Agrupaci&oacute;n :</div>
				</td>
				<td nowrap><input type="text" name="E01EJLGRC" size="4"
					maxlength="4" readonly value="<%= msgMT.getE01EJLGRC() %>"> <input
					type="text" name="E01EJLGRM" size="35" maxlength="35" readonly
					value="<%= msgMT.getE01EJLGRM() %>"></td>
			</tr>
			<tr id=trdark>
				<td nowrap width="20%">
				<div align="right">C&oacute;digo de Jerarqu&iacute;a :</div>
				</td>
				<td nowrap><input type="text" name="E01EJLJRQ" size="4"
					maxlength="4" readonly value="<%= msgMT.getE01EJLJRQ() %>"> <input
					type="text" name="E01EJLJRN" size="35" maxlength="35" readonly
					value="<%= msgMT.getE01EJLJRN() %>"></td>
			</tr>
			<tr id=trclear>
				<td nowrap width="20%">
				<div align="right">Moneda :</div>
				</td>
				<td nowrap><input type="text" name="E01EJLCCY" size="3"
					maxlength="3" readonly value="<%= msgMT.getE01EJLCCY() %>">
				<input type="text" name="E01EJLCCN" size="35" maxlength="35" readonly value="<%= msgMT.getE01EJLCCN() %>">
	      		</td>
     		</tr>
     		<tr id=trdark> 
			  <td nowrap width="20%"> 
			     <div align="right"> <h4> Operaciones Activas </h4> </div>        
			  </td>
	      	</tr>
	     	<tr id=trclear> 
			  <td nowrap width="20%"> 
			     <div align="right">Puntos Persona Natural : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLRTN" size="12" maxlength="9" value="<%= msgMT.getE01EJLRTN() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trdark> 
			  <td nowrap width="20%"> 
			     <div align="right">Puntos Persona Jur&iacute;dica : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLRTJ" size="12" maxlength="9" value="<%= msgMT.getE01EJLRTJ() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trclear> 
			  <td nowrap> 
			     <div align="right">Montos Persona Natural : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLAMN" size="17" maxlength="15" value="<%= msgMT.getE01EJLAMN() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trdark> 
			  <td nowrap> 
			     <div align="right">Montos Persona Jur&iacute;dica : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLAMJ" size="17" maxlength="15" value="<%= msgMT.getE01EJLAMJ() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
   		<tr id=trclear> 
			  <td nowrap width="20%"> 
			     <div align="right"> <h4> Operaciones Pasivas </h4> </div>        
			  </td>
	      	</tr>
	     	<tr id=trdark> 
			  <td nowrap width="20%"> 
			     <div align="right">Puntos Persona Natural : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLRCN" size="12" maxlength="9" value="<%= msgMT.getE01EJLRCN() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trclear> 
			  <td nowrap width="20%"> 
			     <div align="right">Puntos Persona Jur&iacute;dica : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLRCJ" size="12" maxlength="9" value="<%= msgMT.getE01EJLRCJ() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trdark> 
			  <td nowrap> 
			     <div align="right">Montos Persona Natural : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLACN" size="17" maxlength="15" value="<%= msgMT.getE01EJLACN() %>" onkeypress="enterDecimal()">
	      	  </td>     
	      	</tr>
	      	<tr id=trclear> 
			  <td nowrap> 
			     <div align="right">Montos Persona Jur&iacute;dica : </div>        
			  </td>
	      	  <td nowrap> 
	      	    <input type="text" name="E01EJLACJ" size="17" maxlength="15" value="<%= msgMT.getE01EJLACJ() %>" onkeypress="enterDecimal()">
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
