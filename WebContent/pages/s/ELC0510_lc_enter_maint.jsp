<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Mantenimiento Carta de Credito</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="javascript">



</SCRIPT>
</HEAD>
<BODY>

<H3 align="center">Mantenimiento Carta de Credito <IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
    <INPUT TYPE=HIDDEN NAME="E01LCMSWF" VALUE="">
  <TABLE class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">
    <TR>
      <TD nowrap width="50%" align="right">Numero de Carta De Credito : </TD>
      <TD nowrap width="50%">
        <INPUT type="text" name="E01LCMACC" size="13" maxlength="12" onKeypress="enterInteger()">
        <A href="javascript:GetAccount('E01LCMACC','','CC','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></A>
      </TD>
    </TR>
    <TR>
			<TD nowrap align="right">
        Enmienda :</TD><TD align="left">
			<SELECT name="E01LCMAMF">
				<OPTION value=""></OPTION>
				<OPTION value="Y">Si</OPTION>
				<OPTION value="N">No</OPTION>
			</SELECT></TD>

		</TR>
  </table>
  <br>
          <div align="center">
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">

          </div>
<script language="JavaScript">
  document.forms[0].E01LCMACC.focus();
  document.forms[0].E01LCMACC.select();
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
<P><BR>

</BODY>
</HTML>
