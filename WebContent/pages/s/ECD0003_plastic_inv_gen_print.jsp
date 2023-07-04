<html> 
<head>
<title>Inventario de Plastico</title>
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
<h3 align="center">Inventario de Plásticos<BR>General
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_inv_gen_print.jsp , ECD0003">
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
            <th align=CENTER nowrap width="50%"><div align="center">Tipo Pl.</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Estado</div></th>
            <th align=CENTER nowrap width="20%">Agencia</th>
            <th align=CENTER nowrap width="10%">Cantidad</th>
          </tr>
     	<%
        genList.initRow(); 
        while (genList.getNextRow()) {
           datapro.eibs.beans.ECD0003DSMessage msgPart = (datapro.eibs.beans.ECD0003DSMessage) genList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRNPL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRSTD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRBRD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRQTY())%></TD>
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
