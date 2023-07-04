<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Cambio de Identificación</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 


<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript" id="eAction">  
function enterAction(code,desc,id) {
 var form = document.forms[0];

 form["E01CUN"].value = code;
 form["E01IDN"].value = id;
  
 form.submit();
}
</script>
 
<script language="JavaScript">

function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}

function typeClick(value){
  if (value == "I" || value == "N" || value == "D"){    
   
   DIVSUBMIT.style.display="";   
  }
  else{
   
   DIVSUBMIT.style.display="none"; 
  }
  document.forms[0].NameSearch.value="";
  document.forms[0].NameSearch.focus();
}

function enter(){
var NameSearch = document.forms[0].NameSearch.value;
var FromRecord = 0;
var Type ='N';

	if (NameSearch.length < 1){
		//NameSearch=".";
		alert("Debe entrar algún criterio de búsqueda");
		return ;
	}
	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			Type = document.forms[0].Type[i].value;
	}
    
    if (Type == "N") {
		NameSearch = justifyRight(NameSearch,20);
	}
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0001?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
}

function enterRUT(){
var NameSearch = trim(document.forms[0].NameSearch.value);
    if (DIVSUBMIT.style.display !="") return true;
	if (NameSearch.length > 0){
	    if (document.forms[0].Type[0].checked) document.forms[0].E01CUN.value=NameSearch;
	    else {
	    	document.forms[0].E01IDN.value=NameSearch;
	    	document.forms[0].E01CUN.value="0";
	    }
	    return true;
	}else{
		alert("Es requerido que se entre un valor");
		document.forms[0].NameSearch.focus();
		return false;
	}
}

function showSearch() {
     btnSearch.style.visibility="visible";
}

function hideSearch() {
     btnSearch.style.visibility="hidden";
}
</script>

</HEAD>


<body > 

<% 
String title = "";
String action = "";

 if (userPO.getPurpose().equals("PDN")) {
   title="PDN - ";
   action = request.getContextPath()+ userPO.getRedirect();
 } else {
   title="Cambio de Identificación de";
   action = request.getContextPath() + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=55";
 }
%>
<h3 align="center"><%= title %> Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_id_change_enter_search.jsp, ESD0080"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%= action %>" target="_parent" onsubmit="return(enterRUT());">
  <CENTER>
  <!--<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">-->
  <input type="hidden" name="E01CUN" value="<%= client.getE01CUN() %>" size=15 maxlength=9 >
  <input type="hidden" name="E01IDN" value="<%= client.getE01IDN() %>" size=15 maxlength=9 >
       
  <table id="TBHELP" align="center">
    <tr>
	 <td nowrap>
	   <table  id="TBHELP" align="center" width="250" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap id="THHELP">Búsqueda por:</td>
		  <td nowrap><input type="radio" name="Type" value="N" checked onclick="javascript:hideSearch();typeClick('N');">N&uacute;mero</td>		
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="I" onclick="javascript:showSearch();typeClick('I');">Identificaci&oacute;n</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="D" onclick="javascript:showSearch();typeClick('D');">Identificaci&oacute;n Larga</td>
		</tr>
		
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="S" onclick="javascript:showSearch();typeClick('S');">Nombre Corto</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="W" onclick="javascript:showSearch();typeClick('W');">Por Palabra</td>
		</tr>
	   </table>
	 </td>
	 <td nowrap width="168">
        <input type="text" name="NameSearch"  size=25 maxlength="30">
     </td>
	<TD nowrap width="36">
        <div id="btnSearch"><img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter()" width="25" height="20"></div>
	</TD>
	</tr>
   <tr valign="middle">
		<td nowrap colspan="3" align="center">
		<p>
		<div id=DIVSUBMIT align="center"><input id="EIBSBTN" type=submit
			name="Submit" value="Enviar"></div>
		</td>
	</tr>
      
 </table>
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">     
     <% 		
 		if (currClient != null && !currClient.getE01CUSCUN().equals("0")) {
  	 %>
  		document.forms[0].NameSearch.value = "<%= currClient.getE01CUSCUN() %>";
  		enter();	
 	<%	}
		%>
		document.forms[0].NameSearch.focus();
		hideSearch();
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
</BODY>
</HTML>
 