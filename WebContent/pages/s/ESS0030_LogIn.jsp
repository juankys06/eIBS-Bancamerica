<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<TITLE>eIBS Log In</TITLE>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>
<BODY onLoad="setLayout()">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0030">
  <INPUT TYPE="hidden" NAME="Language" VALUE="s">
  <INPUT TYPE="hidden" NAME="LOGIN" VALUE="NO">

 
<% 
    if ( !error.getERRNUM().equals("0")  ) {
%> 
	<SCRIPT LANGUAGE="JavaScript">
	  function setLayout() {
			setCenterSize(470,420);
	  }  
	</SCRIPT>

    <table width="100%" border="0" cellspacing="2" cellpadding="2" class="tbenter" align="center">
      <tr>
        <td width="33"><img src="<%=request.getContextPath()%>/images/warning.gif" width="32" height="32"></td>
        <td valign="bottom"> 
          <table class="tbenter" width="100%" border="1" cellspacing="0" cellpadding="2" align="center" bordercolor="#FF0033">
            <tr valign="top"> 
              <td valign="middle">
                <div align="center">
       				<font color="red"><B> <%= error.getERDS01()%> </B></font>
    			</div>
              </td>
            </tr>
          </table>
        </td>
        <td width="33"><img src="<%=request.getContextPath()%>/images/warning.gif" width="32" height="32"></td>
      </tr>
    </table>
    <br>
<% 
    }
    else {
%> 
	<SCRIPT LANGUAGE="JavaScript">
	
	  function setLayout() {
		setCenterSize(310,200);
	  }
	
	</SCRIPT>

<% 
    }
%>

    <TABLE WIDTH="100%"  class="tbenter" BORDER=0>
      <TR>
	       
        <TD ALIGN=right width="40%" valign="middle"> <B>Usuario</B> </TD>
	       
        <TD width="60%"> 
          <INPUT SIZE=15 MAXLENGTH=10 NAME="UserId">
          </TD>
   	 </TR>
	    <TR>
	       
        <TD ALIGN=right width="40%" > <b>Clave</b></TD>
	       
        <TD width="60%"> 
          <INPUT TYPE="password" SIZE=15 MAXLENGTH=10 NAME="Password">
        </TD>
   	  </TR>
     </TABLE>
  
   <BR>
     
    <TABLE class="tbenter" WIDTH="100%" BORDER=0>
      
      <TR> 
        <TD vALIGN=middle> 
          <div align="center"> 
            <input type=image name="imgLogin" src="<%=request.getContextPath()%>/images/s/login.gif" onClick="imgDown('imgLogin', 'login_over.gif')">
          </div>
        </TD>
      </TR>
    </TABLE>

		 
 </FORM>  
<script language="JavaScript">
  document.forms[0].UserId.focus();
  document.forms[0].UserId.select();
</script>
</BODY>
</HTML>
