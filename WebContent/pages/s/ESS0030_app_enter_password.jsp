<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK HREF="<%=request.getContextPath()%>/pages/style.css" REL="stylesheet">
<TITLE>eIBS Log In</TITLE>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>
<BODY>
<H3 align="center">Verificación de Autoridad<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="app_enter_password, ESS0030"></H3>
<hr size="4">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.menu.JSESS0030">
  <input type="HIDDEN" name="SCREEN" value="8">
  <br>
  <CENTER>
    <table width="100%" height="20%" border="0" cellspacing="2" cellpadding="2" class="tbenter" align="center">
      <tr>
<% 
	if ( !error.getERRNUM().equals("0")  ) {
%> 
        <td valign="bottom"> 
          <table class="tbenter" width="100%" border="1" cellspacing="0" cellpadding="2" align="center">
            <tr valign="top"> 
			  <td>
				<% 
				if (!error.getERDF01().equals("")) {
					if (error.getERWF01().equals("Y")) {
				%>
				<IMG src="<%=request.getContextPath()%>/images/warning01.gif" alt="Warning!!!">
				<% 	} else { 
				%>
				<IMG src="<%=request.getContextPath()%>/images/error01.gif" alt="Error!!!">
				<% 	} 
				}
				%>
			  </td>
  			<td width=30><a href="javascript:setFocus('password', <%= error.getERDR01().trim() %>)"><b><%= error.getERNU01() %></b></a></td>
  			<td align="center"><a href="javascript:setFocus('password', <%= error.getERDR01().trim() %>)"><%= error.getERDS01() %></a></td>
            </tr>
          </table>
        </td>
<% 
	}
%> 
      </tr>
    </table>
    <br>
    <TABLE height="30%" WIDTH="100%"  class="tbenter" BORDER=0>
      <TR>  
	 <TD ALIGN=right width="50%" > 
        		Entre su Clave : 
        </TD>
        <TD width="50%"> 
          <INPUT TYPE="password" SIZE="13" MAXLENGTH="13" NAME="AppPassword">
        </TD>
   	</TR>
     </TABLE>
   </CENTER>  
   <BR>
   <CENTER>  
     
    <TABLE class="tbenter" height="20%" WIDTH="100%" BORDER=0>
      <TR> 
        <TD ALIGN=middle> 
          <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
        </TD>
      </TR>
    </TABLE>
    </CENTER>
		 
 </FORM>  
<script language="JavaScript">
  document.forms[0].AppPassword.focus();
  document.forms[0].AppPassword.select();
</script>
</BODY>
</HTML>
