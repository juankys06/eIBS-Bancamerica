<html> 
<head>
<title>Lista de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "clasList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(screen, option) {
	document.forms[0].SCREEN.value = screen;
	document.forms[0].option.value = option;
	document.forms[0].submit();
}

function setParameters(grouptype,groupcode,jerar,curren) {
    document.forms[0].GroupType.value = grouptype;
    document.forms[0].GroupCode.value = groupcode;
    document.forms[0].Jerar.value = jerar;
    document.forms[0].Curren.value = curren;
}

function cancelBub(){
  event.cancelBubble = true;
}

</script>  

</head>

<body>
<h3 align="center">Aprobaci&oacute;n de L&iacute;mites de Aprobaci&oacute;n de Ejecutivos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="params_approv_limits_list.jsp, EEJ0010"></h3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0010" >
  <input type=hidden name="SCREEN" value="100">
  <input type=hidden name="option" value="">
  <input type=hidden name="totalRow" value="0">
  <input type=hidden name="Row" value="0">
  <input type=hidden name="GroupType" value="<%= userPO.getHeader10()%>">
  <input type=hidden name="GroupCode" value="<%= userPO.getHeader11()%>">
  <input type=hidden name="Jerar" value="<%= userPO.getHeader12()%>">
  <input type=hidden name="Curren" value="<%= userPO.getHeader13()%>">
 
  <%if ( clasList.getNoResult() ) {%>
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
            <th align=CENTER nowrap>Tipo de<BR>Agrupacion</th>
            <th align=CENTER nowrap> 
              <div align="center">Codigo de<BR>Agrupacion</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Codigo de<BR>Jerarquia</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Status</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Fecha Ultima<BR>Actualizacion</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Usuario Ultima<BR>Actualizacion</div>
            </th>
          </tr>
          <%
                clasList.initRow();
				int k=1;
                while (clasList.getNextRow()) {
                 
                  out.println(clasList.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
  <%}%>
</form>
</body>
</html>
