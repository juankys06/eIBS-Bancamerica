<%@ page import = "datapro.eibs.master.Util" %>
<html> 
<head>
<title>ACH Batch Header</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="EDL0375Record" class="datapro.eibs.beans.EDL037501Message"  scope="session" />
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
	String readOnly = userPO.getPurpose().equals("INQUIRY")?"readonly":"";
	if (!(userPO.getPurpose().equals("NEW") || userPO.getPurpose().equals("MAINT"))) read = " readonly ";
%>

<H3 align="center"><% if (userPO.getPurpose().equals("NEW")) out.print("Ingreso ");
   else if (userPO.getPurpose().equals("MAINT")) out.print("Mantenimiento ");
	else if (userPO.getPurpose().equals("INQUIRY"))out.print("Consulta ");%>
de Tablas de Calculos de Seguros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="insurance_table.jsp, EDL0375"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0375" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="O">

  <table  class="tableinfo" width="100%">
      <tr id="trdark"> 
    	<td nowrap> 
          <div align="right"><b>Código de Tabla: </b></div>
        </td>
        <td nowrap> 
            <input type="text" name="E01INSCOD" size="5" maxlength="4" value="<%= EDL0375Record.getE01INSCOD()%>" <%=readOnly%>>
        </td>  	
		<td nowrap> 
          <div align="right"><b>Descripción: </b></div>
        </td>
        <td nowrap> 
            <input type="text" name="E01INSDEC" size="30" maxlength="35" value="<%= EDL0375Record.getE01INSDEC()%>" <%=readOnly%>>
        </td>
	</tr>
	<tr id="trdark"> 
		<td nowrap> 
          <div align="right"><b>Monto por Unidad de Capital : </b></div>
        </td>
        <td nowrap colspan="3"> 
            <input type="text" name="E01INSAMT" size="10" maxlength="9" value="<%= EDL0375Record.getE01INSAMT()%>" <%=readOnly%>>
        </td>
  	</tr>
  </table>

  <h4>Rango de Edad</h4>
<TABLE class="tableinfo" align="center" style="width:'95%'">

	<tr id="trdark"> 
		<th colspan="2">Edad</th>
		<th colspan="3">Titulares</th>
  	</tr>
	<tr id="trdark"> 
		<td align="center">Lim. Inferior</td>
		<td align="center">Lim. Superior</td>
		<td align="center">Titular 1</td>
		<td align="center">Titular 2</td>
		<td align="center">Titular 3</td>
  	</tr>
	<%
			
			int count = 1;
			
			String fieldN;
			String fieldS;
			String fieldT1;
			String fieldT2;
			String fieldT3;
			while(count < 10){
			fieldN = "E01INSE" +count+"1";
			fieldS = "E01INSE" +count+"2";
			fieldT1 = "E01INSP" +count+"1";
			fieldT2 = "E01INSP" +count+"2";
			fieldT3 = "E01INSP" +count+"3";
%>
	
	<tr id="trclear">
		<td nowrap align="center"><input type="text" name="<%= fieldN %>" size="4" maxlength="3" value="<%= EDL0375Record.getField(fieldN).getString()%>" <%=readOnly%>></td>
		<td nowrap align="center"><input type="text" name="<%= fieldS %>" size="4" maxlength="3" value="<%= EDL0375Record.getField(fieldS).getString()%>" <%=readOnly%>></td>
		<td nowrap align="center"><input type="text" name="<%= fieldT1 %>" size="10" maxlength="12" value="<%= EDL0375Record.getField(fieldT1).getString()%>" <%=readOnly%>></td>
		<td nowrap align="center"><input type="text" name="<%= fieldT2 %>" size="10" maxlength="12" value="<%= EDL0375Record.getField(fieldT2).getString()%>" <%=readOnly%>></td>
		<td nowrap align="center"><input type="text" name="<%= fieldT3 %>" size="10" maxlength="12" value="<%= EDL0375Record.getField(fieldT3).getString()%>" <%=readOnly%>></td>
	</tr>
<%			count++;
			

		
		}



		 %>

	 
      
</TABLE>
<%			if(userPO.getPurpose().equals("NEW")|| userPO.getPurpose().equals("MAINT")){



		 %>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td colspan="3"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
      <td colspan="3"> 
        <div align="center"> </div>
      </td>
      <td colspan="3"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Salir" value="salir" onclick="javascript:checkClose()">
        </div>
      </td>
    </tr>
  </table>
<%						

		
		}else{



		 %>
 <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td colspan="3"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Salir" value="salir" onclick="javascript:checkClose()">
        </div>
      </td>
    </tr>
  </table>

<%			
		}
		 %>

  </form>
</body>
</html>
