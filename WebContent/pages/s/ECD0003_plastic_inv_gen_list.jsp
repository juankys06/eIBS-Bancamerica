<html> 
<head>
<title>Inventario de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "genList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function getDetail(rowNum)
{
	page = webapp + "/servlet/datapro.eibs.products.JSECD0003?SCREEN=103&ROW=" + rowNum;
	CenterWindow(page,600,500,2);
}

function PrintPreview() {

	var pg = "<%=request.getContextPath()%>/pages/s/ECD0003_plastic_inv_gen_print.jsp";
	CenterWindow(pg,720,500,2);

}

</SCRIPT>  

</head>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<BODY>
<h3 align="center">Inventario de Plásticos<BR>General
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_inv_gen_list.jsp , ECD0003">
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0003" >
  <input type=HIDDEN name="SCREEN" value="3">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
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
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE03CDRNPL())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE03CDRSTD())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE03CDRBRD())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE03CDRQTY())%></a>
			</TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <p align="center"> 
      <input id="EIBSBTN" type=button name="Print" OnClick="PrintPreview()" value="Imprimir">
    
  </p>
     
  <%}%>
  

</form>

</body>
</html>
