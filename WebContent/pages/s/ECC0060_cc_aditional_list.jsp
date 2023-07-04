<html> 
<head>
<title>Tarjeta de Débito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ecc0060help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<script language="JavaScript">

function goAction(op) {
	if (op == 1) {
		document.forms[0].SCREEN.value = '100';
		document.forms[0].submit(); 		
	}
	else if (op == 2) {
		document.forms[0].SCREEN.value = '300';
		document.forms[0].submit(); 			
	}
		
}


function getParams(code) {
    document.forms[0].CARDNUMBER.value = code;
}

function cancelBub(){
  event.cancelBubble = true;
}

//document.onclick= closeEnter;
</script>
</head>

<BODY onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/s/new_over.gif','<%=request.getContextPath()%>/images/s/maintenance_over.gif','<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif')">
<h3 align="center">Tarjetas de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cc_aditional_list.jsp,ECC0060"></h3>
<hr size="4">
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" >      
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="ACCOUNT" value="<%= userPO.getAccNum()%>">
  <INPUT TYPE=HIDDEN NAME="CARDNUMBER" value="">
  <INPUT TYPE=HIDDEN NAME="opt">
  <input type=HIDDEN name="totalRow">

  <%
	if ( ecc0060help.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
   	<TR>
	  <TD>     
      <div align="center"> 
   	      <table class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b> 
                        <input type="text" readonly name="E01CCRCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                        </font></font></font></div>
                    </td>
                  </tr>
                  <tr id="trdark"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Cuenta :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"> 
                        <input type="text" name="E01CCRCRA" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
                      </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Tipo : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b> 
                        <input type="text" name="E01TARTYP" size="3" maxlength="3" value="<% if (userPO.getHeader2().equals("D")) out.print("Debito"); else out.print("Credito");%>" readonly>
                        </b> </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>      

          <p><font size="3"><b> No existen registros, Por favor seleccione una de las siguientes opciones</b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td align=CENTER class=TDBKG style="cursor:hand"> <a href="javascript:goAction(1)"><b>Nuevo</b></a></td>
              <td align=CENTER class=TDBKG style="cursor:hand"> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a></td>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   </TABLE>
  <%   		
	}
	else {
%> 
   	      <table class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b> 
                        <input type="text" readonly name="E01CCRCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                        </font></font></font></div>
                    </td>
                  </tr>
                  <tr id="trdark"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Cuenta :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"> 
                        <input type="text" name="E01CCRCRA" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
                      </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Tipo : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b> 
                        <input type="text" name="E01TARTYP" size="3" maxlength="3" value="<% if (userPO.getHeader2().equals("D")) out.print("Debito"); else out.print("Credito");  %>" readonly>
                        </b> </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table> 
  <p>
  <table class="tbenter" width="100%">
    <tr> 
      <td align=CENTER class=TDBKG style="cursor:hand" width="20%"> <a href="javascript:goAction(1)"><b>Nuevo</b></a></td>
      <td align=CENTER class=TDBKG style="cursor:hand" width="20%"> <a href="javascript:goAction(2)"><b>Mantenimiento</b></a></td>     
      <td align=CENTER class=TDBKG style="cursor:hand" width="20%"> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a></td>
    </tr>
  </table>
  <center>
    <table  id="mainTable" class="tableinfo">
      <tr > 
        <td NOWRAP valign="top" width="100%" > 
          <table id="headTable" width="100%" >
            <tr id="trdark"> 
              <th align=CENTER nowrap >&nbsp;</th>
              <th align=CENTER nowrap > 
                <div align="center">Numero Tarjeta</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Cliente</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Fecha emision</div>
              </th>
              <th align=CENTER nowrap > 
                <div align="center">Fecha expiracion</div>
              </th>
            </tr>
          </table>
          <div id="dataDiv1" class="scbarcolor" > 
            <table id="dataTable" >
              <%
                ecc0060help.initRow();
				int k=1;
                while (ecc0060help.getNextRow()) {
                 
                  out.println(ecc0060help.getRecord());
                  k++;   
                }
              %> 
            </table>
          </div>
    </table>

  </center>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }

     resizeDoc();    
     window.onresize=resizeDoc;     
</SCRIPT>
  <%
     }   
  %> 
 
</form>
</body>
</html>

