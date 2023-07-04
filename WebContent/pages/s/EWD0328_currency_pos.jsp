<html>
<head>
<title>Currency Rates</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "limPos" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<BODY>

<h3 align="center">Consulta Tasas de Moneda <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="currency_pos.jsp,EWD0328"></h3>
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
        
        <div align="center"> <font size="3"><b> </b></font><b>No hay resultados 
          que correspondan con su criterio de b&uacute;squeda</b></div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
  
    <table class="tableinfo" id="headTable1">
      <tr id="trdark"> 
        <th align=CENTER colspan="6">&nbsp;</th>
        <th align=CENTER colspan="2">Tasa<BR>Contra Valor</th>
        <th align=CENTER colspan="2">Tasa<BR>Moneda Extranjera</th>
        <th align=CENTER colspan="4">Tasas de Cambio a Futuro</th>
      </tr>
      <tr id="TRDARK"> 
        <th align=CENTER>Moneda</th>
        <th align=CENTER>Descripci&oacute;n</th>
        <th align=CENTER>Tasa Spot<BR>del D&iacute;a</th>
        <th align=CENTER>Tasa de<BR>Tolerancia</th>
        <th align=CENTER>Mult /<BR>Div</th>
        <th align=CENTER>D&iacute;as<BR>Base</th>
        <th align=CENTER>Compra</th>
        <th align=CENTER>Venta</th>
        <th align=CENTER>Compra</th>
        <th align=CENTER>Venta</th>
        <th align=CENTER>30<br>D&iacute;as</th>
        <th align=CENTER>90<br>D&iacute;as</th>
        <th align=CENTER>180<br>D&iacute;as</th>
        <th align=CENTER>1<br>A&ntilde;o</th>
      </tr>
      <%
      limPos.initRow();
      while (limPos.getNextRow()) {
       						    
      %> 
      <tr id="TRCLEAR"> 
        <td NOWRAP align=CENTER><%= limPos.getRecord(0)%></td>
        <td NOWRAP align=LEFT><%= limPos.getRecord(1)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(2)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(3)%></td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(4)%></td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(5)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(6)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(7)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(8)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(9)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(12)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(14)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(17)%></td>
        <td NOWRAP align=RIGHT><%= limPos.getRecord(19)%></td>
      </tr>
      <%
     }
   %> 
    </table>

 
  <%
     }   
  %> 
 
</form>
</body>
</html>
