<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 
<TITLE>Abonos Masivos, opción automática</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function CheckFile(){
if ( document.forms[0].FILENAME.value.length < 1) {
  alert("Localización o Nombre de Archivo no Válidos");
  document.forms[0].FILENAME.value='';
  document.forms[0].FILENAME.focus();
}
else {	
  	document.forms[0].SCREEN.value = '200';	
	document.forms[0].submit();
  }
}

</SCRIPT>

</HEAD>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 


<h3 align="center">Abonos Masivos, opción automática<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_transfer_file.jsp, EDL1113"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1113" ENCTYPE="multipart/form-data">	
    <p>
    	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    </p>

  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right">Tipo de Datos :</div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
				<select name="OPT" style="width:220px;">
				<option value="1">Seguro Social Empleados</option>
				<option value="2">Seguro Social Jubilados</option>
				<option value="3">Contraloría General</option>
				<option value="4">Abono General</option>
				</select>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right">Clave de Descuento :</div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="text" readonly name="E01DESCTO" size="8" maxlength="6" value="<%= userPO.getHeader7().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01DESCTO','4D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%">&nbsp;</td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%">&nbsp;</td>
          </tr>


        </table>

		  <table id="tbenter" align="center" style="width:85%" border="1">  
		    <tr> 
		      <td> 
		        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
		
		          	<tr id="trdark">
		     			<td align="center"> 
				            <input type=file name="FILENAME" size="100" maxlength="255" >
		     			</td>        
		    		</tr>
		
			   </table>
		      </td>
		    </tr>
		  </table>



      </td>
    </tr>
  </table>



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
