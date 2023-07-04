<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Treasury</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "EWD0335Help" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<form>

  <h3 align="center">Consulta de Contratos del Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_acc.jsp, EWD0335"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <%
	if ( EWD0335Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> No hay resultados para su criterio de búsqueda </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<p>&nbsp;</p>
<TABLE  id="mainTable" class="tableinfo">
<TR > 
    <TD NOWRAP valign="top" width="100%" >
<TABLE id="headTable" width="100%" >
  <TR id="trdark"> 
    <th ALIGN=CENTER NOWRAP>Cuenta</th>
    <th ALIGN=CENTER NOWRAP>Contraparte</th>
    <th ALIGN=CENTER NOWRAP>Tipo</th>
    <th ALIGN=CENTER NOWRAP>Moneda</th>
    <th ALIGN=CENTER NOWRAP>Monto<BR>Principal</th>
    <th ALIGN=CENTER NOWRAP>Fecha de<BR>Proceso</th>
    <th ALIGN=CENTER NOWRAP>Fecha<br>Valor</th>
	<th ALIGN=CENTER NOWRAP>Fecha de<br>Vencimiento</th>
    <th ALIGN=CENTER NOWRAP>Monto<br>Original</th>
	<th ALIGN=CENTER NOWRAP>Estado</th>
  </tr>
  </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                EWD0335Help.initRow();
				int k = 1;
                while (EWD0335Help.getNextRow()) {
                    if (EWD0335Help.getFlag().equals("")) {
                    		out.println(EWD0335Help.getRecord());
							k ++;
                    }
                }
    %> 
</TABLE>

</div>
</td>
</tr>
</table>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EWD0335Help.getShowPrev() ) {
      			int pos = EWD0335Help.getFirstRec() - 101;
				String cun = userPO.getIdentifier();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0335?Pos=" + pos + "&RWDCUN=" + cun +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0335Help.getShowNext() ) {
      			int pos = EWD0335Help.getLastRec();
				String cun = userPO.getIdentifier();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0335?Pos=" + pos + "&RWDCUN=" + cun +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/next_records.gif\" ></A>");
        }
  %> 
   </TD>
 	</TR>
 	</TABLE>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
</SCRIPT>
<%
   }  
%>

</form>
</body>
</html>

