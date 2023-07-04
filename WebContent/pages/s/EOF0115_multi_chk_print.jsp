<HTML>
<HEAD>
<TITLE>
Lista de Cheques a Imprimir
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "dvList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function enter(accnum , currency) {
   document.forms[0].E01OFMCKN.value = accnum;
   document.forms[0].E01OFMCCY.value = currency;
   document.forms[0].submit();
}

function changeChecked(field){

	for (i = 0; i < field.length; i++)
		field[i].checked = document.forms[0].hcheck.checked ;
}

function submitForm(){

	var anyChecked = false ;
	var checks = document.forms[0].ACCNUM ;
	
	for(i=0 ; i < checks.length ; i++ ){
		if( checks[i].checked == true ){
			anyChecked = true ;
			break ;
		}
	}
	
	if( ! anyChecked ){
		alert("No existe ningun cheque seleccionado.");
		return false ;
	}else{
		document.forms[0].submit();
		return true;
	}
}

<%
String[] checksToPrint = request.getParameterValues("ACCNUM");
String[] params = new String[2];
%>

var checksToPrint = 
	new Array(<% for(int i=0;i<checksToPrint.length;i++){
					out.print("\"" + checksToPrint[i] + "\"");  
					if( i+1 < checksToPrint.length){
						out.print(", " );
					}
				} %>);

var checkIndex = 0 ;

function printNextCheck(){

alert( "To print check " + checkIndex );
	var params = new Array();
	if( checkIndex == checksToPrint.length ){
		alert("Impresión Finalizada");
		return;
	}else{
		params = checksToPrint[ checkIndex ].split("_");
alert( params[0] + ":" + params[1] + ":" + document.getElementById("checkPrint").src );
		document.getElementById("checkPrint").src = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=600&E01OFMCKN=" + params[0] + "&E01OFMCCY=" + params[1] ;
		checkIndex++ ;
	}
	
}

function notifyUser(){

	document.getElementById('btn').style.visibility='visible';
	document.getElementById('label').innerHTML = "<h3>Impresión Finalizada</h3><br>";
}

</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="notifyUser();">

  <h3 align="center"> Impresi&oacute;n de Cheques de Gerencia   <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EOF0115"> 
  </h3>
  <hr size="4">
  
  <BR/><BR/><BR/>

  <DIV align="center" id="label">
	<h3>Imprimiendo Cheques</h3><br>
	<img src="<%=request.getContextPath()%>/images/gears7.gif"> <BR/>
	<h3 align="center" >Por Favor Espere</h3>
  </DIV>
  <DIV align="center" id="btn" style="visibility:hidden">
	<form action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P" >
		<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="100">
		<input id="EIBSBTN" type="submit" name="Submit" value="Regresar"  >
	</form>	
  </DIV>
  
  
  
<%-- 
  <IFRAME id="checkPrint" onload="javascript:printNextCheck();" width="100%" height="100%" >
  </IFRAME>
--%>

<% for(int i=0;i<checksToPrint.length;i++){ 

	params[0] = checksToPrint[i].toString().substring( 0 , checksToPrint[i].toString().indexOf('_') ) ;
	params[1] = checksToPrint[i].toString().substring( checksToPrint[i].toString().indexOf('_') + 1 ) ;

%>
				
<DIV style="display:none" >
  <IFRAME id="checkPrint" width="70%" src="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=600&E01OFMCKN=<%=params[0]%>&E01OFMCCY=<%=params[1]%>" ;  >
  </IFRAME>
  <BR/><BR/>
</DIV>

<%} %>

</BODY>
</HTML>
