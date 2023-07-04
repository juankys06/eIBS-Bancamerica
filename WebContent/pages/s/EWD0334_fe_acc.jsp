<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Treasury </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<jsp:useBean id= "EWD0334Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">

function showInqPlp(ref)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=400&E01DEAACC=" + ref;
	CenterWindow(page,560,500,2);
}


</SCRIPT>
</head>
<body>
<form>

  <h3 align="center">Consulta - Contratos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_acc.jsp, EWD0334"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <%
	if ( EWD0334Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center"> <b> No hay resultados que correspondan con su criterio 
          de b&uacute;squeda</b></div>
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
            <th align="CENTER" nowrap>N&uacute;mero de<BR>Cuenta</th>
            <th align="LEFT" nowrap>Contraparte</th>
            <th align="CENTER" nowrap>C&oacute;digo de<BR>Producto</th>
            <th align="CENTER" nowrap>Tipo</th>
            <th align="CENTER" nowrap>Acci&oacute;n<BR>Tomada</th>
            <th align="RIGHT" nowrap>Monto</th>
            <th align="CENTER" nowrap>Fecha<br>Valor</th>
            <th align="LEFT" nowrap>Estado</th>
          </tr>
        </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                EWD0334Help.initRow();
				int k = 1;
                while (EWD0334Help.getNextRow()) {
                    if (EWD0334Help.getFlag().equals("")) {
                    		out.println(EWD0334Help.getRecord());
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
        if ( EWD0334Help.getShowPrev() ) {
      			int pos = EWD0334Help.getFirstRec() - 31;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0334?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0334Help.getShowNext() ) {
      			int pos = EWD0334Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0334?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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

