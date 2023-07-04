<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EIE003201Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">


function goAction() {

	document.forms[0].SCREEN.value=3;
	
	document.forms[0].submit();
  
}


function getParams(custodian) {

    document.forms[0].CUSTODIAN.value = custodian;    

}

function cancelBub(){
  event.cancelBubble = true;
}



</SCRIPT>  

</head>

<BODY>
<h3 align="center">Instrumento Posici&oacute;n Abierta por Custodio<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_custodian.jsp, EIE0320"></h3>
<hr size="4">

<FORM  METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0320" >  
  <p> 
    <input type=HIDDEN name="SCREEN" value="3">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="CUSTODIAN">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"><a href="javascript:showInstrumentInq(document.forms[0].INSTRUMENT.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="help" align="absbottom" border="0" > 
                Instrumento </a> :</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left"> 
                <input type=HIDDEN name="INSTRUMENT" value="<%= userPO.getHeader22()%>">
                <%= userPO.getHeader20()%></div>
            </td>
            <td nowrap >
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap >
              <div align="left"><%= userPO.getHeader17()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap colspan="5" ><%= userPO.getHeader21()%></td>
            <td nowrap >
              <div align="right">Monto Neto Vigente :</div>
            </td>
            <td nowrap >
              <div align="left"><%= userPO.getHeader16()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><%
	if ( EIE003201Help.getNoResult() ) {
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

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:showOpenTransCust(document.forms[0].CUSTODIAN.value,document.forms[0].INSTRUMENT.value)"><b>Detallar</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
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
              <div align="center">Custodio</div>
            </th>
            <th align=CENTER nowrap > <%= userPO.getHeader18()%></th>
          </tr>
        </table>

     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EIE003201Help.initRow();
				int k=1;
                while (EIE003201Help.getNextRow()) {
                 
                  out.println(EIE003201Help.getRecord());
                  k++;   
                }
              %> 
    </table>
          
        </div>
    
</Table>
  <br>
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
