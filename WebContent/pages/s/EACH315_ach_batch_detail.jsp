<%@ page import = "datapro.eibs.master.Util" %>
<html> 
<head>
<title>ACH Batch Header</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EACH315Record" class="datapro.eibs.beans.EACH31501Message"  scope="session" />
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

function showOfac(fieldValue){
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0092?shrAcc=" + fieldValue + "&shrCategory=ALL" + "&FromRecord=0&shrAction=INQ";
	CenterWindow(page,600,500,2);
 }

</SCRIPT>  
 

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
	boolean display=false;
    String read = "";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE"))) read = " readonly ";
%>


<H3 align="center">Transacción <%= EACH315Record.getE01ACUNUM().trim()%> para el Lote ACH Nro. <%= userPO.getIdentifier().trim() %>
	<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_batch_detail.jsp, EACH315"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH315" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="E01ACUBTH" VALUE="<%= userPO.getIdentifier().trim()%>">
  <INPUT TYPE=HIDDEN NAME="BATCH" VALUE="<%= userPO.getIdentifier().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01ACUNUM" VALUE="<%= EACH315Record.getE01ACUNUM().trim()%>">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">
  <INPUT TYPE=HIDDEN NAME="OPEDSC" VALUE="">
  
  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        <tr id="trdark"> 
            <td nowrap width="15%" align="right">Clase de Entrada : </td>
            <td nowrap width="35%" align="left"> 
                <INPUT type="text" name="E01ACUECD" size="4"  value="<%= EACH315Record.getE01ACUECD().trim()%>" readonly> 
				<INPUT type="text" name="E01ECDDSC" size="40" value="<%= EACH315Record.getE01ECDDSC().trim()%>" readonly>
			</td>
            <TD nowrap width="15%" align="right">Fecha Efectiva : </TD><TD nowrap width="35%" align="left">
		 		<INPUT type="text" name="E01ACUVDD" size="3" value="<%= EACH315Record.getE01ACUVDD().trim()%>" readonly>
				<INPUT type="text" name="E01ACUVDM" size="3" value="<%= EACH315Record.getE01ACUVDM().trim()%>" readonly>
				<INPUT type="text" name="E01ACUVDY" size="3" value="<%= EACH315Record.getE01ACUVDY().trim()%>" readonly>
            </TD>
			
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
            <td nowrap width="15%" align="right">Cuenta Interna: </td>
            <td nowrap align="left" width="35%">
				<INPUT type="text" name="E01ACUACC" size="13"  maxlength="12" value="<%= EACH315Record.getE01ACUACC().trim()%>" <%= read %>>
				<A href="javascript:GetAccount('E01ACUACC','','RT','')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
				<INPUT type="text" name="E01CUNDSC" value="<%= EACH315Record.getE01CUNDSC().trim()%>" size="35" readonly>

			</td>
            <TD nowrap width="15%" align="right">Transacción ACH : </TD>
			<TD nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUCDE" size="3"  maxlength="2" value="<%= EACH315Record.getE01ACUCDE().trim()%> <%= read %>">
				<A href="javascript:GetAchTransaction('E01ACUCDE','E01CDEDSC')"> 
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
				</A><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom">
				<INPUT type="text" name="E01CDEDSC" value="<%= EACH315Record.getE01CDEDSC().trim()%>" size="35" readonly>
			</TD>
          </tr>
		  <TR id="clear">
			<TD nowrap width="15%" align="right">Ruta : </TD>
			<TD nowrap align="left" width="35%">
				<INPUT type="text" name="E01ACUROU" size="11" maxlength="10" value="<%= EACH315Record.getE01ACUROU().trim()%> <%= read %>">
				<A href="javascript:GetAchOperator('E01ACUROU','TYPE','OPEDSC','D')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
				</A>
			</TD>
			<TD nowrap width="15%" align="right">Cuenta Externa : </TD>
			<TD nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUDAC" size="21" maxlength="20" value="<%= EACH315Record.getE01ACUDAC().trim()%>" <%= read %>> 
				<SELECT name="E01ACUACT">
					<OPTION <%= EACH315Record.getE01ACUACT().trim().equals("C")?"Selected":"" %> value="C">Cheques</OPTION>
					<OPTION <%= EACH315Record.getE01ACUACT().trim().equals("S")?"Selected":"" %> value="S">Ahorros</OPTION>
					<OPTION <%= EACH315Record.getE01ACUACT().trim().equals("L")?"Selected":"" %> value="L">Prestamos</OPTION>
					<OPTION <%= EACH315Record.getE01ACUACT().trim().equals("R")?"Selected":"" %> value="R">Tarjetas Crédito</OPTION>
				</SELECT> 
			</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap width="15%" align="right">Monto : </TD>
				<TD nowrap width="35%" align="left">
					<INPUT type="text" name="E01ACUAMT" size="16" maxlength="15" value="<%= EACH315Record.getE01ACUAMT().trim()%>" onkeypress="enterDecimal()" <%= read %>> 	
				 	<INPUT type="text" name="E01ACUCCY" size="4" maxlength="3" value="<%= EACH315Record.getE01ACUCCY().trim()%>" readonly> 	
				</TD>
				
				<TD nowrap width="15%" align="right">Referencia : </TD>
				<TD nowrap width="35%" align="left">
					<INPUT type="text" name="E01ACUIDN" size="16" maxlength="15" value="<%= EACH315Record.getE01ACUIDN().trim()%>" <%= read %>> 
				</TD>
			</TR>
			<tr id="trclear"> 
            <td nowrap width="15%" align="right">Beneficiario : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUNME" size="46" maxlength="45" value="<%= EACH315Record.getE01ACUNME().trim()%>" <%= read %>> 
				<% if (EACH315Record.getH01FLGWK3().trim().equals("3")) { %>
					<IMG border="0" src="<%=request.getContextPath()%>/images/warning_16.jpg" ALT="OFAC Match List" onClick="showOfac('<%= EACH315Record.getE01ACUNUM()%>')">
				<% } %>
			</td>
            <td nowrap width="15%" align="right">Correo Eléctronico : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUEMA" size="61" maxlength="60" value="<%= EACH315Record.getE01ACUEMA().trim()%>" <%= read %>> 
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Dirección :  </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUADR" size="46" maxlength="45" value="<%= EACH315Record.getE01ACUADR().trim()%>" <%= read %>> 
			</td>
            <td nowrap width="15%" align="right"> Adendda : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUADD" size="40" maxlength="80" value="<%= EACH315Record.getE01ACUADD().trim()%>" <%= read %>> 
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Ciudad, Estado, Código Postal: </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACUCSZ" size="46" maxlength="45" value="<%= EACH315Record.getE01ACUCSZ().trim()%>" <%= read %>> 
			</td>
            <td nowrap width="15%" align="right"></td>
			<td nowrap width="35%" align="left">
			</td>
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
<% 
 if (userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINTENANCE")){
	if (EACH315Record.getH01FLGWK2().trim().equals("W") || EACH315Record.getH01FLGWK2().trim().equals("A") ){%>
	<tr>
		<td width="100%" align="center" colspan="3">
			<input type="checkbox" name="H01FLGWK2" value="A" <% if (EACH315Record.getH01FLGWK2().trim().equals("A")) out.print(" checked"); %>>
			Aceptar con Advertencias
		</td>
	</tr>
	<% } %>
  	<tr>
  		<td width="33%" align="center">
  			<input id="EIBSBTN" type=submit name="Submit" value="Someter" onClick="goAction(5);">
     	 </td>
  		<td width="33%" align="center"> 
     		<input id="EIBSBTN" type=button name="Delete" value="Borrar" onClick="goAction(4);"
			<% if (userPO.getPurpose().equals("NEW")) out.print(" disabled"); %>>
  		</td>
  		<td width="34%" align="center">
     		<input id="EIBSBTN" type=submit name="Submit" value="Salir" onClick="goAction(1);">
  		</td>
  	</tr>	
<% } else { %>
  	<tr>
  		<td width="100%" align="center">
     		<input id="EIBSBTN" type=submit name="Submit" value="Salir" onClick="goAction(1);">
  		</td>
  	</tr>	

<% } %>
  </table>	

  </form>
</body>
</html>
