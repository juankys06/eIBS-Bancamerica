<html> 
<head>
<title>Historia de Autorizaciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "hisList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>
<h3 align="center">Historia de Aprobaci&oacute;n de Autorizaciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="special_autoriz_history.jsp, EEJ0050"></h3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0050" >
  <input type=hidden name="SCREEN" value="1">
  <input type=hidden name="option" value="">
  <input type=hidden name="totalRow" value="0">
  <input type=hidden name="Row" value="0">
  <input type=hidden name="Sup" value="<%= userPO.getHeader10()%>">
  <input type=hidden name="Ref" value="<%= userPO.getHeader11()%>">
  <%if ( hisList.getNoResult() ) {%>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay historia para esta referencia</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
              <td class=TDBKG>
                <div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Regresar"></div>
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

  <br>
 <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap>
            	<div align=CENTER>Ejecutivo<BR>Solicita</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Fecha/Hora<BR>Solicitud</div>
            </th>
            <th align=CENTER nowrap>
            	<div align=CENTER>Supervisor<BR>Autoriza</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Fecha/Hora<BR>Autorizacion</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Porcentaje<BR>Aprobado</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Valor<BR>Aprobado</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Tipo de<BR>Autorizaci&oacute;n</div>
            </th>
          </tr>
          <%
                hisList.initRow();
				int k=1;
                while (hisList.getNextRow()) {
                 
                  out.println(hisList.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
    <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Regresar">
  	</p>
  <%}%>
</form>
</body>
</html>
