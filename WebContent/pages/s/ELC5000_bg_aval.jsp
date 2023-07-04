<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Boleta de Garantia</title>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC500001Message"  scope="session" />
<jsp:useBean id= "msgAval" class= "datapro.eibs.beans.ELC500002Message"  scope="session" />
<jsp:useBean id= "bolaval" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>
<BODY>
<%
 		int row;
        int seq;
        boolean maint = ( userPO.getHeader20().equals("DO_MAINT")) ? true : false; //Maintenance
		try {
		  	row = Integer.parseInt(request.getParameter("ROW"));
		  	//maint = true;
		}
		catch (Exception e) {
			row = 0;
			//maint = false;
		}
		try {
		  	seq = Integer.parseInt(request.getParameter("seq"));
		}
		catch (Exception e) {
			seq = 0;
		}

		if (maint) {
		 	bolaval.setCurrentRow(row);
		 	msgAval = (datapro.eibs.beans.ELC500002Message) bolaval.getRecord();
		 	seq = Integer.parseInt(msgAval.getE02CUMMAN());
		}

		 if ( !error.getERRNUM().equals("0")  ) {
		     error.setERRNUM("0");
		     out.println("<SCRIPT Language=\"Javascript\">");
		     out.println("       showErrors()");
		     out.println("</SCRIPT>");
		 }


%>
<h3 align="center">Ingreso de Avales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_aval.jsp,ELC5000"></h3>
<hr size="4">

<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">

<input type="hidden" name="SCREEN" value="6">
<input type=hidden name="ROW" value="<%= row %>">
<input type=hidden name="E02CUMMAN" value="<%= seq %>">
<input type=hidden name="E02LCMACC" value="<%= bolgaran.getE01LCMACC().trim() %>">

<table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
 	     <td align=right nowrap>Boleta :</td>
	     <td>
	      <input size="15" type="text" name="E01LCMACC" value="<%= bolgaran.getE01LCMACC().trim() %>" readonly>
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
	        <input size="3" type="text" name="E01LCMIDM" maxlength="2" value="<%=bolgaran.getE01LCMIDM()%>">
			<INPUT size="3" type="text" name="E01LCMIDD" maxlength="2" value="<%=bolgaran.getE01LCMIDD()%>">
			<INPUT size="5" type="text" name="E01LCMIDY" maxlength="4" value="<%=bolgaran.getE01LCMIDY()%>">
		  </td>
	      <td align=right nowrap>Fecha Vcto. :</td>
          <td>
            <input size="3" type="text" name="E01LCMEXM" maxlength="2" value="<%=bolgaran.getE01LCMEXM()%>">
            <INPUT size="3" type="text" name="E01LCMEXD" maxlength="2" value="<%=bolgaran.getE01LCMEXD()%>">
            <INPUT size="5" type="text" name="E01LCMEXY" maxlength="4" value="<%=bolgaran.getE01LCMEXY()%>">
          </td>
    </tr>
  </table>
  </td>
  </tr>
</table>
<h4>Datos Aval</h4>
<table class=tableinfo>
  <tr>
   <td>
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr id=trdark>
       <td width=40% align=right>Numero Cliente :</td>
       <td>
       		<INPUT size="10" type="text" name="E02CUMRCN" maxlength="9" value="<%= msgAval.getE02CUMRCN() %>">
            <a href="javascript:GetCustomerDescId('E02CUMRCN','E02CUMMA1','E02CUMBNI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
       </td>
    <tr id=trdark>
       <td width=40% align=right>Rut :</td>
       <td>
         	<INPUT size="15" type="text" name="E02CUMBNI" maxlength="15" value="<%= msgAval.getE02CUMBNI() %>" readonly>
        </td>
  	<tr id=trclear>
        <td align=right>Nombre :</td>
        <td>
          <INPUT size="40" type="text" name="E02CUMMA1" maxlength="35" value="<%= msgAval.getE02CUMMA1() %>" readonly>
         </td>
	</tr>
	<tr id=trdark>
       <td align=right>% que Avala :</td>
       <td>
	      <INPUT size="10" type="text" name="E02CUMNST" maxlength="10" value="<%= msgAval.getE02CUMNST() %>">
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
