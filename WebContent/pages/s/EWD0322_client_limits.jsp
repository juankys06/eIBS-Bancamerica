<html>
<head>
<title>Customer Limits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "limPos" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<BODY>

<h3 align="center">Consulta L&iacute;mites de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_limits.jsp,EWD0322"></h3>
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
<table class="tableinfo">
  <tr > 
      <td nowrap > 
        <table  cellspacing="0" cellpadding="2" width="100%"   align="center">
        <tr>
             
            <td nowrap width="10%" align="left">&nbsp; </td>
             
            <td nowrap width="10%" align="right"> <b>Cliente :</b></td>
             <td nowrap width="12%" align="left">
         			<%= userPO.getCusNum()%>
             </td>
             
            <td nowrap width="6%" align="right"><b>Nombre :</b></td>
             <td nowrap width="14%" align="left">
         			<%= userPO.getCusName()%>
             </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

  <h4>L&iacute;mites Establecidos</h4>
<center>
    <table class="tableinfo" id="headTable1">
      <tr id="TRDARK"> 
        <th align=CENTER>Fecha<BR>
          Valor</th>
        <th align=CENTER>Moneda</th>
        <th align=CENTER>Monto<BR>
          Aprobado</th>
        <th align=CENTER>Monto<BR>
          Usado</th>
        <th align=CENTER>Monto<BR>
          Disponible</th>
        <th align=CENTER>Tipo</th>
        <th align=CENTER>C&oacute;digo<BR>
          Dealer</th>
        <th align=CENTER>N&uacute;mero<BR>
          Referencia</th>
        <th align=CENTER>Monto <br>Moneda Base<BR>
        </th>
        <th align=CENTER>Estado<BR>
          Contrato </th>
      </tr>
      <%
      limPos.initRow();
      while (limPos.getNextRow()) {
       	if (limPos.getFlag().equals("SET")) {
					    
      %> 
      <tr id="TRCLEAR"> 
        <td NOWRAP align=CENTER><%= limPos.getRecord(1)%></td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(2)%></td>
        <td NOWRAP align=CENTER> 
          <div align="right"><%= limPos.getRecord(3)%> </div>
        </td>
        <td NOWRAP align=CENTER> 
          <div align="right"><%= limPos.getRecord(4)%></div>
        </td>
        <td NOWRAP align=CENTER> 
          <div align="right"><%= limPos.getRecord(5)%></div>
        </td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(6)%></td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(7)%></td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(8)%></td>
        <td NOWRAP align=CENTER> 
          <div align="right"><%= limPos.getRecord(9)%></div>
        </td>
        <td NOWRAP align=CENTER><%= limPos.getRecord(10)%> </td>
      </tr>
   <%
   		}
     }
   %> 
    </table>
</center>
  <div align="center"></div>
  <h4>L&iacute;neas de Cr&eacute;dito</h4>
  <center>
  <table class="tableinfo" id="headTable2">
    <tr id="TRDARK"> 
        <th align=CENTER>Fecha<br>
          Valor</th>
        <th align=CENTER>Moneda</th>
        <th align=CENTER>Monto<br>
          Aprobado</th>
        <th align=CENTER>Monto<br>
          Usado</th>
        <th align=CENTER>Monto<br>
          Disponible</th>
        <th align=CENTER>Tipo</th>
    </tr>
    <%
     limPos.initRow();
     while (limPos.getNextRow()) {
       	if (!limPos.getFlag().equals("SET")) {
					    
  %> 
    <tr id="TRCLEAR"> 
      <td NOWRAP align=CENTER><%= limPos.getRecord(11)%></td>
      <td NOWRAP align=CENTER><%= limPos.getRecord(12)%></td>
      <td NOWRAP align=CENTER> 
        <div align="right"><%= limPos.getRecord(13)%> </div>
      </td>
      <td NOWRAP align=CENTER> 
        <div align="right"><%= limPos.getRecord(14)%></div>
      </td>
      <td NOWRAP align=CENTER> 
        <div align="right"><%= limPos.getRecord(15)%></div>
      </td>
      <td NOWRAP align=CENTER><%= limPos.getRecord(16)%></td>
    </tr>
    <%
    	}
     }
   
  %> 
  </table>
  </center>
   <%
     }   
  %> 
 
</form>
</body>
</html>
