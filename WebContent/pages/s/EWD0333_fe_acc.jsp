<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Treasury </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "EWD0333Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

</head>
<body>
<form>

  <h3 align="center">Consulta - Cuentas en Moneda Extranjera <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_acc.jsp,EWD0333"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <%
	if ( EWD0333Help.getNoResult() ) {
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
            <th>N&uacute;mero de <BR>
              Cuenta</th>
            <th>       Contraparte           </th>
            <th>Tipo</th>
            <th>Acci&oacute;n<BR>
              Tomada</th>
            <th>Mon.</th>
            <th>Monto Moneda<br>Primaria</th>
            <th>Mon.</th>
            <th>Monto Moneda<br>Derivada</th>
            <th>Tasa</th>
	        <th>Fecha<br>
              valor</th>
	        <th>Estado</th>
  </tr>
  </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                EWD0333Help.initRow();
				int k = 1;
                while (EWD0333Help.getNextRow()) {
                    if (EWD0333Help.getFlag().equals("")) {
                    		out.println(EWD0333Help.getRecord());
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
        if ( EWD0333Help.getShowPrev() ) {
      			int pos = EWD0333Help.getFirstRec() - 101;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0333?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0333Help.getShowNext() ) {
      			int pos = EWD0333Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEWD0333?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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

