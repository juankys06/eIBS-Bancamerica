<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Sistema Bancario: Mantenedor Convenios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "deal" class= "datapro.eibs.beans.ESD079001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
 function enterCode(){
	
	if (trim(document.forms[0].E01COTCDE.value).length > 0) {
	    return true;
	}else{
		alert("Es requerido que se entre un valor");
		document.forms[0].E01COTCDE.focus();
		return false;
	}
 }
</script>

</head>

<body>
 
<H3 align="center">Mantenedor Convenios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="deal_enter.jsp, ESD0790"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0790" onsubmit="return(enterCode());">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0"> 
    <tr>
      <td width="50%"> 
        <div align="right">Número de Convenio : </div>
      </td>
      <td width="50%"> 
        <div align="left"> 
          <input type="text" name="E01COTCDE" size="5" maxlength="4" value="<%= deal.getE01COTCDE().trim()%>">
            <a href="javascript:GetCodeDescDeal('E01COTCDE','E01COTDES')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
          <input type="hidden" name="E01COTDES">
        </div>
      </td>
    </tr>
  </table>
  <p align="center">
    <input type=image class=imgfilter name="Submit" src="<%=request.getContextPath()%>/images/s/bt_enviar.gif" onMouseDown="this.className='' "onMouseUp="this.className='imgfilter' " >
  </p>
<script language="JavaScript">
  document.forms[0].E01COTCDE.focus();
  document.forms[0].E01COTCDE.select();
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
</form>
</body>
</html>
