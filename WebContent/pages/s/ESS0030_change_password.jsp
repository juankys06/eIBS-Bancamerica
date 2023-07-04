<HTML>
<HEAD>
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<TITLE>eIBS Log In</TITLE>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function validate() {
  var ok = true;
  var err = "";
  if (document.forms[0].LoginPassword_1.value != document.forms[0].LoginPassword_2.value) {
    err = "Clave de acceso: \n\tLa nueva clave y la confirmación de la clave \n\ttienen que ser iguales.\n";
    ok = false;
  }
  if ((document.forms[0].LoginPassword_1.value != '' && document.forms[0].LoginPassword.value == '') || (document.forms[0].LoginPassword_1.value == '' && document.forms[0].LoginPassword.value != '')) {
    err += "Clave de acceso: \n\tTiene que entrar los tres campos.\n";
    ok = false;
  }
  if (document.forms[0].AppPassword_1.value != document.forms[0].AppPassword_2.value) {
    err += "Clave de aprobación: \n\tLa nueva clave y la confirmación de la clave \n\ttienen que ser iguales.\n";
    ok = false;
  }
  if ((document.forms[0].AppPassword_1.value != '' && document.forms[0].AppPassword.value == '') || (document.forms[0].AppPassword_1.value == '' && document.forms[0].AppPassword.value != '')) {
    err += "Clave de aprobación: \n\tTiene que entrar los tres campos.\n";
    ok = false;
  }
  if ( ok == true) {
    document.forms[0].submit();   
  }
  else {
    alert(err);
  }

}
</SCRIPT>
</HEAD>
<BODY>
<H3 align="center">Cambio de Clave<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="change_password, ESS0030"></H3>
<hr size="4">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0030">
  <INPUT TYPE="hidden" NAME="SCREEN" VALUE="6">
  <CENTER>
  <TABLE CLASS="tbenter" WIDTH=98% HEIGHT=85% BORDER=0>
    <TR>
    <TD HEIGHT="45%">
    <h4>Clave de Acceso al eIBS</h4>
    <TABLE class="tableinfo"0>
	   <TR  id="trdark">    
              <TD ALIGN=right width="50%" > Clave : </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="LoginPassword">
        </TD>
   	  </TR>
	   <TR  id="trclear">    
        <TD ALIGN=right width="50%" > 
           Nueva Clave : 
        </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="LoginPassword_1">
        </TD>
   	  </TR>
	    <TR id="trdark">
        <TD ALIGN=right width="50%" > 
            Confirmación de Clave : 
        </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="LoginPassword_2">
        </TD>
   	  </TR>
     </TABLE>
    </TD>
    </TR>
    <TR>
    <TD HEIGHT="45%">
    <h4>Clave de Aprobación</h4>
    <TABLE class="tableinfo">
	   <TR  id="trdark">    
              <TD ALIGN=right width="50%" > Clave : </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=4 NAME="AppPassword">
        </TD>
   	 </TR>
	   <TR  id="trclear">    
        <TD ALIGN=right width="50%" > 
            Nueva Clave : 
        </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=4 NAME="AppPassword_1">
        </TD>
   	 </TR>
	    <TR id="trdark">
        <TD ALIGN=right width="50%" > 
             Confirmación de Clave : 
        </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=4 NAME="AppPassword_2">
        </TD>
   	  </TR>
     </TABLE>
    </TD>
    </TR>
    <TR> 
        <TD HEIGHT="71"> 
          <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" OnClick="validate()" value="Enviar">
</p>
     
      </TD>
    </TR>
   </TABLE>
   </CENTER>  
   <BR>
     
		 
 </FORM>  
<script language="JavaScript">
  document.forms[0].LoginPassword.focus();
  document.forms[0].LoginPassword.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>;
 <%
 }
%>
</BODY>
</HTML>
