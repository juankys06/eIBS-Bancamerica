<html>
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "audList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function getDetail(rowNum)
{
	page = webapp + "/servlet/datapro.eibs.products.JSECD0011?SCREEN=20&ROW=" + rowNum;
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
<h3 align="center">Consulta de Auditoría<BR>Resultados de la búsqueda
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_audit_list_byuser.jsp, ECD0011"> 
</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0011" >
  <input type=HIDDEN name="SCREEN" value="20">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Usuario :</div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input readonly type="text" name="E01TDDUSR" size="17" maxlength="15" value="<%= userPO.getHeader1() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
            </td>
            <td nowrap width="20%"> 
            </td>
          </tr>
             
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Desde :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input readonly type="text" name="desde" size="17" maxlength="15" value="<%= userPO.getHeader2() %>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Hasta :</div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input readonly type="text" name="hasta" size="17" maxlength="15" value="<%= userPO.getHeader3() %>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <BR>
<%
	if ( audList.getNoResult() ) {
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
            <th align=CENTER nowrap width="20%"><div align="center">Fecha</th>
            <th align=CENTER nowrap width="10%"><div align="center">Hora</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Cód.</div></th>
            <th align=CENTER nowrap width="20%">Cliente</th>
            
            <th align=CENTER nowrap width="20%"><div align="center">Tarjeta</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Cuenta</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Programa</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Archivo</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Campo 1</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Valor Nuevo 1</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Campo 2</div></th>
            <th align=CENTER nowrap width="20%"><div align="center">Valor Nuevo 2</div></th>
            
          </tr>
     	<%
        audList.initRow(); 
        while (audList.getNextRow()) {
           datapro.eibs.beans.ECD0011DSMessage msgPart = (datapro.eibs.beans.ECD0011DSMessage) audList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')">
				<%= Util.formatDate(msgPart.getE01TDDDAY(),msgPart.getE01TDDMON(),msgPart.getE01TDDYEA()) %></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')">
								<%= Util.formatTime(msgPart.getE01TDDTIM()) %></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDOPE())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDCUN())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDTDD())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDACC())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDPGM())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDARC())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDFIL1())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDVAL1())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDFIL2())%></a>
			</TD>
			<TD NOWRAP ALIGN="CENTER">
				<a href="javascript:getDetail('<%= audList.getCurrentRow() %>')"><%=Util.formatCell(msgPart.getE01TDDVAL2())%></a>
			</TD>

		</TR>    		
    	<%}%>    
        </table>
    </table>
    
     
  <%}%>

</form>

</body>
</html>
