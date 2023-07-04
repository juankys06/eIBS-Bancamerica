<html>
<head>
<title>Lista de Aprobaciones Participaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "partList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(screen, option) {
	document.forms[0].SCREEN.value = screen;
	document.forms[0].option.value = option;
	document.forms[0].submit();
}

function setParameters(bank,priority) {
    document.forms[0].Bank.value = bank;
    document.forms[0].Priority.value = priority;
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;
</script>  

</head>

<body>
<h3 align="center">Aprobacion de Participaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ap_particip_list.jsp, EDL6010"></h3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL6010" >
  <input type=hidden name="SCREEN" value="100">
  <input type=hidden name="option" value="">
  <input type=hidden name="totalRow" value="0">
  <input type=hidden name="Row" value="0">
  <input type=hidden name="Bank" value="<%= userPO.getHeader10()%>">
  <input type=hidden name="Priority" value="<%= userPO.getHeader11()%>">

  <%if ( partList.getNoResult() ) {%>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de busqueda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
  <%} else { 
	  if ( !error.getERRNUM().equals("0")  ) {
	     error.setERRNUM("0");
	     out.println("<SCRIPT Language=\"Javascript\">");
	     out.println("       showErrors()");
	     out.println("</SCRIPT>");
	     }
  %> 
  <p> 
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(100,'')"><b>Detalle</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(200,'A')"><b>Aprobar</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="javascript:goAction(200,'R')"><b>Rechazar</b></a></div>
      </td>
      <td class=TDBKG width="25%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;</th>
            <th align=CENTER nowrap width="10%">Banco&nbsp;</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Tipo</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Prioridad</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Fecha <BR>
				Actualizacion</div>
            </th>
            <th align=CENTER nowrap width="15%"> 
              <div align="center">Usuario</div>
            </th>
          </tr>
          <%
                partList.initRow();
				int k=1;
                while (partList.getNextRow()) {
                 
                  out.println(partList.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
  <%}%>
</form>
</body>
</html>
