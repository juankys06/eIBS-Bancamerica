<html> 
<head>
<title>Inventario de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "stockList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0003DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function showWarn(rowNum)
{
	page = webapp + "/servlet/datapro.eibs.products.JSECD0003?SCREEN=113&ROW=" + rowNum;
	CenterWindow(page,420,250,2);
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
<h3 align="center">Stock de Agencias
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_inv_gen_list.jsp , ECD0003">
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0003" >
  <input type=HIDDEN name="SCREEN" value="3">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
  <%
	if ( stockList.getNoResult() ) {
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
            <th align=CENTER nowrap width="10%"><div align="center">Tipo Pl.</div></th>
            <th align=CENTER nowrap width="30%">Agencia</th>
            <th align=CENTER nowrap width="30%">Stock Actual</th>
            <th align=CENTER nowrap width="30%">Mínimo</th>
            <th align=CENTER nowrap width="30%">Máximo</th>
          </tr>
     	<%
        stockList.initRow(); 
        while (stockList.getNextRow()) {
           	datapro.eibs.beans.ECD0003DSMessage msgPart = (datapro.eibs.beans.ECD0003DSMessage) stockList.getRecord();
			String chkWarn = (msgPart.getH03FLGWK2().equals("1")
							? "<a href=\"javascript:showWarn('"
								+ stockList.getCurrentRow()
								+ "')\"><img src=\"../images/warning01.gif\" alt=\"Warnings\" align=\"absmiddle\" border=\"0\" ></a>"
								: "");
     	%>               
        <TR>
			<TD NOWRAP ALIGN="LEFT"><%=Util.formatCell(msgPart.getE03CDRNPL())%> <%=chkWarn%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRBRD())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRQTY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRTIM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE03CDRTIR())%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>

</form>

</body>
</html>
