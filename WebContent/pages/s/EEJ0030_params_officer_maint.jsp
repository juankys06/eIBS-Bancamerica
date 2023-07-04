<html> 
<head>
<title>Mantenimiento de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<jsp:useBean id= "msgMT1" class= "datapro.eibs.beans.EEJ003001Message"  scope="session" />
<jsp:useBean id= "mtList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   int row = 0;
   try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
   mtList.setCurrentRow(row);
   datapro.eibs.beans.EEJ003001Message msgMT = (datapro.eibs.beans.EEJ003001Message) mtList.getRecord();
   
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
      msgMT = msgMT1;
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</head>
<body>

<H3 align="center">Mantenimiento de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="params_officer_maint,EEJ0030"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0030">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Usuario : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJEUSR" size="12" maxlength="10" readonly value="<%= msgMT.getE01EJEUSR() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">C&oacute;digo : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJECOD" size="5" maxlength="4" value="<%= msgMT.getE01EJECOD() %>">
	         <a href="javascript:GetCodeCNOFC('E01EJECOD','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Nombre : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJENAM" readonly size="50" maxlength="45" value="<%= msgMT.getE01EJENAM() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">R.U.T : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJERUT" size="18" maxlength="15" value="<%= msgMT.getE01EJERUT() %>">
	      </td>    
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJEBNK" size="3" maxlength="2" readonly value="<%= msgMT.getE01EJEBNK() %>">
	        <input type="text" name="E01EJEBNN" size="40" maxlength="35" readonly value="<%= msgMT.getE01EJEBNN() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EJEBRN" size="4" readonly maxlength="3" value="<%= msgMT.getE01EJEBRN() %>" onKeypress="enterInteger()">
	      	<input type="text" name="E01EJEBRM" size="40" maxlength="35" readonly value="<%= msgMT.getE01EJEBRM() %>">
	        <a href="javascript:GetBranch('E01EJEBRN',document.forms[0].E01EJEBNK.value)"></a></td>
     	</tr>
     	<tr id=trdark> 
		  <td nowrap width="20%"> 
		     <div align="right">Centro de Costo : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJECCN" size="9" maxlength="8" readonly value="<%= msgMT.getE01EJECCN().trim()%>">
             <a href="javascript:GetCostCenter('E01EJECCN',document.forms[0].E01EJEBNK.value)"></a> 
            </td>     
      	</tr>
      	<tr id=trclear> 
		  <td nowrap width="20%"> 
		     <div align="right">Ejecutivo Superior : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01EJESUP" size="12" maxlength="10" value="<%= msgMT.getE01EJESUP()%>">
      	    <a href="javascript:GetOfficer('E01EJESUP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">E-Mail : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJEEML" size="45" readonly maxlength="40" value="<%= msgMT.getE01EJEEML() %> ">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="20%"> 
	        <div align="right">Tel&eacute;fono : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJEPHN" size="13" maxlength="11" value="<%= msgMT.getE01EJEPHN() %>" onKeypress="enterInteger()">
	        &nbsp;&nbsp;Extensi&oacute;n :
	        <input type="text" name="E01EJEPXT" size="5" maxlength="4" value="<%= msgMT.getE01EJEPXT() %>" onKeypress="enterInteger()"> 
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="20%"> 
	        <div align="right">Lote Inicial : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01EJEFB1" size="5" maxlength="4" readonly value="<%= msgMT.getE01EJEFB1() %>" onKeypress="enterInteger()">
	        &nbsp;&nbsp;Lote Final :
	        <input type="text" name="E01EJETB1" size="5" maxlength="4" readonly value="<%= msgMT.getE01EJETB1() %>" onKeypress="enterInteger()">
	        &nbsp;&nbsp;Lote por Omisi&oacute;n :
	        <input type="text" name="E01EJEDIB" size="5" maxlength="4" readonly value="<%= msgMT.getE01EJEDIB() %>" onKeypress="enterInteger()">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">C&oacute;digo de Jerarqu&iacute;a :</div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EJEJRQ" size="5" maxlength="4" value="<%= msgMT.getE01EJEJRQ() %>">
      	    <input type="text" name="E01EJEJRN" size="40" maxlength="35" value="<%= msgMT.getE01EJEJRN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EJEJRQ','E01EJEJRN','YY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Jerarqu&iacute;a Superior Activos:</div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EUPF05" size="5" maxlength="4" value="<%= msgMT.getE01EUPF05() %>">
      	    <input type="text" name="D01EUPF05" size="40" maxlength="35" value="<%= msgMT.getD01EUPF05() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EUPF05','D01EUPF05','YY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>
     	</tr>
     	
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Jerarqu&iacute;a Superior Pasivos:</div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01EUPF06" size="5" maxlength="4" value="<%= msgMT.getE01EUPF06() %>">
      	    <input type="text" name="D01EUPF06" size="40" maxlength="35" value="<%= msgMT.getD01EUPF06() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01EUPF06','D01EUPF06','YY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
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
