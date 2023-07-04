 
<!DOCTYPE html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<% 
String client = "0";
String cuenta = "Cuenta";

if (userPO.getOption().equals("CD")) { 
    cuenta = "Deposito";
} else if (userPO.getOption().equals("LN")) { 
	cuenta = "Prestamo";
} 

if (currClient != null) {
  client = currClient.getE01CUSCUN();
    //System.out.println("EWD0005 client::::" + currClient.getE01CUSCUN());
  currClient.setE01CUSCUN("0");   
}
%>


<SCRIPT language="JavaScript">

 

function enterAction(code,numc,name,id,ccy,type,prod,card) {
	
	var form = top.opener.document.forms[0];
	if (card =="") {
		form[top.opener.fieldName].value = code; 
		form[top.opener.fieldName].focus(); 
		form[top.opener.fieldName].select(); 
	} else { 
		form[top.opener.fieldName].value = card;  		
	}
  	if (top.opener.fieldDesc != "" && top.opener.fieldDesc != null){ form[top.opener.fieldDesc].value = name; }
  	if (top.opener.fieldAux1 != "" && top.opener.fieldAux1 != null){ form[top.opener.fieldAux1].value = id; }
  	if (top.opener.fieldAux2 != "" && top.opener.fieldAux2 != null){ form[top.opener.fieldAux2].value = ccy; }
  	if (top.opener.fieldAux3 != "" && top.opener.fieldAux3 != null){ form[top.opener.fieldAux3].value = type; }
  	if (top.opener.fieldAux4 != "" && top.opener.fieldAux4 != null){ form[top.opener.fieldAux4].value = prod; }
	top.close();
 }

function typeClick(value, row){
   
  DIVNAME.style.pixelTop=TBHELP.offsetTop + TBHELP.rows[row].cells[2].offsetTop - 3;
  DIVNAME.style.pixelLeft=TBHELP.offsetLeft + TBHELP.rows[row].cells[2].offsetLeft;
  document.forms[0].NameSearch.value="";
  document.forms[0].NameSearch.focus();
}

function justifyRight(c,l) {
	var fc = "";
	for (var i = 0; i < (l - c.length); i++){
	  	fc += "0";
	}
	return (fc + c);
}
 
function enter(firsTime){
 var AppCode = top.top.opener.AppCode;
 var Bank = top.top.opener.Bank;
 var Selection = top.top.opener.Selection;
 var Client = "<%= client %>";
 var NameSearch = document.forms[0].NameSearch.value;
 var FromRecord = 0;
 var Type ="S";
 var Account = "";
	if (NameSearch.length < 1){
	  NameSearch=".";
	}	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			Type = document.forms[0].Type[i].value;
	}
	
	if (top.top.opener.Client != null && firsTime) { //help by client, to CD and Loans
		Client= top.top.opener.Client;
		NameSearch=".";
	} else if (firsTime) {
	   type ="S";
	}
	
	if (Type == "N" && !firsTime) {
		Client = NameSearch;
		NameSearch=".";
	}
	if (Type == "A") { //Account Search
	    Account = NameSearch;
	    NameSearch="";
	} 
	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0005?NameSearch=" + NameSearch + "@FromRecord=" + FromRecord + "@shrBank=" + Bank + "@shrAppCode=" +  AppCode + "@shrSelect=" + Type + "@shrClient=" + Client + "@shrAcc=" + Account + "'";	
 }
</SCRIPT>

</HEAD>
<BODY>
<FORM Action="javascript:enter(document.forms[0].NameSearch.value)">
	
  <div id=DIVNAME style="position:absolute; left:25px; top:-50px;">
     <input type="text" name="NameSearch"  size=25 maxlength="30" >
     &nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/search1.gif" style="cursor : hand;" onClick="enter(false)"> 
  </div>
  
  <CENTER>  
	   <table  id="TBHELP" align="center" width="360" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap width="80">Búsqueda por:</td>
		  <td nowrap width="140">
		    <input type="radio" name="Type" value="N" onclick="typeClick('N',0)" checked>N&uacute;mero Cliente
		  </td>		
		  <td nowrap width="140">
     	  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="I" onclick="typeClick('I',1)">Identificaci&oacute;n Cliente
		  </td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="D" onclick="typeClick('D',2)">Identificaci&oacute;n Larga Cliente
		  </td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="S" onclick="typeClick('S',3)">Nombre Corto
		  </td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap>
		  	<input type="radio" name="Type" value="W" onclick="typeClick('W',4)">Por Palabra
		  </td>
		  <td nowrap ></td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap><input type="radio" name="Type" value="L" onclick="typeClick('L',5)">Nombre Largo</td>
		  <td nowrap ></td>
		</tr> 
	    <tr>
		  <td nowrap></td>
		  <td nowrap><input type="radio" name="Type" value="A" onclick="typeClick('A',6)">Por No. <%=cuenta%></td>
		  <td nowrap>
		  </td>
		</tr>
	 	
 	</table>
 </CENTER>	
	
  <SCRIPT Language="Javascript">;
	 typeClick('N',0);
     function resizeDoc() {
       for(var i = 0; i < document.forms[0].Type.length; i++)
		{
		if (document.forms[0].Type[i].checked)
			document.forms[0].Type[i].click();
		}
     }
     
     window.onresize=resizeDoc;     
     enter(true);
     
</SCRIPT>

</FORM>
</BODY>
</HTML>
 