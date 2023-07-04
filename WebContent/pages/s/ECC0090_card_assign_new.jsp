<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Tarjetas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "cardList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body>
<h3 align="center">Asignación de Tarjetas<br>Nueva Asignación de Tarjeta Titular<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="card_assign_new.jsp, ECC0090"> 
</h3>
<hr size="4">
<% 
if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
}
 
	cardList.initRow();
	cardList.getNextRow();
	datapro.eibs.beans.ECC009001Message msgPart0 = (datapro.eibs.beans.ECC009001Message) cardList.getRecord();
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= msgPart0.getE01TARTYP().trim()%>">
  <input type=HIDDEN name="E01CCRCID" value="<%= msgPart0.getE01CCRCID().trim()%>">
  <input type=HIDDEN name="E01PRINA1" value="<%= msgPart0.getE01PRINA1().trim()%>">
  <input type=HIDDEN name="E01PRICUN" value="<%= msgPart0.getE01PRICUN().trim()%>">
  <input type=HIDDEN name="E01CCRTYP" value="<%= msgPart0.getE01CCRTYP().trim()%>">

  
<TABLE class="tableinfo" align="center">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD nowrap width="16%">
						<DIV align="right">Número de Tarjeta :</DIV>
						</TD>
						<TD nowrap width="20%" colspan="3">
							<DIV align="left">
								<INPUT type="text" name="E01CCRNUM" size="21" maxlength="20" value="">
								<A href="javascript:GetPlastic2('E01CCRNUM','D','N','<%= currUser.getH01USR() %>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></A>
							</DIV>
						</TD>
					</TR>
					<TR id="trclear">
						<TD nowrap width="16%" colspan="4" align="center">
							<INPUT id="EIBSBTN" type="submit" name="Submit" value="Asignar">
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

  </form>
</body>
</html>
