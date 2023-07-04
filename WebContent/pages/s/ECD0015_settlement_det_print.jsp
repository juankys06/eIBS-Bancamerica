<html> 
<head>
<title>Consulta de Conciliación</title>
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
<h3 align="center">Consulta de Conciliación<BR>Detalle
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="settlement_det_print.jsp, ECD0015">
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
            <th align=CENTER nowrap width="10%"><div align="center">Fecha</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">ATM/POS ID</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Red</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Tarjeta</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Monto</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Traza</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Autorizaci&oacute;n</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Moneda</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado<BR>Conciliaci&oacute;n</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado<br>Compensaci&oacute;n</div></th>
          </tr>
     	<%
        detList.initRow(); 
        while (detList.getNextRow()) {
           datapro.eibs.beans.ECD0015DSMessage msgPart = (datapro.eibs.beans.ECD0015DSMessage) detList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CCRFET().substring(6,8)%>/<%=msgPart.getE01CCRFET().substring(4,6)%>/<%=msgPart.getE01CCRFET().substring(0,4)%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRATM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRRED())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRTAR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRAMO())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRTRA())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRAUT())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRCCY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRSTS())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRSTP())%></TD>
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
