<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Conexión</TITLE>
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
  if (value == "I" || value == "N"){    
   
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
		NameSearch=".";
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
</script>

</HEAD>


<body > 


<h3 align="center">Analisis de Riesgo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cr_enter_basic, ECIF200"></h3>
<hr size="4">
 <FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF200" target="_parent" onsubmit="return(enterRUT());">
  <CENTER>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <input type="hidden" name="E01CUN" value="<%= client.getE01CUN() %>" size=15 maxlength=9 >
  <input type="hidden" name="E01IDN" value="<%= client.getE01IDN() %>" size=15 maxlength=9 >
       
  <table id="TBHELP" align="center">
    <tr>
	 <td nowrap>
	   <table  id="TBHELP" align="center" width="250" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap id="THHELP">Búsqueda por:</td>
		  <td nowrap><input type="radio" name="Type" value="N" checked onclick="typeClick('N')">N&uacute;mero</td>		
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="I" onclick="typeClick('I')">Identificaci&oacute;n</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="S" onclick="typeClick('S')">Nombre Corto</td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="W" onclick="typeClick('W')">Por Palabra</td>
		</tr>
	   </table>
	 </td>
	 <td nowrap>
       <div id=DIVNAME >
        <input type="text" name="NameSearch"  size=14 maxlength="20">
            &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter()"> 
          </div>
     </td>
   </tr>
   <tr valign="middle"> 
      <td nowrap colspan="2" align="middle" >
      	  <p>
          	<div id=DIVSUBMIT align="center">    
    			<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          	</div>
          
        </td>
    </tr>
      
 </table>
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">;     
     <% 		
 		if (currClient != null && !currClient.getE01CUSCUN().equals("0")) {
  	 %>
  		document.forms[0].NameSearch.value = "<%= currClient.getE01CUSCUN() %>";
  		enter();	
 	<%	}
		%>
		document.forms[0].NameSearch.focus();
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
 