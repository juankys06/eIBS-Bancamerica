<html> 
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "detList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function doPrint(){
	if(!window.print){
       var msg ="Debe actualizar su navegador para imprimir";
	   alert(msg);
	   return;
	}
	
    window.focus();
	window.print();

	return;
}

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Inventario de Plásticos<BR>Detalle
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_inv_detail_print.jsp , ECD0003">
</h3>
<hr size="4">
<FORM>
  
  <%
	if ( detList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
	  </div>
	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> 
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="20%"><div align="center"># Tarjeta</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Tipo</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado</div></th>
            <th align=CENTER nowrap width="20%">Agencia</th>
            <th align=CENTER nowrap width="20%">Fecha <br>Rec.</th>
            <th align=CENTER nowrap width="20%"><div align="center">Fecha <br>Entrega</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Usuario <BR>Entrega</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Fecha <br>Venc.</div></th>
            <th align=CENTER nowrap width="20%"># Distrib.</th>
          </tr>
     	<%
        detList.initRow(); 
        while (detList.getNextRow()) {
           datapro.eibs.beans.ECD0003DSMessage msgPart = (datapro.eibs.beans.ECD0003DSMessage) detList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRINI())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRTPL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRSTD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRBRD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE03CDRDAR(),msgPart.getE03CDRMOR(),msgPart.getE03CDRYER()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE03CDRDAD(),msgPart.getE03CDRMOD(),msgPart.getE03CDRYED()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRUSD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%= msgPart.getE03CDRMOX() + "/" + Util.formatYear(msgPart.getE03CDRYEX()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRNUM())%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>
  
<script language="JavaScript">
	doPrint();
</SCRIPT> 

</form>

</body>
</html>
