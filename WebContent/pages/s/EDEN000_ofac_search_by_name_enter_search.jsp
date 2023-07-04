<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Búsqueda OFAC</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"   scope="session"/>

<script language="JavaScript">

function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}

function enterSearch(firsTime){
	var RWDNAM = document.forms[0].RWDNAM.value;
	var FromRecord = 0;
	var RWDTYP = " ";
	var RWDCON = document.forms[0].RWDCON.value;

	for(var i = 0; i < document.forms[0].RWDTYP.length; i++)
	{
		if (document.forms[0].RWDTYP[i].checked)
			 RWDTYP = document.forms[0].RWDTYP[i].value;
	}
	
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN000?SCREEN=200@RWDNAM=" + RWDNAM + "@FromRecord=" + FromRecord  + "@RWDTYP=" + RWDTYP  + "@RWDCON=" + RWDCON + "'";
}

</script>

</HEAD>


<body> 

<%	// Added By Henry (12/06/2006) Valid Only for Bradesco Luxembourg, word OFAC replaced by "World Check"
if (currUser.getH01WK3().equals("4") ) {	
%>
<H3 align="center">Búsqueda de Cheques Globales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ofac_search_by_name_enter_search.jsp, EDEN000"></H3>
<% } else { %>
<h3 align="center">Oficina de Control de Activos Extranjeros (OFAC)<br>Búsqueda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ofac_search_by_name_enter_search.jsp, EDEN000"></h3>
<% } %>

<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN000" target="_parent" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <CENTER>  
  	 <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead" >
		<tr id="trdark">
		  <td nowrap width="30%">
		  	<div align="right">Nombre :</div>
		  </td>
		  <td nowrap colspan="3" width="300">
		   	<input type="text" name="RWDNAM" size="65" maxlength="60" value="" >
		  </td>	
		  <td nowrap >
		   	
		  </td>			  	
		</tr>
		<tr id="trdark">
		  <td nowrap width="30%">
		  	<div align="right">Búsqueda por :</div>
		  </td>
		  <td nowrap width="100">
		    <input type="radio" name="RWDTYP" value=" " checked>Nombre
		  Completo</td>		
		  <td nowrap width="100">
		  	<input type="radio" name="RWDTYP" value="S" >Suena
		  a</td>
		  <td nowrap width="100">
		  	<input type="radio" name="RWDTYP" value="W" >Palabra
		  </td>
		  <td nowrap >
		   	
		  </td>			  	
		  
		</tr>		
		<tr id="trdark">
		  <td nowrap width="30%">
		  	<div align="right">Número de Coincidencias :</div>
		  </td>
		  <td nowrap  colspan="3" >
		   	<input type="text" name="RWDCON" size="3" maxlength="2" value="" onkeypress="enterInteger()"> (Sólo para opciones 'Suena a' y 'Palabra')
		  </td>		
		  <td nowrap >
		   	
		  </td>			  	
		  
		</tr>
	</table>	
	 	
  		<div id=DIVSUBMIT align="center"> 
  		  <p align=center>   
			<input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:enterSearch(false)">
  		   </p>
  		</div>
 	
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">;
	 
     function resizeDoc() {
       for(var i = 0; i < document.forms[0].RWDTYP.length; i++)
		{
		if (document.forms[0].RWDTYP[i].checked)
			document.forms[0].RWDTYP[i].click();
		}
     }
     
     window.onresize=resizeDoc;
</SCRIPT>
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
     <SCRIPT Language="Javascript">
           enterSearch(true);
     </SCRIPT>

</BODY>
</HTML>
 