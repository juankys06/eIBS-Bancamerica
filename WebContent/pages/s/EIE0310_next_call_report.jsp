<html>
<head>
<title>Next Event Report</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EIE0310Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Reporte Pr&oacute;ximo Evento - Pr&oacute;ximo Call/Put<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="next_call_report.jsp, EIE0310"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0310" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="REPORT" value="2">
  <input type=HIDDEN name="totalRow" value="0">
  
  <%
	if ( EIE0310Help.getNoResult() ) {
 %> 
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de b&uacute;squeda</b></p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 

          
  <br>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            
            <th align=CENTER nowrap > 
              <div align="center">Cliente</div>
            </th>
            <th align=CENTER nowrap >N&uacute;mero<br>
              Portafolio</th>
            <th align=CENTER nowrap > 
              <div align="center">Tipo de<br>
                Portafolio</div>
            </th>
            <th align=CENTER nowrap >Instrumento</th>
            <th align=CENTER nowrap >Moneda<br>
              Instrumento</th>
            <th align=CENTER nowrap >Cuenta<br>
              Transaccional</th>
            <th align=CENTER nowrap >Moneda<br>
              Cuenta</th>
            <th align=CENTER nowrap >Monto a<br>
              Pagar</th>
            <th align=CENTER nowrap > 
              <div align="center">Ultima Tasa<br>
                de Cambio</div>
            </th>
			<th align=CENTER nowrap > 
              <div align="center">Monto<br>
                Equivalente</div>
            </th>
           <th align=CENTER nowrap > 
              <div align="center">Custodio</div>
            </th>
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EIE0310Help.initRow();
				int k=1;
                while (EIE0310Help.getNextRow()) {
                 
                  out.println(EIE0310Help.getRecord());
                  k++;   
                }
              %> 
    </table>
   </div>
</Table>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
     try {
	     document.forms[0].index[0].click();
	 } catch (e) {
	     document.forms[0].index.click();
	 }
     
</SCRIPT>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR bgcolor="#FFFFFF"> 
      <TD colspan="2" ALIGN=LEFT>
        <div align="center">
          <input id="EIBSBTN" type=submit name="Submit" value="Imprimir Reporte">
        </div>
      </TD>
    </TR>
    <TR> 
      <TD WIDTH="50%" ALIGN=LEFT> <%
        if ( EIE0310Help.getShowPrev() ) {
      			int pos = EIE0310Help.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=3&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %> </TD>
      <TD WIDTH="50%" ALIGN=RIGHT> <% 
        if ( EIE0310Help.getShowNext() ) {
      			int pos = EIE0310Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEIE0310?SCREEN=3&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
  %> </TD>
    </TR>
  </TABLE>
<%}%>


  </form>

</body>
</html>
