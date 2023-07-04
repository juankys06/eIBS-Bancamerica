<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="Author" CONTENT="Datapro">
<META NAME="Generator" CONTENT="Microsoft FrontPage 5.0">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<TITLE>Transacciones Históricas</TITLE>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script type="text/javascript">
function GetDCIBSUsers(name)
{
	page = webapp + "/servlet/com.datapro.eibs.internet.JSLogTransSearch?SCREEN=" + 3;
	fieldName=name;
	CenterWindow(page,630,230,1);

}
</script>
</HEAD>

<jsp:useBean id="lsEntity"      class="com.datapro.generic.beanutil.BeanList"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<%
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

%>
<H3 align="center">DCIBS Entity User Inquiry <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_helpdesk_users.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSUserList" >

<INPUT TYPE=HIDDEN NAME="PB" VALUE="2">

<table border=0 style="border-collapse: collapse" cellspacing=0 cellpadding=0 width="550">
  <tr> 
    <td width=50% class="sectheader"></td>
    <TD class="smWhite" align="right"></TD>		
  </tr>
</table> 

<br> 
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="3" width="100%" border="0" class="tbhead">
           <tr id="trdark">
            <td nowrap height="23" width="30%">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Entity ID:</div>
            </td>
            <td nowrap colspan="3" width="70%">
            	<div align="left">
              		<input type="text" id="ENT" name="ENTITYID" size="16" maxlength="15" value="">
              		<a href="javascript:GetDCIBSUsers('ENTITYID')">
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0" ></a>            	            	
              	</div>
            </td>
          </tr>
          
        </table>  
      </td>
    </tr>
 </table>         
<BR>



  <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Submit" >
  </p>


</FORM>
</BODY>
</HTML>