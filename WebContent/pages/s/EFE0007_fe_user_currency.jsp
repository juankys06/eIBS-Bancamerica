<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>FX Tolerance by User / Currency</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="Dtl" class="datapro.eibs.beans.EFE000702Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
 
<body>

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
function goAction(op) {
	document.forms[0].SCREEN.value = op;
	if (op == 4) {
		if (!confirm("Desea borrar este registro?")) {
			return;
		}
	}
	document.forms[0].submit();
}
</SCRIPT> 

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
	String read = "";
 	String disabled = "";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))) 
		{ read = " readonly ";
		  disabled = " disabled"; }	
%>


<H3 align="center">Mantenimiento de Tolerancia de Tasas de Cambio<br>por Usuario -  Moneda
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_user_currency.jsp, EFE0007"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0007" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">

  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        <tr id="trdark"> 
            <td nowrap width="10%" align="right">Usuario : </td>
            <td nowrap width="15%" align="left"> 
                <INPUT type="text" name="E01FEUUSR" size="11"  maxlength="10" <%= read %> value="<%= Dtl.getE01FEUUSR().trim()%>"
				<% if (!(userPO.getPurpose().equals("NEW"))) out.print(" readonly"); %>>
				<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
				<A href="javascript:GetUser('E01FEUUSR','E01USRDSC',document.forms[0].E01FEUUSR.value)">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></A>
			</td>
            <td nowrap width="10%" align="right">Nombre :</td>
			<td nowrap width="40%" align="left">
				<INPUT type="text" name="E01USRDSC" maxlength="30" size="31" <%= read %> value="<%= Dtl.getD01USRDSC().trim()%>">
			</td>
			<td nowrap width="25%">Fecha de Actualización : <%= datapro.eibs.master.Util.formatDate(Dtl.getE01FEUUPT())%>
            </td>
		</tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Información Básica</h4>  
  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Moneda : </td>
            <td nowrap align="left" width="35%">
                <INPUT type="text" name="E01FEUCCY" size="4" maxlength="3" <%= read %> value="<%= Dtl.getE01FEUCCY().trim()%>" 
                <% if (!(userPO.getPurpose().equals("NEW"))) out.print(" readonly"); %>>
            	<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom"></B>
            	<A href="javascript:GetCurrency('E01FEUCCY','D01CCYDSC')">
            	<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></A> 
            	<br>
              	<INPUT type="text" name="D01CCYDSC" size="31" maxlength="30" value="<%= Dtl.getD01CCYDSC()%>" readonly></td>
            <td nowrap width="15%" align="right">Porcentaje : </td>
            <td nowrap width="35%" align="left"> 
				<INPUT type="text" name="E01FEUPOR" maxlength="6" size="7" <%= read %> value="<%= Dtl.getE01FEUPOR().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
        </table> 
      </td>  
    </tr>
  </table>
  
    <table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type=submit name="Submit" value="Someter" onClick="goAction(5);" <%= disabled %>>
     	  </div>	
  		</td>
  		<td width="33%"> 
  		  <div align="center"> 
     		<input id="EIBSBTN" type=button name="Delete" value="Borrar" onClick="goAction(4);" <%= disabled %>>
     	  </div>	
  		</td>
  		<td width="34%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type=submit name="Exit" value="Salir" 
			<% if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))  { %>
			    onClick="goAction(1);" 
			<% } else { %>
				onClick="goAction(6);" 
			<% } %>>
     	  </div>	
  		</td>

  	</tr>	
  </table>	

  </form>
</body>
</html>
