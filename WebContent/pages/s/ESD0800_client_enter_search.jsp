<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>

<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Conexión</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="JavaScript" id="eAction">  
function enterAction(code,desc,id) {
 var form = document.forms[0];
// var Type = "N";
 //for(var i = 0; i < document.forms[0].Type.length; i++)
//	{
//		if (document.forms[0].Type[i].checked)
//			Type = document.forms[0].Type[i].value;
//	}
 //if (Type == "A") { //Account Search
 //	form["E01CUSCUN"].value = desc;
// } else {
 	form["E01CUSCUN"].value = code;
// }
 form["E01CUSIDN"].value = id;
  
 form.submit();
}
</script>
 
<script language="JavaScript">

<% if (currClient.getH01WK1().equals("1")) {%>
    parent.parent.client.document.forms[0].E01CUSIDN.value="<%= currClient.getE01CUSIDN() %>";
    parent.parent.client.document.forms[0].E01CUSNA1.value="<%= currClient.getE01CUSNA1() %>";
    parent.parent.client.document.forms[0].E01RELBAN.value="<%= currClient.getE01RELBAN() %>";
   //parent.window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
 <% } %>
     
function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}

function typeClick(value,row){
  if (value == "I" || value == "N"){    
      DIVSUBMIT.style.display="";
      if (value == "N") document.forms[0].NameSearch.onkeypress = enterInteger;
      else document.forms[0].NameSearch.onkeypress = ""; 
  }
  else{   
   	  DIVSUBMIT.style.display="none";
   	  if (value == "A") document.forms[0].NameSearch.onkeypress = enterInteger;
      else document.forms[0].NameSearch.onkeypress = ""; 
  }
  DIVNAME.style.pixelTop=TBHELP.offsetTop + TBHELP.rows[row].cells[2].offsetTop;
  DIVNAME.style.pixelLeft=TBHELP.offsetLeft + TBHELP.rows[row].cells[2].offsetLeft;   
  document.forms[0].NameSearch.value="";
  document.forms[0].NameSearch.focus();
}

function enter(){
var NameSearch = document.forms[0].NameSearch.value;
var FromRecord = 0;
var Type = "N";
var Client = "";
var AppCode = "";
var Bank = "";
var Selection = "";
var tmp = "";
	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			Type = document.forms[0].Type[i].value;
	}
	if (NameSearch.length < 1){
	   if (Type == "A") NameSearch="0";
	   else NameSearch=".";
	}
	if (Type == "N") {
		NameSearch = justifyRight(NameSearch,20);
	}
	//if (Type == "A") { //Account Search
	//	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0005?NameSearch=" + tmp + "@FromRecord=" + FromRecord + "@shrBank=" + Bank + "@shrAppCode=" +  AppCode + "@shrSelect=" + Selection + "@shrClient=" + Client + "@shrAcc=" + NameSearch + "'";
	//} else {
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0001?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
   // }
}

function enterRUT(){
var NameSearch = trim(document.forms[0].NameSearch.value);
    if (DIVSUBMIT.style.display !="") {
    	if (document.forms[0].E01CUSCUN.value !="0") return true;
    	else {
    	   enter(); //call search
    	   return false;
    	}
    }
	if (NameSearch.length > 0){
	    if (document.forms[0].Type[0].checked) document.forms[0].E01CUSCUN.value=NameSearch;
	    else {
	    	document.forms[0].E01CUSIDN.value=NameSearch;
	    	document.forms[0].E01CUSCUN.value="0";
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


<body> 

<h3 align="center">Cliente Actual<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_enter_search, ESD0800"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0800" target="_parent" onsubmit="return(enterRUT());">
  <CENTER>
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="E01CUSCUN" value="<%= currClient.getE01CUSCUN() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUSIDN" value="<%= currClient.getE01CUSIDN() %>">
  <INPUT TYPE=HIDDEN NAME="E01RELBAN" value="<%= currClient.getE01RELBAN() %>">
        <div id=DIVNAME nowrap width="140" style="position:absolute; left:25px; top:-50px;">
          <input type="text" name="NameSearch"  size=25 maxlength="30">
          &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter()" width="25" height="20"> 
        </div>    
  
	   <table  id="TBHELP" align="center" width="360" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap width="80" id="THHELP">Búsqueda por:</td>
		  <td nowrap width="140"><input type="radio" name="Type" value="N" checked onclick="typeClick('N',0)">N&uacute;mero</td>		
		  <td nowrap width="140"></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="I" onclick="typeClick('I',1)">Identificaci&oacute;n</td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="D" onclick="typeClick('D',2)">Identificaci&oacute;n Larga</td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="S" onclick="typeClick('S',2)">Nombre Corto</td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="W" onclick="typeClick('W',3)">Palabra</td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="L" onclick="typeClick('L',4)">Nombre Largo</td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="A" onclick="typeClick('A',5)">Cuenta</td>
		  <td nowrap ></td>
		</tr>   
        <tr valign="middle"> 
      	 <td nowrap colspan="3">
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
	 typeClick('N',0);
     document.forms[0].NameSearch.focus(); 
     function resizeDoc() {
       for(var i = 0; i < document.forms[0].Type.length; i++)
		{
		if (document.forms[0].Type[i].checked)
			document.forms[0].Type[i].click();
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
</BODY>
</HTML>
 