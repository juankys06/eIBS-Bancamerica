<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<TITLE>eIBS Log In</TITLE>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">

  function setLayout() {
		setCenterSize(470,320);
  }

  function validate() {

  	imgDown('imgLogin', 'login_over.gif');
  	if (document.forms[0].NewPassword_1.value == document.forms[0].NewPassword_2.value) {
    	document.forms[0].submit();
  	}
  	else {
    	alert("La nueva clave y la confirmación de la clave \ntienen que ser iguales");
  	}

  }

</SCRIPT>

</HEAD>
<BODY onLoad="setLayout()">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0030">
  <INPUT TYPE="hidden" NAME="SCREEN" VALUE="4">
 <br>
  <CENTER>
    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="tbenter" align="center">
      <tr>
        <td width="33"><img src="<%=request.getContextPath()%>/images/warning.gif" width="32" height="32"></td>
        <td valign="bottom"> 
          <table class="tbenter" width="100%" border="1" cellspacing="0" cellpadding="2" align="center" >
            <tr valign="top"> 
              <td valign="middle">
                <div align="center">
                         <% 
                             if ( !error.getERRNUM().equals("0")  ) {
                          %> 
       									<font color="red"><B> <%= error.getERDS01().trim() %> </B></font>
                         <% 
                          	  }
                          %> 
    				 </div>
              </td>
            </tr>
          </table>
        </td>
        <td width="33"><img src="<%=request.getContextPath()%>/images/warning.gif" width="32" height="32"></td>
      </tr>
    </table>
    <br>
    <TABLE WIDTH="100%"  class="tbenter" BORDER=0>
	<TR>       
        <TD ALIGN=right width="50%" > <b>Nueva Clave</b></TD>
	       
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="NewPassword_1">
        </TD>
   	</TR>
	<TR>
	       
        <TD ALIGN=right width="50%" > <b>Confirmación de Clave</b></TD>
	       
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="NewPassword_2">
        </TD>
   	  </TR>
     </TABLE>
   </CENTER>  
   <BR>
   <CENTER>  
     
    <TABLE class="tbenter" WIDTH="100%" BORDER=0>
      
      <TR> 
        <TD ALIGN=middle> 
          <div align="center"> 
            <input type=image name="imgLogin" src="<%=request.getContextPath()%>/images/s/login.gif" onClick="validate()"> 
          </div>
        </TD>
      </TR>
    </TABLE>
    </CENTER>
		 
 </FORM>  
<script language="JavaScript">
  document.forms[0].NewPassword_1.focus();
  document.forms[0].NewPassword_1.select();
</script>
</BODY>
</HTML>
