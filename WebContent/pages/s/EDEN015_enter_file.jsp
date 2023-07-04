<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Importar FED</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function CheckFile(){
if ( document.forms[0].FILENAME.value.length < 1) {
  alert("A valid file name must be entered");
  document.forms[0].FILENAME.value='';
  document.forms[0].FILENAME.focus();
}
else {
  document.forms[0].SCREEN.value = '200';	
  document.forms[0].submit();
  }
}

</SCRIPT>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

</HEAD>

<body>

 <h3 align="center">Proceso de Carga del Archivo FINCEN<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_file.jsp, EDEN015" width="32" height="32" ></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDEN015A" ENCTYPE="multipart/form-data">	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0"> 
 
        <tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right"></div>
	      </td>
		  <td nowrap width="60%" >
		  </td>
		</tr>  
		 <tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Tipo de Archivo :</div>
	      </td>
		  <td nowrap width="60%" >
              <input type="radio" name="E01TYPE" value="B" checked>Maestro 
              de Negocio<input type="radio" name="E01TYPE" value="P">Maestro
              de Personas<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
		  </td>
		</tr>  
        <tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right"></div>
	      </td>
		  <td nowrap width="60%" >
		  </td>
		</tr>  		  
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Nombre del Archivo :</div>
	      </td>
		  <td nowrap width="60%" >
		  	<input type=file name="FILENAME" size="50" maxlength="255" >
		  	 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  > 
		  </td>
		</tr>
        <tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right"></div>
	      </td>
		  <td nowrap width="60%" >
		  </td>
		</tr>  
	  </table>
    </td>
   </tr>
  </table>
  <br>
	<p align="center"> 
	  <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="CheckFile()"> 
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
 