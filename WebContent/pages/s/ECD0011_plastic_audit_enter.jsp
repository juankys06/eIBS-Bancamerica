<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Auditoría</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "msgEnter" class= "datapro.eibs.beans.ECD0011DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">

function typeClick(value){
	var divOthers = document.getElementById("others");
	var divByUser = document.getElementById("byUser");
	var tbHelp = document.getElementById("TBHELP");
	
	document.forms[0].E01TDDCED.value="";
	document.forms[0].E01TDDCUN.value="";
	document.forms[0].E01TDDACC.value="";
	document.forms[0].E01TDDTDD.value="";
	document.forms[0].E01TDDUSR.value="";

	if (value == "U"){
		document.forms[0].SCREEN.value = "11";
		divOthers.style.display="none";
		divByUser.style.display="";
		document.forms[0].E01TDDUSR.focus();
	} else{
		document.forms[0].SCREEN.value = "10";
		divByUser.style.display="none";
		divOthers.style.display="";
		document.forms[0].E01TDDCED.focus();
	}
}

function validate() {
	if (document.forms[0].Type[0].checked) {
		if (document.forms[0].E01TDDCED.value == "" &&
			document.forms[0].E01TDDCUN.value == "" &&
			document.forms[0].E01TDDACC.value == "" &&
			document.forms[0].E01TDDTDD.value == "") {
			alert("Por favor, coloque al menos un criterio de búsqueda.");
			return false;
		}
		else if (document.forms[0].E01TDDDAD.value == "" ||
				document.forms[0].E01TDDMOD.value == "" ||
				document.forms[0].E01TDDYED.value == "" ||
				document.forms[0].E01TDDDAH.value == "" ||
				document.forms[0].E01TDDMOH.value == "" ||
				document.forms[0].E01TDDYEH.value == "") {
			alert("Por favor, introduzca el intervalo de fechas completo.");
			return false;
		}
		else {
			return true;
		}
	}
	else {
		if (document.forms[0].E01TDDUSR.value == "") {
			alert("Por favor, introduzca el usuario.");
			return false;
		}
		else if (document.forms[0].E01TDDDAD.value == "" ||
				document.forms[0].E01TDDMOD.value == "" ||
				document.forms[0].E01TDDYED.value == "" ||
				document.forms[0].E01TDDDAH.value == "" ||
				document.forms[0].E01TDDMOH.value == "" ||
				document.forms[0].E01TDDYEH.value == "") {
			alert("Por favor, introduzca el intervalo de fechas completo.");
			return false;
		}
		else {
			return true;
		}
	}
}
</script>

</head>
<body>
<h3 align="center">Consulta de Auditoría
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_audit_enter.jsp, ECD0011"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0011" onSubmit="return validate();">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="10">

	   <table  id="TBHELP" align="center" width="370" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td nowrap width="90">Búsqueda por:</td>
		  <td nowrap>
		  	<input type="radio" name="Type" id="Type" value="O" onclick="typeClick('O')" checked>Otros Criterios
		  </td>
		  <td nowrap width="140">
     	  </td>
		</tr>
		<tr>
		  <td nowrap ></td>
		  <td nowrap width="140">
		    <input type="radio" name="Type" id="Type" value="U" onclick="typeClick('U')">Usuario
		  </td>		
		  <td nowrap ></td>
		</tr>
 	</table>
  <br>
  
  <table class="tableinfo" align="center">
    <tr > 
      <td nowrap> 
		<div id="others" align="center"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TDDCED" size="17" maxlength="16" value="<%= msgEnter.getE01TDDCED() %>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TDDCUN" size="17" maxlength="16" value="<%= msgEnter.getE01TDDCUN() %>">
				<a href="javascript:GetCustomer('E01TDDCUN')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
			  </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cuenta :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TDDACC" size="17" maxlength="16" value="<%= msgEnter.getE01TDDACC() %>">
				<a href="javascript:GetAccount('E01TDDACC','','','')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Tarjeta :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TDDTDD" size="17" maxlength="16" value="<%= msgEnter.getE01TDDTDD() %>">
				<a href="javascript:GetPlastic2('E01TDDTDD', '', '')"></a>
			  </div>
            </td>
          </tr>
         </table>
   		</div> 
		<div id="byUser" align="center"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Usuario :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01TDDUSR" size="12" maxlength="10" value="<%= msgEnter.getE01TDDUSR() %>">
              </div>
            </td>
          </tr>
         </table>
		</div> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Desde :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                    <input type="text" size="2" maxlength="2" name="E01TDDDAD" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="E01TDDMOD" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="E01TDDYED" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].E01TDDDAD, document.forms[0].E01TDDMOD, document.forms[0].E01TDDYED)">
                    <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Hasta :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                    <input type="text" size="2" maxlength="2" name="E01TDDDAH" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="E01TDDMOH" onKeyPress="enterInteger()">
                    <input type="text" size="2" maxlength="2" name="E01TDDYEH" onKeyPress="enterInteger()">
                    <a href="javascript:DatePicker(document.forms[0].E01TDDDAH, document.forms[0].E01TDDMOH, document.forms[0].E01TDDYEH)">
                    <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
			  </div>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  
  <BR>
<div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
  
<script language="JavaScript">

	typeClick('O');
</script>
  
</body>
</html>
