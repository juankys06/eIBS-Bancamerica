<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1"> 
<TITLE>N/M Exception</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="exMaint" class="datapro.eibs.beans.ELD001001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


</HEAD>

<body bgcolor="#FFFFFF">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

 <% if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %>
 
<SCRIPT LANGUAGE="JavaScript">
function verifyIt() {
 var form = document.forms[0]
  for ( var i=0; i < form.elements.length;  i++ ) {
    if ( form.elements[i].type =="text" && form.elements[i].value == "") {
    alert(" Por favor, llene todos los datos.")
    form.elements[i].focus()
    return false
    break
		}
	}
 return  true	
}

function isMonth(field) {
 var month = parseInt(field.value)
 if ( field.value.length > 0 ) {
  if ( isNaN(month) ) {
   alert(" Por favor, entre solo números.")
   field.focus()
   field.value = ""
  }
else {
   if ( month < 0 || month > 12 ) {
   alert("Por favor, entre solo meses validos ej.:1..12")
   field.focus()
	}
  }
 } 
}

function isYear(field) {
var year = parseInt(field.value)
if ( field.value.length > 0 ) {

  if ( isNaN(year) ) {
   alert(" Por favor, entre solo números.")
   field.focus()
   field.value = ""
  }
else {
   if ( (""+year).length < 4 || year == 0 ) {
   alert("Por favor, entre solo años validos de cuatro dígitos ej.:1950..2005")
   field.focus()
	}
  }
 }
}

</SCRIPT>
<h3 align="center">Nuevo/Mantenimiento de Excepciones <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="exception_enter_maint, ELD0010"></h3>
<hr>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.misc.JSELD0010" onSubmit="return verifyIt()">
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    </p>
    <table cellspacing="0" cellpadding="2" class="tbenter" border=0   width=81% align="center" height="80%">
      <tr valign="middle"> 
        <td nowrap colspan="2" align="middle" height="131">&nbsp;</td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=45% align="right" height="24"> Ingrese el N&uacute;mero 
          de Cuenta : </td>
        <td nowrap width=55% align="left" height="24" > 
          <input type=TEXT name="E01LDEACC" size=15 maxlength=12 value="<%= exMaint.getE01LDEACC().trim()%>" onKeypress="enterInteger()" >
          <a href="javascript:GetAccount('E01LDEACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
        </td>
      </tr>
      <tr valign="middle"> 
        <td nowrap width=45% align="right" height="28" > Mes/ A&ntilde;o de Excepcion 
          : </td>
        <td nowrap width=55% align="left" height="28" > 
          <input type=TEXT name="E01LDEBDM" size=2 maxlength=2 value="<%= exMaint.getE01LDEBDM().trim()%>"  onBlur="isMonth(this)">
          <input type=TEXT name="E01LDEBDY" size=4 maxlength=4 value="<%= exMaint.getE01LDEBDY().trim()%>" onBlur="isYear(this)">
        </td>
      </tr>
      <tr> 
        <td nowrap align="right" valign="middle" height="28" width="45%"> Secuencia 
          : </td>
        <td nowrap width="55%" valign="middle" height="28" align="left"> 
          <input type=TEXT name="E01LDESEQ" size=2 maxlength=2 value="<%= exMaint.getE01LDESEQ().trim()%>" onKeypress="enterInteger()" >
          <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
          ( "0" si es Nueva Secuencia )</td>
      </tr>
      <tr> 
        <td nowrap colspan="2" valign="middle"> 
          <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
        </td>
      </tr>
    </table>
  </CENTER>
 </FORM>
</BODY>
</HTML>
 