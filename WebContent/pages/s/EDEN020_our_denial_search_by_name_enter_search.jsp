<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Mantenimiento de Lista Propia</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<script language="JavaScript">

function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}

function enterSearch(){
	var E01DOAME = document.forms[0].E01DOAME.value;
	var FromRecord = 0;

    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=200@E01DOAME=" + E01DOAME + "@FromRecord=" + FromRecord + "'";
    return(false);
}

</script>

</HEAD>


<body> 

<h3 align="center">Lista Propia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="our_denial_search_by_name_enter_search.jsp, EDEN020"></h3>
<hr size="4">
 <FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN020" target="_parent" onsubmit="return enterSearch()">
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 
  <CENTER>  
  	 <table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead" >
		<tr id="trdark">
		  <td nowrap width="30%">
		  	<div align="right">Buscar por Nombre :</div>
		  </td>
		  <td nowrap colspan="3" width="300">
		   	<input type="text" name="E01DOAME" size="65" maxlength="60" value="" >
		  </td>	
		  <td nowrap >
		  </td>			  	
		</tr>
	</table>	
	 	
  		<div id=DIVSUBMIT align="center"> 
  		  <p align=center>   
			<input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  		   </p>
  		</div>
 	
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">;
	     
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
 