<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>ACH Operators Detail</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EACH110Record" class="datapro.eibs.beans.EACH11001Message"  scope="session" />
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


<H3 align="center">Mantenimiento de Operadores ACH<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_operators.jsp, EACH110"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH110" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">
  <INPUT TYPE=HIDDEN NAME="OPEDSC" VALUE="">

  <table  class="tableinfo" width="100%">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
        <tr id="trdark"> 
            <td nowrap width="10%" align="right">Código : </td>
            <td nowrap width="15%" align="left"> 
                <INPUT type="text" name="E01ACOCDE" size="11"  maxlength="10" <%= read %> value="<%= EACH110Record.getE01ACOCDE().trim()%>"
				<% if (!(userPO.getPurpose().equals("NEW"))) out.print(" readonly"); %>
				>
				<B><IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="Campo Obligatorio" align="bottom"></B>
			</td>
            <td nowrap width="10%" align="right">Nombre :</td>
			<td nowrap width="40%" align="left">
				<INPUT type="text" name="E01ACONME" maxlength="30" size="31" <%= read %> value="<%= EACH110Record.getE01ACONME().trim()%>">
			</td>
			<td nowrap width="25%">Fecha de Actualización : <%= datapro.eibs.master.Util.formatDate(EACH110Record.getE01ACORDM(),EACH110Record.getE01ACORDD(),EACH110Record.getE01ACORDY())%>
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
            <td nowrap width="15%" align="right">Tipo : </td>
            <td nowrap align="left" width="35%">
				<SELECT name="E01ACOTYP" <%= disabled %>>
					<OPTION <%= EACH110Record.getE01ACOTYP().trim().equals("O")?"Selected":"" %> value="O">Operador</OPTION>
					<OPTION <%= EACH110Record.getE01ACOTYP().trim().equals("D")?"Selected":"" %> value="D">Institucción Financiera</OPTION>
					<OPTION <%= EACH110Record.getE01ACOTYP().trim().equals("C")?"Selected":"" %> value="C">Compañía</OPTION>
				</SELECT>
            <B><IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></B></td>
            <td nowrap width="15%" align="right">Tipo de Oficina : </td>
            <td nowrap width="35%" align="left"> 
              	<SELECT name="E01ACOOCO" <%= disabled %>>
					<OPTION <%= EACH110Record.getE01ACOOCO().trim().equals("O")?"Selected":"" %> value="O">Principal</OPTION>
					<OPTION <%= EACH110Record.getE01ACOOCO().trim().equals("B")?"Selected":"" %> value="B">Sucursal</OPTION>
				</SELECT>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Dirección : </td>
			<td nowrap width="35%" align="left">
                <INPUT type="text" name="E01ACOADD" size="37" maxlength="36" <%= read %> value="<%= EACH110Record.getE01ACOADD().trim()%>">
            </td>
            <td nowrap width="15%" align="right">Estado : </td>
			<td nowrap width="35%" align="left">
                <INPUT type="text" name="E01ACOSTE" maxlength="2" size="3" <%= read %> value="<%= EACH110Record.getE01ACOSTE().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Ciudad : </td>
			<td nowrap width="35%" align="left">
				<INPUT type="text" name="E01ACOCIT" maxlength="15" size="16" <%= read %> value="<%= EACH110Record.getE01ACOCIT().trim()%>">
            </td>
            <td nowrap width="15%" align="right">Código Postal : </td>
			<td nowrap width="35%" align="left"> 
            	 <INPUT type="text" name="E01ACOZIP" maxlength="10" size="11" <%= read %> value="<%= EACH110Record.getE01ACOZIP().trim()%>">
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" align="right">Usando : </td>
			<td nowrap width="35%" align="left"> 
              <SELECT name="E01ACOUTC" <%= disabled %>>
					<OPTION <%= EACH110Record.getE01ACOUTC().trim().equals("0")?"Selected":"" %> value="0">Federal</OPTION>
					<OPTION <%= EACH110Record.getE01ACOUTC().trim().equals("1")?"Selected":"" %> value="1">Normal</OPTION>
					<OPTION <%= EACH110Record.getE01ACOUTC().trim().equals("2")?"Selected":"" %> value="2">Nueva</OPTION>
				</SELECT>
			</td>
            <td nowrap width="15%" align="right">Máximo Valor x Tr.: </td>
            <td nowrap>
				<INPUT type="text" name="E01ACOAMT" maxlength="17" size="18" <%= read %> value="<%= EACH110Record.getE01ACOAMT().trim()%>" onkeypress="enterDecimal()">
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" align="right">Sirviendo a : </td>
			<td nowrap width="35%" align="left"> 
            	<INPUT type="text" name="E01ACOFED" maxlength="9" size="10" <%= read %> value="<%= EACH110Record.getE01ACOFED().trim()%>">
				<A href="javascript:GetAchOperator('E01ACOFED','TYPE','OPEDSC','D')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
				</A>
            </td>
            <td nowrap width="15%" align="right">Nueva Ruta : </td> 
            <td nowrap width="35%" align="left"> 
              	<INPUT type="text" name="E01ACONEW" maxlength="9" size="10" <%= read %> value="<%= EACH110Record.getE01ACONEW().trim()%>">
				<A href="javascript:GetAchOperator('E01ACONEW','TYPE','OPEDSC','D')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="top">
				</A>
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
