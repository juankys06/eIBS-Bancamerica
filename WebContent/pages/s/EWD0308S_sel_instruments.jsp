<html>
<head>
<title>Instruments</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0308Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function closeEnter(){
   	  enterACC.filters[0].apply();
      enterACC.style.visibility="hidden";
      enterACC.filters[0].Play();
}

function ShowEnterCod() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterACC.style.pixelTop=' + y);
     eval('enterACC.style.pixelLeft=' + x);
	 enterACC.filters[0].apply();
     enterACC.style.visibility="visible";
     enterACC.filters[0].Play();
	 document.forms[0].TABLEN.focus();
}

function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}

function getPayment(code) {
   document.forms[0].CODE.value = code;
   document.forms[0].opt.value = "1" ;
   document.forms[0].submit();
}

function getParams(code) {

    document.forms[0].CODE.value = code;
    
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento del Plan de Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_instruments.jsp, EWD0308"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0308S" >
  <p>
    <input type=HIDDEN name="SCREEN" value="2">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="CODE">
    <%
	if ( EWD0308Help.getNoResult() ) {
 %> </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp; </p>
  <TABLE class="tableinfo" width="100%" >
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
  <p>&nbsp; 
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="52%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Selecione el Instrumento</b></a></div>
      </td>
      <td class=TDBKG width="48%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <p><br>
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark">
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">Código</div>
            </th>
            <th align=CENTER nowrap >Descripción</th>
            <th align=CENTER nowrap > 
              <div align="center">Tipo</div>
            </th>
            <th align=CENTER nowrap >Símbolo</th>
            <th align=CENTER nowrap >ISIN</th>
            <th align=CENTER nowrap >CUSIP</th>
            <th align=CENTER nowrap >Estado</th>
            <th align=CENTER nowrap >Emisión</th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha de <br>Emisión</div>
            </th>
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EWD0308Help.initRow();
				int k=1;
                while (EWD0308Help.getNextRow()) {
                 
                  out.println(EWD0308Help.getRecord());
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

<%}%>


  </form>

</body>
</html>
