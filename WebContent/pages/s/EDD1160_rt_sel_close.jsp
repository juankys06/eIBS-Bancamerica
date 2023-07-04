<html>
<head>
<title>Cierre de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD1160Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(convm) {

    document.forms[0].CONVM.value = convm;
	
    
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Cierre Masivo de Cuentas</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.misc.JSEDD1160" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CONVM" value="">
  
  <%
	if ( EDD1160Help.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
            <td class=TDBKG>
                <div align="center" ><a href="javascript:goAction(1)"><b>Nueva</b></a><a></a></div>
              </td>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
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
        <div align="center" ><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Tipo de <br> Producto</div>
            </th>
            <th align=CENTER nowrap width="30%"> 
              <div align="center">Tipo de <br> Convenio</div>
            </th>
            <th align=CENTER nowrap width="10%">Agencia</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Cuenta <br> Contable</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Centro <br> Costo</div>
            </th>

          </tr>
          <%
                EDD1160Help.initRow();
				int k=1;
                while (EDD1160Help.getNextRow()) {
                 
                  out.println(EDD1160Help.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("ACCNUM");
  
</SCRIPT>
</form>

</body>
</html>
