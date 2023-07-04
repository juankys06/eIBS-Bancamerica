<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Importar FED</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

</SCRIPT>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="msgSrc" class="datapro.eibs.beans.EDEN01001Message"  scope="session" />

</HEAD>

<body>

 <h3 align="center">Búsqueda de Parámetros FINCEN<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="search_parameters.jsp, EDEN015" width="32" height="32" ></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDEN015">	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
    
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0"> 
 
        <tr id=trdark> 
	      <td nowrap width="50%"> 
	        <div align="right">Tipo de Búsqueda :</div>
	      </td>
		  <td nowrap width="50%" >
              <input type="radio" name="E01SRCHTP" value="S"  <%if(msgSrc.getE01SRCHTP().equals("S")) out.print("checked");%>>Suena a 
              <input type="radio" name="E01SRCHTP" value="W"  <%if(msgSrc.getE01SRCHTP().equals("W")) out.print("checked");%>>Palabra		  
		  </td>
		</tr>  
		 <tr id=trclear> 
	      <td nowrap width="50%"> 
	        <div align="right">Número de Coincidencias Mínimas a encontrar :</div>
	      </td>
		  <td nowrap width="50%" >
 			<input type="text" name="E01NROWOR" size="3" maxlength="2" value="<%= msgSrc.getE01NROWOR().trim()%>" onkeypress="enterInteger()">	
 		  </td>
		</tr>  
        <tr id=trdark> 
	      <td nowrap width="50%"> 
	        <div align="right">Número de Coincidencias a mostrar :</div>
	      </td>
		  <td nowrap width="50%" >
		  	<input type="text" name="E01NROMAT" size="3" maxlength="2" value="<%= msgSrc.getE01NROMAT().trim()%>" onkeypress="enterInteger()">
		  </td>
		</tr>  		  
 	  </table>
    </td>
   </tr>
  </table>
  <br>
	<p align="center"> 
	  <input id="EIBSBTN" type="submit" name="Submit" value="Enviar" > 
 	</p> 


<script language="JavaScript">
  document.forms[0].FILENAME.focus();
</script>
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
 </FORM>
</BODY>
</HTML>
 