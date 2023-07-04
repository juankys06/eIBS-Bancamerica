<html>
<head>
<title>Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0309Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(code) {

    document.forms[0].CODE.value = code;
    
}

function cancelBub(){
  event.cancelBubble = true;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento de Ordenes Comerciales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_instruments.jsp, EWD0308"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0309" >
  <p>
    <input type=HIDDEN name="SCREEN" value="14">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="CODE">
    <%
	if ( EWD0309Help.getNoResult() ) {
 %></p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de búsqueda</b></p>
          
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

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" >
                <table id="headTable" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">Número de<br>
                Orden</div>
            </th>
            <th align=CENTER nowrap >Tipo de<BR>
              Operación</th>
            <th align=CENTER nowrap >Tipo</th>
            <th align=CENTER nowrap > Instrumento</th>
            <th align=CENTER nowrap > 
              <div align="center">Cliente</div>
            </th>
            <th align=CENTER nowrap >Fecha de<BR>
              Proceso</th>
            <th align=CENTER nowrap >Fecha<BR>
              Valor</th>
		    <th align=CENTER nowrap >Valor<BR>
              Nominal</th>
            <th align=CENTER nowrap >Cantidad</th>
		    <th align=CENTER nowrap >Precio <BR>
              Cliente</th>
            <th align=CENTER nowrap >Precio<BR>
              Banco</th>
			<th align=CENTER nowrap >Monto de<BR>
              Inversión</th>
			<th align=CENTER nowrap >Interés<BR>
              Acumulado</th>
			<th align=CENTER nowrap >Comisión</th>
			<th align=CENTER nowrap >Monto Total<BR>
              de Inversión</th>

            <th align=CENTER nowrap > 
              <div align="center">Estado</div>
            </th>
          </tr>
        </table>
<div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EWD0309Help.initRow();
				int k=1;
                while (EWD0309Help.getNextRow()) {
                 
                  out.println(EWD0309Help.getRecord());
                  k++;   
                }
              %> 
    </table>
   </div>
</Table>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EWD0309Help.getShowPrev() ) {
      			int pos = EWD0309Help.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=13&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0309Help.getShowNext() ) {
      			int pos = EWD0309Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.invest.JSEWD0309?SCREEN=13&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/next_records.gif\" ></A>");
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
     
     try {
	     document.forms[0].index[0].click();
	 } catch (e) {
	     document.forms[0].index.click();
	 }
     
</SCRIPT>

<%}%>


  </form>

</body>
</html>
