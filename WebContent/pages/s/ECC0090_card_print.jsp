<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Asignación de Tarjetas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECC009001Message"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
<!--
function cardPrintPreview() {

	var name = document.forms[0].E01CCRNAM.value
	var pg = "<%=request.getContextPath()%>/pages/s/ECC0090_card_print_preview.jsp?NAME=" + name;
	CenterWindow(pg,241,153,2);

}
//-->
</script>

</head>
<body>
<h3 align="center">Asignación de Tarjetas<BR>Impresión<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="card_print.jsp, ECC0090"></h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">

  <h4>Información del Cliente</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Identificación del Cliente :</div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E02CCRCID" size="16" maxlength="15" value="<% if(userPO.getType().equals("T")) { out.println(userPO.getIdentifier()); } else { out.println(userPO.getHeader9()); }%>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Nombre del Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02PRINA1" size="36" maxlength="35" value="<% if (userPO.getType().equals("T")) { out.println(userPO.getCusName()); } else { out.println(userPO.getHeader11()); }%>" readonly>
              </div>
            </td>
          </tr>    
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número de Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02PRICUN" size="10" maxlength="10" value="<% if (userPO.getType().equals("T")) { out.println(userPO.getCusNum()); } else { out.println(userPO.getHeader10()); }%>" readonly>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Cliente :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRTYP" size="5" maxlength="4" value="<% if (userPO.getType().equals("T")) { out.println(userPO.getCusType()); } else { out.println(userPO.getHeader12()); }%>" readonly>
              </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Información para la Impresión</h4>
  <TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark">
						<TD nowrap width="16%">
							<DIV align="right">Número de Tarjeta Actual :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRNUM" size="21" maxlength="20" value="<% if (userPO.getType().equals("T")) { out.println(userPO.getHeader1()); } else { out.println(userPO.getHeader13()); }%>" readonly>
							</DIV>
						</TD>
						<TD nowrap width="16%">
							<DIV align="right">Nombre del Tarjetahabiente :</DIV>
						</TD>
						<TD nowrap width="20%">
							<DIV align="left">
								<INPUT type="text" name="E01CCRNAM" size="30" maxlength="24" value="<% if (userPO.getType().equals("T")) { out.println(userPO.getCusName()); } else { out.println(userPO.getHeader11()); }%>">
							</DIV>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

  <BR>
<div align="center"> 
	   <INPUT type="button" name="EIBSBTN" id="EIBSBTN" value="Imprimir" onClick="javascript:cardPrintPreview();">
  </div>
</form>
</body>
</html>
