<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
  <META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
  <META http-equiv="Content-Style-Type" content="text/css"> 
  <TITLE>Lista de Accesos</TITLE>
  <link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
  <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
  <script language="JavaScript">

  function goCancel() {
  document.forms[0].SCREEN.value="100";
  document.forms[0].submit(); 
  }
</script>

<jsp:useBean id= "optList746"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error"     class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center">Lista Acceso<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ListAccess_filter,EDP0713"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0713" >
  <CENTER>
    <p><INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="150">
        <input type=HIDDEN name="USR" value=""> 
    </p>
    
    <table cellspacing="0" cellpadding="2" class="tbenter"  border=0  width=70% align="center" >
      
      <tr valign="middle"> 
        <td nowrap width=20% align="right" > 
          Usuario/Grupo : 
        </td>
        <td nowrap width=40% align="left">  
            <select name="E01DPWUID">
             	<%
                optList746.initRow();
                int t=1;
                while (optList746.getNextRow()) {
                    if (optList746.getFlag().equals("")) {
                    		out.println(optList746.getRecord());
                    t++;
                    }        
                }                 
    			%> 
            </select>
        </td>
      </tr>
      
      
      <tr>
		<td nowrap colspan="2" valign="middle" height="146">
		  <div align="center">
		   <INPUT id="EIBSBTN" type="button" name="Submit"
			value="Enviar" onclick="goConfirm()">
		 </div>
		</td>
	</tr>
      <tr>
		<td nowrap colspan="2" valign="middle" height="41">&nbsp;</td>
	</tr>
            	  
    </table>
  </CENTER>
</FORM>
<script language="JavaScript">

function goConfirm() {
	//alert(document.forms[0].SCREEN.value);
	document.forms[0].USR.value=document.forms[0].E01DPWUID.value; 
	document.forms[0].submit();	  		  
}

</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</BODY>
</HTML>
 