<html>
<head>
<title>Currency Position</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "limPos" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


</head>

<BODY>

<h3 align="center">Consulta Posici&oacute;n de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="currency_pos.jsp,EWD0323"></h3>
<hr size="4">
  <form>
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <%
	if ( limPos.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        
        <div align="center"> <font size="3"><b> No hay resultados que correspondan 
          con su criterio de b&uacute;squeda</b></font> </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
  <p>
  <center>
    <table class="tableinfo" id="headTable1">
      <tr id="TRDARK"> 
        <th align=CENTER>Moneda</th>
        <th align=CENTER>Posici&oacute;n<BR>
          D&iacute;a Anterior</th>
        <th align=CENTER>Neto de <BR>
          Hoy </th>
        <th align=CENTER>Fx <BR>
          Futuro</th>
        <th align=CENTER>Pago Futuro<BR>
          a Terceros</th>
        <th align=CENTER>Saldo de<BR>
          Posici&oacute;n</th>
        <th align=CENTER>Posici&oacute;n de <BR>
          Intercambio</th>
      </tr>
      <%
      limPos.initRow();
      while (limPos.getNextRow()) {
       						    
      %> 
      <tr id="TRCLEAR"> 
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <%= limPos.getRecord(0)%></A></td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(1)%></div>
          </A> </td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(2)%></div>
          </A> </td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(3)%></div>
          </A> </td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(4)%></div>
          </A> </td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(5)%></div>
          </A> </td>
        <td NOWRAP align=CENTER><A HREF = "javascript:showCCYDetail('<%= limPos.getRecord(0)%>');"> 
          <div align="right"><%= limPos.getRecord(6)%></div>
          </A> </td>
      </tr>
      <%
     }
   %> 
    </table>
</center>
  <div align="center"></div>
  <h4>&nbsp;</h4>
  <%
     }   
  %> 
 
</form>
</body>
</html>
