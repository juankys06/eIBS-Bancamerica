 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Negociaciones de Cartas de Creditos</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msg01" class= "datapro.eibs.beans.ELC056001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } 
%>

</HEAD>

<BODY>

<H3 align="center">Confirmacion de Pago/Negociación de Cartas de Crédito<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0560" >
<INPUT type="hidden" name="SCREEN" value="8">

<TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
  <TR id="trdark">
    <TD>&nbsp;</TD>
    <TD align="center"><B>Monto</B></TD>
    <TD align="center"><B>Equivalentes</B></TD>
    </TR>
  
	  <TR >
	    <TD align="left">Principal</TD>
	    <TD align="center"><INPUT name="E01DRWAMN" type="text" value="<%=msg01.getE01DRWAMN()%>" size="20" maxlength="15" readonly></TD>
	    <TD align="center"><INPUT name="E01EQVDRW" type="text" value="<%=msg01.getE01EQVDRW()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
	  	  <TR>
	    <TD align="left">Comissiones Aplicante</TD>
	    <TD align="center"><INPUT name="E01COMAPP" type="text" value="<%=msg01.getE01COMAPP()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E01COMBNF" type="text" value="<%=msg01.getE01COMBNF()%>" size="15" maxlength="13" readonly></TD>
	  	  </TR>
	  <TR>
	    <TD align="left">Comisisiones Beneficiario</TD>
	    <TD align="center"><INPUT name="E01COMBNF" type="text" value="<%=msg01.getE01COMBNF()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E01EQVBNF" type="text" value="<%=msg01.getE01EQVBNF()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
	  <TR>
	    <TD align="left">Impuestos Aplicante</TD>
	    <TD align="center"><INPUT name="E01IVAAPP" type="text" value="<%=msg01.getE01IVAAPP()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E01IVABNF" type="text" value="<%=msg01.getE01IVABNF()%>" size="15" maxlength="13" readonly></TD>
	  </TR>
<TR>
	    <TD align="left">Producto Neto</TD>
	    <TD align="center"><INPUT name="E01DRFTBN" type="text" value="<%=msg01.getE01DRFTBN()%>" size="20" maxlength="13" readonly></TD>
	    <TD align="center"><INPUT name="E01EQVDRF" type="text" value="<%=msg01.getE01EQVDRF()%>" size="15" maxlength="13" readonly></TD>
</TR>
</TABLE>
<INPUT type="hidden" name="H01FLGWK1" value="">
<P align="center">  <INPUT id="EIBSBTN" type="submit" name="Submit0" value="Finalizar" onClick="document.forms[0].H02FLGWK1.value='Y'">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
  <INPUT type="submit" name="Voluer" value="Back">
</P>
</FORM>
<%	if (!userPO.getHeader20().equals("")) {%>

<TABLE border="1">
	<TBODY>
		<TR>
			<TD>
			<%
			String s = userPO.getHeader18();
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));
			
			}
			%>
			</TD>
			<TD>
			<%
			s = userPO.getHeader19();
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));
			
			}
			%>
			</TD>
			<TD>
			<%
			s = userPO.getHeader20();
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == ':')	out.print("<BR>");
				else if (s.charAt(i) == '<') out.print("{");
					else if (s.charAt(i) == '>') out.print("}");
				else 					out.print(s.charAt(i));
			
			}
			%>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<%}%>

</BODY>
</HTML>
