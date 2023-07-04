<html>
<head>
<title>Back Office Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0326Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="JavaScript">
  function goAction(op) {
        
		  if(document.forms[0].acc.value == '' && op==2){
             alert("Debe seleccionar una cuenta válida"); 
             return; 
          }
       
        
        if(op==1){
		 document.forms[0].opt.value = 1;
        }  
		else if(op==2){
		 document.forms[0].opt.value = 2;
        }  
		if(op==3){
		 document.forms[0].opt.value = 3;
        }  
		document.forms[0].submit();

  }

</SCRIPT>  

<script language="JavaScript">
  function getValor(Account,Type, Dealer, actiontaken) {

    document.forms[0].acc.value = Account;
	document.forms[0].typ.value = Type;
	document.forms[0].dealer.value = Dealer;
	document.forms[0].actiontaken.value = actiontaken;
  }

</SCRIPT>  

<script language="JavaScript">
</script>
</head>

<BODY>
<h3 align="center">Mantenimiento de Back Office<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel.jsp_EWD0326BO"></h3>
<hr size="4">
<%
	if ( EWD0326Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b><FONT size="3">No hay resultados para su criterio de busqueda</FONT>  </b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
  <FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEWD0326BO" >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
    
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="opt">
  <input type=HIDDEN name="acc">
  <input type=HIDDEN name="dealer">
  <input type=HIDDEN name="typ">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="actiontaken">
  <p> 
  <table class="tbenter" width="100%">
    <tr> 
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </TD>
      <TD class=TDBKG width="16%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
    </tr>
  </table>
 
   <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
  <table id="headTable" width="100%" >
    <tr id="trdark"> 
      <th align=CENTER nowrap >&nbsp;</th>
      <th align=CENTER nowrap > 
        <div align="center">Cuenta</div>
      </th>
      <th align=CENTER nowrap >Producto</th>
      <th align=CENTER nowrap >Contraparte</th>
      <th align=CENTER nowrap > 
        <div align="center">Tipo</div>
      </th>
      <th align=CENTER nowrap > 
        <div align="center">Moneda</div>
      </th>
      <th align=CENTER nowrap > 
        <div align="center">Monto</div>
      </th>
    </tr>
    </table>
        <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
     <%
                EWD0326Help.initRow();
                int k=1;
                while (EWD0326Help.getNextRow()) {
                 
                    		out.println(EWD0326Help.getRecord());
							k ++;
                    
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
     
</SCRIPT>

  </form>
<%}%>
</body>
</html>
