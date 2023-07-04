<%@ page import = "datapro.eibs.master.Util" %>
<html> 
<head>
<title>ACH Operators Detail</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EACH300Record" class="datapro.eibs.beans.EACH30001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
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


<H3 align="center">Autorizaciones de Cliente ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_operators.jsp, EACH300"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH300" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">

  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        <tr id="trdark"> 
            <td nowrap width="15%" align="right">Código de Cliente : </td>
            <td nowrap width="35%" align="left"> 
                <INPUT type="text" name="E01ACACUN" size="10"  maxlength="9" <%= read %> value="<%= EACH300Record.getE01ACACUN().trim()%>">
					<A href="javascript:GetCustomerDescId('E01ACACUN','E01CUNDSC','')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A>
				<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
			</td>
            <td nowrap width="15%" align="right">Nombre :</td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01CUNDSC" size="40" value="<%= EACH300Record.getE01CUNDSC().trim()%>" readonly>
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
            <td nowrap width="15%" align="right">Cuenta : </td>
            <td nowrap align="left" width="35%">
				<INPUT type="text" name="E01ACAACC" size="13"  maxlength="12" <%= read %> value="<%= EACH300Record.getE01ACAACC().trim()%>">
				<A href="javascript:GetAccByClient('E01ACAACC','','RT','','')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A> 
		        <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B></td>
            <td nowrap width="15%" align="right"> </td>
            <td nowrap width="35%" align="left">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Compañía : </td>
			<td nowrap width="35%" align="left">
                <INPUT type="text" name="E01ACAOCD" size="11" <%= read %> maxlength="10" value="<%= EACH300Record.getE01ACAOCD().trim()%>">
				<A href="javascript:GetAchOperator('E01ACAOCD','TYPE','E01OCDDSC','C')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
				</A>
            	<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B></td>
            <td nowrap width="15%" align="right">Nombre : </td>
			<td nowrap width="35%" align="left">
                <INPUT type="text" name="E01OCDDSC" size="40" value="<%= EACH300Record.getE01OCDDSC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Fecha Efectiva : </td>
			<td nowrap width="35%" align="left">
		 		<INPUT type="text" name="E01ACAADD" size="3" maxlength="2" <%= read %> value="<%= EACH300Record.getE01ACAADD().trim()%>" onkeypress="enterInteger()">
				<INPUT type="text" name="E01ACAADM" size="3" maxlength="2" <%= read %> value="<%= EACH300Record.getE01ACAADM().trim()%>" onkeypress="enterInteger()">
				<INPUT type="text" name="E01ACAADY" size="3" maxlength="2" <%= read %> value="<%= EACH300Record.getE01ACAADY().trim()%>" onkeypress="enterInteger()">
              <A href="javascript:DatePicker(document.forms[0].E01ACAADD,document.forms[0].E01ACAADM,document.forms[0].E01ACAADY)">
				<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="middle" border="0">
			  </A><B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
            </td>
            <td nowrap width="15%" align="right">Monto Máximo : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACAMAM" size="20" maxlength="15" <%= read %> value="<%= EACH300Record.getE01ACAMAM().trim()%>" onkeypress="enterSignDecimal()">
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Estado : </td>
			<td nowrap width="35%" align="left"> 
              <SELECT name="E01ACASTS" <%= disabled %>>
					<OPTION <%= EACH300Record.getE01ACASTS().trim().equals("A")?"Selected":"" %> value="A">Activo</OPTION>
					<OPTION <%= EACH300Record.getE01ACASTS().trim().equals("I")?"Selected":"" %> value="I">Inactivo</OPTION>
				</SELECT>
	 		</td>
            <td nowrap width="15%" align="right"></td>
            <td nowrap height="19"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right"></td>
			<td nowrap width="35%" align="left"></td>
            <td nowrap width="15%" align="right"></td> 
            <td nowrap width="35%" align="left"></td>
          </tr>
        </table> 
      </td>  
    </tr>
  </table>
  
    <table width="100%">		
  	<tr>
  		<td width="33%">
  		  <div align="center"> 
     		<input id="EIBSBTN" type=button name="Submit" value="Someter" onClick="goAction(5);" <%= disabled %>>
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
