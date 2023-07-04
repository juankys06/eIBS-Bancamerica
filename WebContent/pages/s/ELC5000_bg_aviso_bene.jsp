<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Boleta de Garantia</title>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC500001Message"  scope="session" />
<jsp:useBean id= "bolprga" class= "datapro.eibs.beans.ELC500004Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
   builtNewMenu(bg_m_opt);
   initMenu();
</SCRIPT>

</head>
<BODY>
<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>
<h3 align="center">Aviso Pago Beneficiario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_aviso_bene.jsp,ELC5000"></h3>
<hr size="4">

<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">

<input type="hidden" name="SCREEN" value="21">


<table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
 	     <td align=right nowrap>Boleta :</td>
	     <td>
	      <input size="15" type="text" name="E04LCMACC" value="<%= bolgaran.getE01LCMACC().trim() %>" readonly>
	     </td>
	     <td align=right nowrap>Sucursal :</td>
         <td><input size="5" type="text" name="E01LCMBRN" maxlength="3" value="<%=bolgaran.getE01LCMBRN()%>"></td>

	</tr>
    <tr id=trclear>
         <td align=right nowrap>Producto :</td>
         <td colspan= 3>
         	<input size="5" type="text" name="E01LCMPRO" readonly value="<%= bolgaran.getE01LCMPRO()%>">
            <input size="35" type="text" name="E01PRDNME" readonly value="<%= bolgaran.getE01PRDNME()%>">
         </td>
    </tr>
    <tr id=trdark>
	      <td align=right nowrap>Fecha Emisión :</td>
	      <td>
	        <input size="3" type="text" name="E01LCMIDM" maxlength="2" value="<%=bolgaran.getE01LCMIDM()%>" readonly>
			<INPUT size="3" type="text" name="E01LCMIDD" maxlength="2" value="<%=bolgaran.getE01LCMIDD()%>" readonly>
			<INPUT size="3" type="text" name="E01LCMIDY" maxlength="2" value="<%=bolgaran.getE01LCMIDY()%>" readonly>
		  </td>
	      <td align=right nowrap>Fecha Vcto. :</td>
          <td>
            <input size="3" type="text" name="E01LCMEXM" maxlength="2" value="<%=bolgaran.getE01LCMEXM()%>" readonly>
            <INPUT size="3" type="text" name="E01LCMEXD" maxlength="2" value="<%=bolgaran.getE01LCMEXD()%>" readonly>
            <INPUT size="3" type="text" name="E01LCMEXY" maxlength="2" value="<%=bolgaran.getE01LCMEXY()%>" readonly>
          </td>
    </tr>
  </table>
  </td>
  </tr>
</table>
<h4>Datos Beneficiario</h4>
<table class=tableinfo>
  <tr>
   <td>
	<table width="100%" cellspacing="0" cellpadding="0">
    <tr id=trdark>
       <td width=40% align=right> Fecha de Aviso. :</td>
       <td>
         <INPUT size="3" type="text" name="E04LCMCNM" maxlength="2" value="<%= bolprga.getE04LCMCNM() %>" onkeypress="enterInteger()">
         <INPUT size="3" type="text" name="E04LCMCND" maxlength="2" value="<%= bolprga.getE04LCMCND() %>" onkeypress="enterInteger()">
         <INPUT size="3" type="text" name="E04LCMCNY" maxlength="2" value="<%= bolprga.getE04LCMCNY() %>" onkeypress="enterInteger()">
       </td>
   </table>
  </td>
  </tr>
</table>
<P align="center">
	<INPUT type="submit" value="enviar" id="EIBSBTN">
</P>

</FORM>
</BODY>
</html>
