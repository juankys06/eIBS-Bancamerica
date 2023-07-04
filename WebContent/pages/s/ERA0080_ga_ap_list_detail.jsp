<html>
<head>
<title>Listado Detalles de la Garantía</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


</head>
<body nowrap >
<%
    collList.initRow();
    int max_row = 15;
    int row;
    int total_row;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < collList.getLastRow()) ) {
      total_row = collList.getLastRow() + 1;
    }
    else {
		total_row = row;       
    }
%> 

<SCRIPT LANGUAGE="JavaScript">
builtNewMenu(colla_A_opt);
initMenu();

</SCRIPT>

<SCRIPT>

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
   if (r > <%= max_row %>) {
    alert("Valor de registros fuera de rango numero maximo es 15");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>

 
<form>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="14">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
  
  <h3 align="center">Listado de Detalles de Garant&iacute;as<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ERA0010_ga_list_detail.jsp" width="35" height="35" ></h3>
  <hr size="4">
  <p align="left"></p>
 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="7%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" readonly value="<%=userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="15%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap width="37%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01RODCUN" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>" >
                </font></font></font></div>
            </td>
            <td nowrap width="11%" > 
              <div align="right">Referencia:</div>
            </td>
            <td nowrap width="21%" > 
              <input type="text" name="E02RODREF" size="9" maxlength="9" readonly value="<%= userPO.getIdentifier().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo">
    <tr id="trdark"> 
      <th align="center" nowrap >No Sec</th>
      <th align="center" nowrap > No Registro<br>
        Mercantil</th>
      <th align="center" nowrap > Descripcion Garantia</th>
      <th align="center" nowrap > Monto </th>
      <th align="center" nowrap > Fecha de Vcto</th>
    </tr>
    <%
   				if ( !collList.getNoResult() ) {
   				 collList.initRow();
                while (collList.getNextRow()) {
	      %> 
    <tr id="trclear"> 
      <td align="center" nowrap > 
        <input type="text"  name="E02RODSEQ_<%= collList.getCurrentRow() %>" size="2" maxlength="2"   readonly value="<%= collList.getRecord(0) %>" id="txtright">
      </td>
      <td align="center" nowrap > 
        <input type="text" name="E02RODNBR_<%= collList.getCurrentRow() %>" size="4" maxlength="4" readonly  value="<%= collList.getRecord(1) %>" >
      </td>
      <td align="center" nowrap> 
        <input type="text" name="E02RODDSC_<%= collList.getCurrentRow() %>" size="35" maxlength="35" readonly value="<%= collList.getRecord(2) %>" >
      </td>
      <td align="center" nowrap > 
        <input type="text" name="E02RODAMT_<%= collList.getCurrentRow() %>" size="17" maxlength="17" readonly value="<%= collList.getRecord(3) %>"  >
      </td>
      <td align="center" nowrap> 
        <input type="text" name="E02RODMD1_<%= collList.getCurrentRow() %>" size="2" maxlength="2" readonly value="<%= collList.getRecord(4) %>">
        <input type="text" name="E02RODMD2_<%= collList.getCurrentRow() %>" size="2" maxlength="2" readonly value="<%= collList.getRecord(5) %>" >
        <input type="text" name="E02RODMD3_<%= collList.getCurrentRow() %>" size="2" maxlength="2" readonly value="<%= collList.getRecord(6) %>">
      </td>
    </tr>
    <%
             }
          }  else {          
      %> 
    <TABLE class="tbenter" width=100% >
      <TR> 
        <TD> 
          <div align="center"> 
            <p>&nbsp;</p>
            <p><font size="3"><b>No hay resultados que correspondan a su criterio 
              de búsqueda </b></font> </p>
          </div>
        </TD>
      </TR>
    </TABLE>
    <%
   		}
   	%> 
  </table>
  <BR>
</form>
</body>
</html>
