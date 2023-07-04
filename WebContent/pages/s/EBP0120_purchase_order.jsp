<%@ page import = "datapro.eibs.master.Util" %>
<html> 
<head>
<title>BAP Encabezado Orden de Compra</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EBP0120Record" class="datapro.eibs.beans.EBP012001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSBillsP.jsp"> </SCRIPT>
<script language="JavaScript">

function goAction(op) { 
	document.forms[0].SCREEN.value = op;
	if (op == 4) {
		if (!confirm("Do you want to delete this record?")) {
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


<H3 align="center">Orden de Compra<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bap_operators.jsp, EBP0120"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0120" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">

  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        <tr id="trdark"> 
            <td nowrap width="15%" align="right">No. de Orden : </td>
            <td nowrap width="35%" align="left"> 
                <INPUT type="text" name="E01BPONUM" size="10"  maxlength="9" <%= read %> value="<%= EBP0120Record.getE01BPONUM().trim()%>">
					<A href="javascript:GetCustomerDescId('E01BPONUM','','')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A>
				<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
			</td>
            <td nowrap width="15%" align="right">Referencia : </td>
		<td nowrap width="35%" align="left">
 
				<INPUT type="text" name="E01BPOVAC" size="26" maxlength="25" <%= read %> value="<%= EBP0120Record.getE01BPOVAC().trim()%>">  

		</td>
		</tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Basic Information </h4>  
  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Proveedor : </td>
            <td nowrap align="left" width="35%">
				<INPUT type="text" name="E01BPOVCO" size="10"  maxlength="9" <%= read %> value="<%= EBP0120Record.getE01BPOVCO().trim()%>">
				<A href="javascript:GetVendorBP('E01BPOVCO','E01BPVNM1')"">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0">
				</A> 
		        <B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B></td>
            <td nowrap width="15%" align="right">Nombre Proveedor : </td>
            <td nowrap width="35%" align="left">
            	<INPUT type="text" name="E01BPVNM1" size="40" value="<%= EBP0120Record.getE01BPVNM1().trim()%>" readonly></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Monto Total : </td>
			<td nowrap width="35%" align="left">
                <INPUT type="text" name="E01BPODCA" size="20" <%= read %> maxlength="15" value="<%= EBP0120Record.getE01BPODCA().trim()%>">
            	<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></B></td>
            <td nowrap width="15%" align="right"> </td>
			<td nowrap width="35%" align="left">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Fecha Efectiva : </td>
			<td nowrap width="35%" align="left">
		 		<INPUT type="text" name="E01BPOIDD" size="3" maxlength="2" <%= read %> value="<%= EBP0120Record.getE01BPOIDD().trim()%>" onkeypress="enterInteger()">
				<INPUT type="text" name="E01BPOIDM" size="3" maxlength="2" <%= read %> value="<%= EBP0120Record.getE01BPOIDM().trim()%>" onkeypress="enterInteger()">
				<INPUT type="text" name="E01BPOIDY" size="3" maxlength="2" <%= read %> value="<%= EBP0120Record.getE01BPOIDY().trim()%>" onkeypress="enterInteger()">
              <A href="javascript:DatePicker(document.forms[0].E01BPOIDD,document.forms[0].E01BPOIDM,document.forms[0].E01BPOIDY)">
              		<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0">
			  </A>
			  <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Mandatory Field" align="bottom"></IMG>
            </td>
            <td nowrap width="15%" align="right">Monto Maximo : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01BPOIAM" size="20" maxlength="15" <%= read %> value="<%= EBP0120Record.getE01BPOIAM().trim()%>" onkeypress="enterSignDecimal()">
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Estado : </td>
			<td nowrap width="35%" align="left"> 
              <SELECT name="E01BPOPST" <%= disabled %>>
					<OPTION <%= EBP0120Record.getE01BPOPST().trim().equals("A")?"Selected":"" %> value="A">Activa</OPTION>
					<OPTION <%= EBP0120Record.getE01BPOPST().trim().equals("I")?"Selected":"" %> value="I">Inactiva</OPTION>
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
     		<input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction(5);" <%= disabled %>>
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
