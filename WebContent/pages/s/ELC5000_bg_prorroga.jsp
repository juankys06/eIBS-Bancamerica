<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Boleta de Garantia</title>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC500001Message"  scope="session" />
<jsp:useBean id= "bolprga" class= "datapro.eibs.beans.ELC500003Message"  scope="session" />
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
<h3 align="center">Prorroga de Boleta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_prorroga.jsp,ELC5000"></h3>
<hr size="4">

<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">

<input type="hidden" name="SCREEN" value="4">


<table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
 	     <td align=right nowrap>Boleta :</td>
	     <td>
	      <input size="15" type="text" name="E03LCMACC" value="<%= bolgaran.getE01LCMACC().trim() %>" readonly>
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
<h4>Datos Prorroga</h4>
<table class=tableinfo>
  <tr>
   <td>
	<table width="100%" cellspacing="0" cellpadding="0">
    <tr id=trdark>
       <td width=40% align=right>Nueva Fecha de Vcto. :</td>
       <td>
         <INPUT size="3" type="text" name="E03LCMEEM" maxlength="2" value="<%= bolprga.getE03LCMEEM() %>" onkeypress="enterInteger()">
         <INPUT size="3" type="text" name="E03LCMEED" maxlength="2" value="<%= bolprga.getE03LCMEED() %>" onkeypress="enterInteger()">
         <INPUT size="3" type="text" name="E03LCMEEY" maxlength="2" value="<%= bolprga.getE03LCMEEY() %>" onkeypress="enterInteger()">
       </td>
  	<tr id=trclear>
       <td align=right>Comision Flat :</td>
       <td>
	     <INPUT size="16" type="text" name="E03LCMC11" maxlength="15" value="<%= bolprga.getE03LCMC11() %>" onkeypress="enterDecimal()">
	   </td>
    </tr>
    <tr id=trdark>
        <td align=right>Tasa Interes :</td>
        <td>
         <INPUT size="10" type="text" name="E03LCMIRT" maxlength="9" value="<%= bolprga.getE03LCMIRT() %>">
        </td>
	</tr>
	<tr id=trclear>
      <td align=right nowrap>Cobros :</td>
      <td>
          <INPUT type="radio" name="E03PAGFLG" value="1" <% if (bolprga.getE03PAGFLG().equals("1")) out.print("checked");%>>Cuenta Corriente
          <INPUT type="radio" name="E03PAGFLG" value="2" <% if (bolprga.getE03PAGFLG().equals("2")) out.print("checked");%>>Caja
          <INPUT type="radio" name="E03PAGFLG" value="3" <% if (bolprga.getE03PAGFLG().equals("3")) out.print("checked");%>>Cuentas por Cobrar
      </td>
    </tr>
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
