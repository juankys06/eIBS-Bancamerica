<html> 
<head>
<title>Consulta de Conciliación</title>
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
	page = webapp + "/servlet/datapro.eibs.products.JSECD0015?SCREEN=103&ROW=" + rowNum;
	CenterWindow(page,800,600,2);
}

function PrintPreview() {

	var pg = "<%=request.getContextPath()%>/pages/s/ECD0015_settlement_gen_print.jsp";
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
<h3 align="center">Consulta de Conciliación<BR>General
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="settlement_gen_list.jsp, ECD0015">
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0015" >
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
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=msgPart.getE01CCRFET().substring(6,8)%>/<%=msgPart.getE01CCRFET().substring(4,6)%>/<%=msgPart.getE01CCRFET().substring(0,4)%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01CCRCCY())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRMTO())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRMTR())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRMCO())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRMCR())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= genList.getCurrentRow() %>')"><%=Util.formatCCY(msgPart.getE01CCRAMP())%></a>
			</TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( genList.getShowPrev() ) {
      			int pos = genList.getFirstRec() - 50 - 1;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0015?SCREEN=101&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( genList.getShowNext() ) {
      			int pos = genList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0015?SCREEN=101&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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
