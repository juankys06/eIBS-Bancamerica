<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
  <META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
  <META http-equiv="Content-Style-Type" content="text/css"> 
  <TITLE>Solicitud Reporte</TITLE>
  <link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
  <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
  <script language="JavaScript">

  function goCancel() {
  document.forms[0].SCREEN.value="100";
  document.forms[0].submit(); 
  }
</script>

<jsp:useBean id="batch" class="datapro.eibs.beans.ELS010001Message"  scope="session" />
<jsp:useBean id= "error"     class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</HEAD>


<body bgcolor="#FFFFFF">

<h3 align="center"><%= batch.getE01DESPRG()%>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="submit_report, ELS0100"></h3> 
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSELS0100" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200"> 
<INPUT TYPE=HIDDEN NAME="COLA" VALUE="<%= batch.getE01COLATR()%>"> 
<INPUT TYPE=HIDDEN NAME="PROG" VALUE="<%= batch.getE01NOMPRG()%>"> 
<INPUT TYPE=HIDDEN NAME="TRAB" VALUE="<%= batch.getE01NOMTRA()%>"> 
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= batch.getE01ACTION()%>"> 
<INPUT TYPE=HIDDEN NAME="DESPRG" VALUE="<%= batch.getE01DESPRG()%>"> 
<INPUT TYPE=HIDDEN NAME="DESDT1" VALUE="<%= batch.getE01DESDT1()%>"> 
<INPUT TYPE=HIDDEN NAME="DESDT2" VALUE="<%= batch.getE01DESDT2()%>"> 
  <table class=tbenter width=100% height=50%>
    <tr > 
      <td align=right > Programa :
      </td>
      <td align=left > <%= batch.getE01NOMPRG()%>
      </td>
    </tr>
    <tr > 
      <td align=right> Cola :
      </td>
      <td> <%= batch.getE01COLATR()%>
      </td>
    </tr>

    <tr > 
      <td align=right> Trabajo :
      </td>
      <td> <%= batch.getE01NOMTRA()%>
      </td>
    </tr>

	<%if(!batch.getE01DESDT1().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESDT1()%>
      </td>
      <td> 
		<INPUT type=TEXT name="E01DATE11" size="3" maxlength="2" value="<%= batch.getE01DATE11().trim()%>" > 
		<INPUT type=TEXT name="E01DATE12" size="3" maxlength="2" value="<%= batch.getE01DATE12().trim()%>" > 
		<INPUT type=TEXT name="E01DATE13" size="3" maxlength="2" value="<%= batch.getE01DATE13().trim()%>" > 
		<a href="javascript:DatePicker(document.forms[0].E01DATE11,document.forms[0].E01DATE12,document.forms[0].E01DATE13)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
      </td>
    </tr>

	<%}%>

	<%if(!batch.getE01DESDT2().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESDT2()%>
      </td>
      <td> 
		<INPUT type=TEXT name="E01DATE21" size="2" maxlength="2" value="<%= batch.getE01DATE21().trim()%>" > 
		<INPUT type=TEXT name="E01DATE22" size="2" maxlength="2" value="<%= batch.getE01DATE22().trim()%>" > 
		<INPUT type=TEXT name="E01DATE23" size="2" maxlength="2" value="<%= batch.getE01DATE23().trim()%>" > 
		<a href="javascript:DatePicker(document.forms[0].E01DATE21,document.forms[0].E01DATE22,document.forms[0].E01DATE23)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
      </td>
    </tr>

	<%}%>

	<%if(!batch.getE01DESSE1().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESSE1()%>
      </td>
      <td> 
		<INPUT type="text" name="E01SECUE1" size="10" maxlength="7" onkeypress="enterDecimal()" 
		value="<%= batch.getE01SECUE1().trim()%>" >
      </td>
    </tr>

	<%}%>
	<%if(!batch.getE01DESSE2().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESSE2()%>
      </td>
      <td> 
		<INPUT type="text" name="E01SECUE2" size="10" maxlength="7" onkeypress="enterDecimal()" 
		value="<%= batch.getE01SECUE2().trim()%>" >
      </td>
    </tr>

	<%}%>
	<%if(!batch.getE01DESTB1().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESTB1()%>
      </td>
      <td> 
		<INPUT type="text" name="E01CODTB1" size="5" maxlength="4" 
		value="<%= batch.getE01CODTB1().trim()%>" readonly>
		
		
		<A href="javascript:GetCodeDescCNOFC('E01CODTB1','E01DPPACD1','<%= batch.getE01CODCN1()%>')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
        <INPUT type="text" name="E01DPPACD1" size="30" maxlength="30" readonly></td>
    </tr>

	<%}%>

	<%if(!batch.getE01DESTB2().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESTB2()%>
      </td>
      <td> 
		<INPUT type="text" name="E01CODTB2" size="5" maxlength="4" 
		value="<%= batch.getE01CODTB2().trim()%>" readonly>
		
		
		<A href="javascript:GetCodeDescCNOFC('E01CODTB2','E01DPPACD2','<%= batch.getE01CODCN2()%>')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
        <INPUT type="text" name="E01DPPACD2" size="30" maxlength="30" readonly></td>
    </tr>

	<%}%>

    <tr > 
      <td colspan=2>
      </td>
    </tr>

    <tr>
		<td nowrap colspan="2" valign="middle" height="55">
		<div align="center">
		<INPUT id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()">

		</div>
		</td>
	</tr>

  </table>
</FORM>
<script language="JavaScript">

function goConfirm() {
	document.forms[0].submit();	  		  
}
 
</script>

</body>
</html>
