<html>
<head>
<title>Consulta de Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "msgTrs" class= "datapro.eibs.beans.ECD001302Message"  scope="session" />
<jsp:useBean id= "trsList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function getDetail(rowNum)
{
	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0013?SCREEN=3&ROW=" + rowNum;
	CenterWindow(page,800,500,2);
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
<h3 align="center">Consulta de Transacciones<BR>Resultados de la búsqueda
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="transac_inq_list.jsp, ECD0013"> 
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0011" >
  <input type=HIDDEN name="SCREEN" value="3">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Identificación :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT readonly type="text" name="E02CDRCID" size="21" maxlength="20" value="<%= msgTrs.getE02CDRCID() %>">
              </DIV>
            </TD>
            
            <TD nowrap width="16%"> 
              <DIV align="right">Nombre del Cliente :</DIV>
            </TD><TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT readonly type="text" name="E02CDRNAM" size="46" maxlength="45" value="<%= msgTrs.getE02CDRNAM() %>">
              </DIV>
            </TD>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Número del Cliente :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input readonly type="text" name="E02CDRCUN" size="10" maxlength="9" value="<%= msgTrs.getE02CDRCUN() %>">
              </div>
            </td>
            <TD nowrap width="16%"> 
              <DIV align="right">Número de Tarjeta :</DIV>
            </TD><TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT readonly type="text" name="E02CDRTAR" size="21" maxlength="20" value="<%= msgTrs.getE02CDRTAR() %>">
              </DIV>
            </TD>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right">Fecha de Entrega :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input readonly type="text" name="E02CDRDLD" size="3" maxlength="2" value="<%= msgTrs.getE02CDRDLD() %>">
                <input readonly type="text" name="E02CDRDLM" size="3" maxlength="2" value="<%= msgTrs.getE02CDRDLM() %>">
                <input readonly type="text" name="E02CDRDLY" size="5" maxlength="4" value="<%= msgTrs.getE02CDRDLY() %>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input readonly type="text" name="E02CDREXM" size="3" maxlength="2" value="<%= msgTrs.getE02CDREXM() %>">
                <input readonly type="text" name="E02CDREXY" size="5" maxlength="4" value="<%= msgTrs.getE02CDREXY() %>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <BR>
<%
	if ( trsList.getNoResult() ) {
 %>
  <TABLE class="tbenter" width="100%" >
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
            <th align=CENTER nowrap width="10%"><div align="center">Fecha</th>
            <th align=CENTER nowrap width="10%"><div align="center">Hora</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Mensaje</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Traza</div></th>
            <th align=CENTER nowrap width="15%"><div align="center">Autorización</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Monto Trs.</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Cód. Proceso</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado</div></th>
            <th align=CENTER nowrap width="15%"><div align="center">Cód. Respuesta</div></th>
          </tr>
     	<%
        trsList.initRow(); 
        while (trsList.getNextRow()) {
           datapro.eibs.beans.ECD001301Message msgPart = (datapro.eibs.beans.ECD001301Message) trsList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%= msgPart.getE01JRNFECH().substring(6,8) %>/<%= msgPart.getE01JRNFECH().substring(4,6) %>/<%= msgPart.getE01JRNFECH().substring(0,4) %></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%= Util.formatTime(msgPart.getE01JRNHORA()) %></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNMEN())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNDE11())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNDE38())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNDE4())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNDE3())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNCONC())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= trsList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01JRNDE39())%></a>
			</TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>

<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( trsList.getShowPrev() ) {
      			int pos = trsList.getFirstRec() - 50 - 1;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0013?SCREEN=2&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
  %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <%      
        if ( trsList.getShowNext() ) {
      			int pos = trsList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSECD0013?SCREEN=2&Pos=" + pos + "\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
   %> 
   </TD>
 	</TR>
</TABLE>

  <%}%>
</form>
</body>
</html>
