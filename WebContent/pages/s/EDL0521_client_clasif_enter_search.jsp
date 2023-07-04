<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Calificación</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "clasif" class= "datapro.eibs.beans.EDL052101Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="JavaScript" id="eAction">
function enterAction(code,desc,id) {
 var form = document.forms[0];

 form["E01CUSCUN"].value = code;
 form["E01CUSIDN"].value = id;
  
 form.submit();
}
</script>
 
<script language="JavaScript">
function typeClick(value){
  if (value == "I" || value == "N"){    
   
   DIVSUBMIT.style.display="";   
  }
  else{
   
   DIVSUBMIT.style.display="none"; 
  }
  document.forms[0].NameSearch.focus();
}

function enter(){
var NameSearch = document.forms[0].NameSearch.value
var FromRecord = 0
	if (NameSearch.length > 0){
		for(var i = 0; i < document.forms[0].Type.length; i++) {
			if (document.forms[0].Type[i].checked){
			var Type = document.forms[0].Type[i].value
			}
 		}

    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0001?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
	}else{
	alert("Es requerido que se entre un valor");
	document.forms[0].NameSearch.focus();
	}
}

function enterRUT(){
var NameSearch = document.forms[0].NameSearch.value
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


<body bgcolor="#FFFFFF">

<h3 align="center">Calificación de Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_clasif_enter, EDL00521"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0521" target="_parent" onsubmit="return(enterRUT());">
  <CENTER>
  <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200"></p>
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
		  <td nowrap><input type="radio" name="Type" value="D" onclick="typeClick('D')">Identificaci&oacute;n Larga</td>
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
        <input type="text" name="NameSearch"  size=25 maxlength="30">
        &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter()" width="25" height="20"> 
       </div>
       <div id=DIVRUT>
         <input type="hidden" name="E01CUSCUN" value="<%= clasif.getE01CUSCUN() %>" size=15 maxlength=9 onKeypress="enterInteger()">
		 <input type="hidden" name="E01CUSIDN" value="<%= clasif.getE01CUSIDN() %>" size=15 maxlength=9 onKeypress="enterInteger()">
       </div>
     </td>
   </tr>
   <tr valign="middle"> 
      <td nowrap colspan="2" align="middle" >
      	  <br>
          <div id=DIVSUBMIT align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
      </td>
    </tr>
      
 </table>
 </CENTER>
</FORM>
<SCRIPT Language="Javascript">;
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
 