<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ECIF20002" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
	 var pg = "";
	 var acc = ""
	 for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "index") 
      	{
				if (document.forms[0].elements[n].checked == true) {
					acc = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
      }
	  if ( ok ) {
		  
		  pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=10&ACCNUM=" + acc +"&opt=" + op; 
		  CenterWindow(pg,600,500,2);

			
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
  function getParams(account){
    document.forms[0].accc.value = account;
 }
</SCRIPT>  

</head>

<BODY>
<h3 align="center"> Detalles de Deudas</h3>
<hr size="4">

<FORM >  
  <p> 
    <input type=HIDDEN name="SCREEN" value="3">
    <input type=HIDDEN name="opt" value="1">
    <input type=HIDDEN name="totalRow" value="0">
	<input type=HIDDEN name="accc" value="0">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Cliente:</div>
            </td>
            <td nowrap colspan="5" > 
              <div align="left">
                <input type=HIDDEN name="CUSTOMER" value="<%= userPO.getCusNum()%>">
                <%= userPO.getCusNum()%> - <%= userPO.getCusName()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p><%
	if (ECIF20002.getNoResult() ) {
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
        <div align="center"><a href="javascript:goAction(1)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:top.close()"><b>Salir</b></a></div>
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
              <div align="center">Producto</div>
            </th>
            <th align=CENTER nowrap >N&uacute;mero <br>de Cuenta</th>
            <th align=CENTER nowrap > Moneda</th>
            <th align=CENTER nowrap >Saldo</th>
            <th align=CENTER nowrap >Intereses</th>
            <th align=CENTER nowrap >Monto de <br>Reajuste</th>
            <th align=CENTER nowrap >Fecha de <br>Vencimiento</th>
          </tr>
        </table>

     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                ECIF20002.initRow();
				int k=1;
                while (ECIF20002.getNextRow()) {
                 
                  out.println(ECIF20002.getRecord());
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
