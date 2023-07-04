<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<!--meta http-equiv="Refresh" CONTENT="4;url='javascript:goExit()'"-->
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="batch" class="datapro.eibs.beans.ELS010001Message"  scope="session" />


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
  function goExit(){
    window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }

 setTimeout("document.forms[0].submit();", 7000);
 </SCRIPT>
</head>

<body bgcolor="#FFFFFF">

<h3 align="center"><%= batch.getE01DESPRG()%>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_confirm, ELS0100"></h3> 
<hr size="4">
 
  <table class=tbenter width=100% height=50%>
    <tr > 
      <td align=right width=50%> Programa :
      </td>
      <td align=left width=50%> <%= batch.getE01NOMPRG()%>
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
		<INPUT type=TEXT name="E01DATE11" size="3" maxlength="2" value="<%= batch.getE01DATE11().trim()%>" readonly> 
		<INPUT type=TEXT name="E01DATE12" size="3" maxlength="2" value="<%= batch.getE01DATE12().trim()%>" readonly> 
		<INPUT type=TEXT name="E01DATE13" size="3" maxlength="2" value="<%= batch.getE01DATE13().trim()%>" readonly> 
      </td>
    </tr>

	<%}%>

	<%if(!batch.getE01DESDT2().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESDT2()%>
      </td>
      <td> 
		<INPUT type=TEXT name="E01DATE21" size="2" maxlength="2" value="<%= batch.getE01DATE21().trim()%>" readonly> 
		<INPUT type=TEXT name="E01DATE22" size="2" maxlength="2" value="<%= batch.getE01DATE22().trim()%>" readonly> 
		<INPUT type=TEXT name="E01DATE23" size="2" maxlength="2" value="<%= batch.getE01DATE23().trim()%>" readonly> 
      </td>
    </tr>

	<%}%>
	<%if(!batch.getE01DESSE1().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESSE1()%>
      </td>
      <td> 
		<INPUT type="text" name="E01SECUE1" size="10" maxlength="7" onkeypress="enterDecimal()" 
		value="<%= batch.getE01SECUE1().trim()%>" readonly>
      </td>
    </tr>

	<%}%>
	<%if(!batch.getE01DESSE2().equals("")) {%>

    <tr > 
      <td align=right> <%= batch.getE01DESSE2()%>
      </td>
      <td> 
		<INPUT type="text" name="E01SECUE2" size="10" maxlength="7" onkeypress="enterDecimal()" 
		value="<%= batch.getE01SECUE2().trim()%>" readonly>
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
		
		
		<A href="javascript:GetCodeDescCNOFC('E01CODTB1','E01DPPACD','P2')">
		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
        <INPUT type="text" name="E01DPPACD" size="30" maxlength="30" readonly></td>
    </tr>

	<%}%>


	
    <tr > 
      <td colspan=2><h4 style="text-align=center">La informacion  ha sido procesada satisfactoriamente.<br>
      Por favor espere... </h4>
      </td>
    </tr>


  </table>

</body>
</html>
