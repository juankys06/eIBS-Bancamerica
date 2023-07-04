<html> 
<head>
<title>Consulta de Conciliación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "genList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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
<h3 align="center">Consulta de Conciliación<BR>General
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="settlement_gen_print.jsp, ECD0015">
</h3>
<hr size="4">
<FORM>
  
  <%
	if ( genList.getNoResult() ) {
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
            <th align=CENTER nowrap width="14%"><div align="center">Fecha</div></th>
            <th align=CENTER nowrap width="14%"><div align="center">Moneda</div></th>
            <th align=CENTER nowrap width="14%"><div align="center">Monto Orig.</div></th>
            <th align=CENTER nowrap width="14%"><div align="center">Monto<br>Reversos</div></th>
            <th align=CENTER nowrap width="15%"><div align="center">Monto Concil.<br>Originales</div></th>
            <th align=CENTER nowrap width="15%"><div align="center">Monto Concil.<br>Reversos</div></th>
            <th align=CENTER nowrap width="14%"><div align="center">Monto<br>Compensado</div></th>
          </tr>
     	<%
        genList.initRow(); 
        while (genList.getNextRow()) {
           datapro.eibs.beans.ECD0015DSMessage msgPart = (datapro.eibs.beans.ECD0015DSMessage) genList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CCRFET().substring(6,8)%>/<%=msgPart.getE01CCRFET().substring(4,6)%>/<%=msgPart.getE01CCRFET().substring(0,4)%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CCRCCY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRMTO())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRMTR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRMCO())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRMCR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCCY(msgPart.getE01CCRAMP())%></TD>
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
