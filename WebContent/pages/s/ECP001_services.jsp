<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "services" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



</head>

<BODY>
<h3 align="center">Cobro de Servicios Prestados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="services.jsp, ECP001"></h3>
<hr size="4">

<FORM  METHOD="post" >  
  <p> 
    <input type=HIDDEN name="SCREEN" value="3">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"> 
                Cliente :</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left">
                <input type=HIDDEN name="CUSTOMER" value="<%= userPO.getCusNum()%>">
                <%= userPO.getCusNum()%> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><%
	if ( services.getNoResult() ) {
 %> </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp; </p>
  <TABLE class="tbenter" width="100%" >
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
    	<TD NOWRAP valign="top" >
                
        <div id="dataDiv1" class="scbarcolor" >
   		<table id="dataTable" width=100%>
			<tr id="trdark"> 
              <th align=CENTER nowrap > 
              <div align="center">Concepto de Servicio Prestado</div>
              
            </th>
            <th align=CENTER nowrap >Sumario</th>
            <th align=CENTER nowrap >Importe Cobrado</th>
          </tr>
 
		<%
                services.initRow();
				int k=1;
                while (services.getNextRow()) {
                 
                  out.println(services.getRecord());
                  k++;   
                }
              %> 
              
   		<tr id="trdark"> 
              <td nowrap  colspan="2" > 
                <div align="right"><b>Totales por Servicios :</b></div>
              </td>

              <td nowrap   > 
                <div align="right"><%= userPO.getHeader21()%></div>
              </td>
            </tr>
    	</table>

        </div>
    
</Table>
  <br>
  
  <%}%> 
</form>

</body>
</html>
