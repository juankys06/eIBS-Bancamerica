<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EIE003003Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">


function goAction() {

	document.forms[0].SCREEN.value = 6;
	document.forms[0].submit();
  
}


function getParams(customer,portfolio,type) {

    document.forms[0].CUSTOMER.value = customer;
    document.forms[0].PORTFOLIO.value = portfolio;
    
}

function cancelBub(){
  event.cancelBubble = true;
}



</SCRIPT>  

</head>

<BODY>
<h3 align="center">Detalles de la Transacci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="position_inquiry.jsp, EIE0300"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0300" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="3">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="TYPEC" value="<%= userPO.getHeader4()%>">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><a href="javascript:showCustomerInq(document.forms[0].CUSTOMER.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Cliente :</a></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="CUSTOMER" size="9" maxlength="9" readonly value="<%= userPO.getCusNum()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="NAME" size="45" maxlength="45" readonly value="<%= userPO.getCusName()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="14%"> 
              <div align="right"><a href="javascript:showPortfolioInq(document.forms[0].CUSTOMER.value,document.forms[0].PORTFOLIO.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Portafolio : </a></div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="PORTFOLIO" size="12" maxlength="12" value="<%= userPO.getHeader8().trim()%>" readonly>
                <b> 
                <input type="text" name="DESCRIPTION" size="30" maxlength="30" readonly value="<%= userPO.getHeader9().trim()%>">
                </b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Tipo de Portafolio :</b></div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="TYPE" size="30" maxlength="30" value= "<%
				if (userPO.getHeader4().equals("D")) out.print("Discretionary");
				
				else out.print("Non Discretionary");%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="14%"> 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].CODE.value)"><img src="<%=request.getContextPath()%>/images/aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento :</a> </div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="CODE" size="7" maxlength="7" value="<%= userPO.getHeader5().trim()%>" readonly>
              <input type="text" name="DESCRIPTION" size="50" maxlength="50" value="<%= userPO.getHeader6()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><%
	if ( EIE003003Help.getNoResult() ) {
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

          
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" >
                
        <table id="headTable" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >N&uacute;mero de<br>
              Orden</th>
            <th align=CENTER nowrap >Transacci&oacute;n</th>
            <th align=CENTER nowrap >Fecha<br>
              Orden</th>
            <th align=CENTER nowrap >Fecha<br>
              Efectiva</th>
            <th align=CENTER nowrap >Valor<br>
              Nominal</th>
            <th align=CENTER nowrap >Cantidad</th>
            <th align=CENTER nowrap >Precio</th>
            <th align=CENTER nowrap >Monto <br>
              Inversi&oacute;n</th>
            <th align=CENTER nowrap >Intereses<br>
              por Pagar</th>
            <th align=CENTER nowrap >Comisi&oacute;n </th>
            <th align=CENTER nowrap >Monto <br>
              Total<br>
              Transacci&oacute;n</th>
             
            <th align=CENTER nowrap >Pignorado</th>
          </tr>
        </table>

     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EIE003003Help.initRow();
				int k=1;
                while (EIE003003Help.getNextRow()) {
                 
                  out.println(EIE003003Help.getRecord());
                  k++;   
                }
              %> 
    </table>
   </div>
</Table>
  <p>
    <SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();
     window.onresize=resizeDoc;
     
     
     
</SCRIPT>
    <%}%> </p>
</form>

</body>
</html>
