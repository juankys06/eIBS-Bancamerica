<html>
<head>
<title>Cotizaciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "invList" class= "datapro.eibs.beans.JBList"  scope="session" />
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


function getParams(date1,date2,date3,timet) {

    document.forms[0].DATE1.value = date1;
    document.forms[0].DATE2.value = date2;
    document.forms[0].DATE3.value = date3;
    document.forms[0].TIMET.value = timet;
    
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

</SCRIPT>  

 <SCRIPT Language="Javascript">
       
      builtNewMenu(inst_app_opt);
 
   </SCRIPT>

</head>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

 out.println("<SCRIPT> initMenu();  </SCRIPT>");

%>
<h3 align="center">Aprobacion de Cotizaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_instruments.jsp, EWD0308"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0060A" >
  <input type=HIDDEN name="SCREEN" value="6">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CODE" value="<%= userPO.getIdentifier()%>">
  <input type=HIDDEN name="DATE1">
  <input type=HIDDEN name="DATE2">
  <input type=HIDDEN name="DATE3">
  <input type=HIDDEN name="TIMET">
  <h4>Basic Information</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right">Código de Instrumento :</div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> <%= userPO.getIdentifier().trim()%> </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= userPO.getHeader1()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> <%= userPO.getHeader2()%> </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> <%= userPO.getHeader3()%> </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%
	if ( invList.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width="100%" height="50%" >
    <tr>
      <TD > 
        <div align="center"> 
          <p><b>No hay cotizaciones definidas </b></p>
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
  <h4>&nbsp;</h4>
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha de <br>Cotización</div>
            </th>
            <th align=CENTER nowrap >Hora</th>
            <th align=CENTER nowrap > 
              <div align="center">Precio <br>MID</div>
            </th>
			<th align=CENTER nowrap > 
              <div align="center">Precio MID <br>antes de Cierre</div>
            </th>
			<th align=CENTER nowrap > 
              <div align="center">Precio BID <br>
                antes de Cierre</div>
            </th>
			<th align=CENTER nowrap > 
              <div align="center">Precio Pedido <br>
                antes de Cierre</div>
            </th>
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                invList.initRow();
				int k=1;
                while (invList.getNextRow()) {
                 
                  out.println(invList.getRecord());
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
     
     try {
	     document.forms[0].index[0].click();
	 } catch (e) {
	     document.forms[0].index.click();
	 }
     
</SCRIPT>
    <%}%> </p>
  </form>

</body>
</html>
