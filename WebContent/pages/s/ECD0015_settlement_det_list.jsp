<html> 
<head>
<title>Consulta de Conciliación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "detList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0015DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function getDetail(rowNum)
{
	page = webapp + "/servlet/datapro.eibs.products.JSECD0015?SCREEN=3&ROW=" + rowNum;
	CenterWindow(page,800,600,2);
}

function PrintPreview() {

	var pg = "<%=request.getContextPath()%>/pages/s/ECD0015_settlement_det_print.jsp";
	CenterWindow(pg,800,500,2);

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
<h3 align="center">Consulta de Conciliación<BR>Detalle
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="settlement_det_list.jsp, ECD0015">
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0015" >
  <input type=HIDDEN name="SCREEN" value="3">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
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
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=msgPart.getE01CCRFET().substring(6,8)%>/<%=msgPart.getE01CCRFET().substring(4,6)%>/<%=msgPart.getE01CCRFET().substring(0,4)%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRATM())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRRED())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRTAR())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRAMO())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRTRA())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRAUT())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRCCY())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRSTS())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= detList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRSTP())%></a>
			</TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
    
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( detList.getShowPrev() ) {
      			int pos = detList.getFirstRec() - 50 - 1;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0015?SCREEN=102&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( detList.getShowNext() ) {
      			int pos = detList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0015?SCREEN=102&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
   %> 
   </TD>
 	</TR>
</TABLE>

  <p align="center"> 
      <input id="EIBSBTN" type=button name="Print" OnClick="PrintPreview()" value="Imprimir">
    
  </p>
     
  <%}%>

</form>

</body>
</html>
